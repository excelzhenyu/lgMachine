/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.baseinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.baseinfo.LgDimDate;
import com.k2data.platform.modules.lg.common.utils.GenDimDate;
import com.k2data.platform.modules.lg.dao.baseinfo.LgDimDateDao;

/**
 * 基础信息-时间维度Service
 * @author wangshengguo
 * @version 2016-06-13
 */
@Service
@Transactional(readOnly = true)
public class LgDimDateService extends CrudService<LgDimDateDao, LgDimDate> {

	@Autowired
	private LgDimDateDao lgDimDateDao;
	
	public LgDimDate get(String id) {
		return super.get(id);
	}
	
	public List<LgDimDate> findList(LgDimDate lgDimDate) {
		return super.findList(lgDimDate);
	}
	
	public Page<LgDimDate> findPage(Page<LgDimDate> page, LgDimDate lgDimDate) {
		return super.findPage(page, lgDimDate);
	}
	
	@Transactional(readOnly = false)
	public void save(LgDimDate lgDimDate) {
		super.save(lgDimDate);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgDimDate lgDimDate) {
		super.delete(lgDimDate);
	}
	/**
	 * 批量保存时间维度，如果所属区间内已经存在数据，则不会重新生成，仅根据最大最小值判断，若数据为多个片段则存在问题，此处忽略
	 * @param startDate 开始日期 如2016-01-01
	 * @param howManyYear 自开始日期开始，生成多少年的数据，比如20年
	 * @return int 插入多少条数据
	 */
	@Transactional(readOnly = false)
	public int saveBatch(Date startDate, int howManyYear) {
		List<LgDimDate> lgDimDateList = new ArrayList<LgDimDate>();
		lgDimDateList = GenDimDate.genDimDateData(lgDimDateDao.selectMinMaxDay(), startDate, howManyYear);
		int insertCount = 0;
		if(lgDimDateList != null && lgDimDateList.size() > 0) {
			insertCount = lgDimDateDao.saveBatch(lgDimDateList);
		}
		return insertCount;
	}

}