/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.dao;

import org.apache.ibatis.annotations.SelectProvider;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.common.persistence.provider.SelectSqlProvider;
import com.k2data.platform.modules.workcenter.entity.WorkcenterGroup;

/**
 * 工作中心作业组DAO接口
 * @author lidong
 * @version 2016-06-16
 */
@MyBatisDao
public interface WorkcenterGroupDao extends CrudDao<WorkcenterGroup> {
	
	/**
	 * 是否存在指定记录
	 * 
	 * @param workcenter
	 * @return
	 */
	@SelectProvider(type=SelectSqlProvider.class, method="checkExist")
	public String checkExist(WorkcenterGroup workcenterGroup);
	
}