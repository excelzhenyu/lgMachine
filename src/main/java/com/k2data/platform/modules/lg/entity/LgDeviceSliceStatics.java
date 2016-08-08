/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import java.util.Date;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 设备切片统计Entity
 * @author chenjingsi
 * @version 2016-05-20
 */
public class LgDeviceSliceStatics extends DataEntity<LgDeviceSliceStatics> {
	
	private static final long serialVersionUID = 1L;
	private Date insertTime;		// 写入时间
	private String deviceNo;		// 整机对外发布的编码
	private Integer sliceCount;		// 设备切片统计
	private Date sliceStart;		// 开始时间
	private Date sliceStop;		// 结束时间
	private Integer sliceRunDuration;		// 开机时长（分钟）
	private Integer sliceWorkDuration;		// 工作时长（分钟）
	private Double latitudes;		// 纬度（切片的最后时刻）
	private Double longitude;		// 经度
	private String province;		// 省份
	private String city;		// 城市
	private String address;		// 详细地址
	private String position;		// 行政位置
	private Integer alarmCount;		//报警次数
	@Transient
	private String machineType;	// 主机类别
	@Transient
	private String[] deviceNoMult;	// 主机多选
	@Transient
	private String orderNumber;	// 订货号
	@Transient
	private String productType;	// 主机型号
	@Transient
	private String[] dealerIdMult;	// 经销商 ID 多选
	@Transient
	private String[] workProvinceMult;	// 工作地域多选
	@Transient
	private String[] saleProvinceMult;	// 销售地域多选
	@Transient
	private Date resumeStart;	// 工作履历开始时间
	@Transient
	private Date resumeStop;	// 工作履历结束时间
	
	public LgDeviceSliceStatics() {
		super();
	}

	public LgDeviceSliceStatics(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	
	@Length(min=0, max=40, message="整机对外发布的编码长度必须介于 0 和 40 之间")
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	
	public Integer getSliceCount() {
		return sliceCount;
	}

	public void setSliceCount(Integer sliceCount) {
		this.sliceCount = sliceCount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSliceStart() {
		return sliceStart;
	}

	public void setSliceStart(Date sliceStart) {
		this.sliceStart = sliceStart;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSliceStop() {
		return sliceStop;
	}

	public void setSliceStop(Date sliceStop) {
		this.sliceStop = sliceStop;
	}
	
	public Integer getSliceRunDuration() {
		return sliceRunDuration;
	}

	public void setSliceRunDuration(Integer sliceRunDuration) {
		this.sliceRunDuration = sliceRunDuration;
	}
	
	public Integer getSliceWorkDuration() {
		return sliceWorkDuration;
	}

	public void setSliceWorkDuration(Integer sliceWorkDuration) {
		this.sliceWorkDuration = sliceWorkDuration;
	}
	
	public Double getLatitudes() {
		return latitudes;
	}

	public void setLatitudes(Double latitudes) {
		this.latitudes = latitudes;
	}
	
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	@Length(min=0, max=20, message="省份长度必须介于 0 和 20 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=20, message="城市长度必须介于 0 和 20 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=200, message="详细地址长度必须介于 0 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=200, message="行政位置长度必须介于 0 和 200 之间")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getResumeStart() {
		return resumeStart;
	}

	public void setResumeStart(Date resumeStart) {
		this.resumeStart = resumeStart;
	}

	public Date getResumeStop() {
		return resumeStop;
	}

	public void setResumeStop(Date resumeStop) {
		this.resumeStop = resumeStop;
	}

    public String[] getDeviceNoMult() {
        return deviceNoMult;
    }

    public void setDeviceNoMult(String[] deviceNoMult) {
        this.deviceNoMult = deviceNoMult;
    }

    public String[] getDealerIdMult() {
        return dealerIdMult;
    }

    public void setDealerIdMult(String[] dealerIdMult) {
        this.dealerIdMult = dealerIdMult;
    }

    public String[] getWorkProvinceMult() {
        return workProvinceMult;
    }

    public void setWorkProvinceMult(String[] workProvinceMult) {
        this.workProvinceMult = workProvinceMult;
    }

    public String[] getSaleProvinceMult() {
        return saleProvinceMult;
    }

    public void setSaleProvinceMult(String[] saleProvinceMult) {
        this.saleProvinceMult = saleProvinceMult;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

	public Integer getAlarmCount() {
		return alarmCount;
	}

	public void setAlarmCount(Integer alarmCount) {
		this.alarmCount = alarmCount;
	}
	
}