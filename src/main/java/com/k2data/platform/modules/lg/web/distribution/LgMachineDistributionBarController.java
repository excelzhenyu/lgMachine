/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.distribution;


import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineDistributionByCity;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineDistributionEntity;
import com.k2data.platform.modules.lg.service.distribution.LgMachineDistributionBarService;

/**
 * 机器柱状图分布Controller
 * @author wangshengguo
 * @version 2016-07-01
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/distribution/lgMachineDistributionBar")
public class LgMachineDistributionBarController extends BaseController {

	@Autowired
	private LgMachineDistributionBarService lgMachineDistributionBarService;
	
	@RequestMapping(value = "bar")
	public String map(LgMachineDistributionEntity lgMachineDistributionEntity, HttpServletRequest request, Model model) {
		lgMachineDistributionEntity.setRunMonth(new Date());
		model.addAttribute("lgMachineDistributionEntity", lgMachineDistributionEntity);
		return "modules/lg/distribution/lgMachineDistributionBar"; 
	}
	@RequestMapping(value = "saveMachineOrderDistributionByCity")
	public @ResponseBody String saveMachineOrderDistributionByCity(LgMachineDistributionByCity lgMachineDistributionByCity, RedirectAttributes redirectAttributes) {
		for(int year = 2015; year < 2017; year++) {
			for(int month = 1; month < 13; month++) {
				lgMachineDistributionBarService.saveMachineOrderDistributionByCity(year, month);
			}
		}
		return "success";
	}
	
	@RequestMapping(value = "saveMachineOrderDistributionByProvince")
	public @ResponseBody String saveMachineOrderDistributionByProvince(LgMachineDistributionByCity lgMachineDistributionByCity, RedirectAttributes redirectAttributes) {
		for(int year = 2015; year < 2017; year++) {
			for(int month = 1; month < 13; month++) {
				lgMachineDistributionBarService.saveMachineOrderDistributionByProvince(year, month);
			}
		}
		return "success";
	}
	@RequestMapping(value = "saveMachineTypeDistributionByCity")
	public @ResponseBody String saveMachineTypeDistributionByCity(LgMachineDistributionByCity lgMachineDistributionByCity, RedirectAttributes redirectAttributes) {
		for(int year = 2015; year < 2017; year++) {
			for(int month = 1; month < 13; month++) {
				lgMachineDistributionBarService.saveMachineTypeDistributionByCity(year, month);
			}
		}
		return "success";
	}
	@RequestMapping(value = "saveMachineTypeDistributionByProvince")
	public @ResponseBody String saveMachineTypeDistributionByProvince(LgMachineDistributionByCity lgMachineDistributionByCity, RedirectAttributes redirectAttributes) {
		for(int year = 2015; year < 2017; year++) {
			for(int month = 1; month < 13; month++) {
				lgMachineDistributionBarService.saveMachineTypeDistributionByProvince(year, month);
			}
		}
		return "success";
	}
	@RequestMapping(value = "getBarData")
	public @ResponseBody Map<String, Object>  getBarData(LgMachineDistributionEntity lgMachineDistributionEntity, HttpServletRequest request) {
		return lgMachineDistributionBarService.getMachineSeriesData(lgMachineDistributionEntity);
	}
	
}