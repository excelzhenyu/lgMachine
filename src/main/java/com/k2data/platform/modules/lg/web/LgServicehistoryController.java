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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.lg.entity.LgServicehistory;
import com.k2data.platform.modules.lg.service.LgServicehistoryService;

/**
 * 维修记录Controller
 * @author chenjingsi
 * @version 2016-05-13
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgServicehistory")
public class LgServicehistoryController extends BaseController {

	@Autowired
	private LgServicehistoryService lgServicehistoryService;
	
	@ModelAttribute
	public LgServicehistory get(@RequestParam(required=false) String id) {
		LgServicehistory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgServicehistoryService.get(id);
		}
		if (entity == null){
			entity = new LgServicehistory();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:lgServicehistory:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgServicehistory lgServicehistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgServicehistory> page = lgServicehistoryService.findPage(new Page<LgServicehistory>(request, response), lgServicehistory); 
		model.addAttribute("page", page);
		return "modules/lg/lgServicehistoryList";
	}

	@RequiresPermissions("lg:lgServicehistory:view")
	@RequestMapping(value = "form")
	public String form(LgServicehistory lgServicehistory, Model model) {
		model.addAttribute("lgServicehistory", lgServicehistory);
		return "modules/lg/lgServicehistoryForm";
	}

	@RequiresPermissions("lg:lgServicehistory:edit")
	@RequestMapping(value = "save")
	public String save(LgServicehistory lgServicehistory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgServicehistory)){
			return form(lgServicehistory, model);
		}
		lgServicehistoryService.save(lgServicehistory);
		addMessage(redirectAttributes, "保存维修记录成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgServicehistory/?repage";
	}
	
	@RequiresPermissions("lg:lgServicehistory:edit")
	@RequestMapping(value = "delete")
	public String delete(LgServicehistory lgServicehistory, RedirectAttributes redirectAttributes) {
		lgServicehistoryService.delete(lgServicehistory);
		addMessage(redirectAttributes, "删除维修记录成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgServicehistory/?repage";
	}

}