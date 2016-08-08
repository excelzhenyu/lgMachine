package com.k2data.platform.modules.lg.entity;

import com.k2data.platform.common.persistence.DataEntity;

public class SliceDataVO extends DataEntity<SliceDataVO>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String latitudes;
	private String longitude;
	private String province;
	private String city;
	private String address;
	private String position;
	
	
	public String getLatitudes() {
		return latitudes;
	}
	public void setLatitudes(String latitudes) {
		this.latitudes = latitudes;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
}
