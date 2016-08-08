/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.slice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceSeasonStatics;
import com.k2data.platform.modules.lg.dao.slice.LgDeviceSeasonStaticsDao;

/**
 * 设备每季工作统计Service
 * @author 设备每季工作统计
 * @version 2016-06-13
 */
@Service
@Transactional(readOnly = true)
public class LgDeviceSeasonStaticsService extends CrudService<LgDeviceSeasonStaticsDao, LgDeviceSeasonStatics> {

	public LgDeviceSeasonStatics get(String id) {
		return super.get(id);
	}
	
	public List<LgDeviceSeasonStatics> findList(LgDeviceSeasonStatics lgDeviceSeasonStatics) {
		return super.findList(lgDeviceSeasonStatics);
	}
	
	public Page<LgDeviceSeasonStatics> findPage(Page<LgDeviceSeasonStatics> page, LgDeviceSeasonStatics lgDeviceSeasonStatics) {
		return super.findPage(page, lgDeviceSeasonStatics);
	}
	
	@Transactional(readOnly = false)
	public void save(LgDeviceSeasonStatics lgDeviceSeasonStatics) {
		super.save(lgDeviceSeasonStatics);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgDeviceSeasonStatics lgDeviceSeasonStatics) {
		super.delete(lgDeviceSeasonStatics);
	}
	
	
}