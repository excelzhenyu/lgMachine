/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.modules.lg.entity.LgMachineType;
import com.k2data.platform.modules.lg.common.service.LgUtilCrudService;
import com.k2data.platform.modules.lg.dao.LgMachineTypeDao;

/**
 * 整机类别Service
 * @author wangshengguo
 * @version 2016-05-09
 * 
 */
@Service
@Transactional(readOnly = true)
public class LgMachineTypeService extends LgUtilCrudService<LgMachineTypeDao, LgMachineType> {

	@Autowired
	private LgMachineTypeDao lgMachineTypeDao;
	
	public LgMachineType get(String id) {
		return super.get(id);
	}
	
	public List<LgMachineType> findList(LgMachineType lgMachineType) {
		return super.findList(lgMachineType);
	}
	
	public Page<LgMachineType> findPage(Page<LgMachineType> page, LgMachineType lgMachineType) {
		return super.findPage(page, lgMachineType);
	}
	
	@Transactional(readOnly = false)
	public void save(LgMachineType lgMachineType) {
		super.save(lgMachineType);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgMachineType lgMachineType) {
		super.delete(lgMachineType);
	}
	
	public long selectCountByEntity(LgMachineType lgMachineType) {
		return super.selectCountByEntity(lgMachineType);
	}

	public LgMachineType checkOrderNumber(LgMachineType lgMachineType) {
		return lgMachineTypeDao.checkOrderNumber(lgMachineType);
	}
	
	public List<String> getProductTypeList( String machineType) {
		return lgMachineTypeDao.getProductTypeList(machineType);
	}

}