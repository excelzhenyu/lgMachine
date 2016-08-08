/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.exptmachine;

import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;
import com.k2data.platform.common.utils.excel.annotation.ExcelField;

/**
 * 试验机器选取明细Entity
 * @author wangshengguo
 * @version 2016-06-21
 */
public class LgExptMachinePickDetail extends DataEntity<LgExptMachinePickDetail> {
	
	private static final long serialVersionUID = 1L;
	private String pickId;		// 试验机选取ID
	private String machineId;		// 整机ID
	private String machineIds;
	
	public LgExptMachinePickDetail() {
		super();
	}

	public LgExptMachinePickDetail(String id){
		super(id);
	}

	@Length(min=0, max=64, message="试验机选取ID长度必须介于 0 和 64 之间")
	public String getPickId() {
		return pickId;
	}

	public void setPickId(String pickId) {
		this.pickId = pickId;
	}
	
	@ExcelField(title="整机编码", align=2, sort=20)
	@Length(min=0, max=64, message="整机ID长度必须介于 0 和 64 之间")
	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getMachineIds() {
		return machineIds;
	}

	public void setMachineIds(String machineIds) {
		this.machineIds = machineIds;
	}
	
}