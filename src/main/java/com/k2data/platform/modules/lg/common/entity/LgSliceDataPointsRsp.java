/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.common.entity;

import java.util.List;

/**
 * 
 * @author K2DATA.wsguo
 * @date Jul 18, 2016 6:14:42 PM    
 * 
 */
public class LgSliceDataPointsRsp {
	private List<LgSliceDataPoints> datePonits;
	private int code;
	private String message;
	
	public List<LgSliceDataPoints> getDatePonits() {
		return datePonits;
	}
	public void setDatePonits(List<LgSliceDataPoints> datePonits) {
		this.datePonits = datePonits;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
