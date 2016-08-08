/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.workcenter.dao.SliceLogDao;
import com.k2data.platform.modules.workcenter.dao.WorkcenterDao;
import com.k2data.platform.modules.workcenter.entity.SliceLog;
import com.k2data.platform.modules.workcenter.entity.Workcenter;
import com.k2data.platform.modules.workcenter.service.WorkcenterService;
import com.k2data.platform.modules.workcenter.utils.WorkCenterUtils;

/**
 * 工作中心Controller
 * @author lidong
 * @version 2016-05-20
 */
@Controller
@RequestMapping(value = "${adminPath}/workcenter/workcenter")
public class WorkcenterController extends BaseController {

	@Autowired
	private WorkcenterService workcenterService;
	@Autowired
	private WorkcenterDao workcenterDao;
	@Autowired
	private SliceLogDao sliceLogDao;
	
	@ModelAttribute
	public Workcenter get(@RequestParam(required=false) String id) {
		Workcenter entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = workcenterService.get(id);
		}
		if (entity == null){
			entity = new Workcenter();
		}
		
		if ("0".equals(entity.getParentId())) {
			entity.setParentName("根节点");
			entity.setParentGroup("根节点");
		}
		
		return entity;
	}
	
	@RequiresPermissions("workcenter:workcenter:view")
	@RequestMapping(value = {"list", ""})
	public String list(Workcenter workcenter, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Workcenter> page = workcenterService.findPage(new Page<Workcenter>(request, response), workcenter); 
		model.addAttribute("page", page);
		return "modules/workcenter/workcenterList";
	}

	@RequiresPermissions("workcenter:workcenter:view")
	@RequestMapping(value = "form")
	public String form(Workcenter workcenter, Model model) {
		model.addAttribute("workcenter", workcenter);
		return "modules/workcenter/workcenterForm";
	}

	@RequiresPermissions("workcenter:workcenter:edit")
	@RequestMapping(value = "save")
	public String save(Workcenter workcenter, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, workcenter)){
			return form(workcenter, model);
		}
		
		if (workcenter.getIsNewRecord()) {
			workcenter.setInsertTime(new Date());
			workcenter.setRunCount(0l);
		}
		
		workcenterService.save(workcenter);
		addMessage(redirectAttributes, "保存工作中心成功");
		return "redirect:"+Global.getAdminPath()+"/workcenter/workcenter/?repage";
	}
	
	@RequiresPermissions("workcenter:workcenter:edit")
	@RequestMapping(value = "delete")
	public String delete(Workcenter workcenter, RedirectAttributes redirectAttributes) {
		workcenterService.delete(workcenter);
		addMessage(redirectAttributes, "删除工作中心成功");
		return "redirect:"+Global.getAdminPath()+"/workcenter/workcenter/?repage";
	}
	
	/**
	 * 单个 job 组图表页
	 * 
	 * @param workcenter
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("workcenter:workcenter:view")
	@RequestMapping(value = "visualization")
	public String visualization(Workcenter workcenter, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Workcenter> page = workcenterService.findPage(new Page<Workcenter>(request, response), workcenter); 
		model.addAttribute("page", page);
		model.addAttribute("rootJobGroup", workcenter.getGroup());
		return "modules/workcenter/workcenterVisual";
	}
	
	/**
	 * Job 概览页面
	 * 
	 * @param workcenter
	 * @return
	 */
	@RequiresPermissions("workcenter:workcenter:view")
	@RequestMapping(value = "index")
	public String index(Workcenter workcenter) {
		if (!StringUtils.isBlank(workcenter.getName()) && !StringUtils.isBlank(workcenter.getGroup())) {
			workcenterService.updateStatusByNameNGroup(workcenter);
		}
		
		return "modules/workcenter/workcenterIndex";
	}
	
	/**
	 * 自动生成 Quartz Job 模板页面
	 * 
	 * @return
	 */
	@RequestMapping(value="jobGenerater")
	@RequiresPermissions("workcenter:workcenter:edit")
	public String jobGenerater() {
		
		return "modules/workcenter/jobGenerater";
	}
	
	/**
	 * 检查指定 JOB 是否存在
	 * 
	 * @param workcenter
	 * @return
	 */
	@RequestMapping(value="checkExist")
	@ResponseBody
	public boolean checkExist(Workcenter workcenter) {
		return Global.YES.equals(workcenterDao.checkExist(workcenter));
	}
	
	/**
	 * 选择父级 Job 时的列表 Dom 
	 * 
	 * @param workcenter
	 * @return
	 */
	@RequestMapping(value="workCenterList")
	@ResponseBody
	public String workCenterList(Workcenter workcenter, HttpServletRequest request, HttpServletResponse response) {
		Page<Workcenter> page = workcenterService.findPage(new Page<Workcenter>(request, response), workcenter); 
		
		Map<String, Object> domMap = new HashMap<String, Object>();
		domMap.put("data", WorkCenterUtils.genParentJobListDom(page.getList()));
		domMap.put("page", page.getHtml());
		
		return JSON.toJSONString(domMap);
	}
	
	/**
	 * 生成前端 job 依赖图需要的json
	 * 
	 * @return
	 */
	@RequestMapping(value = "jobjson")
	@ResponseBody
	public String jobJson(HttpServletRequest request) {
		String rootJobGroup = request.getParameter("rootJobGroup");
		
		Workcenter cond = new Workcenter();
		cond.setGroup(rootJobGroup);
		List<Workcenter> workcenters = workcenterDao.findList(cond);
		
		Map<String, Integer> indexMap = new HashMap<String, Integer>();
		List<Map<String, Object>> nodeList = new ArrayList<Map<String,Object>>();
		List<Map<String, Integer>> linkList = new ArrayList<Map<String,Integer>>();
		
		// 添加索引
		for (int i = 0; i < workcenters.size(); i++) {
			Workcenter workcenter = workcenters.get(i);
			indexMap.put(workcenter.getGroup() + workcenter.getName(), i);
		}	
		
		for (int i = 0; i < workcenters.size(); i++) {
			Workcenter workcenter = workcenters.get(i);
			
			Map<String, Object> nodeMap = new HashMap<String, Object>();
			nodeMap.put("name", workcenter.getName() + ":" + workcenter.getNameCn());
			nodeMap.put("status", workcenter.getStatus());
			nodeMap.put("x", workcenter.getxAxis());
			nodeMap.put("y", workcenter.getyAxis());
			nodeMap.put("fixed", true);
			nodeList.add(nodeMap);
			
			if (!StringUtils.isBlank(workcenter.getParentGroup())) {
				Map<String, Integer> linkMap = new HashMap<String, Integer>();
				linkMap.put("source", indexMap.get(workcenter.getParentGroup() + workcenter.getParentName()));
				linkMap.put("target", i);
				linkList.add(linkMap);
			}
		}
		
		Map<String, List<?>> graph = new HashMap<String, List<?>>();
		graph.put("nodes", nodeList);
		graph.put("links", linkList);
		
		String jsonString = JSON.toJSONString(graph);
		
		return jsonString;
	}
	
	/**
	 * 得到 job 执行时间的　json
	 * 
	 * @return
	 */
	@RequestMapping(value = "jobexetimejson")
	@ResponseBody
	public String jobExeTimeJson(Workcenter workcenter, HttpServletRequest request) {
		SliceLog cond = new SliceLog();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR_OF_DAY, -3);	
		
		cond.setBeginInsertTime(calendar.getTime());
		cond.setEndInsertTime(new Date());
		cond.setJobGroup(request.getParameter("rootJobGroup"));
		
		Page<SliceLog> page = new Page<SliceLog>();
		page.setOrderBy("jobname, inserttime");
		cond.setPage(page);
		
		List<SliceLog> sliceLogs = sliceLogDao.findList(cond);
		
		// 柱状图json 排序规则，数字大小优先，之后 start > end
		Map<String, List<Date>> map = new TreeMap<String, List<Date>>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if (o1.equals(o2))
					return 0;
				
				Pattern pattern = Pattern.compile("(\\d+)");
				
				Matcher o1Matcher = pattern.matcher(o1);
				Matcher o2Matcher = pattern.matcher(o2);
				
				if (o1Matcher.find() && o2Matcher.find()) {
					int o1Num = Integer.valueOf(o1Matcher.group());
					int o2Num = Integer.valueOf(o2Matcher.group());
					
					int compareValue = 0;
					
					compareValue = (o1Num > o2Num) ? 1 
							: (o1Num < o2Num) ? -1 
									: (o1Num == o2Num && o1.contains("start")) ? -1 : 1;
					
					return compareValue;
				}
				
				return o1.compareTo(o2);
			}
		});
		
		int i = 0;
		String lastJobName = "";
		
		List<String> names = new ArrayList<String>();
		for (SliceLog sliceLog : sliceLogs) {
			if (!lastJobName.equals(sliceLog.getJobName())) {
				i = 0;
				names.add(sliceLog.getJobName());
				lastJobName = sliceLog.getJobName();
			}
			
			if (map.get(i + "start") == null) {
				List<Date> startList = new ArrayList<Date>();
				List<Date> endList = new ArrayList<Date>();
				
				if (names.size() != 1) {
					for (int j = 1; j < names.size(); j++) {
						startList.add(null);
						endList.add(null);
					}
				}
				
				startList.add(sliceLog.getStart());
				endList.add(sliceLog.getEnd());
				
				map.put(i + "start", startList);
				map.put(i + "end", endList);
			} else {
				map.get(i + "start").add(sliceLog.getStart());
				map.get(i + "end").add(sliceLog.getEnd());
			}
			
			i++;
		}
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("category", names);
		jsonMap.put("exeTimeList", map);
		
		SerializeConfig mapping = new SerializeConfig();
		mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss.SSS"));
		
		return JSON.toJSONString(jsonMap, mapping);
	}
	
	/**
	 * 渲染 job 索引页需要的 ul
	 * 
	 * @param workcenter
	 * @return
	 */
	@RequestMapping(value="jobindexul")
	@ResponseBody
	public String jobIndexUl(Workcenter workcenter) {
		List<Workcenter> workcenters = workcenterDao.getAllWorkCenter();

		Map<String, Workcenter> rootJobMap = WorkCenterUtils.getRootJobMap(workcenters);
		
		return WorkCenterUtils.genJobIndexUl(rootJobMap); 
	}
	
	/**
	 * 根据名字和组更新坐标
	 * 
	 * @param workcenter
	 */
	@RequestMapping(value="updateAxisByNameNGroup")
	@ResponseBody
	public void updateAxisByNameNGroup(Workcenter workcenter) {
		workcenterService.updateAxisByNameNGroup(workcenter);
	}
	
}