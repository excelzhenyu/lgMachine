/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.workcenter.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.common.utils.FileUtils;
import com.k2data.platform.common.utils.FreeMarkers;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.workcenter.entity.Workcenter;
import com.k2data.platform.modules.workcenter.entity.WorkcenterGenJob;
import com.k2data.platform.modules.workcenter.quartz.common.JobStatus;
import com.k2data.platform.modules.workcenter.utils.WorkCenterUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

import com.k2data.platform.modules.gen.entity.GenTemplate;
import com.k2data.platform.modules.gen.util.GenUtils;
import com.k2data.platform.modules.workcenter.dao.WorkcenterDao;
import com.k2data.platform.modules.workcenter.dao.WorkcenterGenJobDao;

/**
 * Quartz Job 生成方案Service
 * @author lidong
 * @version 2016-06-12
 */
@Service
@Transactional(readOnly = true)
public class WorkcenterGenJobService extends CrudService<WorkcenterGenJobDao, WorkcenterGenJob> {
	
	private static final Logger logger = LoggerFactory.getLogger(WorkcenterGenJobService.class);
	
	@Autowired
	private WorkcenterService workcenterService;
	@Autowired
	private WorkcenterDao workcenterDao;

	public WorkcenterGenJob get(String id) {
		return super.get(id);
	}
	
	public List<WorkcenterGenJob> findList(WorkcenterGenJob workcenterGenJob) {
		return super.findList(workcenterGenJob);
	}
	
	public Page<WorkcenterGenJob> findPage(Page<WorkcenterGenJob> page, WorkcenterGenJob workcenterGenJob) {
		return super.findPage(page, workcenterGenJob);
	}
	
	@Transactional(readOnly = false)
	public void save(WorkcenterGenJob workcenterGenJob) {
		super.save(workcenterGenJob);
	}
	
	@Transactional(readOnly = false)
	public void delete(WorkcenterGenJob workcenterGenJob) {
		super.delete(workcenterGenJob);
	}
	
	/**
	 * 生成 Quartz Job Java文件,xml配置和表记录
	 * 
	 * @param workcenterGenJob
	 */
	@Transactional
	public void generateJob(WorkcenterGenJob newJob, WorkcenterGenJob oldJob) {
		genJavaFile(newJob, oldJob);	// 生成 job java文件
		
		genXmlConfig(newJob, oldJob);	// quartz-jobs.xml 配置文件
		
		genDataBaseData(newJob, oldJob);	// 更新数据库
	}
	
	/* *****************************************
	 * 内部方法
	 * *****************************************/
	
	/**
	 * 自动生成 Quartz Job 的 Java 文件
	 * 
	 * @param newJob
	 * @param oldJob
	 */
	private void genJavaFile(WorkcenterGenJob newJob, WorkcenterGenJob oldJob) {
		// 如果选择了更新 Java 文件选项并且 Job name 或 Group 做了更新，就先删除旧的文件
		if (!StringUtils.isBlank(newJob.getId()) && newJob.getReplaceJavaFile()
				&& (!newJob.getJobName().equals(oldJob.getJobName())
						|| !newJob.getJobGroup().equals(oldJob.getJobGroup()))) {
			logger.debug("Job name 或 Group 做了更新，先删除旧的 Java 文件");
			
			String dirPath = Global.getProjectPath() 	// Java 文件目录位置
					+ "src/main/java/com/k2data/platform/modules/workcenter/quartz/job/"
					+ oldJob.getJobGroup().toLowerCase();
			
			String fileName = StringUtils.replace(dirPath + "/"	// Java 文件位置
					+ StringUtils.capitalize(oldJob.getJobName()) + "Job.java", "/", File.separator);
			
			FileUtils.deleteFile(fileName);
			
			// 如果删除文件后文件夹为空，就删除文件夹
			File dir = new File(dirPath);
			if (dir.isDirectory() && dir.listFiles() != null && dir.listFiles().length == 0)
				FileUtils.deleteDirectory(dirPath);
		}
		
		logger.debug("自动生成 Quartz Job 的 Java 文件");
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("packageName", "com.k2data.platform.modules");
		model.put("jobGroup", newJob.getJobGroup());
		model.put("jobName", newJob.getJobName());
		model.put("functionName", newJob.getJobName());
		model.put("functionAuthor", newJob.getFunctionAuthor());
		
		GenTemplate genTemplate = WorkCenterUtils.xmlFileToObject("baseJobTemplate.xml", GenTemplate.class);
		
		GenUtils.generateToFile(genTemplate, model, newJob.getReplaceJavaFile());
	}
	
	/**
	 * 自动生成 Quartz Job 的 Xml 配置文件
	 * 
	 * @param newJob
	 * @param oldJob
	 */
	private void genXmlConfig(WorkcenterGenJob newJob, WorkcenterGenJob oldJob) {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("tJob", newJob);
		
		String temFilePath = StringUtils.replace(Global.getProjectPath() 	// 模板文件位置
				+ "/src/main/resources/templates/modules/workcenter/baseXmlSingleJob.ftl", "/", File.separator);
		String xmlFilePath = StringUtils.replace(Global.getProjectPath() 	// xml 配置文件位置
				+ "/src/main/resources/quartz-jobs.xml", "/", File.separator);
		
		// 根据模板生成字符串后添加到配置文件里
		File temJobFile = new File(temFilePath);
		File xmlFile = new File(xmlFilePath);
		try {
			Template template = new Template("singleJobTem", new FileReader(temJobFile), new Configuration());
			String content = FreeMarkers.renderTemplate(template, model);
			
			logger.debug("自动生成 Quartz Job 的 Xml 配置文件: {}", content);
			
			String fileStr = FileUtils.readFileToString(xmlFile, "utf-8");
			
			// 新数据直接在末尾插入。旧数据修改，正则匹配后替换
			if (StringUtils.isBlank(newJob.getId())) {
				StringBuilder fileStrBuilder = new StringBuilder(fileStr);
				fileStrBuilder.insert(fileStrBuilder.indexOf("</schedule>"), "\n" +  content + "\t");
				fileStr = fileStrBuilder.toString();
			} else {
				StringBuilder patternStr = new StringBuilder();
				
				// 按照 FunctionName JobName JobGroup 进行匹配
				patternStr.append("\n\t\t")
						  .append("<!-- ").append(oldJob.getFunctionName()).append(" -->([\\s\\S]*?)")
						  .append("<name>").append(oldJob.getJobName()).append("</name>([\\s\\S]*?)")
						  .append("<group>").append(oldJob.getJobGroup()).append("</group>([\\s\\S]*?)");
				
				if (Global.YES.equals(oldJob.getRootJob())) {
					patternStr.append("<job-name>").append(oldJob.getJobName()).append("</job-name>([\\s\\S]*?)")
							  .append("<job-group>").append(oldJob.getJobGroup()).append("</job-group>([\\s\\S]*?)")
							  .append("</trigger>");
				} else {
					patternStr.append("</job>");
				}
				
				Pattern pattern = Pattern.compile(patternStr.toString());
				Matcher jobMatcher = pattern.matcher(fileStr);
				
				if (jobMatcher.find())
					logger.debug("匹配到的数据:\n{}", jobMatcher.group());
				
				fileStr = jobMatcher.replaceAll("\n" +  content.substring(0, content.length() - 1));	// substring 去掉末尾 \n
			}
			
			FileUtils.writeToFile(xmlFilePath, fileStr, false);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 自动生成 workcenter 表数据，没有插入，有就更新
	 * 
	 * @param newJob
	 * @param oldJob
	 */
	private void genDataBaseData(WorkcenterGenJob newJob, WorkcenterGenJob oldJob) {
		logger.debug("自动生成 Quartz Job 的数据库数据");
		
		Workcenter cond = new Workcenter();
		cond.setName(oldJob == null ? newJob.getJobName() : oldJob.getJobName());
		cond.setGroup((oldJob == null ? newJob.getJobGroup() : oldJob.getJobGroup()));
		Workcenter workcenter = workcenterDao.getWorkCenter(cond);
		
		if (workcenter != null) {
			workcenter.setCronExpression(newJob.getCronExpression());
		} else {
			workcenter = new Workcenter();
			
			workcenter.setInsertTime(new Date());
			workcenter.setRunCount(0l);
			workcenter.setStatus(JobStatus.PAUSED.getStatus());
			workcenter.setCronExpression(newJob.getCronExpression());
			workcenter.setxAxis(100);
			workcenter.setyAxis(100);
		}
		
		workcenter.setNameCn(newJob.getFunctionName());
		workcenter.setName(newJob.getJobName());
		workcenter.setGroup(newJob.getJobGroup());
		
		if (Global.YES.equals(newJob.getRootJob()))
			workcenter.setParentId("0");
		
		workcenterService.save(workcenter);
	}
	
}