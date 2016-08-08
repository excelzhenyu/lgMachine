/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.LgMachineGpsContrast;

/**
 * 整机与GPS设备对照DAO接口
 * @author chenjingsi
 * @version 2016-06-03
 */
@MyBatisDao
public interface LgMachineGpsContrastDao extends CrudDao<LgMachineGpsContrast> {

	LgMachineGpsContrast checkDeviceNo(LgMachineGpsContrast lgMachineGpsContrast);

	LgMachineGpsContrast checkGpsNo(LgMachineGpsContrast lgMachineGpsContrast);
	
}