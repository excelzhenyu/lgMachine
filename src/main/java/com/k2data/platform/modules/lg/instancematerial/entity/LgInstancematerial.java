/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.instancematerial.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 实例物料Entity
 * @author lidong
 * @version 2016-05-16
 */
public class LgInstancematerial extends DataEntity<LgInstancematerial> {
	
	private static final long serialVersionUID = 1L;
	private String ledgerid;		// 中性物料ID
	private String ledgerName;		// 中性物料 
	private String materialpin;		// 物料PIN码
	private String materialnum;		// 批次号
	private String supplierid;		// 供应商ID
	private String supplierName;	// 供应商
	private Date producetime;		// 生产日期
	private Date inboundtime;		// 入库日期
	private String suppliermaterialcode;		// 供应商物料编码
	private Date recivetime;		// 领料日期
	private Date deadtime;		// 报废日期
	private Date beginProducetime;		// 开始 生产日期
	private Date endProducetime;		// 结束 生产日期
	
	public LgInstancematerial() {
		super();
	}

	public LgInstancematerial(String id){
		super(id);
	}

	@Length(min=0, max=64, message="中性物料ID长度必须介于 0 和 64 之间")
	public String getLedgerid() {
		return ledgerid;
	}

	public void setLedgerid(String ledgerid) {
		this.ledgerid = ledgerid;
	}
	
	@Length(min=0, max=40, message="物料PIN码长度必须介于 0 和 40 之间")
	public String getMaterialpin() {
		return materialpin;
	}

	public void setMaterialpin(String materialpin) {
		this.materialpin = materialpin;
	}
	
	@Length(min=0, max=20, message="批次号长度必须介于 0 和 20 之间")
	public String getMaterialnum() {
		return materialnum;
	}

	public void setMaterialnum(String materialnum) {
		this.materialnum = materialnum;
	}
	
	@Length(min=0, max=64, message="供应商ID长度必须介于 0 和 64 之间")
	public String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProducetime() {
		return producetime;
	}

	public void setProducetime(Date producetime) {
		this.producetime = producetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInboundtime() {
		return inboundtime;
	}

	public void setInboundtime(Date inboundtime) {
		this.inboundtime = inboundtime;
	}
	
	@Length(min=0, max=40, message="供应商物料编码长度必须介于 0 和 40 之间")
	public String getSuppliermaterialcode() {
		return suppliermaterialcode;
	}

	public void setSuppliermaterialcode(String suppliermaterialcode) {
		this.suppliermaterialcode = suppliermaterialcode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRecivetime() {
		return recivetime;
	}

	public void setRecivetime(Date recivetime) {
		this.recivetime = recivetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDeadtime() {
		return deadtime;
	}

	public void setDeadtime(Date deadtime) {
		this.deadtime = deadtime;
	}
	
	public Date getBeginProducetime() {
		return beginProducetime;
	}

	public void setBeginProducetime(Date beginProducetime) {
		this.beginProducetime = beginProducetime;
	}
	
	public Date getEndProducetime() {
		return endProducetime;
	}

	public void setEndProducetime(Date endProducetime) {
		this.endProducetime = endProducetime;
	}

	public String getLedgerName() {
		return ledgerName;
	}

	public void setLedgerName(String ledgerName) {
		this.ledgerName = ledgerName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
		
}