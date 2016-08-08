/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.machinestatus;

import java.util.Calendar;
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
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.modules.lg.dao.LgMachineprofileDao;
import com.k2data.platform.modules.lg.dao.slice.LgDeviceYearStaticsDao;
import com.k2data.platform.modules.lg.entity.LgMachineprofile;
import com.k2data.platform.modules.lg.entity.machinestatus.LgRunningIndexCond;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceYearStatics;

/**
 * 机器运行概览Controller
 * @author lidong
 * @version 2016-06-24
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/machinestatus/runningindex")
public class LgRunningIndexController extends BaseController {
    
    @Autowired
    private LgDeviceYearStaticsDao lgDeviceYearStaticsDao;
    @Autowired
    private LgMachineprofileDao lgMachineprofileDao;
	
	@RequiresPermissions("lg:machinestatus:runningindex:view")
	@RequestMapping(value = "")
	public String list(LgRunningIndexCond cond, Model model) {
		model.addAttribute("lgRunningIndexCond", cond);
		return "modules/lg/machinestatus/lgRunningIndex";
	}
	
	/**
	 * 工作时间分析图表数据
	 * 
	 * @return
	 */
	@RequestMapping(value="workHourData")
	@ResponseBody
	public String workHourData(LgRunningIndexCond cond) {
        LgMachineprofile mpCond = new LgMachineprofile();
        mpCond.setMachineTypeMult(cond.getMachineTypeMult());
        mpCond.setOrderNumberMult(cond.getOrderNumberMult());
        mpCond.setProductTypeMult(cond.getProductTypeMult());
        mpCond.setWorkProvinceMult(cond.getWorkProvinceMult());
        mpCond.setWorkStateMult(cond.getWorkStateMult());
        mpCond.setSaleDateLess(new Date());
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -cond.getYearGreater());
        mpCond.setSaleDateGreaterOE(calendar.getTime());
        
        List<LgMachineprofile> mpList = lgMachineprofileDao.getMachineProfileList(mpCond);
        
        Map<String, Integer> countMap = new HashMap<String, Integer>();
        String[] deviceNoMultArr = new String[mpList.size()];
        int j = 0;
        for (LgMachineprofile lgMachineprofile : mpList) {
            deviceNoMultArr[j] = lgMachineprofile.getCode();
            
            String key = lgMachineprofile.getModelNumber() + lgMachineprofile.getOrderNumber() + lgMachineprofile.getJobWorkState();
            Integer count = countMap.get(key);
            if (count == null) {
                countMap.put(key, 1);
            } else {
                count++;
                countMap.put(key, count);
            }
            
            j++;
        }
        
        LgDeviceYearStatics dysCond = new LgDeviceYearStatics();
        dysCond.setDeviceNoMult(deviceNoMultArr);
        dysCond.setWorkDurationGreater(cond.getWorkDurationGreater());
        String currYear = DateUtils.formatDate(new Date(), "yyyy");
        dysCond.setWorkYearGreater(Integer.valueOf(currYear) - cond.getYearGreater());
        dysCond.setWorkProvinceMult(cond.getWorkProvinceMult());
        
        List<LgDeviceYearStatics> list = lgDeviceYearStaticsDao.getRunningIndexData(dysCond);
        
        Map<String, Map<String, Object>> dateMap = new HashMap<String, Map<String,Object>>();
        for (LgDeviceYearStatics lgDeviceYearStatics : list) {
            String key = lgDeviceYearStatics.getProductType() + lgDeviceYearStatics.getOrderNumber() + lgDeviceYearStatics.getJobWorkState();
            
            Map<String, Object> dysMap = dateMap.get(key);
            if (dysMap == null) {
                dysMap = new HashMap<String, Object>();
                dysMap.put("productType", lgDeviceYearStatics.getProductType());
                dysMap.put("orderNumber", lgDeviceYearStatics.getOrderNumber());
                dysMap.put("workState", lgDeviceYearStatics.getJobWorkState());
                
                dateMap.put(key, dysMap);
            }
            
            Map<String, Object> yearMap = new HashMap<String, Object>();
            Integer machineCount = countMap.get(key);
            
            yearMap.put("aliveCount", lgDeviceYearStatics.getAliveCount());
            if (machineCount == null) {
                yearMap.put("machineCount", 0);
                yearMap.put("alivePersent", 0);
                yearMap.put("workHourSvg", 0);
                yearMap.put("powerOnSvg", 0);
            } else {
                yearMap.put("machineCount", machineCount);
                yearMap.put("alivePersent", lgDeviceYearStatics.getAliveCount() / machineCount);
                yearMap.put("workHourSvg", lgDeviceYearStatics.getWorkHourSum() / machineCount);
                yearMap.put("powerOnSvg", lgDeviceYearStatics.getPowerOnSum() / machineCount);
            }
            
            dateMap.get(key).put(lgDeviceYearStatics.getWorkYearId() + "", yearMap);
        }
	    
	    return JSON.toJSONString(dateMap);
	}
	
}