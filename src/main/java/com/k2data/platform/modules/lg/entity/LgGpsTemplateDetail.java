/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * GPS传感器模板明细Entity
 * @author chenjingsi
 * @version 2016-06-02
 */
public class LgGpsTemplateDetail extends DataEntity<LgGpsTemplateDetail> {
	
	private static final long serialVersionUID = 1L;
	private String gpsTemplateId;		// GPS传感器模板ID
	private String gpsTemplateNo;		// GPS传感器模板编号
	private String sensorMark;		// 传感器标识
	private String sensorName;		// 传感器名称
	private Integer sensorType;		// 传感器类型（1模拟量2开关量3状态量）
	
	public LgGpsTemplateDetail() {
		super();
	}

	public LgGpsTemplateDetail(String id){
		super(id);
	}

	@Length(min=0, max=64, message="GPS传感器模板ID长度必须介于 0 和 64 之间")
	public String getGpsTemplateId() {
		return gpsTemplateId;
	}

	public void setGpsTemplateId(String gpsTemplateId) {
		this.gpsTemplateId = gpsTemplateId;
	}
	
	@Length(min=0, max=64, message="GPS传感器模板编号长度必须介于 0 和 64 之间")
	public String getGpsTemplateNo() {
		return gpsTemplateNo;
	}

	public void setGpsTemplateNo(String gpsTemplateNo) {
		this.gpsTemplateNo = gpsTemplateNo;
	}
	
	@Length(min=0, max=30, message="传感器标识长度必须介于 0 和 30 之间")
	public String getSensorMark() {
		return sensorMark;
	}

	public void setSensorMark(String sensorMark) {
		this.sensorMark = sensorMark;
	}
	
	@Length(min=0, max=40, message="传感器名称长度必须介于 0 和 40 之间")
	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
	
	public Integer getSensorType() {
		return sensorType;
	}

	public void setSensorType(Integer sensorType) {
		this.sensorType = sensorType;
	}
	
}