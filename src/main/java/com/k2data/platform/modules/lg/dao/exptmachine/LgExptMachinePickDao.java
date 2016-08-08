/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.exptmachine;

import java.util.List;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.exptmachine.LgExptMachinePick;

/**
 * 试验机器选取DAO接口
 * @author wangshengguo
 * @version 2016-06-21
 */
@MyBatisDao
public interface LgExptMachinePickDao extends CrudDao<LgExptMachinePick> {

	//获取批次号列表
	public List<LgExptMachinePick> getBatchNumberList();
	//获取批次号列表,同上,只不过返回值不同
	public List<String> getBatchNumberStringList();
	//检查批次号
	public LgExptMachinePick checkBatchNumber(LgExptMachinePick lgExptMachinePick);

}