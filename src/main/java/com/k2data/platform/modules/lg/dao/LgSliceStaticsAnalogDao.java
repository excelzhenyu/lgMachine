/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao;

import java.util.List;
import java.util.Map;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.LgSliceStaticsAnalog;

/**
 * 模拟量切片统计DAO接口
 * @author chenjingsi
 * @version 2016-05-20
 */
@MyBatisDao
public interface LgSliceStaticsAnalogDao extends CrudDao<LgSliceStaticsAnalog> {

	List<Double> getAverageList(Map<String, Object> params);

	List<Double> getMaxList(Map<String, Object> params);

	List<Double> getMinList(Map<String, Object> params);

	List<Double> getDataCountList(Map<String, Object> params);

	List<Double> getSentFrequenceList(Map<String, Object> params);

	List<Double> getGradList(Map<String, Object> params);

	List<Double> getStandardList(Map<String, Object> params);

	List<Double> getVarianceList(Map<String, Object> params);
	
}