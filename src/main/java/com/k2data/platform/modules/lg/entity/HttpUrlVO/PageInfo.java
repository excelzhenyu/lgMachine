package com.k2data.platform.modules.lg.entity.HttpUrlVO;

import java.util.List;

/**
 * 获取接口设备信息的类
 * @author chenjingsi
 * @version 2016-05-09
 */
public class PageInfo{
	
	private String pageSize;
	private String size;
	private String pageNum;
	
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	
}