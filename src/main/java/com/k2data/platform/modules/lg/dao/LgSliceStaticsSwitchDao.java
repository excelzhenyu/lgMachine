/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao;

import java.util.List;
import java.util.Map;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.LgSliceStaticsSwitch;

/**
 * 开关量切片统计DAO接口
 * @author chenjingsi
 * @version 2016-05-20
 */
@MyBatisDao
public interface LgSliceStaticsSwitchDao extends CrudDao<LgSliceStaticsSwitch> {

	List<Double> getChangeCountList(Map<String, Object> params);

	List<Double> getHighCountList(Map<String, Object> params);

	List<Double> getLowCountList(Map<String, Object> params);

	List<Double> getDataPointList(Map<String, Object> params);

	List<Double> getSentFrequenceList(Map<String, Object> params);
	
}