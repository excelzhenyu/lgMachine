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
import com.k2data.platform.modules.lg.entity.LgSensorlist;
import com.k2data.platform.modules.lg.entity.LgSensorlistVO;
import com.k2data.platform.modules.lg.dao.LgSensorlistDao;

/**
 * 整机传感器清单Service
 * @author chenjingsi
 * @version 2016-05-09
 */
@Service
@Transactional(readOnly = true)
public class LgSensorlistService extends CrudService<LgSensorlistDao, LgSensorlist> {
	@Autowired
	private LgSensorlistDao lgSensorlistDao;
	
	public LgSensorlist get(String id) {
		return super.get(id);
	}
	
	public List<LgSensorlist> findList(LgSensorlist lgSensorlist) {
		return super.findList(lgSensorlist);
	}
	
	public Page<LgSensorlist> findPage(Page<LgSensorlist> page, LgSensorlist lgSensorlist) {
		return super.findPage(page, lgSensorlist);
	}
	
	@Transactional(readOnly = false)
	public void save(LgSensorlist lgSensorlist) {
		super.save(lgSensorlist);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgSensorlist lgSensorlist) {
		super.delete(lgSensorlist);
	}
	@Transactional(readOnly = false)
	public void updateStopSensor(String id) {
		lgSensorlistDao.updateStopSensor(id);
	}

	public LgSensorlist checkSensorCode(LgSensorlist lgSensorlist) {
		return lgSensorlistDao.checkSensorCode(lgSensorlist);
	}

	public LgSensorlist checkSensorPin(LgSensorlist lgSensorlist) {
		return lgSensorlistDao.checkSensorPin(lgSensorlist);
	}
	
}