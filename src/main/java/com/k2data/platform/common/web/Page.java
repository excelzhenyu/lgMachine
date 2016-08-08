package com.k2data.platform.common.web;

import java.util.ArrayList;
import java.util.List;

import com.k2data.platform.common.config.Global;

/**
 * 翻页类
 *  Page.java
 * @author hpt
 * @version 1.0
 * @since 2015-11
 */
public class Page<T> {
	private int page = 1; // 当前页码
	private int rows = Integer.valueOf(Global.getConfig("page.rows"));	// 页面条数
	private long total = 0;	// 总条数
	private List<T> list = new ArrayList<T>();
	
	private String message = "";
	
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	
	public int getRows() {
		return rows;
	}
	/**
	 * 设置页面大小
	 * 
	 * @param rows
	 */
	public void setRows(int rows) {
		this.rows = rows <= 0 ? 10 : rows;
	}

	public long getTotal() {
		return total;
	}
	/**
	 * 设置数据总数
	 * 
	 * @param total
	 */
	public void setTotal(long total) {
//		if (rows >= total) {
//			page = 1;
//		}
		this.total = total;
	}

	/**
	 * 获取本页数据对象列表
	 * 
	 * @return List<T>
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * 设置本页数据对象列表
	 * 
	 * @param list
	 */
	public Page<T> setList(List<T> list) {
		this.list = list;
		 initialize();
		return this;
	}

	/**
	 * 初始化参数
	 */
	public void initialize() {
		
	}
	/**
	 * 构造方法
	 * 
	 * @param page
	 *            当前页码
	 * @param rows
	 *            分页大小
	 */
	public Page(int page, int rows) {
		this(page, rows, 0);
	}
	
	/**
	 * 构造方法
	 * 
	 * @param page
	 *            当前页码
	 * @param rows
	 *            分页大小
	 * @param total
	 *            数据条数
	 */
	public Page(int page, int rows, long total) {
		this(page, rows, total, new ArrayList<T>());
	}

	/**
	 * 构造方法
	 * 
	 * @param page
	 *            当前页码
	 * @param rows
	 *            分页大小
	 * @param total
	 *            数据条数
	 * @param list
	 *            本页数据对象列表
	 */
	public Page(int page, int rows, long total, List<T> list) {
		this.setTotal(total);
		this.rows = rows;
		if(page>getTotalPage()){
			page = getTotalPage();
		}
		this.setPage(page);
		this.list = list;
	}
	
	public Page(){
		
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 *  SQLHelper  翻页拦截 FirstResult
	 * @return
	 */
	public int getFirstResult() {
		int firstResult = (getPage() - 1) * getRows();
		if (firstResult >= getTotal()  ) {
			firstResult = -1;
			rows = -1;
		}
		
		return firstResult;
	}

	/**
	 * SQLHelper 获取  MaxResults
	 */
	public int getMaxResults() {
		return getRows();
	}
	
	/**
	 * 总页数
	 * @return
	 */
	public int getTotalPage() {
		return (int) (total%rows==0?total/rows :(total/rows +1));
	}

}
