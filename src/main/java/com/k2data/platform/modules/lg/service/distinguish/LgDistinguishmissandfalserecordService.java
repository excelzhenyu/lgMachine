/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.distinguish;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.LgFalseStatistics;
import com.k2data.platform.modules.lg.entity.LgGpsTemplateDetail;
import com.k2data.platform.modules.lg.entity.distinguish.LgDistinguishmissandfalserecord;
import com.k2data.platform.modules.lg.dao.distinguish.LgDistinguishmissandfalserecordDao;

/**
 * 机器虚报漏报记录Service
 * @author chenjingsi
 * @version 2016-06-29
 */
@Service
@Transactional(readOnly = true)
public class LgDistinguishmissandfalserecordService extends CrudService<LgDistinguishmissandfalserecordDao, LgDistinguishmissandfalserecord> {
	
	@Autowired
	LgDistinguishmissandfalserecordDao lgDistinguishmissandfalserecordDao;
	
	public LgDistinguishmissandfalserecord get(String id) {
		return super.get(id);
	}
	
	public List<LgDistinguishmissandfalserecord> findList(LgDistinguishmissandfalserecord lgDistinguishmissandfalserecord) {
		return super.findList(lgDistinguishmissandfalserecord);
	}
	
	public Page<LgDistinguishmissandfalserecord> findPage(Page<LgDistinguishmissandfalserecord> page, LgDistinguishmissandfalserecord lgDistinguishmissandfalserecord) {
		return super.findPage(page, lgDistinguishmissandfalserecord);
	}
	
	@Transactional(readOnly = false)
	public void save(LgDistinguishmissandfalserecord lgDistinguishmissandfalserecord) {
		super.save(lgDistinguishmissandfalserecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgDistinguishmissandfalserecord lgDistinguishmissandfalserecord) {
		super.delete(lgDistinguishmissandfalserecord);
	}

	public List<LgFalseStatistics> queryCounts(LgFalseStatistics lgFalseStatistics) {
		return lgDistinguishmissandfalserecordDao.queryCounts(lgFalseStatistics);
	}

	public List<LgDistinguishmissandfalserecord> getModalList(LgFalseStatistics lgFalseStatistics) {
		return lgDistinguishmissandfalserecordDao.getModalList(lgFalseStatistics);
	}

	public static String genGroupListDom(List<LgDistinguishmissandfalserecord> recordList) {
			StringBuilder domStr = new StringBuilder();
			
			for (LgDistinguishmissandfalserecord record : recordList) {
				domStr.append("<tr>")
					  .append("<input name='id' type='hidden' value='").append(record.getId()).append("'></input>")
					  .append("<td name='deviceNo'><a href='/LinGongMachineProfile/a/lg/lgMissAndFalseStatistics/detail?deviceNo=").append(record.getDeviceNo()).append("'>").append(record.getDeviceNo()).append("</a></td>")
					  .append("<td name='productType'>").append(record.getProductType()).append("</td>")
					  .append("<td name='saleName'>").append(record.getSaleName()).append("</td>")
					  .append("<td name='orderNo'>").append(record.getOrderNo()).append("</td>")
					  .append("<td name='confirmState'>").append(record.getConfirmState()).append("</td>")
					  .append("<td name='operator'>").append(record.getOperator()).append("</td>")
					  .append("<td name='confirmDate'>").append(record.getConfirmDate()).append("</td>")
					  .append("</tr>");
			}
			return domStr.toString();
		}
	

	public LgFalseStatistics queryWjjNumbers(LgFalseStatistics lgFalseStatistics) {
		return lgDistinguishmissandfalserecordDao.queryWjjNumbers(lgFalseStatistics);
	}

	public LgFalseStatistics queryZzjNumbers(LgFalseStatistics lgFalseStatistics) {
		return lgDistinguishmissandfalserecordDao.queryZzjNumbers(lgFalseStatistics);
	}

	public LgFalseStatistics queryTotalNumbers(LgFalseStatistics lgFalseStatistics) {
		return lgDistinguishmissandfalserecordDao.queryTotalNumbers(lgFalseStatistics);
	}

	public List<LgFalseStatistics> querySaleCount(LgFalseStatistics lgFalseStatistics) {
		return lgDistinguishmissandfalserecordDao.querySaleCount(lgFalseStatistics);
	}

	public static String genContentTableSale(List<LgFalseStatistics> wjjList, List<LgFalseStatistics> zzjList) {
		StringBuilder domStr = new StringBuilder();
		for(LgFalseStatistics lg : wjjList){
			domStr.append("<tr>")
			.append("<td >").append(lg.getMachineTypeName()).append("</td>")
			.append("<td name='saleName'><a href='#'>").append(lg.getSaleName()).append("</a></td>")
			.append("<td >").append(lg.getFalseNumbers()).append("</td>")
			.append("<td >").append(lg.getConfirmNumbers()).append("</td>")
			.append("<td >").append(lg.getErrorNumbers()).append("</td>")
			.append("<td >").append(lg.getUnconfirmNumbers()).append("</td>")
			.append("</tr>");
		}
		for(LgFalseStatistics lg:zzjList){
			domStr.append("<tr>")
			.append("<td >").append(lg.getMachineTypeName()).append("</td>")
			.append("<td name='saleName'><a href='#'>").append(lg.getSaleName()).append("</a></td>")
			.append("<td >").append(lg.getFalseNumbers()).append("</td>")
			.append("<td >").append(lg.getConfirmNumbers()).append("</td>")
			.append("<td >").append(lg.getErrorNumbers()).append("</td>")
			.append("<td >").append(lg.getUnconfirmNumbers()).append("</td>")
			.append("</tr>");
		}
		return domStr.toString();
	}

	public List<LgDistinguishmissandfalserecord> getSaleModal(LgFalseStatistics lgFalseStatistics) {
		return lgDistinguishmissandfalserecordDao.getSaleModal(lgFalseStatistics);
	}
	
	
}