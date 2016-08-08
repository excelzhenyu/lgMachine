/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.exptmachine;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.LgLabMachineProfileQuery;
import com.k2data.platform.modules.lg.entity.LgMachineprofile;
import com.k2data.platform.modules.lg.entity.exptmachine.LgExptMachinePickDetail;

/**
 * 试验机器选取明细DAO接口
 * @author wangshengguo
 * @version 2016-06-21
 */
@MyBatisDao
public interface LgExptMachinePickDetailDao extends CrudDao<LgExptMachinePickDetail> {
	
	public void deleteMachine(LgExptMachinePickDetail lgExptMachinePickDetail);
	public List<LgMachineprofile> findMachineList(LgMachineprofile lgMachineprofile);
	public List<LgLabMachineProfileQuery> findMachineListByQuery(LgLabMachineProfileQuery lgLabMachineProfileQuery);
	public int saveBatch(@Param("list") List<LgExptMachinePickDetail> list);
	public int saveImportBatch(@Param("list") List<LgExptMachinePickDetail> list);
	
	public List<String> findMachineCodeList(LgExptMachinePickDetail lgExptMachinePickDetail);
	public List<LgMachineprofile> findExistMachineCodeList(LgMachineprofile lgMachineprofile);
	//根据试验批次Id获取试验机器编码
	public List<String> getMachineCodeByPickId(@Param("list") List<String> pickId);
	//根据试验批次号获取试验机器编码
	public List<String> getMachineCodeByBatchNumber(@Param("list") List<String> batchNumber);

	public List<String> getExptMachineCode(@Param("pickId") String pickId);


}