package com.k2data.platform.modules.lg.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.DataEntity;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.common.dao.LgUtilCrudDao;
import com.k2data.platform.modules.lg.common.entity.LgPageUtilEntity;

/**
 * 扩展CrudService基类
 * 
 * @author wangshengguo
 * @version May 13, 2016
 */
@Transactional(readOnly = true)
public abstract class LgUtilCrudService<D extends LgUtilCrudDao<T>, T extends DataEntity<T>> extends CrudService<D,T> {

	/**
	 * 按map查询列表
	 * @param map
	 * @return
	 */
	public List<T> findListByMap(Map<String,Object> map) {
		return dao.findListByMap(map);
	}
	/**
	 * 按list查询列表
	 * @param list
	 * @return
	 */
	public List<T> findListByList(List<Object> list) {
		return dao.findListByList(list);
	}
	/**
	 * 按obj查询列表
	 * @param obj
	 * @return
	 */
	public List<T> findListByObject(Object obj) {
		return dao.findListByObject(obj);
	}
	/**
	 * 按集合查询列表，分页
	 * @param page
	 * @param entity
	 * @return
	 */
	public Page<T> findPageByCollection(Page<T> page, LgPageUtilEntity<T> entity) {
		entity.setPage(page);
		page.setList(dao.findListByCollection(entity));
		return page;
	}
	/**
	 * 批量插入数据
	 * @param list
	 * @return
	 */
	@Transactional(readOnly = false)
	public int saveBatch(List<T> list) {
		return dao.saveBatch(list);
	}
	
	public long selectCount(Object obj) {
		return dao.selectCount(obj);
	}
	public long selectCountByEntity(T  entity) {
		return dao.selectCountByEntity(entity);
	}
	
}
