/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.LgDeviceSliceStatics;
import com.k2data.platform.modules.lg.entity.LgGpsTemplateDetail;
import com.k2data.platform.modules.lg.entity.LgSliceStaticsAnalog;
import com.k2data.platform.modules.lg.entity.LgSliceStaticsSwitch;
import com.k2data.platform.modules.lg.entity.HttpUrlVO.HttpUrlSelect1;
import com.k2data.platform.modules.lg.common.utils.MathUtils;
import com.k2data.platform.modules.lg.common.utils.OkhttpUtils;
import com.k2data.platform.modules.lg.dao.LgDeviceSliceStaticsDao;
import com.k2data.platform.modules.lg.dao.LgGpsTemplateDetailDao;
import com.k2data.platform.modules.lg.dao.LgSliceStaticsAnalogDao;
import com.k2data.platform.modules.lg.dao.LgSliceStaticsSwitchDao;

/**
 * 开关量切片统计Service
 * @author chenjingsi
 * @version 2016-05-20
 */
@Service
@Transactional(readOnly = false)
public class LgSliceStaticsSwitchService extends CrudService<LgSliceStaticsSwitchDao, LgSliceStaticsSwitch> {

	@Autowired
	private LgDeviceSliceStaticsDao lgDeviceSliceStaticsDao;
	@Autowired
	private LgGpsTemplateDetailDao lgGpsTemplateDetailDao;
	
	private static final String HTTP_URL_DATAROWS = "http://192.168.130.62:8089/data-service/v3/data-rows";
	
	private static final long  nd = 1000 * 24 * 60 * 60;
	private static final long  nh = 1000 * 60 * 60;
	private static final long  nm = 1000 * 60;
	
	public LgSliceStaticsSwitch get(String id) {
		return super.get(id);
	}
	
	public List<LgSliceStaticsSwitch> findList(LgSliceStaticsSwitch lgSliceStaticsSwitch) {
		return super.findList(lgSliceStaticsSwitch);
	}
	
	public Page<LgSliceStaticsSwitch> findPage(Page<LgSliceStaticsSwitch> page, LgSliceStaticsSwitch lgSliceStaticsSwitch) {
		return super.findPage(page, lgSliceStaticsSwitch);
	}
	
	@Transactional(readOnly = false)
	public void save(LgSliceStaticsSwitch lgSliceStaticsSwitch) {
		super.save(lgSliceStaticsSwitch);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgSliceStaticsSwitch lgSliceStaticsSwitch) {
		super.delete(lgSliceStaticsSwitch);
	}
	
	public void addSliceStaticsSwitch() throws ParseException{
		List<String> deviceNo = getDeviceAll();
		Map<String,Object> params = new HashMap<String,Object>();
		String startTime = "2016-01-01 00:00:01";
		Date sliceStart = stringToDate(startTime);
		String endTime = "2016-07-28 23:59:59";
		Date sliceStop = stringToDate(endTime);
		for(int i=0;i<deviceNo.size();i++){
			String device = deviceNo.get(i);
			params.put("deviceNo", device);
			params.put("startTime", sliceStart);
			params.put("endTime", sliceStop);
			List<LgDeviceSliceStatics> sliceStatics = lgDeviceSliceStaticsDao.getSliceInfo(params);
			List<LgGpsTemplateDetail> sensorList =lgGpsTemplateDetailDao.getSensorListSwitch(device);	
			for (int j = 0; j < sliceStatics.size(); j++) {
				LgDeviceSliceStatics deviceSliceStatics = sliceStatics.get(j);
				System.out.println(1);
				getSwitchValueList(deviceSliceStatics, sensorList);
			}

		}
	}
	
	public List<String> getDeviceAll(){
		//List<String> deviceNo = lgMachineprofileDao.getDeviceNoList();
		List<String> deviceNo = new ArrayList<String>();
		deviceNo.add("C301F4");
		deviceNo.add("C301F8");
		deviceNo.add("C302AE");
		deviceNo.add("C302D0");
		deviceNo.add("C3020D");
		deviceNo.add("C3022D");
		deviceNo.add("C30266");
		deviceNo.add("C3027E");
		deviceNo.add("C30229");
		deviceNo.add("C30213");
		return deviceNo;
	}
	
	public void getSwitchValueList(LgDeviceSliceStatics lgDeviceSliceStatics,List<LgGpsTemplateDetail> sensorList){
		List<Double> valueList = new ArrayList<Double>();
		for(LgGpsTemplateDetail lgGpsTemplateDetail : sensorList){
			String sensorMark = lgGpsTemplateDetail.getSensorMark();
			List<String> mark0 = new ArrayList<String>();
			mark0.add(sensorMark);
			String select = OkhttpUtils.genSelect(lgDeviceSliceStatics.getDeviceNo(), mark0,lgDeviceSliceStatics.getSliceStart() , lgDeviceSliceStatics.getSliceStop());
			String httpUrl = OkhttpUtils.joinURL(HTTP_URL_DATAROWS, select, null, null, null, 1000, null);
			HttpUrlSelect1 jsonObject = OkhttpUtils.getRowsObject(httpUrl, HttpUrlSelect1.class);
			for (int i = 0; i < jsonObject.getDataRows().size(); i++) {
				valueList.add(Double.parseDouble(jsonObject.getDataRows().get(i).getDataPoints().get(0).getValue()));
			}
			insertSwitchValue(lgDeviceSliceStatics,sensorMark,valueList);
		}
	}
	
	
	
	@Transactional(readOnly = false)
	public void insertSwitchValue(LgDeviceSliceStatics lgDeviceSliceStatics,String sensorMark,List<Double> valueList){
		Date sliceStart = lgDeviceSliceStatics.getSliceStart();
		Date sliceEnd = lgDeviceSliceStatics.getSliceStop();
		Integer runDuration = (int) ((sliceEnd.getTime()-sliceStart.getTime())% nd % nh / nm);
		LgSliceStaticsSwitch lgSliceStaticsSwitch = new LgSliceStaticsSwitch();
		
		lgSliceStaticsSwitch.setInsertTime(new Date());
		lgSliceStaticsSwitch.setDeviceSliceId(lgDeviceSliceStatics.getId());
		lgSliceStaticsSwitch.setDeviceNo(lgDeviceSliceStatics.getDeviceNo());
		lgSliceStaticsSwitch.setSensorType("2");
		lgSliceStaticsSwitch.setDataPointCount(String.valueOf(valueList.size()));
		lgSliceStaticsSwitch.setSensorDataStart(sliceStart);
		lgSliceStaticsSwitch.setSensorDataEnd(sliceEnd);
		lgSliceStaticsSwitch.setSensorDataDuration(String.valueOf(runDuration));
		lgSliceStaticsSwitch.setSensorMark(sensorMark);
		if(runDuration!=0){
		lgSliceStaticsSwitch.setSendFrequence((double)(valueList.size()/runDuration));
		}
		Map<String,Object> resultMap =  MathUtils.analysisStatusDataAndStatusSwitchData(valueList);
		Map<String,List<Double>> statusMap = (Map<String, List<Double>>) resultMap.get("statusMap");
		Map<String,Double> switchMap = (Map<String, Double>) resultMap.get("switchMap");
		List<Double> lowCountList = statusMap.get("1.0");
		List<Double> highCountList = statusMap.get("10.0");
		List<Double> lowDurationList = new ArrayList<Double>();
		List<Double> highDurationList = new ArrayList<Double>();
		double lowToHigh = 0.0;
		double highToLow = 0.0;
		if(switchMap.get("1.0->10.0")!=null){
		lowToHigh = switchMap.get("1.0->10.0");
		}else{
		lowToHigh = 0.0;
		}
		if(switchMap.get("10.0->1.0")!=null){
		highToLow = switchMap.get("10.0->1.0");
		}else{
		highToLow = 0.0;
		}
		Double highCount = 0.0;
		Double lowCount = 0.0;
		for (int i = 0; i < lowCountList.size(); i++) {
			lowDurationList.add(lowCountList.get(i)*3);
			lowCount+=lowCountList.get(i);
		}
		for (int i = 0; i < highCountList.size(); i++) {
			highDurationList.add(highCountList.get(i)*3);
			highCount+=highCountList.get(i);
		}
		lgSliceStaticsSwitch.setChangeCount(lowToHigh+highToLow);
		Map<String, Object> highDurationReturnMap = MathUtils.computeVariance(highDurationList, 1);//获取具体需要的值
		lgSliceStaticsSwitch.setHighCount(highCount);
		lgSliceStaticsSwitch.setHighDurationMax((Double)highDurationReturnMap.get("maxValue"));
		lgSliceStaticsSwitch.setHighDurationMin((Double)highDurationReturnMap.get("minValue"));
		lgSliceStaticsSwitch.setHighDurationAvg((Double)highDurationReturnMap.get("average"));
		lgSliceStaticsSwitch.setHighDuration5(MathUtils.computePercent(highDurationList, 5, 1));
		lgSliceStaticsSwitch.setHighDuration25(MathUtils.computePercent(highDurationList, 25, 1));
		lgSliceStaticsSwitch.setHighDuration50(MathUtils.computePercent(highDurationList, 50, 1));
		lgSliceStaticsSwitch.setHighDuration75(MathUtils.computePercent(highDurationList, 75, 1));
		lgSliceStaticsSwitch.setHighDuration95(MathUtils.computePercent(highDurationList, 95, 1));
		lgSliceStaticsSwitch.setHighDurationStandardStd((Double)highDurationReturnMap.get("stdVariance"));
		lgSliceStaticsSwitch.setHighDurationVarianceStd((Double)highDurationReturnMap.get("variance"));
		
		Map<String, Object> highCountReturnMap = MathUtils.computeVariance(highCountList, 1);//获取具体需要的值
		lgSliceStaticsSwitch.setHighMax((Double)highCountReturnMap.get("maxValue"));
		lgSliceStaticsSwitch.setHighMin((Double)highCountReturnMap.get("minValue"));
		lgSliceStaticsSwitch.setHighAvg((Double)highCountReturnMap.get("average"));
		lgSliceStaticsSwitch.setHigh5(MathUtils.computePercent(highCountList, 5, 1));
		lgSliceStaticsSwitch.setHigh25(MathUtils.computePercent(highCountList, 25, 1));
		lgSliceStaticsSwitch.setHigh50(MathUtils.computePercent(highCountList, 50, 1));
		lgSliceStaticsSwitch.setHigh75(MathUtils.computePercent(highCountList, 75, 1));
		lgSliceStaticsSwitch.setHigh95(MathUtils.computePercent(highCountList, 95, 1));
		lgSliceStaticsSwitch.setHighStandardStd((Double)highCountReturnMap.get("stdVariance"));
		lgSliceStaticsSwitch.setHighVarianceStd((Double)highCountReturnMap.get("variance"));
		
		Map<String,Object> lowDurationReturnMap = MathUtils.computeVariance(lowDurationList,1);
		lgSliceStaticsSwitch.setLowCount(lowCount);
		lgSliceStaticsSwitch.setLowDurationMax((Double)lowDurationReturnMap.get("maxValue"));
		lgSliceStaticsSwitch.setLowDurationMin((Double)lowDurationReturnMap.get("minValue"));
		lgSliceStaticsSwitch.setLowDurationAvg((Double)lowDurationReturnMap.get("average"));
		lgSliceStaticsSwitch.setLowDuration5(MathUtils.computePercent(lowDurationList, 5, 1));
		lgSliceStaticsSwitch.setLowDuration25(MathUtils.computePercent(lowDurationList, 25, 1));
		lgSliceStaticsSwitch.setLowDuration50(MathUtils.computePercent(lowDurationList, 50, 1));
		lgSliceStaticsSwitch.setLowDuration75(MathUtils.computePercent(lowDurationList, 75, 1));
		lgSliceStaticsSwitch.setLowDuration95(MathUtils.computePercent(lowDurationList, 95, 1));
		lgSliceStaticsSwitch.setLowDurationStandardStd((Double)lowDurationReturnMap.get("stdVariance"));
		lgSliceStaticsSwitch.setLowDurationVarianceStd((Double)lowDurationReturnMap.get("variance"));
		
		Map<String,Object> lowCountReturnMap = MathUtils.computeVariance(lowCountList,1);
		lgSliceStaticsSwitch.setLowMax((Double)lowCountReturnMap.get("maxValue"));
		lgSliceStaticsSwitch.setLowMin((Double)lowCountReturnMap.get("minValue"));
		lgSliceStaticsSwitch.setLowAvg((Double)lowCountReturnMap.get("average"));
		lgSliceStaticsSwitch.setLow5(MathUtils.computePercent(lowCountList, 5, 1));
		lgSliceStaticsSwitch.setLow25(MathUtils.computePercent(lowCountList, 25, 1));
		lgSliceStaticsSwitch.setLow50(MathUtils.computePercent(lowCountList, 50, 1));
		lgSliceStaticsSwitch.setLow75(MathUtils.computePercent(lowCountList, 75, 1));
		lgSliceStaticsSwitch.setLow95(MathUtils.computePercent(lowCountList, 95, 1));
		lgSliceStaticsSwitch.setLowStandardStd((Double)lowCountReturnMap.get("stdVariance"));
		lgSliceStaticsSwitch.setLowVarianceStd((Double)lowCountReturnMap.get("variance"));
//		Double changeCountLow = resultMap.get(MathUtils.SWITCH_MAP.get)
//		lgSliceStaticsSwitch.setChangeCount(resultMap.get(Mathut));
//		
		//存入数据库
		 super.save(lgSliceStaticsSwitch);
	}
	
	private Date stringToDate(String str) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(str);
		return date;
	}
	
		
	
	public static String getDateFromISO(String isoDate){  
		        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");  
		        DateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		        try {  
		            return sd.format(sdf.parse(isoDate));  
		        } catch (ParseException e) {  
		            e.printStackTrace();  
		            return null;  
		        }  
		    }  
	
	public static void main(String[] args) {
		List<String> dataList = new ArrayList<String>();
		dataList.add("01");
		dataList.add("01");
		dataList.add("01");
		dataList.add("10");
		dataList.add("10");
		dataList.add("01");
		dataList.add("01");
		dataList.add("01");
		dataList.add("01");
		dataList.add("10");
		dataList.add("10");
		dataList.add("10");
		dataList.add("01");
		dataList.add("01");
		dataList.add("01");
		dataList.add("01");
		dataList.add("01");
		dataList.add("10");
		dataList.add("10");
		dataList.add("10");
		dataList.add("10");
		dataList.add("10");
		dataList.add("01");
		dataList.add("01");
		dataList.add("01");
		dataList.add("01");
		dataList.add("10");
		dataList.add("10");
		Map<String,Object> map = MathUtils.analysisStatusDataAndStatusSwitchData(dataList);
		System.out.println(map);
	}
}