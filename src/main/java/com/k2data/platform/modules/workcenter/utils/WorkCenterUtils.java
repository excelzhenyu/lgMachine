package com.k2data.platform.modules.workcenter.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.k2data.platform.common.mapper.JaxbMapper;
import com.k2data.platform.common.utils.DateUtils;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.sys.utils.DictUtils;
import com.k2data.platform.modules.workcenter.entity.Workcenter;
import com.k2data.platform.modules.workcenter.entity.WorkcenterGroup;
import com.k2data.platform.modules.workcenter.quartz.common.JobStatus;

public class WorkCenterUtils {
	
	/**
	 * XML文件转换为对象
	 * @param fileName
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xmlFileToObject(String fileName, Class<?> clazz){
		try {
			String pathName = "/templates/modules/workcenter/" + fileName;
//			logger.debug("File to object: {}", pathName);
			Resource resource = new ClassPathResource(pathName); 
			InputStream is = resource.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			StringBuilder sb = new StringBuilder();  
			while (true) {
				String line = br.readLine();
				if (line == null){
					break;
				}
				sb.append(line).append("\r\n");
			}
			if (is != null) {
				is.close();
			}
			if (br != null) {
				br.close();
			}

			return (T) JaxbMapper.fromXml(sb.toString(), clazz);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 生成选择父级 job 页面用到的列表 Dom
	 * 
	 * @param workcenters
	 * @return
	 */
	public static String genParentJobListDom(List<Workcenter> workcenters) {
		StringBuilder domStr = new StringBuilder();
		
		domStr.append("<tr>")
			  .append("<input name='jobId' type='hidden' value='0'></input>")
			  .append("<td name='jobName'><a>根节点</a></td>")
			  .append("<td name='jobGroup'>根节点</td>")
			  .append("<td></td>")
			  .append("</tr>");
		
		for (Workcenter workcenter : workcenters) {
			domStr.append("<tr>")
				  .append("<input name='jobId' type='hidden' value='").append(workcenter.getId()).append("'></input>")
				  .append("<td name='jobName'><a>").append(workcenter.getName()).append("</a></td>")
				  .append("<td name='jobGroup'>").append(workcenter.getGroup()).append("</td>")
				  .append("<td>").append(DictUtils.getDictLabel(workcenter.getStatus().toString(), "jobStatus", "")).append("</td>")
				  .append("</tr>");
		}
		
		return domStr.toString();
	}
	
	/**
	 * 得到模态窗口选择任务组的 Dom
	 * 
	 * @param groups
	 * @return
	 */
	public static String genGroupListDom(List<WorkcenterGroup> groups) {
		StringBuilder domStr = new StringBuilder();
		
		for (WorkcenterGroup group : groups) {
			domStr.append("<tr>")
				  .append("<input name='groupId' type='hidden' value='").append(group.getId()).append("'></input>")
				  .append("<td name='groupName'><a>").append(group.getName()).append("</a></td>")
				  .append("<td name='groupDescription'>").append(group.getDescription()).append("</td>")
				  .append("</tr>");
		}
		
		return domStr.toString();
	}

	/**
	 * 根据工作中心 list 获得构造 job 索引页需要的根节点 job 的 map
	 * 
	 * @param workcenters
	 * @return
	 */
	public static Map<String, Workcenter> getRootJobMap(List<Workcenter> workcenters) {
		Workcenter rootJob = null;
		Map<String, Workcenter> rootJobMap = new TreeMap<String, Workcenter>(new Comparator<String>() {	// 根据 key 值排序

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		
		for (Workcenter job : workcenters) {
			// 如果 map 中没有就新增并设置 Group 值，有就直接使用
			if ((rootJob = rootJobMap.get(job.getGroup())) == null) {
				rootJob = new Workcenter();
				rootJob.setGroup(job.getGroup());
				rootJob.setGroupCn(job.getGroupCn());
			}
				
			if ("0".equals(job.getParentId())) {
				rootJob.setName(job.getName());
				rootJob.setStatus(job.getStatus());
				rootJob.setStart(job.getStart());
				rootJob.setEnd(job.getEnd());
			}
			
			rootJob.setJobCount(rootJob.getJobCount() + 1);	// job总数计数
			if (StringUtils.isBlank(job.getParentId())) {
				rootJob.setUnuseJobCount(rootJob.getUnuseJobCount() + 1);	// 未配置 job 计数
			} else {
				rootJob.setUseJobCount(rootJob.getUseJobCount() + 1);	// 已配置 job 计数
			}
			
			// 不同状态 job 计数
			if (JobStatus.RUNNING.getStatus().equals(job.getStatus())) {
				rootJob.setRunningJobCount(rootJob.getRunningJobCount() + 1);
			} else if (JobStatus.PAUSED.getStatus().equals(job.getStatus())) {
				rootJob.setPauseJobCount(rootJob.getPauseJobCount() + 1);
			} else if (JobStatus.FAILURE.getStatus().equals(job.getStatus())) {
				rootJob.setFailureJobCount(rootJob.getFailureJobCount() + 1);
			}
			
			rootJobMap.put(job.getGroup(), rootJob);
		}
		
		return rootJobMap;
	}
	
	/**
	 * 构造 job 索引页显示的 ul
	 * 
	 * @param rootJobMap
	 * @return
	 */
	public static String genJobIndexUl(Map<String, Workcenter> rootJobMap) {
		StringBuilder ulStr = new StringBuilder();
		for (Entry<String, Workcenter> entry : rootJobMap.entrySet()) {
			ulStr.append("<li>")
				 .append("<div class='listbox'>");
			
			String headClass = "listbox-pause";
			if (JobStatus.RUNNING.getStatus().equals(entry.getValue().getStatus())) {
				headClass = "listbox-running";
			} else if (JobStatus.FAILURE.getStatus().equals(entry.getValue().getStatus())) {
				headClass = "listbox-failure";
			}
			
			ulStr.append("<div class='listbox-head " + headClass + "'>")
				 .append("<div class='listbox-head-text'>")
				 .append("Job 总数: " + entry.getValue().getJobCount() + "个	<br />")
				 .append("未配置: " + entry.getValue().getUnuseJobCount() + "个	<br />")
				 .append("已配置: " + entry.getValue().getUseJobCount() + "个	<br />")
				 .append("正常: " + entry.getValue().getRunningJobCount() + "个	<br />")
				 .append("暂停: " + entry.getValue().getPauseJobCount() + "个	<br />")
				 .append("异常: " + entry.getValue().getFailureJobCount() + "个	<br />")
				 .append("开始时间: " + DateUtils.formatDateTime(entry.getValue().getStart()) + "<br />");
			
			if (!JobStatus.RUNNING.getStatus().equals(entry.getValue().getStatus()))
				ulStr.append("结束时间: " + DateUtils.formatDateTime(entry.getValue().getEnd()) + "<br />");
			
			String icon = "icon-play";
			if (!JobStatus.PAUSED.getStatus().equals(entry.getValue().getStatus())) 
				icon = "icon-pause";
			
			ulStr.append("<div class='listbox-head-icon'>")
			 .append("<input id='rootJobName' type='hidden' value='" + entry.getValue().getName() + "' />")
			 .append("<input id='rootJobGroup' type='hidden' value='" + entry.getValue().getGroup() + "' />")
			 .append("<i class='" + icon + "'></i></div>");
			
			ulStr.append("</div><div>");
			
			ulStr.append("</div></div><div class='listbox-bottom'><div class='space'><div>")
				 .append("<a href='visualization?group=" + entry.getValue().getGroup() + "'>")
				 .append(entry.getValue().getGroup()
						 + (StringUtils.isBlank(entry.getValue().getGroupCn()) ? "" : ":" + entry.getValue().getGroupCn()) + "</a>")
				 .append("</div></div></div></div></li>");
		}
		
		return ulStr.toString();
	}
	
}
