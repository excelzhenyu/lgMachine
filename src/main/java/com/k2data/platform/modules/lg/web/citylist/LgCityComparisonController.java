/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.citylist;

import java.util.HashMap;
import java.util.List;
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

import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.lg.entity.citylist.LgCityComparison;
import com.k2data.platform.modules.lg.entity.citylist.LgMapCity;
import com.k2data.platform.modules.lg.entity.citylist.LgSystemCity;
import com.k2data.platform.modules.lg.service.citylist.LgCityComparisonService;
import com.k2data.platform.modules.lg.service.citylist.LgMapCityService;
import com.k2data.platform.modules.lg.service.citylist.LgSystemCityService;

/**
 * 业务销售城市与地图城市对照Controller
 * @author wangshengguo
 * @version 2016-06-06
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/citylist/lgCityComparison")
public class LgCityComparisonController extends BaseController {

	@Autowired
	private LgCityComparisonService lgCityComparisonService;
	@Autowired
	private LgMapCityService lgMapCityService;
	@Autowired
	private LgSystemCityService lgSystemCityService;
	@ModelAttribute
	public LgCityComparison get(@RequestParam(required=false) String id) {
		LgCityComparison entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgCityComparisonService.get(id);
		}
		if (entity == null){
			entity = new LgCityComparison();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:citylist:lgCityComparison:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgCityComparison lgCityComparison, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgCityComparison> page = lgCityComparisonService.findPage(new Page<LgCityComparison>(request, response), lgCityComparison); 
		model.addAttribute("page", page);
		return "modules/lg/citylist/lgCityComparisonList";
	}

	@RequiresPermissions("lg:citylist:lgCityComparison:view")
	@RequestMapping(value = "form")
	public String form(LgCityComparison lgCityComparison, Model model) {
		model.addAttribute("lgCityComparison", lgCityComparison);
		//model.addAttribute("systemCityList", lgCityComparisonService.findSystemCityList(null));
		//model.addAttribute("mapCityList", lgCityComparisonService.findMapCityList(null));
		model.addAttribute("allMapCityList",lgMapCityService.findList(null));
		model.addAttribute("allSystemCityList",lgSystemCityService.findList(null));
		return "modules/lg/citylist/lgCityComparisonForm";
	}

	@RequiresPermissions("lg:citylist:lgCityComparison:edit")
	@RequestMapping(value = "save")
	public String save(LgCityComparison lgCityComparison, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgCityComparison)){
			return form(lgCityComparison, model);
		}
		lgCityComparisonService.save(lgCityComparison);
		addMessage(redirectAttributes, "保存业务销售城市与地图城市对照成功");
		return "redirect:"+Global.getAdminPath()+"/lg/citylist/lgCityComparison/?repage";
	}
	
	@RequiresPermissions("lg:citylist:lgCityComparison:edit")
	@RequestMapping(value = "delete")
	public String delete(LgCityComparison lgCityComparison, RedirectAttributes redirectAttributes) {
		lgCityComparisonService.delete(lgCityComparison);
		addMessage(redirectAttributes, "删除业务销售城市与地图城市对照成功");
		return "redirect:"+Global.getAdminPath()+"/lg/citylist/lgCityComparison/?repage";
	}

	@RequiresPermissions("lg:citylist:lgCityComparison:view")
	@RequestMapping(value = "batchImport")
	public String importData(Model model) {
		int importCount = lgCityComparisonService.importData();
		String message = "";
		if (importCount > 0) {
			message = "导入成功，共导入"+ importCount  + "条记录";
		} else {
			message = "没有需要导入的记录";
		}
		addMessage(model, message);
		return setting(model);
	}
	
	@RequiresPermissions("lg:citylist:lgCityComparison:view")
	@RequestMapping(value = "setting")
	public String setting(Model model) {
		model.addAttribute("systemCityList", lgCityComparisonService.findSystemCityList(null));
		model.addAttribute("mapCityList", lgCityComparisonService.findMapCityList(null));
		return "modules/lg/citylist/lgCityComparisonSetting";
	}
	
	@RequiresPermissions("lg:citylist:lgCityComparison:edit")
	@RequestMapping(value = "manualSave")
	public @ResponseBody String manualSave(LgCityComparison lgCityComparison, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgCityComparison)){
			return setting(model);
		}
		if(lgCityComparison != null) {
			if("".equals(lgCityComparison.getIsEffective())) {
				lgCityComparison.setIsEffective("1");
			}
			lgCityComparisonService.save(lgCityComparison);
			return "200";
		}
		return "500";
	}
	
	@RequiresPermissions("lg:citylist:lgCityComparison:view")
	@RequestMapping(value = "searchSystemCity")
	public @ResponseBody List<LgSystemCity> searchSystemCity(Model model, HttpServletRequest request) {
		LgSystemCity lgSystemCity = new LgSystemCity();
		lgSystemCity.setCityCode(request.getParameter("cityCode"));
		return lgCityComparisonService.findSystemCityList(lgSystemCity);
	}
	@RequiresPermissions("lg:citylist:lgCityComparison:view")
	@RequestMapping(value = "searchMapCity")
	public @ResponseBody List<LgMapCity> searchMapCity(Model model, HttpServletRequest request) {
		LgMapCity lgMapCity = new LgMapCity();
		lgMapCity.setCityCode(request.getParameter("cityCode"));
		return  lgCityComparisonService.findMapCityList(lgMapCity);
	}
}