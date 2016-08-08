package com.k2data.platform.modules.lg.entity.machinestatus;

public class LgRunningIndexCond {
    
    private String[] machineTypeMult;  // 主机类别多选
    private String[] productTypeMult;  // 主机型号多选
    private String[] orderNumberMult;  // 订货号多选
    private String[] workProvinceMult; // 地区多选
    private String[] workStateMult;    // 工作工况多选
    private Integer yearGreater;    // 大于 xx 年
    private Integer workDurationGreater;  // 活跃机器定义：每年度工作大于xx 小时
    
    public String[] getMachineTypeMult() {
        return machineTypeMult;
    }
    public void setMachineTypeMult(String[] machineTypeMult) {
        this.machineTypeMult = machineTypeMult;
    }
    public String[] getProductTypeMult() {
        return productTypeMult;
    }
    public void setProductTypeMult(String[] productTypeMult) {
        this.productTypeMult = productTypeMult;
    }
    public String[] getOrderNumberMult() {
        return orderNumberMult;
    }
    public void setOrderNumberMult(String[] orderNumberMult) {
        this.orderNumberMult = orderNumberMult;
    }
    public String[] getWorkProvinceMult() {
        return workProvinceMult;
    }
    public void setWorkProvinceMult(String[] workProvinceMult) {
        this.workProvinceMult = workProvinceMult;
    }
    public String[] getWorkStateMult() {
        return workStateMult;
    }
    public void setWorkStateMult(String[] workStateMult) {
        this.workStateMult = workStateMult;
    }
    public Integer getYearGreater() {
        return yearGreater;
    }
    public void setYearGreater(Integer yearGreater) {
        this.yearGreater = yearGreater;
    }
    public Integer getWorkDurationGreater() {
        return workDurationGreater;
    }
    public void setWorkDurationGreater(Integer workDurationGreater) {
        this.workDurationGreater = workDurationGreater;
    }
    
}
