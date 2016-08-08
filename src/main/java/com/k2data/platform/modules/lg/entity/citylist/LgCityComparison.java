/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.citylist;

import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 业务销售城市与地图城市对照Entity
 * @author wangshengguo
 * @version 2016-06-06
 */
public class LgCityComparison extends DataEntity<LgCityComparison> {
	
	private static final long serialVersionUID = 1L;
	private String mapCityId;		// 地图城市ID
	private String systemCityId;		// 业务城市ID
	private String isEffective;		// 有效码
	
	private String mapCityCode;
	private String mapCityName;
	private String systemCityCode;
	private String systemCityName;
	
	public LgCityComparison() {
		super();
	}

	public LgCityComparison(String id){
		super(id);
	}

	@Length(min=0, max=64, message="地图城市ID长度必须介于 0 和 64 之间")
	public String getMapCityId() {
		return mapCityId;
	}

	public void setMapCityId(String mapCityId) {
		this.mapCityId = mapCityId;
	}
	
	@Length(min=0, max=64, message="业务城市ID长度必须介于 0 和 64 之间")
	public String getSystemCityId() {
		return systemCityId;
	}

	public void setSystemCityId(String systemCityId) {
		this.systemCityId = systemCityId;
	}
	
	@Length(min=0, max=1, message="有效码长度必须介于 0 和 1 之间")
	public String getIsEffective() {
		return isEffective;
	}

	public void setIsEffective(String isEffective) {
		this.isEffective = isEffective;
	}

	public String getMapCityCode() {
		return mapCityCode;
	}

	public void setMapCityCode(String mapCityCode) {
		this.mapCityCode = mapCityCode;
	}

	public String getMapCityName() {
		return mapCityName;
	}

	public void setMapCityName(String mapCityName) {
		this.mapCityName = mapCityName;
	}

	public String getSystemCityCode() {
		return systemCityCode;
	}

	public void setSystemCityCode(String systemCityCode) {
		this.systemCityCode = systemCityCode;
	}

	public String getSystemCityName() {
		return systemCityName;
	}

	public void setSystemCityName(String systemCityName) {
		this.systemCityName = systemCityName;
	}
	
	
}