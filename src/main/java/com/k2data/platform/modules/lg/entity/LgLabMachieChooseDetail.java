/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 实验机器选取明细Entity
 * @author wangshengguo
 * @version 2016-05-12
 */
public class LgLabMachieChooseDetail extends DataEntity<LgLabMachieChooseDetail> {
	
	private static final long serialVersionUID = 1L;
	private String batchNumber;		// 选取批次号
	private String chooseReason;		// 选取原因
	private String experimentContent;		// 实验内容
	private Date chooseTime;		// 选取时间
	private String chooseStaff;		// 选取员工
	private String machineId;		// 选取整机ID
	private String enableFlag;		// 启用标志
	private Date enableTime;		// 启用时间
	
	public LgLabMachieChooseDetail() {
		super();
	}

	public LgLabMachieChooseDetail(String id){
		super(id);
	}

	@Length(min=1, max=20, message="选取批次号长度必须介于 1 和 20 之间")
	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	
	@Length(min=0, max=255, message="选取原因长度必须介于 0 和 255 之间")
	public String getChooseReason() {
		return chooseReason;
	}

	public void setChooseReason(String chooseReason) {
		this.chooseReason = chooseReason;
	}
	
	@Length(min=0, max=255, message="实验内容长度必须介于 0 和 255 之间")
	public String getExperimentContent() {
		return experimentContent;
	}

	public void setExperimentContent(String experimentContent) {
		this.experimentContent = experimentContent;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getChooseTime() {
		return chooseTime;
	}

	public void setChooseTime(Date chooseTime) {
		this.chooseTime = chooseTime;
	}
	
	@Length(min=0, max=64, message="选取员工长度必须介于 0 和 64 之间")
	public String getChooseStaff() {
		return chooseStaff;
	}

	public void setChooseStaff(String chooseStaff) {
		this.chooseStaff = chooseStaff;
	}
	
	//@Length(min=0, max=1000, message="选取整机ID长度必须介于 0 和 64 之间")
	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	
	@Length(min=0, max=1, message="启用标志长度必须介于 0 和 1 之间")
	public String getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEnableTime() {
		return enableTime;
	}

	public void setEnableTime(Date enableTime) {
		this.enableTime = enableTime;
	}
	
}