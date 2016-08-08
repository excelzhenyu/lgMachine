/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.baseinfo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 基础信息-时间维度Entity
 * @author wangshengguo
 * @version 2016-06-13
 */
public class LgDimDate extends DataEntity<LgDimDate> {
	
	private static final long serialVersionUID = 1L;
	private Date day;		// 日期
	private Integer weekId;		// 周ID
	private String weekName;		// 周名称
	private Integer monthId;		// 月ID
	private String monthName;		// 月名称
	private Integer seasonId;		// 季度ID
	private String seasonName;		// 季度名称
	private Integer yearId;		// 年ID
	private String yearName;		// 年名称
	
	public LgDimDate() {
		super();
	}

	public LgDimDate(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}
	
	public Integer getWeekId() {
		return weekId;
	}

	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}
	
	@Length(min=0, max=20, message="周名称长度必须介于 0 和 20 之间")
	public String getWeekName() {
		return weekName;
	}

	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}
	
	public Integer getMonthId() {
		return monthId;
	}

	public void setMonthId(Integer monthId) {
		this.monthId = monthId;
	}
	
	@Length(min=0, max=20, message="月名称长度必须介于 0 和 20 之间")
	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	
	public Integer getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Integer seasonId) {
		this.seasonId = seasonId;
	}
	
	@Length(min=0, max=20, message="季度名称长度必须介于 0 和 20 之间")
	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
	
	public Integer getYearId() {
		return yearId;
	}

	public void setYearId(Integer yearId) {
		this.yearId = yearId;
	}
	
	@Length(min=0, max=20, message="年名称长度必须介于 0 和 20 之间")
	public String getYearName() {
		return yearName;
	}

	public void setYearName(String yearName) {
		this.yearName = yearName;
	}
	
}