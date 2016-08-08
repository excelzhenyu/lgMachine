package com.k2data.platform.modules.lg.entity.machinestatus;

import java.util.Date;

/**
 * 工作履历分析查询条件 domain
 */
public class LgWorkResumeCond {
	
	private String machineType;	// 主机类别
	private String productType;	// 主机型号
	private String orderNumber;	// 订货号
	private String profileId;	// 主机 ID
	private String dealerId;	// 经销商 ID
	private String workCityId;	// 工作地域 ID
	private String saleCityId;	// 销售地域 ID
	private Date sliceStart;	// 开工日期
	private Date sliceStop;		// 结束日期
	
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
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getDealerId() {
		return dealerId;
	}
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	public String getWorkCityId() {
		return workCityId;
	}
	public void setWorkCityId(String workCityId) {
		this.workCityId = workCityId;
	}
	public String getSaleCityId() {
		return saleCityId;
	}
	public void setSaleCityId(String saleCityId) {
		this.saleCityId = saleCityId;
	}
	public Date getSliceStart() {
		return sliceStart;
	}
	public void setSliceStart(Date sliceStart) {
		this.sliceStart = sliceStart;
	}
	public Date getSliceStop() {
		return sliceStop;
	}
	public void setSliceStop(Date sliceStop) {
		this.sliceStop = sliceStop;
	}

}
