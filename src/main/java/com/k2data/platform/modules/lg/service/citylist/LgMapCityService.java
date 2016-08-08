/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.citylist;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.citylist.LgMapCity;
import com.k2data.platform.modules.lg.entity.citylist.LgTreeSortEntity;
import com.k2data.platform.modules.lg.dao.citylist.LgMapCityDao;

/**
 * 地图城市列表Service
 * @author wangshengguo
 * @version 2016-06-02
 */
@Service
@Transactional(readOnly = true)
public class LgMapCityService extends CrudService<LgMapCityDao, LgMapCity> {

	@Autowired
	private LgMapCityDao lgMapCityDao;
	public LgMapCity get(String id) {
		return super.get(id);
	}
	
	public List<LgMapCity> findList(LgMapCity lgMapCity) {
		return super.findList(lgMapCity);
	}
	
	public Page<LgMapCity> findPage(Page<LgMapCity> page, LgMapCity lgMapCity) {
		return super.findPage(page, lgMapCity);
	}
	
	@Transactional(readOnly = false)
	public void save(LgMapCity lgMapCity) {
		super.save(lgMapCity);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgMapCity lgMapCity) {
		super.delete(lgMapCity);
	}
	@Transactional(readOnly = false)
	public void move(LgTreeSortEntity lgTreeSortEntity) {
		String moveType = lgTreeSortEntity.getMoveType();
		String oldParentId = lgTreeSortEntity.getOldParentId();
		String newParentId = lgTreeSortEntity.getNewParentId();
		Integer nodeSort = lgTreeSortEntity.getNodeSort();
		Integer targetSort = lgTreeSortEntity.getTargetSort(); 
		
		if("inner".equals(moveType)) {
			lgTreeSortEntity.setNewParentId(lgTreeSortEntity.getTargetId());
			lgMapCityDao.moveInner(lgTreeSortEntity);
		} else if("prev".equals(moveType)) {
			if(oldParentId != null && oldParentId.equals(newParentId)) {
				if(targetSort > nodeSort) {
					lgMapCityDao.movePrevB2E(lgTreeSortEntity);
				} else {
					lgMapCityDao.movePrevE2B(lgTreeSortEntity);
				}
			} else {
				lgMapCityDao.moveInnerPrev(lgTreeSortEntity);
			}
		} else if("next".equals(moveType)) {
			if(oldParentId != null && oldParentId.equals(newParentId)) {
				if(targetSort > nodeSort) {
					lgMapCityDao.moveNextB2E(lgTreeSortEntity);
				} else {
					lgMapCityDao.moveNextE2B(lgTreeSortEntity);
				}
			} else {
				lgMapCityDao.moveInnerNext(lgTreeSortEntity);
			}
		}
	}
	public int haveComparison(LgMapCity lgMapCity) {
		return lgMapCityDao.haveComparison(lgMapCity);
	}
	
	public String findCityCode(String cityName) {
		return lgMapCityDao.findCityCode(cityName);
	}
	
	public List<String> getProvinceList() {
		return lgMapCityDao.getProvinceList();
	}
}