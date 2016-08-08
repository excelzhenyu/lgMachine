package com.k2data.platform.modules.lg.entity;

import java.util.Date;
import java.util.List;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 实验机器选取明细查询Entity
 * @author wangshengguo
 *
 */
public class LgLabMachineProfileQuery extends DataEntity<LgLabMachineProfileQuery>  {

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
	
	private List<String> modelNumberList;		// 整机类型
	private List<String> bookBuildingIdList;		// 建档单位ID
	private List<String> saleAreaIdList;		// 销售区域ID
	private List<String> saleUnitIdList;		// 销售单位ID
	private List<String> customerIdList;		// 客户ID
	private List<String> productFactoryIdList;		// 生产工厂ID
	
	private String pickId; //选取ID
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getMachineType() {
		return machineType;
	}
	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	public String getCertificationNumber() {
		return certificationNumber;
	}
	public void setCertificationNumber(String certificationNumber) {
		this.certificationNumber = certificationNumber;
	}
	public String getMachineStatus() {
		return machineStatus;
	}
	public void setMachineStatus(String machineStatus) {
		this.machineStatus = machineStatus;
	}
	public String getStatusDetail() {
		return statusDetail;
	}
	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}
	public Date getProductDate() {
		return productDate;
	}
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	public String getServiceFileNumber() {
		return serviceFileNumber;
	}
	public void setServiceFileNumber(String serviceFileNumber) {
		this.serviceFileNumber = serviceFileNumber;
	}
	public Date getFileCreatedate() {
		return fileCreatedate;
	}
	public void setFileCreatedate(Date fileCreatedate) {
		this.fileCreatedate = fileCreatedate;
	}
	public String getBookBuildingId() {
		return bookBuildingId;
	}
	public void setBookBuildingId(String bookBuildingId) {
		this.bookBuildingId = bookBuildingId;
	}
	public String getSaleAreaId() {
		return saleAreaId;
	}
	public void setSaleAreaId(String saleAreaId) {
		this.saleAreaId = saleAreaId;
	}
	public String getSaleType() {
		return saleType;
	}
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	public String getSaleUnitId() {
		return saleUnitId;
	}
	public void setSaleUnitId(String saleUnitId) {
		this.saleUnitId = saleUnitId;
	}
	public Date getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getProductFactoryId() {
		return productFactoryId;
	}
	public void setProductFactoryId(String productFactoryId) {
		this.productFactoryId = productFactoryId;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getSmartTerminalOrNot() {
		return smartTerminalOrNot;
	}
	public void setSmartTerminalOrNot(String smartTerminalOrNot) {
		this.smartTerminalOrNot = smartTerminalOrNot;
	}
	public String getTransmitCode() {
		return transmitCode;
	}
	public void setTransmitCode(String transmitCode) {
		this.transmitCode = transmitCode;
	}
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
	public List<String> getModelNumberList() {
		return modelNumberList;
	}
	public void setModelNumberList(List<String> modelNumberList) {
		this.modelNumberList = modelNumberList;
	}
	public List<String> getBookBuildingIdList() {
		return bookBuildingIdList;
	}
	public void setBookBuildingIdList(List<String> bookBuildingIdList) {
		this.bookBuildingIdList = bookBuildingIdList;
	}
	public List<String> getSaleAreaIdList() {
		return saleAreaIdList;
	}
	public void setSaleAreaIdList(List<String> saleAreaIdList) {
		this.saleAreaIdList = saleAreaIdList;
	}
	public List<String> getSaleUnitIdList() {
		return saleUnitIdList;
	}
	public void setSaleUnitIdList(List<String> saleUnitIdList) {
		this.saleUnitIdList = saleUnitIdList;
	}
	public List<String> getCustomerIdList() {
		return customerIdList;
	}
	public void setCustomerIdList(List<String> customerIdList) {
		this.customerIdList = customerIdList;
	}
	public List<String> getProductFactoryIdList() {
		return productFactoryIdList;
	}
	public void setProductFactoryIdList(List<String> productFactoryIdList) {
		this.productFactoryIdList = productFactoryIdList;
	}
	public String getPickId() {
		return pickId;
	}
	public void setPickId(String pickId) {
		this.pickId = pickId;
	}

	

}