/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.dao.LgLabMachineQueryDao;
import com.k2data.platform.modules.lg.entity.LgGpsTemplateDetail;
import com.k2data.platform.modules.lg.entity.LgLabMachineQuery;
import com.k2data.platform.modules.workcenter.entity.WorkcenterGroup;

/**
 * 试验机查询Service
 * @author chenjingsi
 * @version 2016-05-04
 */
@Service
@Transactional(readOnly = true)
public class LgLabMachineQueryService extends CrudService<LgLabMachineQueryDao, LgLabMachineQuery> {

	
	public LgLabMachineQuery get(String id) {
		return super.get(id);
	}
	
	public List<LgLabMachineQuery> findList(LgLabMachineQuery lgLabMachineQuery) {
		return super.findList(lgLabMachineQuery);
	}
	
	public Page findPage(Page<LgLabMachineQuery> page, LgLabMachineQuery lgLabMachineQuery) {
		return super.findPage(page, lgLabMachineQuery);
	}
	
	@Transactional(readOnly = false)
	public void save(LgLabMachineQuery lgLabMachineQuery) {
		super.save(lgLabMachineQuery);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgLabMachineQuery lgLabMachineQuery) {
		super.delete(lgLabMachineQuery);
	}

	/**
	 * 得到模态窗口选择任务组的 Dom
	 * 
	 * @param groups
	 * @return
	 */
	public static String genGroupListDom(List<LgGpsTemplateDetail> details) {
		StringBuilder domStr = new StringBuilder();
		
		for (LgGpsTemplateDetail detail : details) {
			domStr.append("<tr>")
				  .append("<input name='id' type='hidden' value='").append(detail.getId()).append("'></input>")
				  .append("<td name='sensorMark'><a>").append(detail.getSensorMark()).append("</a></td>")
				  .append("<td name='sensorName'>").append(detail.getSensorName()).append("</td>")
				  .append("</tr>");
		}
		
		return domStr.toString();
	}
	
}