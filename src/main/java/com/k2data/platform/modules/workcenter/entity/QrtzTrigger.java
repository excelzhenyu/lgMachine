/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * Quartz 触发器Entity
 * @author lidong
 * @version 2016-05-31
 */
@Table(name="QRTZ_TRIGGERS")
public class QrtzTrigger extends DataEntity<QrtzTrigger> {
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	private String schedName;		// sched_name
	private String triggerName;		// trigger_name
	private String triggerGroup;		// trigger_group
	private String jobName;		// job_name
	private String jobGroup;		// job_group
	private String description;		// description
	private Long nextFireTime;		// next_fire_time
	private Long prevFireTime;		// prev_fire_time
	private String priority;		// priority
	private String triggerState;		// trigger_state
	private String triggerType;		// trigger_type
	private Long startTime;		// start_time
	private Long endTime;		// end_time
	private String calendarName;		// calendar_name
	private String misfireInstr;		// misfire_instr
	private String jobData;		// job_data
	private String cronExpression;
	
	/* ***************************************
	 * 查询条件
	 * *************************************** */
	@Transient
	private Long beginNextFireTime;
	@Transient
	private Long endNextFireTime;
	@Transient
	private Long beginPrevFireTime;
	@Transient
	private Long endPrevFireTime;
	@Transient
	private Long beginStartTime;
	@Transient
	private Long endStartTime;
	@Transient
	private Long beginEndTime;
	@Transient
	private Long endEndTime;
	
	public QrtzTrigger() {
		super();
	}

	@Length(min=1, max=120, message="sched_name长度必须介于 1 和 120 之间")
	public String getSchedName() {
		return schedName;
	}

	public void setSchedName(String schedName) {
		this.schedName = schedName;
	}
	
	@Length(min=1, max=200, message="trigger_name长度必须介于 1 和 200 之间")
	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	
	@Length(min=1, max=200, message="trigger_group长度必须介于 1 和 200 之间")
	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}
	
	@Length(min=1, max=200, message="job_name长度必须介于 1 和 200 之间")
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	@Length(min=1, max=200, message="job_group长度必须介于 1 和 200 之间")
	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	
	@Length(min=0, max=250, message="description长度必须介于 0 和 250 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(Long nextFireTime) {
		this.nextFireTime = nextFireTime;
	}
	
	public Long getPrevFireTime() {
		return prevFireTime;
	}

	public void setPrevFireTime(Long prevFireTime) {
		this.prevFireTime = prevFireTime;
	}
	
	@Length(min=0, max=11, message="priority长度必须介于 0 和 11 之间")
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	@Length(min=1, max=16, message="trigger_state长度必须介于 1 和 16 之间")
	public String getTriggerState() {
		return triggerState;
	}

	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}
	
	@Length(min=1, max=8, message="trigger_type长度必须介于 1 和 8 之间")
	public String getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}
	
	@NotNull(message="start_time不能为空")
	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	
	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=200, message="calendar_name长度必须介于 0 和 200 之间")
	public String getCalendarName() {
		return calendarName;
	}

	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}
	
	@Length(min=0, max=2, message="misfire_instr长度必须介于 0 和 2 之间")
	public String getMisfireInstr() {
		return misfireInstr;
	}

	public void setMisfireInstr(String misfireInstr) {
		this.misfireInstr = misfireInstr;
	}
	
	public String getJobData() {
		return jobData;
	}

	public void setJobData(String jobData) {
		this.jobData = jobData;
	}

	public Long getBeginNextFireTime() {
		return beginNextFireTime;
	}

	public void setBeginNextFireTime(Long beginNextFireTime) {
		this.beginNextFireTime = beginNextFireTime;
	}

	public Long getEndNextFireTime() {
		return endNextFireTime;
	}

	public void setEndNextFireTime(Long endNextFireTime) {
		this.endNextFireTime = endNextFireTime;
	}

	public Long getBeginPrevFireTime() {
		return beginPrevFireTime;
	}

	public void setBeginPrevFireTime(Long beginPrevFireTime) {
		this.beginPrevFireTime = beginPrevFireTime;
	}

	public Long getEndPrevFireTime() {
		return endPrevFireTime;
	}

	public void setEndPrevFireTime(Long endPrevFireTime) {
		this.endPrevFireTime = endPrevFireTime;
	}

	public Long getBeginStartTime() {
		return beginStartTime;
	}

	public void setBeginStartTime(Long beginStartTime) {
		this.beginStartTime = beginStartTime;
	}

	public Long getEndStartTime() {
		return endStartTime;
	}

	public void setEndStartTime(Long endStartTime) {
		this.endStartTime = endStartTime;
	}

	public Long getBeginEndTime() {
		return beginEndTime;
	}

	public void setBeginEndTime(Long beginEndTime) {
		this.beginEndTime = beginEndTime;
	}

	public Long getEndEndTime() {
		return endEndTime;
	}

	public void setEndEndTime(Long endEndTime) {
		this.endEndTime = endEndTime;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	
}