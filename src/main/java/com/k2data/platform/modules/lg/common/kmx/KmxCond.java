package com.k2data.platform.modules.lg.common.kmx;

import java.util.Date;

/**
 * 请求大数据平台的条件
 */
public class KmxCond {
    
    private String url; // 地址
    
    /** 请求参数 begin **/
    /* select 查询条件字段 begin */
    private String deviceNo;  // 设备号
    private String[] sensors;   // 传感器列表
    private Date start; // 开始时间
    private Date stop;  // 结束时间
    /* select 查询条件字段 end */
    
    private String valueFilter; // 值过滤
    private String[] aggregations; // 聚合条件
    private String order;   // 排序
    private String size;    // 每页大小
    private String page;    // 第几页
    /** 请求参数 end **/
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getDeviceNo() {
        return deviceNo;
    }
    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
    public String[] getSensors() {
        return sensors;
    }
    public void setSensors(String[] sensors) {
        this.sensors = sensors;
    }
    public Date getStart() {
        return start;
    }
    public void setStart(Date start) {
        this.start = start;
    }
    public Date getStop() {
        return stop;
    }
    public void setStop(Date stop) {
        this.stop = stop;
    }
    public String getValueFilter() {
        return valueFilter;
    }
    public void setValueFilter(String valueFilter) {
        this.valueFilter = valueFilter;
    }
    public String getOrder() {
        return order;
    }
    public void setOrder(String order) {
        this.order = order;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getPage() {
        return page;
    }
    public void setPage(String page) {
        this.page = page;
    }
    public String[] getAggregations() {
        return aggregations;
    }
    public void setAggregations(String[] aggregations) {
        this.aggregations = aggregations;
    }

}
