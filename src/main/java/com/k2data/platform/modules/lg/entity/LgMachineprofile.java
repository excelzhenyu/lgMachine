/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 整机档案Entity
 * @author chenjingsi
 * @version 2016-05-04
 */
public class LgMachineprofile extends DataEntity<LgMachineprofile> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 整机编码
	private String pinCode;		// 整机PIN码
	private String shortName;		// 整机简称
	private String name;		// 整机全称
	private String enName;		// 英文名称
	private String modelNumber;		// 型号
	private String orderNumber;		// 订货号
	private String machineType;		// 整机类型
	private String certificationNumber;		// 合格证号
	private String machineStatus;		// 整机状态
	private String statusDetail;		// 状态说明
	private Date productDate;		// 生产日期
	private String serviceFileNumber;		// 服务档案号
	private Date fileCreatedate;		// 档案建立日期
	private String bookBuildingId;		// 建档单位ID
	private String saleAreaId;		// 销售区域ID
	private String saleType;		// 销售形式
	private String saleUnitId;		// 销售单位ID
	private Date saleDate;		// 销售日期
	private String customerId;		// 客户ID
	private String productFactoryId;		// 生产工厂ID
	private String batchNumber;		// 批次号
	private String productType;		// 整机生产类型
	private String smartTerminalOrNot;		// 是否安装智能终端
	private String transmitCode;		// 整机传输特征码
	private String stopServiceMark;		// 停止服务标志
	private String imgUrl; //整机图片路径
	private Integer labMark;//试验机标识
	private Date labStartTime;//试验机开始时间
	private Date labEndTime;//试验机结束时间
	private String jobWorkState; // 工作工况
	
	@Transient
    private String[] machineTypeMult;  // 主机类别多选
    @Transient
    private String[] productTypeMult;  // 主机型号多选
    @Transient
    private String[] orderNumberMult;  // 订货号多选
    @Transient
    private String[] workProvinceMult; // 地区多选
    @Transient
    private String[] workStateMult;    // 工作工况多选
    @Transient
    private Date saleDateGreaterOE; // 销售时间大于等于
    @Transient
    private Date saleDateLess;  // 销售时间小于
    @Transient
    private Date productDateLess;   // 生产时间小于
	
	public LgMachineprofile() {
		super();
	}

	public LgMachineprofile(String id){
		super(id);
	}

	@Length(min=0, max=40, message="整机编码长度必须介于 0 和 40 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=40, message="整机PIN码长度必须介于 0 和 40 之间")
	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
	@Length(min=0, max=40, message="整机简称长度必须介于 0 和 40 之间")
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	@Length(min=0, max=120, message="整机全称长度必须介于 0 和 120 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=40, message="英文名称长度必须介于 0 和 40 之间")
	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}
	
	@Length(min=0, max=20, message="型号长度必须介于 0 和 20 之间")
	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	
	@Length(min=0, max=20, message="订货号长度必须介于 0 和 20 之间")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	@Length(min=0, max=10, message="整机类型长度必须介于 0 和 10 之间")
	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	
	@Length(min=0, max=30, message="合格证号长度必须介于 0 和 30 之间")
	public String getCertificationNumber() {
		return certificationNumber;
	}

	public void setCertificationNumber(String certificationNumber) {
		this.certificationNumber = certificationNumber;
	}
	
	@Length(min=0, max=10, message="整机状态长度必须介于 0 和 10 之间")
	public String getMachineStatus() {
		return machineStatus;
	}

	public void setMachineStatus(String machineStatus) {
		this.machineStatus = machineStatus;
	}
	
	@Length(min=0, max=255, message="状态说明长度必须介于 0 和 255 之间")
	public String getStatusDetail() {
		return statusDetail;
	}

	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	
	@Length(min=0, max=20, message="服务档案号长度必须介于 0 和 20 之间")
	public String getServiceFileNumber() {
		return serviceFileNumber;
	}

	public void setServiceFileNumber(String serviceFileNumber) {
		this.serviceFileNumber = serviceFileNumber;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFileCreatedate() {
		return fileCreatedate;
	}

	public void setFileCreatedate(Date fileCreatedate) {
		this.fileCreatedate = fileCreatedate;
	}
	
	@Length(min=0, max=64, message="建档单位ID长度必须介于 0 和 64 之间")
	public String getBookBuildingId() {
		return bookBuildingId;
	}

	public void setBookBuildingId(String bookBuildingId) {
		this.bookBuildingId = bookBuildingId;
	}
	
	@Length(min=0, max=64, message="销售区域ID长度必须介于 0 和 64 之间")
	public String getSaleAreaId() {
		return saleAreaId;
	}

	public void setSaleAreaId(String saleAreaId) {
		this.saleAreaId = saleAreaId;
	}
	
	@Length(min=0, max=10, message="销售形式长度必须介于 0 和 10 之间")
	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	
	@Length(min=0, max=64, message="销售单位ID长度必须介于 0 和 64 之间")
	public String getSaleUnitId() {
		return saleUnitId;
	}

	public void setSaleUnitId(String saleUnitId) {
		this.saleUnitId = saleUnitId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	
	@Length(min=0, max=64, message="客户ID长度必须介于 0 和 64 之间")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	@Length(min=0, max=64, message="生产工厂ID长度必须介于 0 和 64 之间")
	public String getProductFactoryId() {
		return productFactoryId;
	}

	public void setProductFactoryId(String productFactoryId) {
		this.productFactoryId = productFactoryId;
	}
	
	@Length(min=0, max=20, message="批次号长度必须介于 0 和 20 之间")
	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	
	@Length(min=0, max=10, message="整机生产类型长度必须介于 0 和 10 之间")
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	@Length(min=0, max=1, message="是否安装智能终端长度必须介于 0 和 1 之间")
	public String getSmartTerminalOrNot() {
		return smartTerminalOrNot;
	}

	public void setSmartTerminalOrNot(String smartTerminalOrNot) {
		this.smartTerminalOrNot = smartTerminalOrNot;
	}
	
	@Length(min=0, max=30, message="整机传输特征码长度必须介于 0 和 30 之间")
	public String getTransmitCode() {
		return transmitCode;
	}

	public void setTransmitCode(String transmitCode) {
		this.transmitCode = transmitCode;
	}
	
	@Length(min=0, max=1, message="停止服务标志长度必须介于 0 和 1 之间")
	public String getStopServiceMark() {
		return stopServiceMark;
	}

	public void setStopServiceMark(String stopServiceMark) {
		this.stopServiceMark = stopServiceMark;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getLabMark() {
		return labMark;
	}

	public void setLabMark(Integer labMark) {
		this.labMark = labMark;
	}

	public Date getLabStartTime() {
		return labStartTime;
	}

	public void setLabStartTime(Date labStartTime) {
		this.labStartTime = labStartTime;
	}

	public Date getLabEndTime() {
		return labEndTime;
	}

	public void setLabEndTime(Date labEndTime) {
		this.labEndTime = labEndTime;
	}

    public Date getSaleDateLess() {
        return saleDateLess;
    }

    public void setSaleDateLess(Date saleDateLess) {
        this.saleDateLess = saleDateLess;
    }

    public Date getSaleDateGreaterOE() {
        return saleDateGreaterOE;
    }

    public void setSaleDateGreaterOE(Date saleDateGreaterOE) {
        this.saleDateGreaterOE = saleDateGreaterOE;
    }

    public String getJobWorkState() {
        return jobWorkState;
    }

    public void setJobWorkState(String jobWorkState) {
        this.jobWorkState = jobWorkState;
    }

    public String[] getMachineTypeMult() {
        return machineTypeMult;
    }

    public void setMachineTypeMult(String[] machineTypeMult) {
        this.machineTypeMult = machineTypeMult;
    }

    public String[] getProductTypeMult() {
        return productTypeMult;
    }

    public void setProductTypeMult(String[] productTypeMult) {
        this.productTypeMult = productTypeMult;
    }

    public String[] getOrderNumberMult() {
        return orderNumberMult;
    }

    public void setOrderNumberMult(String[] orderNumberMult) {
        this.orderNumberMult = orderNumberMult;
    }

    public String[] getWorkProvinceMult() {
        return workProvinceMult;
    }

    public void setWorkProvinceMult(String[] workProvinceMult) {
        this.workProvinceMult = workProvinceMult;
    }

    public String[] getWorkStateMult() {
        return workStateMult;
    }

    public void setWorkStateMult(String[] workStateMult) {
        this.workStateMult = workStateMult;
    }

    public Date getProductDateLess() {
        return productDateLess;
    }

    public void setProductDateLess(Date productDateLess) {
        this.productDateLess = productDateLess;
    }

}