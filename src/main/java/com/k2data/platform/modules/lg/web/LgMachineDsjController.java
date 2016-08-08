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
import com.k2data.platform.modules.lg.entity.LgMachineevent;
import com.k2data.platform.modules.lg.service.LgMachineeventService;

/**
 * 机器大事记Controller
 * @author chenjingsi
 * @version 2016-05-13
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgMachineevent")
public class LgMachineDsjController extends BaseController {

	@Autowired
	private LgMachineeventService lgMachineeventService;
	
	@ModelAttribute
	public LgMachineevent get(@RequestParam(required=false) String id) {
		LgMachineevent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgMachineeventService.get(id);
		}
		if (entity == null){
			entity = new LgMachineevent();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:lgMachineevent:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgMachineevent lgMachineevent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgMachineevent> page = lgMachineeventService.findPage(new Page<LgMachineevent>(request, response), lgMachineevent); 
		model.addAttribute("page", page);
		return "success";
	}

	@RequiresPermissions("lg:lgMachineevent:view")
	@RequestMapping(value = "form")
	public String form(LgMachineevent lgMachineevent, Model model) {
		model.addAttribute("lgMachineevent", lgMachineevent);
		return "modules/lg/lgMachineeventForm";
	}

	@RequiresPermissions("lg:lgMachineevent:edit")
	@RequestMapping(value = "save")
	public String save(LgMachineevent lgMachineevent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgMachineevent)){
			return form(lgMachineevent, model);
		}
		lgMachineeventService.save(lgMachineevent);
		addMessage(redirectAttributes, "保存机器大事记成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgMachineevent/?repage";
	}
	
	@RequiresPermissions("lg:lgMachineevent:edit")
	@RequestMapping(value = "delete")
	public String delete(LgMachineevent lgMachineevent, RedirectAttributes redirectAttributes) {
		lgMachineeventService.delete(lgMachineevent);
		addMessage(redirectAttributes, "删除机器大事记成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgMachineevent/?repage";
	}

}