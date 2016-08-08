/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 试验机工作时长分布Entity
 * @author chenjingsi
 * @version 2016-05-30
 */
public class LgWorktimeDistribution extends DataEntity<LgWorktimeDistribution> {
	
	private static final long serialVersionUID = 1L;
	private int[] machineType;
	private Date startDate;
	private Date endDate;
	private List<String> machineNo;
	private  int oto4Num;
	private  int fourToEight;
	private  int eightToTwelve;
	private  int twelveToSixteen;
	private  int sixteenToTwenty;
	private  int twentyTo24;
	private MultipartFile xlsFile;
	
	
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
	public int[] getMachineType() {
		return machineType;
	}
	public void setMachineType(int[] machineType) {
		this.machineType = machineType;
	}
	public int getOto4Num() {
		return oto4Num;
	}
	public void setOto4Num(int oto4Num) {
		this.oto4Num = oto4Num;
	}
	public int getFourToEight() {
		return fourToEight;
	}
	public void setFourToEight(int fourToEight) {
		this.fourToEight = fourToEight;
	}
	public int getTwelveToSixteen() {
		return twelveToSixteen;
	}
	public void setTwelveToSixteen(int twelveToSixteen) {
		this.twelveToSixteen = twelveToSixteen;
	}
	public int getSixteenToTwenty() {
		return sixteenToTwenty;
	}
	public void setSixteenToTwenty(int sixteenToTwenty) {
		this.sixteenToTwenty = sixteenToTwenty;
	}
	public int getTwentyTo24() {
		return twentyTo24;
	}
	public void setTwentyTo24(int twentyTo24) {
		this.twentyTo24 = twentyTo24;
	}
	public int getEightToTwelve() {
		return eightToTwelve;
	}
	public void setEightToTwelve(int eightToTwelve) {
		this.eightToTwelve = eightToTwelve;
	}
	public MultipartFile getXlsFile() {
		return xlsFile;
	}
	public void setXlsFile(MultipartFile xlsFile) {
		this.xlsFile = xlsFile;
	}


 	

	
}