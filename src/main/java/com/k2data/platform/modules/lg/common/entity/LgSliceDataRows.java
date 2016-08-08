/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.common.entity;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author K2DATA.wsguo
 * @date Jul 5, 2016 1:53:23 PM    
 * 
 */
public class LgSliceDataRows {

	private Date iso;
	private List<LgSliceDataRowsPoints> dataPoints;
	private int dataPointsCount;
	private String device;
	private List<LgSliceDataRowsAggregationResults> aggregationResults;

	public Date getIso() {
		return iso;
	}
	public void setIso(Date iso) {
		this.iso = iso;
	}
	public List<LgSliceDataRowsPoints> getDataPoints() {
		return dataPoints;
	}
	public void setDataPoints(List<LgSliceDataRowsPoints> dataPoints) {
		this.dataPoints = dataPoints;
	}
	
	public int getDataPointsCount() {
		return dataPointsCount;
	}
	public void setDataPointsCount(int dataPointsCount) {
		this.dataPointsCount = dataPointsCount;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	
	public List<LgSliceDataRowsAggregationResults> getAggregationResults() {
		return aggregationResults;
	}
	public void setAggregationResults(List<LgSliceDataRowsAggregationResults> aggregationResults) {
		this.aggregationResults = aggregationResults;
	}
	
	
	
}
