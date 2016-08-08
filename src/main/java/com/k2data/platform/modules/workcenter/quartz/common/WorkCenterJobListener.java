package com.k2data.platform.modules.workcenter.quartz.common;

import java.util.List;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.listeners.JobListenerSupport;

import com.k2data.platform.common.quartz.QuartzException;
import com.k2data.platform.common.utils.SpringContextHolder;
import com.k2data.platform.modules.workcenter.dao.WorkcenterDao;
import com.k2data.platform.modules.workcenter.entity.Workcenter;

/**
 * 工作中心 job 依赖调用使用的监听
 * 添加此监听的 job 在执行完后会查询子 job 并立即触发
 * 
 * @author lidong
 * @since 2016-5-23
 */
public class WorkCenterJobListener extends JobListenerSupport {
	
	private WorkcenterDao workcenterDao = SpringContextHolder.getBean(WorkcenterDao.class);

	@Override
	public String getName() {
		return "WorkCenterJobListener";
	}
	
	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		
		// 触发子 job
		List<Workcenter> workcenters = workcenterDao.getChildrenByParent(jobDataMap.getString("name"), 
																		jobDataMap.getString("group"));
		try {
			JobDataMap childJobDataMap = new JobDataMap();
			for (Workcenter workcenter : workcenters) {
				childJobDataMap.put("data", jobDataMap.get("data"));
				context.getScheduler().triggerJob(JobKey.jobKey(workcenter.getName(), workcenter.getGroup()), childJobDataMap);
			}
		} catch (SchedulerException e) {
			throw new QuartzException(e);
		}
	}

}
