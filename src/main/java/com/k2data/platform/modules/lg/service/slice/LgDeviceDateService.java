/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.slice;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.common.utils.DateUtils;
import com.k2data.platform.modules.lg.common.entity.LgSliceDataRows;
import com.k2data.platform.modules.lg.common.entity.LgSliceDataRowsPoints;
import com.k2data.platform.modules.lg.common.entity.LgSliceDataRowsRsp;
import com.k2data.platform.modules.lg.common.enums.SensorNameEnum;
import com.k2data.platform.modules.lg.common.enums.SensorStatusEnum;
import com.k2data.platform.modules.lg.common.kmx.KmxClient;
import com.k2data.platform.modules.lg.common.kmx.KmxCond;
import com.k2data.platform.modules.lg.common.kmx.KmxResponseHandler;
import com.k2data.platform.modules.lg.dao.slice.LgDeviceDateDao;
import com.k2data.platform.modules.lg.entity.LgDeviceSliceStatics;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceDateStatics;

/**
 * 设备每日工作统计Service
 * @author wangshengguo
 * @version 2016-07-26
 */
@Service
@Transactional(readOnly = true)
public class LgDeviceDateService extends CrudService<LgDeviceDateDao, LgDeviceDateStatics>{
	
	//开机/工作状态
	private static final String WORK_STATUS = SensorStatusEnum.ACC_STATUS.getOpen();
	private static final String SLEEP_STATUS = SensorStatusEnum.ACC_STATUS.getClose();
	private static final int MINUTE = (60*1000);
	//请求传感器参数
	private static final String[] dateSensors = new String[]{
					SensorNameEnum.ACC_STATUS.getSensorName(),
					SensorNameEnum.LATITUDE_NUM.getSensorName(),
					SensorNameEnum.LONGITUDE_NUM.getSensorName(), 
					SensorNameEnum.CITYCODE.getSensorName(),
					SensorNameEnum.ADDRESS.getSensorName(),
					SensorNameEnum.ENGIN_ROTAT.getSensorName(),
					SensorNameEnum.PROVINCE.getSensorName(),
					SensorNameEnum.CITY.getSensorName()};
	@Autowired
	private LgDeviceDateDao lgDeviceDateDao;
	
	//设备每日统计请求KMX，入口函数
	@Transactional(readOnly = false)
    public void saveAsyncDayData(final String deviceNo, final Date recordTime){
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
            	LgDeviceDateStatics lgDeviceDateStatics = analysisSliceData(objectToList(rsp), deviceNo, recordTime);
            	if(lgDeviceDateStatics == null) {
            		//当天无记录，插入一条0 记录
            		lgDeviceDateStatics = new LgDeviceDateStatics();
            		lgDeviceDateStatics.setDeviceNo(deviceNo);
            		lgDeviceDateStatics.setWorkDate(recordTime);
            		lgDeviceDateStatics.setWorkCount(0);
            		lgDeviceDateStatics.setSliceRunDuration(0);
                	lgDeviceDateStatics.setSliceWorkDuration(0);
                	lgDeviceDateStatics.setRunoffCount(0);
                	lgDeviceDateStatics.setOilSum(0d);
                	lgDeviceDateStatics.setOilAvg(0d);
                	lgDeviceDateStatics.setRotationlSpeedMax(0);
                	lgDeviceDateStatics.setLatitudes(0d);
                	lgDeviceDateStatics.setLongitude(0d);
                	lgDeviceDateStatics.setAlarmCount(0);
            	} 
        		lgDeviceDateDao.saveDateData(lgDeviceDateStatics);
            }
        });
    }
	
	 //同步请求入口
	@Transactional(readOnly = false)
    public void saveSyncDayData(final String deviceNo, final Date recordTime){
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
               	LgDeviceDateStatics lgDeviceDateStatics = analysisSliceData(objectToList(rsp), deviceNo, recordTime);
               	if(lgDeviceDateStatics == null) {
               		//当天无记录，插入一条0 记录
               		lgDeviceDateStatics = new LgDeviceDateStatics();
               		lgDeviceDateStatics.setDeviceNo(deviceNo);
               		lgDeviceDateStatics.setWorkDate(recordTime);
               		lgDeviceDateStatics.setWorkCount(0);
               		lgDeviceDateStatics.setSliceRunDuration(0);
                   	lgDeviceDateStatics.setSliceWorkDuration(0);
                   	lgDeviceDateStatics.setRunoffCount(0);
                   	lgDeviceDateStatics.setOilSum(0d);
                   	lgDeviceDateStatics.setOilAvg(0d);
                   	lgDeviceDateStatics.setRotationlSpeedMax(0);
                   	lgDeviceDateStatics.setLatitudes(0d);
                   	lgDeviceDateStatics.setLongitude(0d);
                   	lgDeviceDateStatics.setAlarmCount(0);
               	} 
           		lgDeviceDateDao.saveDateData(lgDeviceDateStatics);
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
                String value = "null".equals(dataPoints.getValue().toString())?"0":dataPoints.getValue().toString();
                rowsMap.put(sensor, value);
                accList.add(rowsMap);
            }
        }
        
        return accList;
    }
    
    //分析数据，返回实体类
    private LgDeviceDateStatics analysisSliceData(List<Map<String, Object>> sliceList, String deviceNo, Date workDate) {
    	
    	if(sliceList == null || sliceList.isEmpty()) {
    		return null;
    	}
    	
    	boolean foundWorkBegin = false; //找到当天初次开机时间
    	boolean foundWorkStop = false; //找到当天最后一次工作结束时间
    	boolean foundStart = false;	//找到某个切片的开始时间
    	boolean foundStop = false;	//找到某个切片的结束时间
    	
    	Date workBegin = null; //当天初次开机时间
    	Date workStop = null; //当天最后一次工作结束时间
    	Date tmpWorkBegin = null; //临时开始时间
    	Date tmpWorkStop = null; //临时结束时间
    	
    	//最后一个,用于获取最后经纬度地点等
    	Map<String, Object> lastMap = sliceList.get(sliceList.size() - 1);

    	int sliceRunDuration = 0;
    	int runOffCount = 0;
    	int rotationlSpeedMax = 0;
    	
    	for(Map<String, Object> sliceMap : sliceList) {
    		
    		int speed = (int)Double.parseDouble(sliceMap.get(SensorNameEnum.ENGIN_ROTAT.getSensorName()).toString());
    		if(speed > rotationlSpeedMax) {
    			rotationlSpeedMax = speed;
    		}
    		if(WORK_STATUS.equals(sliceMap.get(SensorNameEnum.ACC_STATUS.getSensorName()).toString())) {
    			//找到第一个开机状态
    			if(!foundWorkBegin) {
    				workBegin = (Date)sliceMap.get("iso");
    				foundWorkBegin = true;
    			}
    			//找到一个开始时间
    			if(!foundStart) {
        			tmpWorkBegin = (Date)sliceMap.get("iso");
        			foundStart = true;
        			runOffCount++;
    			}
    			foundStop = false;
    			foundWorkStop = false;
    		} else if(SLEEP_STATUS.equals(sliceMap.get(SensorNameEnum.ACC_STATUS.getSensorName()).toString())) {
    			if(foundWorkBegin) {
    				//找到结束状态
    				if(!foundWorkStop) {
    					workStop = (Date)sliceMap.get("iso");
    					foundWorkStop = true;
    				}
    				
    				//找到一个结束时间
        			if(!foundStop) {
            			tmpWorkStop = (Date)sliceMap.get("iso");
            			sliceRunDuration += (int)Math.round((tmpWorkStop.getTime()-tmpWorkBegin.getTime())/MINUTE);
            			foundStop = true;
        			}
        			foundStart = false;
    			}
    		}
    	}
    	//没有开始时间，全天无开机工作数据
    	if(workBegin == null) {
    		return null;
    	}
    	LgDeviceDateStatics lgDeviceDateStatics = new LgDeviceDateStatics();
    	lgDeviceDateStatics.setWorkBegin(workBegin);
    	if(workStop == null) {
    		//考虑有开机时间,没有关机时间的情况
    		workStop = DateUtils.parseDate(DateUtils.formatDate(workBegin) + " 23:59:59");
    		if(tmpWorkBegin != null) {
    			sliceRunDuration +=  (int)Math.round((workStop.getTime()-tmpWorkBegin.getTime())/MINUTE);
    		}
    	}
		lgDeviceDateStatics.setWorkCount(1); 
    	lgDeviceDateStatics.setDeviceNo(deviceNo);
    	lgDeviceDateStatics.setWorkDate(workDate);
    	lgDeviceDateStatics.setWorkStop(workStop);
    	lgDeviceDateStatics.setSliceRunDuration(sliceRunDuration);
    	lgDeviceDateStatics.setSliceWorkDuration(sliceRunDuration);
    	lgDeviceDateStatics.setRunoffCount(runOffCount);
    	lgDeviceDateStatics.setOilSum(0d);
    	lgDeviceDateStatics.setOilAvg(0d);
    	lgDeviceDateStatics.setRotationlSpeedMax(rotationlSpeedMax);
    	lgDeviceDateStatics.setLatitudes(Double.parseDouble(lastMap.get(SensorNameEnum.LATITUDE_NUM.getSensorName()).toString()));
    	lgDeviceDateStatics.setLongitude(Double.parseDouble(lastMap.get(SensorNameEnum.LONGITUDE_NUM.getSensorName()).toString()));
    	lgDeviceDateStatics.setCity(lastMap.get(SensorNameEnum.CITY.getSensorName()).toString());
    	lgDeviceDateStatics.setProvince(lastMap.get(SensorNameEnum.PROVINCE.getSensorName()).toString());
    	lgDeviceDateStatics.setAddress(lastMap.get(SensorNameEnum.ADDRESS.getSensorName()).toString());
    	lgDeviceDateStatics.setAlarmCount(0);
    	
    	return lgDeviceDateStatics;
    }
    
}