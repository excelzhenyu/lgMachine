package com.k2data.platform.modules.lg.common.kmx;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.k2data.platform.common.utils.DateUtils;
import com.k2data.platform.modules.lg.common.entity.LgSliceDataRowsRsp;
import com.k2data.platform.modules.lg.common.utils.OkhttpUtils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 连接大数据平台通用客户端
 * 
 * @author lidong
 * @since 2016-7-26
 */
public class KmxClient {
    
    public static final String ISO_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";
    public static final int PAGE_SIZE = 1000; //页大小
    public static final int RETRY_COUNT = 3;  //重试次数
    
    private static final Logger logger = LoggerFactory.getLogger(KmxClient.class);
    
    /**
     * 同步 get 请求
     * 
     * @param cond
     * @param handler
     */
    public static LgSliceDataRowsRsp getSync(final KmxCond cond, final KmxResponseHandler handler) {
        LgSliceDataRowsRsp rsp = null;

        Long start = System.currentTimeMillis();
        try {
            Response response = OkhttpUtils.get(cond.getUrl(), genParams(cond));
            
            String responseStr = response.body().string();
            JSONObject rowsJson = JSON.parseObject(responseStr);
            
            if(rowsJson.getInteger("code") == 0) { 
                rsp = JSON.toJavaObject(rowsJson, LgSliceDataRowsRsp.class);
            } else {
                logger.warn("Kmx get error! code: {}, message: {}, url: {}", rowsJson.getString("code"), rowsJson.getString("message"), cond.getUrl());
            }
            
            handler.handleResponose(rsp);
            
            logger.info("Kmx get callback end. url: {}, cast: {}", cond.getUrl(), System.currentTimeMillis() - start);
        } catch (IOException e) {
            logger.error("Kmx get error! url: " + cond.getUrl(), e);
        }
        return rsp;
    }
    
    /**
     * 异步 get 请求
     * 
     * @param cond
     * @param handler
     */
    public static void getAsync(final KmxCond cond, final KmxResponseHandler handler) {
        OkhttpUtils.get(cond.getUrl(), genParams(cond), new Callback() {
            
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();
                JSONObject rowsJson = JSON.parseObject(responseStr);
                
                LgSliceDataRowsRsp rsp = null;
                if(rowsJson.getInteger("code") == 0) { 
                    rsp = JSON.toJavaObject(rowsJson, LgSliceDataRowsRsp.class);
                } else {
                    logger.warn("Kmx get error! code: {}, message: {}, url: {}", rowsJson.getString("code"), rowsJson.getString("message"), call.request().url().toString());
                }
                
                handler.handleResponose(rsp);
                
                logger.info("Kmx get callback end. url: {}", cond.getUrl());
            }
            
            @Override
            public void onFailure(Call call, IOException e) {
                logger.error("Kmx get error! url: " + call.request().url().toString(), e);
            }
        });
    }
    
    /**
     * 转换查询参数格式
     * 
     * @param cond
     * @return
     */
    private static Map<String, String> genParams(KmxCond cond) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("select", genSelectCond(cond.getDeviceNo(), cond.getSensors(), cond.getStart(), cond.getStop()));
        if (!StringUtils.isBlank(cond.getValueFilter()))
            params.put("valueFilter", cond.getValueFilter());
        if (cond.getAggregations() != null && cond.getAggregations().length != 0)
            params.put("aggregation", genAggregationCond(cond.getAggregations()));
        if (!StringUtils.isBlank(cond.getOrder()))
            params.put("order", cond.getOrder());
        if (!StringUtils.isBlank(cond.getSize()))
            params.put("size", cond.getSize());
        if (!StringUtils.isBlank(cond.getPage()))
            params.put("page", cond.getPage());
        
        return params;
    }
    
    /**
     * 生成 select 查询条件
     * 
     * @param device 设备号
     * @param sensors 传感器
     * @param start 开始时间
     * @param stop 结束时间
     * @return
     */
    private static String genSelectCond(String device, String[] sensors, Date start, Date stop) {
        String sliceStart = DateUtils.formatDate(start, KmxClient.ISO_PATTERN).replace("+", "%2B");
        String sliceStop = DateUtils.formatDate(stop, KmxClient.ISO_PATTERN).replace("+", "%2B");

        StringBuilder selectSb = new StringBuilder();
        selectSb.append("{\"sources\":{\"device\":")
                .append("\"").append(device).append("\"")
                .append(",\"sensors\":[");
        
        int i = 0;
        for(String sensor: sensors) {
            if (i != 0)
                selectSb.append(",");
            
            selectSb.append("\"").append(sensor).append("\"");
            i++;
        }
        
        selectSb.append("]")
                .append(",\"timeRange\":{\"start\":{\"iso\":")
                .append("\"").append(sliceStart).append("\"")
                .append("},\"end\":{\"iso\":")
                .append("\"").append(sliceStop).append("\"")
                .append("}}}}");
        return selectSb.toString();
    }
    
    /**
     * 生成聚合查询条件
     * 
     * @param aggregations
     * @return
     */
    private static String genAggregationCond(String[] aggregations) {
        StringBuilder aggregationSb = new StringBuilder();
        aggregationSb.append("{\"defaultAggregation\":[");
        
        int i = 0;
        for(String aggregation: aggregations) {
            if (i != 0)
                aggregationSb.append(",");
            
            aggregationSb.append("\"").append(aggregation).append("\"");
            i++;
        }
        aggregationSb.append("]}");
        return aggregationSb.toString();
    }
    
}
