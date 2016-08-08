/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.distribution;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 
 * @author K2DATA.wsguo
 * @date Jul 9, 2016 10:27:51 AM    
 * 
 */
public class LgMachineDistributionBarData extends DataEntity<LgMachineDistributionBarData>{

	private String cityNo;		//城市编号
	private String cityName;	//城市名称
	private String dataType;	//数据类型 productType or orderNumber
	private Integer dataCount;	//数据数量
	
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

	public Integer getDataCount() {
		return dataCount;
	}

	public void setDataCount(Integer dataCount) {
		this.dataCount = dataCount;
	}

}
