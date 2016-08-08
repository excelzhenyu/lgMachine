/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
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
import com.k2data.platform.modules.workcenter.entity.QrtzTrigger;
import com.k2data.platform.modules.workcenter.service.QrtzTriggerService;

/**
 * Quartz 触发器Controller
 * @author lidong
 * @version 2016-05-31
 */
@Controller
@RequestMapping(value = "${adminPath}/workcenter/qrtzTrigger")
public class QrtzTriggerController extends BaseController {
	
	private SchedulerFactoryBean schedulerFactoryBean = SpringContextHolder.getBean(SchedulerFactoryBean.class);
	private Scheduler scheduler = schedulerFactoryBean.getScheduler();

	@Autowired
	private QrtzTriggerService qrtzTriggerService;
	
	@RequiresPermissions("workcenter:qrtzTrigger:view")
	@RequestMapping(value = {"list", ""})
	public String list(QrtzTrigger qrtzTrigger, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QrtzTrigger> page = qrtzTriggerService.findPage(new Page<QrtzTrigger>(request, response), qrtzTrigger); 
		model.addAttribute("page", page);
		return "modules/workcenter/qrtzTriggerList";
	}

	@RequiresPermissions("workcenter:qrtzTrigger:edit")
	@RequestMapping(value = "resumeTrigger")
	public String resumeTrigger(QrtzTrigger qrtzTrigger, RedirectAttributes redirectAttributes) {
		try {
			scheduler.resumeTrigger(TriggerKey.triggerKey(qrtzTrigger.getTriggerName(), qrtzTrigger.getTriggerGroup()));
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
		
		addMessage(redirectAttributes, "恢复 Quartz 触发器成功");
		return "redirect:"+Global.getAdminPath()+"/workcenter/qrtzTrigger/?repage";
	}
	
	@RequiresPermissions("workcenter:qrtzTrigger:edit")
	@RequestMapping(value = "pauseTrigger")
	public String pauseTrigger(QrtzTrigger qrtzTrigger, RedirectAttributes redirectAttributes) {
		try {
			scheduler.pauseTrigger(TriggerKey.triggerKey(qrtzTrigger.getTriggerName(), qrtzTrigger.getTriggerGroup()));
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
		
		addMessage(redirectAttributes, "暂停 Quartz 触发器成功");
		return "redirect:"+Global.getAdminPath()+"/workcenter/qrtzTrigger/?repage";
	}

}