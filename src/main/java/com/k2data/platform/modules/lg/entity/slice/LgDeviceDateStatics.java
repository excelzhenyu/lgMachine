/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.slice;

import java.util.Date;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 设备每日工作统计Entity
 * @author wangshengguo
 * @version 2016-05-30
 */
public class LgDeviceDateStatics extends DataEntity<LgDeviceDateStatics> {
	
	private static final long serialVersionUID = 1L;
	private Date inserTtime;		// 写入时间
	private String deviceNo;		// 整机对外发布的编码
	private Date workDate;		// 工作日期
	private Integer workCount;		// 工作天数统计
	private Date workBegin;		// 当天初次开机时间
	private Date workStop;		// 当天最后一次工作结束时间
	private Integer sliceRunDuration;		// 开机时长
	private Integer sliceWorkDuration;		// 工作时长
	private Integer runoffCount;		// 当天开机次数

	private Integer runDurationTotal;	//累计开机时长
	private Integer workDurationTotal;	//累计工作时长
	private Integer runOffTotal;	//累计开机次数

	private Double oilSum;		// 总油耗
	private Double oilAvg;		// 平均油耗
	private Integer rotationlSpeedMax;		// 最高转速
	private Double latitudes;		// 维度
	private Double longitude;		// 经度
	private String province;		// 省份
	private String city;		// 城市
	private String address;		// 地址
	private String position;		// 行政位置
	private Integer alarmCount; //报警次数
	
	@Transient
	private String modelNumber;    // 机器型号
	@Transient
	private String orderNumber;    // 订货号
	@Transient
	private Integer inactiveCount; // 呆滞数量
	@Transient
	private String location;   // 位置
	@Transient
	private String saleUnit;   // 经销商
	@Transient
	private Date productDate;  // 生产时间
	@Transient
    private String[] machineTypeMult;  // 主机类别多选
    @Transient
    private String[] productTypeMult;  // 主机型号多选
    @Transient
    private String[] orderNumberMult;  // 订货号多选
    @Transient
    private String[] workProvinceMult; // 地区多选
    @Transient
    private String[] deviceNoMult;  // 设备号多选
    @Transient
    private Date productDateLess;   // 生产时间小于
    @Transient
    private Integer machineStatus;   // 机器状态
    @Transient
    private Date workDateGreaterOE;   // 工作时间大于等于
	
	public LgDeviceDateStatics() {
		super();
	}

	public LgDeviceDateStatics(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInserTtime() {
		return inserTtime;
	}

	public void setInserTtime(Date inserTtime) {
		this.inserTtime = inserTtime;
	}
	
	@Length(min=0, max=40, message="整机对外发布的编码长度必须介于 0 和 40 之间")
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	
	public Integer getWorkCount() {
		return workCount;
	}

	public void setWorkCount(Integer workCount) {
		this.workCount = workCount;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWorkBegin() {
		return workBegin;
	}

	public void setWorkBegin(Date workBegin) {
		this.workBegin = workBegin;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWorkStop() {
		return workStop;
	}

	public void setWorkStop(Date workStop) {
		this.workStop = workStop;
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
	
	public Integer getRunoffCount() {
		return runoffCount;
	}

	public void setRunoffCount(Integer runoffCount) {
		this.runoffCount = runoffCount;
	}
	
	public Integer getRunDurationTotal() {
		return runDurationTotal;
	}

	public void setRunDurationTotal(Integer runDurationTotal) {
		this.runDurationTotal = runDurationTotal;
	}

	public Integer getWorkDurationTotal() {
		return workDurationTotal;
	}

	public void setWorkDurationTotal(Integer workDurationTotal) {
		this.workDurationTotal = workDurationTotal;
	}

	public Integer getRunOffTotal() {
		return runOffTotal;
	}

	public void setRunOffTotal(Integer runOffTotal) {
		this.runOffTotal = runOffTotal;
	}
	
	public Double getOilSum() {
		return oilSum;
	}

	public void setOilSum(Double oilSum) {
		this.oilSum = oilSum;
	}
	
	public Double getOilAvg() {
		return oilAvg;
	}

	public void setOilAvg(Double oilAvg) {
		this.oilAvg = oilAvg;
	}
	
	public Integer getRotationlSpeedMax() {
		return rotationlSpeedMax;
	}

	public void setRotationlSpeedMax(Integer rotationlSpeedMax) {
		this.rotationlSpeedMax = rotationlSpeedMax;
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
	
	@Length(min=0, max=200, message="地址长度必须介于 0 和 200 之间")
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

	public Integer getAlarmCount() {
		return alarmCount;
	}

	public void setAlarmCount(Integer alarmCount) {
		this.alarmCount = alarmCount;
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

    public Date getProductDateLess() {
        return productDateLess;
    }

    public void setProductDateLess(Date productDateLess) {
        this.productDateLess = productDateLess;
    }

    public Integer getMachineStatus() {
        return machineStatus;
    }

    public void setMachineStatus(Integer machineStatus) {
        this.machineStatus = machineStatus;
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

    public Integer getInactiveCount() {
        return inactiveCount;
    }

    public void setInactiveCount(Integer inactiveCount) {
        this.inactiveCount = inactiveCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSaleUnit() {
        return saleUnit;
    }

    public void setSaleUnit(String saleUnit) {
        this.saleUnit = saleUnit;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getWorkDateGreaterOE() {
        return workDateGreaterOE;
    }

    public void setWorkDateGreaterOE(Date workDateGreaterOE) {
        this.workDateGreaterOE = workDateGreaterOE;
    }

    public String[] getDeviceNoMult() {
        return deviceNoMult;
    }

    public void setDeviceNoMult(String[] deviceNoMult) {
        this.deviceNoMult = deviceNoMult;
    }

}