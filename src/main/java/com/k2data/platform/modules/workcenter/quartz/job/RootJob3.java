package com.k2data.platform.modules.workcenter.quartz.job;

import org.quartz.DisallowConcurrentExecution;

import com.k2data.platform.modules.workcenter.quartz.common.BaseWorkCenterJob;
import com.k2data.platform.modules.workcenter.quartz.common.MessageExt;

@DisallowConcurrentExecution
public class RootJob3 extends BaseWorkCenterJob<String> {

	@Override
	protected MessageExt run(String data) {
		System.out.println("=========================");
		System.out.println("我是rootjob3..............");
		System.out.println("=========================");
		
		return new MessageExt(0, 0, "3");
	}


}
