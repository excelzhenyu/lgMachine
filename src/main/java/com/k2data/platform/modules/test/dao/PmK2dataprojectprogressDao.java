/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.test.dao;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.test.entity.PmK2dataprojectprogress;

/**
 * 项目进度DAO接口
 * @author wangshengguo
 * @version 2016-05-09
 */
@MyBatisDao
public interface PmK2dataprojectprogressDao extends CrudDao<PmK2dataprojectprogress> {
	
}