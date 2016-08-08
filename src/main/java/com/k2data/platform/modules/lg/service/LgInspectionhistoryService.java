/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.LgInspectionhistory;
import com.k2data.platform.modules.lg.dao.LgInspectionhistoryDao;

/**
 * 巡检整改Service
 * @author chenjingsi
 * @version 2016-06-29
 */
@Service
@Transactional(readOnly = true)
public class LgInspectionhistoryService extends CrudService<LgInspectionhistoryDao, LgInspectionhistory> {

	public LgInspectionhistory get(String id) {
		return super.get(id);
	}
	
	public List<LgInspectionhistory> findList(LgInspectionhistory lgInspectionhistory) {
		return super.findList(lgInspectionhistory);
	}
	
	public Page<LgInspectionhistory> findPage(Page<LgInspectionhistory> page, LgInspectionhistory lgInspectionhistory) {
		return super.findPage(page, lgInspectionhistory);
	}
	
	@Transactional(readOnly = false)
	public void save(LgInspectionhistory lgInspectionhistory) {
		super.save(lgInspectionhistory);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgInspectionhistory lgInspectionhistory) {
		super.delete(lgInspectionhistory);
	}
	
}