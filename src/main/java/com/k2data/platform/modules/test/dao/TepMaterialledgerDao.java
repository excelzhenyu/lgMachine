/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.test.dao;

import java.util.List;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.test.entity.TepMaterialledger;

/**
 * 物料台账（中性物料）DAO接口
 * @author wangshengguo
 * @version 2016-05-05
 */
@MyBatisDao
public interface TepMaterialledgerDao extends CrudDao<TepMaterialledger> {
	
	public List<TepMaterialledger> getAllTepMaterialLedger();
	
}