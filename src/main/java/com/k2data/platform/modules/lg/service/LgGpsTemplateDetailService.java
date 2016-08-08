/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.LgGpsTemplateDetail;
import com.k2data.platform.modules.lg.dao.LgGpsTemplateDetailDao;

/**
 * GPS传感器模板明细Service
 * @author chenjingsi
 * @version 2016-06-02
 */
@Service
@Transactional(readOnly = true)
public class LgGpsTemplateDetailService extends CrudService<LgGpsTemplateDetailDao, LgGpsTemplateDetail> {
	@Autowired
	LgGpsTemplateDetailDao lgGpsTemplateDetailDao;

	public LgGpsTemplateDetail get(String id) {
		return super.get(id);
	}
	
	public List<LgGpsTemplateDetail> findList(LgGpsTemplateDetail lgGpsTemplateDetail) {
		return super.findList(lgGpsTemplateDetail);
	}
	
	public Page<LgGpsTemplateDetail> findPage(Page<LgGpsTemplateDetail> page, LgGpsTemplateDetail lgGpsTemplateDetail) {
		return super.findPage(page, lgGpsTemplateDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(LgGpsTemplateDetail lgGpsTemplateDetail) {
		super.save(lgGpsTemplateDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgGpsTemplateDetail lgGpsTemplateDetail) {
		super.delete(lgGpsTemplateDetail);
	}
	
	public List<LgGpsTemplateDetail> getSensorList(String code) {
		return lgGpsTemplateDetailDao.getSensorList(code);
	}

	public LgGpsTemplateDetail checkSensorMark(LgGpsTemplateDetail lgGpsTemplateDetail) {
		return lgGpsTemplateDetailDao.checkSensorMark(lgGpsTemplateDetail);
	}

	@Transactional(readOnly = false)
	public void deleteByTemplateId(LgGpsTemplateDetail lgGpsTemplateDetail) {
		lgGpsTemplateDetailDao.deleteByTemplateId(lgGpsTemplateDetail);
	}

	public List<LgGpsTemplateDetail> getTemplateDetailList(String deviceNo) {
		return lgGpsTemplateDetailDao.getTemplateDetailList(deviceNo);
	}
	
}