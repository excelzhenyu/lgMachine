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
import com.k2data.platform.modules.lg.entity.LgSensorlist;
import com.k2data.platform.modules.lg.entity.LgSensorlistVO;
import com.k2data.platform.modules.lg.service.LgSensorlistService;

/**
 * 整机传感器清单Controller
 * @author chenjingsi
 * @version 2016-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgSensorlist")
public class LgSensorlistController extends BaseController {

	@Autowired
	private LgSensorlistService lgSensorlistService;
	
	@ModelAttribute
	public LgSensorlist get(@RequestParam(required=false) String id) {
		LgSensorlist entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgSensorlistService.get(id);
		}
		if (entity == null){
			entity = new LgSensorlist();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:lgSensorlist:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgSensorlist lgSensorlist, HttpServletRequest request, HttpServletResponse response, Model model) {
		 Page page = lgSensorlistService.findPage(new Page<LgSensorlist>(request, response), lgSensorlist); 
		model.addAttribute("page", page);
		return "modules/lg/lgSensorlistList";
	}

	@RequiresPermissions("lg:lgSensorlist:view")
	@RequestMapping(value = "form")
	public String form(LgSensorlist lgSensorlist, Model model) {
		model.addAttribute("lgSensorlist", lgSensorlist);
		return "modules/lg/lgSensorlistForm";
	}

	@RequiresPermissions("lg:lgSensorlist:edit")
	@RequestMapping(value = "save")
	public String save(LgSensorlist lgSensorlist, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgSensorlist)){
			return form(lgSensorlist, model);
		}
		if(lgSensorlist.getId()!=null && !lgSensorlist.getId().equals("")){
			lgSensorlistService.save(lgSensorlist);
			return "redirect:"+Global.getAdminPath()+"/lg/lgSensorlist/?repage";
		}
		lgSensorlistService.save(lgSensorlist);
		lgSensorlist.setId("");
		model.addAttribute("lgSensorlist",lgSensorlist);
		addMessage(redirectAttributes, "保存整机传感器信息成功");
		return "modules/lg/lgSensorlistForm";
	}
	
	@RequiresPermissions("lg:lgSensorlist:edit")
	@RequestMapping(value = "delete")
	public String delete(LgSensorlist lgSensorlist, RedirectAttributes redirectAttributes) {
		lgSensorlistService.updateStopSensor(lgSensorlist.getId());
		addMessage(redirectAttributes, "删除整机传感器信息成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgSensorlist/?repage";
	}
	
	@RequiresPermissions("lg:lgSensorlist:view")
	@RequestMapping(value = "checkSensorCode")
	@ResponseBody
	public String checkSensorCode(LgSensorlist lgSensorlist){
		LgSensorlist check = lgSensorlistService.checkSensorCode(lgSensorlist);
		if(check!=null){
			return "false";
		}else{
			return "true";
		}
	}
	
	@RequiresPermissions("lg:lgSensorlist:view")
	@RequestMapping(value = "checkSensorPin")
	@ResponseBody
	public String checkSensorPin(LgSensorlist lgSensorlist){
		LgSensorlist check = lgSensorlistService.checkSensorPin(lgSensorlist);
		if(check!=null){
			return "false";
		}else{
			return "true";
		}
	}

}