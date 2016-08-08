/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.web;

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
import com.k2data.platform.modules.workcenter.entity.SliceLog;
import com.k2data.platform.modules.workcenter.service.SliceLogService;

/**
 * 切片处理日志Controller
 * @author lidong
 * @version 2016-05-25
 */
@Controller
@RequestMapping(value = "${adminPath}/workcenter/sliceLog")
public class SliceLogController extends BaseController {

	@Autowired
	private SliceLogService sliceLogService;
	
	@ModelAttribute
	public SliceLog get(@RequestParam(required=false) String id) {
		SliceLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sliceLogService.get(id);
		}
		if (entity == null){
			entity = new SliceLog();
		}
		return entity;
	}
	
	@RequiresPermissions("workcenter:sliceLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(SliceLog sliceLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SliceLog> page = sliceLogService.findPage(new Page<SliceLog>(request, response), sliceLog); 
		model.addAttribute("page", page);
		return "modules/workcenter/sliceLogList";
	}

	@RequiresPermissions("workcenter:sliceLog:view")
	@RequestMapping(value = "form")
	public String form(SliceLog sliceLog, Model model) {
		model.addAttribute("sliceLog", sliceLog);
		return "modules/workcenter/sliceLogForm";
	}

	@RequiresPermissions("workcenter:sliceLog:edit")
	@RequestMapping(value = "save")
	public String save(SliceLog sliceLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sliceLog)){
			return form(sliceLog, model);
		}
		sliceLogService.save(sliceLog);
		addMessage(redirectAttributes, "保存切片处理日志成功");
		return "redirect:"+Global.getAdminPath()+"/workcenter/sliceLog/?repage";
	}
	
	@RequiresPermissions("workcenter:sliceLog:edit")
	@RequestMapping(value = "delete")
	public String delete(SliceLog sliceLog, RedirectAttributes redirectAttributes) {
		sliceLogService.delete(sliceLog);
		addMessage(redirectAttributes, "删除切片处理日志成功");
		return "redirect:"+Global.getAdminPath()+"/workcenter/sliceLog/?repage";
	}

}