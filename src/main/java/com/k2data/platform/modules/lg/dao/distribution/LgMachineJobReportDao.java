/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.distribution;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineJobReportData;

/**
 * 机器热度分布DAO接口
 * @author wangshengguo
 * @version 2016-06-30
 */
@MyBatisDao
public interface LgMachineJobReportDao extends CrudDao<LgMachineJobReportData> {

}