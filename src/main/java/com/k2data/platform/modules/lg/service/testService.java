package com.k2data.platform.modules.lg.service;

import java.text.ParseException;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-context.xml"})
public class testService {
	
	@Autowired
	private LgSliceStaticsSwitchService lgSliceStaticsSwitchService;
	
	@org.junit.Test
	public void testMethod() throws ParseException{
		lgSliceStaticsSwitchService.addSliceStaticsSwitch();
	}
}
