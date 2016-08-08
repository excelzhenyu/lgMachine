/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.exptmachine;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.LgMachineprofileVO;

/**
 * 试验机机器电子档案DAO
 * @author K2DATA.wsguo
 * @date 2016-08-01 10:36:17
 * 
 */
@MyBatisDao
public interface LgExptMachineProfileDao extends CrudDao<LgMachineprofileVO> {

}
