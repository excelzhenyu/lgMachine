/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 保养记录Entity
 * @author chenjingsi
 * @version 2016-05-13
 */
public class LgUpkeephistory extends DataEntity<LgUpkeephistory> {
	
	private static final long serialVersionUID = 1L;
	private String machineId;		// 整机ID
	private Date upkeepTime;		// 保养时间
	private String orderNo;		// 保养单号
	private String orderHuman;		// 保养人
	private String upkeepInfo;		// 保养说明
	
	public LgUpkeephistory() {
		super();
	}

	public LgUpkeephistory(String id){
		super(id);
	}

	@Length(min=0, max=64, message="整机ID长度必须介于 0 和 64 之间")
	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpkeepTime() {
		return upkeepTime;
	}

	public void setUpkeepTime(Date upkeepTime) {
		this.upkeepTime = upkeepTime;
	}
	
	@Length(min=0, max=40, message="保养单号长度必须介于 0 和 40 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=200, message="保养人长度必须介于 0 和 200 之间")
	public String getOrderHuman() {
		return orderHuman;
	}

	public void setOrderHuman(String orderHuman) {
		this.orderHuman = orderHuman;
	}
	
	@Length(min=0, max=200, message="保养说明长度必须介于 0 和 200 之间")
	public String getUpkeepInfo() {
		return upkeepInfo;
	}

	public void setUpkeepInfo(String upkeepInfo) {
		this.upkeepInfo = upkeepInfo;
	}
	
}