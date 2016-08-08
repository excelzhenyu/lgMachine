package com.k2data.platform.modules.workcenter.quartz.common;

/**
 * job 状态枚举
 */
public enum JobStatus {
	
	RUNNING(1),
	PAUSED(2),
	FAILURE(9);
	
	private Integer status;
	
	private JobStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
