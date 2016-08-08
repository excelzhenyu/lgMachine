/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.slice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceWeekStatics;
import com.k2data.platform.modules.lg.dao.slice.LgDeviceWeekStaticsDao;

/**
 * 设备每周工作统计Service
 * @author wangshengguo
 * @version 2016-06-13
 */
@Service
@Transactional(readOnly = true)
public class LgDeviceWeekStaticsService extends CrudService<LgDeviceWeekStaticsDao, LgDeviceWeekStatics> {

	public LgDeviceWeekStatics get(String id) {
		return super.get(id);
	}
	
	public List<LgDeviceWeekStatics> findList(LgDeviceWeekStatics lgDeviceWeekStatics) {
		return super.findList(lgDeviceWeekStatics);
	}
	
	public Page<LgDeviceWeekStatics> findPage(Page<LgDeviceWeekStatics> page, LgDeviceWeekStatics lgDeviceWeekStatics) {
		return super.findPage(page, lgDeviceWeekStatics);
	}
	
	@Transactional(readOnly = false)
	public void save(LgDeviceWeekStatics lgDeviceWeekStatics) {
		super.save(lgDeviceWeekStatics);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgDeviceWeekStatics lgDeviceWeekStatics) {
		super.delete(lgDeviceWeekStatics);
	}


}