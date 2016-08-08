/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.dao;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.workcenter.entity.SliceLog;

/**
 * 切片处理日志DAO接口
 * @author lidong
 * @version 2016-05-25
 */
@MyBatisDao
public interface SliceLogDao extends CrudDao<SliceLog> {
	
}