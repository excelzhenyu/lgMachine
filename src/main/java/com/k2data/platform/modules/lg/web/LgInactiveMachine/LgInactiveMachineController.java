/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.LgInactiveMachine;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.k2data.platform.common.utils.DateUtils;
import com.k2data.platform.common.utils.MapUtils;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.modules.lg.dao.citylist.LgSystemCityDao;
import com.k2data.platform.modules.lg.dao.slice.LgDeviceDateStaticsDao;
import com.k2data.platform.modules.lg.entity.citylist.LgSystemCity;
import com.k2data.platform.modules.lg.entity.inactivemachine.LgInactiveMachineCond;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceDateStatics;

/**
 * 呆滞机智能调拨Controller
 * @author lidong
 * @version 2016-06-24
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/inactivemachine/inactivemachine")
public class LgInactiveMachineController extends BaseController {
    
    @Autowired
    private LgDeviceDateStaticsDao lgDeviceDateStaticsDao;
    @Autowired
    private LgSystemCityDao lgSystemCityDao;
	
	@RequiresPermissions("lg:inactivemachine:inactivemachine:view")
	@RequestMapping(value = "")
	public String list(LgInactiveMachineCond cond, Model model) {
		model.addAttribute("lgInactiveMachineCond", cond);
		return "modules/lg/inactivemachine/lgInactiveMachine";
	}
	
	@RequestMapping("data")
	@ResponseBody
	public String data(final LgInactiveMachineCond cond) {
	    LgSystemCity lgSystemCityCond = new LgSystemCity();
	    lgSystemCityCond.setId(cond.getDemandCity());
	    LgSystemCity demandCity = lgSystemCityDao.get(lgSystemCityCond);
	    if (demandCity == null)
	        demandCity = new LgSystemCity();
	    
	    LgDeviceDateStatics deviceDateStaticsCond = new LgDeviceDateStatics();
	    deviceDateStaticsCond.setMachineTypeMult(cond.getMachineTypeMult());
	    deviceDateStaticsCond.setProductTypeMult(cond.getProductTypeMult());
	    deviceDateStaticsCond.setOrderNumberMult(cond.getOrderNumberMult());
	    
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date());
	    calendar.add(Calendar.MONTH, -cond.getInactiveMonth());
	    deviceDateStaticsCond.setProductDateLess(calendar.getTime());  // 当前日期 - 呆滞月
	    
	    deviceDateStaticsCond.setMachineStatus(2); // 待售
	    
	    List<LgDeviceDateStatics> dataList = lgDeviceDateStaticsDao.getInactiveDataList(deviceDateStaticsCond);
	    
	    List<Map<String, Object>> jsonList = new ArrayList<Map<String,Object>>();
	    
	    for (LgDeviceDateStatics lgDeviceDateStatics : dataList) {
	        Double distance = 0d;
	        
	        Double dLat = demandCity.getLatitude();
	        Double dLng = demandCity.getLongitude();
	        Double sLat = lgDeviceDateStatics.getLatitudes();
	        Double sLng = lgDeviceDateStatics.getLongitude();
	        
	        if (dLat != null && dLng != null && sLat != null && sLng != null) {
	            distance = MapUtils.getDistance(dLat, dLng, sLat, sLng);
	        }
            
            Map<String, Object> innerMap = new HashMap<String, Object>();
            innerMap.put("productType", lgDeviceDateStatics.getModelNumber());
            innerMap.put("orderNumber", lgDeviceDateStatics.getOrderNumber());
            innerMap.put("inactiveCount", lgDeviceDateStatics.getInactiveCount());
            innerMap.put("location", lgDeviceDateStatics.getLocation());
            innerMap.put("distance", distance);
            innerMap.put("saleUnit", lgDeviceDateStatics.getSaleUnit());
            innerMap.put("productDate", lgDeviceDateStatics.getProductDate());
            innerMap.put("inactiveDays", DateUtils.pastDays(lgDeviceDateStatics.getProductDate()));
            
            innerMap.put("latitude", sLat);
            innerMap.put("longitude", sLng);
            
            jsonList.add(innerMap);
        }
	    
	    // 按条件排列，默认距离升序 
	    Collections.sort(jsonList, new Comparator<Map<String, Object>>() {

            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                if (LgInactiveMachineCond.DISTANCE_ASC == cond.getOrderType()) { 
                    return ((Double)o1.get("distance")).compareTo((Double)o2.get("distance")); 
                } else if (LgInactiveMachineCond.DISTANCE_DESC == cond.getOrderType()) { 
                    return ((Double)o2.get("distance")).compareTo((Double)o1.get("distance")); 
                } else if (LgInactiveMachineCond.INACTICE_DAYS_ASC == cond.getOrderType()) { 
                    return ((Long)o1.get("inactiveDays")).compareTo((Long)o2.get("inactiveDays")); 
                } else if (LgInactiveMachineCond.INACTICE_DAYS_DESC == cond.getOrderType()) { 
                    return ((Long)o2.get("inactiveDays")).compareTo((Long)o1.get("inactiveDays")); 
                } else { 
                    return ((Double)o1.get("distance")).compareTo((Double)o2.get("distance")); 
                } 
            }
        });
	    
	    Map<String, Object> jsonMap = new HashMap<String, Object>();
	    jsonMap.put("data", jsonList);
	    if (demandCity.getLatitude() != null && demandCity.getLongitude() != null) {
	        jsonMap.put("demandCityLat", demandCity.getLatitude());
	        jsonMap.put("demandCityLng", demandCity.getLongitude());
	    }
	    
	    return JSON.toJSONString(jsonMap);
	}
	
}