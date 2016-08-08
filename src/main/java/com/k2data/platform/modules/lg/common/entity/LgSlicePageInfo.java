/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.common.entity;

/**
 * 切片查询数据分页信息
 * @author K2DATA.wsguo
 * @date Jul 18, 2016 5:44:17 PM    
 * 
 */
public class LgSlicePageInfo {
	
	private int pageSize;
	private int size;
	private int pageNum;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	

	
}
