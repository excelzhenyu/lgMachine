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
import com.k2data.platform.modules.lg.entity.LgMachineinfo;
import com.k2data.platform.modules.lg.service.LgMachineinfoService;

/**
 * 机器简述Controller
 * @author chenjingsi
 * @version 2016-05-13
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgMachineinfo")
public class LgMachineinfoController extends BaseController {

	@Autowired
	private LgMachineinfoService lgMachineinfoService;
	
	@ModelAttribute
	public LgMachineinfo get(@RequestParam(required=false) String id) {
		LgMachineinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgMachineinfoService.get(id);
		}
		if (entity == null){
			entity = new LgMachineinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:lgMachineinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgMachineinfo lgMachineinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgMachineinfo> page = lgMachineinfoService.findPage(new Page<LgMachineinfo>(request, response), lgMachineinfo); 
		model.addAttribute("page", page);
		return "modules/lg/lgMachineinfoList";
	}

	@RequiresPermissions("lg:lgMachineinfo:view")
	@RequestMapping(value = "form")
	public String form(LgMachineinfo lgMachineinfo, Model model) {
		model.addAttribute("lgMachineinfo", lgMachineinfo);
		return "modules/lg/lgMachineinfoForm";
	}

	@RequiresPermissions("lg:lgMachineinfo:edit")
	@RequestMapping(value = "save")
	public String save(LgMachineinfo lgMachineinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgMachineinfo)){
			return form(lgMachineinfo, model);
		}
		lgMachineinfoService.save(lgMachineinfo);
		addMessage(redirectAttributes, "保存机器简述成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgMachineinfo/?repage";
	}
	
	@RequiresPermissions("lg:lgMachineinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(LgMachineinfo lgMachineinfo, RedirectAttributes redirectAttributes) {
		lgMachineinfoService.delete(lgMachineinfo);
		addMessage(redirectAttributes, "删除机器简述成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgMachineinfo/?repage";
	}

}