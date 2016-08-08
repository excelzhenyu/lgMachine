package com.k2data.platform.modules.lg.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k2data.platform.modules.lg.dao.LgGpsTemplateDetailDao;
import com.k2data.platform.modules.lg.dao.LgMachineDimensionDao;
import com.k2data.platform.modules.lg.dao.LgMachineTypeDao;
import com.k2data.platform.modules.lg.dao.LgMachineprofileDao;
import com.k2data.platform.modules.lg.dao.baseinfo.LgDimDateDao;
import com.k2data.platform.modules.lg.dao.citylist.LgSystemCityDao;
import com.k2data.platform.modules.lg.entity.LgGpsTemplateDetail;
import com.k2data.platform.modules.lg.entity.LgMachineDimension;
import com.k2data.platform.modules.lg.entity.LgMachineType;
import com.k2data.platform.modules.lg.entity.baseinfo.LgDimDate;
import com.k2data.platform.modules.lg.entity.citylist.LgSystemCity;
/**
 * 通用查询 service
 */
@Service
public class GeneralQueryService {

    @Autowired
    private LgMachineTypeDao lgMachineTypeDao;
    @Autowired
    private LgMachineprofileDao lgMachineprofileDao;
    @Autowired
    private LgMachineDimensionDao lgMachineDimensionDao;
    @Autowired
    private LgSystemCityDao lgSystemCityDao;
    @Autowired
    private LgGpsTemplateDetailDao lgGpsTemplateDetailDao;
    @Autowired
    private LgDimDateDao lgDimDateDao;

    /**
     * 主机型号列表
     * 
     * @param machineType 主机类别
     * @return
     */
    public List<String> getProductTypeList(String machineType) {
        return lgMachineTypeDao.getProductTypeList(machineType);
    }

    /**
     * 订货号列表
     * 
     * @param productType 主机型号
     * @return
     */
    public List<LgMachineType> getOrderNumberList(String productType) {
        return lgMachineTypeDao.getOrderNumberList(productType);
    }

    /**
     * 得到机器号列表
     */
    public List<String> getDeviceNoList() {
        return lgMachineprofileDao.getDeviceNoList();
    }

    /**
     * 整机维度查询
     */
    public List<LgMachineDimension> getMachineDimensionList(Integer type) {
        return lgMachineDimensionDao.getMachineDimension(type);
    }

    /**
     * 得到省份列表
     */
    public List<LgSystemCity> getProvinceList() {
        return lgSystemCityDao.getProvinceList();
    }
    
    /**
     * 得到城市列表
     */
    public List<LgSystemCity> getCityList() {
        return lgSystemCityDao.getCityList();
    }
    
    /** 
     * 地区列表 
     *  
     * @param lgSystemCity 
     * @return 
     */ 
    public List<LgSystemCity> getSystemCityList(LgSystemCity lgSystemCity) { 
        return lgSystemCityDao.findList(lgSystemCity); 
    } 

    /**
     * 根据设备号查询传感器列表
     * 
     * @param deviceNo
     * @return
     */
    public List<LgGpsTemplateDetail> getSensorNameList(String deviceNo) {
        return lgGpsTemplateDetailDao.getSensorNameList(deviceNo);
    }
    
    /**
     * 得到经销年度列表
     */
    public List<LgDimDate> getSaleYearList(Integer yearGreaterOE) {
        return lgDimDateDao.getYearList(yearGreaterOE);
    }
    
}
