/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.test.entity.PmK2dataprojectprogress;
import com.k2data.platform.modules.test.dao.PmK2dataprojectprogressDao;

/**
 * 项目进度Service
 * @author wangshengguo
 * @version 2016-05-09
 */
@Service
@Transactional(readOnly = true)
public class PmK2dataprojectprogressService extends CrudService<PmK2dataprojectprogressDao, PmK2dataprojectprogress> {

	public PmK2dataprojectprogress get(String id) {
		return super.get(id);
	}
	
	public List<PmK2dataprojectprogress> findList(PmK2dataprojectprogress pmK2dataprojectprogress) {
		return super.findList(pmK2dataprojectprogress);
	}
	
	public Page<PmK2dataprojectprogress> findPage(Page<PmK2dataprojectprogress> page, PmK2dataprojectprogress pmK2dataprojectprogress) {
		return super.findPage(page, pmK2dataprojectprogress);
	}
	
	@Transactional(readOnly = false)
	public void save(PmK2dataprojectprogress pmK2dataprojectprogress) {
		super.save(pmK2dataprojectprogress);
	}
	
	@Transactional(readOnly = false)
	public void delete(PmK2dataprojectprogress pmK2dataprojectprogress) {
		super.delete(pmK2dataprojectprogress);
	}
	
}