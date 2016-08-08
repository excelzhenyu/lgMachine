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
import com.k2data.platform.modules.lg.entity.LgMachineDimension;
import com.k2data.platform.modules.lg.service.LgMachineDimensionService;

/**
 * 整机维度Controller
 * @author wangshengguo
 * @version 2016-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgMachineDimension")
public class LgMachineDimensionController extends BaseController {

	@Autowired
	private LgMachineDimensionService lgMachineDimensionService;
	
	@ModelAttribute
	public LgMachineDimension get(@RequestParam(required=false) String id) {
		LgMachineDimension entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgMachineDimensionService.get(id);
		}
		if (entity == null){
			entity = new LgMachineDimension();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:lgMachineDimension:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgMachineDimension lgMachineDimension, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgMachineDimension> page = lgMachineDimensionService.findPage(new Page<LgMachineDimension>(request, response), lgMachineDimension); 
		model.addAttribute("page", page);
		return "modules/lg/lgMachineDimensionList";
	}

	@RequiresPermissions("lg:lgMachineDimension:view")
	@RequestMapping(value = "form")
	public String form(LgMachineDimension lgMachineDimension, Model model) {
		model.addAttribute("lgMachineDimension", lgMachineDimension);
		return "modules/lg/lgMachineDimensionForm";
	}

	@RequiresPermissions("lg:lgMachineDimension:edit")
	@RequestMapping(value = "save")
	public String save(LgMachineDimension lgMachineDimension, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgMachineDimension)){
			return form(lgMachineDimension, model);
		}
		if("".equals(lgMachineDimension.getId())) {
			long count = lgMachineDimensionService.selectCountByEntity(lgMachineDimension);
			if(count == 0) {
				lgMachineDimensionService.save(lgMachineDimension);
				addMessage(redirectAttributes, "保存整机维度成功");
				return "redirect:"+Global.getAdminPath()+"/lg/lgMachineDimension/?repage";
			} else {
				//维度类别和维度编号，不能重复
				addMessage(model, "存在重复记录，请核实");
				return form(lgMachineDimension, model);
			}
		} else {
			LgMachineDimension entity = get(lgMachineDimension.getId());
			if(entity != null && entity.getDimensionType().equals(lgMachineDimension.getDimensionType())
					&& entity.getDimensionCode().equals(lgMachineDimension.getDimensionCode())) {
				lgMachineDimensionService.save(lgMachineDimension);
				addMessage(redirectAttributes, "保存整机维度成功");
				return "redirect:"+Global.getAdminPath()+"/lg/lgMachineDimension/?repage";
			} else {
				//维度类别和维度编号，不能重复
				long count = lgMachineDimensionService.selectCountByEntity(lgMachineDimension);
				if(count == 0) {
					lgMachineDimensionService.save(lgMachineDimension);
					addMessage(redirectAttributes, "保存整机维度成功");
					return "redirect:"+Global.getAdminPath()+"/lg/lgMachineDimension/?repage";
				} else {
					//维度类别和维度编号，不能重复
					addMessage(model, "存在重复记录，请核实");
					return form(lgMachineDimension, model);
				}
			}
			
		}
	}
	
	@RequiresPermissions("lg:lgMachineDimension:edit")
	@RequestMapping(value = "delete")
	public String delete(LgMachineDimension lgMachineDimension, RedirectAttributes redirectAttributes) {
		lgMachineDimensionService.delete(lgMachineDimension);
		addMessage(redirectAttributes, "删除整机维度成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgMachineDimension/?repage";
	}

}