/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.workcenter.entity.Workcenter;
import com.k2data.platform.modules.workcenter.dao.WorkcenterDao;

/**
 * 工作中心Service
 * @author lidong
 * @version 2016-05-20
 */
@Service
@Transactional(readOnly = true)
public class WorkcenterService extends CrudService<WorkcenterDao, Workcenter> {
	
	@Autowired
	private WorkcenterDao workcenterDao;

	public Workcenter get(String id) {
		return super.get(id);
	}
	
	public List<Workcenter> findList(Workcenter workcenter) {
		return super.findList(workcenter);
	}
	
	public Page<Workcenter> findPage(Page<Workcenter> page, Workcenter workcenter) {
		return super.findPage(page, workcenter);
	}
	
	@Transactional(readOnly = false)
	public void save(Workcenter workcenter) {
		super.save(workcenter);
	}
	
	@Transactional(readOnly = false)
	public void delete(Workcenter workcenter) {
		super.delete(workcenter);
	}
	
	/**
	 * 根据 job 的 name 和 group 来更新状态
	 * 
	 * @param workcenter
	 */
	@Transactional
	public void updateStatusByNameNGroup(Workcenter workcenter) {
		workcenterDao.updateStatusByNameNGroup(workcenter);
	}
	
	/**
	 * 根据 job 的 name 和 group 来删除数据
	 * 
	 * @param workcenter
	 */
	@Transactional
	public void deleteByNameNGroup(Workcenter workcenter) {
		workcenterDao.deleteByNameNGroup(workcenter);
	}
	
	/**
	 * 根据名字和组更新坐标
	 * 
	 * @param workcenter
	 */
	@Transactional
	public void updateAxisByNameNGroup(Workcenter workcenter) {
		workcenterDao.updateAxisByNameNGroup(workcenter);
	}
	
}