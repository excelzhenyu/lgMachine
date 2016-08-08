/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.common.entity;

import java.util.Date;

/**
 * 
 * @author K2DATA.wsguo
 * @date Jul 18, 2016 6:05:39 PM    
 * 
 */
public class LgSliceDataPoints {
	private Date iso;
	private String sensor;
	private String device;
	private Object value;
	
	public Date getIso() {
		return iso;
	}
	public void setIso(Date iso) {
		this.iso = iso;
	}
	public String getSensor() {
		return sensor;
	}
	public void setSensor(String sensor) {
		this.sensor = sensor;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	
}
