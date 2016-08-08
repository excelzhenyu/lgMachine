/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.slice;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceDateStatics;

/**
 * 设备每日工作统计DAO接口
 * @author wangshengguo
 * @version 2016-07-26
 */
@MyBatisDao
public interface LgDeviceDateDao extends CrudDao<LgDeviceDateStatics> {

	public void saveDateData(LgDeviceDateStatics lgDeviceDateStatics);
	public void saveDateDataByPrevDate(LgDeviceDateStatics lgDeviceDateStatics);

}