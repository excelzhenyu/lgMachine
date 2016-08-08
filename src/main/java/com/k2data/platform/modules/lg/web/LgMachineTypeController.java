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
import com.k2data.platform.modules.lg.entity.LgMachineType;
import com.k2data.platform.modules.lg.entity.LgMachineprofile;
import com.k2data.platform.modules.lg.service.LgMachineTypeService;

/**
 * 整机类别Controller
 * @author wangshengguo
 * @version 2016-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgMachineType")
public class LgMachineTypeController extends BaseController {

	@Autowired
	private LgMachineTypeService lgMachineTypeService;
	
	@ModelAttribute
	public LgMachineType get(@RequestParam(required=false) String id) {
		LgMachineType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgMachineTypeService.get(id);
		}
		if (entity == null){
			entity = new LgMachineType();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:lgMachineType:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgMachineType lgMachineType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgMachineType> page = lgMachineTypeService.findPage(new Page<LgMachineType>(request, response), lgMachineType); 
		model.addAttribute("page", page);
		return "modules/lg/lgMachineTypeList";
	}

	@RequiresPermissions("lg:lgMachineType:view")
	@RequestMapping(value = "form")
	public String form(LgMachineType lgMachineType, Model model) {
		model.addAttribute("lgMachineType", lgMachineType);
		return "modules/lg/lgMachineTypeForm";
	}

	@RequiresPermissions("lg:lgMachineType:edit")
	@RequestMapping(value = "save")
	public String save(LgMachineType lgMachineType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgMachineType)){
			return form(lgMachineType, model);
		}

		if("".equals(lgMachineType.getId())) {
			long count = lgMachineTypeService.selectCountByEntity(lgMachineType);
			if(count == 0 ) {
				lgMachineTypeService.save(lgMachineType);
				addMessage(redirectAttributes, "保存整机类别成功");
				return "redirect:" + Global.getAdminPath() + "/lg/lgMachineType/?repage";
			} else {
				addMessage(model, "存在重复记录，请核实");
				return form(lgMachineType, model);
			}
		} else {
			LgMachineType entity = get(lgMachineType.getId());
			if( entity != null && entity.getMachineType().equals(lgMachineType.getMachineType()) 
					&& entity.getProductType().equals(lgMachineType.getProductType()) 
					&& entity.getOrderNumber().equals(lgMachineType.getOrderNumber())) {
				lgMachineTypeService.save(lgMachineType);
				addMessage(redirectAttributes, "保存整机类别成功");
				return "redirect:" + Global.getAdminPath() + "/lg/lgMachineType/?repage";
			} else {
				long count = lgMachineTypeService.selectCountByEntity(lgMachineType);
				if(count == 0 ) {
					lgMachineTypeService.save(lgMachineType);
					addMessage(redirectAttributes, "保存整机类别成功");
					return "redirect:" + Global.getAdminPath() + "/lg/lgMachineType/?repage";
				} else {
					addMessage(model, "存在重复记录，请核实");
					return form(lgMachineType, model);
				}
			}
		}
		
	}
	
	@RequiresPermissions("lg:lgMachineType:edit")
	@RequestMapping(value = "delete")
	public String delete(LgMachineType lgMachineType, RedirectAttributes redirectAttributes) {
		lgMachineTypeService.delete(lgMachineType);
		addMessage(redirectAttributes, "删除整机类别成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgMachineType/?repage";
	}
	
	@RequiresPermissions("lg:lgMachineType:view")
	@RequestMapping(value = "checkOrderNumber")
	@ResponseBody
	public String checkOrderNumber(LgMachineType lgMachineType){
		LgMachineType check = lgMachineTypeService.checkOrderNumber(lgMachineType);
		if(check!=null){
			return "false";
		}else{
			return "true";
		}
	}

}