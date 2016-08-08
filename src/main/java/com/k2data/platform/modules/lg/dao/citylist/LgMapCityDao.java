/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.citylist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.citylist.LgMapCity;
import com.k2data.platform.modules.lg.entity.citylist.LgTreeSortEntity;

/**
 * 地图城市列表DAO接口
 * @author wangshengguo
 * @version 2016-06-02
 */
@MyBatisDao
public interface LgMapCityDao extends CrudDao<LgMapCity> {

	//移动事件
	public void movePrevB2E(LgTreeSortEntity lgTreeSortEntity);
	public void moveNextB2E(LgTreeSortEntity lgTreeSortEntity);
	public void movePrevE2B(LgTreeSortEntity lgTreeSortEntity);
	public void moveNextE2B(LgTreeSortEntity lgTreeSortEntity);
	
	public void moveInner(LgTreeSortEntity lgTreeSortEntity);
	public void moveInnerPrev(LgTreeSortEntity lgTreeSortEntity);
	public void moveInnerNext(LgTreeSortEntity lgTreeSortEntity);

	//是否存在对照
	public int haveComparison(LgMapCity lgMapCity);
	//查找cityCode
	public String findCityCode(@Param("cityName") String cityName);
	//获取地图省份列表
	public List<String> getProvinceList();
	//根据父节点名称获取城市列表
	public List<String> getCityListByParentName(@Param("cityName") String cityName);
	//获取城市叶子节点，直辖市、特别行政区、台湾及地级市
	public List<LgMapCity> getLeafCityList();
}