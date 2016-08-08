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
import com.k2data.platform.modules.lg.entity.LgMachineprofile;
import com.k2data.platform.modules.lg.entity.LgMachineprofileVO;
import com.k2data.platform.modules.lg.entity.LgWorktimeDistribution;
import com.k2data.platform.modules.lg.dao.LgMachineprofileDao;

/**
 * 整机档案Service
 * @author chenjingsi
 * @version 2016-05-04
 */
@Service
@Transactional(readOnly = true)
public class LgMachineprofileService extends CrudService<LgMachineprofileDao, LgMachineprofile> {

	@Autowired
	private LgMachineprofileDao lgMachineprofileDao;
	
	public LgMachineprofile get(String id) {
		return super.get(id);
	}
	
	public List<LgMachineprofile> findList(LgMachineprofile lgMachineprofile) {
		return super.findList(lgMachineprofile);
	}
	
	public Page findPage(Page<LgMachineprofile> page, LgMachineprofile lgMachineprofile) {
		return super.findPage(page, lgMachineprofile);
	}
	
	@Transactional(readOnly = false)
	public void save(LgMachineprofile lgMachineprofile) {
		super.save(lgMachineprofile);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgMachineprofile lgMachineprofile) {
		super.delete(lgMachineprofile);
	}

	public LgMachineprofile checkCode(LgMachineprofile lgMachineprofile) {
		return lgMachineprofileDao.checkCode(lgMachineprofile);
	}

	public LgMachineprofile checkPinCode(LgMachineprofile lgMachineprofile) {
		return lgMachineprofileDao.checkPinCode(lgMachineprofile);
	}

	public List<String> getMachineNoList(LgWorktimeDistribution lgWorktimeDistribution) {
		return lgMachineprofileDao.getMachineNoList(lgWorktimeDistribution);
	}

	public LgMachineprofileVO getProfileVO(LgMachineprofile lgMachineprofile) {
		return lgMachineprofileDao.getProfileVO(lgMachineprofile);
	}

	public String getId(String deviceNo) {
		return lgMachineprofileDao.getId(deviceNo);
	}
	
}