/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.slice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceYearStatics;
import com.k2data.platform.modules.lg.dao.slice.LgDeviceYearStaticsDao;

/**
 * 设备每年工作统计Service
 * @author wangshengguo
 * @version 2016-06-13
 */
@Service
@Transactional(readOnly = true)
public class LgDeviceYearStaticsService extends CrudService<LgDeviceYearStaticsDao, LgDeviceYearStatics> {

	public LgDeviceYearStatics get(String id) {
		return super.get(id);
	}
	
	public List<LgDeviceYearStatics> findList(LgDeviceYearStatics lgDeviceYearStatics) {
		return super.findList(lgDeviceYearStatics);
	}
	
	public Page<LgDeviceYearStatics> findPage(Page<LgDeviceYearStatics> page, LgDeviceYearStatics lgDeviceYearStatics) {
		return super.findPage(page, lgDeviceYearStatics);
	}
	
	@Transactional(readOnly = false)
	public void save(LgDeviceYearStatics lgDeviceYearStatics) {
		super.save(lgDeviceYearStatics);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgDeviceYearStatics lgDeviceYearStatics) {
		super.delete(lgDeviceYearStatics);
	}
	
}