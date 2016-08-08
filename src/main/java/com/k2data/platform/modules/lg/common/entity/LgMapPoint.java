/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.common.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 地图位置实体
 * @author K2DATA.wsguo
 * @date Jun 24, 2016 10:14:49 AM    
 * 
 */
public class LgMapPoint {
	private String name;
	private String address;
	private Date workDate;
	private List<Double> value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public List<Double> getValue() {
		return value;
	}
	public void setValue(List<Double> value) {
		this.value = value;
	}
	
}
