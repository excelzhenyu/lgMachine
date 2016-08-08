/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.distribution;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.k2data.platform.common.persistence.DataEntity;

/**
 * 机器工作报告 实体类
 * @author K2DATA.wsguo
 * @date Jul 14, 2016 10:27:51 AM    
 * 
 */
public class LgMachineJobReportData extends DataEntity<LgMachineJobReportData>{

	private String deviceNo;		// 机器编号
	private String machineType;		// 机器类别
	private String modelNumber;		// 机器型号
	private String orderNumber;		// 订货号
	private Date workDate;		// 工作日期
	private Double oilAvg;		// 平均油耗
	private Integer sliceWorkDuration;		// 工作时长
	private Integer runoffCount;		// 当天开机次数
	private String city;		// 城市
	private String received;	//回款逾期
	private Integer onceRunDurationMax;	//单次最大时长
	private Integer onceRunDurationMIn;	//单次最小时长
	private Integer rotationlSpeedMax;		// 最高转速
	private Integer alarmCount; //报警次数
	private String jobWorkState; 	// 作业工况
	
	private Double latitudes;		// 维度
	private Double longitude;		// 经度
	private String province;		// 省份
	private String address;		// 地址
	private String position;		// 行政位置
	
	//查询条件
	private String deviceNos;
	private List<String> deviceNoList;
	private Date runDateBegin;
	private Date runDateEnd;
	
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getMachineType() {
		return machineType;
	}
	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public Double getOilAvg() {
		return oilAvg;
	}
	public void setOilAvg(Double oilAvg) {
		this.oilAvg = oilAvg;
	}
	public Integer getSliceWorkDuration() {
		return sliceWorkDuration;
	}
	public void setSliceWorkDuration(Integer sliceWorkDuration) {
		this.sliceWorkDuration = sliceWorkDuration;
	}
	public Integer getRunoffCount() {
		return runoffCount;
	}
	public void setRunoffCount(Integer runoffCount) {
		this.runoffCount = runoffCount;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getReceived() {
		return received;
	}
	public void setReceived(String received) {
		this.received = received;
	}
	public Integer getOnceRunDurationMax() {
		return onceRunDurationMax;
	}
	public void setOnceRunDurationMax(Integer onceRunDurationMax) {
		this.onceRunDurationMax = onceRunDurationMax;
	}
	public Integer getOnceRunDurationMIn() {
		return onceRunDurationMIn;
	}
	public void setOnceRunDurationMIn(Integer onceRunDurationMIn) {
		this.onceRunDurationMIn = onceRunDurationMIn;
	}
	public Integer getRotationlSpeedMax() {
		return rotationlSpeedMax;
	}
	public void setRotationlSpeedMax(Integer rotationlSpeedMax) {
		this.rotationlSpeedMax = rotationlSpeedMax;
	}
	public Integer getAlarmCount() {
		return alarmCount;
	}
	public void setAlarmCount(Integer alarmCount) {
		this.alarmCount = alarmCount;
	}
	public String getJobWorkState() {
		return jobWorkState;
	}
	public void setJobWorkState(String jobWorkState) {
		this.jobWorkState = jobWorkState;
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
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getRunDateBegin() {
		return runDateBegin;
	}
	public void setRunDateBegin(Date runDateBegin) {
		this.runDateBegin = runDateBegin;
	}
	public Date getRunDateEnd() {
		return runDateEnd;
	}
	public void setRunDateEnd(Date runDateEnd) {
		this.runDateEnd = runDateEnd;
	}
	
	public String getDeviceNos() {
		return deviceNos;
	}
	public void setDeviceNos(String deviceNos) {
		this.deviceNos = deviceNos;
		if(StringUtils.isNoneBlank(deviceNos)) {
			String[] deviceNoArr = deviceNos.split(",");
			deviceNoList = Arrays.asList(deviceNoArr);
		}
	}
	public List<String> getDeviceNoList() {
		return deviceNoList;
	}
	public void setDeviceNoList(List<String> deviceNoList) {
		this.deviceNoList = deviceNoList;
	}

	
}
