/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.distinguish;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.distinguish.LgDistinguishmissandfalsedetail;
import com.k2data.platform.modules.lg.dao.distinguish.LgDistinguishmissandfalsedetailDao;

/**
 * 虚漏报方案配置详细Service
 * @author chenjingsi
 * @version 2016-06-29
 */
@Service
@Transactional(readOnly = true)
public class LgDistinguishmissandfalsedetailService extends CrudService<LgDistinguishmissandfalsedetailDao, LgDistinguishmissandfalsedetail> {

	@Autowired
	LgDistinguishmissandfalsedetailDao lgDistinguishmissandfalsedetailDao;
	
	public LgDistinguishmissandfalsedetail get(String id) {
		return super.get(id);
	}
	
	public List<LgDistinguishmissandfalsedetail> findList(LgDistinguishmissandfalsedetail lgDistinguishmissandfalsedetail) {
		return super.findList(lgDistinguishmissandfalsedetail);
	}
	
	public Page<LgDistinguishmissandfalsedetail> findPage(Page<LgDistinguishmissandfalsedetail> page, LgDistinguishmissandfalsedetail lgDistinguishmissandfalsedetail) {
		return super.findPage(page, lgDistinguishmissandfalsedetail);
	}
	
	@Transactional(readOnly = false)
	public void save(LgDistinguishmissandfalsedetail lgDistinguishmissandfalsedetail) {
		super.save(lgDistinguishmissandfalsedetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgDistinguishmissandfalsedetail lgDistinguishmissandfalsedetail) {
		super.delete(lgDistinguishmissandfalsedetail);
	}

	public LgDistinguishmissandfalsedetail checkCondition(
			LgDistinguishmissandfalsedetail lgDistinguishmissandfalsedetail) {
		return lgDistinguishmissandfalsedetailDao.checkCondition(lgDistinguishmissandfalsedetail);
	}
	
}