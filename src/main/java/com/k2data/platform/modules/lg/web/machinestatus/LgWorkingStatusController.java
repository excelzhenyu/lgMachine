/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.machinestatus;

import java.util.ArrayList;
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
import com.k2data.platform.common.utils.DateUtils;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.modules.lg.dao.slice.LgSliceTempDataDao;
import com.k2data.platform.modules.lg.entity.machinestatus.LgWorkingStatusCond;
import com.k2data.platform.modules.lg.entity.slice.LgSliceTempDataSection;
import com.k2data.platform.modules.sys.entity.Dict;
import com.k2data.platform.modules.sys.utils.DictUtils;

/**
 * 机器工作情况Controller
 * @author lidong
 * @version 2016-06-24
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/machinestatus/workingstatus")
public class LgWorkingStatusController extends BaseController {
	
	@Autowired
	private LgSliceTempDataDao lgSliceTempDataDao;
	
	@RequiresPermissions("lg:machinestatus:workingstatus:view")
	@RequestMapping(value = "")
	public String list(LgWorkingStatusCond cond, Model model) {
		model.addAttribute("lgWorkingStatusCond", cond);
		return "modules/lg/machinestatus/lgWorkingStatus";
	}
	
	/**
	 * 得到切片地图位置 json
	 * 
	 * @param cond
	 * @return
	 */
	@RequestMapping("statusMapData")
	@ResponseBody
	public String getStatusMapData(LgWorkingStatusCond cond) {
		List<LgSliceTempDataSection> dataList = lgSliceTempDataDao.getSliceLocationList(cond);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();	// 传前台 json 的 map
		
		if ("0".equals(cond.getType())) {	// 实时数据
			Map<String, List<List<String>>> tempTypeMap = new HashMap<String, List<List<String>>>();
			for (LgSliceTempDataSection data : dataList) {
				List<List<String>> iList = tempTypeMap.get(data.getMachineTypeName());
				if (iList == null) {
					iList = new ArrayList<List<String>>();
					tempTypeMap.put(data.getMachineTypeName(), iList);
				}
				
				List<String> jList = new ArrayList<String>();
				jList.add(data.getLongitudeNum() + "");
				jList.add(data.getLatitudeNum() + "");
				jList.add(data.getDeviceNum());
				
				iList.add(jList);
			}
			
			jsonMap.put("seriesData", tempTypeMap);
		} else {	// 24小时轮播数据
			List<Map<String, List<List<String>>>> temp24List = new ArrayList<Map<String,List<List<String>>>>();
			for (int i = 0; i < 24; i++) {
				temp24List.add(new HashMap<String, List<List<String>>>());
			}
			
			for (LgSliceTempDataSection data : dataList) {
				String recordHour = DateUtils.formatDate(data.getRecordTime(), "HH");
				
				Map<String, List<List<String>>> curr24Map = temp24List.get(Integer.valueOf(recordHour));
				List<List<String>> xList = curr24Map.get(data.getMachineTypeName());
				if (xList == null) {
					xList = new ArrayList<List<String>>();
					curr24Map.put(data.getMachineTypeName(), xList);
				}
				
				List<String> yList = new ArrayList<String>();
				yList.add(data.getLongitudeNum() + "");
				yList.add(data.getLatitudeNum() + "");
				yList.add(data.getDeviceNum());
				
				xList.add(yList);
			}
			
			jsonMap.put("seriesData24", temp24List);
		}
		
		// 主机类别数据
		List<Dict> dicts = DictUtils.getDictList("machineType");
		List<String> machineTypes = new ArrayList<String>();
		for (Dict dict : dicts) {
			machineTypes.add(dict.getLabel());
		}
		jsonMap.put("legendData", machineTypes);
		
		return JSON.toJSONString(jsonMap);
	}
	
}