/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 切片处理日志Entity
 * @author lidong
 * @version 2016-05-25
 */
public class SliceLog extends DataEntity<SliceLog> {
	
	private static final long serialVersionUID = 1L;
	private Date insertTime;		// 写入时间
	private String workCenter;		// 作业ID
	private String jobName;		// 作业名称
	private Date start;		// 开始时间
	private String jobGroup;		// group
	private Date end;		// 结束时间
	private Integer writeCount;		// 写入记录数
	private Integer errorCount;		// 错误记录数
	private String log;		// 日志
	private Date beginInsertTime;		// 开始 写入时间
	private Date endInsertTime;		// 结束 写入时间
	private Date beginStart;		// 开始 开始时间
	private Date endStart;		// 结束 开始时间
	private Date beginEnd;		// 开始 结束时间
	private Date endEnd;		// 结束 结束时间
	
	public SliceLog() {
		super();
	}

	public SliceLog(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	
	@Length(min=0, max=64, message="作业ID长度必须介于 0 和 64 之间")
	public String getWorkCenter() {
		return workCenter;
	}

	public void setWorkCenter(String workCenter) {
		this.workCenter = workCenter;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
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
	
	@Length(min=0, max=1000, message="日志长度必须介于 0 和 1000 之间")
	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}
	
	public Date getBeginInsertTime() {
		return beginInsertTime;
	}

	public void setBeginInsertTime(Date beginInsertTime) {
		this.beginInsertTime = beginInsertTime;
	}
	
	public Date getEndInsertTime() {
		return endInsertTime;
	}

	public void setEndInsertTime(Date endInsertTime) {
		this.endInsertTime = endInsertTime;
	}
		
	public Date getBeginStart() {
		return beginStart;
	}

	public void setBeginStart(Date beginStart) {
		this.beginStart = beginStart;
	}
	
	public Date getEndStart() {
		return endStart;
	}

	public void setEndStart(Date endStart) {
		this.endStart = endStart;
	}
		
	public Date getBeginEnd() {
		return beginEnd;
	}

	public void setBeginEnd(Date beginEnd) {
		this.beginEnd = beginEnd;
	}
	
	public Date getEndEnd() {
		return endEnd;
	}

	public void setEndEnd(Date endEnd) {
		this.endEnd = endEnd;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
		
}