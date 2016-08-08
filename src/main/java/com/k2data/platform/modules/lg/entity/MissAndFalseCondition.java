/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;


/**
 * 虚报漏报条件的VO类
 * @author chenjingsi
 * @version 2016-05-09
 */
public class MissAndFalseCondition{
	
	private String runDurationTotal;
	private String runDurationTotalOption;
	
	private String runOffTotal;
	private String runOffTotalOption;
	
	private String oilSum;
	private String oilSumOption;
	
	private String alarmCount;
	private String alarmCountOption;
	
	private String yesterday;
	private String deviceNo;
	
	public String getRunDurationTotal() {
		return runDurationTotal;
	}
	public void setRunDurationTotal(String runDurationTotal) {
		this.runDurationTotal = runDurationTotal;
	}
	public String getRunDurationTotalOption() {
		return runDurationTotalOption;
	}
	public void setRunDurationTotalOption(String runDurationTotalOption) {
		this.runDurationTotalOption = runDurationTotalOption;
	}
	public String getRunOffTotal() {
		return runOffTotal;
	}
	public void setRunOffTotal(String runOffTotal) {
		this.runOffTotal = runOffTotal;
	}
	public String getRunOffTotalOption() {
		return runOffTotalOption;
	}
	public void setRunOffTotalOption(String runOffTotalOption) {
		this.runOffTotalOption = runOffTotalOption;
	}
	public String getOilSum() {
		return oilSum;
	}
	public void setOilSum(String oilSum) {
		this.oilSum = oilSum;
	}
	public String getOilSumOption() {
		return oilSumOption;
	}
	public void setOilSumOption(String oilSumOption) {
		this.oilSumOption = oilSumOption;
	}
	public String getAlarmCount() {
		return alarmCount;
	}
	public void setAlarmCount(String alarmCount) {
		this.alarmCount = alarmCount;
	}
	public String getAlarmCountOption() {
		return alarmCountOption;
	}
	public void setAlarmCountOption(String alarmCountOption) {
		this.alarmCountOption = alarmCountOption;
	}
	public String getYesterday() {
		return yesterday;
	}
	public void setYesterday(String yesterday) {
		this.yesterday = yesterday;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	
	
	
}