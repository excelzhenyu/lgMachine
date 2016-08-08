/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.slice;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 状态量切片内切换明细Entity
 * @author wangshengguo
 * @version 2016-05-23
 */
public class LgSliceSwitchStatusDetail extends DataEntity<LgSliceSwitchStatusDetail> {
	
	private static final long serialVersionUID = 1L;
	private Date insertTime;		// 写入时间
	private String sliceStatusId;		// 状态量切片ID
	private String deviceNo;		// 整机对外发布的编码
	private String sensorNo;		// 传感器(工况)编码
	private String sourceStatus;		// 源状态值
	private String destStatus;		// 目标状态值
	private Integer statusCount;		// 该状态间切换次数
	private Integer durationMax;		// 状态量切换最大时间
	private Integer durationMin;		// 状态量切换最小时间
	private Double durationAvg;		// 状态量切换平均时间
	private Double duration5;		// 状态量持续次数切片内5分位
	private Double duration25;		// 状态量持续次数切片内25分位
	private Double duration50;		// 状态量持续次数切片内中分位
	private Double duration75;		// 状态量持续次数切片内75分位
	private Double durations95;		// 状态量持续次数切片内95分位
	private Double durationStandardStd;		// 状态量持续次数标准差
	private Double durationVarianceStd;		// 状态量持续次数方差
	
	public LgSliceSwitchStatusDetail() {
		super();
	}

	public LgSliceSwitchStatusDetail(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	
	@Length(min=0, max=64, message="状态量切片ID长度必须介于 0 和 64 之间")
	public String getSliceStatusId() {
		return sliceStatusId;
	}

	public void setSliceStatusId(String sliceStatusId) {
		this.sliceStatusId = sliceStatusId;
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
	
	@Length(min=0, max=10, message="源状态值长度必须介于 0 和 10 之间")
	public String getSourceStatus() {
		return sourceStatus;
	}

	public void setSourceStatus(String sourceStatus) {
		this.sourceStatus = sourceStatus;
	}
	
	@Length(min=0, max=10, message="目标状态值长度必须介于 0 和 10 之间")
	public String getDestStatus() {
		return destStatus;
	}

	public void setDestStatus(String destStatus) {
		this.destStatus = destStatus;
	}
	
	public Integer getStatusCount() {
		return statusCount;
	}

	public void setStatusCount(Integer statusCount) {
		this.statusCount = statusCount;
	}
	
	public Integer getDurationMax() {
		return durationMax;
	}

	public void setDurationMax(Integer durationMax) {
		this.durationMax = durationMax;
	}
	
	public Integer getDurationMin() {
		return durationMin;
	}

	public void setDurationMin(Integer durationMin) {
		this.durationMin = durationMin;
	}
	
	public Double getDurationAvg() {
		return durationAvg;
	}

	public void setDurationAvg(Double durationAvg) {
		this.durationAvg = durationAvg;
	}
	
	public Double getDuration5() {
		return duration5;
	}

	public void setDuration5(Double duration5) {
		this.duration5 = duration5;
	}
	
	public Double getDuration25() {
		return duration25;
	}

	public void setDuration25(Double duration25) {
		this.duration25 = duration25;
	}
	
	public Double getDuration50() {
		return duration50;
	}

	public void setDuration50(Double duration50) {
		this.duration50 = duration50;
	}
	
	public Double getDuration75() {
		return duration75;
	}

	public void setDuration75(Double duration75) {
		this.duration75 = duration75;
	}
	
	public Double getDurations95() {
		return durations95;
	}

	public void setDurations95(Double durations95) {
		this.durations95 = durations95;
	}
	
	public Double getDurationStandardStd() {
		return durationStandardStd;
	}

	public void setDurationStandardStd(Double durationStandardStd) {
		this.durationStandardStd = durationStandardStd;
	}
	
	public Double getDurationVarianceStd() {
		return durationVarianceStd;
	}

	public void setDurationVarianceStd(Double durationVarianceStd) {
		this.durationVarianceStd = durationVarianceStd;
	}
	
}