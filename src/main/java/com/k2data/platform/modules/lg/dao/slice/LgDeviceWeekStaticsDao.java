/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.slice;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceWeekStatics;

/**
 * 设备每周工作统计DAO接口
 * @author wangshengguo
 * @version 2016-06-13
 */
@MyBatisDao
public interface LgDeviceWeekStaticsDao extends CrudDao<LgDeviceWeekStatics> {
	
}