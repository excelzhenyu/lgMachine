/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.LgMachineDimension;
import com.k2data.platform.modules.lg.common.service.LgUtilCrudService;
import com.k2data.platform.modules.lg.dao.LgMachineDimensionDao;

/**
 * 整机维度Service
 * @author wangshengguo
 * @version 2016-05-09
 * 
 */
@Service
@Transactional(readOnly = true)
public class LgMachineDimensionService extends LgUtilCrudService<LgMachineDimensionDao, LgMachineDimension> {

	public LgMachineDimension get(String id) {
		return super.get(id);
	}
	
	public List<LgMachineDimension> findList(LgMachineDimension lgMachineDimension) {
		return super.findList(lgMachineDimension);
	}
	
	public Page<LgMachineDimension> findPage(Page<LgMachineDimension> page, LgMachineDimension lgMachineDimension) {
		return super.findPage(page, lgMachineDimension);
	}
	
	@Transactional(readOnly = false)
	public void save(LgMachineDimension lgMachineDimension) {
		super.save(lgMachineDimension);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgMachineDimension lgMachineDimension) {
		super.delete(lgMachineDimension);
	}
	
	public long selectCountByEntity(LgMachineDimension lgMachineDimension) {
		return super.selectCountByEntity(lgMachineDimension);
	}
}