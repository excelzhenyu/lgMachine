/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.distribution;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.k2data.platform.common.persistence.DataEntity;

/**
 * 地级市机器型号热度分布Entity
 * @author wangshengguo
 * @version 2016-06-30
 */
public class LgProductTypeDistributionByCity extends DataEntity<LgProductTypeDistributionByCity> {
	
	private static final long serialVersionUID = 1L;
	private Integer yearMonthId;		// 年月ID
	private String yearMonthName;		// 年月
	private String machineType;		// 机器类别
	private String productType;		// 机器型号
	private String provinceNo;		// 省代码
	private String levelCityNo;		// 地级市代码
	private String levelCityName;		// 市名称
	private Integer cityMachineRunDurationAvg;		// 市平均开机时长
	private Integer cityMachineRunDurationSum;		// 市开机总时长
	private Integer cityMachineWorkDurationSum;		// 市工作总时长
	private Double cityMachineRunTimesAvg;		// 市平均开机次数
	private Integer cityMachineRunTimesSum;		// 市开机总次数
	private Integer cityMachineSum;		// 市开机机器总数
	private Date insertTime;		// 写入时间

	public LgProductTypeDistributionByCity() {
		super();
	}

	public LgProductTypeDistributionByCity(String id){
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
	
	@Length(min=0, max=20, message="机器型号长度必须介于 0 和 20 之间")
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	@Length(min=0, max=10, message="省代码长度必须介于 0 和 10 之间")
	public String getProvinceNo() {
		return provinceNo;
	}

	public void setProvinceNo(String provinceNo) {
		this.provinceNo = provinceNo;
	}
	
	@Length(min=0, max=10, message="地级市代码长度必须介于 0 和 10 之间")
	public String getLevelCityNo() {
		return levelCityNo;
	}

	public void setLevelCityNo(String levelCityNo) {
		this.levelCityNo = levelCityNo;
	}
	
	@Length(min=0, max=20, message="市名称长度必须介于 0 和 20 之间")
	public String getLevelCityName() {
		return levelCityName;
	}

	public void setLevelCityName(String levelCityName) {
		this.levelCityName = levelCityName;
	}
	
	public Integer getCityMachineRunDurationAvg() {
		return cityMachineRunDurationAvg;
	}

	public void setCityMachineRunDurationAvg(Integer cityMachineRunDurationAvg) {
		this.cityMachineRunDurationAvg = cityMachineRunDurationAvg;
	}
	
	public Integer getCityMachineRunDurationSum() {
		return cityMachineRunDurationSum;
	}

	public void setCityMachineRunDurationSum(Integer cityMachineRunDurationSum) {
		this.cityMachineRunDurationSum = cityMachineRunDurationSum;
	}
	
	public Integer getCityMachineWorkDurationSum() {
		return cityMachineWorkDurationSum;
	}

	public void setCityMachineWorkDurationSum(Integer cityMachineWorkDurationSum) {
		this.cityMachineWorkDurationSum = cityMachineWorkDurationSum;
	}
	
	public Double getCityMachineRunTimesAvg() {
		return cityMachineRunTimesAvg;
	}

	public void setCityMachineRunTimesAvg(Double cityMachineRunTimesAvg) {
		this.cityMachineRunTimesAvg = cityMachineRunTimesAvg;
	}
	
	public Integer getCityMachineRunTimesSum() {
		return cityMachineRunTimesSum;
	}

	public void setCityMachineRunTimesSum(Integer cityMachineRunTimesSum) {
		this.cityMachineRunTimesSum = cityMachineRunTimesSum;
	}
	
	public Integer getCityMachineSum() {
		return cityMachineSum;
	}

	public void setCityMachineSum(Integer cityMachineSum) {
		this.cityMachineSum = cityMachineSum;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
}