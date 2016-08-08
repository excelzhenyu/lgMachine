/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.slice;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 状态量切片统计明细Entity
 * @author wangshengguo
 * @version 2016-07-05
 */
public class LgSliceStaticsStatusDetail extends DataEntity<LgSliceStaticsStatusDetail> {
	
	private static final long serialVersionUID = 1L;
	private Date insertTime;		// 写入时间
	private String sliceStatusId;		// 状态量切片ID
	private String deviceNo;		// 整机对外发布的编码
	private String sensorNo;		// 传感器(工况)编码
	private String status;		// 状态量
	private Integer statusCount;		// 切片内传感器状态量次数
	private Integer statusDuration;		// 切片内传感器状态量持续时间
	private Double statusMax;		// 状态量持续次数最大值
	private Double statusMin;		// 状态量持续次数最小值
	private Double statusAvg;		// 状态量持续次数平均值
	private Double status5;		// 状态量持续次数切片内5分位
	private Double status25;		// 状态量持续次数切片内25分位
	private Double status50;		// 状态量持续次数切片内中分位
	private Double status75;		// 状态量持续次数切片内75分位
	private Double status95;		// 状态量持续次数切片内95分位
	private Double statusStandardStd;		// 状态量持续次数标准差
	private Double statusVarianceStd;		// 状态量持续次数方差
	
	public LgSliceStaticsStatusDetail() {
		super();
	}

	public LgSliceStaticsStatusDetail(String id){
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
	
	@Length(min=0, max=10, message="状态量长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getStatusCount() {
		return statusCount;
	}

	public void setStatusCount(Integer statusCount) {
		this.statusCount = statusCount;
	}
	
	public Integer getStatusDuration() {
		return statusDuration;
	}

	public void setStatusDuration(Integer statusDuration) {
		this.statusDuration = statusDuration;
	}
	
	public Double getStatusMax() {
		return statusMax;
	}

	public void setStatusMax(Double statusMax) {
		this.statusMax = statusMax;
	}
	
	public Double getStatusMin() {
		return statusMin;
	}

	public void setStatusMin(Double statusMin) {
		this.statusMin = statusMin;
	}
	
	public Double getStatusAvg() {
		return statusAvg;
	}

	public void setStatusAvg(Double statusAvg) {
		this.statusAvg = statusAvg;
	}
	
	public Double getStatus5() {
		return status5;
	}

	public void setStatus5(Double status5) {
		this.status5 = status5;
	}
	
	public Double getStatus25() {
		return status25;
	}

	public void setStatus25(Double status25) {
		this.status25 = status25;
	}
	
	public Double getStatus50() {
		return status50;
	}

	public void setStatus50(Double status50) {
		this.status50 = status50;
	}
	
	public Double getStatus75() {
		return status75;
	}

	public void setStatus75(Double status75) {
		this.status75 = status75;
	}
	
	public Double getStatus95() {
		return status95;
	}

	public void setStatus95(Double status95) {
		this.status95 = status95;
	}
	
	public Double getStatusStandardStd() {
		return statusStandardStd;
	}

	public void setStatusStandardStd(Double statusStandardStd) {
		this.statusStandardStd = statusStandardStd;
	}
	
	public Double getStatusVarianceStd() {
		return statusVarianceStd;
	}

	public void setStatusVarianceStd(Double statusVarianceStd) {
		this.statusVarianceStd = statusVarianceStd;
	}
	
}