/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.slice;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 状态量切片统计Entity
 * @author wangshengguo
 * @version 2016-05-23
 */
public class LgSliceStaticsStatus extends DataEntity<LgSliceStaticsStatus> {
	
	private static final long serialVersionUID = 1L;
	private Date insertTime;		// 写入时间
	private String deviceSliceId;		// 设备切片ID
	private String deviceNo;		// 整机对外发布的编码
	private String sensorNo;		// 传感器(工况)编码
	private String sensorType;		// 传感器类别
	private Integer dataPointCount;		// 切片内传感器回传次数
	private Date sensorDataStart;		// 传感器首个数据点回传时间
	private Date sensorDataEnd;		// 传感器末尾数据点回传时间
	private Integer sensorDataDuration;		// 传感器回传时长
	private Integer changeCount;		// 状态切换次数
	
	public LgSliceStaticsStatus() {
		super();
	}

	public LgSliceStaticsStatus(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	
	@Length(min=0, max=64, message="设备切片ID长度必须介于 0 和 64 之间")
	public String getDeviceSliceId() {
		return deviceSliceId;
	}

	public void setDeviceSliceId(String deviceSliceId) {
		this.deviceSliceId = deviceSliceId;
	}
	
	@Length(min=0, max=40, message="整机对外发布的编码长度必须介于 0 和 40 之间")
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	
	@Length(min=0, max=40, message="传感器(工况)编码长度必须介于 0 和 40 之间")
	public String getSensorNo() {
		return sensorNo;
	}

	public void setSensorNo(String sensorNo) {
		this.sensorNo = sensorNo;
	}
	
	@Length(min=0, max=1, message="传感器类别长度必须介于 0 和 1 之间")
	public String getSensorType() {
		return sensorType;
	}

	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}
	
	public Integer getDataPointCount() {
		return dataPointCount;
	}

	public void setDataPointCount(Integer dataPointCount) {
		this.dataPointCount = dataPointCount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSensorDataStart() {
		return sensorDataStart;
	}

	public void setSensorDataStart(Date sensorDataStart) {
		this.sensorDataStart = sensorDataStart;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSensorDataEnd() {
		return sensorDataEnd;
	}

	public void setSensorDataEnd(Date sensorDataEnd) {
		this.sensorDataEnd = sensorDataEnd;
	}
	
	public Integer getSensorDataDuration() {
		return sensorDataDuration;
	}

	public void setSensorDataDuration(Integer sensorDataDuration) {
		this.sensorDataDuration = sensorDataDuration;
	}
	
	public Integer getChangeCount() {
		return changeCount;
	}

	public void setChangeCount(Integer changeCount) {
		this.changeCount = changeCount;
	}
	
}