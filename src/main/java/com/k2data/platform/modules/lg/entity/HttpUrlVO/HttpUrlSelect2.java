package com.k2data.platform.modules.lg.entity.HttpUrlVO;

import java.util.List;

/**
 * 获取接口设备信息的类
 * @author chenjingsi
 * @version 2016-05-09
 */
public class HttpUrlSelect2{
	
	private String iso;
	private List<HttpUrlSelect3> dataPoints;
	private String dataPointsCount;
	private String device;
	
	public String getIso() {
		return iso;
	}
	public void setIso(String iso) {
		this.iso = iso;
	}
	public List<HttpUrlSelect3> getDataPoints() {
		return dataPoints;
	}
	public void setDataPoints(List<HttpUrlSelect3> dataPoints) {
		this.dataPoints = dataPoints;
	}
	public String getDataPointsCount() {
		return dataPointsCount;
	}
	public void setDataPointsCount(String dataPointsCount) {
		this.dataPointsCount = dataPointsCount;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	
	
}