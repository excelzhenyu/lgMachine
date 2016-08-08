/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.slice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.slice.LgSliceStaticsStatusDetail;
import com.k2data.platform.modules.lg.dao.slice.LgSliceStaticsStatusDetailDao;

/**
 * 状态量切片统计明细Service
 * @author wangshengguo
 * @version 2016-07-05
 */
@Service
@Transactional(readOnly = true)
public class LgSliceStaticsStatusDetailService extends CrudService<LgSliceStaticsStatusDetailDao, LgSliceStaticsStatusDetail> {

	public LgSliceStaticsStatusDetail get(String id) {
		return super.get(id);
	}
	
	public List<LgSliceStaticsStatusDetail> findList(LgSliceStaticsStatusDetail lgSliceStaticsStatusDetail) {
		return super.findList(lgSliceStaticsStatusDetail);
	}
	
	public Page<LgSliceStaticsStatusDetail> findPage(Page<LgSliceStaticsStatusDetail> page, LgSliceStaticsStatusDetail lgSliceStaticsStatusDetail) {
		return super.findPage(page, lgSliceStaticsStatusDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(LgSliceStaticsStatusDetail lgSliceStaticsStatusDetail) {
		super.save(lgSliceStaticsStatusDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgSliceStaticsStatusDetail lgSliceStaticsStatusDetail) {
		super.delete(lgSliceStaticsStatusDetail);
	}
	
}