/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.LgDeviceSliceStatics;
import com.k2data.platform.modules.lg.entity.SliceDataVO;

/**
 * 设备切片统计DAO接口
 * @author chenjingsi
 * @version 2016-05-20
 */
@MyBatisDao
public interface LgDeviceSliceStaticsDao extends CrudDao<LgDeviceSliceStatics> {

	Date getSliceStart(Map<String, Object> params);

	Date getSliceEnd(Map<String, Object> params);

	int getCount(String deviceNum);

	SliceDataVO getLatestSliceData(Map<String, Object> params);

	List<Double> getEngineTemperature(Map<String, Object> params);
	//批量保存
	public void batchSave(@Param("list")List<LgDeviceSliceStatics> list);
	//更新结束日期
	public int updatePrevDay(LgDeviceSliceStatics lgDeviceSliceStatics);

	List<LgDeviceSliceStatics> getSliceInfo(Map<String, Object> params);
	
}