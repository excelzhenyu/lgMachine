/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao;

import java.util.List;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.LgMachineprofile;
import com.k2data.platform.modules.lg.entity.LgMachineprofileVO;
import com.k2data.platform.modules.lg.entity.LgWorktimeDistribution;

/**
 * 整机档案DAO接口
 * @author chenjingsi
 * @version 2016-05-04
 */
@MyBatisDao
public interface LgMachineprofileDao extends CrudDao<LgMachineprofile> {

	LgMachineprofile checkCode(LgMachineprofile lgMachineprofile);

	LgMachineprofile checkPinCode(LgMachineprofile lgMachineprofile);

	List<String> getMachineNoList(LgWorktimeDistribution lgWorktimeDistribution);

	LgMachineprofileVO getProfileVO(LgMachineprofile lgMachineprofile);
	
	public List<String> getDeviceNoList();

	List<LgMachineprofileVO> getMachineSaled();

	List<LgMachineprofileVO> getMachineSaling();
	
	//用于根据主机类别、主机型号、订货号获取机器列表
	public List<String> getMachineCodeListByCondition(LgMachineprofile lgMachineprofile);

	public List<LgMachineprofile> getMachineProfileList(LgMachineprofile lgMachineprofile);

	String getId(String deviceNo);
	
}