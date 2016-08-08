/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.LgSensorlist;

/**
 * 整机传感器清单DAO接口
 * @author chenjingsi
 * @version 2016-05-09
 */
@MyBatisDao
public interface LgSensorlistDao extends CrudDao<LgSensorlist> {

	void updateStopSensor(String id);

	LgSensorlist checkSensorCode(LgSensorlist lgSensorlist);

	LgSensorlist checkSensorPin(LgSensorlist lgSensorlist);
	//根据传感器类型获取设备下的传感器列表
	public List<String> getSensorListBySensorType(@Param("deviceNo") String deviceNo, @Param("sensorType") int sensorType);
}