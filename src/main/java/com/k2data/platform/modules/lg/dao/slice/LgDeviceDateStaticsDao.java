/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.slice;

import java.util.Map;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.MissAndFalseCondition;
import com.k2data.platform.modules.lg.entity.distinguish.MissFalseDetail;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceDateStatics;

/**
 * 设备每日工作统计DAO接口
 * @author wangshengguo
 * @version 2016-05-30
 */
@MyBatisDao
public interface LgDeviceDateStaticsDao extends CrudDao<LgDeviceDateStatics> {

	Integer getHours(Map<String, Object> params);
	
	//查找日期和设备号 查询日统计列表
	public List<LgDeviceDateStatics> findMachineDateStatics(LgDeviceDateStatics lgDeviceDateStatics);
	//查找销售机(非试验机)列表
	public List<LgDeviceDateStatics> findSaleMachineDateStatics(LgDeviceDateStatics lgDeviceDateStatics);
	//查找试验机列表
	public List<LgDeviceDateStatics> findExptMachineDateStatics(@Param("dateBegin") Date dateBegin,
			@Param("dateEnd") Date dateEnd, @Param("batchNumber") String batchNumber);
	//查找试验机列表
	public List<LgDeviceDateStatics> findExptMachineDateStatics(LgDeviceDateStatics lgDeviceDateStatics);
	//获取试验机开机时长分布 按试验机批次
	public List<LgDeviceDateStatics> findExptMachineRunDuration(LgDeviceDateStatics lgDeviceDateStatics);
	//获取试验机开机时长分布 按设备
	public List<LgDeviceDateStatics> findExptMachineRunDuration2(LgDeviceDateStatics lgDeviceDateStatics);

	
	List<LgDeviceDateStatics> getResultList(MissAndFalseCondition mfc1);
	
	MissFalseDetail getMissAndFalseDetail(String deviceNo);
	
	List<String> getOilSumChartDate(String deviceNo);
	
	List<Integer> getOilSumChartData(String deviceNo);

	/**
	 * 获得呆滞机页面需要的数据
	 * 
	 * @param lgDeviceDateStatics
	 * @return
	 */
	public List<LgDeviceDateStatics> getInactiveDataList(LgDeviceDateStatics lgDeviceDateStatics);
	
	/**
	 * 获得每个设备最大时间的数据
	 * 
	 * @param lgDeviceDateStatics
	 * @return
	 */
	public List<LgDeviceDateStatics> getMaxDateData(LgDeviceDateStatics lgDeviceDateStatics);

}