package com.k2data.platform.common.quartz;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;

/**
 * Quartz 管理类
 * 
 * @author lidong
 * @since 2016-5-20
 */
public class QuartzManager {
	
	/**
	 * 更新当前运行JOB的定时表达式
	 * 
	 * @param context Quartz的上下文
	 * @param newCronExpression 新定时表达式
	 * @return true 已更新 false 不需要更新（定时表达式相同）
	 */
	public static boolean updateCronExpression(final JobExecutionContext context, final String newCronExpression) {
		// 判断是否需要更新定时表达式
		if (!StringUtils.isBlank(newCronExpression)) {
			final Scheduler scheduler = context.getScheduler();
			
			try {
				final CronTrigger trigger = (CronTrigger) context.getTrigger();
				final TriggerKey triggerKey = trigger.getKey();

				if (!newCronExpression.equals(trigger.getCronExpression())) { // 更新定时计划任务执行时间表达式
					final CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(newCronExpression);
					
					final CronTrigger newTrigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

					scheduler.rescheduleJob(triggerKey, newTrigger);
					
					return true;
				}
			} catch (SchedulerException se) {
				throw new QuartzException(se);
			}
		}
		
		return false;
	}

}
