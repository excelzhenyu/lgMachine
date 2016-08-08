/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.entity;

import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * Quartz JobEntity
 * @author lidong
 * @version 2016-06-01
 */
@Table(name="QRTZ_JOB_DETAILS")
public class QrtzJob extends DataEntity<QrtzJob> {
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	private String schedName;		// sched_name
	private String jobName;		// job_name
	private String jobGroup;		// job_group
	private String description;		// description
	private String jobClassName;		// job_class_name
	private String isDurable;		// is_durable
	private String isNonconcurrent;		// is_nonconcurrent
	private String isUpdateData;		// is_update_data
	private String requestsRecovery;		// requests_recovery
	private String jobData;		// job_data
	
	public QrtzJob() {
		super();
	}

	public QrtzJob(String id){
		super(id);
	}

	@Length(min=1, max=120, message="sched_name长度必须介于 1 和 120 之间")
	public String getSchedName() {
		return schedName;
	}

	public void setSchedName(String schedName) {
		this.schedName = schedName;
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
	
	@Length(min=1, max=250, message="job_class_name长度必须介于 1 和 250 之间")
	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}
	
	@Length(min=1, max=1, message="is_durable长度必须介于 1 和 1 之间")
	public String getIsDurable() {
		return isDurable;
	}

	public void setIsDurable(String isDurable) {
		this.isDurable = isDurable;
	}
	
	@Length(min=1, max=1, message="is_nonconcurrent长度必须介于 1 和 1 之间")
	public String getIsNonconcurrent() {
		return isNonconcurrent;
	}

	public void setIsNonconcurrent(String isNonconcurrent) {
		this.isNonconcurrent = isNonconcurrent;
	}
	
	@Length(min=1, max=1, message="is_update_data长度必须介于 1 和 1 之间")
	public String getIsUpdateData() {
		return isUpdateData;
	}

	public void setIsUpdateData(String isUpdateData) {
		this.isUpdateData = isUpdateData;
	}
	
	@Length(min=1, max=1, message="requests_recovery长度必须介于 1 和 1 之间")
	public String getRequestsRecovery() {
		return requestsRecovery;
	}

	public void setRequestsRecovery(String requestsRecovery) {
		this.requestsRecovery = requestsRecovery;
	}
	
	public String getJobData() {
		return jobData;
	}

	public void setJobData(String jobData) {
		this.jobData = jobData;
	}
	
}