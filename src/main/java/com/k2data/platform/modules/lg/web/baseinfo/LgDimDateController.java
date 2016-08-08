/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.baseinfo;

import java.util.List;

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
import com.k2data.platform.common.utils.DateUtils;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.lg.entity.baseinfo.LgDimDate;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceDateStatics;
import com.k2data.platform.modules.lg.service.baseinfo.LgDimDateService;

/**
 * 基础信息-时间维度Controller
 * @author wangshengguo
 * @version 2016-06-13
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/baseinfo/lgDimDate")
public class LgDimDateController extends BaseController {

	@Autowired
	private LgDimDateService lgDimDateService;
	
	@ModelAttribute
	public LgDimDate get(@RequestParam(required=false) String id) {
		LgDimDate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgDimDateService.get(id);
		}
		if (entity == null){
			entity = new LgDimDate();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:baseinfo:lgDimDate:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgDimDate lgDimDate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgDimDate> page = lgDimDateService.findPage(new Page<LgDimDate>(request, response), lgDimDate); 
		model.addAttribute("page", page);
		return "modules/lg/baseinfo/lgDimDateList";
	}

	@RequiresPermissions("lg:baseinfo:lgDimDate:view")
	@RequestMapping(value = "form")
	public String form(LgDimDate lgDimDate, Model model) {
		model.addAttribute("lgDimDate", lgDimDate);
		return "modules/lg/baseinfo/lgDimDateForm";
	}

	@RequiresPermissions("lg:baseinfo:lgDimDate:edit")
	@RequestMapping(value = "save")
	public String save(LgDimDate lgDimDate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgDimDate)){
			return form(lgDimDate, model);
		}
		lgDimDateService.save(lgDimDate);
		addMessage(redirectAttributes, "保存基础信息-时间维度成功");
		return "redirect:"+Global.getAdminPath()+"/lg/baseinfo/lgDimDate/?repage";
	}
	
	@RequiresPermissions("lg:baseinfo:lgDimDate:edit")
	@RequestMapping(value = "delete")
	public String delete(LgDimDate lgDimDate, RedirectAttributes redirectAttributes) {
		lgDimDateService.delete(lgDimDate);
		addMessage(redirectAttributes, "删除基础信息-时间维度成功");
		return "redirect:"+Global.getAdminPath()+"/lg/baseinfo/lgDimDate/?repage";
	}

	@RequestMapping(value = "import")
	public @ResponseBody String saveDayData(Model model, HttpServletRequest request) {
		int count = lgDimDateService.saveBatch(DateUtils.parseDate("2015-11-01"), 20);
		return "" + count;
	}
}