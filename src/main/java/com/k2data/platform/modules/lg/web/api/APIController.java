/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.api;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.lg.entity.LgMachineprofile;
import com.k2data.platform.modules.lg.service.LgMachineprofileService;
import com.k2data.platform.modules.lg.service.distinguish.LgDistinguishmissandfalseService;
import com.k2data.platform.modules.lg.service.distinguish.LgDistinguishmissandfalsedetailService;

/**
 * 对外APIController
 * @author chenjingsi
 * @version 2016-05-05
 */
@Controller
@RequestMapping(value = "/api/lg")
public class APIController extends BaseController {

	@Autowired
	private LgMachineprofileService lgMachineprofileService;
	@Autowired
	private LgDistinguishmissandfalseService lgDistinguishmissandfalseService;
	
	@RequestMapping(value="/getMachineInfoByCode")
	@ResponseBody
	public Map<String,Object> getMachineInfoByCode(String code,String pinCode){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		LgMachineprofile lgMachineprofile = new LgMachineprofile();
		List<LgMachineprofile> list = new ArrayList<LgMachineprofile>();
		if((code==null || code.equals("") || code.equals("&quot;&quot;")) && (pinCode == null || pinCode.equals("") || pinCode.equals("&quot;&quot;"))){
			resultMap.put("resultCode", 1001);
			resultMap.put("resultMessage", "至少要有一个参数不为空");
			return resultMap;
		}else{
			if(code==null || code.equals("") || code.equals("&quot;&quot;")){
				lgMachineprofile.setPinCode(pinCode);
				list = lgMachineprofileService.findList(lgMachineprofile);			
			}else
			if(pinCode == null || pinCode.equals("") || pinCode.equals("&quot;&quot;")){
				lgMachineprofile.setCode(code);
				list = lgMachineprofileService.findList(lgMachineprofile);	
			}else{
				lgMachineprofile.setCode(code);
				lgMachineprofile.setPinCode(pinCode);
				list = lgMachineprofileService.findList(lgMachineprofile);
			}
		}
		if(list.size()>0){
			resultMap.put("resultCode", 1000);
			resultMap.put("resultMessage", "查询成功");
			resultMap.put("result", list.get(0));
		}else{
			resultMap.put("resultCode", 1002);
			resultMap.put("resultMessage", "查询无此项");
		}
		return resultMap;
	}
	
	
	@RequestMapping(value="insertMissAndFalse")
	@ResponseBody
	public String insertMissAndFalse() throws ParseException{
		lgDistinguishmissandfalseService.insertMissAndFalse();
		return "";
	}
}