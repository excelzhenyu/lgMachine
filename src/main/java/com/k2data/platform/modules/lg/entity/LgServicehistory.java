/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 维修记录Entity
 * @author chenjingsi
 * @version 2016-05-13
 */
public class LgServicehistory extends DataEntity<LgServicehistory> {
	
	private static final long serialVersionUID = 1L;
	private String machineId;		// 整机ID
	private Date serviceTime;		// 维修时间
	private String orderNo;		// 维修单号
	private String serviceHuman;		// 维修人员
	private String serviceInfo;		// 维修说明
	
	public LgServicehistory() {
		super();
	}

	public LgServicehistory(String id){
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
	public Date getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Date serviceTime) {
		this.serviceTime = serviceTime;
	}
	
	@Length(min=0, max=40, message="维修单号长度必须介于 0 和 40 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=40, message="维修人员长度必须介于 0 和 40 之间")
	public String getServiceHuman() {
		return serviceHuman;
	}

	public void setServiceHuman(String serviceHuman) {
		this.serviceHuman = serviceHuman;
	}
	
	@Length(min=0, max=200, message="维修说明长度必须介于 0 和 200 之间")
	public String getServiceInfo() {
		return serviceInfo;
	}

	public void setServiceInfo(String serviceInfo) {
		this.serviceInfo = serviceInfo;
	}
	
}