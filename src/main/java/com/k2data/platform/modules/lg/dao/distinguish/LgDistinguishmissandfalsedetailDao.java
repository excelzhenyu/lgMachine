/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.distinguish;

import java.util.List;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.distinguish.LgDistinguishmissandfalsedetail;

/**
 * 虚漏报方案配置详细DAO接口
 * @author chenjingsi
 * @version 2016-06-29
 */
@MyBatisDao
public interface LgDistinguishmissandfalsedetailDao extends CrudDao<LgDistinguishmissandfalsedetail> {

	LgDistinguishmissandfalsedetail checkCondition(LgDistinguishmissandfalsedetail lgDistinguishmissandfalsedetail);

	List<LgDistinguishmissandfalsedetail> getDetailList(String solutionType);
	
}