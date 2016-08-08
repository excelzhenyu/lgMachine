/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 模拟量切片统计Entity
 * @author chenjingsi
 * @version 2016-05-20
 */
public class LgSliceStaticsAnalog extends DataEntity<LgSliceStaticsAnalog> {
	
	private static final long serialVersionUID = 1L;
	private Date insertTime;		// 写入时间
	private String deviceSliceId;		// 设备切片ID
	private String deviceNo;		// 整机对外发布的编码
	private String sensorNo;		// 传感器（工况）编码
	private Integer sensorType;		// 传感器类别（1：模拟量2：开关量3：数字量）
	private Integer dataPointCount;		// 切片内传感器回传次数
	private Date sensorDataStart;		// 传感器首个数据点回传时间
	private Date sensorDataEnd;		// 传感器末尾数据点回传时间
	private Integer sensorDataDuration;		// 传感器回传时长（分钟）
	private Double sendFrequence;		// 发送频率（dataPointCount/sensorDataDuration）
	private Double valueMax;		// 切片内最大值
	private Double valueMin;		// 切片内最小值
	private Double valueAvg;		// 切片内均值
	private Double value5;		// 切片内5分位
	private Double value25;		// 切片内25分位
	private Double value50;		// 切片内中分位
	private Double value75;		// 切片内75分位
	private Double value95;		// 切片内95分位
	private Double standardStd;		// 标准差
	private Double varianceStd;		// 方差
	private Double skewMax;		// 切片内偏度最大值
	private Double skewMin;		// 切片内偏度最小值
	private Double skewAvg;		// 切片内偏度均值
	private Double skew5;		// 切片内偏度5分位
	private Double skew25;		// 切片内偏度25分位
	private Double skew50;		// 切片内偏度中分位
	private Double skew75;		// 切片内偏度75分位
	private Double skew95;		// 切片内偏度95分位
	private Double abunMax;		// 切片内丰度最大值
	private Double abunMin;		// 切片内丰度最小值
	private Double abunAvg;		// 切片内丰度均值
	private Double abun5;		// 切片内丰度5分位
	private Double abun25;		// 切片内丰度25分位
	private Double abun50;		// 切片内丰度中分位
	private Double abun75;		// 切片内丰度75分位
	private Double abun95;		// 切片内丰度95分位
	private Double gradMax;		// 切片内梯度最大值
	private Double gradMin;		// 切片内梯度最小值
	private Double gradAvg;		// 切片内梯度均值
	private Double grad5;		// 切片内梯度5分位
	private Double grad25;		// 切片内梯度25分位
	private Double grad50;		// 切片内梯度中分位
	private Double grad75;		// 切片内梯度75分位
	private Double grad95;		// 切片内梯度95分位
	
	public LgSliceStaticsAnalog() {
		super();
	}

	public LgSliceStaticsAnalog(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	
	@Length(min=0, max=64, message="设备切片ID长度必须介于 0 和 64 之间")
	public String getDeviceSliceId() {
		return deviceSliceId;
	}

	public void setDeviceSliceId(String deviceSliceId) {
		this.deviceSliceId = deviceSliceId;
	}
	
	@Length(min=0, max=40, message="整机对外发布的编码长度必须介于 0 和 40 之间")
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	
	@Length(min=0, max=40, message="传感器（工况）编码长度必须介于 0 和 40 之间")
	public String getSensorNo() {
		return sensorNo;
	}

	public void setSensorNo(String sensorNo) {
		this.sensorNo = sensorNo;
	}
	
	public Integer getSensorType() {
		return sensorType;
	}

	public void setSensorType(Integer sensorType) {
		this.sensorType = sensorType;
	}
	
	public Integer getDataPointCount() {
		return dataPointCount;
	}

	public void setDataPointCount(Integer dataPointCount) {
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
	
	public Integer getSensorDataDuration() {
		return sensorDataDuration;
	}

	public void setSensorDataDuration(Integer sensorDataDuration) {
		this.sensorDataDuration = sensorDataDuration;
	}
	
	public Double getSendFrequence() {
		return sendFrequence;
	}

	public void setSendFrequence(Double sendFrequence) {
		this.sendFrequence = sendFrequence;
	}
	
	public Double getValueMax() {
		return valueMax;
	}

	public void setValueMax(Double valueMax) {
		this.valueMax = valueMax;
	}
	
	public Double getValueMin() {
		return valueMin;
	}

	public void setValueMin(Double valueMin) {
		this.valueMin = valueMin;
	}
	
	public Double getValueAvg() {
		return valueAvg;
	}

	public void setValueAvg(Double valueAvg) {
		this.valueAvg = valueAvg;
	}
	
	public Double getValue5() {
		return value5;
	}

	public void setValue5(Double value5) {
		this.value5 = value5;
	}
	
	public Double getValue25() {
		return value25;
	}

	public void setValue25(Double value25) {
		this.value25 = value25;
	}
	
	public Double getValue50() {
		return value50;
	}

	public void setValue50(Double value50) {
		this.value50 = value50;
	}
	
	public Double getValue75() {
		return value75;
	}

	public void setValue75(Double value75) {
		this.value75 = value75;
	}
	
	public Double getValue95() {
		return value95;
	}

	public void setValue95(Double value95) {
		this.value95 = value95;
	}
	
	public Double getStandardStd() {
		return standardStd;
	}

	public void setStandardStd(Double standardStd) {
		this.standardStd = standardStd;
	}
	
	public Double getVarianceStd() {
		return varianceStd;
	}

	public void setVarianceStd(Double varianceStd) {
		this.varianceStd = varianceStd;
	}
	
	public Double getSkewMax() {
		return skewMax;
	}

	public void setSkewMax(Double skewMax) {
		this.skewMax = skewMax;
	}
	
	public Double getSkewMin() {
		return skewMin;
	}

	public void setSkewMin(Double skewMin) {
		this.skewMin = skewMin;
	}
	
	public Double getSkewAvg() {
		return skewAvg;
	}

	public void setSkewAvg(Double skewAvg) {
		this.skewAvg = skewAvg;
	}
	
	public Double getSkew5() {
		return skew5;
	}

	public void setSkew5(Double skew5) {
		this.skew5 = skew5;
	}
	
	public Double getSkew25() {
		return skew25;
	}

	public void setSkew25(Double skew25) {
		this.skew25 = skew25;
	}
	
	public Double getSkew50() {
		return skew50;
	}

	public void setSkew50(Double skew50) {
		this.skew50 = skew50;
	}
	
	public Double getSkew75() {
		return skew75;
	}

	public void setSkew75(Double skew75) {
		this.skew75 = skew75;
	}
	
	public Double getSkew95() {
		return skew95;
	}

	public void setSkew95(Double skew95) {
		this.skew95 = skew95;
	}
	
	public Double getAbunMax() {
		return abunMax;
	}

	public void setAbunMax(Double abunMax) {
		this.abunMax = abunMax;
	}
	
	public Double getAbunMin() {
		return abunMin;
	}

	public void setAbunMin(Double abunMin) {
		this.abunMin = abunMin;
	}
	
	public Double getAbunAvg() {
		return abunAvg;
	}

	public void setAbunAvg(Double abunAvg) {
		this.abunAvg = abunAvg;
	}
	
	public Double getAbun5() {
		return abun5;
	}

	public void setAbun5(Double abun5) {
		this.abun5 = abun5;
	}
	
	public Double getAbun25() {
		return abun25;
	}

	public void setAbun25(Double abun25) {
		this.abun25 = abun25;
	}
	
	public Double getAbun50() {
		return abun50;
	}

	public void setAbun50(Double abun50) {
		this.abun50 = abun50;
	}
	
	public Double getAbun75() {
		return abun75;
	}

	public void setAbun75(Double abun75) {
		this.abun75 = abun75;
	}
	
	public Double getAbun95() {
		return abun95;
	}

	public void setAbun95(Double abun95) {
		this.abun95 = abun95;
	}
	
	public Double getGradMax() {
		return gradMax;
	}

	public void setGradMax(Double gradMax) {
		this.gradMax = gradMax;
	}
	
	public Double getGradMin() {
		return gradMin;
	}

	public void setGradMin(Double gradMin) {
		this.gradMin = gradMin;
	}
	
	public Double getGradAvg() {
		return gradAvg;
	}

	public void setGradAvg(Double gradAvg) {
		this.gradAvg = gradAvg;
	}
	
	public Double getGrad5() {
		return grad5;
	}

	public void setGrad5(Double grad5) {
		this.grad5 = grad5;
	}
	
	public Double getGrad25() {
		return grad25;
	}

	public void setGrad25(Double grad25) {
		this.grad25 = grad25;
	}
	
	public Double getGrad50() {
		return grad50;
	}

	public void setGrad50(Double grad50) {
		this.grad50 = grad50;
	}
	
	public Double getGrad75() {
		return grad75;
	}

	public void setGrad75(Double grad75) {
		this.grad75 = grad75;
	}
	
	public Double getGrad95() {
		return grad95;
	}

	public void setGrad95(Double grad95) {
		this.grad95 = grad95;
	}
	
}