/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.common.persistence.provider.SelectSqlProvider;
import com.k2data.platform.modules.workcenter.entity.WorkcenterGenJob;

/**
 * Quartz Job 生成方案DAO接口
 * @author lidong
 * @version 2016-06-12
 */
@MyBatisDao
public interface WorkcenterGenJobDao extends CrudDao<WorkcenterGenJob> {
	
	/**
	 * 拿到所有生成方案记录
	 * 
	 * @return
	 */
	public List<WorkcenterGenJob> getAllWorkCenterGenJob();
	
	/**
	 * 验证数据是否存在
	 * 
	 * @return
	 */
	@SelectProvider(type=SelectSqlProvider.class, method="checkExistWithCamelhumpToUnderline")
	public String checkExist(WorkcenterGenJob workcenterGenJob);
	
	/**
	 * 验证功能名是否存在
	 * 
	 * @return
	 */
	@SelectProvider(type=SelectSqlProvider.class, method="checkExistWithCamelhumpToUnderline")
	public String checkFunctionNameExist(WorkcenterGenJob workcenterGenJob);
	
}