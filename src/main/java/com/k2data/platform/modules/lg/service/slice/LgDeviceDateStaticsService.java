/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.slice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.distinguish.MissFalseDetail;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceDateStatics;
import com.k2data.platform.modules.lg.dao.slice.LgDeviceDateStaticsDao;

/**
 * 设备每日工作统计Service
 * @author wangshengguo
 * @version 2016-05-30
 */
@Service
@Transactional(readOnly = true)
public class LgDeviceDateStaticsService extends CrudService<LgDeviceDateStaticsDao, LgDeviceDateStatics> {

	@Autowired
	private LgDeviceDateStaticsDao lgDeviceDateStaticsDao;
	
	public LgDeviceDateStatics get(String id) {
		return super.get(id);
	}
	
	public List<LgDeviceDateStatics> findList(LgDeviceDateStatics lgDeviceDateStatics) {
		return super.findList(lgDeviceDateStatics);
	}
	

	public Page<LgDeviceDateStatics> findPage(Page<LgDeviceDateStatics> page, LgDeviceDateStatics lgDeviceDateStatics) {
		return super.findPage(page, lgDeviceDateStatics);
	}
	
	@Transactional(readOnly = false)
	public void save(LgDeviceDateStatics lgDeviceDateStatics) {
		super.save(lgDeviceDateStatics);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgDeviceDateStatics lgDeviceDateStatics) {
		super.delete(lgDeviceDateStatics);
	}
	public Integer getHours(Map<String, Object> params) {
		return lgDeviceDateStaticsDao.getHours(params);
	}
	public MissFalseDetail getMissAndFalseDetail(String deviceNo) {
		return lgDeviceDateStaticsDao.getMissAndFalseDetail(deviceNo);
	}
	public List<String> getOilSumChartDate(String deviceNo) {
		return lgDeviceDateStaticsDao.getOilSumChartDate(deviceNo);
	}
	public List<Integer> getOilSumChartData(String deviceNo) {
		return lgDeviceDateStaticsDao.getOilSumChartData(deviceNo);
	}
	
}