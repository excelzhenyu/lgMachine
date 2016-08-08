/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.distribution;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.k2data.platform.common.persistence.DataEntity;

/**
 * 省热度分布Entity
 * @author wangshengguo
 * @version 2016-06-30
 */
public class LgMachineDistributionByProvince extends DataEntity<LgMachineDistributionByProvince> {
	
	private static final long serialVersionUID = 1L;
	private Integer yearMonthId;		// 年月ID
	private String yearMonthName;		// 年月
	private String machineType;		// 机器类别
	private String provinceNo;		// 省代码
	private String provinceName;		// 省名称
	private Integer provinceMachineRunDurationAvg;		// 省平均开机时长
	private Integer provinceMachineRunDurationSum;		// 省总开机时长
	private Double provinceMachineRunTimesAvg;		// 省平均开机次数
	private Integer provinceMachineRunTimesSum;		// 省开机总次数
	private Integer provinceMachineSum;		// 省开机机器总数
	private Date insertTime;		// 写入时间

	public LgMachineDistributionByProvince() {
		super();
	}

	public LgMachineDistributionByProvince(String id){
		super(id);
	}

	public Integer getYearMonthId() {
		return yearMonthId;
	}

	public void setYearMonthId(Integer yearMonthId) {
		this.yearMonthId = yearMonthId;
	}
	
	@Length(min=0, max=20, message="年月长度必须介于 0 和 20 之间")
	public String getYearMonthName() {
		return yearMonthName;
	}

	public void setYearMonthName(String yearMonthName) {
		this.yearMonthName = yearMonthName;
	}
	
	@Length(min=0, max=1, message="机器类别长度必须介于 0 和 1 之间")
	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	
	@Length(min=0, max=10, message="省代码长度必须介于 0 和 10 之间")
	public String getProvinceNo() {
		return provinceNo;
	}

	public void setProvinceNo(String provinceNo) {
		this.provinceNo = provinceNo;
	}
	
	@Length(min=0, max=20, message="省名称长度必须介于 0 和 20 之间")
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	public Integer getProvinceMachineRunDurationAvg() {
		return provinceMachineRunDurationAvg;
	}

	public void setProvinceMachineRunDurationAvg(Integer provinceMachineRunDurationAvg) {
		this.provinceMachineRunDurationAvg = provinceMachineRunDurationAvg;
	}
	
	public Integer getProvinceMachineRunDurationSum() {
		return provinceMachineRunDurationSum;
	}

	public void setProvinceMachineRunDurationSum(Integer provinceMachineRunDurationSum) {
		this.provinceMachineRunDurationSum = provinceMachineRunDurationSum;
	}
	
	public Double getProvinceMachineRunTimesAvg() {
		return provinceMachineRunTimesAvg;
	}

	public void setProvinceMachineRunTimesAvg(Double provinceMachineRunTimesAvg) {
		this.provinceMachineRunTimesAvg = provinceMachineRunTimesAvg;
	}
	
	public Integer getProvinceMachineRunTimesSum() {
		return provinceMachineRunTimesSum;
	}

	public void setProvinceMachineRunTimesSum(Integer provinceMachineRunTimesSum) {
		this.provinceMachineRunTimesSum = provinceMachineRunTimesSum;
	}
	
	public Integer getProvinceMachineSum() {
		return provinceMachineSum;
	}

	public void setProvinceMachineSum(Integer provinceMachineSum) {
		this.provinceMachineSum = provinceMachineSum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	
}