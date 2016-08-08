/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 机器简述Entity
 * @author chenjingsi
 * @version 2016-05-13
 */
public class LgMachineinfo extends DataEntity<LgMachineinfo> {
	
	private static final long serialVersionUID = 1L;
	private String machineId;		// 整机ID
	private String machineInfo;		// 机器简述
	
	public LgMachineinfo() {
		super();
	}

	public LgMachineinfo(String id){
		super(id);
	}

	@Length(min=0, max=64, message="整机ID长度必须介于 0 和 64 之间")
	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	
	@Length(min=0, max=1000, message="机器简述长度必须介于 0 和 1000 之间")
	public String getMachineInfo() {
		return machineInfo;
	}

	public void setMachineInfo(String machineInfo) {
		this.machineInfo = machineInfo;
	}
	
}