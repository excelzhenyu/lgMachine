/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.distribution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineDistributionBarData;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineDistributionEntity;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineOrderDistributionByCityBar;
import com.k2data.platform.modules.sys.utils.DictUtils;
import com.k2data.platform.modules.lg.common.utils.CalendarUtils;
import com.k2data.platform.modules.lg.dao.citylist.LgMapCityDao;
import com.k2data.platform.modules.lg.dao.distribution.LgMachineDistributionBarDao;

/**
 * 机器柱状图分布Service
 * @author wangshengguo
 * @version 2016-07-08
 */
@Service
@Transactional(readOnly = true)
public class LgMachineDistributionBarService extends CrudService<LgMachineDistributionBarDao, LgMachineOrderDistributionByCityBar> {

	@Autowired
	private LgMachineDistributionBarDao lgMachineDistributionBarDao;
	@Autowired
	private LgMapCityDao lgMapCityDao;
	
	@Transactional(readOnly = false)
	public int saveMachineOrderDistributionByCity(int year, int month) {
		return lgMachineDistributionBarDao.saveMachineOrderDistributionByCity(CalendarUtils.genMonthNumber(year, month));
	}
	
	@Transactional(readOnly = false)
	public int saveMachineOrderDistributionByProvince(int year, int month) {
		return lgMachineDistributionBarDao.saveMachineOrderDistributionByProvince(CalendarUtils.genMonthNumber(year, month));
	}
	
	@Transactional(readOnly = false)
	public int saveMachineTypeDistributionByCity(int year, int month) {
		return lgMachineDistributionBarDao.saveMachineTypeDistributionByCity(CalendarUtils.genMonthNumber(year, month));
	}
	
	@Transactional(readOnly = false)
	public int saveMachineTypeDistributionByProvince(int year, int month) {
		return lgMachineDistributionBarDao.saveMachineTypeDistributionByProvince(CalendarUtils.genMonthNumber(year, month));
	}
	//排序
	private List<String> sortMapCity(Map<String, Integer> map) {
		//排序城市列表
        List<Map.Entry<String,Integer>> sortCityMapEntryList = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());

        Collections.sort(sortCityMapEntryList,new Comparator<Map.Entry<String,Integer>>() {
            //降序排序
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        System.out.println(sortCityMapEntryList);
        List<String> cityList = new ArrayList<String>();
        for(Map.Entry<String,Integer> entry : sortCityMapEntryList) {
        	cityList.add(entry.getKey());
        }
        return cityList;
	}
	//生成bar数据
	private Map<String, Object>  genBarData(LgMachineDistributionEntity entity, List<LgMachineDistributionBarData> barDataList, List<String> mapCityNameList) {

		//初始化一个对应地图地区的map，初始number为0
		Map<String, Integer> numberInitMap = new HashMap<String, Integer>();
		for(String mapCityName : mapCityNameList) {
			numberInitMap.put(mapCityName, 0);
		}
		//构建城市名称排序，默认为城市地图中的城市数据，或许填入数据后，根据数量总和排序
		Map<String, Integer> sortCityName = new HashMap<String, Integer>(numberInitMap);
		//构建一个key为productType or orderNumber的map
		Map<String, Map<String, Integer>> cityProductMap = new HashMap<String, Map<String, Integer>>();

		List<String> cityNameList = new ArrayList<String>();

		for(LgMachineDistributionBarData barData : barDataList) {
			//clone numberInitMap
			Map<String, Integer> numberMap = new HashMap<String, Integer>(numberInitMap);

			String dataType = barData.getDataType();
			String cityName = barData.getCityName();
			Integer dataCount = barData.getDataCount();
			Integer sumCount = 0;
			if(sortCityName.get(cityName) != null) {
				sumCount = dataCount + sortCityName.get(cityName);
			} 
			sortCityName.put(cityName, sumCount);
			
			if(cityProductMap.get(dataType) == null) {
				cityProductMap.put(dataType,numberMap);
			} 
			cityProductMap.get(dataType).put(cityName, dataCount);
		}
		//排序 城市名称
		List<String> xAxisData = sortMapCity(sortCityName);
		
        //List<Map<String,List<Integer>>> seriesList = new ArrayList<Map<String,List<Integer>>>();
		//使用treeMap key排序
		Map<String,List<Integer>> seriesMap = new TreeMap<String,List<Integer>>(
				new Comparator<String>() {  
	                @Override  
	                public int compare(String o1, String o2) {  
	                    return o1.compareTo(o2);//升序  
	                }  
	            }); 
		Iterator<Entry<String, Map<String, Integer>>> mapIt = cityProductMap.entrySet().iterator();
		while(mapIt.hasNext()) {
			Map.Entry<String, Map<String, Integer>> mapEntry = mapIt.next();
			String key = mapEntry.getKey();
			cityNameList.add(key);
			Map<String, Integer> value = mapEntry.getValue();
			List<Integer> seriesData = new ArrayList<Integer>();
			
			for(String cname : xAxisData) {
				seriesData.add(value.get(cname));
			}
			seriesMap.put(key, seriesData);
			//seriesList.add(seriesMap);
		}
		Map<String, Object> barData = new HashMap<String, Object>();
		//如果没有数据
		if(seriesMap.size() == 0) {
			String machineType = entity.getMachineType();
			String machineName = DictUtils.getDictLabels(machineType,"machineType", "");
			List<Integer> zeroList = new ArrayList<Integer>();
			for(String name : xAxisData) {
				zeroList.add(0);
			}
			seriesMap.put(machineName, zeroList);
		}
		barData.put("seriesMap", seriesMap);
		barData.put("xAxisData", xAxisData);
		return barData;
	}
	
	public Map<String, Object> getMachineSeriesData(LgMachineDistributionEntity entity) {
		String machineType = entity.getMachineType();
		String productType = entity.getProductType();
		String location = entity.getLocation();
		
		//地图城市列表
		List<String> mapCityNameList = new ArrayList<String>();
		//bar数据
		List<LgMachineDistributionBarData> barDataList = new ArrayList<LgMachineDistributionBarData>();
		
		if(StringUtils.isBlank(location)) {
			mapCityNameList = lgMapCityDao.getProvinceList();
			if(StringUtils.isNotBlank(machineType) && StringUtils.isBlank(productType)) {
				barDataList = lgMachineDistributionBarDao.getProvinceMachineTypeBarData(entity);
			} else {
				barDataList = lgMachineDistributionBarDao.getProvinceMachineOrderBarData(entity);
			}
			
		} else {
			mapCityNameList = lgMapCityDao.getCityListByParentName(location);
			if(mapCityNameList.size() == 0) {
				mapCityNameList.add(location);
			}
			if(StringUtils.isNotBlank(machineType) && StringUtils.isBlank(productType)) {
				barDataList = lgMachineDistributionBarDao.getCityMachineTypeBarData(entity);
			} else {
				barDataList = lgMachineDistributionBarDao.getCityMachineOrderBarData(entity);
			}
		}

		return genBarData(entity, barDataList, mapCityNameList);
	}
	
	
}