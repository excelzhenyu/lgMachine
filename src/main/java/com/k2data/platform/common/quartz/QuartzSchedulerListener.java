package com.k2data.platform.common.quartz;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * quartz 监听
 * 
 * @author lidong
 * @since 2016-5-20
 */
@Component
public class QuartzSchedulerListener implements SchedulerListener {
	
	private static final Logger logger = LoggerFactory.getLogger(QuartzSchedulerListener.class);
	
	@Override
	public void jobAdded(JobDetail jobDetail) {
		logger.info("jobAdd, key:{}, des:{}", jobDetail.getKey(), jobDetail.getDescription());
	}

	@Override
	public void jobDeleted(JobKey jobKey) {
		logger.info("jobDeleted, Group:{}, Name:{}", jobKey.getGroup(), jobKey.getName());
	}

	@Override
	public void jobPaused(JobKey jobKey) {
		logger.info("jobDeleted, Group:{}, Name:{}", jobKey.getGroup(), jobKey.getName());
	}

	@Override
	public void jobResumed(JobKey jobKey) {
		logger.info("jobDeleted, Group:{}, Name:{}", jobKey.getGroup(), jobKey.getName());
	}

	@Override
	public void jobScheduled(Trigger arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jobUnscheduled(TriggerKey arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jobsPaused(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jobsResumed(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schedulerError(String arg0, SchedulerException arg1) {
		// TODO Auto-generated method stub
//		logger.info("job执行异常:" + arg1);
	}

	@Override
	public void schedulerInStandbyMode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schedulerShutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schedulerShuttingdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schedulerStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schedulerStarting() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schedulingDataCleared() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggerFinalized(Trigger arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggerPaused(TriggerKey arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggerResumed(TriggerKey arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggersPaused(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggersResumed(String arg0) {
		// TODO Auto-generated method stub
		
	}


}
