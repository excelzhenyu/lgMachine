package com.k2data.platform.modules.workcenter.quartz.common;

import java.io.Serializable;

/**
 * 信息拓展类，用于将一些 job 运行数据记录日志
 * 
 * @author lidong
 */
public class MessageExt implements Serializable {
	
	private static final long serialVersionUID = 6255931749869633983L;

	private Integer writeCount;	// 成功条数
	private Integer errorCount; // 失败条数
	private Exception exception; // 异常
	private Object data;	// job 间要传递的数据
	private String log;		// 日志
	
	public MessageExt() {
		this.writeCount = 0;
		this.errorCount = 0;
		this.exception = null;
		this.data = new Object();
	}
	
	/**
	 * @param writeCount 成功条数
	 * @param errorCount 失败条数
	 */
	public MessageExt(Integer writeCount, Integer errorCount) {
		this.writeCount = writeCount;
		this.errorCount = errorCount;
		this.exception = null;
	}
	
	/**
	 * @param writeCount 成功条数
	 * @param errorCount 失败条数
	 * @param log 日志
	 */
	public MessageExt(Integer writeCount, Integer errorCount, String log) {
		this.writeCount = writeCount;
		this.errorCount = errorCount;
		this.log = log;
	}
	
	/**
	 * @param writeCount 成功条数
	 * @param errorCount 失败条数
	 * @param data job 间要传递的数据
	 * @param log 日志
	 */
	public MessageExt(Integer writeCount, Integer errorCount, Object data, String log) {
		this.writeCount = writeCount;
		this.errorCount = errorCount;
		this.log = log;
		this.data = data;
	}
	
	public Integer getWriteCount() {
		return writeCount;
	}
	public void setWriteCount(Integer writeCount) {
		this.writeCount = writeCount;
	}
	public Integer getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}

}
