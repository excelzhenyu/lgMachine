/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.modules.workcenter.entity.QrtzJob;
import com.k2data.platform.modules.workcenter.dao.QrtzJobDao;

/**
 * Quartz JobService
 * @author lidong
 * @version 2016-06-01
 */
@Service
@Transactional(readOnly = true)
public class QrtzJobService {

	@Autowired
	private QrtzJobDao qrtzJobDao;

	public List<QrtzJob> findList(QrtzJob qrtzJob) {
		return qrtzJobDao.findList(qrtzJob);
	}
	
	public Page<QrtzJob> findPage(Page<QrtzJob> page, QrtzJob qrtzJob) {
		qrtzJob.setPage(page);
		page.setList(qrtzJobDao.findList(qrtzJob));
		return page;
	}
	
}