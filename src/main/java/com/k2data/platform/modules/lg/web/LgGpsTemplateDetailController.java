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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.lg.entity.LgGpsTemplate;
import com.k2data.platform.modules.lg.entity.LgGpsTemplateDetail;
import com.k2data.platform.modules.lg.service.LgGpsTemplateDetailService;

/**
 * GPS传感器模板明细Controller
 * @author chenjingsi
 * @version 2016-06-02
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgGpsTemplateDetail")
public class LgGpsTemplateDetailController extends BaseController {

	@Autowired
	private LgGpsTemplateDetailService lgGpsTemplateDetailService;
	
	@ModelAttribute
	public LgGpsTemplateDetail get(@RequestParam(required=false) String id) {
		LgGpsTemplateDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgGpsTemplateDetailService.get(id);
		}
		if (entity == null){
			entity = new LgGpsTemplateDetail();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(LgGpsTemplateDetail lgGpsTemplateDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgGpsTemplateDetail> page = lgGpsTemplateDetailService.findPage(new Page<LgGpsTemplateDetail>(request, response), lgGpsTemplateDetail); 
		model.addAttribute("page", page);
		return "modules/lg/lgGpsTemplateDetailList";
	}

	@RequiresPermissions("lg:lgGpsTemplateDetail:view")
	@RequestMapping(value = "form")
	public String form(LgGpsTemplateDetail lgGpsTemplateDetail, Model model) {
		model.addAttribute("lgGpsTemplateDetail", lgGpsTemplateDetail);
		return "modules/lg/lgGpsTemplateDetailForm";
	}

	@RequiresPermissions("lg:lgGpsTemplateDetail:edit")
	@RequestMapping(value = "save")
	public String save(LgGpsTemplateDetail lgGpsTemplateDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgGpsTemplateDetail)){
			return form(lgGpsTemplateDetail, model);
		}
		lgGpsTemplateDetailService.save(lgGpsTemplateDetail);
		addMessage(redirectAttributes, "保存GPS传感器模板明细成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgGpsTemplateDetail/list?gpsTemplateId="+lgGpsTemplateDetail.getGpsTemplateId()+"&gpsTemplateNo="+lgGpsTemplateDetail.getGpsTemplateNo();
	}
	
	@RequiresPermissions("lg:lgGpsTemplateDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(LgGpsTemplateDetail lgGpsTemplateDetail, RedirectAttributes redirectAttributes) {
		lgGpsTemplateDetailService.delete(lgGpsTemplateDetail);
		addMessage(redirectAttributes, "删除GPS传感器模板明细成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgGpsTemplateDetail/?repage";
	}
	
	@RequiresPermissions("lg:lgGpsTemplateDetail:view")
	@RequestMapping(value="checkSensorMark")
	@ResponseBody
	public String checkSensorMark(LgGpsTemplateDetail lgGpsTemplateDetail){
		LgGpsTemplateDetail check = lgGpsTemplateDetailService.checkSensorMark(lgGpsTemplateDetail);
		if(check!=null){
			return "false";
		}else{
			return "true";
		}
	}

}