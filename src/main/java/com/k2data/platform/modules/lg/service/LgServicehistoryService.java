/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.LgServicehistory;
import com.k2data.platform.modules.lg.dao.LgServicehistoryDao;

/**
 * 维修记录Service
 * @author chenjingsi
 * @version 2016-05-13
 */
@Service
@Transactional(readOnly = true)
public class LgServicehistoryService extends CrudService<LgServicehistoryDao, LgServicehistory> {

	public LgServicehistory get(String id) {
		return super.get(id);
	}
	
	public List<LgServicehistory> findList(LgServicehistory lgServicehistory) {
		return super.findList(lgServicehistory);
	}
	
	public Page<LgServicehistory> findPage(Page<LgServicehistory> page, LgServicehistory lgServicehistory) {
		return super.findPage(page, lgServicehistory);
	}
	
	@Transactional(readOnly = false)
	public void save(LgServicehistory lgServicehistory) {
		super.save(lgServicehistory);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgServicehistory lgServicehistory) {
		super.delete(lgServicehistory);
	}
	
}