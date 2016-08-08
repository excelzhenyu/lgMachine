/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * GPS传感器模板Entity
 * @author chenjingsi
 * @version 2016-06-02
 */
public class LgGpsTemplate extends DataEntity<LgGpsTemplate> {
	
	private static final long serialVersionUID = 1L;
	private String gpsTemplateNo;		// GPS传感器模板编号
	private String gpsTemplateName;		// 模版名称
	private Integer stopMark;		// 停用标志
	private Date startTime;		// 启用时间
	private Date stopTime;		// 停用时间
	
	public LgGpsTemplate() {
		super();
	}

	public LgGpsTemplate(String id){
		super(id);
	}

	@Length(min=0, max=40, message="gpstemplateno长度必须介于 0 和 40 之间")
	public String getGpsTemplateNo() {
		return gpsTemplateNo;
	}

	public void setGpsTemplateNo(String gpsTemplateNo) {
		this.gpsTemplateNo = gpsTemplateNo;
	}
	
	@Length(min=0, max=30, message="模版名称长度必须介于 0 和 30 之间")
	public String getGpsTemplateName() {
		return gpsTemplateName;
	}

	public void setGpsTemplateName(String gpsTemplateName) {
		this.gpsTemplateName = gpsTemplateName;
	}
	
	public Integer getStopMark() {
		return stopMark;
	}

	public void setStopMark(Integer stopMark) {
		this.stopMark = stopMark;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}
	
}