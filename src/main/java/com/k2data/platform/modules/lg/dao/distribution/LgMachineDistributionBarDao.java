/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.distribution;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineDistributionBarData;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineDistributionEntity;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineOrderDistributionByCityBar;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineTypeDistributionByCityBar;

/**
 * 机器柱状图分布DAO接口
 * @author wangshengguo
 * @version 2016-06-30
 */
@MyBatisDao
public interface LgMachineDistributionBarDao extends CrudDao<LgMachineOrderDistributionByCityBar> {
	//地级市订货号分布
	public int saveMachineOrderDistributionByCity(@Param("monthId") int monthId);
	//省订货号分布
	public int saveMachineOrderDistributionByProvince(@Param("monthId") int monthId);
	//地级市机型分布
	public int saveMachineTypeDistributionByCity(@Param("monthId") int monthId);
	//省机型分布
	public int saveMachineTypeDistributionByProvince(@Param("monthId") int monthId);

	//获取地级市机型分布数据
	public List<LgMachineDistributionBarData> getCityMachineTypeBarData(LgMachineDistributionEntity entity);
	//获取省机型分布数据
	public List<LgMachineDistributionBarData> getProvinceMachineTypeBarData(LgMachineDistributionEntity entity);
	//获取地级市订货号分布数据
	public List<LgMachineDistributionBarData> getCityMachineOrderBarData(LgMachineDistributionEntity entity);
	//获取省订货号分布数据
	public List<LgMachineDistributionBarData> getProvinceMachineOrderBarData(LgMachineDistributionEntity entity);

	
}