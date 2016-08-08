package com.k2data.platform.modules.lg.entity.HttpUrlVO;

import java.util.List;

/**
 * 获取接口设备信息的类
 * @author chenjingsi
 * @version 2016-05-09
 */
public class HttpUrlDeviceNo2{
	
	private String id;
	private String url;
	private List<HttpUrlDeviceNo3> deviceType;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<HttpUrlDeviceNo3> getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(List<HttpUrlDeviceNo3> deviceType) {
		this.deviceType = deviceType;
	}
	
	
	
	
	
	
}