/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.LgMachineinfo;

/**
 * 机器简述DAO接口
 * @author chenjingsi
 * @version 2016-05-13
 */
@MyBatisDao
public interface LgMachineinfoDao extends CrudDao<LgMachineinfo> {
	
}