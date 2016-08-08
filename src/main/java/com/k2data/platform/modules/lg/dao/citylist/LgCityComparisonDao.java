/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.citylist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.citylist.LgCityComparison;
import com.k2data.platform.modules.lg.entity.citylist.LgMapCity;
import com.k2data.platform.modules.lg.entity.citylist.LgSystemCity;

/**
 * 业务销售城市与地图城市对照DAO接口
 * @author wangshengguo
 * @version 2016-06-06
 */
@MyBatisDao
public interface LgCityComparisonDao extends CrudDao<LgCityComparison> {
	public List<LgCityComparison> findListFromCity();
	public int saveBatch(@Param("list") List<LgCityComparison> list);
	public List<LgMapCity> findMapCityList(LgMapCity lgMapCity);
	public List<LgSystemCity> findSystemCityList(LgSystemCity lgSystemCity);

}