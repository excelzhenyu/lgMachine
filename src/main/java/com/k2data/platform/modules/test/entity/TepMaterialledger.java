/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.test.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 物料台账（中性物料）Entity
 * @author wangshengguo
 * @version 2016-05-05
 */
public class TepMaterialledger extends DataEntity<TepMaterialledger> {
	
	private static final long serialVersionUID = 1L;
	private String materialCode;		// 物料编码
	private String materialName;		// 物料名称
	private String materialShortname;		// 物料简称
	private String materialFullname;		// 物料全称
	private String materialEnName;		// 物料英文名称
	private String materialType;		// 规格型号
	private String materialClass;		// 物料类别
	private String madeMode;		// 制造方式
	private String mainDept;		// 主单位
	private String isValid;		// 有效标志
	private Date deadTime;		// 失效时间
	private String isNumManage;		// 批号管理
	private String serialNumberManage;		// 序列号管理
	
	public TepMaterialledger() {
		super();
	}

	public TepMaterialledger(String id){
		super(id);
	}

	@Length(min=1, max=20, message="物料编码长度必须介于 1 和 20 之间")
	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	
	@Length(min=1, max=60, message="物料名称长度必须介于 1 和 60 之间")
	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
	@Length(min=1, max=40, message="物料简称长度必须介于 1 和 40 之间")
	public String getMaterialShortname() {
		return materialShortname;
	}

	public void setMaterialShortname(String materialShortname) {
		this.materialShortname = materialShortname;
	}
	
	@Length(min=1, max=120, message="物料全称长度必须介于 1 和 120 之间")
	public String getMaterialFullname() {
		return materialFullname;
	}

	public void setMaterialFullname(String materialFullname) {
		this.materialFullname = materialFullname;
	}
	
	@Length(min=1, max=40, message="物料英文名称长度必须介于 1 和 40 之间")
	public String getMaterialEnName() {
		return materialEnName;
	}

	public void setMaterialEnName(String materialEnName) {
		this.materialEnName = materialEnName;
	}
	
	@Length(min=1, max=20, message="规格型号长度必须介于 1 和 20 之间")
	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	
	@Length(min=1, max=20, message="物料类别长度必须介于 1 和 20 之间")
	public String getMaterialClass() {
		return materialClass;
	}

	public void setMaterialClass(String materialClass) {
		this.materialClass = materialClass;
	}
	
	@Length(min=1, max=10, message="制造方式长度必须介于 1 和 10 之间")
	public String getMadeMode() {
		return madeMode;
	}

	public void setMadeMode(String madeMode) {
		this.madeMode = madeMode;
	}
	
	@Length(min=1, max=10, message="主单位长度必须介于 1 和 10 之间")
	public String getMainDept() {
		return mainDept;
	}

	public void setMainDept(String mainDept) {
		this.mainDept = mainDept;
	}
	
	@Length(min=1, max=1, message="有效标志长度必须介于 1 和 1 之间")
	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="失效时间不能为空")
	public Date getDeadTime() {
		return deadTime;
	}

	public void setDeadTime(Date deadTime) {
		this.deadTime = deadTime;
	}
	
	@Length(min=1, max=1, message="批号管理长度必须介于 1 和 1 之间")
	public String getIsNumManage() {
		return isNumManage;
	}

	public void setIsNumManage(String isNumManage) {
		this.isNumManage = isNumManage;
	}
	
	@Length(min=1, max=1, message="序列号管理长度必须介于 1 和 1 之间")
	public String getSerialNumberManage() {
		return serialNumberManage;
	}

	public void setSerialNumberManage(String serialNumberManage) {
		this.serialNumberManage = serialNumberManage;
	}
	
}