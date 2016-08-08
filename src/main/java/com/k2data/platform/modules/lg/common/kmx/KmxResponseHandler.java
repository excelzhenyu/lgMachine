package com.k2data.platform.modules.lg.common.kmx;

import com.k2data.platform.modules.lg.common.entity.LgSliceDataRowsRsp;

/**
 * 大数据平台接口结果处理接口
 */
public interface KmxResponseHandler {
    
    public void handleResponose(LgSliceDataRowsRsp rsp);

}
