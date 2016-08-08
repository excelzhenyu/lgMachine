/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.LgReplacehistory;
import com.k2data.platform.modules.lg.dao.LgReplacehistoryDao;

/**
 * 部件变更记录Service
 * @author chenjingsi
 * @version 2016-05-13
 */
@Service
@Transactional(readOnly = true)
public class LgReplacehistoryService extends CrudService<LgReplacehistoryDao, LgReplacehistory> {

	public LgReplacehistory get(String id) {
		return super.get(id);
	}
	
	public List<LgReplacehistory> findList(LgReplacehistory lgReplacehistory) {
		return super.findList(lgReplacehistory);
	}
	
	public Page<LgReplacehistory> findPage(Page<LgReplacehistory> page, LgReplacehistory lgReplacehistory) {
		return super.findPage(page, lgReplacehistory);
	}
	
	@Transactional(readOnly = false)
	public void save(LgReplacehistory lgReplacehistory) {
		super.save(lgReplacehistory);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgReplacehistory lgReplacehistory) {
		super.delete(lgReplacehistory);
	}
	
}