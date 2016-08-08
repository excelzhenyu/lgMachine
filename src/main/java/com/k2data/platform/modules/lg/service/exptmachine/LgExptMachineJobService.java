/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.exptmachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.utils.DateUtils;
import com.k2data.platform.modules.lg.common.entity.LgDeviceMapLine;
import com.k2data.platform.modules.lg.common.entity.LgDeviceMapPoint;
import com.k2data.platform.modules.lg.common.entity.LgMapLine;
import com.k2data.platform.modules.lg.common.entity.LgMapPoint;
import com.k2data.platform.modules.lg.common.entity.LgSliceDataRows;
import com.k2data.platform.modules.lg.common.entity.LgSliceDataRowsPoints;
import com.k2data.platform.modules.lg.common.entity.LgSliceDataRowsRsp;
import com.k2data.platform.modules.lg.common.enums.SensorNameEnum;
import com.k2data.platform.modules.lg.common.kmx.KmxClient;
import com.k2data.platform.modules.lg.common.kmx.KmxCond;
import com.k2data.platform.modules.lg.common.kmx.KmxResponseHandler;
import com.k2data.platform.modules.lg.common.utils.MathUtils;
import com.k2data.platform.modules.lg.dao.exptmachine.LgExptMachinePickDao;
import com.k2data.platform.modules.lg.dao.slice.LgDeviceDateStaticsDao;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceDateStatics;

/**
 * 试验机器工作预览Service
 * @author wangshengguo
 * @version 2016-07-21
 */
@Service
@Transactional(readOnly = true)
public class LgExptMachineJobService {
	private final Logger logger = LoggerFactory.getLogger(LgExptMachineJobService.class);
	
	private static final String[] dateSensors = new String[]{
			SensorNameEnum.LATITUDE_NUM.getSensorName(),
			SensorNameEnum.LONGITUDE_NUM.getSensorName(), 
			SensorNameEnum.ADDRESS.getSensorName()};
	
	@Autowired 
	private LgDeviceDateStaticsDao lgDeviceDateStaticsDao;
	@Autowired 
	private LgExptMachinePickDao lgExptMachinePickDao;
	
	//按试验机分组
	public  List<LgDeviceMapPoint> getScatterExptMachine(String[] deviceNo, String dateBegin, String dateEnd ) {

		List<LgDeviceMapPoint> devicePointList = new ArrayList<LgDeviceMapPoint>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("dateBegin", dateBegin);
		queryMap.put("dateEnd", dateEnd);
		
		for(String device : deviceNo) {
			LgDeviceDateStatics lgDeviceDateStatics = new LgDeviceDateStatics();
			lgDeviceDateStatics.setDeviceNo(device);
			lgDeviceDateStatics.setQueryMap(queryMap);
			List<LgDeviceDateStatics> devicelist= lgDeviceDateStaticsDao.findMachineDateStatics(lgDeviceDateStatics);
			List<LgMapPoint> mapPointList = new ArrayList<LgMapPoint>();
			LgDeviceMapPoint deviceMapPoint = new LgDeviceMapPoint();

			for(LgDeviceDateStatics ld : devicelist) {
				LgMapPoint pointMap = new LgMapPoint();
				pointMap.setName(ld.getDeviceNo());
				pointMap.setAddress(ld.getAddress());
				pointMap.setWorkDate(ld.getWorkDate());

				List<Double> point = new ArrayList<Double>();
				point.add(ld.getLongitude());
				point.add(ld.getLatitudes());
				pointMap.setValue(point);
				mapPointList.add(pointMap);
			}
			if(mapPointList.size() > 0) {
				deviceMapPoint.setSeriesName(device);
				deviceMapPoint.setMapPointList(mapPointList);
				devicePointList.add(deviceMapPoint);
			}
		}
		
		return devicePointList; 
	}
	
	//试验机
	public List<LgDeviceMapPoint> getScatterExpt(String[] deviceNo, String dateBegin, String dateEnd ) {
		List<LgDeviceMapPoint> devicePointList = new ArrayList<LgDeviceMapPoint>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("dateBegin", dateBegin);
		queryMap.put("dateEnd", dateEnd);
		queryMap.put("list", Arrays.asList(deviceNo));
		if(deviceNo.length == 0) {
			return devicePointList;
		}
		LgDeviceDateStatics lgDeviceDateStatics = new LgDeviceDateStatics();
		lgDeviceDateStatics.setQueryMap(queryMap);
		List<LgDeviceDateStatics> devicelist= lgDeviceDateStaticsDao.findMachineDateStatics(lgDeviceDateStatics);
		List<LgMapPoint> mapPointList = new ArrayList<LgMapPoint>();
		LgDeviceMapPoint deviceMapPoint = new LgDeviceMapPoint();

		for(LgDeviceDateStatics ld : devicelist) {
			LgMapPoint pointMap = new LgMapPoint();
			pointMap.setName(ld.getDeviceNo());
			pointMap.setAddress(ld.getAddress());
			pointMap.setWorkDate(ld.getWorkDate());

			List<Double> point = new ArrayList<Double>();
			point.add(ld.getLongitude());
			point.add(ld.getLatitudes());
			pointMap.setValue(point);
			mapPointList.add(pointMap);
		}
		if(mapPointList.size() > 0) {
			deviceMapPoint.setSeriesName("试验机");
			deviceMapPoint.setMapPointList(mapPointList);
			devicePointList.add(deviceMapPoint);
		}
		
		return devicePointList; 
	}
	//销售机器
	public List<LgDeviceMapPoint> getScatterSale(String dateBegin, String dateEnd ) {
		List<LgDeviceMapPoint> devicePointList = new ArrayList<LgDeviceMapPoint>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("dateBegin", dateBegin);
		queryMap.put("dateEnd", dateEnd);
		
		LgDeviceDateStatics lgDeviceDateStatics = new LgDeviceDateStatics();
		lgDeviceDateStatics.setQueryMap(queryMap);
		List<LgDeviceDateStatics> devicelist= lgDeviceDateStaticsDao.findSaleMachineDateStatics(lgDeviceDateStatics);
		List<LgMapPoint> mapPointList = new ArrayList<LgMapPoint>();
		LgDeviceMapPoint deviceMapPoint = new LgDeviceMapPoint();

		for(LgDeviceDateStatics ld : devicelist) {
			LgMapPoint pointMap = new LgMapPoint();
			pointMap.setName(ld.getDeviceNo());
			pointMap.setAddress(ld.getAddress());
			pointMap.setWorkDate(ld.getWorkDate());

			List<Double> point = new ArrayList<Double>();
			point.add(ld.getLongitude());
			point.add(ld.getLatitudes());
			pointMap.setValue(point);
			mapPointList.add(pointMap);
		}
		if(mapPointList.size() > 0) {
			deviceMapPoint.setSeriesName("销售机");
			deviceMapPoint.setMapPointList(mapPointList);
			devicePointList.add(deviceMapPoint);
		}
		
		return devicePointList; 
	}
	//试验批次号分组
	public  List<LgDeviceMapPoint> getScatterBatch(String batchNumber, String dateBegin, String dateEnd ) {
		List<LgDeviceMapPoint> devicePointList = new ArrayList<LgDeviceMapPoint>();
		List<String> batchNumberList = new ArrayList<String>();
		
		//如果批次号为空
		if(StringUtils.isBlank(batchNumber)) {
			batchNumberList = lgExptMachinePickDao.getBatchNumberStringList();
		} else {
			String[] batchNumbers = batchNumber.split(",");
			batchNumberList = Arrays.asList(batchNumbers);
		}
		
		Date start = DateUtils.parseDate(dateBegin);
		Date stop = DateUtils.parseDate(dateEnd);
		
		logger.debug(batchNumberList + " ||| " + dateBegin +  " ||| " + dateEnd + " ||| " + start + " ||| " + stop);
		
		for(String  batchNum : batchNumberList) {
			List<LgMapPoint> mapPointList = new ArrayList<LgMapPoint>();
			LgDeviceMapPoint deviceMapPoint = new LgDeviceMapPoint();
			List<LgDeviceDateStatics> lgDeviceDateStatics = lgDeviceDateStaticsDao.findExptMachineDateStatics(start, stop , batchNum);
			
			for(LgDeviceDateStatics device : lgDeviceDateStatics) {
				LgMapPoint pointMap = new LgMapPoint();
				pointMap.setName(device.getDeviceNo());
				pointMap.setAddress(device.getAddress());
				pointMap.setWorkDate(device.getWorkDate());
				List<Double> point = new ArrayList<Double>();
				point.add(device.getLongitude());
				point.add(device.getLatitudes());
				pointMap.setValue(point);
				mapPointList.add(pointMap);
			}
			
			if(mapPointList.size() > 0) {
				deviceMapPoint.setSeriesName(batchNum);
				deviceMapPoint.setMapPointList(mapPointList);
				devicePointList.add(deviceMapPoint);
			}
		}
		
		return devicePointList; 
	}
	
	//按机器筛选
	public List<LgMapPoint> filterMachine(String[] deviceNo, String dateBegin, String dateEnd ) {
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("dateBegin", dateBegin);
		queryMap.put("dateEnd", dateEnd);
		queryMap.put("list", Arrays.asList(deviceNo));
		
		logger.debug(" ||| " + Arrays.asList(deviceNo));
		LgDeviceDateStatics lgDeviceDateStatics = new LgDeviceDateStatics();
		lgDeviceDateStatics.setQueryMap(queryMap);
		
		List<LgDeviceDateStatics> devicelist= lgDeviceDateStaticsDao.findMachineDateStatics(lgDeviceDateStatics);
		List<LgMapPoint> mapPointList = new ArrayList<LgMapPoint>();
		for(LgDeviceDateStatics ld : devicelist) {
			LgMapPoint pointMap = new LgMapPoint();
			pointMap.setName(ld.getDeviceNo());
			pointMap.setAddress(ld.getAddress());
			pointMap.setWorkDate(ld.getWorkDate());

			List<Double> point = new ArrayList<Double>();
			point.add(ld.getLongitude());
			point.add(ld.getLatitudes());
			pointMap.setValue(point);
			mapPointList.add(pointMap);
		}
		return mapPointList;
	}
	
	public Map<String, Object> getExptMachineRunDuration(String batchNumber, String dateBegin, String dateEnd ) {
		List<String> batchNumberList = new ArrayList<String>();
		
		//如果批次号为空
		if(StringUtils.isBlank(batchNumber)) {
			batchNumberList = lgExptMachinePickDao.getBatchNumberStringList();
		} else {
			String[] batchNumbers = batchNumber.split(",");
			batchNumberList = Arrays.asList(batchNumbers);
		}

		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("dateBegin", dateBegin);
		queryMap.put("dateEnd", dateEnd);
		queryMap.put("list", batchNumberList);
		
		LgDeviceDateStatics lgDeviceDateStatics = new LgDeviceDateStatics();
		lgDeviceDateStatics.setQueryMap(queryMap);
		List<LgDeviceDateStatics> deviceRunDurationList= lgDeviceDateStaticsDao.findExptMachineRunDuration(lgDeviceDateStatics);
		List<String> xAxisList = new ArrayList<String>();
		List<Integer> runDurationList = new ArrayList<Integer>();
		for(LgDeviceDateStatics entity : deviceRunDurationList) {
			xAxisList.add(entity.getDeviceNo());
			runDurationList.add(entity.getSliceRunDuration());
		}
		Map<String, Object> runDurationBarMap = new HashMap<String, Object>();
		runDurationBarMap.put("xAxisList", xAxisList);
		runDurationBarMap.put("runDurationList", runDurationList);
		return runDurationBarMap;
	}
	
	public Map<String, Object> getExptMachineRunDuration2(String deviceNos, String dateBegin, String dateEnd ) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		
		String[] deviceNo = deviceNos.split(",");
		queryMap.put("dateBegin", dateBegin);
		queryMap.put("dateEnd", dateEnd);
		queryMap.put("list", Arrays.asList(deviceNo));
		
		LgDeviceDateStatics lgDeviceDateStatics = new LgDeviceDateStatics();
		lgDeviceDateStatics.setQueryMap(queryMap);
		
		List<LgDeviceDateStatics> deviceRunDurationList= lgDeviceDateStaticsDao.findExptMachineRunDuration2(lgDeviceDateStatics);
		List<String> xAxisList = new ArrayList<String>();
		List<Integer> runDurationList = new ArrayList<Integer>();
		for(LgDeviceDateStatics entity : deviceRunDurationList) {
			xAxisList.add(entity.getDeviceNo());
			runDurationList.add(entity.getSliceRunDuration());
		}
		Map<String, Object> runDurationBarMap = new HashMap<String, Object>();
		runDurationBarMap.put("xAxisList", xAxisList);
		runDurationBarMap.put("runDurationList", runDurationList);
		return runDurationBarMap;
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
    
    
    public LgDeviceMapLine getTrackData(final String deviceNo, final String recordTime){
        Date start = DateUtils.parseDate(recordTime + " 00:00:00");
        Date stop = DateUtils.parseDate(recordTime + " 23:59:59");
    
        KmxCond cond = new KmxCond();
        cond.setUrl(Global.getApiDataRowsURL());
        cond.setDeviceNo(deviceNo);
        cond.setSensors(dateSensors);
        cond.setStart(start);
        cond.setStop(stop);
        cond.setSize(""+KmxClient.PAGE_SIZE);
        
        LgSliceDataRowsRsp rsp =  KmxClient.getSync(cond, new KmxResponseHandler() {
            
            @Override
            public void handleResponose(LgSliceDataRowsRsp rsp) {
            }
        });
        
        return convertTrackData(objectToList(rsp));
    }

    private LgDeviceMapLine convertTrackData(List<Map<String, Object>> list) {
		LgDeviceMapLine deviceMapLine = new LgDeviceMapLine();

		if(list == null || list.isEmpty()) {
			return deviceMapLine;
		}
		List<List<LgMapLine>> lineList = new ArrayList<List<LgMapLine>>();
		List<Double> longitudeList = new ArrayList<Double>();
		List<Double> latitudeList = new ArrayList<Double>();
		for(int index = 0; index < list.size()-1; index++) {
		//for(Map<String, Object> map : list) {
			Map<String, Object> trackBeiginMap = list.get(index);
			Map<String, Object> trackEndMap = list.get(index+1);

			String longitude1 = trackBeiginMap.get(SensorNameEnum.LONGITUDE_NUM.getSensorName()).toString();
			String latitude1 = trackBeiginMap.get(SensorNameEnum.LATITUDE_NUM.getSensorName()).toString();
			String longitude2 = trackEndMap.get(SensorNameEnum.LONGITUDE_NUM.getSensorName()).toString();
			String latitude2 = trackEndMap.get(SensorNameEnum.LATITUDE_NUM.getSensorName()).toString();
			
			double longitudeNum1 = Double.parseDouble(StringUtils.isNotBlank(longitude1)?longitude1:"0");
			double latitudeNum1 = Double.parseDouble(StringUtils.isNotBlank(latitude1)?latitude1:"0");
			double longitudeNum2 = Double.parseDouble(StringUtils.isNotBlank(longitude2)?longitude2:"0");
			double latitudeNum2 = Double.parseDouble(StringUtils.isNotBlank(latitude2)?latitude2:"0");
			
			if(index == 0) {
				longitudeList.add(longitudeNum1);
				latitudeList.add(latitudeNum1);
			}
			longitudeList.add(longitudeNum2);
			latitudeList.add(latitudeNum2);
			LgMapLine lgMapLine1 = new LgMapLine();
			LgMapLine lgMapLine2 = new LgMapLine();

			List<Double> coordList1 = new ArrayList<Double>();
			List<Double> coordList2 = new ArrayList<Double>();
			List<LgMapLine> lines = new ArrayList<LgMapLine>();

			if(longitudeNum1 > 0 && latitudeNum1 > 0 && longitudeNum2 > 0 && latitudeNum2 > 0) {
				coordList1.add(longitudeNum1);
				coordList1.add(latitudeNum1);
				lgMapLine1.setCoord(coordList1);
				lines.add(lgMapLine1);

				coordList2.add(longitudeNum2);
				coordList2.add(latitudeNum2);
				lgMapLine2.setCoord(coordList2);
				lines.add(lgMapLine2);
				
				lineList.add(lines);
			}
		}

		List<Double> centerPosition = new ArrayList<Double>();
		long zoomValue = 1;
		Map<String, Object> longitudeMap = MathUtils.computeVarianceHasDegree(longitudeList);
		Map<String, Object> latitudeMap = MathUtils.computeVarianceHasDegree(latitudeList);
		Double longitudeAvg = Double.parseDouble(longitudeMap.get(MathUtils.AVERAGE).toString());
		Double longitudeDValue = Double.parseDouble(longitudeMap.get(MathUtils.MAX_VALUE).toString())
				- Double.parseDouble(longitudeMap.get(MathUtils.MIN_VALUE).toString());

		Double latitudeAvg = Double.parseDouble(latitudeMap.get(MathUtils.AVERAGE).toString());
		Double latitudeDValue = Double.parseDouble(latitudeMap.get(MathUtils.MAX_VALUE).toString())
				- Double.parseDouble(latitudeMap.get(MathUtils.MIN_VALUE).toString());
		centerPosition.add(longitudeAvg);
		centerPosition.add(latitudeAvg);
		double maxDiff = Math.max(longitudeDValue, latitudeDValue);
//		if(maxDiff < 0.001) {
//			deviceMapLine.setSeriesType("scatter");
//		} else {
			deviceMapLine.setSeriesType("lines");
//		}
		zoomValue = Math.round(25 / maxDiff);
	
		deviceMapLine.setMapLineList(lineList);
		deviceMapLine.setCenterPosition(centerPosition);
		deviceMapLine.setZoomValue(zoomValue);
		
		return deviceMapLine;
	}

}
