/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web;

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

import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.lg.entity.LgGpsTemplate;
import com.k2data.platform.modules.lg.entity.LgMachineGpsContrast;
import com.k2data.platform.modules.lg.service.LgMachineGpsContrastService;

/**
 * 整机与GPS设备对照Controller
 * @author chenjingsi
 * @version 2016-06-03
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgMachineGpsContrast")
public class LgMachineGpsContrastController extends BaseController {

	@Autowired
	private LgMachineGpsContrastService lgMachineGpsContrastService;
	
	@ModelAttribute
	public LgMachineGpsContrast get(@RequestParam(required=false) String id) {
		LgMachineGpsContrast entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgMachineGpsContrastService.get(id);
		}
		if (entity == null){
			entity = new LgMachineGpsContrast();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:lgMachineGpsContrast:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgMachineGpsContrast lgMachineGpsContrast, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgMachineGpsContrast> page = lgMachineGpsContrastService.findPage(new Page<LgMachineGpsContrast>(request, response), lgMachineGpsContrast); 
		model.addAttribute("page", page);
		return "modules/lg/lgMachineGpsContrastList";
	}

	@RequiresPermissions("lg:lgMachineGpsContrast:view")
	@RequestMapping(value = "form")
	public String form(LgMachineGpsContrast lgMachineGpsContrast, Model model) {
		if(lgMachineGpsContrast.getIsValid()==null){
			lgMachineGpsContrast.setIsValid(0);
		}
		model.addAttribute("lgMachineGpsContrast", lgMachineGpsContrast);
		return "modules/lg/lgMachineGpsContrastForm";
	}

	@RequiresPermissions("lg:lgMachineGpsContrast:edit")
	@RequestMapping(value = "save")
	public String save(LgMachineGpsContrast lgMachineGpsContrast, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgMachineGpsContrast)){
			return form(lgMachineGpsContrast, model);
		}
		lgMachineGpsContrastService.save(lgMachineGpsContrast);
		addMessage(redirectAttributes, "保存整机与GPS设备对照成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgMachineGpsContrast/?repage"; 
	}
	
	@RequiresPermissions("lg:lgMachineGpsContrast:edit")
	@RequestMapping(value = "delete")
	public String delete(LgMachineGpsContrast lgMachineGpsContrast, RedirectAttributes redirectAttributes) {
		lgMachineGpsContrastService.delete(lgMachineGpsContrast);
		addMessage(redirectAttributes, "删除整机与GPS设备对照成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgMachineGpsContrast/?repage";
	}
	
	@RequiresPermissions("lg:lgMachineGpsContrast:view")
	@RequestMapping(value="checkDeviceNo")
	@ResponseBody
	public String checkDeviceNo(LgMachineGpsContrast lgMachineGpsContrast){
		LgMachineGpsContrast check = lgMachineGpsContrastService.checkDeviceNo(lgMachineGpsContrast);
		if(check!=null){
			return "false";
		}else{
			return "true";
		}
	}
	
	@RequiresPermissions("lg:lgMachineGpsContrast:view")
	@RequestMapping(value="checkGpsNo")
	@ResponseBody
	public String checkGpsNo(LgMachineGpsContrast lgMachineGpsContrast){
		LgMachineGpsContrast check = lgMachineGpsContrastService.checkGpsNo(lgMachineGpsContrast);
		if(check!=null){
			return "false";
		}else{
			return "true";
		}
	}

}