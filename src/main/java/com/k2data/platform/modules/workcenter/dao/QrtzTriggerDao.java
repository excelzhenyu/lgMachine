/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.dao;

import java.util.List;

import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.workcenter.entity.QrtzTrigger;

/**
 * Quartz 触发器DAO接口
 * @author lidong
 * @version 2016-05-31
 */
@MyBatisDao
public interface QrtzTriggerDao {
	
	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<QrtzTrigger> findList(QrtzTrigger entity);
	
}