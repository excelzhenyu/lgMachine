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
import com.k2data.platform.modules.lg.entity.LgRepairhistory;
import com.k2data.platform.modules.lg.service.LgRepairhistoryService;

/**
 * 报修记录Controller
 * @author chenjingsi
 * @version 2016-05-13
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgRepairhistory")
public class LgRepairhistoryController extends BaseController {

	@Autowired
	private LgRepairhistoryService lgRepairhistoryService;
	
	@ModelAttribute
	public LgRepairhistory get(@RequestParam(required=false) String id) {
		LgRepairhistory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgRepairhistoryService.get(id);
		}
		if (entity == null){
			entity = new LgRepairhistory();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:lgRepairhistory:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgRepairhistory lgRepairhistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgRepairhistory> page = lgRepairhistoryService.findPage(new Page<LgRepairhistory>(request, response), lgRepairhistory); 
		model.addAttribute("page", page);
		return "modules/lg/lgRepairhistoryList";
	}

	@RequiresPermissions("lg:lgRepairhistory:view")
	@RequestMapping(value = "form")
	public String form(LgRepairhistory lgRepairhistory, Model model) {
		model.addAttribute("lgRepairhistory", lgRepairhistory);
		return "modules/lg/lgRepairhistoryForm";
	}

	@RequiresPermissions("lg:lgRepairhistory:edit")
	@RequestMapping(value = "save")
	public String save(LgRepairhistory lgRepairhistory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgRepairhistory)){
			return form(lgRepairhistory, model);
		}
		lgRepairhistoryService.save(lgRepairhistory);
		addMessage(redirectAttributes, "保存报修记录成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgRepairhistory/?repage";
	}
	
	@RequiresPermissions("lg:lgRepairhistory:edit")
	@RequestMapping(value = "delete")
	public String delete(LgRepairhistory lgRepairhistory, RedirectAttributes redirectAttributes) {
		lgRepairhistoryService.delete(lgRepairhistory);
		addMessage(redirectAttributes, "删除报修记录成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgRepairhistory/?repage";
	}

}