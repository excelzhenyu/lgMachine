/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.slice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.common.utils.DateUtils;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceStatics;
import com.k2data.platform.modules.lg.common.utils.CalendarUtils;
import com.k2data.platform.modules.lg.dao.slice.LgDeviceStaticsDao;

/**
 * 设备 周/月/季度/年 工作统计Service、每日统计在LgDeviceDateService
 * @author wangshengguo
 * @version 2016-07-27
 */
@Service
@Transactional(readOnly = true)
public class LgDeviceStaticsService extends CrudService<LgDeviceStaticsDao, LgDeviceStatics> {
	@Autowired
	private LgDeviceStaticsDao lgDeviceStaticsDao;

	//按周统计 从按天统计中取数据
	@Transactional(readOnly = false)
	public int saveDeviceWeekStatics(String deviceNo, int year, int week) {
		LgDeviceStatics entity = new LgDeviceStatics();
		String weekBegin = CalendarUtils.getYearWeekFirstDay(year, week);
		String weekEnd = CalendarUtils.getYearWeekEndDay(year, week);
		entity.setDeviceNo(deviceNo);
		entity.setDateBegin(DateUtils.parseDate(weekBegin));
		entity.setDateEnd(DateUtils.parseDate(weekEnd));
		entity.setWorkDateId(CalendarUtils.genWeekNumber(year, week));
		entity.setWorkDateName(CalendarUtils.genWeekName(year, week));
		return lgDeviceStaticsDao.saveDeviceWeekStatics(entity);
	}
	//按月统计 从按天统计中取数据
	@Transactional(readOnly = false)
	public int saveDeviceMonthStatics(String deviceNo, int year, int month) {
		LgDeviceStatics entity = new LgDeviceStatics();
		String monthBegin = CalendarUtils.getYearMonthFirstDay(year, month)
;		String monthEnd = CalendarUtils.getYearMonthEndDay(year, month);
		entity.setDeviceNo(deviceNo);
		entity.setDateBegin(DateUtils.parseDate(monthBegin));
		entity.setDateEnd(DateUtils.parseDate(monthEnd));
		entity.setWorkDateId(CalendarUtils.genMonthNumber(year, month));
		entity.setWorkDateName(CalendarUtils.genMonthName(year, month));
		return lgDeviceStaticsDao.saveDeviceMonthStatics(entity);
	}
	//按季度统计 从按月统计中取数据
	@Transactional(readOnly = false)
	public int saveDeviceSeasonStatics(String deviceNo, int year, int quarter) {
		LgDeviceStatics entity = new LgDeviceStatics();
		int beginWorkId = CalendarUtils.getYearQuarterFirstMonthNumber(year, quarter);
		int endWorkId = CalendarUtils.getYearQuarterEndMonthNumber(year, quarter);
		entity.setDeviceNo(deviceNo);
		entity.setBeginWorkId(beginWorkId);
		entity.setEndWorkId(endWorkId);
		entity.setWorkDateId(CalendarUtils.genQuarterNumber(year, quarter));
		entity.setWorkDateName(CalendarUtils.genQuarterName(year, quarter));
		return lgDeviceStaticsDao.saveDeviceSeasonStatics(entity);
	}
	//按年统计 从按季度统计中取数据
	@Transactional(readOnly = false)
	public int saveDeviceYearStatics(String deviceNo, int year) {
		LgDeviceStatics entity = new LgDeviceStatics();
		int beginWorkId = CalendarUtils.getYearFirstQuarterNumber(year);
		int endWorkId = CalendarUtils.getYearEndQuarterNumber(year);
		entity.setDeviceNo(deviceNo);
		entity.setBeginWorkId(beginWorkId);
		entity.setEndWorkId(endWorkId);
		entity.setWorkDateId(CalendarUtils.genYearNumber(year));
		entity.setWorkDateName(CalendarUtils.genYearName(year));
		return lgDeviceStaticsDao.saveDeviceYearStatics(entity);
	}
	
}