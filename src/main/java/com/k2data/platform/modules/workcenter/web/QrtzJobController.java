/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.utils.SpringContextHolder;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.modules.workcenter.entity.QrtzJob;
import com.k2data.platform.modules.workcenter.service.QrtzJobService;

/**
 * Quartz JobController
 * @author lidong
 * @version 2016-06-01
 */
@Controller
@RequestMapping(value = "${adminPath}/workcenter/qrtzJob")
public class QrtzJobController extends BaseController {
	
	private SchedulerFactoryBean schedulerFactoryBean = SpringContextHolder.getBean(SchedulerFactoryBean.class);
	private Scheduler scheduler = schedulerFactoryBean.getScheduler();

	@Autowired
	private QrtzJobService qrtzJobService;
	
	@RequiresPermissions("workcenter:qrtzJob:view")
	@RequestMapping(value = {"list", ""})
	public String list(QrtzJob qrtzJob, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QrtzJob> page = qrtzJobService.findPage(new Page<QrtzJob>(request, response), qrtzJob); 
		model.addAttribute("page", page);
		return "modules/workcenter/qrtzJobList";
	}
	
	/**
	 * 触发 Quartz Job
	 * 
	 * @param qrtzJob
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("workcenter:qrtzJob:edit")
	@RequestMapping(value = "triggerJob")
	public String triggerJob(QrtzJob qrtzJob, RedirectAttributes redirectAttributes) {
		try {
			scheduler.triggerJob(JobKey.jobKey(qrtzJob.getJobName(), qrtzJob.getJobGroup()));
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
		
		addMessage(redirectAttributes, "触发 Quartz Job 成功");
		return "redirect:"+Global.getAdminPath()+"/workcenter/qrtzJob/?repage";
	}
	
	/**
	 * 删除 Quartz Job
	 * 
	 * @param qrtzJob
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("workcenter:qrtzJob:edit")
	@RequestMapping(value = "deleteJob")
	public String deleteJob(QrtzJob qrtzJob, RedirectAttributes redirectAttributes) {
		try {
			scheduler.deleteJob(JobKey.jobKey(qrtzJob.getJobName(), qrtzJob.getJobGroup()));
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
		
		addMessage(redirectAttributes, "删除 Quartz Job 成功");
		return "redirect:"+Global.getAdminPath()+"/workcenter/qrtzJob/?repage";
	}

}