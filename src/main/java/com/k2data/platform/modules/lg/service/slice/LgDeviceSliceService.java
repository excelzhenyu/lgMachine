/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.slice;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.utils.DateUtils;
import com.k2data.platform.common.utils.IdGen;
import com.k2data.platform.modules.lg.common.entity.LgSliceDataRows;
import com.k2data.platform.modules.lg.common.entity.LgSliceDataRowsPoints;
import com.k2data.platform.modules.lg.common.entity.LgSliceDataRowsRsp;
import com.k2data.platform.modules.lg.common.enums.SensorNameEnum;
import com.k2data.platform.modules.lg.common.enums.SensorStatusEnum;
import com.k2data.platform.modules.lg.common.kmx.KmxClient;
import com.k2data.platform.modules.lg.common.kmx.KmxCond;
import com.k2data.platform.modules.lg.common.kmx.KmxResponseHandler;
import com.k2data.platform.modules.lg.entity.LgDeviceSliceStatics;
import com.k2data.platform.modules.lg.service.LgDeviceSliceStaticsService;

/**
 * 设备切片统计
 * @author wangshengguo
 * @version 2016-07-27
 */

@Service
public class LgDeviceSliceService {
	//开机/工作状态
	private static final String WORK_STATUS = SensorStatusEnum.ACC_STATUS.getOpen();
	private static final String SLEEP_STATUS = SensorStatusEnum.ACC_STATUS.getClose();
    private static final int MINUTE = 60*1000;
    
	//请求传感器参数
	private static final String[] dateSensors = new String[]{
					SensorNameEnum.ACC_STATUS.getSensorName(),
					SensorNameEnum.LATITUDE_NUM.getSensorName(),
					SensorNameEnum.LONGITUDE_NUM.getSensorName(), 
					SensorNameEnum.CITYCODE.getSensorName(),
					SensorNameEnum.ADDRESS.getSensorName(),
					SensorNameEnum.PROVINCE.getSensorName(),
					SensorNameEnum.CITY.getSensorName()};
    
    @Autowired
    private LgDeviceSliceStaticsService lgDeviceSliceStaticsService;
    

    //异步请求入口
    public void saveAsyncDeviceSliceData(final String deviceNo, final Date recordTime){
        Date start = DateUtils.parseDate(DateUtils.formatDate(recordTime) + " 00:00:00");
        Date stop = DateUtils.parseDate(DateUtils.formatDate(recordTime) + " 23:59:59");
    
        KmxCond cond = new KmxCond();
        cond.setUrl(Global.getApiDataRowsURL());
        cond.setDeviceNo(deviceNo);
        cond.setSensors(dateSensors);
        cond.setStart(start);
        cond.setStop(stop);
        cond.setSize(""+KmxClient.PAGE_SIZE);
        KmxClient.getAsync(cond, new KmxResponseHandler() {
            
            @Override
            public void handleResponose(LgSliceDataRowsRsp rsp) {
            	
                List<LgDeviceSliceStatics> dateList = analysisSliceData(objectToList(rsp), deviceNo, recordTime);
                
                if(dateList != null && !dateList.isEmpty()) {
                    lgDeviceSliceStaticsService.batchSave(dateList);
                }
            }
        });
    }
    
    //同步请求入口
    public void saveSyncDeviceSliceData(final String deviceNo, final Date recordTime){
        Date start = DateUtils.parseDate(DateUtils.formatDate(recordTime) + " 00:00:00");
        Date stop = DateUtils.parseDate(DateUtils.formatDate(recordTime) + " 23:59:59");
    
        KmxCond cond = new KmxCond();
        cond.setUrl(Global.getApiDataRowsURL());
        cond.setDeviceNo(deviceNo);
        cond.setSensors(dateSensors);
        cond.setStart(start);
        cond.setStop(stop);
        cond.setSize(""+KmxClient.PAGE_SIZE);
        KmxClient.getSync(cond, new KmxResponseHandler() {
            
            @Override
            public void handleResponose(LgSliceDataRowsRsp rsp) {
            	
                List<LgDeviceSliceStatics> dateList = analysisSliceData(objectToList(rsp), deviceNo, recordTime);
                
                if(dateList != null && !dateList.isEmpty()) {
                    lgDeviceSliceStaticsService.batchSave(dateList);
                }
            }
        });
    }
    
    //对象转List<Map<String, Object>>
    private List<Map<String, Object>> objectToList(LgSliceDataRowsRsp rsp) {
    	List<Map<String, Object>> accList = new ArrayList<Map<String, Object>>();
        
        if(rsp == null ) {
            return null;
        }

        for(LgSliceDataRows dataRows : rsp.getDataRows()) {
            Map<String, Object> rowsMap = new HashMap<String, Object>();
            rowsMap.put("iso", dataRows.getIso());

            for(LgSliceDataRowsPoints dataPoints: dataRows.getDataPoints()) {
                String sensor = dataPoints.getSensor();
                String value = "null".equals(dataPoints.getValue().toString())?"":dataPoints.getValue().toString();
                rowsMap.put(sensor, value);
                accList.add(rowsMap);
            }
        }
        return accList;
    }
    //分析数据
    private List<LgDeviceSliceStatics>  analysisSliceData(List<Map<String, Object>> sliceList, String deviceNo, Date workDate) {
    	
    	if(sliceList == null || sliceList.isEmpty()) {
    		return null;
    	}
    	boolean foundWorkStop = false; //找到当天第一个工作结束时间

    	boolean foundStart = false;	//找到某个切片的开始时间
    	boolean foundStop = false;	//找到某个切片的结束时间
    	
    	Date sliceStart = null; //开始时间
    	Date sliceStop = null; //结束时间
    	Date workStop = null;	//第一次工作结束时间

    	//最后一个,用于获取最后经纬度地点等
    	Map<String, Object> lastMap = sliceList.get(sliceList.size() - 1);
    	
    	List<LgDeviceSliceStatics> deviceStaticsList = new ArrayList<LgDeviceSliceStatics>();

		LgDeviceSliceStatics maybeNeedUpdateDeviceStatics = new LgDeviceSliceStatics();

		//该判断不适用,[**废弃**],因为可能存在上一天最后一个正好为开机状态,但是第二天的第一个状态正好为关机状态的情况
        //如果第一个点为开机状态,则当天存在关机状态,则需要更新之前天中结束时间为空的记录
        //boolean needUpdate = WORK_STATUS.equals(firstMap.get(SensorNameEnum.ACC_STATUS.getSensorName()).toString());
        
        int sliceCount = 1;
        
    	for(Map<String, Object> sliceMap : sliceList) {
    		if(WORK_STATUS.equals(sliceMap.get(SensorNameEnum.ACC_STATUS.getSensorName()).toString())) {
    			//找到一个开始时间
    			if(!foundStart) {
    				sliceStart = (Date)sliceMap.get("iso");
        			foundStart = true;
    			}
    			foundStop = false;
    		} else if(SLEEP_STATUS.equals(sliceMap.get(SensorNameEnum.ACC_STATUS.getSensorName()).toString())) {
    			if(!foundWorkStop) {
    			//	if(needUpdate) {
    				workStop = (Date)sliceMap.get("iso");
					maybeNeedUpdateDeviceStatics.setDeviceNo(deviceNo);
					maybeNeedUpdateDeviceStatics.setSliceStop(workStop);
    				maybeNeedUpdateDeviceStatics.setLatitudes(Double.parseDouble(sliceMap.get(SensorNameEnum.LATITUDE_NUM.getSensorName()).toString()));
    				maybeNeedUpdateDeviceStatics.setLongitude(Double.parseDouble(sliceMap.get(SensorNameEnum.LONGITUDE_NUM.getSensorName()).toString()));
    				maybeNeedUpdateDeviceStatics.setCity(sliceMap.get(SensorNameEnum.CITY.getSensorName()).toString());
    				maybeNeedUpdateDeviceStatics.setProvince(sliceMap.get(SensorNameEnum.PROVINCE.getSensorName()).toString());
    				maybeNeedUpdateDeviceStatics.setAddress(sliceMap.get(SensorNameEnum.ADDRESS.getSensorName()).toString());
    				
    				//更新之前天数的结束日期为空的情况
    				int updateCount  = lgDeviceSliceStaticsService.updatePrevDay(maybeNeedUpdateDeviceStatics);
    				//如果存在更新记录,则舍去本次开始时间
    				if(updateCount > 0 && foundStart) {
    					foundStart = false;
    				}
    			//	}
        			foundWorkStop = true;
				}
    			//找到一个结束时间
    			if(!foundStop && foundStart) {
    				sliceStop = (Date)sliceMap.get("iso");
        			foundStop = true;
        			int sliceRunDuration = (int)Math.round((sliceStop.getTime()-sliceStart.getTime())/MINUTE);
        	        
        			LgDeviceSliceStatics deviceStatics = new LgDeviceSliceStatics();
        			deviceStatics.setId(IdGen.uuid());
        			deviceStatics.setDeviceNo(deviceNo);
        			deviceStatics.setSliceCount(sliceCount++);
        			deviceStatics.setSliceStart(sliceStart);
        			deviceStatics.setSliceStop(sliceStop);
        			deviceStatics.setSliceRunDuration(sliceRunDuration);
        			deviceStatics.setSliceWorkDuration(sliceRunDuration);
        			deviceStatics.setLatitudes(Double.parseDouble(sliceMap.get(SensorNameEnum.LATITUDE_NUM.getSensorName()).toString()));
        			deviceStatics.setLongitude(Double.parseDouble(sliceMap.get(SensorNameEnum.LONGITUDE_NUM.getSensorName()).toString()));
        			deviceStatics.setCity(sliceMap.get(SensorNameEnum.CITY.getSensorName()).toString());
        			deviceStatics.setProvince(sliceMap.get(SensorNameEnum.PROVINCE.getSensorName()).toString());
        			deviceStatics.setAddress(sliceMap.get(SensorNameEnum.ADDRESS.getSensorName()).toString());
        			deviceStatics.setAlarmCount(0);
        			
        			deviceStaticsList.add(deviceStatics);
    			}
    			foundStart = false;
    		}
    	}
    	
    	//当天存在关机状态的情况(如果不存在关机状态则认为全天为开机状态,不生成切片), 最后一个切片有开始时间,没有结束时间
    	if(foundStart && foundWorkStop) {
    		LgDeviceSliceStatics deviceStatics = new LgDeviceSliceStatics();
			deviceStatics.setId(IdGen.uuid());
			deviceStatics.setDeviceNo(deviceNo);
			deviceStatics.setSliceCount(sliceCount++);
			deviceStatics.setSliceStart(sliceStart);
			//deviceStatics.setSliceStop(sliceStop);
			deviceStatics.setSliceRunDuration(0);
			deviceStatics.setSliceWorkDuration(0);
			deviceStatics.setLatitudes(Double.parseDouble(lastMap.get(SensorNameEnum.LATITUDE_NUM.getSensorName()).toString()));
			deviceStatics.setLongitude(Double.parseDouble(lastMap.get(SensorNameEnum.LONGITUDE_NUM.getSensorName()).toString()));
			deviceStatics.setCity(lastMap.get(SensorNameEnum.CITY.getSensorName()).toString());
			deviceStatics.setProvince(lastMap.get(SensorNameEnum.PROVINCE.getSensorName()).toString());
			deviceStatics.setAddress(lastMap.get(SensorNameEnum.ADDRESS.getSensorName()).toString());
			deviceStatics.setAlarmCount(0);
			
			deviceStaticsList.add(deviceStatics);
    	}
    	return deviceStaticsList;
    }
}