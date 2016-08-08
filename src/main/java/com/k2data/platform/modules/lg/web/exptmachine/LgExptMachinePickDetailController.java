/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.exptmachine;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.metal.MetalCheckBoxIcon;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.k2data.platform.common.beanvalidator.BeanValidators;
import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.common.utils.DateUtils;
import com.k2data.platform.common.utils.IdGen;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.common.utils.excel.ExportExcel;
import com.k2data.platform.common.utils.excel.ImportExcel;
import com.k2data.platform.modules.lg.entity.LgMachineprofile;
import com.k2data.platform.modules.lg.entity.exptmachine.LgExptMachinePickDetail;
import com.k2data.platform.modules.lg.service.exptmachine.LgExptMachinePickDetailService;
import com.k2data.platform.modules.sys.entity.User;
import com.k2data.platform.modules.sys.service.SystemService;
import com.k2data.platform.modules.sys.utils.UserUtils;

/**
 * 试验机器选取明细Controller
 * @author wangshengguo
 * @version 2016-06-21
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/exptmachine/lgExptMachinePickDetail")
public class LgExptMachinePickDetailController extends BaseController {

	@Autowired
	private LgExptMachinePickDetailService lgExptMachinePickDetailService;
	
	@ModelAttribute
	public LgExptMachinePickDetail get(@RequestParam(required=false) String id) {
		LgExptMachinePickDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgExptMachinePickDetailService.get(id);
		}
		if (entity == null){
			entity = new LgExptMachinePickDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:exptmachine:lgExptMachinePickDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgExptMachinePickDetail lgExptMachinePickDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgExptMachinePickDetail> page = lgExptMachinePickDetailService.findPage(new Page<LgExptMachinePickDetail>(request, response), lgExptMachinePickDetail); 
		model.addAttribute("page", page);
		return "modules/lg/exptmachine/lgExptMachinePickDetailList";
	}

	@RequiresPermissions("lg:exptmachine:lgExptMachinePickDetail:view")
	@RequestMapping(value = "form")
	public String form(LgExptMachinePickDetail lgExptMachinePickDetail, Model model) {
		model.addAttribute("lgExptMachinePickDetail", lgExptMachinePickDetail);
		return "modules/lg/exptmachine/lgExptMachinePickDetailForm";
	}

	@RequiresPermissions("lg:exptmachine:lgExptMachinePickDetail:edit")
	@RequestMapping(value = "save")
	public String save(LgExptMachinePickDetail lgExptMachinePickDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgExptMachinePickDetail)){
			return form(lgExptMachinePickDetail, model);
		}
		lgExptMachinePickDetailService.save(lgExptMachinePickDetail);
		addMessage(redirectAttributes, "保存试验机器选取明细成功");
		return "redirect:"+Global.getAdminPath()+"/lg/exptmachine/lgExptMachinePickDetail/?repage";
	}
	
	@RequiresPermissions("lg:exptmachine:lgExptMachinePickDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(LgExptMachinePickDetail lgExptMachinePickDetail, RedirectAttributes redirectAttributes) {
		lgExptMachinePickDetailService.delete(lgExptMachinePickDetail);
		addMessage(redirectAttributes, "删除试验机器选取明细成功");
		return "redirect:"+Global.getAdminPath()+"/lg/exptmachine/lgExptMachinePickDetail/?repage";
	}
	
	@RequiresPermissions("lg:exptmachine:lgExptMachinePick:edit")
	@RequestMapping(value = "deleteMachine")
	public String deleteMachine(LgExptMachinePickDetail lgExptMachinePickDetail, RedirectAttributes redirectAttributes) {
		lgExptMachinePickDetailService.deleteMachine(lgExptMachinePickDetail);
		addMessage(redirectAttributes, "删除试验机器选取明细成功");
		return "redirect:"+Global.getAdminPath()+"/lg/exptmachine/lgExptMachinePick/info?id=" + lgExptMachinePickDetail.getPickId();
	}
	
	@RequiresPermissions("lg:exptmachine:lgExptMachinePick:edit")
	@RequestMapping(value = "saveBatch")
	public String saveBatch(LgExptMachinePickDetail lgExptMachinePickDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgExptMachinePickDetail)){
			return form(lgExptMachinePickDetail, model);
		}
		List<LgExptMachinePickDetail> machineList =  new ArrayList<LgExptMachinePickDetail>();
		String machineIds = lgExptMachinePickDetail.getMachineIds();
		String pickId = lgExptMachinePickDetail.getPickId();
		String remarks = DateUtils.formatDateTime(new Date()) + "批量导入";

		if(machineIds != null) {
			String[] machineId = machineIds.split(",");
			for (String mId: machineId) {
				LgExptMachinePickDetail lgExptMachine = new LgExptMachinePickDetail();
				lgExptMachine.setId(IdGen.uuid());
				lgExptMachine.setMachineId(mId);
				lgExptMachine.setPickId(pickId);
				lgExptMachine.setRemarks(remarks);
				machineList.add(lgExptMachine);
			}
		}
		if(machineList != null && machineList.size() > 0) {
			//批量插入
			lgExptMachinePickDetailService.saveBatch(machineList);
		}
		addMessage(redirectAttributes, "保存试验机器选取明细成功");
		return "redirect:"+Global.getAdminPath()+"/lg/exptmachine/lgExptMachinePick/info?id=" + pickId;
	}

	
	
}