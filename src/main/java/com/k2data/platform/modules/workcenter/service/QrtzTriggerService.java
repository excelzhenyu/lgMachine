/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.modules.workcenter.entity.QrtzTrigger;
import com.k2data.platform.modules.workcenter.dao.QrtzTriggerDao;

/**
 * Quartz 触发器Service
 * @author lidong
 * @version 2016-05-31
 */
@Service
@Transactional(readOnly = true)
public class QrtzTriggerService {
	
	@Autowired
	private QrtzTriggerDao qrtzTriggerDao;

	public List<QrtzTrigger> findList(QrtzTrigger qrtzTrigger) {
		return qrtzTriggerDao.findList(qrtzTrigger);
	}
	
	public Page<QrtzTrigger> findPage(Page<QrtzTrigger> page, QrtzTrigger qrtzTrigger) {
		qrtzTrigger.setPage(page);
		page.setList(qrtzTriggerDao.findList(qrtzTrigger));
		return page;
	}
	
}