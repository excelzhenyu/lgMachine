/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.homepage;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.homepage.LgUserHomepageRelationship;
import com.k2data.platform.modules.lg.dao.homepage.LgHomepageDisplayDao;

/**
 * 用户首页关系Service
 * @author wangshengguo
 * @version 2016-05-17
 */
@Service
@Transactional(readOnly = true)
public class LgHomepageDisplayService extends CrudService<LgHomepageDisplayDao, LgUserHomepageRelationship> {

	public LgUserHomepageRelationship get(String id) {
		return super.get(id);
	}
	
	public List<LgUserHomepageRelationship> findList(LgUserHomepageRelationship lgUserHomepageRelationship) {
		return super.findList(lgUserHomepageRelationship);
	}
	
	public Page<LgUserHomepageRelationship> findPage(Page<LgUserHomepageRelationship> page, LgUserHomepageRelationship lgUserHomepageRelationship) {
		return super.findPage(page, lgUserHomepageRelationship);
	}
	
	@Transactional(readOnly = false)
	public void save(LgUserHomepageRelationship lgUserHomepageRelationship) {
		super.save(lgUserHomepageRelationship);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgUserHomepageRelationship lgUserHomepageRelationship) {
		super.delete(lgUserHomepageRelationship);
	}
	/**
	 * 获取行数
	 * @param size
	 * @return
	 */
	public int calcRows(int size) {
		if(size == 0) {
			return 0;
		}
		int col = (int)Math.ceil(Math.sqrt(size));
		int row = size/col;
		if(col*row != size) {
			row += 1;
		}
		return row;
	}
	/**
	 * 获取动态首页HTML
	 * @param homepageList
	 * @return
	 */
	public String genHtml(List<LgUserHomepageRelationship> homepageList, String path) {
		
		int size = homepageList.size();
		if(size == 0) {
			return  "";
		}
		int col = (int)Math.ceil(Math.sqrt(size));
		int row = size/col;
		if(col*row != size) {
			row += 1;
		}
		String span = ""+12/col;
		int index = 0;
		String dynamicHtml = "";
		for (int i=1; i < row+1; i++) {
			dynamicHtml += "<div class=\"row-fluid\">";
			for(int j=1; j < col+1; j++) {
				if(index < size) {
					dynamicHtml += "<div class=\"span" + span +" span_height\">";
					dynamicHtml += "<iframe width=100% height=100% frameborder=0 scrolling=auto src=\""+ path +homepageList.get(index++).getUrlId()+"\"></iframe>";
					dynamicHtml += "</div>";
				} else {
					dynamicHtml += "<div class=\"span" +span +" span_height\">";
					dynamicHtml += "<iframe width=100% height=100% frameborder=0 scrolling=auto src=\"\"></iframe>";
					dynamicHtml += "</div>";
				}
			}
			dynamicHtml += "</div>";
		}
		return dynamicHtml;
	}
}