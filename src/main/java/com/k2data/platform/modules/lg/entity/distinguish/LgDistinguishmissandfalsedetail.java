/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.distinguish;

import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 虚漏报方案配置详细Entity
 * @author chenjingsi
 * @version 2016-06-29
 */
public class LgDistinguishmissandfalsedetail extends DataEntity<LgDistinguishmissandfalsedetail> {
	
	private static final long serialVersionUID = 1L;
	private String distinguishId;		// 配置ID
	private String condition;		// 筛选条件1:开机时长 2：开机次数 3：总油耗  4：一次开机最长工作时间 5：回传频率 6：地理位置 7：报警回传数 8：已售天数 9：1400-1800 转速累计时长 10：行驶速度
	private String option;		// 选项1:大于，2:小于
	private Double value1;		// value1
	private Integer value2;		// value2
	private Integer isValid;		// 有效标识
	
	public LgDistinguishmissandfalsedetail() {
		super();
	}

	public LgDistinguishmissandfalsedetail(String id){
		super(id);
	}

	@Length(min=0, max=64, message="配置ID长度必须介于 0 和 64 之间")
	public String getDistinguishId() {
		return distinguishId;
	}

	public void setDistinguishId(String distinguishId) {
		this.distinguishId = distinguishId;
	}
	
	@Length(min=0, max=2, message="筛选条件1:开机时长 2：开机次数 3：总油耗  4：一次开机最长工作时间 5：回传频率 6：地理位置 7：报警回传数 8：已售天数 9：1400-1800 转速累计时长 10：行驶速度长度必须介于 0 和 2 之间")
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	@Length(min=0, max=1, message="选项1:大于，2:小于长度必须介于 0 和 1 之间")
	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
	
	public Double getValue1() {
		return value1;
	}

	public void setValue1(Double value1) {
		this.value1 = value1;
	}
	
	public Integer getValue2() {
		return value2;
	}

	public void setValue2(Integer value2) {
		this.value2 = value2;
	}
	
	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	
}