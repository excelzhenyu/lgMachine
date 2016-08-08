/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.slice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.slice.LgSliceTempDataSection;
import com.k2data.platform.modules.lg.dao.slice.LgSliceTempDataDao;

/**
 * 设备每日工作统计Service
 * @author wangshengguo
 * @version 2016-05-30
 */
@Service
@Transactional(readOnly = true)
public class LgSliceTempDataService extends CrudService<LgSliceTempDataDao, LgSliceTempDataSection> {

	public List<LgSliceTempDataSection> findList(LgSliceTempDataSection lgSliceData) {
		return super.findList(lgSliceData);
	}
	
}