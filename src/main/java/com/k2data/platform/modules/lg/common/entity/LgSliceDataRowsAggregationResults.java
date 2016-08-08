/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.common.entity;

/**
 * 
 * @author K2DATA.wsguo
 * @date Jul 19, 2016 2:05:04 PM    
 * 
 */
public class LgSliceDataRowsAggregationResults {

	private String sensor;
	private Object min;
	private Object max;
	private Object count;
	
	public String getSensor() {
		return sensor;
	}
	public void setSensor(String sensor) {
		this.sensor = sensor;
	}
	public Object getMin() {
		return min;
	}
	public void setMin(Object min) {
		this.min = min;
	}
	public Object getMax() {
		return max;
	}
	public void setMax(Object max) {
		this.max = max;
	}
	public Object getCount() {
		return count;
	}
	public void setCount(Object count) {
		this.count = count;
	}
	
	

}
