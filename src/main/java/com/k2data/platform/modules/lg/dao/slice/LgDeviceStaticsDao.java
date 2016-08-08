/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.slice;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.LgDeviceSliceStatics;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceStatics;

/**
 * 设备工作统计DAO接口
 * @author wangshengguo
 * @version 2016-07-13
 */
@MyBatisDao
public interface LgDeviceStaticsDao extends CrudDao<LgDeviceStatics> {
	
	//获取上一天的切片统计信息
	public List<LgDeviceSliceStatics> getSliceStatics(@Param("recordTime") Date recordTime);
	
	//按周统计 从按天统计中取数据
	public int saveDeviceWeekStatics(LgDeviceStatics entity);
	//按月统计 从按天统计中取数据
	public int saveDeviceMonthStatics(LgDeviceStatics entity);
	//按季度统计 从按月统计中取数据
	public int saveDeviceSeasonStatics(LgDeviceStatics entity);
	//按年统计 从按季度统计中取数据
	public int saveDeviceYearStatics(LgDeviceStatics entity);

}