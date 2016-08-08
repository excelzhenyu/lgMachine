/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.test.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 项目进度Entity
 * @author wangshengguo
 * @version 2016-05-09
 */
public class PmK2dataprojectprogress extends DataEntity<PmK2dataprojectprogress> {
	
	private static final long serialVersionUID = 1L;
	private String projectName;		// 项目名称
	private String functionName;		// 功能名称
	private String developer;		// 开发人
	private Date completionTime;		// 完成时间
	private String isTest;		// 是否测试
	private String isPush;		// 是否提交GIT
	private String isAccept;		// 是否验收
	private String remark;		// 备注
	
	public PmK2dataprojectprogress() {
		super();
	}

	public PmK2dataprojectprogress(String id){
		super(id);
	}

	@Length(min=1, max=60, message="项目名称长度必须介于 1 和 60 之间")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Length(min=1, max=60, message="功能名称长度必须介于 1 和 60 之间")
	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	@Length(min=1, max=60, message="开发人长度必须介于 1 和 60 之间")
	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(Date completionTime) {
		this.completionTime = completionTime;
	}
	
	@Length(min=1, max=1, message="是否测试长度必须介于 1 和 1 之间")
	public String getIsTest() {
		return isTest;
	}

	public void setIsTest(String isTest) {
		this.isTest = isTest;
	}
	
	@Length(min=1, max=1, message="是否提交GIT长度必须介于 1 和 1 之间")
	public String getIsPush() {
		return isPush;
	}

	public void setIsPush(String isPush) {
		this.isPush = isPush;
	}
	
	@Length(min=1, max=1, message="是否验收长度必须介于 1 和 1 之间")
	public String getIsAccept() {
		return isAccept;
	}

	public void setIsAccept(String isAccept) {
		this.isAccept = isAccept;
	}
	
	@Length(min=0, max=256, message="备注长度必须介于 0 和 256 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}