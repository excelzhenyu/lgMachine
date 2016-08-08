/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 虚报统计的VO类
 * @author chenjingsi
 * @version 2016-05-09
 */
public class LgFalseStatistics extends DataEntity<LgFalseStatistics>{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String startDate;
		private Integer machineType;
		private String modelNumber;
		private String deviceNo;
		
		private Integer falseNumbers;
		private Integer confirmNumbers;
		private Integer errorNumbers;
		private Integer unconfirmNumbers;
		
		private Integer wjjFalseNumbers;
		private Integer wjjConfirmNumbers;
		private Integer wjjErrorNumbers;
		private Integer wjjUnconfirmNumbers;
		
		private Integer zzjFalseNumbers;
		private Integer zzjConfirmNumbers;
		private Integer zzjErrorNumbers;
		private Integer zzjUnconfirmNumbers;
		
		private Integer totalFalseNumbers;
		private Integer totalConfirmNumbers;
		private Integer totalErrorNumbers;
		private Integer totalUnconfirmNumbers;
		private String machineTypeName;
		private String saleName;
		private Integer type;
		private Integer solutionType;
		
		
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getModelNumber() {
			return modelNumber;
		}
		public void setModelNumber(String modelNumber) {
			this.modelNumber = modelNumber;
		}
		public Integer getFalseNumbers() {
			return falseNumbers;
		}
		public void setFalseNumbers(Integer falseNumbers) {
			this.falseNumbers = falseNumbers;
		}
		public Integer getConfirmNumbers() {
			return confirmNumbers;
		}
		public void setConfirmNumbers(Integer confirmNumbers) {
			this.confirmNumbers = confirmNumbers;
		}
		public Integer getErrorNumbers() {
			return errorNumbers;
		}
		public void setErrorNumbers(Integer errorNumbers) {
			this.errorNumbers = errorNumbers;
		}
		public Integer getUnconfirmNumbers() {
			return unconfirmNumbers;
		}
		public void setUnconfirmNumbers(Integer unconfirmNumbers) {
			this.unconfirmNumbers = unconfirmNumbers;
		}
		public Integer getWjjFalseNumbers() {
			return wjjFalseNumbers;
		}
		public void setWjjFalseNumbers(Integer wjjFalseNumbers) {
			this.wjjFalseNumbers = wjjFalseNumbers;
		}
		public Integer getWjjConfirmNumbers() {
			return wjjConfirmNumbers;
		}
		public void setWjjConfirmNumbers(Integer wjjConfirmNumbers) {
			this.wjjConfirmNumbers = wjjConfirmNumbers;
		}
		public Integer getWjjErrorNumbers() {
			return wjjErrorNumbers;
		}
		public void setWjjErrorNumbers(Integer wjjErrorNumbers) {
			this.wjjErrorNumbers = wjjErrorNumbers;
		}
		public Integer getWjjUnconfirmNumbers() {
			return wjjUnconfirmNumbers;
		}
		public void setWjjUnconfirmNumbers(Integer wjjUnconfirmNumbers) {
			this.wjjUnconfirmNumbers = wjjUnconfirmNumbers;
		}
		public Integer getZzjFalseNumbers() {
			return zzjFalseNumbers;
		}
		public void setZzjFalseNumbers(Integer zzjFalseNumbers) {
			this.zzjFalseNumbers = zzjFalseNumbers;
		}
		public Integer getZzjConfirmNumbers() {
			return zzjConfirmNumbers;
		}
		public void setZzjConfirmNumbers(Integer zzjConfirmNumbers) {
			this.zzjConfirmNumbers = zzjConfirmNumbers;
		}
		public Integer getZzjErrorNumbers() {
			return zzjErrorNumbers;
		}
		public void setZzjErrorNumbers(Integer zzjErrorNumbers) {
			this.zzjErrorNumbers = zzjErrorNumbers;
		}
		public Integer getZzjUnconfirmNumbers() {
			return zzjUnconfirmNumbers;
		}
		public void setZzjUnconfirmNumbers(Integer zzjUnconfirmNumbers) {
			this.zzjUnconfirmNumbers = zzjUnconfirmNumbers;
		}
		public Integer getTotalFalseNumbers() {
			return totalFalseNumbers;
		}
		public void setTotalFalseNumbers(Integer totalFalseNumbers) {
			this.totalFalseNumbers = totalFalseNumbers;
		}
		public Integer getTotalConfirmNumbers() {
			return totalConfirmNumbers;
		}
		public void setTotalConfirmNumbers(Integer totalConfirmNumbers) {
			this.totalConfirmNumbers = totalConfirmNumbers;
		}
		public Integer getTotalErrorNumbers() {
			return totalErrorNumbers;
		}
		public void setTotalErrorNumbers(Integer totalErrorNumbers) {
			this.totalErrorNumbers = totalErrorNumbers;
		}
		public Integer getTotalUnconfirmNumbers() {
			return totalUnconfirmNumbers;
		}
		public void setTotalUnconfirmNumbers(Integer totalUnconfirmNumbers) {
			this.totalUnconfirmNumbers = totalUnconfirmNumbers;
		}
		public Integer getMachineType() {
			return machineType;
		}
		public void setMachineType(Integer machineType) {
			this.machineType = machineType;
		}
		public String getDeviceNo() {
			return deviceNo;
		}
		public void setDeviceNo(String deviceNo) {
			this.deviceNo = deviceNo;
		}
		public String getMachineTypeName() {
			return machineTypeName;
		}
		public void setMachineTypeName(String machineTypeName) {
			this.machineTypeName = machineTypeName;
		}
		public String getSaleName() {
			return saleName;
		}
		public void setSaleName(String saleName) {
			this.saleName = saleName;
		}
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
		}
		public Integer getSolutionType() {
			return solutionType;
		}
		public void setSolutionType(Integer solutionType) {
			this.solutionType = solutionType;
		}
		
		

}