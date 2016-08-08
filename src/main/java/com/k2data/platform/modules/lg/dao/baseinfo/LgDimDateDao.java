/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.baseinfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.baseinfo.LgDimDate;

/**
 * 基础信息-时间维度DAO接口
 * @author wangshengguo
 * @version 2016-06-13
 */
@MyBatisDao
public interface LgDimDateDao extends CrudDao<LgDimDate> {
	public int saveBatch(@Param("list") List<LgDimDate> list);
	public Map<String,Object> selectMinMaxDay(); 
	
	public List<LgDimDate> getYearList(@Param("yearGreaterOE") Integer yearGreaterOE);
}