/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 部件变更记录Entity
 * @author chenjingsi
 * @version 2016-05-13
 */
public class LgReplacehistory extends DataEntity<LgReplacehistory> {
	
	private static final long serialVersionUID = 1L;
	private String machineId;		// 整机ID
	private Date replaceTime;		// 换件时间
	private String orderNo;		// 维修单号
	private String replaceCode;		// 换件编码
	private String replaceName;		// 换件名称
	private String replaceNums;		// 换件数量
	
	public LgReplacehistory() {
		super();
	}

	public LgReplacehistory(String id){
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
	public Date getReplaceTime() {
		return replaceTime;
	}

	public void setReplaceTime(Date replaceTime) {
		this.replaceTime = replaceTime;
	}
	
	@Length(min=0, max=40, message="维修单号长度必须介于 0 和 40 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=40, message="换件编码长度必须介于 0 和 40 之间")
	public String getReplaceCode() {
		return replaceCode;
	}

	public void setReplaceCode(String replaceCode) {
		this.replaceCode = replaceCode;
	}
	
	@Length(min=0, max=100, message="换件名称长度必须介于 0 和 100 之间")
	public String getReplaceName() {
		return replaceName;
	}

	public void setReplaceName(String replaceName) {
		this.replaceName = replaceName;
	}
	
	@Length(min=0, max=11, message="换件数量长度必须介于 0 和 11 之间")
	public String getReplaceNums() {
		return replaceNums;
	}

	public void setReplaceNums(String replaceNums) {
		this.replaceNums = replaceNums;
	}
	
}