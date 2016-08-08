/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 报修记录Entity
 * @author chenjingsi
 * @version 2016-05-13
 */
public class LgRepairhistory extends DataEntity<LgRepairhistory> {
	
	private static final long serialVersionUID = 1L;
	private String machineId;		// 整机ID
	private Date repairTime;		// 报修时间
	private String orderNo;		// 报修单号
	private String orderHuman;		// 报修人
	private String troubleInfo;		// 故障说明
	private String feedBack;		// 报修反馈
	
	public LgRepairhistory() {
		super();
	}

	public LgRepairhistory(String id){
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
	public Date getRepairTime() {
		return repairTime;
	}

	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}
	
	@Length(min=0, max=40, message="报修单号长度必须介于 0 和 40 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=60, message="报修人长度必须介于 0 和 60 之间")
	public String getOrderHuman() {
		return orderHuman;
	}

	public void setOrderHuman(String orderHuman) {
		this.orderHuman = orderHuman;
	}
	
	@Length(min=0, max=200, message="故障说明长度必须介于 0 和 200 之间")
	public String getTroubleInfo() {
		return troubleInfo;
	}

	public void setTroubleInfo(String troubleInfo) {
		this.troubleInfo = troubleInfo;
	}
	
	@Length(min=0, max=200, message="报修反馈长度必须介于 0 和 200 之间")
	public String getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}
	
}