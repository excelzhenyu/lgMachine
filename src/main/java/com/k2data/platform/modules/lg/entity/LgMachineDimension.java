/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 整机维度Entity
 * @author wangshengguo
 * @version 2016-05-09
 * 
 */
public class LgMachineDimension extends DataEntity<LgMachineDimension> {
	
	private static final long serialVersionUID = 1L;
	private String dimensionType;		// 维度类别
	private String dimensionCode;		// 维度编号
	private String dimensionName;		// 维度名称
	private Double longitude;  // 经度
	private Double latitude;   // 纬度
	
	public LgMachineDimension() {
		super();
	}

	public LgMachineDimension(String id){
		super(id);
	}

	@Length(min=0, max=2, message="维度类别长度必须介于 0 和 2 之间")
	public String getDimensionType() {
		return dimensionType;
	}

	public void setDimensionType(String dimensionType) {
		this.dimensionType = dimensionType;
	}
	
	@Length(min=0, max=20, message="维度编号长度必须介于 0 和 20 之间")
	public String getDimensionCode() {
		return dimensionCode;
	}

	public void setDimensionCode(String dimensionCode) {
		this.dimensionCode = dimensionCode;
	}
	
	@Length(min=0, max=120, message="维度名称长度必须介于 0 和 120 之间")
	public String getDimensionName() {
		return dimensionName;
	}

	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
	}

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
	
}