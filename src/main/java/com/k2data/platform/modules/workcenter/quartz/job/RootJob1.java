package com.k2data.platform.modules.workcenter.quartz.job;

import org.quartz.DisallowConcurrentExecution;

import com.k2data.platform.modules.workcenter.quartz.common.BaseWorkCenterJob;
import com.k2data.platform.modules.workcenter.quartz.common.MessageExt;

@DisallowConcurrentExecution
public class RootJob1 extends BaseWorkCenterJob<String> {

	@Override
	protected MessageExt run(String data) {
		System.out.println("=========================");
		System.out.println("我是rootjob1..............");
		System.out.println("=========================");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new MessageExt(0, 0, "111", "11111");
	}

}
