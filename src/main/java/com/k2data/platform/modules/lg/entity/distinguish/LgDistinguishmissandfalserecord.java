/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.distinguish;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 机器虚报漏报记录Entity
 * @author chenjingsi
 * @version 2016-06-29
 */
public class LgDistinguishmissandfalserecord extends DataEntity<LgDistinguishmissandfalserecord> {
	
	private static final long serialVersionUID = 1L;
	private String distinguishId;		// 虚漏报配置ID
	private Integer solutionType;		// 类型（1：虚报 2：漏报）
	private String deviceNo;		// 机器编码
	private Integer machineType;		// 机器类型
	private String productType;		// 机器型号
	private String orderNo;		// 订货号
	private String saleId;		// 经销商ID
	private String saleName;		// 经销商名称
	private String info;		// 简介
	private Date confirmDate;		// 确认日期
	private Integer confirmState;		// 确认状态（1：未处理 2：误报 3：人工已处理）
	private String operator;		// 处理人
	private Date operateDate;		// 处理日期
	private String operateInfo;		// 处理说明
	
	public LgDistinguishmissandfalserecord() {
		super();
	}

	public LgDistinguishmissandfalserecord(String id){
		super(id);
	}

	@Length(min=0, max=64, message="虚漏报配置ID长度必须介于 0 和 64 之间")
	public String getDistinguishId() {
		return distinguishId;
	}

	public void setDistinguishId(String distinguishId) {
		this.distinguishId = distinguishId;
	}
	
	public Integer getSolutionType() {
		return solutionType;
	}

	public void setSolutionType(Integer solutionType) {
		this.solutionType = solutionType;
	}
	
	@Length(min=0, max=20, message="机器编码长度必须介于 0 和 20 之间")
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	
	public Integer getMachineType() {
		return machineType;
	}

	public void setMachineType(Integer machineType) {
		this.machineType = machineType;
	}
	
	@Length(min=0, max=20, message="机器型号长度必须介于 0 和 20 之间")
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	@Length(min=0, max=20, message="订货号长度必须介于 0 和 20 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=64, message="经销商ID长度必须介于 0 和 64 之间")
	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	
	@Length(min=0, max=20, message="经销商名称长度必须介于 0 和 20 之间")
	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}
	
	@Length(min=0, max=255, message="简介长度必须介于 0 和 255 之间")
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}
	
	public Integer getConfirmState() {
		return confirmState;
	}

	public void setConfirmState(Integer confirmState) {
		this.confirmState = confirmState;
	}
	
	@Length(min=0, max=10, message="处理人长度必须介于 0 和 10 之间")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	
	@Length(min=0, max=255, message="处理说明长度必须介于 0 和 255 之间")
	public String getOperateInfo() {
		return operateInfo;
	}

	public void setOperateInfo(String operateInfo) {
		this.operateInfo = operateInfo;
	}
	
}