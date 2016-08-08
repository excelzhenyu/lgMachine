/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.dao.transporthistory;

import com.k2data.platform.common.persistence.CrudDao;
import com.k2data.platform.common.persistence.annotation.MyBatisDao;
import com.k2data.platform.modules.lg.entity.transporthistory.LgMachineTransportHistory;

/**
 * 机器运输记录DAO接口
 * @author lidong
 * @version 2016-07-21
 */
@MyBatisDao
public interface LgMachineTransportHistoryDao extends CrudDao<LgMachineTransportHistory> {
    
    /**
     * 取单条数据
     * 
     * @param lgMachineTransportHistory
     * @return
     */
    public LgMachineTransportHistory getSingleData(LgMachineTransportHistory lgMachineTransportHistory);
    
    /**
     * 获得承运单位和收货单位的位置
     * 
     * @param deviceNo
     * @return
     */
    public LgMachineTransportHistory getTNPLocation(String deviceNo);
    
}