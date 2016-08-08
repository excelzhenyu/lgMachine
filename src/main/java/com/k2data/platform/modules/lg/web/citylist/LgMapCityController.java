/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.citylist;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.lg.entity.citylist.LgMapCity;
import com.k2data.platform.modules.lg.entity.citylist.LgTreeSortEntity;
import com.k2data.platform.modules.lg.service.citylist.LgCityComparisonService;
import com.k2data.platform.modules.lg.service.citylist.LgMapCityService;

/**
 * 地图城市列表Controller
 * @author wangshengguo
 * @version 2016-06-02
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/citylist/lgMapCity")
public class LgMapCityController extends BaseController {

	@Autowired
	private LgMapCityService lgMapCityService;
	
	@ModelAttribute
	public LgMapCity get(@RequestParam(required=false) String id) {
		LgMapCity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgMapCityService.get(id);
		}
		if (entity == null){
			entity = new LgMapCity();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:citylist:lgMapCity:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgMapCity lgMapCity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgMapCity> page = lgMapCityService.findPage(new Page<LgMapCity>(request, response), lgMapCity); 
		model.addAttribute("page", page);
		return "modules/lg/citylist/lgMapCityList";
	}

	@RequiresPermissions("lg:citylist:lgMapCity:view")
	@RequestMapping(value = "form")
	public String form(LgMapCity lgMapCity, Model model) {
		model.addAttribute("lgMapCity", lgMapCity);
		return "modules/lg/citylist/lgMapCityForm";
	}


	@RequiresPermissions("lg:citylist:lgMapCity:edit")
	@RequestMapping(value = "save")
	public String save(LgMapCity lgMapCity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgMapCity)){
			return form(lgMapCity, model);
		}
		lgMapCityService.save(lgMapCity);
		addMessage(model, "保存地图城市成功");
		lgMapCity = null;
		return tree(lgMapCity, model);
	}
	
	@RequiresPermissions("lg:citylist:lgMapCity:edit")
	@RequestMapping(value = "delete")
	public String delete(LgMapCity lgMapCity, Model model, RedirectAttributes redirectAttributes) {
		int count = lgMapCityService.haveComparison(lgMapCity);
		if(count == 0) {
			lgMapCityService.delete(lgMapCity);
			addMessage(model, "删除地图城市成功");
		} else {
			addMessage(model, "删除失败，该城市存在对照关系，请先解除对照关系后再删除！");
		}
		lgMapCity = null;
		return tree(lgMapCity, model);
	}

	@RequiresPermissions("lg:citylist:lgMapCity:edit")
	@RequestMapping(value = "tree")
	public String tree(LgMapCity lgMapCity, Model model) {
		model.addAttribute("cityList", lgMapCityService.findList(lgMapCity));
		return "modules/lg/citylist/lgMapCity";
	}
	@RequiresPermissions("lg:citylist:lgMapCity:edit")
	@RequestMapping(value = "move")
	public @ResponseBody String move(LgTreeSortEntity lgTreeSortEntity, Model model) {
		lgMapCityService.move(lgTreeSortEntity);
		return "200";
	}
}