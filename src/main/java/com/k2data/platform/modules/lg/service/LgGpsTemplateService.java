/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.LgGpsTemplate;
import com.k2data.platform.modules.lg.dao.LgGpsTemplateDao;

/**
 * GPS传感器模板Service
 * @author chenjingsi
 * @version 2016-06-02
 */
@Service
@Transactional(readOnly = true)
public class LgGpsTemplateService extends CrudService<LgGpsTemplateDao, LgGpsTemplate> {
	
	@Autowired
	private LgGpsTemplateDao lgGpsTemplateDao;
	
	public LgGpsTemplate get(String id) {
		return super.get(id);
	}
	
	public List<LgGpsTemplate> findList(LgGpsTemplate lgGpsTemplate) {
		return super.findList(lgGpsTemplate);
	}
	
	public Page<LgGpsTemplate> findPage(Page<LgGpsTemplate> page, LgGpsTemplate lgGpsTemplate) {
		return super.findPage(page, lgGpsTemplate);
	}
	
	@Transactional(readOnly = false)
	public void save(LgGpsTemplate lgGpsTemplate) {
		super.save(lgGpsTemplate);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgGpsTemplate lgGpsTemplate) {
		super.delete(lgGpsTemplate);
	}

	public LgGpsTemplate checkGpsTemplateNo(LgGpsTemplate lgGpsTemplate) {
		return lgGpsTemplateDao.checkGpsTemplateNo(lgGpsTemplate);
	}

	public LgGpsTemplate checkGpsTemplateName(LgGpsTemplate lgGpsTemplate) {
		return lgGpsTemplateDao.checkGpsTemplateName(lgGpsTemplate);
	}
	
}