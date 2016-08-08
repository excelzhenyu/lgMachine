/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.LgMachineinfo;
import com.k2data.platform.modules.lg.dao.LgMachineinfoDao;

/**
 * 机器简述Service
 * @author chenjingsi
 * @version 2016-05-13
 */
@Service
@Transactional(readOnly = true)
public class LgMachineinfoService extends CrudService<LgMachineinfoDao, LgMachineinfo> {

	public LgMachineinfo get(String id) {
		return super.get(id);
	}
	
	public List<LgMachineinfo> findList(LgMachineinfo lgMachineinfo) {
		return super.findList(lgMachineinfo);
	}
	
	public Page<LgMachineinfo> findPage(Page<LgMachineinfo> page, LgMachineinfo lgMachineinfo) {
		return super.findPage(page, lgMachineinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(LgMachineinfo lgMachineinfo) {
		super.save(lgMachineinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgMachineinfo lgMachineinfo) {
		super.delete(lgMachineinfo);
	}
	
}