/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.distribution;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 
 * @author K2DATA.wsguo
 * @date Jul 10, 2016 10:27:51 AM    
 * 
 */
public class LgMachineDistributionData extends DataEntity<LgMachineDistributionData>{

	private String cityNo;		//城市编号
	private String cityName;	//城市名称
	private String dataType;	//数据类型 machineType or productType or orderNumber
	private Integer runDurationAvg; //平均开机时长
	private Integer runDurationSum;	//总开机时长
	private Double runTimesAvg;		//平均开机次数
	private Integer runTimesSum;	//总开机次数
	private Integer machineSum;	//数据数量
	
	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getRunDurationAvg() {
		return runDurationAvg;
	}

	public void setRunDurationAvg(Integer runDurationAvg) {
		this.runDurationAvg = runDurationAvg;
	}

	public Integer getRunDurationSum() {
		return runDurationSum;
	}

	public void setRunDurationSum(Integer runDurationSum) {
		this.runDurationSum = runDurationSum;
	}

	public Double getRunTimesAvg() {
		return runTimesAvg;
	}

	public void setRunTimesAvg(Double runTimesAvg) {
		this.runTimesAvg = runTimesAvg;
	}

	public Integer getRunTimesSum() {
		return runTimesSum;
	}

	public void setRunTimesSum(Integer runTimesSum) {
		this.runTimesSum = runTimesSum;
	}

	public Integer getMachineSum() {
		return machineSum;
	}

	public void setMachineSum(Integer machineSum) {
		this.machineSum = machineSum;
	}


}
