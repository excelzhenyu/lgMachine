/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.citylist;

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

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.lg.entity.citylist.LgSystemCity;
import com.k2data.platform.modules.lg.entity.citylist.LgTreeSortEntity;
import com.k2data.platform.modules.lg.service.citylist.LgSystemCityService;

/**
 * 业务系统城市列表Controller
 * @author wangshengguo
 * @version 2016-06-01
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/citylist/lgSystemCity")
public class LgSystemCityController extends BaseController {

	@Autowired
	private LgSystemCityService lgSystemCityService;
	@ModelAttribute
	public LgSystemCity get(@RequestParam(required=false) String id) {
		LgSystemCity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgSystemCityService.get(id);
		}
		if (entity == null){
			entity = new LgSystemCity();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:citylist:lgSystemCity:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgSystemCity lgSystemCity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgSystemCity> page = lgSystemCityService.findPage(new Page<LgSystemCity>(request, response), lgSystemCity); 
		model.addAttribute("page", page);
		
		return "modules/lg/citylist/lgSystemCityList";
	}

	@RequiresPermissions("lg:citylist:lgSystemCity:view")
	@RequestMapping(value = "form")
	public String form(LgSystemCity lgSystemCity, Model model) {
		model.addAttribute("lgSystemCity", lgSystemCity);
		model.addAttribute("cityList", lgSystemCityService.findList(lgSystemCity));
		return "modules/lg/citylist/lgSystemCityForm";
	}

	@RequiresPermissions("lg:citylist:lgSystemCity:edit")
	@RequestMapping(value = "save")
	public String save(LgSystemCity lgSystemCity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgSystemCity)){
			return form(lgSystemCity, model);
		}
		lgSystemCityService.save(lgSystemCity);
		addMessage(model, "保存业务系统城市成功");
		lgSystemCity = null;
		return tree(lgSystemCity, model);
	}
	
	@RequiresPermissions("lg:citylist:lgSystemCity:edit")
	@RequestMapping(value = "delete")
	public String delete(LgSystemCity lgSystemCity,  Model model, RedirectAttributes redirectAttributes) {
		int count = lgSystemCityService.haveComparison(lgSystemCity);
		if(count == 0) {
			lgSystemCityService.delete(lgSystemCity);
			addMessage(model, "删除业务系统城市成功");
		} else {
			addMessage(model, "删除失败，该城市存在对照关系，请先解除对照关系后再删除！");
		}
		lgSystemCity = null;
		return tree(lgSystemCity, model);
	}

	@RequestMapping(value = "json")
	public @ResponseBody List<LgSystemCity> json(LgSystemCity lgSystemCity, RedirectAttributes redirectAttributes) {
		return lgSystemCityService.findList(lgSystemCity);
	}

	@RequiresPermissions("lg:citylist:lgSystemCity:view")
	@RequestMapping(value = "tree")
	public String tree(LgSystemCity lgSystemCity, Model model) {
		model.addAttribute("cityList", lgSystemCityService.findList(lgSystemCity));
		return "modules/lg/citylist/lgSystemCity";
	}
	
	@RequiresPermissions("lg:citylist:lgSystemCity:edit")
	@RequestMapping(value = "move")
	public @ResponseBody String move(LgTreeSortEntity lgTreeSortEntity, Model model) {
		lgSystemCityService.move(lgTreeSortEntity);
		return "200";
	}
}