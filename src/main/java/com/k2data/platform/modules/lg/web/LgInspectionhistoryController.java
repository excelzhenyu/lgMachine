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
import com.k2data.platform.modules.lg.entity.LgInspectionhistory;
import com.k2data.platform.modules.lg.service.LgInspectionhistoryService;

/**
 * 巡检整改Controller
 * @author chenjingsi
 * @version 2016-06-29
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgInspectionhistory")
public class LgInspectionhistoryController extends BaseController {

	@Autowired
	private LgInspectionhistoryService lgInspectionhistoryService;
	
	@ModelAttribute
	public LgInspectionhistory get(@RequestParam(required=false) String id) {
		LgInspectionhistory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgInspectionhistoryService.get(id);
		}
		if (entity == null){
			entity = new LgInspectionhistory();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:lgInspectionhistory:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgInspectionhistory lgInspectionhistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgInspectionhistory> page = lgInspectionhistoryService.findPage(new Page<LgInspectionhistory>(request, response), lgInspectionhistory); 
		model.addAttribute("page", page);
		return "modules/lg/lgInspectionhistoryList";
	}

	@RequiresPermissions("lg:lgInspectionhistory:view")
	@RequestMapping(value = "form")
	public String form(LgInspectionhistory lgInspectionhistory, Model model) {
		model.addAttribute("lgInspectionhistory", lgInspectionhistory);
		return "modules/lg/lgInspectionhistoryForm";
	}

	@RequiresPermissions("lg:lgInspectionhistory:edit")
	@RequestMapping(value = "save")
	public String save(LgInspectionhistory lgInspectionhistory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgInspectionhistory)){
			return form(lgInspectionhistory, model);
		}
		lgInspectionhistoryService.save(lgInspectionhistory);
		addMessage(redirectAttributes, "保存巡检整改成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgInspectionhistory/?repage";
	}
	
	@RequiresPermissions("lg:lgInspectionhistory:edit")
	@RequestMapping(value = "delete")
	public String delete(LgInspectionhistory lgInspectionhistory, RedirectAttributes redirectAttributes) {
		lgInspectionhistoryService.delete(lgInspectionhistory);
		addMessage(redirectAttributes, "删除巡检整改成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgInspectionhistory/?repage";
	}

}