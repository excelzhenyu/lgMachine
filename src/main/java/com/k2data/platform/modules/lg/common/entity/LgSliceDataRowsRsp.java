/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.common.entity;

import java.util.List;

/**
 * 
 * @author K2DATA.wsguo
 * @date Jul 5, 2016 1:57:30 PM    
 * 
 */
public class LgSliceDataRowsRsp {

	private List<LgSliceDataRows> dataRows;
	private LgSlicePageInfo pageInfo;
	private int code;
	private String message;
	
	
	public List<LgSliceDataRows> getDataRows() {
		return dataRows;
	}
	public void setDataRows(List<LgSliceDataRows> dataRows) {
		this.dataRows = dataRows;
	}
	public LgSlicePageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(LgSlicePageInfo pageInfo) {
		this.pageInfo = pageInfo;
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
