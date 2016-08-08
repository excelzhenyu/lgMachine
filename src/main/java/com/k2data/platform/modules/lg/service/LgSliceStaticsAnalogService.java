/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.LgSliceStaticsAnalog;
import com.k2data.platform.modules.lg.dao.LgSliceStaticsAnalogDao;

/**
 * 模拟量切片统计Service
 * @author chenjingsi
 * @version 2016-05-20
 */
@Service
@Transactional(readOnly = true)
public class LgSliceStaticsAnalogService extends CrudService<LgSliceStaticsAnalogDao, LgSliceStaticsAnalog> {

	public LgSliceStaticsAnalog get(String id) {
		return super.get(id);
	}
	
	public List<LgSliceStaticsAnalog> findList(LgSliceStaticsAnalog lgSliceStaticsAnalog) {
		return super.findList(lgSliceStaticsAnalog);
	}
	
	public Page<LgSliceStaticsAnalog> findPage(Page<LgSliceStaticsAnalog> page, LgSliceStaticsAnalog lgSliceStaticsAnalog) {
		return super.findPage(page, lgSliceStaticsAnalog);
	}
	
	@Transactional(readOnly = false)
	public void save(LgSliceStaticsAnalog lgSliceStaticsAnalog) {
		super.save(lgSliceStaticsAnalog);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgSliceStaticsAnalog lgSliceStaticsAnalog) {
		super.delete(lgSliceStaticsAnalog);
	}
	
}