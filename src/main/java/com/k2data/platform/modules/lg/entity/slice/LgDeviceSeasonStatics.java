/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.slice;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 设备每季工作统计Entity
 * @author 设备每季工作统计
 * @version 2016-06-13
 */
public class LgDeviceSeasonStatics extends DataEntity<LgDeviceSeasonStatics> {
	
	private static final long serialVersionUID = 1L;
	private Date insertTime;		// 写入时间
	private String deviceNo;		// 整机对外发布的编码
	private Integer workQuarterId;		// 工作季度ID
	private String workQuarterName;		// 工作季度名称
	private Integer workCount;		// 当季工作天数统计
	private Date workBegin;		// 当季初次开机时间
	private Date workStop;		// 当季最后一次工作结束时间
	private Integer sliceRunDuration;		// 开机时长
	private Integer sliceWorkDuration;		// 工作时长
	private Integer runOffCount;		// 当季开机次数
	private Double oilSum;		// 总油耗
	private Double oilAvg;		// 平均油耗
	private Integer rotationlSpeedMax;		// 最高转速
	private Double latitudes;		// 维度
	private Double longitude;		// 经度
	private String province;		// 省份
	private String city;		// 城市
	private String address;		// 地址
	private String position;		// 行政位置
	private Integer alarmCount;		// 报警次数
	
	public LgDeviceSeasonStatics() {
		super();
	}

	public LgDeviceSeasonStatics(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	
	@Length(min=0, max=40, message="整机对外发布的编码长度必须介于 0 和 40 之间")
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	
	public Integer getWorkQuarterId() {
		return workQuarterId;
	}

	public void setWorkQuarterId(Integer workQuarterId) {
		this.workQuarterId = workQuarterId;
	}
	
	@Length(min=0, max=20, message="工作季度名称长度必须介于 0 和 20 之间")
	public String getWorkQuarterName() {
		return workQuarterName;
	}

	public void setWorkQuarterName(String workQuarterName) {
		this.workQuarterName = workQuarterName;
	}
	
	public Integer getWorkCount() {
		return workCount;
	}

	public void setWorkCount(Integer workCount) {
		this.workCount = workCount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWorkBegin() {
		return workBegin;
	}

	public void setWorkBegin(Date workBegin) {
		this.workBegin = workBegin;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWorkStop() {
		return workStop;
	}

	public void setWorkStop(Date workStop) {
		this.workStop = workStop;
	}
	
	public Integer getSliceRunDuration() {
		return sliceRunDuration;
	}

	public void setSliceRunDuration(Integer sliceRunDuration) {
		this.sliceRunDuration = sliceRunDuration;
	}
	
	public Integer getSliceWorkDuration() {
		return sliceWorkDuration;
	}

	public void setSliceWorkDuration(Integer sliceWorkDuration) {
		this.sliceWorkDuration = sliceWorkDuration;
	}
	
	public Integer getRunOffCount() {
		return runOffCount;
	}

	public void setRunOffCount(Integer runOffCount) {
		this.runOffCount = runOffCount;
	}
	
	public Double getOilSum() {
		return oilSum;
	}

	public void setOilSum(Double oilSum) {
		this.oilSum = oilSum;
	}
	
	public Double getOilAvg() {
		return oilAvg;
	}

	public void setOilAvg(Double oilAvg) {
		this.oilAvg = oilAvg;
	}
	
	public Integer getRotationlSpeedMax() {
		return rotationlSpeedMax;
	}

	public void setRotationlSpeedMax(Integer rotationlSpeedMax) {
		this.rotationlSpeedMax = rotationlSpeedMax;
	}
	
	public Double getLatitudes() {
		return latitudes;
	}

	public void setLatitudes(Double latitudes) {
		this.latitudes = latitudes;
	}
	
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	@Length(min=0, max=20, message="省份长度必须介于 0 和 20 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=20, message="城市长度必须介于 0 和 20 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=200, message="地址长度必须介于 0 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=200, message="行政位置长度必须介于 0 和 200 之间")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public Integer getAlarmCount() {
		return alarmCount;
	}

	public void setAlarmCount(Integer alarmCount) {
		this.alarmCount = alarmCount;
	}
	
}