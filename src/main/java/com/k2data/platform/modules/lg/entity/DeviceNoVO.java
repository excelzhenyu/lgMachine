/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.k2data.platform.common.persistence.DataEntity;
import com.k2data.platform.common.utils.excel.annotation.ExcelField;

/**
 * 供导入功能使用的VO类
 * @author chenjingsi
 * @version 2016-05-09
 */
public class DeviceNoVO{
	
	@ExcelField(title="no", align=2, sort=40)
	private String no;
	@ExcelField(title="deviceNo", align=2, sort=40)
	private String deviceNo;
	
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	
	
	
}