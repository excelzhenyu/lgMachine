/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.citylist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.common.utils.IdGen;
import com.k2data.platform.modules.lg.entity.citylist.LgCityComparison;
import com.k2data.platform.modules.lg.entity.citylist.LgMapCity;
import com.k2data.platform.modules.lg.entity.citylist.LgSystemCity;
import com.k2data.platform.modules.lg.dao.citylist.LgCityComparisonDao;
import com.k2data.platform.modules.lg.dao.citylist.LgMapCityDao;
import com.k2data.platform.modules.lg.dao.citylist.LgSystemCityDao;

/**
 * 业务销售城市与地图城市对照Service
 * @author wangshengguo
 * @version 2016-06-06
 */
@Service
@Transactional(readOnly = true)
public class LgCityComparisonService extends CrudService<LgCityComparisonDao, LgCityComparison> {

	@Autowired
	private LgCityComparisonDao lgCityComparisonDao;
	@Autowired
	private LgMapCityDao lgMapCityDao;
	@Autowired
	private LgSystemCityDao lgSystemCityDao;
	
	public LgCityComparison get(String id) {
		return super.get(id);
	}
	
	public List<LgCityComparison> findList(LgCityComparison lgCityComparison) {
		return super.findList(lgCityComparison);
	}
	
	public Page<LgCityComparison> findPage(Page<LgCityComparison> page, LgCityComparison lgCityComparison) {
		return super.findPage(page, lgCityComparison);
	}
	
	@Transactional(readOnly = false)
	public void save(LgCityComparison lgCityComparison) {
		super.save(lgCityComparison);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgCityComparison lgCityComparison) {
		super.delete(lgCityComparison);
	}
	
	public List<LgCityComparison> findListFromCity() {
		return lgCityComparisonDao.findListFromCity();
	}
	@Transactional(readOnly = false)
	public int importData() {
		 List<LgCityComparison> srcList = new ArrayList<LgCityComparison>();
		 List<LgCityComparison> destList = new ArrayList<LgCityComparison>();

		 srcList = lgCityComparisonDao.findListFromCity();
		 for(LgCityComparison city : srcList) {
			 LgCityComparison destCity = new LgCityComparison();
			 destCity.setId(IdGen.uuid());
			 destCity.setMapCityId(city.getMapCityId());
			 destCity.setSystemCityId(city.getSystemCityId());
			 destCity.setIsEffective(city.getIsEffective());
			 destList.add(destCity);
		 }
		 if (destList != null && destList.size() > 0) {
			 return lgCityComparisonDao.saveBatch(destList);
		 }
		 return 0;
	}
	
	public List<LgMapCity> findMapCityList(LgMapCity lgMapCity ) {
		return lgCityComparisonDao.findMapCityList(lgMapCity);
	}
	
	public List<LgSystemCity> findSystemCityList(LgSystemCity lgSystemCity) {
		return lgCityComparisonDao.findSystemCityList(lgSystemCity);
	}
	
	
}