/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.transporthistory;

import java.util.Date;

import javax.persistence.Transient;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 机器运输记录Entity
 * @author lidong
 * @version 2016-07-21
 */
public class LgMachineTransportHistory extends DataEntity<LgMachineTransportHistory> {
	
	private static final long serialVersionUID = 1L;
	private String deviceNo;		// 整机编号
	private Date transportDate;		// 承运日期
	private String transportUnit;		// 承运单位
	@Transient
	private String transportUnitName;
	private Date expectedDeliveryDate;		// 预计交车时间
	private Date actualDeliveryDate;		// 实际交车时间
	private String carrier;		// 承运人
	private String carrierPhone;		// 联系方式
	private String vehicleNo;		// 运输车牌照号
	private String purchaserUnit;		// 收货单位
	@Transient
	private String purchaserUnitName;
	private String purchaserAddress;		// 收货地址
	private String purchaser;		// 收货人
	private String purchaserPhone;		// 收货联系方式
	private String overflag;		// 运输结束标志 0-没结束 1-结束
	
	@Transient
	private String productType;
	@Transient
	private String orderNumber;
	@Transient
	private Double transportLng;
	@Transient
	private Double transportLat;
	@Transient
    private Double purchaserLng;
    @Transient
    private Double purchaserLat;
	@Transient
	private String[] deviceNoMult;
	@Transient
    private String[] transportUnitMult;
	@Transient
    private String[] purchaserUnitMult;
	@Transient
    private Date transportDateBegin;
	@Transient
    private Date transportDateEnd;
	
	public LgMachineTransportHistory() {
		super();
	}

	public LgMachineTransportHistory(String id){
		super(id);
	}

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Date getTransportDate() {
        return transportDate;
    }

    public void setTransportDate(Date transportDate) {
        this.transportDate = transportDate;
    }

    public String getTransportUnit() {
        return transportUnit;
    }

    public void setTransportUnit(String transportUnit) {
        this.transportUnit = transportUnit;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public Date getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(Date actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCarrierPhone() {
        return carrierPhone;
    }

    public void setCarrierPhone(String carrierPhone) {
        this.carrierPhone = carrierPhone;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getPurchaserUnit() {
        return purchaserUnit;
    }

    public void setPurchaserUnit(String purchaserUnit) {
        this.purchaserUnit = purchaserUnit;
    }

    public String getPurchaserAddress() {
        return purchaserAddress;
    }

    public void setPurchaserAddress(String purchaserAddress) {
        this.purchaserAddress = purchaserAddress;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public String getPurchaserPhone() {
        return purchaserPhone;
    }

    public void setPurchaserPhone(String purchaserPhone) {
        this.purchaserPhone = purchaserPhone;
    }

    public String getOverflag() {
        return overflag;
    }

    public void setOverflag(String overflag) {
        this.overflag = overflag;
    }

    public String[] getDeviceNoMult() {
        return deviceNoMult;
    }

    public void setDeviceNoMult(String[] deviceNoMult) {
        this.deviceNoMult = deviceNoMult;
    }

    public String[] getTransportUnitMult() {
        return transportUnitMult;
    }

    public void setTransportUnitMult(String[] transportUnitMult) {
        this.transportUnitMult = transportUnitMult;
    }

    public String[] getPurchaserUnitMult() {
        return purchaserUnitMult;
    }

    public void setPurchaserUnitMult(String[] purchaserUnitMult) {
        this.purchaserUnitMult = purchaserUnitMult;
    }

    public Date getTransportDateBegin() {
        return transportDateBegin;
    }

    public void setTransportDateBegin(Date transportDateBegin) {
        this.transportDateBegin = transportDateBegin;
    }

    public Date getTransportDateEnd() {
        return transportDateEnd;
    }

    public void setTransportDateEnd(Date transportDateEnd) {
        this.transportDateEnd = transportDateEnd;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getTransportLng() {
        return transportLng;
    }

    public void setTransportLng(Double transportLng) {
        this.transportLng = transportLng;
    }

    public Double getTransportLat() {
        return transportLat;
    }

    public void setTransportLat(Double transportLat) {
        this.transportLat = transportLat;
    }

    public Double getPurchaserLng() {
        return purchaserLng;
    }

    public void setPurchaserLng(Double purchaserLng) {
        this.purchaserLng = purchaserLng;
    }

    public Double getPurchaserLat() {
        return purchaserLat;
    }

    public void setPurchaserLat(Double purchaserLat) {
        this.purchaserLat = purchaserLat;
    }

    public String getTransportUnitName() {
        return transportUnitName;
    }

    public void setTransportUnitName(String transportUnitName) {
        this.transportUnitName = transportUnitName;
    }

    public String getPurchaserUnitName() {
        return purchaserUnitName;
    }

    public void setPurchaserUnitName(String purchaserUnitName) {
        this.purchaserUnitName = purchaserUnitName;
    }
	
}