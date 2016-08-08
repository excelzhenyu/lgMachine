/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.exptmachine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.exptmachine.LgExptMachinePick;
import com.k2data.platform.modules.lg.dao.exptmachine.LgExptMachinePickDao;

/**
 * 试验机器选取Service
 * @author wangshengguo
 * @version 2016-06-21
 */
@Service
@Transactional(readOnly = true)
public class LgExptMachinePickService extends CrudService<LgExptMachinePickDao, LgExptMachinePick> {

	@Autowired
	public LgExptMachinePickDao lgExptMachinePickDao;
	
	public LgExptMachinePick get(String id) {
		return super.get(id);
	}
	
	public List<LgExptMachinePick> findList(LgExptMachinePick lgExptMachinePick) {
		return super.findList(lgExptMachinePick);
	}
	
	public Page<LgExptMachinePick> findPage(Page<LgExptMachinePick> page, LgExptMachinePick lgExptMachinePick) {
		return super.findPage(page, lgExptMachinePick);
	}
	
	@Transactional(readOnly = false)
	public void save(LgExptMachinePick lgExptMachinePick) {
		super.save(lgExptMachinePick);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgExptMachinePick lgExptMachinePick) {
		super.delete(lgExptMachinePick);
	}
	
	public Boolean checkBatchNumber(LgExptMachinePick lgExptMachinePick) {
		return lgExptMachinePickDao.checkBatchNumber(lgExptMachinePick) == null;
	}
}