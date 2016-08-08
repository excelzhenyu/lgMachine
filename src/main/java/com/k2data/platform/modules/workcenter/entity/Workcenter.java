/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.entity;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 工作中心Entity
 * @author lidong
 * @version 2016-05-20
 */
@Table(name="workcenter")
public class Workcenter extends DataEntity<Workcenter> {
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	private Date insertTime;		// 写入时间
	private String nameCn;		// 中文名
	private String name;		// 作业名称
	private String group;		// 作业所属组
	private String path;		// 访问路径
	private Date start;		// 开始时间
	private Date end;		// 结束时间
	private String parentId;		// 父级作业
	private Integer status;		// 作业状态 1.工作 2.休息 9.异常
	private Long runCount;		// 运行总次数
	private String cronExpression;		// cron 表达式
	private Integer xAxis;	// x 轴位置
	private Integer yAxis;	// y 轴位置
	
	@Transient
	private String groupCn;	// 中文组名
	@Transient
	private Date beginInsertTime;		// 开始 写入时间
	@Transient
	private Date endInsertTime;		// 结束 写入时间
	@Transient
	private Date beginStart;		// 开始 开始时间
	@Transient
	private Date endStart;		// 结束 开始时间
	@Transient
	private Date beginEnd;		// 开始 结束时间
	@Transient
	private Date endEnd;		// 结束 结束时间
	@Transient
	private String parentName;	// 父节点名字
	@Transient
	private String parentGroup;	// 父节点组
	
	/* 根节点页面字段 */
	@Transient
	private Integer jobCount;	// job 总数
	@Transient
	private Integer useJobCount;	// 已配置 job 数
	@Transient
	private Integer unuseJobCount;	// 未配置 job 数
	@Transient
	private Integer runningJobCount;	// 正常 job 数
	@Transient
	private Integer pauseJobCount;	// 暂停 job 数
	@Transient
	private Integer failureJobCount;	// 异常 job 数
	
	public Workcenter() {
		super();
		jobCount = 0;
		useJobCount = 0;
		unuseJobCount = 0;
		runningJobCount = 0;
		pauseJobCount = 0;
		failureJobCount = 0;
	}

	public Workcenter(String id){
		super(id);
		jobCount = 0;
		useJobCount = 0;
		unuseJobCount = 0;
		runningJobCount = 0;
		pauseJobCount = 0;
		failureJobCount = 0;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	
	@Length(min=0, max=40, message="作业名称长度必须介于 0 和 40 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="访问路径长度必须介于 0 和 100 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
	
	@Length(min=0, max=40, message="cron 表达式长度必须介于 0 和 40 之间")
	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentGroup() {
		return parentGroup;
	}

	public void setParentGroup(String parentGroup) {
		this.parentGroup = parentGroup;
	}

	public Long getRunCount() {
		return runCount;
	}

	public void setRunCount(Long runCount) {
		this.runCount = runCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Length(min=0, max=300, message="父级作业长度必须介于 0 和 300 之间")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getJobCount() {
		return jobCount;
	}

	public void setJobCount(Integer jobCount) {
		this.jobCount = jobCount;
	}

	public Integer getUseJobCount() {
		return useJobCount;
	}

	public void setUseJobCount(Integer useJobCount) {
		this.useJobCount = useJobCount;
	}

	public Integer getUnuseJobCount() {
		return unuseJobCount;
	}

	public void setUnuseJobCount(Integer unuseJobCount) {
		this.unuseJobCount = unuseJobCount;
	}

	public Integer getPauseJobCount() {
		return pauseJobCount;
	}

	public void setPauseJobCount(Integer pauseJobCount) {
		this.pauseJobCount = pauseJobCount;
	}

	public Integer getRunningJobCount() {
		return runningJobCount;
	}

	public void setRunningJobCount(Integer runningJobCount) {
		this.runningJobCount = runningJobCount;
	}

	public Integer getFailureJobCount() {
		return failureJobCount;
	}

	public void setFailureJobCount(Integer failureJobCount) {
		this.failureJobCount = failureJobCount;
	}

	public Integer getxAxis() {
		return xAxis;
	}

	public void setxAxis(Integer xAxis) {
		this.xAxis = xAxis;
	}

	public Integer getyAxis() {
		return yAxis;
	}

	public void setyAxis(Integer yAxis) {
		this.yAxis = yAxis;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getGroupCn() {
		return groupCn;
	}

	public void setGroupCn(String groupCn) {
		this.groupCn = groupCn;
	}
		
}