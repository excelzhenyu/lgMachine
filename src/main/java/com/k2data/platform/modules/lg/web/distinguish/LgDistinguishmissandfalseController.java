/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.distinguish;

import java.text.ParseException;

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
import com.k2data.platform.modules.lg.entity.distinguish.LgDistinguishmissandfalse;
import com.k2data.platform.modules.lg.service.distinguish.LgDistinguishmissandfalseService;

/**
 * 虚漏报方案配置Controller
 * @author chenjingsi
 * @version 2016-06-29
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/distinguish/lgDistinguishmissandfalse")
public class LgDistinguishmissandfalseController extends BaseController {

	@Autowired
	private LgDistinguishmissandfalseService lgDistinguishmissandfalseService;
	
	@ModelAttribute
	public LgDistinguishmissandfalse get(@RequestParam(required=false) String id) {
		LgDistinguishmissandfalse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgDistinguishmissandfalseService.get(id);
		}
		if (entity == null){
			entity = new LgDistinguishmissandfalse();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:distinguish:lgDistinguishmissandfalse:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgDistinguishmissandfalse lgDistinguishmissandfalse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgDistinguishmissandfalse> page = lgDistinguishmissandfalseService.findPage(new Page<LgDistinguishmissandfalse>(request, response), lgDistinguishmissandfalse); 
		model.addAttribute("page", page);
		return "modules/lg/distinguish/lgDistinguishmissandfalseList";
	}

	@RequiresPermissions("lg:distinguish:lgDistinguishmissandfalse:view")
	@RequestMapping(value = "form")
	public String form(LgDistinguishmissandfalse lgDistinguishmissandfalse, Model model) {
		if(lgDistinguishmissandfalse.getSolutionType()==null){
		lgDistinguishmissandfalse.setSolutionType("1");
		}
		if(lgDistinguishmissandfalse.getIsValid()==null){
		lgDistinguishmissandfalse.setIsValid("1");
		}
		model.addAttribute("lgDistinguishmissandfalse", lgDistinguishmissandfalse);
		return "modules/lg/distinguish/lgDistinguishmissandfalseForm";
	}

	@RequiresPermissions("lg:distinguish:lgDistinguishmissandfalse:edit")
	@RequestMapping(value = "save")
	public String save(LgDistinguishmissandfalse lgDistinguishmissandfalse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgDistinguishmissandfalse)){
			return form(lgDistinguishmissandfalse, model);
		}
		lgDistinguishmissandfalseService.save(lgDistinguishmissandfalse);
		addMessage(redirectAttributes, "保存虚漏报方案配置成功");
		return "redirect:"+Global.getAdminPath()+"/lg/distinguish/lgDistinguishmissandfalse/?repage";
	}
	
	@RequiresPermissions("lg:distinguish:lgDistinguishmissandfalse:edit")
	@RequestMapping(value = "delete")
	public String delete(LgDistinguishmissandfalse lgDistinguishmissandfalse, RedirectAttributes redirectAttributes) {
		lgDistinguishmissandfalseService.delete(lgDistinguishmissandfalse);
		addMessage(redirectAttributes, "删除虚漏报方案配置成功");
		return "redirect:"+Global.getAdminPath()+"/lg/distinguish/lgDistinguishmissandfalse/?repage";
	}
	
	@RequiresPermissions("lg:distinguish:lgDistinguishmissandfalse:edit")
	@RequestMapping(value="checkSolutionName")
	@ResponseBody
	public String checkSolutionName(LgDistinguishmissandfalse lgDistinguishmissandfalse){
		LgDistinguishmissandfalse check = lgDistinguishmissandfalseService.checkSolutionName(lgDistinguishmissandfalse);
		if(check!=null){
			return "false";
		}else{
			return "true";
		}
	}


}