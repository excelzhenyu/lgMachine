/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.distribution;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineJobReportData;
import com.k2data.platform.modules.lg.dao.distribution.LgMachineJobReportDao;

/**
 * 机器工作报告Service
 * @author wangshengguo
 * @version 2016-07-14
 */
@Service
@Transactional(readOnly = true)
public class LgMachineJobReportService extends CrudService<LgMachineJobReportDao, LgMachineJobReportData> {

	public LgMachineJobReportData get(String id) {
		return super.get(id);
	}
	
	public List<LgMachineJobReportData> findList(LgMachineJobReportData entity) {
		return super.findList(entity);
	}
	
	public Page<LgMachineJobReportData> findPage(Page<LgMachineJobReportData> page, LgMachineJobReportData entity) {
		return super.findPage(page, entity);
	}
	
	@Transactional(readOnly = false)
	public void save(LgMachineJobReportData entity) {
		super.save(entity);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgMachineJobReportData entity) {
		super.delete(entity);
	}

}