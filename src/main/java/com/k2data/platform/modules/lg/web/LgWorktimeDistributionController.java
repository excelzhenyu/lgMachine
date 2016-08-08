/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.k2data.platform.common.utils.DateUtils;
import com.k2data.platform.common.utils.UploadUtils;
import com.k2data.platform.common.utils.excel.ImportExcel;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.modules.lg.entity.DeviceNoVO;
import com.k2data.platform.modules.lg.entity.LgWorktimeDistribution;
import com.k2data.platform.modules.lg.service.LgMachineTypeService;
import com.k2data.platform.modules.lg.service.LgMachineprofileService;
import com.k2data.platform.modules.lg.service.slice.LgDeviceDateStaticsService;
import com.k2data.platform.modules.sys.entity.User;

/**
 * 试验机工作时长分布Controller
 * @author chenjingsi
 * @version 2016-05-30
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/lgWorktimeDistribution")
public class LgWorktimeDistributionController extends BaseController {

	@Autowired
	LgMachineTypeService lgmachineTypeService;
	@Autowired
	LgDeviceDateStaticsService lgDeviceDateStaticsService;
	@Autowired
	LgMachineprofileService lgMachineprofileService;
	
	private  int oto4Num=0;
	private  int fourToEight=0;
	private  int eightToTwelve=0;
	private  int twelveToSixteen=0;
	private  int sixteenToTwenty=0;
	private  int twentyTo24=0;
	
	@RequestMapping(value = {"list", ""})
	public String list(LgWorktimeDistribution lgWorktimeDistribution, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/lg/lgWorktimeDistribution";
	}
	
	@RequestMapping(value = "query",method=RequestMethod.POST)
	public String query(LgWorktimeDistribution lgWorktimeDistribution,HttpServletRequest request,HttpServletResponse response,Model model) throws InvalidFormatException, IOException, InstantiationException, IllegalAccessException{
		oto4Num=0;
		fourToEight=0;
		eightToTwelve=0;
		twelveToSixteen=0;
		sixteenToTwenty=0;
		twentyTo24=0;
		List<String> machineNoList = new ArrayList<String>();
		LgWorktimeDistribution distributionFrom = (LgWorktimeDistribution)model.asMap().get("lgWorktimeDistribution");
		if(lgWorktimeDistribution.getXlsFile().isEmpty()==true){
		machineNoList = lgMachineprofileService.getMachineNoList(distributionFrom);
		}else{
		MultipartFile file = lgWorktimeDistribution.getXlsFile();
		ImportExcel ei = new ImportExcel(file, 1, 0);
		List<DeviceNoVO> list = ei.getDataList(DeviceNoVO.class);
		for (int i = 0; i < list.size(); i++) {
			machineNoList.add(list.get(i).getDeviceNo());
		}
		}
		lgWorktimeDistribution.setMachineNo(machineNoList);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("startDate", distributionFrom.getStartDate());
		params.put("endDate", distributionFrom.getEndDate());
		double days = DateUtils.getDistanceOfTwoDate(distributionFrom.getStartDate(), distributionFrom.getEndDate());
		int day = this.changeDoubleToInt(days);
		for (int i = 0; i < machineNoList.size(); i++) {
			params.put("deviceNo", machineNoList.get(i));
			Integer minutesResult = lgDeviceDateStaticsService.getHours(params);
			if(minutesResult==null){
				minutesResult=0;
			}
			int minutesAverage = minutesResult/day;
			countHour(minutesAverage);
		}
		lgWorktimeDistribution.setOto4Num(oto4Num);
		lgWorktimeDistribution.setFourToEight(fourToEight);
		lgWorktimeDistribution.setEightToTwelve(eightToTwelve);
		lgWorktimeDistribution.setTwelveToSixteen(twelveToSixteen);
		lgWorktimeDistribution.setSixteenToTwenty(sixteenToTwenty);
		lgWorktimeDistribution.setTwentyTo24(twentyTo24);
		
		model.addAttribute("lgWorktimeDistribution", lgWorktimeDistribution);
		
		return "modules/lg/lgWorktimeDistribution";
	}
	
	public void countHour(Integer minutes){
		int x=0 ;
		if(minutes>=0&&minutes<=240){
			x=0;
		}
		if(minutes>240&&minutes<=60*8){
			x=1;
		}
		if(minutes>60*8&&minutes<=60*12){
			x=2;
		}
		if(minutes>60*12&&minutes<=60*16){
			x=3;
		}
		if(minutes>60*16&&minutes<=60*20){
			x=4;
		}
		if(minutes>60*20&&minutes<=60*24){
			x=5;
		}
		switch (x) {
		case 0:
			oto4Num++;
			break;
		case 1:
			fourToEight++;
			break;
		case 2:
			eightToTwelve++;
			break;
		case 3:
			twelveToSixteen++;
			break;
		case 4:
			sixteenToTwenty++;
			break;
		case 5:
			twentyTo24++;
			break;
		}
	}
	
	public int changeDoubleToInt(double d){
		DecimalFormat dfi = new DecimalFormat("#");
		RoundingMode roundingMode = RoundingMode.UP;
		dfi.setRoundingMode(roundingMode);
		int parseInt = Integer.parseInt(dfi.format(d));
		return parseInt;
	}
	
 

}