package com.k2data.platform.modules.lg.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.modules.lg.common.service.GeneralQueryService;
import com.k2data.platform.modules.lg.entity.citylist.LgSystemCity;
import com.k2data.platform.modules.sys.utils.DictUtils;

/**
 * 提供各种通用查询类
 */
@Controller
@RequestMapping("${adminPath}/lg/general/query")
public class GeneralQueryController extends BaseController {

    @Autowired
    private GeneralQueryService generalQueryService;

    /**
     * 字典类型列表
     * 
     * @param type
     * @return
     */
    @RequestMapping("dictList")
    @ResponseBody
    public String dictList(String type) {
        return JSON.toJSONString(DictUtils.getDictList(type));
    }
    
    /**
     * 产品类型
     * 
     * @param machineType 主机类别
     * @return
     */
    @RequestMapping("productTypeList")
    @ResponseBody
    public String productTypeList(String machineType) {
        return JSON.toJSONString(generalQueryService.getProductTypeList(machineType));
    }

    /**
     * 订货号
     * 
     * @param productType 主机型号
     * @return
     */
    @RequestMapping("orderNumberList")
    @ResponseBody
    public String orderNumberList(String productType) {
        return JSON.toJSONString(generalQueryService.getOrderNumberList(productType));
    }

    /**
     * 得到机器号列表
     */
    @RequestMapping("deviceNoList")
    @ResponseBody
    public String deviceNoList() {
        return JSON.toJSONString(generalQueryService.getDeviceNoList());
    }

    /**
     * 整机维度
     */
    @RequestMapping("machineDimensionList")
    @ResponseBody
    public String machineDimensionList(Integer type) {
        return JSON.toJSONString(generalQueryService.getMachineDimensionList(type));
    }

    /**
     * 得到省份列表
     */
    @RequestMapping("provinceList")
    @ResponseBody
    public String provinceList() {
        return JSON.toJSONString(generalQueryService.getProvinceList());
    }
    
    /**
     * 得到城市列表
     */
    @RequestMapping("cityList")
    @ResponseBody
    public String cityList() {
        return JSON.toJSONString(generalQueryService.getCityList());
    }
    
    /** 
     * 地区列表 
     */ 
    @RequestMapping("systemCityList") 
    @ResponseBody 
    public String systemCityList(LgSystemCity lgSystemCity) { 
        return JSON.toJSONString(generalQueryService.getSystemCityList(lgSystemCity)); 
    } 
    
    /**
     * 根据设备号查询传感器列表
     * 
     * @param deviceNo
     * @return
     */
    @RequestMapping("sensorNameList")
    @ResponseBody
    public String sensorNameList(String deviceNo) {
        return JSON.toJSONString(generalQueryService.getSensorNameList(deviceNo));
    }
    
    /**
     * 得到经销年度列表
     */
    @RequestMapping("saleYearList")
    @ResponseBody
    public String saleYearList(Integer yearGreaterOE) {
        return JSON.toJSONString(generalQueryService.getSaleYearList(yearGreaterOE));
    }

}
