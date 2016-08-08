/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.homepage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.lg.entity.homepage.LgUserHomepageRelationship;
import com.k2data.platform.modules.sys.utils.UserUtils;
import com.k2data.platform.modules.lg.dao.homepage.LgUserHomepageRelationshipDao;

/**
 * 用户首页关系Service
 * @author wangshengguo
 * @version 2016-05-17
 */
@Service
@Transactional(readOnly = true)
public class LgUserHomepageRelationshipService extends CrudService<LgUserHomepageRelationshipDao, LgUserHomepageRelationship> {
	@Autowired
	private LgUserHomepageRelationshipDao lgUserHomepageRelationshipDao;
	public LgUserHomepageRelationship get(String id) {
		return super.get(id);
	}
	
	public List<LgUserHomepageRelationship> findList(LgUserHomepageRelationship lgUserHomepageRelationship) {
		return super.findList(lgUserHomepageRelationship);
	}
	
	public Page<LgUserHomepageRelationship> findPage(Page<LgUserHomepageRelationship> page, LgUserHomepageRelationship lgUserHomepageRelationship) {
		return super.findPage(page, lgUserHomepageRelationship);
	}
	
	@Transactional(readOnly = false)
	public void save(LgUserHomepageRelationship lgUserHomepageRelationship) {
		super.save(lgUserHomepageRelationship);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgUserHomepageRelationship lgUserHomepageRelationship) {
		super.delete(lgUserHomepageRelationship);
	}
	@Transactional(readOnly = false)
	public int insertHomePageUrl(LgUserHomepageRelationship lgUserHomepageRelationship) {
		lgUserHomepageRelationship.setUserId(UserUtils.getUser().getId()); 
		deleteByUserId(lgUserHomepageRelationship);
		
		if(lgUserHomepageRelationship.getMenuMapList().size() > 0) {
			return lgUserHomepageRelationshipDao.insertHomePageUrl(lgUserHomepageRelationship);
		} 
		return 0;
	}
	@Transactional(readOnly = false)
	public void deleteByUserId(LgUserHomepageRelationship lgUserHomepageRelationship) {
		lgUserHomepageRelationshipDao.deleteByUserId(lgUserHomepageRelationship);
	}
	
}