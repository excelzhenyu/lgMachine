/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 机器大事记Entity
 * @author chenjingsi
 * @version 2016-05-13
 */
public class LgMachineevent extends DataEntity<LgMachineevent> {
	
	private static final long serialVersionUID = 1L;
	private String machineId;		// 整机ID
	private Date eventTime;		// 事件时间
	private String eventInfo;		// 大事记
	private String urlOrNot;		// 是否有外部链接
	private String url;		// 访问接口url
	private String eventDescription;		// 事件描述
	private String stopRemark;		// 停用标志
	private String eventType; //事记类型
	
	public LgMachineevent() {
		super();
	}

	public LgMachineevent(String id){
		super(id);
	}

	@Length(min=0, max=64, message="整机ID长度必须介于 0 和 64 之间")
	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	
	@Length(min=0, max=100, message="大事记长度必须介于 0 和 100 之间")
	public String getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(String eventInfo) {
		this.eventInfo = eventInfo;
	}
	
	@Length(min=0, max=1, message="是否有外部链接长度必须介于 0 和 1 之间")
	public String getUrlOrNot() {
		return urlOrNot;
	}

	public void setUrlOrNot(String urlOrNot) {
		this.urlOrNot = urlOrNot;
	}
	
	@Length(min=0, max=200, message="访问接口url长度必须介于 0 和 200 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=200, message="事件描述长度必须介于 0 和 200 之间")
	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	
	@Length(min=0, max=1, message="停用标志长度必须介于 0 和 1 之间")
	public String getStopRemark() {
		return stopRemark;
	}

	public void setStopRemark(String stopRemark) {
		this.stopRemark = stopRemark;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
}