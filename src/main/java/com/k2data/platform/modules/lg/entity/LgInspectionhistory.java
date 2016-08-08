/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 巡检整改Entity
 * @author chenjingsi
 * @version 2016-06-29
 */
public class LgInspectionhistory extends DataEntity<LgInspectionhistory> {
	
	private static final long serialVersionUID = 1L;
	private String machineId;		// 机器ID
	private Date inspectionTime;		// 巡检时间
	private String inspectionNo;		// 巡检整改编号
	private String orderHuman;		// 操作人
	private String info;		// 点修说明
	private String isMaintain;		// 转维修工单（1是0否）
	private String type;		// 单据类型（1巡检2整改）
	
	public LgInspectionhistory() {
		super();
	}

	public LgInspectionhistory(String id){
		super(id);
	}

	@Length(min=0, max=64, message="机器ID长度必须介于 0 和 64 之间")
	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInspectionTime() {
		return inspectionTime;
	}

	public void setInspectionTime(Date inspectionTime) {
		this.inspectionTime = inspectionTime;
	}
	
	@Length(min=0, max=40, message="巡检整改编号长度必须介于 0 和 40 之间")
	public String getInspectionNo() {
		return inspectionNo;
	}

	public void setInspectionNo(String inspectionNo) {
		this.inspectionNo = inspectionNo;
	}
	
	@Length(min=0, max=10, message="操作人长度必须介于 0 和 10 之间")
	public String getOrderHuman() {
		return orderHuman;
	}

	public void setOrderHuman(String orderHuman) {
		this.orderHuman = orderHuman;
	}
	
	@Length(min=0, max=100, message="点修说明长度必须介于 0 和 100 之间")
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	@Length(min=0, max=1, message="转维修工单（1是0否）长度必须介于 0 和 1 之间")
	public String getIsMaintain() {
		return isMaintain;
	}

	public void setIsMaintain(String isMaintain) {
		this.isMaintain = isMaintain;
	}
	
	@Length(min=0, max=1, message="单据类型（1巡检2整改）长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}