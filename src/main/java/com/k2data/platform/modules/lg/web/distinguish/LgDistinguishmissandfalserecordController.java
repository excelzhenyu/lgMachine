/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.distinguish;

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
import com.k2data.platform.modules.lg.entity.distinguish.LgDistinguishmissandfalserecord;
import com.k2data.platform.modules.lg.service.distinguish.LgDistinguishmissandfalserecordService;

/**
 * 机器虚报漏报记录Controller
 * @author chenjingsi
 * @version 2016-06-29
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/distinguish/lgDistinguishmissandfalserecord")
public class LgDistinguishmissandfalserecordController extends BaseController {

	@Autowired
	private LgDistinguishmissandfalserecordService lgDistinguishmissandfalserecordService;
	
	@ModelAttribute
	public LgDistinguishmissandfalserecord get(@RequestParam(required=false) String id) {
		LgDistinguishmissandfalserecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgDistinguishmissandfalserecordService.get(id);
		}
		if (entity == null){
			entity = new LgDistinguishmissandfalserecord();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:distinguish:lgDistinguishmissandfalserecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgDistinguishmissandfalserecord lgDistinguishmissandfalserecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgDistinguishmissandfalserecord> page = lgDistinguishmissandfalserecordService.findPage(new Page<LgDistinguishmissandfalserecord>(request, response), lgDistinguishmissandfalserecord); 
		model.addAttribute("page", page);
		return "modules/lg/distinguish/lgDistinguishmissandfalserecordList";
	}

	@RequiresPermissions("lg:distinguish:lgDistinguishmissandfalserecord:view")
	@RequestMapping(value = "form")
	public String form(LgDistinguishmissandfalserecord lgDistinguishmissandfalserecord, Model model) {
		model.addAttribute("lgDistinguishmissandfalserecord", lgDistinguishmissandfalserecord);
		return "modules/lg/distinguish/lgDistinguishmissandfalserecordForm";
	}

	@RequiresPermissions("lg:distinguish:lgDistinguishmissandfalserecord:edit")
	@RequestMapping(value = "save")
	public String save(LgDistinguishmissandfalserecord lgDistinguishmissandfalserecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgDistinguishmissandfalserecord)){
			return form(lgDistinguishmissandfalserecord, model);
		}
		lgDistinguishmissandfalserecordService.save(lgDistinguishmissandfalserecord);
		addMessage(redirectAttributes, "保存机器虚报漏报记录成功");
		return "redirect:"+Global.getAdminPath()+"/lg/distinguish/lgDistinguishmissandfalserecord/?repage";
	}
	
	@RequiresPermissions("lg:distinguish:lgDistinguishmissandfalserecord:edit")
	@RequestMapping(value = "delete")
	public String delete(LgDistinguishmissandfalserecord lgDistinguishmissandfalserecord, RedirectAttributes redirectAttributes) {
		lgDistinguishmissandfalserecordService.delete(lgDistinguishmissandfalserecord);
		addMessage(redirectAttributes, "删除机器虚报漏报记录成功");
		return "redirect:"+Global.getAdminPath()+"/lg/distinguish/lgDistinguishmissandfalserecord/?repage";
	}

}