/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.exptmachine;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.dao.exptmachine.LgExptMachineProfileDao;
import com.k2data.platform.modules.lg.entity.LgMachineprofileVO;

/**
 * 
 * @author K2DATA.wsguo
 * @date Aug 1, 2016 10:40:33 AM    
 * 
 */
@Service
@Transactional(readOnly=true)
public class LgExptMachineProfileService extends CrudService<LgExptMachineProfileDao, LgMachineprofileVO>{

	public List<LgMachineprofileVO> findList(LgMachineprofileVO lgMachineprofileVO) {
		return super.findList(lgMachineprofileVO);
	}
	
	public Page<LgMachineprofileVO> findPage(Page<LgMachineprofileVO> page, LgMachineprofileVO lgMachineprofileVO) {
		return super.findPage(page, lgMachineprofileVO);
	}
	
}
