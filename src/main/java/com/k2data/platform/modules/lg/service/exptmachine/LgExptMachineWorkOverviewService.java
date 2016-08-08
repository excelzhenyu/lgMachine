/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.exptmachine;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.k2data.platform.modules.lg.common.utils.MathUtils;
import com.k2data.platform.modules.lg.common.utils.OkhttpUtils;
import com.k2data.platform.modules.lg.dao.slice.LgDeviceDateStaticsDao;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceDateStatics;
import com.k2data.platform.modules.lg.entity.slice.LgSliceTempDataSection;

/**
 * 试验机器工作预览Service
 * @author wangshengguo
 * @version 2016-07-21
 */
@Service
@Transactional(readOnly = true)
public class LgExptMachineWorkOverviewService {
	@Autowired 
	private LgDeviceDateStaticsDao lgDeviceDateStaticsDao;
	
	public  List<LgDeviceMapPoint> getPointData(String[] deviceNo, String dateBegin, String dateEnd ) {
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
				pointMap.setName(DateUtils.formatDate(ld.getWorkDate()) + "<br />" + ((ld.getAddress()==null || "".equals(ld.getAddress()) )?"暂无":ld.getAddress()));

				List<Double> point = new ArrayList<Double>();
				point.add(ld.getLongitude());
				point.add(ld.getLatitudes());
				pointMap.setValue(point);
				mapPointList.add(pointMap);
			}
			if(mapPointList.size() > 0) {
				deviceMapPoint.setDevice(device);
				deviceMapPoint.setMapPointList(mapPointList);
				devicePointList.add(deviceMapPoint);
			}
		}
		
		return devicePointList; 
	}
	
	private List<Map<String, Object>> convertData(Map<String, LgSliceDataRowsRsp> pageDataRowsRspMap, int pageNum) {
		
		List<Map<String, Object>> sensorValueList = new ArrayList<Map<String, Object>>();
		//如果获取到的页数不足
		if(pageDataRowsRspMap.size() != pageNum) {
			return sensorValueList;
		}
		for(int index = 1; index < pageNum+1; index++) {
			LgSliceDataRowsRsp pageRsp = pageDataRowsRspMap.get(""+index);
			if(pageRsp != null && pageRsp.getCode() == 0) {
				for(LgSliceDataRows dataRows : pageRsp.getDataRows()) {
					Map<String, Object> sensorValueMap = new HashMap<String, Object>();
					for(LgSliceDataRowsPoints dataPoints: dataRows.getDataPoints()) {
						String sensor = dataPoints.getSensor();
						String value = "null".equals(dataPoints.getValue().toString())?"":dataPoints.getValue().toString();
						sensorValueMap.put(sensor, value);
					}
					sensorValueList.add(sensorValueMap);
				}
				System.out.println("==sensorValueList===" + sensorValueList );
			}
		}
		return sensorValueList;
	}
//	private List<LgMapTrack> convertTrackData(List<Map<String, Object>> list) {
//		List<LgMapTrack> lgMapTrackList = new ArrayList<LgMapTrack>();
//		for(Map<String, Object> map : list) {
//			List<Double> coordList = new ArrayList<Double>();
//			String longitude = map.get("longitude_num").toString();
//			String latitude = map.get("latitude_num").toString();
//			String cityCode = "0" + (int)Double.parseDouble(StringUtils.isNotBlank(map.get("citycode").toString())?map.get("citycode").toString():"0");
//			double longitudeNum = Double.parseDouble(StringUtils.isNotBlank(longitude)?longitude:"0");
//			double latitudeNum = Double.parseDouble(StringUtils.isNotBlank(latitude)?latitude:"0");
//			
//			LgMapTrack lgMapTrack = new LgMapTrack();
//			if(longitudeNum > 0 && latitudeNum > 0) {
//				coordList.add(longitudeNum);
//				coordList.add(latitudeNum);
//				lgMapTrack.setCoord(coordList);
//				lgMapTrack.setCityCode(cityCode);
//			}
//			lgMapTrackList.add(lgMapTrack);
//		}
//		
//		return lgMapTrackList;
//	}

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

			String longitude1 = trackBeiginMap.get("longitude_num").toString();
			String latitude1 = trackBeiginMap.get("latitude_num").toString();
			String longitude2 = trackEndMap.get("longitude_num").toString();
			String latitude2 = trackEndMap.get("latitude_num").toString();
			
			//String cityCode = "0" + (int)Double.parseDouble(StringUtils.isNotBlank(map.get("citycode").toString())?map.get("citycode").toString():"0");
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
		if(maxDiff < 0.001) {
			deviceMapLine.setSeriesType("scatter");
		} else {
			deviceMapLine.setSeriesType("lines");
		}
		zoomValue = Math.round(25 / maxDiff);
	
		deviceMapLine.setMapLineList(lineList);
		deviceMapLine.setCenterPosition(centerPosition);
		deviceMapLine.setZoomValue(zoomValue);
		
		return deviceMapLine;
	}
	
	public LgDeviceMapLine getLineData(String deviceNo, String lineDate) {

		LgDeviceMapLine deviceMapLine = new LgDeviceMapLine();
		
		LgSliceTempDataSection sliceData = new LgSliceTempDataSection();
		sliceData.setDeviceNum(deviceNo);
		sliceData.setRecordTime(DateUtils.parseDate(lineDate));
		
		Date start = DateUtils.parseDate(lineDate + " 00:00:00");
		Date stop = DateUtils.parseDate(lineDate + " 23:59:59");
		String[] sensors = new String[]{"latitude_num","longitude_num","citycode"};
		String[] aggregations = new String[]{"count"};
		
		String url = OkhttpUtils.joinURL(Global.getApiDataRowsURL(), OkhttpUtils.genSelect(deviceNo,sensors, start, stop), null, OkhttpUtils.genAggregation(aggregations), null, null, null);
		LgSliceDataRowsRsp rowsRsp = OkhttpUtils.getRowsObject(url, LgSliceDataRowsRsp.class);
		
		System.out.println(url);

		if(rowsRsp != null) {
			//记录数
			int count = Integer.valueOf(rowsRsp.getDataRows().get(0).getAggregationResults().get(0).getCount().toString());
			//计算分页数
			int pageNum = count/OkhttpUtils.PAGE_SIZE + 1;
			//放置分页返回值，key为页码，value为每页的值列表
			Map<String, LgSliceDataRowsRsp> pageDataRowsRspMap = new HashMap<String, LgSliceDataRowsRsp>();
			//分页请求，根据页码放至在map中
			for(int page = 1; page < pageNum+1; page++) {
				String pageUrl = OkhttpUtils.joinURL(Global.getApiDataRowsURL(), OkhttpUtils.genSelect(deviceNo,sensors, start, stop), null,null,null, OkhttpUtils.PAGE_SIZE, page);
				System.out.println("=====pageUrl=====" + pageUrl);
				pageDataRowsRspMap.put(""+page,  OkhttpUtils.getRowsObject(pageUrl+"&asdas=dasd", LgSliceDataRowsRsp.class));
			} 
			System.out.println("===pageDataRowsRspMap==" + pageDataRowsRspMap);
			List<Map<String, Object>> sensorValueList = convertData(pageDataRowsRspMap, pageNum);
			if(sensorValueList.isEmpty()) {
				//TODO 写错误日志 ,不继续以下操作
				
			} else {
				deviceMapLine = convertTrackData(sensorValueList);
			}
		}
		return deviceMapLine;
	}
	
}
