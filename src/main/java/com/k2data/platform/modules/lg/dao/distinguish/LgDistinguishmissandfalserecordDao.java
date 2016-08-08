/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.distinguish;

import java.util.List;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.LgFalseStatistics;
import com.k2data.platform.modules.lg.entity.distinguish.LgDistinguishmissandfalserecord;

/**
 * 机器虚报漏报记录DAO接口
 * @author chenjingsi
 * @version 2016-06-29
 */
@MyBatisDao
public interface LgDistinguishmissandfalserecordDao extends CrudDao<LgDistinguishmissandfalserecord> {

	List<LgFalseStatistics> queryCounts(LgFalseStatistics lgFalseStatistics);

	List<LgDistinguishmissandfalserecord> getModalList(LgFalseStatistics lgFalseStatistics);

	LgFalseStatistics queryWjjNumbers(LgFalseStatistics lgFalseStatistics);

	LgFalseStatistics queryZzjNumbers(LgFalseStatistics lgFalseStatistics);

	LgFalseStatistics queryTotalNumbers(LgFalseStatistics lgFalseStatistics);

	List<LgFalseStatistics> querySaleCount(LgFalseStatistics lgFalseStatistics);

	List<LgDistinguishmissandfalserecord> getSaleModal(LgFalseStatistics lgFalseStatistics);
		
}