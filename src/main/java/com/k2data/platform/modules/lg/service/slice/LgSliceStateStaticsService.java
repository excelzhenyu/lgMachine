/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.slice;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.utils.IdGen;
import com.k2data.platform.modules.lg.common.entity.LgSliceDataRows;
import com.k2data.platform.modules.lg.common.entity.LgSliceDataRowsPoints;
import com.k2data.platform.modules.lg.common.entity.LgSliceDataRowsRsp;
import com.k2data.platform.modules.lg.common.kmx.KmxClient;
import com.k2data.platform.modules.lg.common.kmx.KmxCond;
import com.k2data.platform.modules.lg.common.kmx.KmxResponseHandler;
import com.k2data.platform.modules.lg.common.utils.MathUtils;
import com.k2data.platform.modules.lg.dao.slice.LgDeviceStaticsDao;
import com.k2data.platform.modules.lg.entity.LgDeviceSliceStatics;
import com.k2data.platform.modules.lg.entity.slice.LgSliceStaticsStatus;
import com.k2data.platform.modules.lg.entity.slice.LgSliceStaticsStatusDetail;
import com.k2data.platform.modules.lg.entity.slice.LgSliceSwitchStatusDetail;


/**
 * 状态量切片统计Service
 * @author wangshengguo
 * @version 2016-07-27
 */
@Service
public class LgSliceStateStaticsService {
	
	private static final int SENSOR_TYPE = 3;

	@Autowired
	private LgDeviceStaticsDao lgDeviceStaticsDao;
	@Autowired
	private LgSliceStaticsStatusService lgSliceStaticsStatusService;
	
	 //状态量切片统计请求KMX，入口函数
	@Transactional(readOnly = false)
    public void saveSliceStateData(final String deviceNo, List<String> sensors, 
    		final Date start, final Date stop, final String deviceSliceID){
    
        KmxCond cond = new KmxCond();
        cond.setUrl(Global.getApiDataRowsURL());
        cond.setDeviceNo(deviceNo);
        cond.setSensors(sensors.toArray(new String[0]));
        cond.setStart(start);
        cond.setStop(stop);
        cond.setSize(""+KmxClient.PAGE_SIZE);
        KmxClient.getAsync(cond, new KmxResponseHandler() {
            
            @Override
            public void handleResponose(LgSliceDataRowsRsp rsp) {
    			////////状态量切片统计
            	getSliceStateData(rsp, deviceNo, start, stop, deviceSliceID);
            }
        });
    }
	
	public void getSliceStateData(LgSliceDataRowsRsp rsp, final String deviceNo, 
			final Date start, final Date stop,
			final String deviceSliceID){
		
		List<LgSliceStaticsStatus> stateList = new ArrayList<LgSliceStaticsStatus>();
		////////状态量切片统计
		//根据传感器分组 key为传感器，value为传感器值列表
		Map<String,List<String>> sensorMap = groupSensorDataMap(rsp);
		if(!sensorMap.isEmpty()) {
			Iterator<Entry<String, List<String>>> it = sensorMap.entrySet().iterator();
			//遍历
			while(it.hasNext()) {
				Map.Entry<String, List<String>> mapEntry = it.next();
				String sensorNo = mapEntry.getKey();
				List<String> sensorValueList = mapEntry.getValue();
				
				//手动获取uuid，便于写入状态明细和切换明细表
				String sliceStatusID = IdGen.uuid();
				//传感器回传时长 取结束、开始结束之差（分钟）
				int sensorDataDuration = (int)Math.round((stop.getTime() - start.getTime())/(1000*60));
				
				LgSliceStaticsStatus lgSliceStaticsStatus = new LgSliceStaticsStatus();
				lgSliceStaticsStatus.setId(sliceStatusID);
				lgSliceStaticsStatus.setDeviceSliceId(deviceSliceID);
				lgSliceStaticsStatus.setDeviceNo(deviceNo);
				lgSliceStaticsStatus.setSensorNo(sensorNo);
				lgSliceStaticsStatus.setSensorType(""+SENSOR_TYPE);
				lgSliceStaticsStatus.setDataPointCount(sensorValueList.size());
				lgSliceStaticsStatus.setSensorDataStart(start);
				lgSliceStaticsStatus.setSensorDataEnd(stop);
				lgSliceStaticsStatus.setSensorDataDuration(sensorDataDuration);
				
				//状态明细和切换明细统计，返回值为切换次数，便于写入状态量切片统计表
				int changeCount = analysisSensorStatus(sensorValueList, deviceNo, sensorNo, sliceStatusID);
				
				lgSliceStaticsStatus.setChangeCount(changeCount);
				//lgSliceStaticsStatus.setIsNewRecord(true); //设置为新记录，平台save要求
				
				stateList.add(lgSliceStaticsStatus);
			}
		}
		if(!stateList.isEmpty()) {
			lgSliceStaticsStatusService.batchSave(stateList);
		}
    }

	/**
	 * 根据response的对象，提取value值 key为传感器名称，value为传感器值
	 *
	 * @author K2DATA.wsguo
	 * @date Jul 5, 2016 5:30:09 PM   
	 * @param rspMap
	 * @return
	 */
	private Map<String,List<String>> groupSensorDataMap(LgSliceDataRowsRsp rsp) {
		Map<String,List<String>> sensorMap = new HashMap<String,List<String>>();
		if(rsp != null && rsp.getCode() == 0) {
			for(LgSliceDataRows dataRows : rsp.getDataRows()) {
				for(LgSliceDataRowsPoints dataPoints: dataRows.getDataPoints()) {
					String sensor = dataPoints.getSensor();
					String value = "null".equals(dataPoints.getValue().toString())?"":dataPoints.getValue().toString();
					if(StringUtils.isNotBlank(value)) {
						if(sensorMap != null && sensorMap.get(dataPoints.getSensor()) != null) {
							sensorMap.get(sensor).add(value);
						} else {
							sensorMap.put(sensor, new ArrayList<String>());
							sensorMap.get(sensor).add(value);
						}
					}
				}
			}
		}
		return sensorMap;
	}
	
	/**
	 * 状态量切片统计明细及状态量切片内切换明细
	 *
	 * @author K2DATA.wsguo
	 * @date Jul 21, 2016 10:17:21 AM   
	 * @param sensorValueList
	 * @param deviceNo
	 * @param sensorNo
	 * @param sliceStatusId
	 * @return 状态切换次数，用于写入状态量切片统计表
	 */
	@SuppressWarnings("unchecked")
	private int analysisSensorStatus(List<String> sensorValueList, String deviceNo, String sensorNo, String sliceStatusId) {
		int switchCount = 0; //切换次数
		
		//构建状态和状态切换明细Map
		Map<String, Object> statusAndSwitchMap =  MathUtils.analysisStatusDataAndStatusSwitchData(sensorValueList);
		Map<String,List<Double>> statusMap =  (Map<String,List<Double>>)statusAndSwitchMap.get(MathUtils.STATUS_MAP);
		Map<String,Double> switchMap = (Map<String,Double>)statusAndSwitchMap.get(MathUtils.SWITCH_MAP);
		
		///////状态量切片统计明细
		List<LgSliceStaticsStatusDetail> statusList = new ArrayList<LgSliceStaticsStatusDetail>();
		
		Iterator<Entry<String, List<Double>>> statusIt = statusMap.entrySet().iterator();
		
		while(statusIt.hasNext()) {
			Map.Entry<String, List<Double>> mapEntry = statusIt.next();
			
			LgSliceStaticsStatusDetail statusEntity = new LgSliceStaticsStatusDetail();

			statusEntity.setId(IdGen.uuid());
			statusEntity.setDeviceNo(deviceNo);
			statusEntity.setSliceStatusId(sliceStatusId);
			statusEntity.setSensorNo(sensorNo);
			statusEntity.setStatus(mapEntry.getKey());
			
			Map<String, Object> varianceMap = MathUtils.computeVariance(mapEntry.getValue());
			Map<Integer, Double> percentMap = MathUtils.computeAllPercent(mapEntry.getValue());
			if(varianceMap != null && varianceMap.size() > 0) { 
				statusEntity.setStatusMin(Double.parseDouble(varianceMap.get(MathUtils.MIN_VALUE).toString()));
				statusEntity.setStatusMax(Double.parseDouble(varianceMap.get(MathUtils.MAX_VALUE).toString()));
				statusEntity.setStatusAvg(Double.parseDouble(varianceMap.get(MathUtils.AVERAGE).toString()));
				statusEntity.setStatusStandardStd(Double.parseDouble(varianceMap.get(MathUtils.STD_VARIANCE).toString()));
				statusEntity.setStatusVarianceStd(Double.parseDouble(varianceMap.get(MathUtils.VARIANCE).toString()));
			}
			if(percentMap != null && percentMap.size() > 0) { 
				statusEntity.setStatus5(percentMap.get(MathUtils.PERCENT_5));
				statusEntity.setStatus25(percentMap.get(MathUtils.PERCENT_25));
				statusEntity.setStatus50(percentMap.get(MathUtils.PERCENT_50));
				statusEntity.setStatus75(percentMap.get(MathUtils.PERCENT_75));
				statusEntity.setStatus95(percentMap.get(MathUtils.PERCENT_95));
			}
			//切片内传感器状态量次数
			int count = MathUtils.computeSum(mapEntry.getValue()).intValue();
			statusEntity.setStatusCount(count);
			statusEntity.setStatusDuration(count * MathUtils.TIME_INTERVAL);
			
			statusList.add(statusEntity);
		}
		

		/////////状态量切片内切换明细
		List<LgSliceSwitchStatusDetail> switchList = new ArrayList<LgSliceSwitchStatusDetail>();
		
		Iterator<Entry<String, Double>> switchIt = switchMap.entrySet().iterator();
		while(switchIt.hasNext()) {
			LgSliceSwitchStatusDetail switchEntity = new LgSliceSwitchStatusDetail();
			Map.Entry<String, Double> switchMapEntry = switchIt.next();
			String key = switchMapEntry.getKey();
			Double value = switchMapEntry.getValue();
			String[] status = key.split("->");
			
			switchEntity.setId(IdGen.uuid());
			switchEntity.setInsertTime(new Date());
			switchEntity.setDeviceNo(deviceNo);
			switchEntity.setSliceStatusId(sliceStatusId);
			switchEntity.setSensorNo(sensorNo);
			
			switchEntity.setSourceStatus(status[0]);
			switchEntity.setDestStatus(status[1]);
			switchEntity.setStatusCount(value.intValue());
			
			switchList.add(switchEntity);
			
			//状态切换次数，用于写入状态量切片统计表
			switchCount += value.intValue();
		}
		
		if(!statusList.isEmpty()) {
			lgSliceStaticsStatusService.batchSaveState(statusList);
		}
		if(!switchList.isEmpty()) {
			lgSliceStaticsStatusService.batchSaveSwitch(switchList);
		}
		
		return switchCount;
	}
	
	//状态量切片统计 
	public void saveDeviceSensorSliceStaticsStatus(Date recordTime) {

		//获取设备切片统计信息
		List<LgDeviceSliceStatics> sliceStaticslist = lgDeviceStaticsDao.getSliceStatics(recordTime);
		
		//遍历设备切片信息，对每个设备切片处理，请求API
		for(LgDeviceSliceStatics entity : sliceStaticslist) {
			String deviceNo = entity.getDeviceNo();
			//设备切片id，写入状态量切片统计表
			String deviceSliceID = entity.getId();
			Date start = entity.getSliceStart();
			Date stop = entity.getSliceStop();

			
			//TODO 获取传感器模板，存放于缓存中
			//TODO sensorList 先从缓存中读取，若不存在则去数据库中读取
			//select * from lg_gpstemplatedetail a where EXISTS (
			//	select 1 from lg_machinegpscontrast b where a.gpsTemplateNo=b.gpsTemplateNo and b.deviceNo='C301F4'
			//	) and sensorType=3
			
			List<String> sensorList = lgSliceStaticsStatusService.getSensor(deviceNo);
			saveSliceStateData(deviceNo, sensorList, start, stop, deviceSliceID);
			
		}
	}
}
