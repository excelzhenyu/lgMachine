package com.k2data.platform.modules.lg.entity.onroadmachine;

import java.util.Date;

public class LgOnroadMachineCond {

    private String[] deviceNoMult;
    private String[] transportUnitMult;
    private String[] purchaserUnitMult;
    private Date transportDateBegin;
    private Date transportDateEnd;
    
    public String[] getDeviceNoMult() {
        return deviceNoMult;
    }
    public void setDeviceNoMult(String[] deviceNoMult) {
        this.deviceNoMult = deviceNoMult;
    }
    public String[] getTransportUnitMult() {
        return transportUnitMult;
    }
    public void setTransportUnitMult(String[] transportUnitMult) {
        this.transportUnitMult = transportUnitMult;
    }
    public String[] getPurchaserUnitMult() {
        return purchaserUnitMult;
    }
    public void setPurchaserUnitMult(String[] purchaserUnitMult) {
        this.purchaserUnitMult = purchaserUnitMult;
    }
    public Date getTransportDateBegin() {
        return transportDateBegin;
    }
    public void setTransportDateBegin(Date transportDateBegin) {
        this.transportDateBegin = transportDateBegin;
    }
    public Date getTransportDateEnd() {
        return transportDateEnd;
    }
    public void setTransportDateEnd(Date transportDateEnd) {
        this.transportDateEnd = transportDateEnd;
    }

}
