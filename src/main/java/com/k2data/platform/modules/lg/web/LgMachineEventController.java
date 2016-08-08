/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.modules.lg.entity.LgGpsTemplateDetail;
import com.k2data.platform.modules.lg.entity.LgInspectionhistory;
import com.k2data.platform.modules.lg.entity.LgMachineevent;
import com.k2data.platform.modules.lg.entity.LgMachineinfo;
import com.k2data.platform.modules.lg.entity.LgMachineprofile;
import com.k2data.platform.modules.lg.entity.LgMachineprofileVO;
import com.k2data.platform.modules.lg.entity.LgRepairhistory;
import com.k2data.platform.modules.lg.entity.LgReplacehistory;
import com.k2data.platform.modules.lg.entity.LgServicehistory;
import com.k2data.platform.modules.lg.entity.LgUpkeephistory;
import com.k2data.platform.modules.lg.entity.SensorChartJson;
import com.k2data.platform.modules.lg.service.LgGpsTemplateDetailService;
import com.k2data.platform.modules.lg.service.LgInspectionhistoryService;
import com.k2data.platform.modules.lg.service.LgMachineeventService;
import com.k2data.platform.modules.lg.service.LgMachineinfoService;
import com.k2data.platform.modules.lg.service.LgMachineprofileService;
import com.k2data.platform.modules.lg.service.LgRepairhistoryService;
import com.k2data.platform.modules.lg.service.LgReplacehistoryService;
import com.k2data.platform.modules.lg.service.LgServicehistoryService;
import com.k2data.platform.modules.lg.service.LgUpkeephistoryService;
import com.k2data.platform.modules.lg.service.distinguish.LgDistinguishmissandfalserecordService;

/**
 * 整机档案Controller
 * @author chenjingsi
 * @version 2016-05-04
 */
@Controller


@RequestMapping(value = "${adminPath}/lg/lgMachineEvent")
public class LgMachineEventController extends BaseController {
	
	@Autowired
	private LgMachineprofileService lgMachineprofileService;
	@Autowired
	private LgMachineeventService lgMachineeventService;
	@Autowired
	private LgRepairhistoryService lgRepairhistoryService;
	@Autowired
	private LgServicehistoryService lgServicehistoryService;
	@Autowired
	private LgUpkeephistoryService lgUpkeephistoryService;
	@Autowired
	private LgReplacehistoryService lgReplacehistoryService;
	@Autowired
	private LgMachineinfoService lgMachineinfoService;
	@Autowired
	private LgInspectionhistoryService lgInspectionhistoryService;
	@Autowired
	private LgGpsTemplateDetailService LgGpsTemplateDetailService;
	
	
	@RequestMapping(value = "list")
	public String list(LgMachineprofile lgMachineprofile,HttpServletRequest request,HttpServletResponse response,Model model) {
		//获取整机档案信息
		LgMachineprofileVO lgMachineprofileVO = lgMachineprofileService.getProfileVO(lgMachineprofile);
		String machineId = lgMachineprofileVO.getId();
		//获取传感器列表
		List<LgGpsTemplateDetail> sensorList =LgGpsTemplateDetailService.getSensorList(lgMachineprofileVO.getCode());	
		//获取大事记数据
		LgMachineevent lgMachineevent = new LgMachineevent();
		lgMachineevent.setMachineId(machineId);
		List<LgMachineevent> eventList = lgMachineeventService.findList(lgMachineevent);
		//获取报修记录
		LgRepairhistory lgRepairhistory = new LgRepairhistory();
		lgRepairhistory.setMachineId(machineId);
		List<LgRepairhistory> repairList = lgRepairhistoryService.findList(lgRepairhistory);
		//获取维修记录
		LgServicehistory lgServicehistory = new LgServicehistory();
		lgServicehistory.setMachineId(machineId);
		List<LgServicehistory> serviceList = lgServicehistoryService.findList(lgServicehistory);
		//获取保养记录
		LgUpkeephistory lgUpkeephistory = new LgUpkeephistory();
		lgUpkeephistory.setMachineId(machineId);
		List<LgUpkeephistory> upkeepList = lgUpkeephistoryService.findList(lgUpkeephistory);
		//获取部件变更记录
		LgReplacehistory lgReplacehistory = new LgReplacehistory();
		lgReplacehistory.setMachineId(machineId);
		List<LgReplacehistory> replaceList = lgReplacehistoryService.findList(lgReplacehistory);
		//获取机器简述
		LgMachineinfo lgMachineinfo = new LgMachineinfo();
		lgMachineinfo.setMachineId(machineId);
		lgMachineinfo = lgMachineinfoService.get(lgMachineinfo);
		//获取巡查整改记录
		LgInspectionhistory lgInspectionhistory = new LgInspectionhistory();
		lgInspectionhistory.setMachineId(machineId);
		List<LgInspectionhistory> inspectionList = lgInspectionhistoryService.findList(lgInspectionhistory);
		
		
		model.addAttribute("lgMachineprofile",lgMachineprofileVO);
		model.addAttribute("sensorList",sensorList);
		model.addAttribute("eventList",eventList);
		model.addAttribute("repairList",repairList);
		model.addAttribute("serviceList", serviceList);
		model.addAttribute("upkeepList",upkeepList);
		model.addAttribute("replaceList",replaceList);
		model.addAttribute("lgMachineinfo",lgMachineinfo);
		model.addAttribute("inspectionList", inspectionList);
		return "modules/lg/lgMachineEvent";
	}
	
	@RequestMapping(value="sensorChart")
	public String sensorChart(HttpServletRequest request,HttpServletResponse response,Model model){
		String deviceNo = request.getParameter("deviceNo");
		model.addAttribute("deviceNo",deviceNo);
		return "modules/lg/lgSensorChart";
	}
	
	@RequestMapping(value="sensorList")
	@ResponseBody
	public Map<String,Object> sensorList(String deviceNo){
		List<LgGpsTemplateDetail> sensorList =LgGpsTemplateDetailService.getSensorList(deviceNo);
		String json = lgMachineeventService.genSensorListDom(sensorList);
		Map<String, Object> domMap = new HashMap<String, Object>();
		domMap.put("data",json);
		return domMap;
	}
	
	@RequestMapping(value="sensorListBottom")
	@ResponseBody
	public Map<String,Object> sensorListBottom(String deviceNo){
		List<LgGpsTemplateDetail> sensorList =LgGpsTemplateDetailService.getSensorList(deviceNo);
		String json = lgMachineeventService.genSensorListDomBottom(sensorList);
		Map<String, Object> domMap = new HashMap<String, Object>();
		domMap.put("data",json);
		return domMap;
	}
	
	@RequestMapping(value="getChartJson")
	@ResponseBody
	public List<SensorChartJson> getChartJson(@RequestParam(value="sensorMark[]") List<String> sensorMark,
																								@RequestParam(value="startDate") Date startDate,
																								@RequestParam(value="endDate") Date endDate) throws ParseException, IOException{
		List<SensorChartJson>  jsonList = lgMachineeventService.getChartJson(sensorMark,startDate,endDate);
		return jsonList;
	}
	
	@RequestMapping(value="getChartJsonBottom")
	@ResponseBody
	public List<SensorChartJson> getChartJsonBottom(@RequestParam(value="sensorMark[]") List<String> sensorMark,
																								@RequestParam(value="startDate") Date startDate,
																								@RequestParam(value="endDate") Date endDate) throws ParseException, IOException{
		List<SensorChartJson>  jsonList = lgMachineeventService.getChartJsonBottom(sensorMark,startDate,endDate);
		return jsonList;
	}

}