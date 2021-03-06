/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.homepage;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.homepage.LgUserHomepageRelationship;

/**
 * 用户首页关系DAO接口
 * @author wangshengguo
 * @version 2016-05-17
 */
@MyBatisDao
public interface LgHomepageDisplayDao extends CrudDao<LgUserHomepageRelationship> {
	
}