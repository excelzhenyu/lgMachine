/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import java.util.List;

/**
 * 供导入功能使用的VO类
 * @author chenjingsi
 * @version 2016-05-09
 */
public class SensorChartJson{
	
	private String[] sensorMark;
	private String sensorName;
	private String deviceNo;
	private List<String> isoList;
	private List<Double> valueList;
	private List<Double> xValueList;
	private List<Double> yValueList;
	private List<List<Double>> xyValueList;
	

	public String[] getSensorMark() {
		return sensorMark;
	}

	public void setSensorMark(String[] sensorMark) {
		this.sensorMark = sensorMark;
	}

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public List<String> getIsoList() {
		return isoList;
	}

	public void setIsoList(List<String> isoList) {
		this.isoList = isoList;
	}

	public List<Double> getValueList() {
		return valueList;
	}

	public void setValueList(List<Double> valueList) {
		this.valueList = valueList;
	}

	public List<Double> getxValueList() {
		return xValueList;
	}

	public void setxValueList(List<Double> xValueList) {
		this.xValueList = xValueList;
	}

	public List<Double> getyValueList() {
		return yValueList;
	}

	public void setyValueList(List<Double> yValueList) {
		this.yValueList = yValueList;
	}

	public List<List<Double>> getXyValueList() {
		return xyValueList;
	}

	public void setXyValueList(List<List<Double>> xyValueList) {
		this.xyValueList = xyValueList;
	}


	
	
	
}