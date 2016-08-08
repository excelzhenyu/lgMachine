/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.exptmachine;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 试验机器选取Entity
 * @author wangshengguo
 * @version 2016-06-21
 */
public class LgExptMachinePick extends DataEntity<LgExptMachinePick> {
	
	private static final long serialVersionUID = 1L;
	private String batchNumber;		// 试验批次号
	private String exptGoal;		// 试验目的
	private String exptContent;		// 试验内容
	private Date pickTime;		// 选取时间
	private String pickStaff;		// 选取人
	private String isEffective;		// 有效码
	private Date exptBeginTime;		// 试验开始时间
	private Date exptEndTime;		// 试验结束时间
	
	private String pickStaffName; //选取人姓名
	private String pickStaffLoginName; //选取人登陆名
	
	
	public LgExptMachinePick() {
		super();
	}

	public LgExptMachinePick(String id){
		super(id);
	}

	@Length(min=0, max=20, message="试验批次号长度必须介于 0 和 20 之间")
	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	
	@Length(min=0, max=255, message="试验目的长度必须介于 0 和 255 之间")
	public String getExptGoal() {
		return exptGoal;
	}

	public void setExptGoal(String exptGoal) {
		this.exptGoal = exptGoal;
	}
	
	@Length(min=0, max=255, message="试验内容长度必须介于 0 和 255 之间")
	public String getExptContent() {
		return exptContent;
	}

	public void setExptContent(String exptContent) {
		this.exptContent = exptContent;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPickTime() {
		return pickTime;
	}

	public void setPickTime(Date pickTime) {
		this.pickTime = pickTime;
	}
	
	@Length(min=0, max=100, message="选取人长度必须介于 0 和 100 之间")
	public String getPickStaff() {
		return pickStaff;
	}

	public void setPickStaff(String pickStaff) {
		this.pickStaff = pickStaff;
	}
	
	@Length(min=0, max=1, message="有效码长度必须介于 0 和 1 之间")
	public String getIsEffective() {
		return isEffective;
	}

	public void setIsEffective(String isEffective) {
		this.isEffective = isEffective;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExptBeginTime() {
		return exptBeginTime;
	}

	public void setExptBeginTime(Date exptBeginTime) {
		this.exptBeginTime = exptBeginTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExptEndTime() {
		return exptEndTime;
	}

	public void setExptEndTime(Date exptEndTime) {
		this.exptEndTime = exptEndTime;
	}

	public String getPickStaffName() {
		return pickStaffName;
	}

	public void setPickStaffName(String pickStaffName) {
		this.pickStaffName = pickStaffName;
	}

	public String getPickStaffLoginName() {
		return pickStaffLoginName;
	}

	public void setPickStaffLoginName(String pickStaffLoginName) {
		this.pickStaffLoginName = pickStaffLoginName;
	}
	
}