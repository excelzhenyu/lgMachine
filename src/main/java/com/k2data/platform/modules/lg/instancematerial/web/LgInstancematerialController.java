/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.instancematerial.web;

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
import com.k2data.platform.modules.lg.instancematerial.entity.LgInstancematerial;
import com.k2data.platform.modules.lg.instancematerial.service.LgInstancematerialService;
import com.k2data.platform.common.utils.StringUtils;

/**
 * 实例物料Controller
 * @author lidong
 * @version 2016-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/instancematerial/lgInstancematerial")
public class LgInstancematerialController extends BaseController {

	@Autowired
	private LgInstancematerialService lgInstancematerialService;
	
	@ModelAttribute
	public LgInstancematerial get(@RequestParam(required=false) String id) {
		LgInstancematerial entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgInstancematerialService.get(id);
		}
		if (entity == null){
			entity = new LgInstancematerial();
		}
		return entity;
	}
	
	@RequiresPermissions("instancematerial:lgInstancematerial:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgInstancematerial lgInstancematerial, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgInstancematerial> page = lgInstancematerialService.findPage(new Page<LgInstancematerial>(request, response), lgInstancematerial); 
		model.addAttribute("page", page);
		return "modules/lg/instancematerial/lgInstancematerialList";
	}

	@RequiresPermissions("instancematerial:lgInstancematerial:view")
	@RequestMapping(value = "form")
	public String form(LgInstancematerial lgInstancematerial, Model model) {
		model.addAttribute("lgInstancematerial", lgInstancematerial);
		return "modules/lg/instancematerial/lgInstancematerialForm";
	}

	@RequiresPermissions("instancematerial:lgInstancematerial:edit")
	@RequestMapping(value = "save")
	public String save(LgInstancematerial lgInstancematerial, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgInstancematerial)){
			return form(lgInstancematerial, model);
		}
		lgInstancematerialService.save(lgInstancematerial);
		addMessage(redirectAttributes, "保存实例物料成功");
		return "redirect:"+Global.getAdminPath()+"/lg/instancematerial/lgInstancematerial/?repage";
	}
	
	@RequiresPermissions("instancematerial:lgInstancematerial:edit")
	@RequestMapping(value = "delete")
	public String delete(LgInstancematerial lgInstancematerial, RedirectAttributes redirectAttributes) {
		lgInstancematerialService.delete(lgInstancematerial);
		addMessage(redirectAttributes, "删除实例物料成功");
		return "redirect:"+Global.getAdminPath()+"/lg/instancematerial/lgInstancematerial/?repage";
	}
	
	/**
	 * 检查指定中性物料下的实例物料是否存在
	 * 
	 * @param materialPin 实例物料PIN
	 * @param ledgerId 中性物料ID
	 * @return
	 */
	@RequiresPermissions("instancematerial:lgInstancematerial:edit")
	@RequestMapping(value = "checkMaterialPin")
	@ResponseBody
	public String checkMaterialPin(LgInstancematerial lgInstancematerial) {
		final String checkPin = lgInstancematerialService.checkMaterialPin(lgInstancematerial);
		
		if (Global.YES.equals(checkPin)) 
			return Global.FALSE;
		
		return Global.TRUE;
	}
	
}