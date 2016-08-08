/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.common.enums;

/**
 * 传感器列表 Enum
 * @author K2DATA.wsguo
 * @date Jul 25, 2016 9:23:14 AM    
 * 
 */
public enum SensorStatusEnum {

	ACC_STATUS("1","10");

	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getClose() {
		return close;
	}
	public void setClose(String close) {
		this.close = close;
	}
	private SensorStatusEnum(String open, String close) {
		this.open = open;
		this.close = close;
	}
	
	private String open;	
	private String close;
	
}
