/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.common.entity;

import java.util.List;

/**
 * 
 * @author K2DATA.wsguo
 * @date Jun 24, 2016 12:29:11 PM    
 * 
 */
public class LgDeviceMapPoint {
	private String seriesName;
	private String device;
	private List<LgMapPoint> mapPointList;
	
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getSeriesName() {
		return seriesName;
	}
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	public List<LgMapPoint> getMapPointList() {
		return mapPointList;
	}
	public void setMapPointList(List<LgMapPoint> mapPointList) {
		this.mapPointList = mapPointList;
	}
	
}
