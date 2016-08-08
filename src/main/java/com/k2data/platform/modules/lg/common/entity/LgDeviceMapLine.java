/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.common.entity;

import java.util.List;

/**
 * 地图轨迹实体
 * @author K2DATA.wsguo
 * @date Jun 24, 2016 12:29:11 PM    
 * 
 */
public class LgDeviceMapLine {
	private String device;
	private List<List<LgMapLine>> mapLineList;
	private List<Double> centerPosition;
	private long zoomValue;
	private String seriesType;
	
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public List<List<LgMapLine>> getMapLineList() {
		return mapLineList;
	}
	public void setMapLineList(List<List<LgMapLine>> mapLineList) {
		this.mapLineList = mapLineList;
	}
	public List<Double> getCenterPosition() {
		return centerPosition;
	}
	public void setCenterPosition(List<Double> centerPosition) {
		this.centerPosition = centerPosition;
	}
	public long getZoomValue() {
		return zoomValue;
	}
	public void setZoomValue(long zoomValue) {
		this.zoomValue = zoomValue;
	}
	public String getSeriesType() {
		return seriesType;
	}
	public void setSeriesType(String seriesType) {
		this.seriesType = seriesType;
	}
}
