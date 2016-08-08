/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.LgBaselineofdevice;
import com.k2data.platform.modules.lg.common.utils.MathUtils;
import com.k2data.platform.modules.lg.dao.LgBaselineofdeviceDao;
import com.k2data.platform.modules.lg.dao.LgSliceStaticsAnalogDao;
import com.k2data.platform.modules.lg.dao.LgSliceStaticsSwitchDao;

/**
 * 二级传感器分析表Service
 * @author chenjingsi
 * @version 2016-05-30
 */
@Service
@Transactional(readOnly = true)
public class LgBaselineofdeviceService extends CrudService<LgBaselineofdeviceDao, LgBaselineofdevice> {
	@Autowired
	private LgSliceStaticsAnalogDao lgSliceStaticsAnalogDao;
	@Autowired
	private LgSliceStaticsSwitchDao lgSliceStaticsSwitchDao;
	
	public LgBaselineofdevice get(String id) {
		return super.get(id);
	}
	
	public List<LgBaselineofdevice> findList(LgBaselineofdevice lgBaselineofdevice) {
		return super.findList(lgBaselineofdevice);
	}
	
	public Page<LgBaselineofdevice> findPage(Page<LgBaselineofdevice> page, LgBaselineofdevice lgBaselineofdevice) {
		return super.findPage(page, lgBaselineofdevice);
	}
	
	@Transactional(readOnly = false)
	public void save(LgBaselineofdevice lgBaselineofdevice) {
		super.save(lgBaselineofdevice);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgBaselineofdevice lgBaselineofdevice) {
		super.delete(lgBaselineofdevice);
	}
	
	public void addBaselineOfDevice(String deviceNo,String sensorNo,Integer type){
		Map<String,Object> params = new HashMap<String,Object>();
		LgBaselineofdevice lgBaselineofdevice = new LgBaselineofdevice();
		lgBaselineofdevice.setDeviceNo(deviceNo);
		lgBaselineofdevice.setSensorNo(sensorNo);
		if(type==0){//模拟量
			//获取平均值的List
			params.put("deviceNo", deviceNo);
			params.put("sensorNo", sensorNo);
			//处理平均值二级指标
			List<Double> averageList = lgSliceStaticsAnalogDao.getAverageList(params);
			Map<String,Object> averageMap = MathUtils.computeVariance(averageList, 1);
			lgBaselineofdevice.setAverageAvg((Double)averageMap.get("average"));
			lgBaselineofdevice.setAverageMax((Double)averageMap.get("maxValue"));
			lgBaselineofdevice.setAverageMin((Double)averageMap.get("minValue"));
			lgBaselineofdevice.setAverageStd((Double)averageMap.get("stdVariance"));
			lgBaselineofdevice.setAverage5(MathUtils.computePercent(averageList, 5, 1));
			lgBaselineofdevice.setAverage25(MathUtils.computePercent(averageList, 25, 1));
			lgBaselineofdevice.setAverage50(MathUtils.computePercent(averageList, 50, 1));
			lgBaselineofdevice.setAverage75(MathUtils.computePercent(averageList,75, 1));
			lgBaselineofdevice.setAverage95(MathUtils.computePercent(averageList, 95, 1));
			//处理最大值二级指标
			List<Double> maxList = lgSliceStaticsAnalogDao.getMaxList(params);
			Map<String,Object> maxMap = MathUtils.computeVariance(maxList, 1);
			lgBaselineofdevice.setMaxAvg((Double)maxMap.get("average"));
			lgBaselineofdevice.setMaxMax((Double)maxMap.get("maxValue"));
			lgBaselineofdevice.setMaxMin((Double)maxMap.get("minValue"));
			lgBaselineofdevice.setMaxStd((Double)maxMap.get("stdVariance"));
			lgBaselineofdevice.setMax5(MathUtils.computePercent(maxList, 5, 1));
			lgBaselineofdevice.setMax25(MathUtils.computePercent(maxList, 25, 1));
			lgBaselineofdevice.setMax50(MathUtils.computePercent(maxList, 50, 1));
			lgBaselineofdevice.setMax75(MathUtils.computePercent(maxList,75, 1));
			lgBaselineofdevice.setMax95(MathUtils.computePercent(maxList, 95, 1));
			//处理最小值二级指标
			List<Double> minList = lgSliceStaticsAnalogDao.getMinList(params);
			Map<String,Object> minMap = MathUtils.computeVariance(minList, 1);
			lgBaselineofdevice.setMinAvg((Double)minMap.get("average"));
			lgBaselineofdevice.setMinMax((Double)minMap.get("maxValue"));
			lgBaselineofdevice.setMinMin((Double)minMap.get("minValue"));
			lgBaselineofdevice.setMinStd((Double)minMap.get("stdVariance"));
			lgBaselineofdevice.setMin5(MathUtils.computePercent(minList, 5, 1));
			lgBaselineofdevice.setMin25(MathUtils.computePercent(minList, 25, 1));
			lgBaselineofdevice.setMin50(MathUtils.computePercent(minList, 50, 1));
			lgBaselineofdevice.setMin75(MathUtils.computePercent(minList,75, 1));
			lgBaselineofdevice.setMin95(MathUtils.computePercent(minList, 95, 1));
			//处理数据点计数二级指标
			List<Double> dataCountList = lgSliceStaticsAnalogDao.getDataCountList(params);
			Map<String,Object> dataCountMap = MathUtils.computeVariance(dataCountList, 1);
			lgBaselineofdevice.setCountOfChangeAvg((Double)dataCountMap.get("average"));
			lgBaselineofdevice.setCountOfChangeMax((Double)dataCountMap.get("maxValue"));
			lgBaselineofdevice.setCountOfChangeMin((Double)dataCountMap.get("minValue"));
			lgBaselineofdevice.setCountOfChangeStd((Double)dataCountMap.get("stdVariance"));
			lgBaselineofdevice.setCountOfChange5(MathUtils.computePercent(dataCountList, 5, 1));
			lgBaselineofdevice.setCountOfChange25(MathUtils.computePercent(dataCountList, 25, 1));
			lgBaselineofdevice.setCountOfChange50(MathUtils.computePercent(dataCountList, 50, 1));
			lgBaselineofdevice.setCountOfChange75(MathUtils.computePercent(dataCountList,75, 1));
			lgBaselineofdevice.setCountOfChange95(MathUtils.computePercent(dataCountList, 95, 1));
			//处理不同取值二级指标
			//处理发送频率二级指标
			List<Double> sentFrequnceList = lgSliceStaticsAnalogDao.getSentFrequenceList(params);
			Map<String,Object> sentFrequenceMap = MathUtils.computeVariance(sentFrequnceList, 1);
			lgBaselineofdevice.setSentFrequenceAvg((Double)sentFrequenceMap.get("average"));
			lgBaselineofdevice.setSentFrequenceMax((Double)sentFrequenceMap.get("maxValue"));
			lgBaselineofdevice.setSentFrequenceMin((Double)sentFrequenceMap.get("minValue"));
			lgBaselineofdevice.setSentFrequenceStd((Double)sentFrequenceMap.get("stdVariance"));
			lgBaselineofdevice.setSentFrequence5(MathUtils.computePercent(sentFrequnceList, 5, 1));
			lgBaselineofdevice.setSentFrequence25(MathUtils.computePercent(sentFrequnceList, 25, 1));
			lgBaselineofdevice.setSentFrequence50(MathUtils.computePercent(sentFrequnceList, 50, 1));
			lgBaselineofdevice.setSentFrequence75(MathUtils.computePercent(sentFrequnceList,75, 1));
			lgBaselineofdevice.setSentFrequence95(MathUtils.computePercent(sentFrequnceList, 95, 1));
			//处理梯度二级指标
			List<Double> gradList = lgSliceStaticsAnalogDao.getGradList(params);
			Map<String,Object>  gradMap= MathUtils.computeVariance(gradList, 1);
			lgBaselineofdevice.setGradAvg((Double)gradMap.get("average"));
			lgBaselineofdevice.setGradMax((Double)gradMap.get("maxValue"));
			lgBaselineofdevice.setGradMin((Double)gradMap.get("minValue"));
			lgBaselineofdevice.setGradStd((Double)gradMap.get("stdVariance"));
			lgBaselineofdevice.setGrad5(MathUtils.computePercent(gradList, 5, 1));
			lgBaselineofdevice.setGrad25(MathUtils.computePercent(gradList, 25, 1));
			lgBaselineofdevice.setGrad50(MathUtils.computePercent(gradList, 50, 1));
			lgBaselineofdevice.setGrad75(MathUtils.computePercent(gradList,75, 1));
			lgBaselineofdevice.setGrad95(MathUtils.computePercent(gradList, 95, 1));
			//处理标准差二级指标
			List<Double> standardList = lgSliceStaticsAnalogDao.getStandardList(params);
			Map<String,Object> standardMap = MathUtils.computeVariance(standardList, 1);
			lgBaselineofdevice.setStandardAvg((Double)standardMap.get("average"));
			lgBaselineofdevice.setStandardMax((Double)standardMap.get("maxValue"));
			lgBaselineofdevice.setStandardMin((Double)standardMap.get("minValue"));
			lgBaselineofdevice.setStandardStd((Double)standardMap.get("stdVariance"));
			lgBaselineofdevice.setStandard5(MathUtils.computePercent(standardList, 5, 1));
			lgBaselineofdevice.setStandard25(MathUtils.computePercent(standardList, 25, 1));
			lgBaselineofdevice.setStandard50(MathUtils.computePercent(standardList, 50, 1));
			lgBaselineofdevice.setStandard75(MathUtils.computePercent(standardList,75, 1));
			lgBaselineofdevice.setStandard95(MathUtils.computePercent(standardList, 95, 1));
			//处理方差二级指标
			List<Double> varianceList = lgSliceStaticsAnalogDao.getVarianceList(params);
			Map<String,Object> varianceMap = MathUtils.computeVariance(varianceList, 1);
			lgBaselineofdevice.setVariance5((Double)varianceMap.get("average"));
			lgBaselineofdevice.setVariance5((Double)varianceMap.get("maxValue"));
			lgBaselineofdevice.setVariance5((Double)varianceMap.get("minValue"));
			lgBaselineofdevice.setVariance5((Double)varianceMap.get("stdVariance"));
			lgBaselineofdevice.setVariance5(MathUtils.computePercent(varianceList, 5, 1));
			lgBaselineofdevice.setVariance5(MathUtils.computePercent(varianceList, 25, 1));
			lgBaselineofdevice.setVariance5(MathUtils.computePercent(varianceList, 50, 1));
			lgBaselineofdevice.setVariance5(MathUtils.computePercent(varianceList,75, 1));
			lgBaselineofdevice.setVariance5(MathUtils.computePercent(varianceList, 95, 1));
		}
		if(type==1){//开关量
			//取值切换次数二级指标
			List<Double> changeCountList = lgSliceStaticsSwitchDao.getChangeCountList(params);
			Map<String,Object> changeCountMap = MathUtils.computeVariance(changeCountList, 1);
			lgBaselineofdevice.setCountOfChangeAvg((Double)changeCountMap.get("average"));
			lgBaselineofdevice.setCountOfChangeMax((Double)changeCountMap.get("maxValue"));
			lgBaselineofdevice.setCountOfChangeMin((Double)changeCountMap.get("minValue"));
			lgBaselineofdevice.setCountOfChangeStd((Double)changeCountMap.get("stdVariance"));
			lgBaselineofdevice.setCountOfChange5(MathUtils.computePercent(changeCountList, 5, 1));
			lgBaselineofdevice.setCountOfChange25(MathUtils.computePercent(changeCountList, 25, 1));
			lgBaselineofdevice.setCountOfChange50(MathUtils.computePercent(changeCountList, 50, 1));
			lgBaselineofdevice.setCountOfChange75(MathUtils.computePercent(changeCountList,75, 1));
			lgBaselineofdevice.setCountOfChange95(MathUtils.computePercent(changeCountList, 95, 1));
			//高电平计数二级指标
			List<Double> highCountList = lgSliceStaticsSwitchDao.getHighCountList(params);
			Map<String,Object> highCountMap = MathUtils.computeVariance(highCountList, 1);
			lgBaselineofdevice.setCountOfOneAvg((Double)highCountMap.get("average"));
			lgBaselineofdevice.setCountOfOneMax((Double)highCountMap.get("maxValue"));
			lgBaselineofdevice.setCountOfOneMin((Double)highCountMap.get("minValue"));
			lgBaselineofdevice.setCountOfOneStd((Double)highCountMap.get("stdVariance"));
			lgBaselineofdevice.setCountOfOne5(MathUtils.computePercent(highCountList, 5, 1));
			lgBaselineofdevice.setCountOfOne25(MathUtils.computePercent(highCountList, 25, 1));
			lgBaselineofdevice.setCountOfOne50(MathUtils.computePercent(highCountList, 50, 1));
			lgBaselineofdevice.setCountOfOne75(MathUtils.computePercent(highCountList,75, 1));
			lgBaselineofdevice.setCountOfOne95(MathUtils.computePercent(highCountList, 95, 1));
			//低电平计数二级指标
			List<Double> lowCountList = lgSliceStaticsSwitchDao.getLowCountList(params);
			Map<String,Object> lowCountMap = MathUtils.computeVariance(lowCountList, 1);
			lgBaselineofdevice.setCountOfZeroAvg((Double)lowCountMap.get("average"));
			lgBaselineofdevice.setCountOfZeroMax((Double)lowCountMap.get("maxValue"));
			lgBaselineofdevice.setCountOfZeroMin((Double)lowCountMap.get("minValue"));
			lgBaselineofdevice.setCountOfZeroStd((Double)lowCountMap.get("stdVariance"));
			lgBaselineofdevice.setCountOfZero5(MathUtils.computePercent(lowCountList, 5, 1));
			lgBaselineofdevice.setCountOfZero25(MathUtils.computePercent(lowCountList, 25, 1));
			lgBaselineofdevice.setCountOfZero50(MathUtils.computePercent(lowCountList, 50, 1));
			lgBaselineofdevice.setCountOfZero75(MathUtils.computePercent(lowCountList,75, 1));
			lgBaselineofdevice.setCountOfZero95(MathUtils.computePercent(lowCountList, 95, 1));
			//数据点计数二级指标
			List<Double> dataPointList = lgSliceStaticsSwitchDao.getDataPointList(params);
			Map<String,Object> dataPointMap = MathUtils.computeVariance(dataPointList, 1);
			lgBaselineofdevice.setCountOfSentAvg((Double)dataPointMap.get("average"));
			lgBaselineofdevice.setCountOfSentMax((Double)dataPointMap.get("maxValue"));
			lgBaselineofdevice.setCountOfSentMin((Double)dataPointMap.get("minValue"));
			lgBaselineofdevice.setCountOfSentStd((Double)dataPointMap.get("stdVariance"));
			lgBaselineofdevice.setCountOfSent5(MathUtils.computePercent(dataPointList, 5, 1));
			lgBaselineofdevice.setCountOfSent25(MathUtils.computePercent(dataPointList, 25, 1));
			lgBaselineofdevice.setCountOfSent50(MathUtils.computePercent(dataPointList, 50, 1));
			lgBaselineofdevice.setCountOfSent75(MathUtils.computePercent(dataPointList,75, 1));
			lgBaselineofdevice.setCountOfSent95(MathUtils.computePercent(dataPointList, 95, 1));
			//不同取值二级指标
			//发送频率二级指标
			List<Double> sentFrequenceList = lgSliceStaticsSwitchDao.getSentFrequenceList(params);
			Map<String,Object> sentFrequenceMap = MathUtils.computeVariance(sentFrequenceList, 1);
			lgBaselineofdevice.setSentFrequenceAvg((Double)sentFrequenceMap.get("average"));
			lgBaselineofdevice.setSentFrequenceMax((Double)sentFrequenceMap.get("maxValue"));
			lgBaselineofdevice.setSentFrequenceMin((Double)sentFrequenceMap.get("minValue"));
			lgBaselineofdevice.setSentFrequenceStd((Double)sentFrequenceMap.get("stdVariance"));
			lgBaselineofdevice.setSentFrequence5(MathUtils.computePercent(sentFrequenceList, 5, 1));
			lgBaselineofdevice.setSentFrequence25(MathUtils.computePercent(sentFrequenceList, 25, 1));
			lgBaselineofdevice.setSentFrequence50(MathUtils.computePercent(sentFrequenceList, 50, 1));
			lgBaselineofdevice.setSentFrequence75(MathUtils.computePercent(sentFrequenceList,75, 1));
			lgBaselineofdevice.setSentFrequence95(MathUtils.computePercent(sentFrequenceList, 95, 1));
			//梯度
			//标准差
			//方差
		}
		if(type==2){//状态量
			//TODO：
		}
		super.save(lgBaselineofdevice);
	}
}