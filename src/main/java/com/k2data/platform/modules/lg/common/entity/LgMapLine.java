/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.common.entity;

import java.util.List;

/**
 * 地图轨迹实体
 * @author K2DATA.wsguo
 * @date Jun 24, 2016 10:14:49 AM    
 * 
 */
public class LgMapLine {
	private String name;
	private List<Double> coord;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Double> getCoord() {
		return coord;
	}
	public void setCoord(List<Double> coord) {
		this.coord = coord;
	}

	
}
