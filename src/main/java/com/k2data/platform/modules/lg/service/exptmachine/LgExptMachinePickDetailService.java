/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.exptmachine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.LgLabMachineProfileQuery;
import com.k2data.platform.modules.lg.entity.LgMachineprofile;
import com.k2data.platform.modules.lg.entity.exptmachine.LgExptMachinePickDetail;
import com.k2data.platform.modules.lg.dao.exptmachine.LgExptMachinePickDetailDao;

/**
 * 试验机器选取明细Service
 * @author wangshengguo
 * @version 2016-06-21
 */
@Service
@Transactional(readOnly = true)
public class LgExptMachinePickDetailService extends CrudService<LgExptMachinePickDetailDao, LgExptMachinePickDetail> {
	
	@Autowired
	private LgExptMachinePickDetailDao lgExptMachinePickDetailDao;
	public LgExptMachinePickDetail get(String id) {
		return super.get(id);
	}
	
	public List<LgExptMachinePickDetail> findList(LgExptMachinePickDetail lgExptMachinePickDetail) {
		return super.findList(lgExptMachinePickDetail);
	}
	
	public Page<LgExptMachinePickDetail> findPage(Page<LgExptMachinePickDetail> page, LgExptMachinePickDetail lgExptMachinePickDetail) {
		return super.findPage(page, lgExptMachinePickDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(LgExptMachinePickDetail lgExptMachinePickDetail) {
		super.save(lgExptMachinePickDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgExptMachinePickDetail lgExptMachinePickDetail) {
		super.delete(lgExptMachinePickDetail);
	}
	
	@Transactional(readOnly = false)
	public void deleteMachine(LgExptMachinePickDetail lgExptMachinePickDetail) {
		lgExptMachinePickDetailDao.deleteMachine(lgExptMachinePickDetail);
	}
	public List<LgMachineprofile> findMachineList(LgMachineprofile lgMachineprofile) {
		return lgExptMachinePickDetailDao.findMachineList(lgMachineprofile);
	}

	//获取在批次下已经配置的列表
	public Page<LgMachineprofile> findMachinePage(Page<LgMachineprofile> page, LgMachineprofile lgMachineprofile) {
		lgMachineprofile.setPage(page);
		page.setList(lgExptMachinePickDetailDao.findMachineList(lgMachineprofile));
		return page;
	}
	//获取不在批次下已经配置的列表
	public Page<LgLabMachineProfileQuery> findMachinePageAdd(Page<LgLabMachineProfileQuery> page, LgLabMachineProfileQuery lgLabMachineProfileQuery) {
		lgLabMachineProfileQuery.setPage(page);
		page.setList(lgExptMachinePickDetailDao.findMachineListByQuery(lgLabMachineProfileQuery));
		return page;
	}
	@Transactional(readOnly = false)
	public int saveBatch(List<LgExptMachinePickDetail> list) {
		return lgExptMachinePickDetailDao.saveBatch(list);
	}
		
	@Transactional(readOnly = false)
	public int saveImportBatch(List<LgExptMachinePickDetail> list) {
		return lgExptMachinePickDetailDao.saveImportBatch(list);
	}
	//获取已经存在的本次批次下的整机编码
	public List<String> findMachineCodeList(LgExptMachinePickDetail lgExptMachinePickDetail) {
		return lgExptMachinePickDetailDao.findMachineCodeList(lgExptMachinePickDetail);
	}
	
	public List<LgMachineprofile> findExistMachineCodeList(LgMachineprofile lgMachineprofile) {
		return lgExptMachinePickDetailDao.findExistMachineCodeList(lgMachineprofile);
	}
	
	public List<String> getMachineCodeByPickId(List<String> pickId) {
		return  lgExptMachinePickDetailDao.getMachineCodeByPickId(pickId);
	}
	public List<String> getMachineCodeByBatchNumber(List<String> batchNumber) {
		return  lgExptMachinePickDetailDao.getMachineCodeByBatchNumber(batchNumber);
	}
	
}