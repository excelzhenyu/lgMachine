/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 整机传感器清单Entity
 * @author chenjingsi
 * @version 2016-05-09
 */
public class LgSensorlist extends DataEntity<LgSensorlist> {
	
	private static final long serialVersionUID = 1L;
	private String machineId;		// 整机ID
	private String sensorCode;		// 传感器编码
	private String sensorPIN;		// 传感器PIN码
	private String sensorName;		// 传感器名称
	private String stopMark;		// 停用标志
	private Date startTime;		// 启用时间
	private Date stopTime;		// 停用时间
	private String sensorType; //传感器类型
	
	public LgSensorlist() {
		super();
	}

	public LgSensorlist(String id){
		super(id);
	}

	@Length(min=0, max=64, message="整机ID长度必须介于 0 和 64 之间")
	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getSensorCode() {
		return sensorCode;
	}

	public void setSensorCode(String sensorCode) {
		this.sensorCode = sensorCode;
	}
	
	@Length(min=0, max=64, message="传感器PIN码长度必须介于 0 和 64 之间")
	public String getSensorPIN() {
		return sensorPIN;
	}

	public void setSensorPIN(String sensorPIN) {
		this.sensorPIN = sensorPIN;
	}
	
	@Length(min=0, max=64, message="传感器名称长度必须介于 0 和 64 之间")
	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
	
	@Length(min=0, max=1, message="停用标志长度必须介于 0 和 1 之间")
	public String getStopMark() {
		return stopMark;
	}

	public void setStopMark(String stopMark) {
		this.stopMark = stopMark;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public String getSensorType() {
		return sensorType;
	}

	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}
	
}