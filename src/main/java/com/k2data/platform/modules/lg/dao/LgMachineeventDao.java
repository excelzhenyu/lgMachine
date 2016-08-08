/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao;

import java.util.List;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.LgMachineevent;

/**
 * 机器大事记DAO接口
 * @author chenjingsi
 * @version 2016-05-13
 */
@MyBatisDao
public interface LgMachineeventDao extends CrudDao<LgMachineevent> {

	List<String> getEventList(String deviceNo);
	
}