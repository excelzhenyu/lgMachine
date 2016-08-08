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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.workcenter.dao.WorkcenterGroupDao;
import com.k2data.platform.modules.workcenter.entity.WorkcenterGroup;
import com.k2data.platform.modules.workcenter.service.WorkcenterGroupService;

/**
 * 工作中心作业组Controller
 * @author lidong
 * @version 2016-06-16
 */
@Controller
@RequestMapping(value = "${adminPath}/workcenter/workcenterGroup")
public class WorkcenterGroupController extends BaseController {
	
	@Autowired
	private WorkcenterGroupService workcenterGroupService;
	@Autowired
	private WorkcenterGroupDao workcenterGroupDao;
	
	@ModelAttribute
	public WorkcenterGroup get(@RequestParam(required=false) String id) {
		WorkcenterGroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = workcenterGroupService.get(id);
		}
		if (entity == null){
			entity = new WorkcenterGroup();
		}
		return entity;
	}
	
	@RequiresPermissions("workcenter:workcenterGroup:view")
	@RequestMapping(value = {"list", ""})
	public String list(WorkcenterGroup workcenterGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WorkcenterGroup> page = workcenterGroupService.findPage(new Page<WorkcenterGroup>(request, response), workcenterGroup); 
		model.addAttribute("page", page);
		return "modules/workcenter/workcenterGroupList";
	}

	@RequiresPermissions("workcenter:workcenterGroup:view")
	@RequestMapping(value = "form")
	public String form(WorkcenterGroup workcenterGroup, Model model) {
		model.addAttribute("workcenterGroup", workcenterGroup);
		return "modules/workcenter/workcenterGroupForm";
	}

	@RequiresPermissions("workcenter:workcenterGroup:edit")
	@RequestMapping(value = "save")
	public String save(WorkcenterGroup workcenterGroup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, workcenterGroup)){
			return form(workcenterGroup, model);
		}
		workcenterGroupService.save(workcenterGroup);
		addMessage(redirectAttributes, "保存工作中心作业组成功");
		return "redirect:"+Global.getAdminPath()+"/workcenter/workcenterGroup/?repage";
	}
	
	@RequiresPermissions("workcenter:workcenterGroup:edit")
	@RequestMapping(value = "delete")
	public String delete(WorkcenterGroup workcenterGroup, RedirectAttributes redirectAttributes) {
		workcenterGroupService.delete(workcenterGroup);
		addMessage(redirectAttributes, "删除工作中心作业组成功");
		return "redirect:"+Global.getAdminPath()+"/workcenter/workcenterGroup/?repage";
	}
	
	/**
	 * 检查指定组是否存在
	 * 
	 * @param workcenter
	 * @return
	 */
	@RequestMapping(value="checkExist")
	@ResponseBody
	public boolean checkExist(WorkcenterGroup workcenterGroup) {
		return Global.YES.equals(workcenterGroupDao.checkExist(workcenterGroup));
	}


}