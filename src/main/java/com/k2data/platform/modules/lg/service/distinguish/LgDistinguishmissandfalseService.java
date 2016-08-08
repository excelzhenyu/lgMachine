/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service.distinguish;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.LgMachineprofile;
import com.k2data.platform.modules.lg.entity.LgMachineprofileVO;
import com.k2data.platform.modules.lg.entity.MissAndFalseCondition;
import com.k2data.platform.modules.lg.entity.distinguish.LgDistinguishmissandfalse;
import com.k2data.platform.modules.lg.entity.distinguish.LgDistinguishmissandfalsedetail;
import com.k2data.platform.modules.lg.entity.distinguish.LgDistinguishmissandfalserecord;
import com.k2data.platform.modules.lg.entity.slice.LgDeviceDateStatics;
import com.k2data.platform.modules.lg.dao.LgMachineprofileDao;
import com.k2data.platform.modules.lg.dao.distinguish.LgDistinguishmissandfalseDao;
import com.k2data.platform.modules.lg.dao.distinguish.LgDistinguishmissandfalsedetailDao;
import com.k2data.platform.modules.lg.dao.distinguish.LgDistinguishmissandfalserecordDao;
import com.k2data.platform.modules.lg.dao.slice.LgDeviceDateStaticsDao;

/**
 * 虚漏报方案配置Service
 * @author chenjingsi
 * @version 2016-06-29
 */
@Service
@Transactional(readOnly = true)
public class LgDistinguishmissandfalseService extends CrudService<LgDistinguishmissandfalseDao, LgDistinguishmissandfalse> {

	@Autowired
	LgDistinguishmissandfalseDao lgDistinguishmissandfalseDao;
	@Autowired
	LgMachineprofileDao lgMachineprofileDao;
	@Autowired
	LgDistinguishmissandfalsedetailDao lgDistinguishmissandfalsedetailDao;
	@Autowired
	LgDeviceDateStaticsDao lgDeviceDateStaticsDao;
	@Autowired
	LgDistinguishmissandfalserecordDao lgDistinguishmissandfalserecordDao;
	
	public LgDistinguishmissandfalse get(String id) {
		return super.get(id);
	}
	
	public List<LgDistinguishmissandfalse> findList(LgDistinguishmissandfalse lgDistinguishmissandfalse) {
		return super.findList(lgDistinguishmissandfalse);
	}
	
	public Page<LgDistinguishmissandfalse> findPage(Page<LgDistinguishmissandfalse> page, LgDistinguishmissandfalse lgDistinguishmissandfalse) {
		return super.findPage(page, lgDistinguishmissandfalse);
	}
	
	@Transactional(readOnly = false)
	public void save(LgDistinguishmissandfalse lgDistinguishmissandfalse) {
		super.save(lgDistinguishmissandfalse);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgDistinguishmissandfalse lgDistinguishmissandfalse) {
		super.delete(lgDistinguishmissandfalse);
	}

	public LgDistinguishmissandfalse checkSolutionName(LgDistinguishmissandfalse lgDistinguishmissandfalse) {
		return lgDistinguishmissandfalseDao.checkSolutionName(lgDistinguishmissandfalse);
	}
	
	@Transactional(readOnly = false)
	public void insertMissAndFalse() throws ParseException{
		Calendar   cal   =   Calendar.getInstance();
		cal.add(Calendar.DATE,   -1);
		//String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
		String yesterday = "2016-06-20";
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
		//虚报分析
		LgDistinguishmissandfalserecord recordSaled = new LgDistinguishmissandfalserecord();
		LgDistinguishmissandfalserecord recordSaling = new LgDistinguishmissandfalserecord();
		List<LgMachineprofileVO> machineNoSaled = lgMachineprofileDao.getMachineSaled();//获取已售的机器编号
		MissAndFalseCondition mfc1 = createCondition("1");
		mfc1.setYesterday(yesterday);
		for (LgMachineprofileVO profile:machineNoSaled) {
			String machineNo = profile.getCode();
			mfc1.setDeviceNo(machineNo);
			List<LgDeviceDateStatics> resultList = lgDeviceDateStaticsDao.getResultList(mfc1);
			if(resultList.size()>0){
				recordSaled.setId(UUID.randomUUID().toString());
				recordSaled.setSolutionType(1);
				recordSaled.setDeviceNo(machineNo);
				recordSaled.setMachineType(Integer.parseInt(profile.getMachineType()));
				recordSaled.setProductType(profile.getModelNumber());
				recordSaled.setOrderNo(profile.getOrderNumber());
				recordSaled.setSaleId(profile.getSaleUnitId());
				recordSaled.setSaleName(profile.getCustomerName());
				recordSaled.setConfirmDate(formatter.parse(yesterday));
				recordSaled.setConfirmState(1);
				lgDistinguishmissandfalserecordDao.insert(recordSaled);
			}else{
				continue;
			}
		}
		//漏报分析
		List<LgMachineprofileVO> machineNoSaling = lgMachineprofileDao.getMachineSaling();//获取代售的机器编号
		MissAndFalseCondition mfc2 = createCondition("2");
		mfc2.setYesterday(yesterday);
		for (LgMachineprofileVO profile:machineNoSaling) {
			String machineNo = profile.getCode();
			mfc2.setDeviceNo(machineNo);
			List<LgDeviceDateStatics> resultList = lgDeviceDateStaticsDao.getResultList(mfc2);
			if(resultList.size()>0){
				recordSaling.setId(UUID.randomUUID().toString());
				recordSaling.setSolutionType(2);
				recordSaling.setDeviceNo(machineNo);
				recordSaling.setMachineType(Integer.parseInt(profile.getMachineType()));
				recordSaling.setProductType(profile.getProductType());
				recordSaling.setOrderNo(profile.getOrderNumber());
				recordSaling.setSaleId(profile.getSaleUnitId());
				recordSaling.setSaleName(profile.getCustomerName());
				recordSaling.setConfirmDate(formatter.parse(yesterday));
				recordSaling.setConfirmState(1);
				lgDistinguishmissandfalserecordDao.insert(recordSaling);
			}else{
				continue;
			}
		}
	}
	
	public MissAndFalseCondition createCondition(String solutionType){//1虚报2漏报
		MissAndFalseCondition mfc = new MissAndFalseCondition();
		List<LgDistinguishmissandfalsedetail> detailList = lgDistinguishmissandfalsedetailDao.getDetailList(solutionType);
		for(LgDistinguishmissandfalsedetail lgDistinguishmissandfalsedetail : detailList){
			switch (Integer.parseInt(lgDistinguishmissandfalsedetail.getCondition())) {
			case 1:
				mfc.setRunDurationTotalOption(lgDistinguishmissandfalsedetail.getOption());
				mfc.setRunDurationTotal(lgDistinguishmissandfalsedetail.getValue1().toString());
				break;
			case 2:
				mfc.setRunOffTotalOption(lgDistinguishmissandfalsedetail.getOption());
				mfc.setRunOffTotal(lgDistinguishmissandfalsedetail.getValue1().toString());
				break;
			case 3:
				mfc.setOilSumOption(lgDistinguishmissandfalsedetail.getOption());
				mfc.setOilSum(lgDistinguishmissandfalsedetail.getValue1().toString());
				break;
			case 7:
				mfc.setAlarmCountOption(lgDistinguishmissandfalsedetail.getOption());
				mfc.setAlarmCount(lgDistinguishmissandfalsedetail.getValue1().toString());
				break;
			}
		}
		return mfc;
	}
	
	
	
}