/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 整机类别Entity
 * @author wangshengguo
 * @version 2016-05-09
 * 
 */
public class LgMachineType extends DataEntity<LgMachineType> {
	
	private static final long serialVersionUID = 1L;
	private String machineType;		// 机器类型
	private String productType;		// 产品型号
	private String orderNumber;		// 订货号
	
	public LgMachineType() {
		super();
	}

	public LgMachineType(String id){
		super(id);
	}

	@Length(min=1, max=1, message="机器类型长度必须介于 1 和 1 之间")
	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	
	@Length(min=1, max=20, message="产品型号长度必须介于 1 和 20 之间")
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	@Length(min=1, max=20, message="订货号长度必须介于 1 和 20 之间")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
}