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
import com.k2data.platform.modules.lg.entity.LgBaselineofdevice;
import com.k2data.platform.modules.lg.service.LgBaselineofdeviceService;

/**
 * 二级传感器分析表Controller
 * @author chenjingsi
 * @version 2016-05-30
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgBaselineofdevice")
public class LgBaselineofdeviceController extends BaseController {

	@Autowired
	private LgBaselineofdeviceService lgBaselineofdeviceService;
	
	@ModelAttribute
	public LgBaselineofdevice get(@RequestParam(required=false) String id) {
		LgBaselineofdevice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgBaselineofdeviceService.get(id);
		}
		if (entity == null){
			entity = new LgBaselineofdevice();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:lgBaselineofdevice:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgBaselineofdevice lgBaselineofdevice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgBaselineofdevice> page = lgBaselineofdeviceService.findPage(new Page<LgBaselineofdevice>(request, response), lgBaselineofdevice); 
		model.addAttribute("page", page);
		return "modules/lg/lgBaselineofdeviceList";
	}

	@RequiresPermissions("lg:lgBaselineofdevice:view")
	@RequestMapping(value = "form")
	public String form(LgBaselineofdevice lgBaselineofdevice, Model model) {
		model.addAttribute("lgBaselineofdevice", lgBaselineofdevice);
		return "modules/lg/lgBaselineofdeviceForm";
	}

	@RequiresPermissions("lg:lgBaselineofdevice:edit")
	@RequestMapping(value = "save")
	public String save(LgBaselineofdevice lgBaselineofdevice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgBaselineofdevice)){
			return form(lgBaselineofdevice, model);
		}
		lgBaselineofdeviceService.save(lgBaselineofdevice);
		addMessage(redirectAttributes, "保存二级传感器分析成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgBaselineofdevice/?repage";
	}
	
	@RequiresPermissions("lg:lgBaselineofdevice:edit")
	@RequestMapping(value = "delete")
	public String delete(LgBaselineofdevice lgBaselineofdevice, RedirectAttributes redirectAttributes) {
		lgBaselineofdeviceService.delete(lgBaselineofdevice);
		addMessage(redirectAttributes, "删除二级传感器分析成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgBaselineofdevice/?repage";
	}

}