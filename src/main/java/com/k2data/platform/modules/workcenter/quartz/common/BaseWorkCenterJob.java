package com.k2data.platform.modules.workcenter.quartz.common;

import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Matcher;
import org.quartz.impl.matchers.KeyMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.k2data.platform.common.quartz.QuartzException;
import com.k2data.platform.common.quartz.QuartzManager;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.modules.workcenter.dao.WorkcenterDao;
import com.k2data.platform.modules.workcenter.entity.SliceLog;
import com.k2data.platform.modules.workcenter.entity.Workcenter;
import com.k2data.platform.modules.workcenter.service.SliceLogService;
import com.k2data.platform.modules.workcenter.service.WorkcenterService;

/**
 * 工作中心依赖调度 job 用到的抽象类，实现单依赖顺序调度 job 
 * 
 * <p>要实现依赖调度的 job 需继承此类，并重写 {@link BaseWorkCenterJob#run()} 方法,
 * 根据情况决定是否添加 @{@link DisallowConcurrentExecution} 注解
 * 
 * @param <T> job 间传递数据的类型
 * @author lidong
 * @since 2016-5-23
 */
@DisallowConcurrentExecution
public abstract class BaseWorkCenterJob<T> extends QuartzJobBean {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseWorkCenterJob.class);
	
	@Autowired
	private SliceLogService sliceLogService;
	@Autowired
	private WorkcenterDao workcenterDao;
	@Autowired
	private WorkcenterService workcenterService;
	
	/**
	 * 要执行的代码
	 */
	protected abstract MessageExt run(final T data);
	
	@SuppressWarnings("unchecked")
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		Date start = null;
		String exceptionStr = null;
		MessageExt messageExt = new MessageExt();
		JobDetail jobDetail = context.getJobDetail();
		
		Workcenter cond = new Workcenter();
		cond.setName(jobDetail.getKey().getName());
		cond.setGroup(jobDetail.getKey().getGroup());
		Workcenter workcenter = workcenterDao.getWorkCenter(cond);
		
		logger.info("trigger job, name: {}, group: {}, status: {}", 
				jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), workcenter.getStatus());
		
		// 暂停状态跳过，运行和异常状态都会继续执行
		if (JobStatus.PAUSED.getStatus().equals(workcenter.getStatus()))
			return;
		
		QuartzManager.updateCronExpression(context, workcenter.getCronExpression());
		
		try {
			start = new Date();
			JobDataMap jobDataMap = jobDetail.getJobDataMap();
			
			messageExt = run((T) context.getTrigger().getJobDataMap().get("data"));
			
			jobDataMap.put("name", jobDetail.getKey().getName());
			jobDataMap.put("group", jobDetail.getKey().getGroup());
			jobDataMap.put("data", messageExt.getData());
			
			Matcher<JobKey> matcher = KeyMatcher.keyEquals(jobDetail.getKey());
			context.getScheduler().getListenerManager().addJobListener(new WorkCenterJobListener(), matcher);
		} catch (Exception e) {
			exceptionStr = ExceptionUtils.getStackTrace(e);
			if (exceptionStr.length() > 1000) {
				exceptionStr = ExceptionUtils.getStackTrace(e.getCause());
				if (exceptionStr.length() > 1000)
					exceptionStr = exceptionStr.substring(0, 1000);
			}
			throw new QuartzException(e);
		} finally {
			// 更新 workcenter　表状态
			workcenter.setStart(start);
			workcenter.setEnd(new Date());
			workcenter.setRunCount(workcenter.getRunCount() + 1);
			if (!StringUtils.isBlank(exceptionStr)) {
				workcenter.setStatus(JobStatus.FAILURE.getStatus());
			} else {
				workcenter.setStatus(JobStatus.RUNNING.getStatus());
			}
			workcenterService.save(workcenter);
			
			// log
			SliceLog sliceLog = new SliceLog();
			sliceLog.setInsertTime(new Date());
			sliceLog.setJobName(jobDetail.getKey().getName());
			sliceLog.setJobGroup(jobDetail.getKey().getGroup());
			sliceLog.setStart(start);
			sliceLog.setEnd(new Date());
			sliceLog.setWriteCount(messageExt.getWriteCount());
			sliceLog.setErrorCount(messageExt.getErrorCount());
			sliceLog.setLog(StringUtils.isBlank(exceptionStr) ? messageExt.getLog() : exceptionStr);
			sliceLogService.save(sliceLog);
		}
	}

}
