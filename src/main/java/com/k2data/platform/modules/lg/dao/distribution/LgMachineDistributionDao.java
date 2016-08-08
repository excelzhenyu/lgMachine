/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.distribution;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineDistributionByProvince;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineDistributionData;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineDistributionEntity;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineOrderDistributionByCity;

/**
 * 机器热度分布DAO接口
 * @author wangshengguo
 * @version 2016-06-30
 */
@MyBatisDao
public interface LgMachineDistributionDao extends CrudDao<LgMachineOrderDistributionByCity> {
	//地级市订货号分布
	public int saveMachineOrderDistributionByCity(@Param("monthId") int monthId);
	//省订货号分布
	public int saveMachineOrderDistributionByProvince(@Param("monthId") int monthId);
	
	//地级市机型分布
	public int saveProductTypeDistributionByCity(@Param("monthId") int monthId);
	//省机型分布
	public int saveProductTypeDistributionByProvince(@Param("monthId") int monthId);

	//地级市类别分布
	public int saveMachineTypeDistributionByCity(@Param("monthId") int monthId);
	//省类别分布
	public int saveMachineTypeDistributionByProvince(@Param("monthId") int monthId);
	//全国机器分布
	public int saveMachineTypeDistributionByCountry(@Param("monthId") int monthId);

	public List<LgMachineDistributionByProvince> getMapMachineNumBar(LgMachineDistributionByProvince lgMachineDistributionByProvince);

	//获取地级市机型分布数据
	public List<LgMachineDistributionData> getCityMachineTypeData(LgMachineDistributionEntity entity);
	//获取省机型分布数据
	public List<LgMachineDistributionData> getProvinceMachineTypeData(LgMachineDistributionEntity entity);
	//获取地级市型号分布数据
	public List<LgMachineDistributionData> getCityProductTypeData(LgMachineDistributionEntity entity);
	//获取省型号分布数据
	public List<LgMachineDistributionData> getProvinceProductTypeData(LgMachineDistributionEntity entity);
	//获取地级市订货号分布数据
	public List<LgMachineDistributionData> getCityMachineOrderData(LgMachineDistributionEntity entity);
	//获取省订货号分布数据
	public List<LgMachineDistributionData> getProvinceMachineOrderData(LgMachineDistributionEntity entity);

}