/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.entity;

import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 工作中心作业组Entity
 * @author lidong
 * @version 2016-06-16
 */
@Table(name="workcenter_group")
public class WorkcenterGroup extends DataEntity<WorkcenterGroup> {
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	private String name;		// 英文名
	private String description;		// 中文描述
	private String remark;		// 备注
	
	public WorkcenterGroup() {
		super();
	}

	public WorkcenterGroup(String id){
		super(id);
	}

	@Length(min=1, max=30, message="英文名长度必须介于 1 和 30 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=50, message="中文描述长度必须介于 1 和 50 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=40, message="备注长度必须介于 0 和 40 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}