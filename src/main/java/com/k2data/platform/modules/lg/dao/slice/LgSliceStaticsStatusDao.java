/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.slice;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.slice.LgSliceStaticsStatus;
import com.k2data.platform.modules.lg.entity.slice.LgSliceStaticsStatusDetail;
import com.k2data.platform.modules.lg.entity.slice.LgSliceSwitchStatusDetail;

/**
 * 状态量切片统计DAO接口
 * @author wangshengguo
 * @version 2016-05-23
 */
@MyBatisDao
public interface LgSliceStaticsStatusDao extends CrudDao<LgSliceStaticsStatus> {
	
	//获取设备下的传感器列表
	public List<String> getSensor(@Param("deviceNo") String deviceNo);
	//批量保存状态量统计
	public void batchSave(@Param("list") List<LgSliceStaticsStatus> list);
	//批量保存状态量明细统计
	public void batchSaveState(@Param("list") List<LgSliceStaticsStatusDetail> list);
	//批量保存状态量切换统计
	public void batchSaveSwitch(@Param("list") List<LgSliceSwitchStatusDetail> list);
}