/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.instancematerial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.instancematerial.dao.LgInstancematerialDao;
import com.k2data.platform.modules.lg.instancematerial.entity.LgInstancematerial;

/**
 * 实例物料Service
 * @author lidong
 * @version 2016-05-16
 */
@Service
@Transactional(readOnly = true)
public class LgInstancematerialService extends CrudService<LgInstancematerialDao, LgInstancematerial> {

	@Autowired
	private LgInstancematerialDao lgInstancematerialDao;
	
	public LgInstancematerial get(String id) {
		return super.get(id);
	}
	
	public List<LgInstancematerial> findList(LgInstancematerial lgInstancematerial) {
		return super.findList(lgInstancematerial);
	}
	
	public Page<LgInstancematerial> findPage(Page<LgInstancematerial> page, LgInstancematerial lgInstancematerial) {
		return super.findPage(page, lgInstancematerial);
	}
	
	@Transactional(readOnly = false)
	public void save(LgInstancematerial lgInstancematerial) {
		super.save(lgInstancematerial);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgInstancematerial lgInstancematerial) {
		super.delete(lgInstancematerial);
	}
	
	/**
	 * 检查指定中性物料下的实例物料是否存在
	 * 
	 * @param materialPin 实例物料PIN
	 * @param ledgerId 中性物料ID
	 * @return
	 */
	public String checkMaterialPin(LgInstancematerial lgInstancematerial) {
		return lgInstancematerialDao.checkMaterialPin(lgInstancematerial);
	}
	
}