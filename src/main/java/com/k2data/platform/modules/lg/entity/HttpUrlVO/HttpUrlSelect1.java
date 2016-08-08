package com.k2data.platform.modules.lg.entity.HttpUrlVO;

import java.util.List;

/**
 * 获取接口设备信息的类
 * @author chenjingsi
 * @version 2016-05-09
 */
public class HttpUrlSelect1{
	
	private List<HttpUrlSelect2> dataRows;
	private String code;
	private PageInfo pageInfo;
	private String message;
	
	
	public List<HttpUrlSelect2> getDataRows() {
		return dataRows;
	}
	public void setDataRows(List<HttpUrlSelect2> dataRows) {
		this.dataRows = dataRows;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
}