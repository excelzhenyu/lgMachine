/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.workcenter.entity.SliceLog;
import com.k2data.platform.modules.workcenter.dao.SliceLogDao;

/**
 * 切片处理日志Service
 * @author lidong
 * @version 2016-05-25
 */
@Service
@Transactional(readOnly = true)
public class SliceLogService extends CrudService<SliceLogDao, SliceLog> {

	public SliceLog get(String id) {
		return super.get(id);
	}
	
	public List<SliceLog> findList(SliceLog sliceLog) {
		return super.findList(sliceLog);
	}
	
	public Page<SliceLog> findPage(Page<SliceLog> page, SliceLog sliceLog) {
		return super.findPage(page, sliceLog);
	}
	
	@Transactional(readOnly = false)
	public void save(SliceLog sliceLog) {
		super.save(sliceLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(SliceLog sliceLog) {
		super.delete(sliceLog);
	}
	
}