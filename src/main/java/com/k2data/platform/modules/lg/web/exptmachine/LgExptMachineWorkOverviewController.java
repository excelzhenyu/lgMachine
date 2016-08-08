/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.exptmachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.common.utils.excel.ExportExcel;
import com.k2data.platform.common.utils.excel.ImportExcel;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.modules.lg.common.entity.LgDeviceMapLine;
import com.k2data.platform.modules.lg.common.entity.LgDeviceMapPoint;
import com.k2data.platform.modules.lg.common.utils.CalendarUtils;
import com.k2data.platform.modules.lg.dao.exptmachine.LgExptMachinePickDao;
import com.k2data.platform.modules.lg.entity.exptmachine.LgExptMachinePickDetail;
import com.k2data.platform.modules.lg.service.exptmachine.LgExptMachinePickDetailService;
import com.k2data.platform.modules.lg.service.exptmachine.LgExptMachineWorkOverviewService;

/**
 * 
 * @author K2DATA.wsguo
 * @date Jun 23, 2016 3:54:18 PM    
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/exptmachine/lgExptMachineWorkOverview")
public class LgExptMachineWorkOverviewController extends BaseController{

	@Autowired
	public LgExptMachinePickDetailService lgExptMachinePickDetailService;
	@Autowired
	public LgExptMachineWorkOverviewService lgExptMachineWorkOverviewService;

	@RequiresPermissions("lg:exptmachine:lgExptMachineWorkOverview:view")
	@RequestMapping(value = "point")
	public String point(HttpServletRequest request, Model model) {
		return "modules/lg/exptmachine/lgExptMachineWorkPointOverview"; 
	}

	@RequiresPermissions("lg:exptmachine:lgExptMachineWorkOverview:view")
	@RequestMapping(value = "line")
	public String line(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("deviceNo", request.getParameter("deviceNo"));
		model.addAttribute("exptDate", request.getParameter("exptDate"));
		return "modules/lg/exptmachine/lgExptMachineWorkLineOverview"; 
	}
	
	//@RequiresPermissions("lg:exptmachine:lgExptMachineWorkOverview:view")
	@RequestMapping(value = "track")
	public String track(HttpServletRequest request, HttpServletResponse response, Model model) {
		Date yesterday = CalendarUtils.getBeforeDate();
		model.addAttribute("dateBegin", yesterday);
		model.addAttribute("dateEnd", yesterday);
		return "modules/lg/exptmachine/lgExptMachineJobOverview"; 
	}
	
//	@RequestMapping(value = "getTrackData")
//	public @ResponseBody List<List<LgMapTrack>>  getTrackData(HttpServletRequest request, HttpServletResponse response, Model model) {
//		String deviceNo = request.getParameter("deviceNo");
//		String exptDate = request.getParameter("exptDate");
//		return lgExptMachineWorkOverviewService.getLineData(deviceNo, exptDate);
//	}

	@RequiresPermissions("lg:exptmachine:lgExptMachineWorkOverview:view")
	@RequestMapping(value = "datePointData")
	public @ResponseBody List<LgDeviceMapPoint> datePointData(HttpServletRequest request, HttpServletResponse response) {
		String deviceNos = request.getParameter("deviceNo");
		String dateBegin = request.getParameter("dateBegin");
		String dateEnd = request.getParameter("dateEnd");
		String[] deviceNo = deviceNos.split(",");
		return lgExptMachineWorkOverviewService.getPointData(deviceNo, dateBegin, dateEnd);
	}
	
	@RequiresPermissions("lg:exptmachine:lgExptMachineWorkOverview:view")
	@RequestMapping(value = "dateLineData")
	public @ResponseBody LgDeviceMapLine dataLineData(HttpServletRequest request, HttpServletResponse response) {
		String deviceNo = request.getParameter("deviceNo");
		String lineDate = request.getParameter("lineDate");
		return lgExptMachineWorkOverviewService.getLineData(deviceNo, lineDate);
	}
	
	
	
	/**
	 * 导入试验机器查询条件
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("lg:exptmachine:lgExptMachineWorkOverview:view")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public @ResponseBody List<String> importFile(MultipartFile file) {
		List<String> machineList = new ArrayList<String>();

		try {
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<LgExptMachinePickDetail> list = ei.getDataList(LgExptMachinePickDetail.class);
			//用户导入的整机编码内容列表
			for (LgExptMachinePickDetail machine : list){
				String machineId = StringUtils.trim(machine.getMachineId());
				if (!StringUtils.isBlank(machineId)){
					machineList.add(machineId);
				} 
			}
			//去重
			HashSet<String> tmpHashSet  = new HashSet<String>(machineList); 
			machineList.clear(); 
			machineList.addAll(tmpHashSet); 
			Collections.sort(machineList);
		} catch (Exception e) {
			e.printStackTrace();
			//addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		//model.addAttribute("machineList", machineList);
		return machineList;// point(request, model);
    }
	
	/**
	 * 下载导入试验机器明细数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	
	@RequiresPermissions("lg:exptmachine:lgExptMachineWorkOverview:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "试验机器导入模板.xlsx";
    		List<LgExptMachinePickDetail> list = Lists.newArrayList(); 
    		new ExportExcel("试验机器选取", LgExptMachinePickDetail.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"modules/lg/exptmachine/lgExptMachineWorkPointOverview"; 
    }
    @RequestMapping(value = "getMachineCodeByPickId")
	public @ResponseBody List<String> getMachineCodeByPickId(String pickIds) {
    	List<String> pickIdList = new ArrayList<String>();
    	if(StringUtils.isNotBlank(pickIds)) {
    		String[] pickId = pickIds.split(",");
        	pickIdList = Arrays.asList(pickId);
    	}
		return lgExptMachinePickDetailService.getMachineCodeByPickId(pickIdList);
		//return pickIdList;
	}
    
  
}
