/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.LgInspectionhistory;

/**
 * 巡检整改DAO接口
 * @author chenjingsi
 * @version 2016-06-29
 */
@MyBatisDao
public interface LgInspectionhistoryDao extends CrudDao<LgInspectionhistory> {
	
}