/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.common.utils.UploadUtils;
import com.k2data.platform.modules.lg.entity.LgMachineprofile;
import com.k2data.platform.modules.lg.entity.LgSensorlist;
import com.k2data.platform.modules.lg.service.LgMachineprofileService;

/**
 * 整机档案Controller
 * @author chenjingsi
 * @version 2016-05-04
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgMachineprofile")
public class LgMachineprofileController extends BaseController {

	@Autowired
	private LgMachineprofileService lgMachineprofileService;
	
	@ModelAttribute
	public LgMachineprofile get(@RequestParam(required=false) String id) {
		LgMachineprofile entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgMachineprofileService.get(id);
		}
		if (entity == null){
			entity = new LgMachineprofile();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:lgMachineprofile:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgMachineprofile lgMachineprofile, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page page = lgMachineprofileService.findPage(new Page<LgMachineprofile>(request, response), lgMachineprofile); 
		model.addAttribute("page", page);
		return "modules/lg/lgMachineprofileList";
	}
	
	@RequiresPermissions("lg:lgMachineprofile:view")
	@RequestMapping(value = "newList")
	public String newList(LgMachineprofile lgMachineprofile,HttpServletRequest request,HttpServletResponse response,Model model){
		Page page = lgMachineprofileService.findPage(new Page<LgMachineprofile>(request,response),lgMachineprofile);
		model.addAttribute("page",page);
		return "modules/lg/lgMachineprofileListNew";
	}

	@RequiresPermissions("lg:lgMachineprofile:view")
	@RequestMapping(value = "form")
	public String form(LgMachineprofile lgMachineprofile, Model model) {
		model.addAttribute("lgMachineprofile", lgMachineprofile);
		return "modules/lg/lgMachineprofileForm";
	}

	@RequiresPermissions("lg:lgMachineprofile:edit")
	@RequestMapping(value = "save")
	public String save(LgMachineprofile lgMachineprofile, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgMachineprofile)){
			return form(lgMachineprofile, model);
		}
		lgMachineprofileService.save(lgMachineprofile);
		addMessage(redirectAttributes, "保存整机档案成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgMachineprofile/?repage";
	}
	
	@RequiresPermissions("lg:lgMachineprofile:edit")
	@RequestMapping(value = "delete")
	public String delete(LgMachineprofile lgMachineprofile, RedirectAttributes redirectAttributes) {
		lgMachineprofileService.delete(lgMachineprofile);
		addMessage(redirectAttributes, "删除整机档案成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgMachineprofile/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value = "uploaderajax")
	public String Ajaxuploader(@RequestParam MultipartFile[] myfiles, HttpServletRequest request,
			HttpServletResponse response) {
		String info = null;
		JSONObject json = new JSONObject();
		
		int fileCount = myfiles.length;
		int success = 0;
		json.put("fileCount", fileCount);
		List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();
		
		// 设置响应给前台内容的数据格式
		response.setContentType("text/plain; charset=UTF-8");
		for (MultipartFile file : myfiles) {
			try {
				if (file != null) {
					/* if (!UploadUtils.checkFileType(file.getOriginalFilename(), UploadUtils.EXTFILE_EXCEL)) {
					 * return "只能上传(.xlsx,.xls)格式的Excel文件";
					 * } */
					Map<String, String> fileStatus = new HashMap<String, String>();
					String fileName = UploadUtils.uploadFile(file);
					System.out.println(fileName);
					fileStatus.put("fileName", fileName);
					fileStatus.put("oldFileName", file.getOriginalFilename());
					if (fileName != null && !fileName.equals("")) {
						success++;
					}
					fileList.add(fileStatus);
				}
				
			}
			catch (Exception e) {
				info = null;
				logger.error(e.getMessage(), e);
			}
		}
		
		json.put("files", fileList);
		json.put("success", success);
		String jsonstring = json.toString();
		System.out.println(jsonstring);
		return json.toString();
	}
	
	@RequiresPermissions("lg:lgMachineprofile:view")
	@RequestMapping(value = "checkCode")
	@ResponseBody
	public String checkCode(LgMachineprofile lgMachineprofile){
		LgMachineprofile check = lgMachineprofileService.checkCode(lgMachineprofile);
		if(check!=null){
			return "false";
		}else{
			return "true";
		}
	}
	
	@RequiresPermissions("lg:lgMachineprofile:view")
	@RequestMapping(value = "checkPinCode")
	@ResponseBody
	public String checkPinCode(LgMachineprofile lgMachineprofile){
		LgMachineprofile check = lgMachineprofileService.checkPinCode(lgMachineprofile);
		if(check!=null){
			return "false";
		}else{
			return "true";
		}
	}

}