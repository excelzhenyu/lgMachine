/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.common.dao.LgUtilCrudDao;
import com.k2data.platform.modules.lg.entity.LgMachineType;

/**
 * 整机类别DAO接口
 * @author wangshengguo
 * @version 2016-05-09
 * 
 */
@MyBatisDao
public interface LgMachineTypeDao extends LgUtilCrudDao<LgMachineType> {

	List<LgMachineType> getModelNumberList();

	LgMachineType checkOrderNumber(LgMachineType lgMachineType);
	
	/**
	 * 获得主机型号列表
	 * 
	 * @return
	 */
	public List<String> getProductTypeList(@Param("machineType") String machineType);
	
	/**
	 * 获得订货号列表
	 * 
	 * @param productType 主机型号
	 * @return
	 */
	public List<LgMachineType> getOrderNumberList(@Param("productType") String productType);
	
}