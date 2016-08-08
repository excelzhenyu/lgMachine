package com.k2data.platform.modules.lg.entity.slice;

import java.util.Date;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.k2data.platform.common.persistence.DataEntity;

public class LgSliceTempDataSection  extends DataEntity<LgSliceTempDataSection> {
	
	private static final long serialVersionUID = 1L;
	
	private String accStatus; //ACC状态
	private String deviceNum;	//设备号
	private Date recordTime;	//记录时间
	private String province;	//省份
	private String city;		//城市
	private String address;		//地址
	private Double latitudeNum;  //纬度
	private Double longitudeNum;//经度
	private Integer enginRotate; //发动机转速
	private Integer startTimes;	//启动次数
	
	@Transient
	private String machineType;	// 主机类别
	@Transient
	private String machineTypeName;	// 主机类别描述
	
	public String getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}
	public String getDeviceNum() {
		return deviceNum;
	}
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLatitudeNum() {
		return latitudeNum;
	}
	public void setLatitudeNum(Double latitudeNum) {
		this.latitudeNum = latitudeNum;
	}
	public Double getLongitudeNum() {
		return longitudeNum;
	}
	public void setLongitudeNum(Double longitudeNum) {
		this.longitudeNum = longitudeNum;
	}

	public Integer getEnginRotate() {
		return enginRotate;
	}
	public void setEnginRotate(Integer enginRotate) {
		this.enginRotate = enginRotate;
	}
	public Integer getStartTimes() {
		return startTimes;
	}
	public void setStartTimes(Integer startTimes) {
		this.startTimes = startTimes;
	}
	public String getMachineType() {
		return machineType;
	}
	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	public String getMachineTypeName() {
		return machineTypeName;
	}
	public void setMachineTypeName(String machineTypeName) {
		this.machineTypeName = machineTypeName;
	}

	
}
