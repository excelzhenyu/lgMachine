/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.workcenter.entity.WorkcenterGroup;
import com.k2data.platform.modules.workcenter.dao.WorkcenterGroupDao;

/**
 * 工作中心作业组Service
 * @author lidong
 * @version 2016-06-16
 */
@Service
@Transactional(readOnly = true)
public class WorkcenterGroupService extends CrudService<WorkcenterGroupDao, WorkcenterGroup> {

	public WorkcenterGroup get(String id) {
		return super.get(id);
	}
	
	public List<WorkcenterGroup> findList(WorkcenterGroup workcenterGroup) {
		return super.findList(workcenterGroup);
	}
	
	public Page<WorkcenterGroup> findPage(Page<WorkcenterGroup> page, WorkcenterGroup workcenterGroup) {
		return super.findPage(page, workcenterGroup);
	}
	
	@Transactional(readOnly = false)
	public void save(WorkcenterGroup workcenterGroup) {
		super.save(workcenterGroup);
	}
	
	@Transactional(readOnly = false)
	public void delete(WorkcenterGroup workcenterGroup) {
		super.delete(workcenterGroup);
	}
	
}