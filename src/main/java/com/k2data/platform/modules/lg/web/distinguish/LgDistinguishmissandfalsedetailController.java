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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.lg.entity.distinguish.LgDistinguishmissandfalsedetail;
import com.k2data.platform.modules.lg.service.distinguish.LgDistinguishmissandfalsedetailService;

/**
 * 虚漏报方案配置详细Controller
 * @author chenjingsi
 * @version 2016-06-29
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/distinguish/lgDistinguishmissandfalsedetail")
public class LgDistinguishmissandfalsedetailController extends BaseController {

	@Autowired
	private LgDistinguishmissandfalsedetailService lgDistinguishmissandfalsedetailService;
	
	@ModelAttribute
	public LgDistinguishmissandfalsedetail get(@RequestParam(required=false) String id) {
		LgDistinguishmissandfalsedetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgDistinguishmissandfalsedetailService.get(id);
		}
		if (entity == null){
			entity = new LgDistinguishmissandfalsedetail();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(LgDistinguishmissandfalsedetail lgDistinguishmissandfalsedetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgDistinguishmissandfalsedetail> page = lgDistinguishmissandfalsedetailService.findPage(new Page<LgDistinguishmissandfalsedetail>(request, response), lgDistinguishmissandfalsedetail); 
		model.addAttribute("page", page);
		return "modules/lg/distinguish/lgDistinguishmissandfalsedetailList";
	}

	@RequestMapping(value = "form")
	public String form(LgDistinguishmissandfalsedetail lgDistinguishmissandfalsedetail, Model model) {
		if(lgDistinguishmissandfalsedetail.getOption()==null){
			lgDistinguishmissandfalsedetail.setOption("1");
		}
		if(lgDistinguishmissandfalsedetail.getIsValid()==null){
			lgDistinguishmissandfalsedetail.setIsValid(1);
		}
		model.addAttribute("lgDistinguishmissandfalsedetail", lgDistinguishmissandfalsedetail);
		return "modules/lg/distinguish/lgDistinguishmissandfalsedetailForm";
	}

	@RequestMapping(value = "save")
	public String save(LgDistinguishmissandfalsedetail lgDistinguishmissandfalsedetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgDistinguishmissandfalsedetail)){
			return form(lgDistinguishmissandfalsedetail, model);
		}
		lgDistinguishmissandfalsedetailService.save(lgDistinguishmissandfalsedetail);
		addMessage(redirectAttributes, "保存虚漏报方案配置详细成功");
		return "redirect:"+Global.getAdminPath()+"/lg/distinguish/lgDistinguishmissandfalsedetail/list?distinguishId="+lgDistinguishmissandfalsedetail.getDistinguishId();
	}
	
	@RequestMapping(value = "delete")
	public String delete(LgDistinguishmissandfalsedetail lgDistinguishmissandfalsedetail, RedirectAttributes redirectAttributes) {
		lgDistinguishmissandfalsedetailService.delete(lgDistinguishmissandfalsedetail);
		addMessage(redirectAttributes, "删除虚漏报方案配置详细成功");
		return "redirect:"+Global.getAdminPath()+"/lg/distinguish/lgDistinguishmissandfalsedetail/?repage";
	}
	
	@RequestMapping(value="checkCondition")
	@ResponseBody
	public String checkCondition(LgDistinguishmissandfalsedetail lgDistinguishmissandfalsedetail){
		LgDistinguishmissandfalsedetail check = lgDistinguishmissandfalsedetailService.checkCondition(lgDistinguishmissandfalsedetail);
		if(check!=null){
			return "false";
		}else{
			return "true";
		}
	}

}