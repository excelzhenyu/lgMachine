/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.web;

import java.util.HashMap;
import java.util.Map;

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

import com.alibaba.fastjson.JSON;
import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.workcenter.dao.WorkcenterGenJobDao;
import com.k2data.platform.modules.workcenter.entity.WorkcenterGenJob;
import com.k2data.platform.modules.workcenter.entity.WorkcenterGroup;
import com.k2data.platform.modules.workcenter.service.WorkcenterGenJobService;
import com.k2data.platform.modules.workcenter.service.WorkcenterGroupService;
import com.k2data.platform.modules.workcenter.utils.WorkCenterUtils;

/**
 * Quartz Job 生成方案Controller
 * @author lidong
 * @version 2016-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/workcenter/workcenterGenJob")
public class WorkcenterGenJobController extends BaseController {

	@Autowired
	private WorkcenterGenJobService workcenterGenJobService;
	@Autowired
	private WorkcenterGenJobDao workcenterGenJobDao;
	@Autowired
	private WorkcenterGroupService workcenterGroupService;
	
	@ModelAttribute
	public WorkcenterGenJob get(@RequestParam(required=false) String id) {
		WorkcenterGenJob entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = workcenterGenJobService.get(id);
		}
		if (entity == null){
			entity = new WorkcenterGenJob();
		}
		
		return entity;
	}
	
	@RequiresPermissions("workcenter:workcenterGenJob:view")
	@RequestMapping(value = {"list", ""})
	public String list(WorkcenterGenJob workcenterGenJob, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WorkcenterGenJob> page = workcenterGenJobService.findPage(new Page<WorkcenterGenJob>(request, response), workcenterGenJob); 
		model.addAttribute("page", page);
		return "modules/workcenter/workcenterGenJobList";
	}

	@RequiresPermissions("workcenter:workcenterGenJob:view")
	@RequestMapping(value = "form")
	public String form(WorkcenterGenJob workcenterGenJob, Model model) {
		model.addAttribute("workcenterGenJob", workcenterGenJob);
		return "modules/workcenter/workcenterGenJobForm";
	}

	@RequiresPermissions("workcenter:workcenterGenJob:edit")
	@RequestMapping(value = "save")
	public String save(WorkcenterGenJob workcenterGenJob, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, workcenterGenJob))
			return form(workcenterGenJob, model);
		
		if (Global.YES.equals(workcenterGenJob.getGenFlag()))  	// 是否生成代码
			workcenterGenJobService.generateJob(workcenterGenJob, get(workcenterGenJob.getId()));
		
		workcenterGenJobService.save(workcenterGenJob);
		addMessage(redirectAttributes, "保存Quartz Job 生成方案成功");
		return "redirect:"+Global.getAdminPath()+"/workcenter/workcenterGenJob/?repage";
	}
	
	@RequiresPermissions("workcenter:workcenterGenJob:edit")
	@RequestMapping(value = "delete")
	public String delete(WorkcenterGenJob workcenterGenJob, RedirectAttributes redirectAttributes) {
		workcenterGenJobService.delete(workcenterGenJob);
		addMessage(redirectAttributes, "删除Quartz Job 生成方案成功");
		return "redirect:"+Global.getAdminPath()+"/workcenter/workcenterGenJob/?repage";
	}
	
	/**
	 * 得到模态窗口选择作业组的 Dom
	 * 
	 * @param workcenterGroup
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "groupListDom")
	@ResponseBody
	public String GroupListDom(WorkcenterGroup workcenterGroup, HttpServletRequest request, HttpServletResponse response) {
		Page<WorkcenterGroup> page = workcenterGroupService.findPage(new Page<WorkcenterGroup>(request, response), workcenterGroup); 
		
		Map<String, Object> domMap = new HashMap<String, Object>();
		domMap.put("data", WorkCenterUtils.genGroupListDom(page.getList()));
		domMap.put("page", page.getHtml());
		
		return JSON.toJSONString(domMap);
	}
	
	/**
	 * 检查数据是否存在
	 * 
	 * @param workcenterGenJob
	 * @return
	 */
	@RequestMapping(value = "checkExist")
	@ResponseBody
	public String checkExist(WorkcenterGenJob workcenterGenJob) {
		return workcenterGenJobDao.checkExist(workcenterGenJob);
	}
	
	/**
	 * 检查功能名是否存在
	 * 
	 * @param workcenterGenJob
	 * @return
	 */
	@RequestMapping(value = "checkFunctionNameExist")
	@ResponseBody
	public String checkFunctionNameExist(WorkcenterGenJob workcenterGenJob) {
		return workcenterGenJobDao.checkFunctionNameExist(workcenterGenJob);
	}

}