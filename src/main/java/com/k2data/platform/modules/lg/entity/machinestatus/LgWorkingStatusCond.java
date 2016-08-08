package com.k2data.platform.modules.lg.entity.machinestatus;

/**
 * 开机实况查询条件
 */
public class LgWorkingStatusCond {
	
	private String machineType;	// 主机类别
	private String type;		// 展示方式 0-实时 1-24小时
	private String customerId;	// 经销商 ID
	private String saleYear;		// 销售年度 ID
	private String province;	// 省
	
	public String getMachineType() {
		return machineType;
	}
	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getSaleYear() {
		return saleYear;
	}
	public void setSaleYear(String saleYear) {
		this.saleYear = saleYear;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
}
