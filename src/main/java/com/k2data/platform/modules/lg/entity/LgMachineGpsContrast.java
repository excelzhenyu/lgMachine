/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 整机与GPS设备对照Entity
 * @author chenjingsi
 * @version 2016-06-03
 */
public class LgMachineGpsContrast extends DataEntity<LgMachineGpsContrast> {
	
	private static final long serialVersionUID = 1L;
	private String deviceNo;		// 整机编码
	private String gpsNo;		// GPS设备编码
	private String gpsTemplateNo;		// GPS传感器模板编号
	private Integer isValid;		// 有效标志（0无效1有效）
	
	public LgMachineGpsContrast() {
		super();
	}

	public LgMachineGpsContrast(String id){
		super(id);
	}

	@Length(min=0, max=40, message="整机编码长度必须介于 0 和 40 之间")
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	
	@Length(min=0, max=40, message="GPS设备编码长度必须介于 0 和 40 之间")
	public String getGpsNo() {
		return gpsNo;
	}

	public void setGpsNo(String gpsNo) {
		this.gpsNo = gpsNo;
	}
	
	@Length(min=0, max=64, message="GPS传感器模板编号长度必须介于 0 和 64 之间")
	public String getGpsTemplateNo() {
		return gpsTemplateNo;
	}

	public void setGpsTemplateNo(String gpsTemplateNo) {
		this.gpsTemplateNo = gpsTemplateNo;
	}
	
	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	
}