/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.slice;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.slice.LgSliceStaticsStatus;
import com.k2data.platform.modules.lg.entity.slice.LgSliceStaticsStatusDetail;
import com.k2data.platform.modules.lg.entity.slice.LgSliceSwitchStatusDetail;
import com.k2data.platform.modules.lg.dao.slice.LgSliceStaticsStatusDao;

/**
 * 状态量切片统计Service
 * @author wangshengguo
 * @version 2016-05-23
 */
@Service
@Transactional(readOnly = true)
public class LgSliceStaticsStatusService extends CrudService<LgSliceStaticsStatusDao, LgSliceStaticsStatus> {

	@Autowired 
	private LgSliceStaticsStatusDao lgSliceStaticsStatusDao;
	
	public LgSliceStaticsStatus get(String id) {
		return super.get(id);
	}
	
	public List<LgSliceStaticsStatus> findList(LgSliceStaticsStatus lgSliceStaticsStatus) {
		return super.findList(lgSliceStaticsStatus);
	}
	
	public Page<LgSliceStaticsStatus> findPage(Page<LgSliceStaticsStatus> page, LgSliceStaticsStatus lgSliceStaticsStatus) {
		return super.findPage(page, lgSliceStaticsStatus);
	}
	
	@Transactional(readOnly = false)
	public void save(LgSliceStaticsStatus lgSliceStaticsStatus) {
		super.save(lgSliceStaticsStatus);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgSliceStaticsStatus lgSliceStaticsStatus) {
		super.delete(lgSliceStaticsStatus);
	}
	
	//获取设备下的传感器列表
	public List<String> getSensor(@Param("deviceNo") String deviceNo) {
		return lgSliceStaticsStatusDao.getSensor(deviceNo);
	}
	//批量保存状态量统计
	@Transactional(readOnly = false)
	public void batchSave(@Param("list") List<LgSliceStaticsStatus> list) {
		lgSliceStaticsStatusDao.batchSave(list);
	}
	//批量保存状态量明细统计
	@Transactional(readOnly = false)
	public void batchSaveState(@Param("list") List<LgSliceStaticsStatusDetail> list) {
		lgSliceStaticsStatusDao.batchSaveState(list);
	}
	//批量保存状态量切换统计
	@Transactional(readOnly = false)
	public void batchSaveSwitch(@Param("list") List<LgSliceSwitchStatusDetail> list) {
		lgSliceStaticsStatusDao.batchSaveSwitch(list);
	}
}