/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.machinestatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.modules.lg.dao.LgDeviceSliceStaticsDao;
import com.k2data.platform.modules.lg.entity.LgDeviceSliceStatics;

/**
 * 机器画像Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/machinestatus/machinepic")
public class LgMachinePicController extends BaseController {
    
    @Autowired
    private LgDeviceSliceStaticsDao lgDeviceSliceStaticsDao;
	
	@RequiresPermissions("lg:machinestatus:machinepic:view")
	@RequestMapping(value = "")
	public String list(LgDeviceSliceStatics cond, Model model) {
		model.addAttribute("LgDeviceSliceStatics", cond);
		return "modules/lg/machinestatus/lgMachinePic";
	}
	
	/**
	 * 第二种布局
	 * 
	 * @param cond
	 * @param model
	 * @return
	 */
	@RequiresPermissions("lg:machinestatus:machinepic:view")
    @RequestMapping(value = "2")
    public String list2(LgDeviceSliceStatics cond, Model model) {
        model.addAttribute("LgDeviceSliceStatics", cond);
        return "modules/lg/machinestatus/lgMachinePic2";
    }
	
	@RequestMapping("seriesData")
	@ResponseBody
	public String workTimeLineData(LgDeviceSliceStatics cond) {
	    Page<LgDeviceSliceStatics> page = new Page<LgDeviceSliceStatics>();
        page.setOrderBy("a.`deviceNo`, a.sliceStart");
        cond.setPage(page);
        List<LgDeviceSliceStatics> datas = lgDeviceSliceStaticsDao.findList(cond);
        
        List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
        

        return JSON.toJSONString(dataList);
	}
	
}