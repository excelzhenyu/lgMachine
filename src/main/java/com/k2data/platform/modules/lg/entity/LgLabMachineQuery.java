/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.k2data.platform.common.persistence.DataEntity;
import com.k2data.platform.common.utils.excel.annotation.ExcelField;

/**
 * 试验机查询Entity
 * @author chenjingsi
 * @version 2016-05-30
 */
public class LgLabMachineQuery extends DataEntity<LgLabMachineQuery> {
	
	private static final long serialVersionUID = 1L;
	private int[] machineType;
	private Date startDate;
	private Date endDate;
	private List<String> machineNo;
	private String batchNumber;
	private String workTimeDaily;
	private String workTimeTotal;
	
	
	private String deviceNo;		// 整机对外发布的编码
	private Date workDate;		// 工作日期
	private String sliceWorkDuration;		// 工作时长
	private String runoffCount;		// 开机次数
	private String runDurationTotal;	//累计开机时长
	private String runOffTotal;	//累计开机次数
	private String rotationlSpeedMax;		// 最高转速
	private String position;		// 行政位置
	private String alarmCount; //报警次数
	private String modelNumber;//机器型号
	private String saleUnit;//经销商
	private String province;
	private String city;
	private MultipartFile xlsFile;
	
	public int[] getMachineType() {
		return machineType;
	}
	public void setMachineType(int[] machineType) {
		this.machineType = machineType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<String> getMachineNo() {
		return machineNo;
	}
	public void setMachineNo(List<String> machineNo) {
		this.machineNo = machineNo;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getWorkTimeDaily() {
		return workTimeDaily;
	}
	public void setWorkTimeDaily(String workTimeDaily) {
		this.workTimeDaily = workTimeDaily;
	}
	public String getWorkTimeTotal() {
		return workTimeTotal;
	}
	public void setWorkTimeTotal(String workTimeTotal) {
		this.workTimeTotal = workTimeTotal;
	}
	@ExcelField(title="机器编号",align=2,sort=1)
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	@ExcelField(title="工作日期",align=2,sort=10)
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	@ExcelField(title="工作时长",align=2,sort=20)
	public String getSliceWorkDuration() {
		return sliceWorkDuration;
	}
	public void setSliceWorkDuration(String sliceWorkDuration) {
		this.sliceWorkDuration = sliceWorkDuration;
	}
	@ExcelField(title="开机次数",align=2,sort=30)
	public String getRunoffCount() {
		return runoffCount;
	}
	public void setRunoffCount(String runoffCount) {
		this.runoffCount = runoffCount;
	}
	@ExcelField(title="累计开机时长",align=2,sort=40)
	public String getRunDurationTotal() {
		return runDurationTotal;
	}
	public void setRunDurationTotal(String runDurationTotal) {
		this.runDurationTotal = runDurationTotal;
	}
	@ExcelField(title="累计开机次数",align=2,sort=50)
	public String getRunOffTotal() {
		return runOffTotal;
	}
	public void setRunOffTotal(String runOffTotal) {
		this.runOffTotal = runOffTotal;
	}
	@ExcelField(title="最高转速",align=2,sort=60)
	public String getRotationlSpeedMax() {
		return rotationlSpeedMax;
	}
	public void setRotationlSpeedMax(String rotationlSpeedMax) {
		this.rotationlSpeedMax = rotationlSpeedMax;
	}
	@ExcelField(title="行政位置",align=2,sort=70)
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	@ExcelField(title="报警次数",align=2,sort=80)
	public String getAlarmCount() {
		return alarmCount;
	}
	public void setAlarmCount(String alarmCount) {
		this.alarmCount = alarmCount;
	}
	@ExcelField(title="机器型号",align=2,sort=2)
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	@ExcelField(title="经销商",align=2,sort=3)
	public String getSaleUnit() {
		return saleUnit;
	}
	public void setSaleUnit(String saleUnit) {
		this.saleUnit = saleUnit;
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
	public MultipartFile getXlsFile() {
		return xlsFile;
	}
	public void setXlsFile(MultipartFile xlsFile) {
		this.xlsFile = xlsFile;
	}
	
}