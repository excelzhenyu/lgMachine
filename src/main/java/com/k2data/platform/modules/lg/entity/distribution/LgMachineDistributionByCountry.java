/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.distribution;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.k2data.platform.common.persistence.DataEntity;

/**
 * 全国热度分布Entity
 * @author wangshengguo
 * @version 2016-06-30
 */
public class LgMachineDistributionByCountry extends DataEntity<LgMachineDistributionByCountry> {
	
	private static final long serialVersionUID = 1L;
	private Integer yearMonthId;		// 年月ID
	private String yearMonthName;		// 年月
	private String machineType;		// 机器类别
	private Integer countryMachineRunDurationAvg;		// 全国平均开机时长
	private Integer countryMachineRunDurationSum;		// 全国总开机时长
	private Double countryMachineRunTimesAvg;		// 全国平均开机次数
	private Integer countryMachineRunTimesSum;		// 全国开机总次数
	private Integer countryMachineSum;		// 全国开机机器总数
	private Date insertTime;		// 写入时间

	public LgMachineDistributionByCountry() {
		super();
	}

	public LgMachineDistributionByCountry(String id){
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
	
	public Integer getCountryMachineRunDurationAvg() {
		return countryMachineRunDurationAvg;
	}

	public void setCountryMachineRunDurationAvg(Integer countryMachineRunDurationAvg) {
		this.countryMachineRunDurationAvg = countryMachineRunDurationAvg;
	}
	
	public Integer getCountryMachineRunDurationSum() {
		return countryMachineRunDurationSum;
	}

	public void setCountryMachineRunDurationSum(Integer countryMachineRunDurationSum) {
		this.countryMachineRunDurationSum = countryMachineRunDurationSum;
	}
	
	public Double getCountryMachineRunTimesAvg() {
		return countryMachineRunTimesAvg;
	}

	public void setCountryMachineRunTimesAvg(Double countryMachineRunTimesAvg) {
		this.countryMachineRunTimesAvg = countryMachineRunTimesAvg;
	}
	
	public Integer getCountryMachineRunTimesSum() {
		return countryMachineRunTimesSum;
	}

	public void setCountryMachineRunTimesSum(Integer countryMachineRunTimesSum) {
		this.countryMachineRunTimesSum = countryMachineRunTimesSum;
	}
	
	public Integer getCountryMachineSum() {
		return countryMachineSum;
	}

	public void setCountryMachineSum(Integer countryMachineSum) {
		this.countryMachineSum = countryMachineSum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
}