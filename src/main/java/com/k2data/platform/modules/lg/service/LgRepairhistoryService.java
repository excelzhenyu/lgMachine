/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.LgRepairhistory;
import com.k2data.platform.modules.lg.dao.LgRepairhistoryDao;

/**
 * 报修记录Service
 * @author chenjingsi
 * @version 2016-05-13
 */
@Service
@Transactional(readOnly = true)
public class LgRepairhistoryService extends CrudService<LgRepairhistoryDao, LgRepairhistory> {

	public LgRepairhistory get(String id) {
		return super.get(id);
	}
	
	public List<LgRepairhistory> findList(LgRepairhistory lgRepairhistory) {
		return super.findList(lgRepairhistory);
	}
	
	public Page<LgRepairhistory> findPage(Page<LgRepairhistory> page, LgRepairhistory lgRepairhistory) {
		return super.findPage(page, lgRepairhistory);
	}
	
	@Transactional(readOnly = false)
	public void save(LgRepairhistory lgRepairhistory) {
		super.save(lgRepairhistory);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgRepairhistory lgRepairhistory) {
		super.delete(lgRepairhistory);
	}
	
}