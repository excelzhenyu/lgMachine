package com.k2data.platform.modules.lg.entity.HttpUrlVO;

import java.util.List;

/**
 * 获取接口设备信息的类
 * @author chenjingsi
 * @version 2016-05-09
 */
public class HttpUrlDeviceNo1{
	
	private String count;
	private String total;
	private String page;
	private String size;
	private List<HttpUrlDeviceNo2> data;
	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public List<HttpUrlDeviceNo2> getData() {
		return data;
	}
	public void setData(List<HttpUrlDeviceNo2> data) {
		this.data = data;
	}
	
	
	
}