/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.slice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceMonthStatics;
import com.k2data.platform.modules.lg.dao.slice.LgDeviceMonthStaticsDao;

/**
 * 设备每月工作统计Service
 * @author wangshengguo
 * @version 2016-06-13
 */
@Service
@Transactional(readOnly = true)
public class LgDeviceMonthStaticsService extends CrudService<LgDeviceMonthStaticsDao, LgDeviceMonthStatics> {

	public LgDeviceMonthStatics get(String id) {
		return super.get(id);
	}
	
	public List<LgDeviceMonthStatics> findList(LgDeviceMonthStatics lgDeviceMonthStatics) {
		return super.findList(lgDeviceMonthStatics);
	}
	
	public Page<LgDeviceMonthStatics> findPage(Page<LgDeviceMonthStatics> page, LgDeviceMonthStatics lgDeviceMonthStatics) {
		return super.findPage(page, lgDeviceMonthStatics);
	}
	
	@Transactional(readOnly = false)
	public void save(LgDeviceMonthStatics lgDeviceMonthStatics) {
		super.save(lgDeviceMonthStatics);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgDeviceMonthStatics lgDeviceMonthStatics) {
		super.delete(lgDeviceMonthStatics);
	}
}