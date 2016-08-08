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
import com.k2data.platform.modules.lg.entity.LgMachineGpsContrast;
import com.k2data.platform.modules.lg.dao.LgMachineGpsContrastDao;

/**
 * 整机与GPS设备对照Service
 * @author chenjingsi
 * @version 2016-06-03
 */
@Service
@Transactional(readOnly = true)
public class LgMachineGpsContrastService extends CrudService<LgMachineGpsContrastDao, LgMachineGpsContrast> {

	@Autowired
	private LgMachineGpsContrastDao lgMachineGpsContrastDao;
	
	public LgMachineGpsContrast get(String id) {
		return super.get(id);
	}
	
	public List<LgMachineGpsContrast> findList(LgMachineGpsContrast lgMachineGpsContrast) {
		return super.findList(lgMachineGpsContrast);
	}
	
	public Page<LgMachineGpsContrast> findPage(Page<LgMachineGpsContrast> page, LgMachineGpsContrast lgMachineGpsContrast) {
		return super.findPage(page, lgMachineGpsContrast);
	}
	
	@Transactional(readOnly = false)
	public void save(LgMachineGpsContrast lgMachineGpsContrast) {
		super.save(lgMachineGpsContrast);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgMachineGpsContrast lgMachineGpsContrast) {
		super.delete(lgMachineGpsContrast);
	}

	public LgMachineGpsContrast checkDeviceNo(LgMachineGpsContrast lgMachineGpsContrast) {
		return lgMachineGpsContrastDao.checkDeviceNo(lgMachineGpsContrast);
	}

	public LgMachineGpsContrast checkGpsNo(LgMachineGpsContrast lgMachineGpsContrast) {
		return lgMachineGpsContrastDao.checkGpsNo(lgMachineGpsContrast);
	}
	
}