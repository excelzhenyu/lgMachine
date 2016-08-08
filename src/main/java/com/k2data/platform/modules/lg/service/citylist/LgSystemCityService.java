/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.citylist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.citylist.LgMapCity;
import com.k2data.platform.modules.lg.entity.citylist.LgSystemCity;
import com.k2data.platform.modules.lg.entity.citylist.LgTreeSortEntity;
import com.k2data.platform.modules.lg.dao.citylist.LgSystemCityDao;

/**
 * 业务系统城市列表Service
 * @author wangshengguo
 * @version 2016-06-01
 */
@Service
@Transactional(readOnly = true)
public class LgSystemCityService extends CrudService<LgSystemCityDao, LgSystemCity> {
	@Autowired
	private LgSystemCityDao lgSystemCityDao;
	
	public LgSystemCity get(String id) {
		return super.get(id);
	}
	
	public List<LgSystemCity> findList(LgSystemCity lgSystemCity) {
		return super.findList(lgSystemCity);
	}
	
	public Page<LgSystemCity> findPage(Page<LgSystemCity> page, LgSystemCity lgSystemCity) {
		return super.findPage(page, lgSystemCity);
	}
	
	@Transactional(readOnly = false)
	public void save(LgSystemCity lgSystemCity) {
		super.save(lgSystemCity);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgSystemCity lgSystemCity) {
		super.delete(lgSystemCity);
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
			lgSystemCityDao.moveInner(lgTreeSortEntity);
		} else if("prev".equals(moveType)) {
			if(oldParentId != null && oldParentId.equals(newParentId)) {
				if(targetSort > nodeSort) {
					lgSystemCityDao.movePrevB2E(lgTreeSortEntity);
				} else {
					lgSystemCityDao.movePrevE2B(lgTreeSortEntity);
				}
			} else {
				lgSystemCityDao.moveInnerPrev(lgTreeSortEntity);
			}
		} else if("next".equals(moveType)) {
			if(oldParentId != null && oldParentId.equals(newParentId)) {
				if(targetSort > nodeSort) {
					lgSystemCityDao.moveNextB2E(lgTreeSortEntity);
				} else {
					lgSystemCityDao.moveNextE2B(lgTreeSortEntity);
				}
			} else {
				lgSystemCityDao.moveInnerNext(lgTreeSortEntity);
			}
		}
	}
	public int haveComparison(LgSystemCity lgSystemCity) {
		return lgSystemCityDao.haveComparison(lgSystemCity);
	}
}