/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.utils.DateUtils;
import com.k2data.platform.common.utils.excel.ExportExcel;
import com.k2data.platform.common.utils.excel.ImportExcel;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.modules.lg.entity.DeviceNoVO;
import com.k2data.platform.modules.lg.entity.LgGpsTemplateDetail;
import com.k2data.platform.modules.lg.entity.LgLabMachineQuery;
import com.k2data.platform.modules.lg.entity.LgMachineprofile;
import com.k2data.platform.modules.lg.entity.LgWorktimeDistribution;
import com.k2data.platform.modules.lg.service.LgGpsTemplateDetailService;
import com.k2data.platform.modules.lg.service.LgLabMachineQueryService;
import com.k2data.platform.modules.lg.service.LgMachineprofileService;
import com.k2data.platform.modules.lg.service.slice.LgDeviceDateStaticsService;
import com.k2data.platform.modules.sys.entity.User;
import com.k2data.platform.modules.workcenter.entity.WorkcenterGroup;
import com.k2data.platform.modules.workcenter.utils.WorkCenterUtils;

/**
 * 试验机查询Controller
 * @author chenjingsi
 * @version 2016-05-30
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgLabMachineQuery")
public class LgLabMachineQueryController extends BaseController {

	@Autowired
	LgLabMachineQueryService lgLabMachineQueryService;
	@Autowired
	LgDeviceDateStaticsService lgDeviceDateStaticsService;
	@Autowired
	LgMachineprofileService lgMachineprofileService;
	@Autowired
	LgGpsTemplateDetailService lgGpsTemplateDetailService;
	
	@RequestMapping(value = {"list", ""})
	public String list(LgLabMachineQuery lgLabMachineQuery, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/lg/lgLabMachineQuery";
	}
	
	@RequestMapping(value="query",method=RequestMethod.POST)
	public String query(LgLabMachineQuery lgLabMachineQuery,HttpServletRequest request,HttpServletResponse response,Model model) throws InvalidFormatException, IOException, InstantiationException, IllegalAccessException{
		if(lgLabMachineQuery.getXlsFile().isEmpty()!=true){
			List<String> machineNoList = new ArrayList<String>();
			MultipartFile file = lgLabMachineQuery.getXlsFile();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<DeviceNoVO> list = ei.getDataList(DeviceNoVO.class);
			for (int i = 0; i < list.size(); i++) {
				machineNoList.add(list.get(i).getDeviceNo());
			}
			lgLabMachineQuery.setMachineNo(machineNoList);
		}
		Page page = lgLabMachineQueryService.findPage(new Page<LgLabMachineQuery>(request, response), lgLabMachineQuery); 
		model.addAttribute("page", page);
		return "modules/lg/lgLabMachineQuery";
	}
	
	  @RequestMapping(value = "export", method=RequestMethod.POST)
	    public String exportFile(LgLabMachineQuery lgLabMachineQuery, HttpServletRequest request, HttpServletResponse response, Model model) {
			try {
	            String fileName = "试验机数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
	            List<LgLabMachineQuery> list = lgLabMachineQueryService.findList(lgLabMachineQuery);
	    		new ExportExcel("试验机数据", LgLabMachineQuery.class).setDataList(list).write(response, fileName).dispose();
	    		return null;
			} catch (Exception e) {
			}
			return "modules/lg/lgLabMachineQuery";
	    }
	  
	  	@RequestMapping(value = "sensorModal")
		@ResponseBody
		public String SensorModal(String deviceNo,HttpServletRequest request, HttpServletResponse response) {
		  	List<LgGpsTemplateDetail> templateDetailList = lgGpsTemplateDetailService.getTemplateDetailList(deviceNo);
			Map<String, Object> domMap = new HashMap<String, Object>();
			domMap.put("data", LgLabMachineQueryService.genGroupListDom(templateDetailList));
			
			return JSON.toJSONString(domMap);
		}
	   
	
 

}