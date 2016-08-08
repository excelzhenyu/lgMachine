/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web;

import java.util.Date;

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
import com.k2data.platform.modules.lg.entity.LgGpsTemplate;
import com.k2data.platform.modules.lg.entity.LgGpsTemplateDetail;
import com.k2data.platform.modules.lg.service.LgGpsTemplateDetailService;
import com.k2data.platform.modules.lg.service.LgGpsTemplateService;

/**
 * GPS传感器模板Controller
 * @author chenjingsi
 * @version 2016-06-02
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgGpsTemplate")
public class LgGpsTemplateController extends BaseController {

	@Autowired
	private LgGpsTemplateService lgGpsTemplateService;
	@Autowired
	private LgGpsTemplateDetailService lgGpsTemplateDetailService;
	
	@ModelAttribute
	public LgGpsTemplate get(@RequestParam(required=false) String id) {
		LgGpsTemplate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgGpsTemplateService.get(id);
		}
		if (entity == null){
			entity = new LgGpsTemplate();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:lgGpsTemplate:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgGpsTemplate lgGpsTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgGpsTemplate> page = lgGpsTemplateService.findPage(new Page<LgGpsTemplate>(request, response), lgGpsTemplate); 
		model.addAttribute("page", page);
		return "modules/lg/lgGpsTemplateList";
	}

	@RequiresPermissions("lg:lgGpsTemplate:view")
	@RequestMapping(value = "form")
	public String form(LgGpsTemplate lgGpsTemplate, Model model) {
		lgGpsTemplate.setStopMark(1);
		model.addAttribute("lgGpsTemplate", lgGpsTemplate);
		return "modules/lg/lgGpsTemplateForm";
	}

	@RequiresPermissions("lg:lgGpsTemplate:edit")
	@RequestMapping(value = "save")
	public String save(LgGpsTemplate lgGpsTemplate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgGpsTemplate)){
			return form(lgGpsTemplate, model);
		}
		if(lgGpsTemplate.getStartTime()==null){
			lgGpsTemplate.setStartTime(new Date());
		}
		lgGpsTemplateService.save(lgGpsTemplate);
		addMessage(redirectAttributes, "保存GPS传感器模板成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgGpsTemplate/?repage";
	}
	
	@RequiresPermissions("lg:lgGpsTemplate:edit")
	@RequestMapping(value = "delete")
	public String delete(LgGpsTemplate lgGpsTemplate, RedirectAttributes redirectAttributes) {
		lgGpsTemplateService.delete(lgGpsTemplate);
		LgGpsTemplateDetail lgGpsTemplateDetail = new LgGpsTemplateDetail();
		lgGpsTemplateDetail.setGpsTemplateId(lgGpsTemplate.getId());
		lgGpsTemplateDetailService.deleteByTemplateId(lgGpsTemplateDetail);
		addMessage(redirectAttributes, "删除GPS传感器模板成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgGpsTemplate/?repage";
	}
	
	@RequiresPermissions("lg:lgGpsTemplate:view")
	@RequestMapping(value="checkTemplateNo")
	@ResponseBody
	public String checkGpsTemplateNo(LgGpsTemplate lgGpsTemplate){
		LgGpsTemplate check = lgGpsTemplateService.checkGpsTemplateNo(lgGpsTemplate);
		if(check!=null){
			return "false";
		}else{
			return "true";
		}
	}
	
	@RequiresPermissions("lg:lgGpsTemplate:view")
	@RequestMapping(value="checkTemplateName")
	@ResponseBody
	public String checkGpsTemplateName(LgGpsTemplate lgGpsTemplate){
		LgGpsTemplate check = lgGpsTemplateService.checkGpsTemplateName(lgGpsTemplate);
		if(check!=null){
			return "false";
		}else{
			return "true";
		}
	}

}