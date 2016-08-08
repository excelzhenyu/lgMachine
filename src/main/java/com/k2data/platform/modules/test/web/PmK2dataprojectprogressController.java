/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.test.web;

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
import com.k2data.platform.modules.test.entity.PmK2dataprojectprogress;
import com.k2data.platform.modules.test.service.PmK2dataprojectprogressService;

/**
 * 项目进度Controller
 * @author wangshengguo
 * @version 2016-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/test/pmK2dataprojectprogress")
public class PmK2dataprojectprogressController extends BaseController {

	@Autowired
	private PmK2dataprojectprogressService pmK2dataprojectprogressService;
	
	@ModelAttribute
	public PmK2dataprojectprogress get(@RequestParam(required=false) String id) {
		PmK2dataprojectprogress entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pmK2dataprojectprogressService.get(id);
		}
		if (entity == null){
			entity = new PmK2dataprojectprogress();
		}
		return entity;
	}
	
	@RequiresPermissions("test:pmK2dataprojectprogress:view")
	@RequestMapping(value = {"list", ""})
	public String list(PmK2dataprojectprogress pmK2dataprojectprogress, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PmK2dataprojectprogress> page = pmK2dataprojectprogressService.findPage(new Page<PmK2dataprojectprogress>(request, response), pmK2dataprojectprogress); 
		model.addAttribute("page", page);
		return "modules/test/pmK2dataprojectprogressList";
	}

	@RequiresPermissions("test:pmK2dataprojectprogress:view")
	@RequestMapping(value = "form")
	public String form(PmK2dataprojectprogress pmK2dataprojectprogress, Model model) {
		model.addAttribute("pmK2dataprojectprogress", pmK2dataprojectprogress);
		return "modules/test/pmK2dataprojectprogressForm";
	}

	@RequiresPermissions("test:pmK2dataprojectprogress:edit")
	@RequestMapping(value = "save")
	public String save(PmK2dataprojectprogress pmK2dataprojectprogress, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pmK2dataprojectprogress)){
			return form(pmK2dataprojectprogress, model);
		}
		pmK2dataprojectprogressService.save(pmK2dataprojectprogress);
		addMessage(redirectAttributes, "保存项目进度成功");
		return "redirect:"+Global.getAdminPath()+"/test/pmK2dataprojectprogress/?repage";
	}
	
	@RequiresPermissions("test:pmK2dataprojectprogress:edit")
	@RequestMapping(value = "delete")
	public String delete(PmK2dataprojectprogress pmK2dataprojectprogress, RedirectAttributes redirectAttributes) {
		pmK2dataprojectprogressService.delete(pmK2dataprojectprogress);
		addMessage(redirectAttributes, "删除项目进度成功");
		return "redirect:"+Global.getAdminPath()+"/test/pmK2dataprojectprogress/?repage";
	}

}