/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.common.persistence.provider.SelectSqlProvider;
import com.k2data.platform.modules.workcenter.entity.Workcenter;

/**
 * 工作中心DAO接口
 * @author lidong
 * @version 2016-05-20
 */
@MyBatisDao
public interface WorkcenterDao extends CrudDao<Workcenter> {
	
	/**
	 * 获取所有工作中心记录
	 * 
	 * @return List<Workcenter>
	 */
	public List<Workcenter> getAllWorkCenter();
	
	/**
	 * 根据名字和组名获取所有子job
	 * 
	 * @param parentName
	 * @return
	 */
	public List<Workcenter> getChildrenByParent(@Param(value="parentName") String parentName,
												@Param(value="parentGroup") String parentGroup);
	
	/**
	 * 拿到单个对象
	 * 
	 * @param workcenter
	 * @return
	 */
	public Workcenter getWorkCenter(Workcenter workcenter);
	
	/**
	 * 是否存在指定记录
	 * 
	 * @param workcenter
	 * @return
	 */
	@SelectProvider(type=SelectSqlProvider.class, method="checkExist")
	public String checkExist(Workcenter workcenter);
	
	/**
	 * 根据 job 的 name 和 group 来更新状态
	 * 
	 * @param workcenter
	 */
	public void updateStatusByNameNGroup(Workcenter workcenter);
	
	/**
	 * 根据 job 的 name 和 group 来删除数据
	 * 
	 * @param workcenter
	 */
	public void deleteByNameNGroup(Workcenter workcenter);
	
	/**
	 * 根据，查询条件没有 like
	 * 
	 * @param workcenter
	 */
	public List<String> getNameListByGroup(@Param("group") String group);
	
	public void updateAxisByNameNGroup(Workcenter workcenter);
	
}