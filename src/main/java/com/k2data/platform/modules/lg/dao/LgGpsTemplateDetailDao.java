/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao;

import java.util.List;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.LgGpsTemplateDetail;

/**
 * GPS传感器模板明细DAO接口
 * @author chenjingsi
 * @version 2016-06-02
 */
@MyBatisDao
public interface LgGpsTemplateDetailDao extends CrudDao<LgGpsTemplateDetail> {

	List<LgGpsTemplateDetail> getSensorList(String code);

	LgGpsTemplateDetail checkSensorMark(LgGpsTemplateDetail lgGpsTemplateDetail);

	void deleteByTemplateId(LgGpsTemplateDetail lgGpsTemplateDetail);

	List<LgGpsTemplateDetail> getTemplateDetailList(String deviceNo);
	
	/**
	 * 根据设备号查询传感器列表
	 * 
	 * @param deviceNo
	 * @return
	 */
	public List<LgGpsTemplateDetail> getSensorNameList(String deviceNo);

	List<LgGpsTemplateDetail> getSensorListAnalog(String device);

	List<LgGpsTemplateDetail> getSensorListSwitch(String device);
	
}