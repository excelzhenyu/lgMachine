/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao;

import java.util.List;

import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.common.dao.LgUtilCrudDao;
import com.k2data.platform.modules.lg.entity.LgMachineDimension;

/**
 * 整机维度DAO接口
 * @author wangshengguo
 * @version 2016-05-09
 * 
 */
@MyBatisDao
public interface LgMachineDimensionDao extends LgUtilCrudDao<LgMachineDimension> {

	List<LgMachineDimension> getMachineDimension(Integer type);
	
}