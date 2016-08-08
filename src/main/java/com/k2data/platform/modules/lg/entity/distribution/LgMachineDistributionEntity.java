/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.distribution;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.k2data.platform.modules.lg.common.utils.CalendarUtils;

/**
 * 
 * @author K2DATA.wsguo
 * @date Jul 1, 2016 10:27:51 AM    
 * 
 */
public class LgMachineDistributionEntity {

	private String machineType; //机器类型
	private String productType;	//主机型号
	private String orderNumber;	//订货号
	private String densityType;	//热度类型
	private Date runMonth;		//开工月份
	private String location;	//地理位置，为全国，具体名称为省份等
	private String mapType;		//地图类型 china or province
	private int monthId;		//年月ID
	
	public String getMachineType() {
		return machineType;
	}
	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getDensityType() {
		return densityType;
	}
	public void setDensityType(String densityType) {
		this.densityType = densityType;
	}
	public Date getRunMonth() {
		return runMonth;
	}
	public void setRunMonth(Date runMonth) {
		this.runMonth = runMonth;
		if(runMonth != null) {
			this.monthId = CalendarUtils.genMonthNumber(runMonth);
		}
	}
	public String getMapType() {
		return mapType;
	}
	public void setMapType(String mapType) {
		this.mapType = mapType;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getMonthId() {
		return monthId;
	}
	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
