/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 开关量切片统计Entity
 * @author chenjingsi
 * @version 2016-05-20
 */
public class LgSliceStaticsSwitch extends DataEntity<LgSliceStaticsSwitch> {
	
	private static final long serialVersionUID = 1L;
	private Date insertTime;		// 设备切片ID
	private String sensorMark;
	private String deviceSliceId;		// 整机对外发布的编码
	private String deviceNo;		// 传感器（工况）编码
	private String sensorType;		// 传感器类别
	private String dataPointCount;		// 切片内传感器回传次数
	private Date sensorDataStart;		// 传感器首个数据点回传时间
	private Date sensorDataEnd;		// 传感器末尾数据点回传时间
	private String sensorDataDuration;		// 传感器回传时长（单位分钟）
	private Double sendFrequence;		// 发送频率（dataPointCount/sensorDataDuration）
	private Double changeCount;		// 高低电平切换次数
	private Double highCount;		// 高电平次数
	private Double highDurationMax;		// 高电平持续时间最大值
	private Double highDurationMin;		// 高电平持续时间最小值
	private Double highDurationAvg;		// 高电平持续时间平均值
	private Double highDuration5;		// 高电平持续时间切片内第5值
	private Double highDuration25;		// 高电平持续时间切片内第25值
	private Double highDuration50;		// 高电平持续时间切片内中位置
	private Double highDuration75;		// 高电平持续时间切片内第75值
	private Double highDuration95;		// 高电平持续时间切片内第95值
	private Double highDurationStandardStd;		// 高电平持续时间标准差
	private Double highDurationVarianceStd;		// 高电平持续时间方差
	private Double highMax;		// 高电平持续次数最大值
	private Double highMin;		// 高电平持续次数最小值
	private Double highAvg;		// 高电平持续次数最大值
	private Double high5;		// 高电平持续次数最大值
	private Double high25;		// 高电平持续次数切片内第25值
	private Double high50;		// 高电平持续次数切片内中位置
	private Double high75;		// 高电平持续次数切片内第75值
	private Double high95;		// 高电平持续次数切片内第95值
	private Double highStandardStd;		// 高电平持续次数标准差
	private Double highVarianceStd;		// 高电平持续次数方差
	private Double lowCount;		// 低电平次数
	private Double lowDurationMax;		// 低电平持续时间最大值
	private Double lowDurationMin;		// 低电平持续时间最小值
	private Double lowDurationAvg;		// 低电平持续时间平均值
	private Double lowDuration5;		// 切片内低电平持续时间5分位
	private Double lowDuration25;		// 切片内低电平持续时间25分位
	private Double lowDuration50;		// 切片内低电平持续时间中分位
	private Double lowDuration75;		// 切片内低电平持续时间75分位
	private Double lowDuration95;		// 切片内低电平持续时间95分位
	private Double lowDurationStandardStd;		// 低电平持续时间标准差
	private Double lowDurationVarianceStd;		// 低电平持续时间方差
	private Double lowMax;		// 低电平持续次数最大值
	private Double lowMin;		// 低电平持续次数最小值
	private Double lowAvg;		// 低电平持续次数平均值
	private Double low5;		// 切片内低电平持续次数5分位
	private Double low25;		// 切片内低电平持续次数25分位
	private Double low50;		// 切片内低电平持续次数中分位
	private Double low75;		// 切片内低电平持续次数75分位
	private Double low95;		// 切片内低电平持续次数95分位
	private Double lowStandardStd;		// 低电平持续次数标准差
	private Double lowVarianceStd;		// 低电平持续次数方差
	
	public LgSliceStaticsSwitch() {
		super();
	}

	public LgSliceStaticsSwitch(String id){
		super(id);
	}
	
	@Length(min=0, max=40, message="整机对外发布的编码长度必须介于 0 和 40 之间")
	public String getDeviceSliceId() {
		return deviceSliceId;
	}

	public void setDeviceSliceId(String deviceSliceId) {
		this.deviceSliceId = deviceSliceId;
	}
	
	@Length(min=0, max=40, message="传感器（工况）编码长度必须介于 0 和 40 之间")
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	
	@Length(min=0, max=1, message="传感器类别长度必须介于 0 和 1 之间")
	public String getSensorType() {
		return sensorType;
	}

	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}
	
	@Length(min=0, max=11, message="切片内传感器回传次数长度必须介于 0 和 11 之间")
	public String getDataPointCount() {
		return dataPointCount;
	}

	public void setDataPointCount(String dataPointCount) {
		this.dataPointCount = dataPointCount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSensorDataStart() {
		return sensorDataStart;
	}

	public void setSensorDataStart(Date sensorDataStart) {
		this.sensorDataStart = sensorDataStart;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSensorDataEnd() {
		return sensorDataEnd;
	}

	public void setSensorDataEnd(Date sensorDataEnd) {
		this.sensorDataEnd = sensorDataEnd;
	}
	
	@Length(min=0, max=11, message="传感器回传时长（单位分钟）长度必须介于 0 和 11 之间")
	public String getSensorDataDuration() {
		return sensorDataDuration;
	}

	public void setSensorDataDuration(String sensorDataDuration) {
		this.sensorDataDuration = sensorDataDuration;
	}
	
	public Double getSendFrequence() {
		return sendFrequence;
	}

	public void setSendFrequence(Double sendFrequence) {
		this.sendFrequence = sendFrequence;
	}
	
	public Double getChangeCount() {
		return changeCount;
	}

	public void setChangeCount(Double changeCount) {
		this.changeCount = changeCount;
	}
	
	public Double getHighCount() {
		return highCount;
	}

	public void setHighCount(Double highCount) {
		this.highCount = highCount;
	}
	
	public Double getHighDurationMax() {
		return highDurationMax;
	}

	public void setHighDurationMax(Double highDurationMax) {
		this.highDurationMax = highDurationMax;
	}
	
	public Double getHighDurationMin() {
		return highDurationMin;
	}

	public void setHighDurationMin(Double highDurationMin) {
		this.highDurationMin = highDurationMin;
	}
	
	public Double getHighDurationAvg() {
		return highDurationAvg;
	}

	public void setHighDurationAvg(Double highDurationAvg) {
		this.highDurationAvg = highDurationAvg;
	}
	
	public Double getHighDuration5() {
		return highDuration5;
	}

	public void setHighDuration5(Double highDuration5) {
		this.highDuration5 = highDuration5;
	}
	
	public Double getHighDuration25() {
		return highDuration25;
	}

	public void setHighDuration25(Double highDuration25) {
		this.highDuration25 = highDuration25;
	}
	
	public Double getHighDuration50() {
		return highDuration50;
	}

	public void setHighDuration50(Double highDuration50) {
		this.highDuration50 = highDuration50;
	}
	
	public Double getHighDuration75() {
		return highDuration75;
	}

	public void setHighDuration75(Double highDuration75) {
		this.highDuration75 = highDuration75;
	}
	
	public Double getHighDuration95() {
		return highDuration95;
	}

	public void setHighDuration95(Double highDuration95) {
		this.highDuration95 = highDuration95;
	}
	
	public Double getHighDurationStandardStd() {
		return highDurationStandardStd;
	}

	public void setHighDurationStandardStd(Double highDurationStandardStd) {
		this.highDurationStandardStd = highDurationStandardStd;
	}
	
	public Double getHighDurationVarianceStd() {
		return highDurationVarianceStd;
	}

	public void setHighDurationVarianceStd(Double highDurationVarianceStd) {
		this.highDurationVarianceStd = highDurationVarianceStd;
	}
	
	public Double getHighMax() {
		return highMax;
	}

	public void setHighMax(Double highMax) {
		this.highMax = highMax;
	}
	
	public Double getHighMin() {
		return highMin;
	}

	public void setHighMin(Double highMin) {
		this.highMin = highMin;
	}
	
	public Double getHighAvg() {
		return highAvg;
	}

	public void setHighAvg(Double highAvg) {
		this.highAvg = highAvg;
	}
	
	public Double getHigh5() {
		return high5;
	}

	public void setHigh5(Double high5) {
		this.high5 = high5;
	}
	
	public Double getHigh25() {
		return high25;
	}

	public void setHigh25(Double high25) {
		this.high25 = high25;
	}
	
	public Double getHigh50() {
		return high50;
	}

	public void setHigh50(Double high50) {
		this.high50 = high50;
	}
	
	public Double getHigh75() {
		return high75;
	}

	public void setHigh75(Double high75) {
		this.high75 = high75;
	}
	
	public Double getHigh95() {
		return high95;
	}

	public void setHigh95(Double high95) {
		this.high95 = high95;
	}
	
	public Double getHighStandardStd() {
		return highStandardStd;
	}

	public void setHighStandardStd(Double highStandardStd) {
		this.highStandardStd = highStandardStd;
	}
	
	public Double getHighVarianceStd() {
		return highVarianceStd;
	}

	public void setHighVarianceStd(Double highVarianceStd) {
		this.highVarianceStd = highVarianceStd;
	}
	
	public Double getLowCount() {
		return lowCount;
	}

	public void setLowCount(Double lowCount) {
		this.lowCount = lowCount;
	}
	
	public Double getLowDurationMax() {
		return lowDurationMax;
	}

	public void setLowDurationMax(Double lowDurationMax) {
		this.lowDurationMax = lowDurationMax;
	}
	
	public Double getLowDurationMin() {
		return lowDurationMin;
	}

	public void setLowDurationMin(Double lowDurationMin) {
		this.lowDurationMin = lowDurationMin;
	}
	
	public Double getLowDurationAvg() {
		return lowDurationAvg;
	}

	public void setLowDurationAvg(Double lowDurationAvg) {
		this.lowDurationAvg = lowDurationAvg;
	}
	
	public Double getLowDuration5() {
		return lowDuration5;
	}

	public void setLowDuration5(Double lowDuration5) {
		this.lowDuration5 = lowDuration5;
	}
	
	public Double getLowDuration25() {
		return lowDuration25;
	}

	public void setLowDuration25(Double lowDuration25) {
		this.lowDuration25 = lowDuration25;
	}
	
	public Double getLowDuration50() {
		return lowDuration50;
	}

	public void setLowDuration50(Double lowDuration50) {
		this.lowDuration50 = lowDuration50;
	}
	
	public Double getLowDuration75() {
		return lowDuration75;
	}

	public void setLowDuration75(Double lowDuration75) {
		this.lowDuration75 = lowDuration75;
	}
	
	public Double getLowDuration95() {
		return lowDuration95;
	}

	public void setLowDuration95(Double lowDuration95) {
		this.lowDuration95 = lowDuration95;
	}
	
	public Double getLowDurationStandardStd() {
		return lowDurationStandardStd;
	}

	public void setLowDurationStandardStd(Double lowDurationStandardStd) {
		this.lowDurationStandardStd = lowDurationStandardStd;
	}
	
	public Double getLowDurationVarianceStd() {
		return lowDurationVarianceStd;
	}

	public void setLowDurationVarianceStd(Double lowDurationVarianceStd) {
		this.lowDurationVarianceStd = lowDurationVarianceStd;
	}
	
	public Double getLowMax() {
		return lowMax;
	}

	public void setLowMax(Double lowMax) {
		this.lowMax = lowMax;
	}
	
	public Double getLowMin() {
		return lowMin;
	}

	public void setLowMin(Double lowMin) {
		this.lowMin = lowMin;
	}
	
	public Double getLowAvg() {
		return lowAvg;
	}

	public void setLowAvg(Double lowAvg) {
		this.lowAvg = lowAvg;
	}
	
	public Double getLow5() {
		return low5;
	}

	public void setLow5(Double low5) {
		this.low5 = low5;
	}
	
	public Double getLow25() {
		return low25;
	}

	public void setLow25(Double low25) {
		this.low25 = low25;
	}
	
	public Double getLow50() {
		return low50;
	}

	public void setLow50(Double low50) {
		this.low50 = low50;
	}
	
	public Double getLow75() {
		return low75;
	}

	public void setLow75(Double low75) {
		this.low75 = low75;
	}
	
	public Double getLow95() {
		return low95;
	}

	public void setLow95(Double low95) {
		this.low95 = low95;
	}
	
	public Double getLowStandardStd() {
		return lowStandardStd;
	}

	public void setLowStandardStd(Double lowStandardStd) {
		this.lowStandardStd = lowStandardStd;
	}
	
	public Double getLowVarianceStd() {
		return lowVarianceStd;
	}

	public void setLowVarianceStd(Double lowVarianceStd) {
		this.lowVarianceStd = lowVarianceStd;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public String getSensorMark() {
		return sensorMark;
	}

	public void setSensorMark(String sensorMark) {
		this.sensorMark = sensorMark;
	}
	
}