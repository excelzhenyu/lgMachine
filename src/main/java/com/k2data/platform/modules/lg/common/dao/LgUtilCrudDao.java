package com.k2data.platform.modules.lg.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.modules.lg.common.entity.LgPageUtilEntity;
/**
 * 扩展CurdDao基类
 * @author wangshengguo
 */
public interface LgUtilCrudDao<T> extends CrudDao<T> {

	/**
	 * 根据map查询列表
	 * @param map
	 * @return
	 */
	public List<T> findListByMap(Map<String,Object> map);
	/**
	 * 根据list查询列表
	 * @param list
	 * @return
	 */
	public List<T> findListByList(@Param("list") List<Object> list);
	/**
	 * 根据obj查询列表
	 * @param obj
	 * @return
	 */
	public List<T> findListByObject(@Param("obj") Object obj);
	/**
	 * 根据LgPageUtilEntity实体查询列表
	 * @param entity
	 * @return
	 */
	public List<T> findListByCollection(LgPageUtilEntity<T> entity);
	
	/**
	 * 批量插入
	 * @param list
	 * @return
	 */
	public int saveBatch(@Param("list") List<T> list);

	/**
	 * 查询条数
	 * @param obj
	 * @return
	 */
	public long selectCount(@Param("obj") Object obj);
	/**
	 * 查询记录条数
	 * @param entity
	 * @return
	 */
	public long selectCountByEntity(T entity);
	
}
