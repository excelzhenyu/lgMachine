/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.citylist;

import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 地图城市列表Entity
 * @author wangshengguo
 * @version 2016-06-02
 */
public class LgMapCity extends DataEntity<LgMapCity> {
	
	private static final long serialVersionUID = 1L;
	private String parentId;		// 父级编号
	private String cityCode;		// 城市编号
	private String cityName;		// 城市名称
	private Integer isort;		// 排序字段
	
	public LgMapCity() {
		super();
	}

	public LgMapCity(String id){
		super(id);
	}

	@Length(min=1, max=64, message="父级编号长度必须介于 1 和 64 之间")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Length(min=1, max=20, message="城市编号长度必须介于 1 和 20 之间")
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	@Length(min=1, max=40, message="城市名称长度必须介于 1 和 40 之间")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public Integer getIsort() {
		return isort;
	}

	public void setIsort(Integer isort) {
		this.isort = isort;
	}
	
}