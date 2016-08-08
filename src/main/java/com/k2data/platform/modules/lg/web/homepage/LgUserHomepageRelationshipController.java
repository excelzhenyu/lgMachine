/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.homepage;

import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.lg.entity.homepage.LgUserHomepageRelationship;
import com.k2data.platform.modules.lg.service.homepage.LgUserHomepageRelationshipService;
import com.k2data.platform.modules.sys.entity.Role;
import com.k2data.platform.modules.sys.service.SystemService;
import com.k2data.platform.modules.sys.utils.UserUtils;

/**
 * 用户首页关系Controller
 * @author wangshengguo
 * @version 2016-05-17
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgUserHomepageRelationship")
public class LgUserHomepageRelationshipController extends BaseController {

	@Autowired
	private LgUserHomepageRelationshipService lgUserHomepageRelationshipService;
	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public LgUserHomepageRelationship get(@RequestParam(required=false) String id) {
		LgUserHomepageRelationship entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgUserHomepageRelationshipService.get(id);
		}
		if (entity == null){
			entity = new LgUserHomepageRelationship();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:lgUserHomepageRelationship:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgUserHomepageRelationship lgUserHomepageRelationship, HttpServletRequest request, HttpServletResponse response, Model model) {
		lgUserHomepageRelationship.setUserId(UserUtils.getUser().getId());
		Page<LgUserHomepageRelationship> page = lgUserHomepageRelationshipService.findPage(new Page<LgUserHomepageRelationship>(request, response), lgUserHomepageRelationship); 
		model.addAttribute("page", page);
		return "modules/lg/homepage/lgUserHomepageRelationshipList";
	}

	@RequiresPermissions("lg:lgUserHomepageRelationship:view")
	@RequestMapping(value = "form")
	public String form(LgUserHomepageRelationship lgUserHomepageRelationship, Model model) {
		lgUserHomepageRelationship.setUserId(UserUtils.getUser().getId());
		List<LgUserHomepageRelationship> urlList = lgUserHomepageRelationshipService.findList(lgUserHomepageRelationship);
		List<Map<String, Object>> menuMapList = new ArrayList<Map<String, Object>>();
		for(LgUserHomepageRelationship urlHomepage : urlList) {
			Map<String, Object> homePageMap = new HashMap<String, Object>();
			homePageMap.put("urlId", urlHomepage.getUrlId());
			homePageMap.put("urlNo", urlHomepage.getUrlNo());
			menuMapList.add(homePageMap);
		}
		lgUserHomepageRelationship.setMenuMapList(menuMapList);
		model.addAttribute("menuList", systemService.findAllMenu());
		model.addAttribute("userHomepage", lgUserHomepageRelationship);
		
		return "modules/lg/homepage/lgUserHomepageRelationshipForm";
	}

	@RequiresPermissions("lg:lgUserHomepageRelationship:edit")
	@RequestMapping(value = "save")
	public String save(LgUserHomepageRelationship lgUserHomepageRelationship, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgUserHomepageRelationship)){
			return form(lgUserHomepageRelationship, model);
		}
		lgUserHomepageRelationshipService.insertHomePageUrl(lgUserHomepageRelationship);
		//addMessage(redirectAttributes, "保存用户首页关系成功");
		//return "redirect:"+Global.getAdminPath()+"/lg/lgUserHomepageRelationship/?repage";
		addMessage(model, "保存用户首页成功");
		return form(lgUserHomepageRelationship,model);
	}
	
	@RequiresPermissions("lg:lgUserHomepageRelationship:edit")
	@RequestMapping(value = "delete")
	public String delete(LgUserHomepageRelationship lgUserHomepageRelationship, RedirectAttributes redirectAttributes) {
		lgUserHomepageRelationshipService.delete(lgUserHomepageRelationship);
		addMessage(redirectAttributes, "删除用户首页关系成功");
		return "redirect:"+Global.getAdminPath()+"/lg/lgUserHomepageRelationship/?repage";
	}

}