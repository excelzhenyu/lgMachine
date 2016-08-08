/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.lg.entity.EcharsData;
import com.k2data.platform.modules.lg.entity.LgFalseStatistics;
import com.k2data.platform.modules.lg.entity.LgGpsTemplate;
import com.k2data.platform.modules.lg.entity.LgGpsTemplateDetail;
import com.k2data.platform.modules.lg.entity.LgMachineprofile;
import com.k2data.platform.modules.lg.entity.distinguish.LgDistinguishmissandfalserecord;
import com.k2data.platform.modules.lg.entity.distinguish.MissFalseDetail;
import com.k2data.platform.modules.lg.service.LgGpsTemplateDetailService;
import com.k2data.platform.modules.lg.service.LgGpsTemplateService;
import com.k2data.platform.modules.lg.service.LgLabMachineQueryService;
import com.k2data.platform.modules.lg.service.LgMachineeventService;
import com.k2data.platform.modules.lg.service.LgMachineprofileService;
import com.k2data.platform.modules.lg.service.distinguish.LgDistinguishmissandfalserecordService;
import com.k2data.platform.modules.lg.service.slice.LgDeviceDateStaticsService;

/**
 * 虚报漏报统计Controller
 * @author chenjingsi
 * @version 2016-06-02
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgMissAndFalseStatistics")
public class LgMissAndFalseStatisticsController extends BaseController {
	
	@Autowired
	LgDistinguishmissandfalserecordService lgDistinguishmissandfalserecordService;
	@Autowired
	LgDeviceDateStaticsService lgDeviceDateStaticsService;
	@Autowired
	LgMachineeventService lgMachineeventService;
	@Autowired
	LgMachineprofileService lgMachineprofileService;

	@RequestMapping(value="list")
	public String list(LgFalseStatistics lgFalseStatistics,HttpServletRequest request,HttpServletResponse response){
		
		if(lgFalseStatistics.getType()==1){
			return "modules/lg/distinguish/lgFalseStatistics";
		}
		
		if(lgFalseStatistics.getType()==2){
			return "modules/lg/distinguish/lgMissStatistics";
		}
		
		return "";
	}
	
	@RequestMapping(value="query")
	public String list(LgFalseStatistics lgFalseStatistics,HttpServletRequest request,HttpServletResponse response,Model model){
		if(lgFalseStatistics.getStartDate()==null){
			lgFalseStatistics.setStartDate(new Date().toString());
		}
		
		lgFalseStatistics.setType(0);
		List<LgFalseStatistics> wjjListModel = lgDistinguishmissandfalserecordService.queryCounts(lgFalseStatistics);
		lgFalseStatistics.setType(1);
		List<LgFalseStatistics> zzjListModel = lgDistinguishmissandfalserecordService.queryCounts(lgFalseStatistics);
		
		LgFalseStatistics  wjjNumbersModel = lgDistinguishmissandfalserecordService.queryWjjNumbers(lgFalseStatistics);
		LgFalseStatistics zzjNumbersModel = lgDistinguishmissandfalserecordService.queryZzjNumbers(lgFalseStatistics);
		LgFalseStatistics totalNumbersModel = lgDistinguishmissandfalserecordService.queryTotalNumbers(lgFalseStatistics);
		model.addAttribute("wjjListModel", wjjListModel);
		model.addAttribute("zzjListModel",zzjListModel);
		model.addAttribute("wjjNumbersModel",wjjNumbersModel);
		model.addAttribute("zzjNumbersModel", zzjNumbersModel);
		model.addAttribute("totalNumbersModel",totalNumbersModel);
		if(lgFalseStatistics.getSolutionType()==1){
			return "modules/lg/distinguish/lgFalseStatistics";
		}
		
		if(lgFalseStatistics.getSolutionType()==2){
			return "modules/lg/distinguish/lgMissStatistics";
		}
		return "";
	}
	
	@RequestMapping(value = "detailModal")
	@ResponseBody
	public String detailModal(LgFalseStatistics lgFalseStatistics,HttpServletRequest request, HttpServletResponse response) {
		List<LgDistinguishmissandfalserecord> recordList = lgDistinguishmissandfalserecordService.getModalList(lgFalseStatistics);
		Map<String, Object> domMap = new HashMap<String, Object>();
		domMap.put("data", LgDistinguishmissandfalserecordService.genGroupListDom(recordList));
		return JSON.toJSONString(domMap);
	}
	
	@RequestMapping(value = "saleModal")
	@ResponseBody
	public String saleModal(LgFalseStatistics lgFalseStatistics,HttpServletRequest request, HttpServletResponse response) {
		List<LgDistinguishmissandfalserecord> recordList = lgDistinguishmissandfalserecordService.getSaleModal(lgFalseStatistics);
		Map<String, Object> domMap = new HashMap<String, Object>();
		domMap.put("data", LgDistinguishmissandfalserecordService.genGroupListDom(recordList));
		return JSON.toJSONString(domMap);
	}
	
	
	@RequestMapping(value="chartData")
	@ResponseBody
	public Map<String,Object> data(LgFalseStatistics lgFalseStatistics){
		if(lgFalseStatistics.getStartDate()==null){
			lgFalseStatistics.setStartDate(new Date().toString());
		}
		List<EcharsData> wjjEdataList = new ArrayList<EcharsData>();
		List<EcharsData> zzjEdataList = new ArrayList<EcharsData>();
		List<String> wjjModels = new ArrayList<String>();
		List<String> zzjModels = new ArrayList<String>();
		lgFalseStatistics.setType(0);
		List<LgFalseStatistics> wjjList = lgDistinguishmissandfalserecordService.queryCounts(lgFalseStatistics);
		lgFalseStatistics.setType(1);
		List<LgFalseStatistics> zzjList = lgDistinguishmissandfalserecordService.queryCounts(lgFalseStatistics);
		for(LgFalseStatistics lg : zzjList){
				EcharsData zzjEdata = new EcharsData();
				zzjModels.add(lg.getModelNumber());
				zzjEdata.setName(lg.getModelNumber());
				zzjEdata.setValue(lg.getUnconfirmNumbers());
				zzjEdataList.add(zzjEdata);
			}
		for(LgFalseStatistics lg : wjjList){
			EcharsData wjjEdata = new EcharsData();
			wjjModels.add(lg.getModelNumber());
			wjjEdata.setName(lg.getModelNumber());
			wjjEdata.setValue(lg.getUnconfirmNumbers());
			wjjEdataList.add(wjjEdata);
		}
		
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("wjjModelNumber", wjjModels);
		data.put("wjjNumberData", wjjEdataList);
		data.put("zzjModelNumber", zzjModels);
		data.put("zzjNumberData", zzjEdataList);
		return data;
		}
	
		@RequestMapping(value="contentTable2")
		@ResponseBody
		public String contentTable2(LgFalseStatistics lgFalseStatistics ,HttpServletRequest request,HttpServletResponse response){
			if(lgFalseStatistics.getStartDate()==null){
				lgFalseStatistics.setStartDate(new Date().toString());
			}
			lgFalseStatistics.setType(0);
			List<LgFalseStatistics> wjjList = lgDistinguishmissandfalserecordService.querySaleCount(lgFalseStatistics);
			lgFalseStatistics.setType(1);
			List<LgFalseStatistics> zzjList = lgDistinguishmissandfalserecordService.querySaleCount(lgFalseStatistics);
			Map<String,Object> domMap = new HashMap<String,Object>();
			domMap.put("data", LgDistinguishmissandfalserecordService.genContentTableSale(wjjList,zzjList));
			return JSON.toJSONString(domMap);
		}
	
	@RequestMapping(value="detail")
	public String statisticsDetail(String deviceNo,HttpServletRequest request, HttpServletResponse response,Model model){
		MissFalseDetail detail = lgDeviceDateStaticsService.getMissAndFalseDetail(deviceNo);
		List<String> eventList = lgMachineeventService.getEventList(deviceNo);
		String machineId = lgMachineprofileService.getId(deviceNo);
		if(eventList.size()>0){
			detail.setEventList(eventList);
		}
		model.addAttribute("deviceNo",deviceNo);
		model.addAttribute("detail", detail);
		model.addAttribute("machineId",machineId);
		return "modules/lg/distinguish/lgFalseStatisticsDetail";
	}
	
	@RequestMapping(value="oilSumChart")
	@ResponseBody
	public Map<String,Object> oilSumChart(String deviceNo){
		List<String> oilSumDate = lgDeviceDateStaticsService.getOilSumChartDate(deviceNo);
		List<Integer> oilSumData = lgDeviceDateStaticsService.getOilSumChartData(deviceNo);
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("oilSumData", oilSumData);
		data.put("oilSumDate", oilSumDate);
		return data;
	}
	
	@RequestMapping(value="chartData2")
	@ResponseBody
	public Map<String,Object> chartData2(LgFalseStatistics lgFalseStatistics){
		if(lgFalseStatistics.getStartDate()==null){
			lgFalseStatistics.setStartDate(new Date().toString());
		}
		List<EcharsData> wjjEdataList = new ArrayList<EcharsData>();
		List<EcharsData> zzjEdataList = new ArrayList<EcharsData>();
		List<String> wjjModels = new ArrayList<String>();
		List<String> zzjModels = new ArrayList<String>();
		lgFalseStatistics.setType(0);
		List<LgFalseStatistics> wjjList = lgDistinguishmissandfalserecordService.querySaleCount(lgFalseStatistics);
		lgFalseStatistics.setType(1);
		List<LgFalseStatistics> zzjList = lgDistinguishmissandfalserecordService.querySaleCount(lgFalseStatistics);
		for(LgFalseStatistics lg : zzjList){
				EcharsData zzjEdata = new EcharsData();
				zzjModels.add(lg.getSaleName());
				zzjEdata.setName(lg.getSaleName());
				zzjEdata.setValue(lg.getUnconfirmNumbers());
				zzjEdataList.add(zzjEdata);
			}
		for(LgFalseStatistics lg : wjjList){
			EcharsData wjjEdata = new EcharsData();
			wjjModels.add(lg.getSaleName());
			wjjEdata.setName(lg.getSaleName());
			wjjEdata.setValue(lg.getUnconfirmNumbers());
			wjjEdataList.add(wjjEdata);
		}
		
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("wjjModelNumber", wjjModels);
		data.put("wjjNumberData", wjjEdataList);
		data.put("zzjModelNumber", zzjModels);
		data.put("zzjNumberData", zzjEdataList);
		return data;
		}
	
	
	
	

}