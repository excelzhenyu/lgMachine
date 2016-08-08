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
import com.k2data.platform.modules.lg.entity.LgUpkeephistory;
import com.k2data.platform.modules.lg.service.LgUpkeephistoryService;

/**
 * 保养记录Controller
 * @author chenjingsi
 * @version 2016-05-13
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgUpkeephistory")
public class LgUpkeephistoryController extends BaseController {

	@Autowired
	private LgUpkeephistoryService lgUpkeephistoryService;
	
	@ModelAttribute
	public LgUpkeephistory get(@RequestParam(required=false) String id) {
		LgUpkeephistory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgUpkeephistoryService.get(id);
		}
		if (entity == null){
			entity = new LgUpkeephistory();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:lgUpkeephistory:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgUpkeephistory lgUpkeephistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgUpkeephistory> page = lgUpkeephistoryService.findPage(new Page<LgUpkeephistory>(request, response), lgUpkeephistory); 
		model.addAttribute("page", page);
		return "modules/lg/lgUpkeephistoryList";
	}

	@RequiresPermissions("lg:lgUpkeephistory:view")
	@RequestMapping(value = "form")
	public String form(LgUpkeephistory lgUpkeephistory, Model model) {
		model.addAttribute("lgUpkeephistory", lgUpkeephistory);
		return "modules/lg/lgUpkeephistoryForm";
	}

	@RequiresPermissions("lg:lgUpkeephistory:edit")
	@RequestMapping(value = "save")
	public String save(LgUpkeephistory lgUpkeephistory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgUpkeephistory)){
			return form(lgUpkeephistory, model);
		}
		lgUpkeephistoryService.save(lgUpkeephistory);
		addMessage(redirectAttributes, "保存保养记录成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgUpkeephistory/?repage";
	}
	
	@RequiresPermissions("lg:lgUpkeephistory:edit")
	@RequestMapping(value = "delete")
	public String delete(LgUpkeephistory lgUpkeephistory, RedirectAttributes redirectAttributes) {
		lgUpkeephistoryService.delete(lgUpkeephistory);
		addMessage(redirectAttributes, "删除保养记录成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgUpkeephistory/?repage";
	}

}