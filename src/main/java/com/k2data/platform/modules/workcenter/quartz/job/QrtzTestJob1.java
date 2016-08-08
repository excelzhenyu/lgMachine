package com.k2data.platform.modules.workcenter.quartz.job;

import org.quartz.DisallowConcurrentExecution;

import com.k2data.platform.modules.workcenter.quartz.common.BaseWorkCenterJob;
import com.k2data.platform.modules.workcenter.quartz.common.MessageExt;

@DisallowConcurrentExecution
public class QrtzTestJob1 extends BaseWorkCenterJob<String> {

	@Override
	protected MessageExt run(String data) {
		System.out.println("=========================");
		System.out.println("我是QrtzTestJob1.............. data: " + data);
		System.out.println("=========================");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new MessageExt(1, 2);
	}

}
