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
import com.k2data.platform.modules.lg.entity.LgReplacehistory;
import com.k2data.platform.modules.lg.service.LgReplacehistoryService;

/**
 * 部件变更记录Controller
 * @author chenjingsi
 * @version 2016-05-13
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgReplacehistory")
public class LgReplacehistoryController extends BaseController {

	@Autowired
	private LgReplacehistoryService lgReplacehistoryService;
	
	@ModelAttribute
	public LgReplacehistory get(@RequestParam(required=false) String id) {
		LgReplacehistory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgReplacehistoryService.get(id);
		}
		if (entity == null){
			entity = new LgReplacehistory();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:lgReplacehistory:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgReplacehistory lgReplacehistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgReplacehistory> page = lgReplacehistoryService.findPage(new Page<LgReplacehistory>(request, response), lgReplacehistory); 
		model.addAttribute("page", page);
		return "modules/lg/lgReplacehistoryList";
	}

	@RequiresPermissions("lg:lgReplacehistory:view")
	@RequestMapping(value = "form")
	public String form(LgReplacehistory lgReplacehistory, Model model) {
		model.addAttribute("lgReplacehistory", lgReplacehistory);
		return "modules/lg/lgReplacehistoryForm";
	}

	@RequiresPermissions("lg:lgReplacehistory:edit")
	@RequestMapping(value = "save")
	public String save(LgReplacehistory lgReplacehistory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgReplacehistory)){
			return form(lgReplacehistory, model);
		}
		lgReplacehistoryService.save(lgReplacehistory);
		addMessage(redirectAttributes, "保存部件变更记录成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgReplacehistory/?repage";
	}
	
	@RequiresPermissions("lg:lgReplacehistory:edit")
	@RequestMapping(value = "delete")
	public String delete(LgReplacehistory lgReplacehistory, RedirectAttributes redirectAttributes) {
		lgReplacehistoryService.delete(lgReplacehistory);
		addMessage(redirectAttributes, "删除部件变更记录成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgReplacehistory/?repage";
	}

}