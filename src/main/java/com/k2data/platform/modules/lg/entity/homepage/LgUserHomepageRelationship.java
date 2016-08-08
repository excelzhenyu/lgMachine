/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.entity.homepage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;
import com.k2data.platform.common.persistence.DataEntity;
import com.k2data.platform.common.utils.IdGen;

/**
 * 用户首页关系Entity
 * @author wangshengguo
 * @version 2016-05-17
 */
public class LgUserHomepageRelationship extends DataEntity<LgUserHomepageRelationship> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户ID
	private String urlId;		// 页面URLID
	private String urlNo;		// 页面位置编号
	//private List<String> urlIdList = Lists.newArrayList(); // 拥有菜单列表
	//private List<String> menuList = Lists.newArrayList(); // 拥有菜单列表
	//private List<String> urlNoList = Lists.newArrayList(); // 拥有url位置编号列表

	private List<Map<String,Object>> menuMapList = Lists.newArrayList(); // 拥有菜单列表
	public LgUserHomepageRelationship() {
		super();
	}

	public LgUserHomepageRelationship(String id){
		super(id);
	}

	@Length(min=0, max=64, message="用户ID长度必须介于 0 和 64 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=64, message="页面URLID长度必须介于 0 和 64 之间")
	public String getUrlId() {
		return urlId;
	}

	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}
	
	@Length(min=0, max=2, message="页面位置编号长度必须介于 0 和 2 之间")
	public String getUrlNo() {
		return urlNo;
	}

	public void setUrlNo(String urlNo) {
		this.urlNo = urlNo;
	}
	/*
	public List<String> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<String> menuList) {
		this.menuList = menuList;
	}
	public List<String> getUrlNoList() {
		return urlNoList;
	}

	public void setUrlNoList(List<String> urlNoList) {
		this.urlNoList = urlNoList;
	}
	 */
	public List<String> getMenuIdList() {
		List<String> menuIdList = Lists.newArrayList();
/*		int listSize = menuList.size() < urlNoList.size()?menuList.size():urlNoList.size();
		for(int index = 0; index < listSize; index++) {
			menuIdList.add(menuList.get(index)+"-"+urlNoList.get(index));
		}
*/		
		for(Map<String, Object> map : menuMapList) {
			menuIdList.add(map.get("urlId")+"-"+map.get("urlNo"));
		}
		return menuIdList;
	}

	public void setMenuIdList(List<String> menuIdList) {

		//menuList = Lists.newArrayList();
		//urlNoList = Lists.newArrayList();
		menuMapList = Lists.newArrayList();
		for (String menuId : menuIdList) {
			String[] ids = menuId.split("-");
			if(ids.length >1) {
				//menuList.add(ids[0]);
				//urlNoList.add(ids[1]);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("urlId", ids[0]);
				map.put("urlNo", ids[1]);
				map.put("id", IdGen.uuid());
				menuMapList.add(map);
			}
		}
	}

	public String getMenuIds() {
		return StringUtils.join(getMenuIdList(), ",");
	}
	
	public void setMenuIds(String menuIds) {
		if (menuIds != null){
			String[] ids = StringUtils.split(menuIds, ",");
			setMenuIdList(Lists.newArrayList(ids));
		}
	}

	public List<Map<String, Object>> getMenuMapList() {
		return menuMapList;
	}

	public void setMenuMapList(List<Map<String, Object>> menuMapList) {
		this.menuMapList = menuMapList;
	}

}