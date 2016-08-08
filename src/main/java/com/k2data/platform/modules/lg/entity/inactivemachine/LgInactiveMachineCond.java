package com.k2data.platform.modules.lg.entity.inactivemachine;

public class LgInactiveMachineCond {
    
    public final static Integer DISTANCE_ASC = 1;  // 排序规则 距离正序 
    public final static Integer DISTANCE_DESC = 2;  // 排序规则 距离降序 
    public final static Integer INACTICE_DAYS_ASC = 3;  // 排序规则 呆滞天数正序 
    public final static Integer INACTICE_DAYS_DESC = 4; // 排序规则 呆滞天数降序
    
    private String[] machineTypeMult;  // 主机类别多选
    private String[] productTypeMult;  // 主机型号多选
    private String[] orderNumberMult;  // 订货号多选
    private Integer inactiveMonth;  // 呆滞 xx 月以上
    private String demandCity;    // 需求城市
    private Integer demandNum;  // 需求数量
    private Integer orderType;   // 排序规则 
    
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
    public Integer getInactiveMonth() {
        return inactiveMonth;
    }
    public void setInactiveMonth(Integer inactiveMonth) {
        this.inactiveMonth = inactiveMonth;
    }
    public String getDemandCity() {
        return demandCity;
    }
    public void setDemandCity(String demandCity) {
        this.demandCity = demandCity;
    }
    public Integer getDemandNum() {
        return demandNum;
    }
    public void setDemandNum(Integer demandNum) {
        this.demandNum = demandNum;
    }
    public Integer getOrderType() { 
        return orderType; 
    } 
    public void setOrderType(Integer orderType) { 
        this.orderType = orderType; 
    }

}
