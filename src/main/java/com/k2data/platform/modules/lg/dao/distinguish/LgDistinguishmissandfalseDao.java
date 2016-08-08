/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.distinguish;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.distinguish.LgDistinguishmissandfalse;

/**
 * 虚漏报方案配置DAO接口
 * @author chenjingsi
 * @version 2016-06-29
 */
@MyBatisDao
public interface LgDistinguishmissandfalseDao extends CrudDao<LgDistinguishmissandfalse> {

	LgDistinguishmissandfalse checkSolutionName(LgDistinguishmissandfalse lgDistinguishmissandfalse);
	
}