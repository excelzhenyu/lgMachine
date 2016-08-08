/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.slice;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k2data.platform.common.utils.DateUtils;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.modules.lg.common.utils.CalendarUtils;
import com.k2data.platform.modules.lg.service.slice.LgDeviceDateService;
import com.k2data.platform.modules.lg.service.slice.LgDeviceSliceService;
import com.k2data.platform.modules.lg.service.slice.LgDeviceStaticsService;
import com.k2data.platform.modules.lg.service.slice.LgSliceStateStaticsService;

/**
 * 设备工作统计Controller
 * @author wangshengguo
 * @version 2016-05-30
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/slice/lgDeviceStatics")
public class LgDeviceStaticsController extends BaseController {

	@Autowired
	private LgDeviceStaticsService lgDeviceStaticsService;
	@Autowired
	private LgDeviceDateService lgDeviceDateService;
	@Autowired 
	private LgDeviceSliceService lgDeviceSliceService;
	
	@Autowired
	private LgSliceStateStaticsService lgSliceStateStaticsService;
	private static final String[] deviceNos = {"C301F4",
            "C301F8",
            "C302AE",
            "C302D0",
            "C3020D",
            "C3022D",
            "C30266",
            "C3027E",
            "C30229",
            "C30213"};
	@RequestMapping(value = "saveDeviceWeekStatics")
	public @ResponseBody String saveDeviceWeekStatics() {
		
		int count = 0;
		for(int year = 2016; year < 2017; year++) {
			//int weekConnt = CalendarUtils.getWeekNumByYear(year);
			//for(int week = 1; week < weekConnt + 1; week++ ) {
			for(int week = 1; week < 30 ; week++ ) {
				for(String deviceNo :deviceNos) {
					count += lgDeviceStaticsService.saveDeviceWeekStatics(deviceNo, year, week);
				}
			}
		}
		return "success-" + count; 
	}
	
	@RequestMapping(value = "saveDeviceMonthStatics")
	public @ResponseBody String saveDeviceMonthStatics() {
		//String[] deviceNos = {"C2000B","C30090"};
		int count = 0;
		for(int year = 2016; year < 2017; year++) {
			for(int month = 1; month < 7; month++) {
				for(String deviceNo :deviceNos ) {
					count += lgDeviceStaticsService.saveDeviceMonthStatics(deviceNo, year, month);
				}
			}
		}
		return "success-" + count; 
	}
	
	@RequestMapping(value = "saveDeviceSeasonStatics")
	public @ResponseBody String saveDeviceSeasonStatics() {
		//String[] deviceNos = {"C2000B","C30090"};
		int count = 0;

		for(int year = 2016; year < 2017; year++) {
			for(int season = 1; season < 3; season++) {
				for(String deviceNo :deviceNos ) {
					count +=lgDeviceStaticsService.saveDeviceSeasonStatics(deviceNo, year, season);
				}
			}
		}
		return "success-" + count; 
	}
	
	@RequestMapping(value = "saveDeviceYearStatics")
	public @ResponseBody String saveDeviceYearStatics() {
		//String[] deviceNos = {"C2000B","C30090"};
		int count = 0;
		for(int year = 2016; year < 2017; year++) {
			for(String deviceNo :deviceNos ) {
				count += lgDeviceStaticsService.saveDeviceYearStatics(deviceNo, year);

			}
		}
		return "success-" + count; 
	}
	
	
	@RequestMapping(value = "saveDeviceDayStatics")
	public @ResponseBody String saveDeviceDayStatics(HttpServletRequest request) {
		//Date recordTime = DateUtils.parseDate("2016-03-03");
		long start = System.currentTimeMillis();
		//String deviceNo = "C301F4";
	    Calendar beginCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		beginCalendar.setTime(DateUtils.parseDate("2016-01-01"));
		endCalendar.setTime(DateUtils.parseDate("2016-07-01"));
		while(beginCalendar.getTime().getTime() < endCalendar.getTime().getTime()) {
			Date day = beginCalendar.getTime();
			for(String device: deviceNos) {
				//lgDeviceSliceService.saveSyncDeviceSliceData(device, day);
				lgDeviceDateService.saveSyncDayData(device, day);
			}
			beginCalendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		long end = System.currentTimeMillis();

		System.out.println("********************************************\n" + (end - start));
		return "success" +(end - start);
	}
	
	
	@RequestMapping(value = "saveAsyncDeviceSliceData")
	public @ResponseBody String saveAsyncDeviceSliceData(HttpServletRequest request) {
		String deviceNo= "C301F4";
		String record = request.getParameter("record");
		Date recordTime = DateUtils.parseDate(record);
		lgDeviceSliceService.saveAsyncDeviceSliceData(deviceNo, recordTime);
		return "success";
	}
	
	@RequestMapping(value = "saveDeviceSensorSliceStaticsStatus")
	public @ResponseBody String saveDeviceSensorSliceStaticsStatus(HttpServletRequest request) {
		Calendar beginCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		beginCalendar.setTime(DateUtils.parseDate("2016-03-01"));
		endCalendar.setTime(DateUtils.parseDate("2016-07-01"));

		while(beginCalendar.getTime().getTime() < endCalendar.getTime().getTime()) {
			Date day =beginCalendar.getTime();
			lgSliceStateStaticsService.saveDeviceSensorSliceStaticsStatus(day);
			beginCalendar.add(Calendar.DAY_OF_YEAR, 1);
		}

		return "success";
	}
	
	
	
	
}