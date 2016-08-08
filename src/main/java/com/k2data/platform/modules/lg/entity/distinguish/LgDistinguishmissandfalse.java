/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.distinguish;

import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 虚漏报方案配置Entity
 * @author chenjingsi
 * @version 2016-06-29
 */
public class LgDistinguishmissandfalse extends DataEntity<LgDistinguishmissandfalse> {
	
	private static final long serialVersionUID = 1L;
	private String solutionType;		// 类型（1虚报2漏报）
	private String solutionName;		// 方案名称
	private String isValid;		// 有效标识
	private String remark;		// 备注
	
	public LgDistinguishmissandfalse() {
		super();
	}

	public LgDistinguishmissandfalse(String id){
		super(id);
	}

	@Length(min=0, max=1, message="类型（1虚报2漏报）长度必须介于 0 和 1 之间")
	public String getSolutionType() {
		return solutionType;
	}

	public void setSolutionType(String solutionType) {
		this.solutionType = solutionType;
	}
	
	@Length(min=0, max=40, message="方案名称长度必须介于 0 和 40 之间")
	public String getSolutionName() {
		return solutionName;
	}

	public void setSolutionName(String solutionName) {
		this.solutionName = solutionName;
	}
	
	@Length(min=0, max=1, message="有效标识长度必须介于 0 和 1 之间")
	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}