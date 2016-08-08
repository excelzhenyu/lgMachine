/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.citylist;

import java.util.List;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.citylist.LgSystemCity;
import com.k2data.platform.modules.lg.entity.citylist.LgTreeSortEntity;

/**
 * 业务系统城市列表DAO接口
 * @author wangshengguo
 * @version 2016-06-01
 */
@MyBatisDao
public interface LgSystemCityDao extends CrudDao<LgSystemCity> {
	public void movePrevB2E(LgTreeSortEntity lgTreeSortEntity);
	public void moveNextB2E(LgTreeSortEntity lgTreeSortEntity);
	public void movePrevE2B(LgTreeSortEntity lgTreeSortEntity);
	public void moveNextE2B(LgTreeSortEntity lgTreeSortEntity);
	
	public void moveInner(LgTreeSortEntity lgTreeSortEntity);
	public void moveInnerPrev(LgTreeSortEntity lgTreeSortEntity);
	public void moveInnerNext(LgTreeSortEntity lgTreeSortEntity);
	
	public int haveComparison(LgSystemCity lgSystemCity);
	
	/**
	 * 得到省份列表
	 *
	 * @return
	 */
	public List<LgSystemCity> getProvinceList();
	
	/**
     * 得到城市列表
     *
     * @return
     */
    public List<LgSystemCity> getCityList();
}