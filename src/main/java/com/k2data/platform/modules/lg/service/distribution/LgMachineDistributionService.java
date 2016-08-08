/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.distribution;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineDistributionByProvince;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineDistributionData;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineDistributionEntity;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineOrderDistributionByCity;
import com.k2data.platform.modules.sys.utils.DictUtils;
import com.k2data.platform.modules.lg.common.utils.CalendarUtils;
import com.k2data.platform.modules.lg.dao.distribution.LgMachineDistributionDao;

/**
 * 机器热度分布Service
 * @author wangshengguo
 * @version 2016-07-08
 */
@Service
@Transactional(readOnly = true)
public class LgMachineDistributionService extends CrudService<LgMachineDistributionDao, LgMachineOrderDistributionByCity> {

	@Autowired
	private LgMachineDistributionDao lgMachineDistributionDao;

	@Transactional(readOnly = false)
	public int saveMachineOrderDistributionByCity(int year, int month) {
		return lgMachineDistributionDao.saveMachineOrderDistributionByCity(CalendarUtils.genMonthNumber(year, month));
	}
	
	@Transactional(readOnly = false)
	public int saveMachineOrderDistributionByProvince(int year, int month) {
		return lgMachineDistributionDao.saveMachineOrderDistributionByProvince(CalendarUtils.genMonthNumber(year, month));
	}
	
	@Transactional(readOnly = false)
	public int saveProductTypeDistributionByCity(int year, int month) {
		return lgMachineDistributionDao.saveProductTypeDistributionByCity(CalendarUtils.genMonthNumber(year, month));
	}
	@Transactional(readOnly = false)
	public int saveProductTypeDistributionByProvince(int year, int month) {
		return lgMachineDistributionDao.saveProductTypeDistributionByProvince(CalendarUtils.genMonthNumber(year, month));
	}
	
	@Transactional(readOnly = false)
	public int saveMachineTypeDistributionByCity(int year, int month) {
		return lgMachineDistributionDao.saveMachineTypeDistributionByCity(CalendarUtils.genMonthNumber(year, month));
	}
	
	@Transactional(readOnly = false)
	public int saveMachineTypeDistributionByProvince(int year, int month) {
		return lgMachineDistributionDao.saveMachineTypeDistributionByProvince(CalendarUtils.genMonthNumber(year, month));
	}
	
	@Transactional(readOnly = false)
	public int saveMachineTypeDistributionByCountry(int year, int month) {
		return lgMachineDistributionDao.saveMachineTypeDistributionByCountry(CalendarUtils.genMonthNumber(year, month));
	}
	

	private Map<String, Object> genMapData(LgMachineDistributionEntity entity, List<LgMachineDistributionData> mapDataList) {
		String densityType = entity.getDensityType();
		int maxMapData = 0;
		List<Map<String, Object>> seriesDataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapDataMap = new HashMap<String, Object>();

		for(LgMachineDistributionData mapData : mapDataList) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("name", mapData.getCityName());
			Object value = 0;
			if("1".equals(densityType)) {
				value = mapData.getRunDurationAvg();
			} else if("2".equals(densityType)) {
				value = mapData.getRunTimesAvg();
			}
			int tmpData = (int)Math.ceil(Double.parseDouble(value.toString()));
			dataMap.put("value",value);
			maxMapData = maxMapData < tmpData?tmpData:maxMapData;
			
			seriesDataList.add(dataMap);
		}
		String 	machineName = DictUtils.getDictLabels(entity.getMachineType(),"machineType", "");
		int len = String.valueOf(maxMapData).length();
		int ceilValue = (int)Math.pow(10,len);
		maxMapData = (maxMapData / (int)Math.pow(10,len-1) < 5)?ceilValue/2 : ceilValue;
		mapDataMap.put("dataList", seriesDataList);
		mapDataMap.put("seriesName", machineName);
		mapDataMap.put("maxValue", maxMapData);
		return mapDataMap;
	}
	public Map<String, Object> getMachineSeriesData(LgMachineDistributionEntity entity) {
		String machineType = entity.getMachineType();
		String productType = entity.getProductType();
		String orderNumber = entity.getOrderNumber();
		String location = entity.getLocation();
		
		//地图数据
		List<LgMachineDistributionData> mapDataList = new ArrayList<LgMachineDistributionData>();
		
		if(StringUtils.isBlank(location)) {
			if(StringUtils.isNotBlank(machineType) && StringUtils.isBlank(productType)) {
				mapDataList = lgMachineDistributionDao.getProvinceMachineTypeData(entity);
			} else if(StringUtils.isNotBlank(productType) && StringUtils.isBlank(orderNumber)){
				mapDataList = lgMachineDistributionDao.getProvinceProductTypeData(entity);
			} else if(StringUtils.isNotBlank(orderNumber)){
				mapDataList = lgMachineDistributionDao.getProvinceMachineOrderData(entity);
			}
		} else {
			if(StringUtils.isNotBlank(machineType) && StringUtils.isBlank(productType)) {
				mapDataList = lgMachineDistributionDao.getCityMachineTypeData(entity);
			} else if(StringUtils.isNotBlank(productType) && StringUtils.isBlank(orderNumber)){
				mapDataList = lgMachineDistributionDao.getCityProductTypeData(entity);
			} else if(StringUtils.isNotBlank(orderNumber)){
				mapDataList = lgMachineDistributionDao.getCityMachineOrderData(entity);
			}
		}
		return genMapData(entity, mapDataList);
	}
	
	public Map<String, Object> getMapMachineNumBar(LgMachineDistributionEntity lgMachineDistributionEntity) {

		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		String localtion = lgMachineDistributionEntity.getLocation();
		String machineType = lgMachineDistributionEntity.getMachineType();
		Date runMonth = lgMachineDistributionEntity.getRunMonth();
		
		LgMachineDistributionByProvince lgMachineDistributionByProvince = new LgMachineDistributionByProvince();
		lgMachineDistributionByProvince.setMachineType(machineType);
		lgMachineDistributionByProvince.setYearMonthId(CalendarUtils.genMonthNumber(runMonth));
		lgMachineDistributionByProvince.setProvinceName(localtion);
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		
		queryMap.put("list", CalendarUtils.getRecentMonthNumberList24());
		lgMachineDistributionByProvince.setQueryMap(queryMap);
		List<LgMachineDistributionByProvince> distList = lgMachineDistributionDao.getMapMachineNumBar(lgMachineDistributionByProvince);
		
		Map<Integer, Object> recentMonthDataMap = CalendarUtils.getRecentMonthNameInitDataMap24();

		for( LgMachineDistributionByProvince province : distList) {
			recentMonthDataMap.put(province.getYearMonthId(), province.getProvinceMachineSum());
		}
		List<Integer> monthDataList  = new ArrayList<Integer>(recentMonthDataMap.keySet());  
		List<Object> seriesDataList  = new ArrayList<Object>(recentMonthDataMap.values());  
		List<String> aAxisDataList = new ArrayList<String>();
		for(Integer monthNumber : monthDataList) {
			aAxisDataList.add(CalendarUtils.genMonthName(monthNumber));
		}
		dataMap.put("seriesData", seriesDataList);
		dataMap.put("xAxisData", aAxisDataList);
		return dataMap;
	}
	
	private Map<String, Object> gentMapCompareBarData(LgMachineDistributionEntity entity, List<LgMachineDistributionData> mapProvinceDataList, List<LgMachineDistributionData> mapCityDataList) {
		String densityType = entity.getDensityType();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<Object> aAxisDataList = new ArrayList<Object>();
		List<Object> citySeriesData = new ArrayList<Object>();
		List<Object> provinceSeriesData = new ArrayList<Object>();
		List<Object> machineSriesData = new ArrayList<Object>();
		List<Object> barLegendList = new ArrayList<Object>();
		
		Object runValue = mapProvinceDataList.size() > 0?mapProvinceDataList.get(0).getRunDurationAvg():0;
		if("1".equals(densityType)) {
			for( LgMachineDistributionData city : mapCityDataList) {
				aAxisDataList.add(city.getCityName());
				//按平均开工时长
				citySeriesData.add(city.getRunDurationAvg());
				provinceSeriesData.add(runValue);
				machineSriesData.add(city.getMachineSum());
			}
		} else if("2".equals(densityType)){
			for( LgMachineDistributionData city : mapCityDataList) {
				aAxisDataList.add(city.getCityName());
				//按平均开工次数
				citySeriesData.add(city.getRunTimesAvg());
				provinceSeriesData.add(runValue);
				machineSriesData.add(city.getMachineSum());
			}
		}
		String densityName = DictUtils.getDictLabels(densityType,"densityType", "");
		barLegendList.add("主机数量");
		barLegendList.add("省内"+ densityName);
		barLegendList.add("地区"+ densityName);
		
		dataMap.put("barLegendList", barLegendList);
		dataMap.put("citySeriesData", citySeriesData);
		dataMap.put("machineSriesData", machineSriesData);
		dataMap.put("provinceSeriesData", provinceSeriesData);
		dataMap.put("xAxisData", aAxisDataList);
		
		return dataMap;
	}
	public Map<String, Object> getMapCompareBarData(LgMachineDistributionEntity entity) {
		String machineType = entity.getMachineType();
		String productType = entity.getProductType();
		String orderNumber = entity.getOrderNumber();
		//String location = entity.getLocation();
		
		//地图数据
		List<LgMachineDistributionData> mapProvinceDataList = new ArrayList<LgMachineDistributionData>();
		List<LgMachineDistributionData> mapCityDataList = new ArrayList<LgMachineDistributionData>();

		if(StringUtils.isNotBlank(machineType) && StringUtils.isBlank(productType)) {
			mapProvinceDataList = lgMachineDistributionDao.getProvinceMachineTypeData(entity);
			mapCityDataList = lgMachineDistributionDao.getCityMachineTypeData(entity);
		} else if(StringUtils.isNotBlank(productType) && StringUtils.isBlank(orderNumber)){
			mapProvinceDataList = lgMachineDistributionDao.getProvinceProductTypeData(entity);
			mapCityDataList = lgMachineDistributionDao.getCityProductTypeData(entity);
		} else if(StringUtils.isNotBlank(orderNumber)){
			mapProvinceDataList = lgMachineDistributionDao.getProvinceMachineOrderData(entity);
			mapCityDataList = lgMachineDistributionDao.getCityMachineOrderData(entity);
		}
		return gentMapCompareBarData(entity, mapProvinceDataList, mapCityDataList);	
	}

}