/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.slice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.slice.LgSliceSwitchStatusDetail;
import com.k2data.platform.modules.lg.dao.slice.LgSliceSwitchStatusDetailDao;

/**
 * 状态量切片内切换明细Service
 * @author wangshengguo
 * @version 2016-05-23
 */
@Service
@Transactional(readOnly = true)
public class LgSliceSwitchStatusDetailService extends CrudService<LgSliceSwitchStatusDetailDao, LgSliceSwitchStatusDetail> {

	public LgSliceSwitchStatusDetail get(String id) {
		return super.get(id);
	}
	
	public List<LgSliceSwitchStatusDetail> findList(LgSliceSwitchStatusDetail lgSliceSwitchStatusDetail) {
		return super.findList(lgSliceSwitchStatusDetail);
	}
	
	public Page<LgSliceSwitchStatusDetail> findPage(Page<LgSliceSwitchStatusDetail> page, LgSliceSwitchStatusDetail lgSliceSwitchStatusDetail) {
		return super.findPage(page, lgSliceSwitchStatusDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(LgSliceSwitchStatusDetail lgSliceSwitchStatusDetail) {
		super.save(lgSliceSwitchStatusDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgSliceSwitchStatusDetail lgSliceSwitchStatusDetail) {
		super.delete(lgSliceSwitchStatusDetail);
	}
	
}