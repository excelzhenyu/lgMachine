package com.k2data.platform.modules.lg.web.homepage;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.modules.lg.service.homepage.LgHomepageDisplayService;
import com.k2data.platform.modules.sys.entity.User;
import com.k2data.platform.modules.sys.utils.UserUtils;
import com.k2data.platform.modules.lg.entity.homepage.LgUserHomepageRelationship;
/**
 * 用户首页Controller
 * @author wangshengguo
 * @version 2016-05-19
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgHomepage")
public class LgHomepageDisplayController extends BaseController {
	
	@Autowired
	private LgHomepageDisplayService  lgHomepageDisplayService;
	
	@RequestMapping(value = "display")
	public String display(Model model, HttpServletRequest request) {
		User user = UserUtils.getUser();
		String tid = user.getId();
		LgUserHomepageRelationship lgUserHomepageRelationship = new LgUserHomepageRelationship();
		lgUserHomepageRelationship.setUserId(tid);
		String path = request.getContextPath()+ Global.getAdminPath();
		List<LgUserHomepageRelationship> homepageList = lgHomepageDisplayService.findList(lgUserHomepageRelationship);
		model.addAttribute("dynamicHtml", lgHomepageDisplayService.genHtml(homepageList, path));
		model.addAttribute("dynamicRow", lgHomepageDisplayService.calcRows(homepageList.size()));
		return "modules/lg/homepage/lgHomepageDisplay";
	}

}
