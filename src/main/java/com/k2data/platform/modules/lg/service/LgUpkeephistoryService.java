/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.LgUpkeephistory;
import com.k2data.platform.modules.lg.dao.LgUpkeephistoryDao;

/**
 * 保养记录Service
 * @author chenjingsi
 * @version 2016-05-13
 */
@Service
@Transactional(readOnly = true)
public class LgUpkeephistoryService extends CrudService<LgUpkeephistoryDao, LgUpkeephistory> {

	public LgUpkeephistory get(String id) {
		return super.get(id);
	}
	
	public List<LgUpkeephistory> findList(LgUpkeephistory lgUpkeephistory) {
		return super.findList(lgUpkeephistory);
	}
	
	public Page<LgUpkeephistory> findPage(Page<LgUpkeephistory> page, LgUpkeephistory lgUpkeephistory) {
		return super.findPage(page, lgUpkeephistory);
	}
	
	@Transactional(readOnly = false)
	public void save(LgUpkeephistory lgUpkeephistory) {
		super.save(lgUpkeephistory);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgUpkeephistory lgUpkeephistory) {
		super.delete(lgUpkeephistory);
	}
	
}