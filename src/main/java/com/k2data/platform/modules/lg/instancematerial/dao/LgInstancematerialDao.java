/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.instancematerial.dao;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.instancematerial.entity.LgInstancematerial;

/**
 * 实例物料DAO接口
 * @author lidong
 * @version 2016-05-16
 */
@MyBatisDao
public interface LgInstancematerialDao extends CrudDao<LgInstancematerial> {
	
	public String checkMaterialPin(LgInstancematerial lgInstancematerial);
	
}