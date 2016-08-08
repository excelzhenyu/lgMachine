/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.entity;

import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * Quartz Job 生成方案Entity
 * @author lidong
 * @version 2016-06-12
 */
@Table(name="workcenter_gen_job")
public class WorkcenterGenJob extends DataEntity<WorkcenterGenJob> {
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	private String functionName;	// 功能名
	private String jobName;			// job 名
	private String jobGroup;		// job所属组
	private String rootJob;			// 是否是根节点
	private String cronExpression;	// cron 表达式
	private String functionAuthor;		// 生成功能作者
	
	@Transient
	private String packageName;	// 包名
	@Transient
	private String genFlag;	// 是否生成代码标志
	@Transient
	private Boolean replaceJavaFile;	// 是否替换 Java 文件标志
	@Transient
	private WorkcenterGenJob oldWorkcenterGenJob;	// 未修改前的状态，用于更新后对旧的生成文件进行操作
	
	public WorkcenterGenJob() {
		super();
	}

	public WorkcenterGenJob(String id){
		super(id);
	}

	@Length(min=0, max=20, message="生成job名长度必须介于 0 和 20 之间")
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	@Length(min=0, max=500, message="生成功能名长度必须介于 0 和 500 之间")
	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	@Length(min=0, max=100, message="生成功能作者长度必须介于 0 和 100 之间")
	public String getFunctionAuthor() {
		return functionAuthor;
	}

	public void setFunctionAuthor(String functionAuthor) {
		this.functionAuthor = functionAuthor;
	}

	public String getGenFlag() {
		return genFlag;
	}

	public void setGenFlag(String genFlag) {
		this.genFlag = genFlag;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getRootJob() {
		return rootJob;
	}

	public void setRootJob(String rootJob) {
		this.rootJob = rootJob;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Boolean getReplaceJavaFile() {
		return replaceJavaFile;
	}

	public void setReplaceJavaFile(Boolean replaceJavaFile) {
		this.replaceJavaFile = replaceJavaFile;
	}

	public WorkcenterGenJob getOldWorkcenterGenJob() {
		return oldWorkcenterGenJob;
	}

	public void setOldWorkcenterGenJob(WorkcenterGenJob oldWorkcenterGenJob) {
		this.oldWorkcenterGenJob = oldWorkcenterGenJob;
	}

}