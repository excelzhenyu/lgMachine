/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.distinguish;

import java.util.List;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 供导入功能使用的VO类
 * @author chenjingsi
 * @version 2016-05-09
 */
public class MissFalseDetail extends DataEntity<MissFalseDetail>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer runDurationTotal;
	private Integer runOffTotal;
	private Integer runDurationMax;
	private Integer rotationlSpeedMax;
	private Integer driveSpeedMax;
	private List<String> eventList;
	
	public Integer getRunDurationTotal() {
		return runDurationTotal;
	}
	public void setRunDurationTotal(Integer runDurationTotal) {
		this.runDurationTotal = runDurationTotal;
	}
	public Integer getRunOffTotal() {
		return runOffTotal;
	}
	public void setRunOffTotal(Integer runOffTotal) {
		this.runOffTotal = runOffTotal;
	}
	public Integer getRunDurationMax() {
		return runDurationMax;
	}
	public void setRunDurationMax(Integer runDurationMax) {
		this.runDurationMax = runDurationMax;
	}
	public Integer getRotationlSpeedMax() {
		return rotationlSpeedMax;
	}
	public void setRotationlSpeedMax(Integer rotationlSpeedMax) {
		this.rotationlSpeedMax = rotationlSpeedMax;
	}
	public Integer getDriveSpeedMax() {
		return driveSpeedMax;
	}
	public void setDriveSpeedMax(Integer driveSpeedMax) {
		this.driveSpeedMax = driveSpeedMax;
	}
	public List<String> getEventList() {
		return eventList;
	}
	public void setEventList(List<String> eventList) {
		this.eventList = eventList;
	}
	
	
	
}