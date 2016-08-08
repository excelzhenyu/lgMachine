/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.common.entity;

import java.util.List;

/**
 * 地图轨迹图实体类
 * @author K2DATA.wsguo
 * @date Jul 21, 2016 2:56:43 PM    
 * 
 */
public class LgMapTrack {

	private List<Double> coord;
	private String cityCode;
	
	public List<Double> getCoord() {
		return coord;
	}
	public void setCoord(List<Double> coord) {
		this.coord = coord;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	
	
}
