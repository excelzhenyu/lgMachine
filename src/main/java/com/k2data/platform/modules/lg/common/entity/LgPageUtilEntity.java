package com.k2data.platform.modules.lg.common.entity;

import java.util.List;
import java.util.Map;

import com.k2data.platform.common.persistence.DataEntity;

/**
 * 因目前分页拦截器限制，增加此类
 * 根据map/list/object查询时，如果要求分页，参数使用该实体类
 * @author wangshengguo
 * @param <T> 
 */
public class LgPageUtilEntity<T> extends DataEntity<T> {
	
	private static final long serialVersionUID = 1L;
	
	private Map<String,Object> map; //map参数
	private List<Object> list;		//list参数
	private Object obj;				//object参数
	private T entity;				//数据实体
	//private Page<T> page; 		//继承自父类，此处不需要编写
	
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public T getEntity() {
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}
//	public Page<T> getPage() {
//		return page;
//	}
//	public void setPage(Page<T> page) {
//		this.page = page;
//	}
	
}
