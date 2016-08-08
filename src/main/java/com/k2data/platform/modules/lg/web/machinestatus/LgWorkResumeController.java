/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.machinestatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.k2data.platform.common.utils.DateUtils;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.modules.lg.dao.LgDeviceSliceStaticsDao;
import com.k2data.platform.modules.lg.entity.LgDeviceSliceStatics;

/**
 * 工作履历分析Controller
 * @author lidong
 * @version 2016-06-24
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/machinestatus/workresume")
public class LgWorkResumeController extends BaseController {
	
	@Autowired
	private LgDeviceSliceStaticsDao lgDeviceSliceStaticsDao;
	
	@RequiresPermissions("lg:machinestatus:workresume:view")
	@RequestMapping(value = "")
	public String list(LgDeviceSliceStatics cond, Model model) {
		model.addAttribute("LgDeviceSliceStatics", cond);
		return "modules/lg/machinestatus/lgWorkResume";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("getResumeData")
	@ResponseBody
	public String getResumeData(LgDeviceSliceStatics cond) {
		Page<LgDeviceSliceStatics> page = new Page<LgDeviceSliceStatics>();
		page.setOrderBy("a.`deviceNo`, a.sliceStart");
		cond.setPage(page);
		List<LgDeviceSliceStatics> datas = lgDeviceSliceStaticsDao.findList(cond);
		
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		String codeTemp = "";
		int i = -1;
		String tempDate = "";
		for (LgDeviceSliceStatics data : datas) {
			if (!codeTemp.equals(data.getDeviceNo())) {	
				Map<String, Object> tempMap = new HashMap<String, Object>();
				codeTemp = data.getDeviceNo();
				dataList.add(tempMap);
				i++;
				tempDate = "";
			}
			
			Map<String, Object> dataMap = dataList.get(i);
			if (dataMap.get("machine_number") == null)
				dataMap.put("machine_number", data.getDeviceNo());
			
			// datas 数组
			List<Map<String, Object>> deviceDataList = null;
			if ((deviceDataList = (List<Map<String, Object>>) dataMap.get("datas")) == null) {
				dataMap.put("datas", new ArrayList<Map<String, Object>>());
				deviceDataList = (List<Map<String, Object>>) dataMap.get("datas");
			}
			
			// 机器 datas 数据
			Date start = data.getSliceStart();
			Date end = data.getSliceStop();
			
			String date = DateUtils.formatDate(start, "yyyy-MM-dd");
			String startTime = DateUtils.formatDate(start, "HH:mm");
			String endTime = DateUtils.formatDate(end, "HH:mm");
			
			Map<String, Object> datasMap = null;
			if (!tempDate.equals(date)) {
				datasMap = new HashMap<String, Object>();
				deviceDataList.add(datasMap);
				tempDate = date;
			} else {
				datasMap = deviceDataList.get(deviceDataList.size() - 1);
			}
			
			if (datasMap.get("date") == null)
				datasMap.put("date", date);
			
			if (datasMap.get("data") == null) {
				datasMap.put("data", new ArrayList<Map<String, String>>());
			}
			
			List<Map<String, String>> innerList = (List<Map<String, String>>) datasMap.get("data");
			Map<String, String> innerMap = new HashMap<String, String>();
			innerMap.put("address", data.getAddress());
			innerMap.put("beginTime", startTime);
			innerMap.put("endTime", endTime);
			innerMap.put("total", data.getSliceCount() + "");
			innerMap.put("warn", "0");
			innerList.add(innerMap);
		}

		return JSON.toJSONString(dataList);
	}

}