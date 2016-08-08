/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.slice;

import java.util.List;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceYearStatics;

/**
 * 设备每年工作统计DAO接口
 * @author wangshengguo
 * @version 2016-06-13
 */
@MyBatisDao
public interface LgDeviceYearStaticsDao extends CrudDao<LgDeviceYearStatics> {
    
    /**
     * 机器运行概览用到的数据
     * 
     * @param lgDeviceYearStatics
     * @return
     */
    public List<LgDeviceYearStatics> getRunningIndexData(LgDeviceYearStatics lgDeviceYearStatics);
	
}