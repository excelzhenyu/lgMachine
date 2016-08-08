package com.k2data.platform.modules.workcenter.quartz.job;

import org.quartz.DisallowConcurrentExecution;

import com.k2data.platform.modules.workcenter.quartz.common.BaseWorkCenterJob;
import com.k2data.platform.modules.workcenter.quartz.common.MessageExt;

@DisallowConcurrentExecution
public class QrtzTestJob4 extends BaseWorkCenterJob<String> {

	@Override
	protected MessageExt run(String data) {
		System.out.println("=========================");
		System.out.println("我是QrtzTestJob4..............");
		System.out.println("=========================");
		
		return new MessageExt(0, 0);
	}

}
