/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.slice;

import java.util.List;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.machinestatus.LgWorkingStatusCond;
import com.k2data.platform.modules.lg.entity.slice.LgSliceTempDataSection;

/**
 * 设备每日工作统计数据获取DAO接口
 * @author wangshengguo
 * @version 2016-05-30
 */
@MyBatisDao
public interface LgSliceTempDataDao extends CrudDao<LgSliceTempDataSection> {
	
	/**
	 * 得到切片位置列表
	 * 
	 * @param cond 
	 * @return
	 */
	public List<LgSliceTempDataSection> getSliceLocationList(LgWorkingStatusCond cond);
	
}