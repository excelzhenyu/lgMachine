package com.k2data.platform.common.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * 自定义 JobFactory，解决 quartz 内无法用 spring 自动注入
 * 
 * @author lidong
 * @since 2016-5-20
 */
public class JobFactoryExt extends SpringBeanJobFactory implements ApplicationContextAware {
	
	private transient AutowireCapableBeanFactory beanFactory;

	@Override
	public void setApplicationContext(final ApplicationContext context) throws BeansException {
		beanFactory = context.getAutowireCapableBeanFactory();
	}
	
	@Override
	protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
		// 忽略 quartz xml plugin
		if (bundle.getJobDetail().getKey().toString().contains("JobSchedulingDataLoaderPlugin"))
			return bundle.getJobDetail().getJobClass().newInstance();
		
		final Object job = super.createJobInstance(bundle);
		beanFactory.autowireBean(job);
		return job;
	}
	
}
