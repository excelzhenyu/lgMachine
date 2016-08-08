/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.workcenter.entity.QrtzJob;

/**
 * Quartz JobDAO接口
 * @author lidong
 * @version 2016-06-01
 */
@MyBatisDao
public interface QrtzJobDao {
	
	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<QrtzJob> findList(QrtzJob entity);
	
	/**
	 * 检查 JOB 是否正在触发
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 */
	public String isFired(@Param("jobName") String jobName, @Param("jobGroup") String jobGroup);
	
}