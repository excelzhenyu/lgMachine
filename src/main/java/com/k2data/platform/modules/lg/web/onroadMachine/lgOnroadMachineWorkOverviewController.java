/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.onroadMachine;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.utils.MapUtils;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.modules.lg.dao.slice.LgDeviceDateStaticsDao;
import com.k2data.platform.modules.lg.dao.transporthistory.LgMachineTransportHistoryDao;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceDateStatics;
import com.k2data.platform.modules.lg.entity.transporthistory.LgMachineTransportHistory;

@Controller
@RequestMapping(value = "${adminPath}/lg/onroadmachine/onroadmachine")
public class lgOnroadMachineWorkOverviewController extends BaseController{

	@Autowired
	private LgMachineTransportHistoryDao lgMachineTransportHistoryDao;
	@Autowired
	private LgDeviceDateStaticsDao lgDeviceDateStaticsDao;
	
	/**
	 * 进入在途车辆位置追踪页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("lg:onroadmachine:onroadmachine:view")
	@RequestMapping(value = "point")
	public String point(HttpServletRequest request, Model model) {
		return "modules/lg/onroadmachine/lgOnroadMachineWorkPointOverview"; 
	}

	/**
	 * 进入在途车辆轨迹页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("lg:onroadmachine:onroadmachine:view")
	@RequestMapping(value = "line")
	public String line(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("deviceNo", request.getParameter("deviceNo"));
		model.addAttribute("transportDate", request.getParameter("transportDate"));
		return "modules/lg/onroadmachine/lgOnroadMachineWorkLineOverview"; 
	}
	
	/**
	 * 进入在途车辆详细页面
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("lg:onroadmachine:onroadmachine:view")
    @RequestMapping(value = "detail")
    @ResponseBody
    public String detail(String deviceNo, Double lng, Double lat) {
	    // 详细信息
	    LgMachineTransportHistory cond = new LgMachineTransportHistory();
	    cond.setDeviceNo(deviceNo);
	    LgMachineTransportHistory detail = lgMachineTransportHistoryDao.getSingleData(cond);
	    if (detail == null)
	        detail = new LgMachineTransportHistory();
	    
	    // 距离百分比
	    LgMachineTransportHistory location = lgMachineTransportHistoryDao.getTNPLocation(deviceNo);
	    Double distiance = MapUtils.getDistance(location.getTransportLat(), location.getTransportLng(), 
	                    location.getPurchaserLat(), location.getPurchaserLng());
	    Double moveedDis = MapUtils.getDistance(lat, lng, location.getTransportLat(), location.getTransportLng());
	    Integer present = (int) (moveedDis / distiance * 100);
	    
	    Map<String, Object> jsonMap = new HashMap<String, Object>();
	    jsonMap.put("detail", detail);
	    jsonMap.put("present", present);
	    
        return JSON.toJSONString(jsonMap);
	} 
	
	/**
	 * 轨迹数据
	 * 
	 * @param request
	 * @return
	 */
	@RequiresPermissions("lg:onroadmachine:onroadmachine:view")
	@RequestMapping("lineData")
	@ResponseBody
	public String lineData(HttpServletRequest request) {
	    String deviceNo = request.getParameter("deviceNo");
	    Date transportDate = new Date(Long.valueOf(request.getParameter("transportDate")));
	    
	    LgDeviceDateStatics cond = new LgDeviceDateStatics();
	    cond.setDeviceNo(deviceNo);
	    cond.setWorkDateGreaterOE(transportDate);
	    
	    Page<LgDeviceDateStatics> page = new Page<LgDeviceDateStatics>();
	    page.setOrderBy("a.workDate");
	    cond.setPage(page);
	    
	    return JSON.toJSONString(lgDeviceDateStaticsDao.findList(cond));
	}
	

	/**
	 * 在途车辆数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("lg:onroadmachine:onroadmachine:view")
	@RequestMapping(value = "historyData")
	@ResponseBody
	public String datePointData(LgMachineTransportHistory cond) {
	    // 右侧数据
	    Page<LgMachineTransportHistory> page = new Page<LgMachineTransportHistory>();
        page.setOrderBy("a.deviceNo, a.transportDate DESC");
        cond.setPage(page);
        cond.setOverflag(Global.NO);
	    List<LgMachineTransportHistory> list = lgMachineTransportHistoryDao.findList(cond);
	    
	    Set<String> deviceNoSet = new HashSet<String>();
	    List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
	    for (LgMachineTransportHistory lgMachineTransportHistory : list) {
	        deviceNoSet.add(lgMachineTransportHistory.getDeviceNo());
	        
	        Map<String, Object> innerMap = new HashMap<String, Object>();
	        innerMap.put("id", lgMachineTransportHistory.getId());
	        innerMap.put("deviceNo", lgMachineTransportHistory.getDeviceNo());
	        innerMap.put("transportDate", lgMachineTransportHistory.getTransportDate());
	        dataList.add(innerMap);
        }
	    
	    // 地图数据
	    List<LgDeviceDateStatics> maxDateList = new ArrayList<LgDeviceDateStatics>();
	    if (deviceNoSet.size() > 0) {
	        String[] deviceNoArr = new String[deviceNoSet.size()];
	        LgDeviceDateStatics maxCond = new LgDeviceDateStatics();
	        maxCond.setDeviceNoMult(deviceNoSet.toArray(deviceNoArr));
	        maxDateList = lgDeviceDateStaticsDao.getMaxDateData(maxCond);
	    }
	    
	    Map<String, Object> jsonMap = new HashMap<String, Object>();
	    jsonMap.put("dataList", dataList);
	    jsonMap.put("maxDateList", maxDateList);
	    
		return JSON.toJSONString(jsonMap); 
	}
	
}
