/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity;

import org.hibernate.validator.constraints.Length;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 二级传感器分析表Entity
 * @author chenjingsi
 * @version 2016-05-30
 */
public class LgBaselineofdevice extends DataEntity<LgBaselineofdevice> {
	
	private static final long serialVersionUID = 1L;
	private String deviceNo;		// 设备编码
	private String sensorNo;		// sensorno
	private Integer sliceTotal;		// 设备开机切片数目
	private Integer sliceCount;		// 该传感器有回传值切片数目
	private Double average5;		// 平均值二级指标5分位（模拟量）
	private Double average25;		// 平均值二级指标25分位（模拟量）
	private Double average50;		// 平均值二级指标中分位（模拟量）
	private Double average75;		// 平均值二级指标75分位（模拟量）
	private Double average95;		// 平均值二级指标95分位（模拟量）
	private Double averageAvg;		// 平均值二级指标平均值（模拟量）
	private Double averageMax;		// 平均值二级指标最大值（模拟量）
	private Double averageMin;		// 平均值二级指标最小值（模拟量）
	private Double averageStd;		// 平均值二级指标标准差（模拟量）
	private Double max5;		// 最大值二级指标5分位（模拟量）
	private Double max25;		// 最大值二级指标25分位（模拟量）
	private Double max50;		// 最大值二级指标中分位（模拟量）
	private Double max75;		// 最大值二级指标75分位（模拟量）
	private Double max95;		// 最大值二级指标95分位（模拟量）
	private Double maxAvg;		// 最大值二级指标平均值（模拟量）
	private Double maxMax;		// 最大值二级指标最大值（模拟量）
	private Double maxMin;		// 最大值二级指标最小值（模拟量）
	private Double maxStd;		// 最大值二级指标标准差（模拟量）
	private Double min5;		// 最小值二级指标5分位（模拟量）
	private Double min25;		// 最小值二级指标25分位（模拟量）
	private Double min50;		// 最小值二级指标中分位（模拟量）
	private Double min75;		// 最小值二级指标75分位（模拟量）
	private Double min95;		// 最小值二级指标95分位（模拟量）
	private Double minAvg;		// 最小值二级指标平均值（模拟量）
	private Double minMax;		// 最小值二级指标最大值（模拟量）
	private Double minMin;		// 最小值二级指标最小值（模拟量）
	private Double minStd;		// 最小值二级指标标准差（模拟量）
	private Double countOfChange5;		// 取值切换次数二级指标5分位（开关量）
	private Double countOfChange25;		// 取值切换次数二级指标25分位（开关量）
	private Double countOfChange50;		// 取值切换次数二级指标中分位（开关量）
	private Double countOfChange75;		// 取值切换次数二级指标75分位（开关量）
	private Double countOfChange95;		// 取值切换次数二级指标95分位（开关量）
	private Double countOfChangeAvg;		// 取值切换次数二级指标平均值（开关量）
	private Double countOfChangeMin;		// 取值切换次数二级指标最小值（开关量）
	private Double countOfChangeMax;		// 取值切换次数二级指标最大值（开关量）
	private Double countOfChangeStd;		// 取值切换次数二级指标标准差（开关量）
	private Double countOfOne5;		// 高电平计数二级指标5分位（开关量）
	private Double countOfOne25;		// 高电平计数二级指标25分位（开关量）
	private Double countOfOne50;		// 高电平计数二级指标中分位（开关量）
	private Double countOfOne75;		// 高电平计数二级指标75分位（开关量）
	private Double countOfOne95;		// 高电平计数二级指标95分位（开关量）
	private Double countOfOneAvg;		// 高电平计数二级指标平均值（开关量）
	private Double countOfOneMax;		// 高电平计数二级指标最大值（开关量）
	private Double countOfOneMin;		// 高电平计数二级指标最小值（开关量）
	private Double countOfOneStd;		// 高电平计数二级指标标准差（开关量）
	private Double countOfZero5;		// 低电平计数二级指标5分位（开关量）
	private Double countOfZero25;		// 低电平计数二级指标25分位（开关量）
	private Double countOfZero50;		// 低电平计数二级指标中分位（开关量）
	private Double countOfZero75;		// 低电平计数二级指标75分位（开关量）
	private Double countOfZero95;		// 低电平计数二级指标95分位（开关量）
	private Double countOfZeroAvg;		// 低电平计数二级指标平均值（开关量）
	private Double countOfZeroMax;		// 低电平计数二级指标最大值（开关量）
	private Double countOfZeroMin;		// 低电平计数二级指标最小值（开关量）
	private Double countOfZeroStd;		// 低电平计数二级指标标准差（开关量）
	private Double countOfSent5;		// 数据点计数二级指标5分位（共用）
	private Double countOfSent25;		// 数据点计数二级指标25分位（共用）
	private Double countOfSent50;		// 数据点计数二级指标中分位（共用）
	private Double countOfSent75;		// 数据点计数二级指标75分位（共用）
	private Double countOfSent95;		// 数据点计数二级指标95分位（共用）
	private Double countOfSentAvg;		// 数据点计数二级指标平均值（共用）
	private Double countOfSentMax;		// 数据点计数二级指标最大值（共用）
	private Double countOfSentMin;		// 数据点计数二级指标最小值（共用）
	private Double countOfSentStd;		// 数据点计数二级指标标准值（共用）
	private Double countOfSentDistinctParavalue5;		// 不同取值二级指标5分位（共用）
	private Double countOfSentDistinctParavalue25;		// 不同取值二级指标25分位（共用）
	private Double countOfSentDistinctParavalue50;		// 不同取值二级指标中分位（共用）
	private Double countOfSentDistinctParavalue75;		// 不同取值二级指标75分位（共用）
	private Double countOfSentDistinctParavalue95;		// 不同取值二级指标95分位（共用）
	private Double countOfSentDistinctParavalueAvg;		// 不同取值二级指标平均值（共用）
	private Double countOfSentDistinctParavalueMax;		// 不同取值二级指标最大值（共用）
	private Double countOfSentDistinctParavalueMin;		// 不同取值二级指标最小值（共用）
	private Double countOfSentDistinctParavalueStd;		// 不同取值二级指标标准差（共用）
	private Double sentFrequence5;		// 发送频率二级指标5分位（共用）
	private Double sentFrequence25;		// 发送频率二级指标5分位（共用）
	private Double sentFrequence50;		// 发送频率二级指标中分位（共用）
	private Double sentFrequence75;		// 发送频率二级指标75分位（共用）
	private Double sentFrequence95;		// 发送频率二级指标95分位（共用）
	private Double sentFrequenceAvg;		// sent_frequence_avg
	private Double sentFrequenceMax;		// 发送频率二级指标最大值（共用）
	private Double sentFrequenceMin;		// 发送频率二级指标最小值（共用）
	private Double sentFrequenceStd;		// 发送频率二级指标标准差（共用）
	private Double grad5;		// 梯度二级指标5分位（共用）
	private Double grad25;		// 梯度二级指标25分位（共用）
	private Double grad50;		// 梯度二级指标中分位（共用）
	private Double grad75;		// 梯度二级指标75分位（共用）
	private Double grad95;		// 梯度二级指标95分位（共用）
	private Double gradAvg;		// 梯度二级指标平均值（共用）
	private Double gradMax;		// 梯度二级指标最大值（共用）
	private Double gradMin;		// 梯度二级指标最小值（共用）
	private Double gradStd;		// 梯度二级指标标准差（共用）
	private Double standard5;		// 标准差二级指标5分位（共用）
	private Double standard25;		// 标准差二级指标25分位（共用）
	private Double standard50;		// 标准差二级指标中分位（共用）
	private Double standard75;		// 标准差二级指标75分位（共用）
	private Double standard95;		// 标准差二级指标95分位（共用）
	private Double standardAvg;		// 标准差二级指标平均值（共用）
	private Double standardMax;		// 标准差二级指标最大值（共用）
	private Double standardMin;		// 标准差二级指标最小值（共用）
	private Double standardStd;		// 标准差二级指标标准差（共用）
	private Double variance5;		// 方差二级指标5分位（共用）
	private Double variance25;		// 方差二级指标25分位（共用）
	private Double variance50;		// 方差二级指标中分位（共用）
	private Double variance75;		// 方差二级指标75分位（共用）
	private Double variance95;		// 方差二级指标95分位（共用）
	private Double varianceAvg;		// 方差二级指标平均值（共用）
	private Double varianceMax;		// 方差二级指标最大值（共用）
	private Double varianceMin;		// 方差二级指标最小值（共用）
	private Double varianceStd;		// 方差二级指标标准差（共用）
	
	public LgBaselineofdevice() {
		super();
	}

	public LgBaselineofdevice(String id){
		super(id);
	}

	@Length(min=0, max=64, message="设备编码长度必须介于 0 和 64 之间")
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	
	@Length(min=0, max=64, message="sensorno长度必须介于 0 和 64 之间")
	public String getSensorNo() {
		return sensorNo;
	}

	public void setSensorNo(String sensorNo) {
		this.sensorNo = sensorNo;
	}
	
	public Integer getSliceTotal() {
		return sliceTotal;
	}

	public void setSliceTotal(Integer sliceTotal) {
		this.sliceTotal = sliceTotal;
	}
	
	public Integer getSliceCount() {
		return sliceCount;
	}

	public void setSliceCount(Integer sliceCount) {
		this.sliceCount = sliceCount;
	}
	
	public Double getAverage5() {
		return average5;
	}

	public void setAverage5(Double average5) {
		this.average5 = average5;
	}
	
	public Double getAverage25() {
		return average25;
	}

	public void setAverage25(Double average25) {
		this.average25 = average25;
	}
	
	public Double getAverage50() {
		return average50;
	}

	public void setAverage50(Double average50) {
		this.average50 = average50;
	}
	
	public Double getAverage75() {
		return average75;
	}

	public void setAverage75(Double average75) {
		this.average75 = average75;
	}
	
	public Double getAverage95() {
		return average95;
	}

	public void setAverage95(Double average95) {
		this.average95 = average95;
	}
	
	public Double getAverageAvg() {
		return averageAvg;
	}

	public void setAverageAvg(Double averageAvg) {
		this.averageAvg = averageAvg;
	}
	
	public Double getAverageMax() {
		return averageMax;
	}

	public void setAverageMax(Double averageMax) {
		this.averageMax = averageMax;
	}
	
	public Double getAverageMin() {
		return averageMin;
	}

	public void setAverageMin(Double averageMin) {
		this.averageMin = averageMin;
	}
	
	public Double getAverageStd() {
		return averageStd;
	}

	public void setAverageStd(Double averageStd) {
		this.averageStd = averageStd;
	}
	
	public Double getMax5() {
		return max5;
	}

	public void setMax5(Double max5) {
		this.max5 = max5;
	}
	
	public Double getMax25() {
		return max25;
	}

	public void setMax25(Double max25) {
		this.max25 = max25;
	}
	
	public Double getMax50() {
		return max50;
	}

	public void setMax50(Double max50) {
		this.max50 = max50;
	}
	
	public Double getMax75() {
		return max75;
	}

	public void setMax75(Double max75) {
		this.max75 = max75;
	}
	
	public Double getMax95() {
		return max95;
	}

	public void setMax95(Double max95) {
		this.max95 = max95;
	}
	
	public Double getMaxAvg() {
		return maxAvg;
	}

	public void setMaxAvg(Double maxAvg) {
		this.maxAvg = maxAvg;
	}
	
	public Double getMaxMax() {
		return maxMax;
	}

	public void setMaxMax(Double maxMax) {
		this.maxMax = maxMax;
	}
	
	public Double getMaxMin() {
		return maxMin;
	}

	public void setMaxMin(Double maxMin) {
		this.maxMin = maxMin;
	}
	
	public Double getMaxStd() {
		return maxStd;
	}

	public void setMaxStd(Double maxStd) {
		this.maxStd = maxStd;
	}
	
	public Double getMin5() {
		return min5;
	}

	public void setMin5(Double min5) {
		this.min5 = min5;
	}
	
	public Double getMin25() {
		return min25;
	}

	public void setMin25(Double min25) {
		this.min25 = min25;
	}
	
	public Double getMin50() {
		return min50;
	}

	public void setMin50(Double min50) {
		this.min50 = min50;
	}
	
	public Double getMin75() {
		return min75;
	}

	public void setMin75(Double min75) {
		this.min75 = min75;
	}
	
	public Double getMin95() {
		return min95;
	}

	public void setMin95(Double min95) {
		this.min95 = min95;
	}
	
	public Double getMinAvg() {
		return minAvg;
	}

	public void setMinAvg(Double minAvg) {
		this.minAvg = minAvg;
	}
	
	public Double getMinMax() {
		return minMax;
	}

	public void setMinMax(Double minMax) {
		this.minMax = minMax;
	}
	
	public Double getMinMin() {
		return minMin;
	}

	public void setMinMin(Double minMin) {
		this.minMin = minMin;
	}
	
	public Double getMinStd() {
		return minStd;
	}

	public void setMinStd(Double minStd) {
		this.minStd = minStd;
	}
	
	public Double getCountOfChange5() {
		return countOfChange5;
	}

	public void setCountOfChange5(Double countOfChange5) {
		this.countOfChange5 = countOfChange5;
	}
	
	public Double getCountOfChange25() {
		return countOfChange25;
	}

	public void setCountOfChange25(Double countOfChange25) {
		this.countOfChange25 = countOfChange25;
	}
	
	public Double getCountOfChange50() {
		return countOfChange50;
	}

	public void setCountOfChange50(Double countOfChange50) {
		this.countOfChange50 = countOfChange50;
	}
	
	public Double getCountOfChange75() {
		return countOfChange75;
	}

	public void setCountOfChange75(Double countOfChange75) {
		this.countOfChange75 = countOfChange75;
	}
	
	public Double getCountOfChange95() {
		return countOfChange95;
	}

	public void setCountOfChange95(Double countOfChange95) {
		this.countOfChange95 = countOfChange95;
	}
	
	public Double getCountOfChangeAvg() {
		return countOfChangeAvg;
	}

	public void setCountOfChangeAvg(Double countOfChangeAvg) {
		this.countOfChangeAvg = countOfChangeAvg;
	}
	
	public Double getCountOfChangeMin() {
		return countOfChangeMin;
	}

	public void setCountOfChangeMin(Double countOfChangeMin) {
		this.countOfChangeMin = countOfChangeMin;
	}
	
	public Double getCountOfChangeMax() {
		return countOfChangeMax;
	}

	public void setCountOfChangeMax(Double countOfChangeMax) {
		this.countOfChangeMax = countOfChangeMax;
	}
	
	public Double getCountOfChangeStd() {
		return countOfChangeStd;
	}

	public void setCountOfChangeStd(Double countOfChangeStd) {
		this.countOfChangeStd = countOfChangeStd;
	}
	
	public Double getCountOfOne5() {
		return countOfOne5;
	}

	public void setCountOfOne5(Double countOfOne5) {
		this.countOfOne5 = countOfOne5;
	}
	
	public Double getCountOfOne25() {
		return countOfOne25;
	}

	public void setCountOfOne25(Double countOfOne25) {
		this.countOfOne25 = countOfOne25;
	}
	
	public Double getCountOfOne50() {
		return countOfOne50;
	}

	public void setCountOfOne50(Double countOfOne50) {
		this.countOfOne50 = countOfOne50;
	}
	
	public Double getCountOfOne75() {
		return countOfOne75;
	}

	public void setCountOfOne75(Double countOfOne75) {
		this.countOfOne75 = countOfOne75;
	}
	
	public Double getCountOfOne95() {
		return countOfOne95;
	}

	public void setCountOfOne95(Double countOfOne95) {
		this.countOfOne95 = countOfOne95;
	}
	
	public Double getCountOfOneAvg() {
		return countOfOneAvg;
	}

	public void setCountOfOneAvg(Double countOfOneAvg) {
		this.countOfOneAvg = countOfOneAvg;
	}
	
	public Double getCountOfOneMax() {
		return countOfOneMax;
	}

	public void setCountOfOneMax(Double countOfOneMax) {
		this.countOfOneMax = countOfOneMax;
	}
	
	public Double getCountOfOneMin() {
		return countOfOneMin;
	}

	public void setCountOfOneMin(Double countOfOneMin) {
		this.countOfOneMin = countOfOneMin;
	}
	
	public Double getCountOfOneStd() {
		return countOfOneStd;
	}

	public void setCountOfOneStd(Double countOfOneStd) {
		this.countOfOneStd = countOfOneStd;
	}
	
	public Double getCountOfZero5() {
		return countOfZero5;
	}

	public void setCountOfZero5(Double countOfZero5) {
		this.countOfZero5 = countOfZero5;
	}
	
	public Double getCountOfZero25() {
		return countOfZero25;
	}

	public void setCountOfZero25(Double countOfZero25) {
		this.countOfZero25 = countOfZero25;
	}
	
	public Double getCountOfZero50() {
		return countOfZero50;
	}

	public void setCountOfZero50(Double countOfZero50) {
		this.countOfZero50 = countOfZero50;
	}
	
	public Double getCountOfZero75() {
		return countOfZero75;
	}

	public void setCountOfZero75(Double countOfZero75) {
		this.countOfZero75 = countOfZero75;
	}
	
	public Double getCountOfZero95() {
		return countOfZero95;
	}

	public void setCountOfZero95(Double countOfZero95) {
		this.countOfZero95 = countOfZero95;
	}
	
	public Double getCountOfZeroAvg() {
		return countOfZeroAvg;
	}

	public void setCountOfZeroAvg(Double countOfZeroAvg) {
		this.countOfZeroAvg = countOfZeroAvg;
	}
	
	public Double getCountOfZeroMax() {
		return countOfZeroMax;
	}

	public void setCountOfZeroMax(Double countOfZeroMax) {
		this.countOfZeroMax = countOfZeroMax;
	}
	
	public Double getCountOfZeroMin() {
		return countOfZeroMin;
	}

	public void setCountOfZeroMin(Double countOfZeroMin) {
		this.countOfZeroMin = countOfZeroMin;
	}
	
	public Double getCountOfZeroStd() {
		return countOfZeroStd;
	}

	public void setCountOfZeroStd(Double countOfZeroStd) {
		this.countOfZeroStd = countOfZeroStd;
	}
	
	public Double getCountOfSent5() {
		return countOfSent5;
	}

	public void setCountOfSent5(Double countOfSent5) {
		this.countOfSent5 = countOfSent5;
	}
	
	public Double getCountOfSent25() {
		return countOfSent25;
	}

	public void setCountOfSent25(Double countOfSent25) {
		this.countOfSent25 = countOfSent25;
	}
	
	public Double getCountOfSent50() {
		return countOfSent50;
	}

	public void setCountOfSent50(Double countOfSent50) {
		this.countOfSent50 = countOfSent50;
	}
	
	public Double getCountOfSent75() {
		return countOfSent75;
	}

	public void setCountOfSent75(Double countOfSent75) {
		this.countOfSent75 = countOfSent75;
	}
	
	public Double getCountOfSent95() {
		return countOfSent95;
	}

	public void setCountOfSent95(Double countOfSent95) {
		this.countOfSent95 = countOfSent95;
	}
	
	public Double getCountOfSentAvg() {
		return countOfSentAvg;
	}

	public void setCountOfSentAvg(Double countOfSentAvg) {
		this.countOfSentAvg = countOfSentAvg;
	}
	
	public Double getCountOfSentMax() {
		return countOfSentMax;
	}

	public void setCountOfSentMax(Double countOfSentMax) {
		this.countOfSentMax = countOfSentMax;
	}
	
	public Double getCountOfSentMin() {
		return countOfSentMin;
	}

	public void setCountOfSentMin(Double countOfSentMin) {
		this.countOfSentMin = countOfSentMin;
	}
	
	public Double getCountOfSentStd() {
		return countOfSentStd;
	}

	public void setCountOfSentStd(Double countOfSentStd) {
		this.countOfSentStd = countOfSentStd;
	}
	
	public Double getCountOfSentDistinctParavalue5() {
		return countOfSentDistinctParavalue5;
	}

	public void setCountOfSentDistinctParavalue5(Double countOfSentDistinctParavalue5) {
		this.countOfSentDistinctParavalue5 = countOfSentDistinctParavalue5;
	}
	
	public Double getCountOfSentDistinctParavalue25() {
		return countOfSentDistinctParavalue25;
	}

	public void setCountOfSentDistinctParavalue25(Double countOfSentDistinctParavalue25) {
		this.countOfSentDistinctParavalue25 = countOfSentDistinctParavalue25;
	}
	
	public Double getCountOfSentDistinctParavalue50() {
		return countOfSentDistinctParavalue50;
	}

	public void setCountOfSentDistinctParavalue50(Double countOfSentDistinctParavalue50) {
		this.countOfSentDistinctParavalue50 = countOfSentDistinctParavalue50;
	}
	
	public Double getCountOfSentDistinctParavalue75() {
		return countOfSentDistinctParavalue75;
	}

	public void setCountOfSentDistinctParavalue75(Double countOfSentDistinctParavalue75) {
		this.countOfSentDistinctParavalue75 = countOfSentDistinctParavalue75;
	}
	
	public Double getCountOfSentDistinctParavalue95() {
		return countOfSentDistinctParavalue95;
	}

	public void setCountOfSentDistinctParavalue95(Double countOfSentDistinctParavalue95) {
		this.countOfSentDistinctParavalue95 = countOfSentDistinctParavalue95;
	}
	
	public Double getCountOfSentDistinctParavalueAvg() {
		return countOfSentDistinctParavalueAvg;
	}

	public void setCountOfSentDistinctParavalueAvg(Double countOfSentDistinctParavalueAvg) {
		this.countOfSentDistinctParavalueAvg = countOfSentDistinctParavalueAvg;
	}
	
	public Double getCountOfSentDistinctParavalueMax() {
		return countOfSentDistinctParavalueMax;
	}

	public void setCountOfSentDistinctParavalueMax(Double countOfSentDistinctParavalueMax) {
		this.countOfSentDistinctParavalueMax = countOfSentDistinctParavalueMax;
	}
	
	public Double getCountOfSentDistinctParavalueMin() {
		return countOfSentDistinctParavalueMin;
	}

	public void setCountOfSentDistinctParavalueMin(Double countOfSentDistinctParavalueMin) {
		this.countOfSentDistinctParavalueMin = countOfSentDistinctParavalueMin;
	}
	
	public Double getCountOfSentDistinctParavalueStd() {
		return countOfSentDistinctParavalueStd;
	}

	public void setCountOfSentDistinctParavalueStd(Double countOfSentDistinctParavalueStd) {
		this.countOfSentDistinctParavalueStd = countOfSentDistinctParavalueStd;
	}
	
	public Double getSentFrequence5() {
		return sentFrequence5;
	}

	public void setSentFrequence5(Double sentFrequence5) {
		this.sentFrequence5 = sentFrequence5;
	}
	
	public Double getSentFrequence25() {
		return sentFrequence25;
	}

	public void setSentFrequence25(Double sentFrequence25) {
		this.sentFrequence25 = sentFrequence25;
	}
	
	public Double getSentFrequence50() {
		return sentFrequence50;
	}

	public void setSentFrequence50(Double sentFrequence50) {
		this.sentFrequence50 = sentFrequence50;
	}
	
	public Double getSentFrequence75() {
		return sentFrequence75;
	}

	public void setSentFrequence75(Double sentFrequence75) {
		this.sentFrequence75 = sentFrequence75;
	}
	
	public Double getSentFrequence95() {
		return sentFrequence95;
	}

	public void setSentFrequence95(Double sentFrequence95) {
		this.sentFrequence95 = sentFrequence95;
	}
	
	public Double getSentFrequenceAvg() {
		return sentFrequenceAvg;
	}

	public void setSentFrequenceAvg(Double sentFrequenceAvg) {
		this.sentFrequenceAvg = sentFrequenceAvg;
	}
	
	public Double getSentFrequenceMax() {
		return sentFrequenceMax;
	}

	public void setSentFrequenceMax(Double sentFrequenceMax) {
		this.sentFrequenceMax = sentFrequenceMax;
	}
	
	public Double getSentFrequenceMin() {
		return sentFrequenceMin;
	}

	public void setSentFrequenceMin(Double sentFrequenceMin) {
		this.sentFrequenceMin = sentFrequenceMin;
	}
	
	public Double getSentFrequenceStd() {
		return sentFrequenceStd;
	}

	public void setSentFrequenceStd(Double sentFrequenceStd) {
		this.sentFrequenceStd = sentFrequenceStd;
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
	
	public Double getGradAvg() {
		return gradAvg;
	}

	public void setGradAvg(Double gradAvg) {
		this.gradAvg = gradAvg;
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
	
	public Double getGradStd() {
		return gradStd;
	}

	public void setGradStd(Double gradStd) {
		this.gradStd = gradStd;
	}
	
	public Double getStandard5() {
		return standard5;
	}

	public void setStandard5(Double standard5) {
		this.standard5 = standard5;
	}
	
	public Double getStandard25() {
		return standard25;
	}

	public void setStandard25(Double standard25) {
		this.standard25 = standard25;
	}
	
	public Double getStandard50() {
		return standard50;
	}

	public void setStandard50(Double standard50) {
		this.standard50 = standard50;
	}
	
	public Double getStandard75() {
		return standard75;
	}

	public void setStandard75(Double standard75) {
		this.standard75 = standard75;
	}
	
	public Double getStandard95() {
		return standard95;
	}

	public void setStandard95(Double standard95) {
		this.standard95 = standard95;
	}
	
	public Double getStandardAvg() {
		return standardAvg;
	}

	public void setStandardAvg(Double standardAvg) {
		this.standardAvg = standardAvg;
	}
	
	public Double getStandardMax() {
		return standardMax;
	}

	public void setStandardMax(Double standardMax) {
		this.standardMax = standardMax;
	}
	
	public Double getStandardMin() {
		return standardMin;
	}

	public void setStandardMin(Double standardMin) {
		this.standardMin = standardMin;
	}
	
	public Double getStandardStd() {
		return standardStd;
	}

	public void setStandardStd(Double standardStd) {
		this.standardStd = standardStd;
	}
	
	public Double getVariance5() {
		return variance5;
	}

	public void setVariance5(Double variance5) {
		this.variance5 = variance5;
	}
	
	public Double getVariance25() {
		return variance25;
	}

	public void setVariance25(Double variance25) {
		this.variance25 = variance25;
	}
	
	public Double getVariance50() {
		return variance50;
	}

	public void setVariance50(Double variance50) {
		this.variance50 = variance50;
	}
	
	public Double getVariance75() {
		return variance75;
	}

	public void setVariance75(Double variance75) {
		this.variance75 = variance75;
	}
	
	public Double getVariance95() {
		return variance95;
	}

	public void setVariance95(Double variance95) {
		this.variance95 = variance95;
	}
	
	public Double getVarianceAvg() {
		return varianceAvg;
	}

	public void setVarianceAvg(Double varianceAvg) {
		this.varianceAvg = varianceAvg;
	}
	
	public Double getVarianceMax() {
		return varianceMax;
	}

	public void setVarianceMax(Double varianceMax) {
		this.varianceMax = varianceMax;
	}
	
	public Double getVarianceMin() {
		return varianceMin;
	}

	public void setVarianceMin(Double varianceMin) {
		this.varianceMin = varianceMin;
	}
	
	public Double getVarianceStd() {
		return varianceStd;
	}

	public void setVarianceStd(Double varianceStd) {
		this.varianceStd = varianceStd;
	}
	
}