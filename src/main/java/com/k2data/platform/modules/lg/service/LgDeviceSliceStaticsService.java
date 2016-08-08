/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.service;

import java.io.IOException;
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

import com.alibaba.fastjson.JSON;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.common.utils.HttpUtil;
import com.k2data.platform.modules.lg.entity.LgDeviceSliceStatics;
import com.k2data.platform.modules.lg.entity.LgGpsTemplateDetail;
import com.k2data.platform.modules.lg.entity.LgSliceStaticsAnalog;
import com.k2data.platform.modules.lg.entity.SliceDataVO;
import com.k2data.platform.modules.lg.entity.HttpUrlVO.HttpUrlDeviceNo1;
import com.k2data.platform.modules.lg.entity.HttpUrlVO.HttpUrlDeviceNo2;
import com.k2data.platform.modules.lg.entity.HttpUrlVO.HttpUrlSelect1;
import com.k2data.platform.modules.lg.entity.HttpUrlVO.HttpUrlSelect2;
import com.k2data.platform.modules.lg.common.utils.MathUtils;
import com.k2data.platform.modules.lg.common.utils.OkhttpUtils;
import com.k2data.platform.modules.lg.dao.LgDeviceSliceStaticsDao;
import com.k2data.platform.modules.lg.dao.LgGpsTemplateDetailDao;
import com.k2data.platform.modules.lg.dao.LgSliceStaticsAnalogDao;

/**
 * 设备切片统计Service
 * @author chenjingsi
 * @version 2016-05-20
 */
@Service
@Transactional(readOnly = false)
public class LgDeviceSliceStaticsService extends CrudService<LgDeviceSliceStaticsDao, LgDeviceSliceStatics> {
	
	private static final String HTTP_URL_DEVICENOLIST="http://192.168.130.2/cloud/qa3/kmx/v2/devices";
	
	private static final String HTTP_URL_DATAROWS = "http://192.168.130.62:8089/data-service/v3/data-rows";
	
	private static final long  nd = 1000 * 24 * 60 * 60;
	private static final long  nh = 1000 * 60 * 60;
	private static final long  nm = 1000 * 60;
	
	@Autowired
	private LgDeviceSliceStaticsDao lgDeviceSliceStaticsDao;
	@Autowired
	private LgSliceStaticsAnalogDao lgSliceStaticsAnalogDao;
	@Autowired
	private LgGpsTemplateDetailDao lgGpsTemplateDetailDao;
	@Autowired
	private LgSliceStaticsAnalogService lgSliceStaticsAnalogService;
	
	public LgDeviceSliceStatics get(String id) {
		return super.get(id);
	}
	
	public List<LgDeviceSliceStatics> findList(LgDeviceSliceStatics lgDeviceSliceStatics) {
		return super.findList(lgDeviceSliceStatics);
	}
	
	public Page<LgDeviceSliceStatics> findPage(Page<LgDeviceSliceStatics> page, LgDeviceSliceStatics lgDeviceSliceStatics) {
		return super.findPage(page, lgDeviceSliceStatics);
	}
	
	@Transactional(readOnly = false)
	public void save(LgDeviceSliceStatics lgDeviceSliceStatics) {
		super.save(lgDeviceSliceStatics);
	}
	
	/**
	 * 批量插入切片数据
	 * 
	 * @param list
	 */
	@Transactional(readOnly = false)
	public void batchSave(List<LgDeviceSliceStatics> list) {
	    lgDeviceSliceStaticsDao.batchSave(list);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgDeviceSliceStatics lgDeviceSliceStatics) {
		super.delete(lgDeviceSliceStatics);
	}
	/**
	 * 更新前期结束日期
	 *
	 * @param lgDeviceSliceStatics
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updatePrevDay(LgDeviceSliceStatics lgDeviceSliceStatics) {
		return lgDeviceSliceStaticsDao.updatePrevDay(lgDeviceSliceStatics);
	}

	@Transactional(readOnly = false)
	public void addDeviceSliceStatics(){
		 long nd = 1000 * 24 * 60 * 60;
		 long nh = 1000 * 60 * 60;
		 long nm = 1000 * 60;
		LgDeviceSliceStatics lgDeviceSliceStatics = new LgDeviceSliceStatics();
		Map<String,Object> params = new HashMap<String,Object>();
		String deviceNum = "C200B";//设备号，后期为参数
		String latestStartTime = "2015-01-01 00:00:00";//为判断第一次切片起始时间而选择的一个靠前的时间
		String latestEndTime = "";
		params.put("deviceNum", deviceNum);
		params.put("latestStartTime", latestStartTime);
		Date sliceStart = lgDeviceSliceStaticsDao.getSliceStart(params);  //获取本次切片的开始时间
		params.put("latestStartTime", sliceStart);
		Date sliceEnd = lgDeviceSliceStaticsDao.getSliceEnd(params); //获取本次切片的结束时间
		latestStartTime = sliceStart.toString();	//将最近的一次开始时间赋值为此次切片开始时间
		latestEndTime= sliceEnd.toString();//将最近的一次结束时间赋值为此次切片结束时间
		int count = lgDeviceSliceStaticsDao.getCount(deviceNum);
		Integer runDuration = (int) ((sliceEnd.getTime()-sliceStart.getTime())% nd % nh / nm);
		//取最后一条数据值
		SliceDataVO sliceData = new SliceDataVO();
		params.put("sliceStart", sliceStart);
		params.put("sliceEnd", sliceEnd);
		sliceData = lgDeviceSliceStaticsDao.getLatestSliceData(params);
		//赋值存储
		lgDeviceSliceStatics.setSliceStart(sliceStart);
		lgDeviceSliceStatics.setSliceStop(sliceEnd);
		lgDeviceSliceStatics.setDeviceNo(deviceNum);
		lgDeviceSliceStatics.setInsertTime(new Date());
		lgDeviceSliceStatics.setSliceCount(count+1);
		lgDeviceSliceStatics.setSliceRunDuration(runDuration);
		lgDeviceSliceStatics.setLatitudes(Double.parseDouble(sliceData.getLatitudes()));
		lgDeviceSliceStatics.setLongitude(Double.parseDouble(sliceData.getLongitude()));
		lgDeviceSliceStatics.setProvince(sliceData.getProvince());
		lgDeviceSliceStatics.setCity(sliceData.getCity());
		lgDeviceSliceStatics.setAddress(sliceData.getAddress());
		super.save(lgDeviceSliceStatics);	
	}
	
	public void SliceEngineTemperature(Date sliceStart,Date sliceEnd,String deviceNum,Integer runDuration ){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sliceStart", sliceStart);
		params.put("sliceEnd", sliceEnd);
		params.put("deviceNum", deviceNum);
		List<Double> tmp = lgDeviceSliceStaticsDao.getEngineTemperature(params);//获取切片内所有的引擎温度数据
		LgSliceStaticsAnalog lgSliceStaticsAnalog = new LgSliceStaticsAnalog();
		lgSliceStaticsAnalog.setInsertTime(new Date());//写入时间
		lgSliceStaticsAnalog.setDeviceNo(deviceNum);//设备号
		lgSliceStaticsAnalog.setSensorType(1);//1：模拟量2：开关量3：数字量
		lgSliceStaticsAnalog.setDataPointCount(tmp.size());//回传次数为查询结果的长度
		lgSliceStaticsAnalog.setSensorDataStart(sliceStart);
		lgSliceStaticsAnalog.setSensorDataEnd(sliceEnd);
		lgSliceStaticsAnalog.setSensorDataDuration(runDuration);
		lgSliceStaticsAnalog.setSendFrequence((double)(tmp.size()/runDuration));
		Map<String, Object> returnMap = MathUtils.computeVariance(tmp, 1);//获取具体需要的值
		//值处理
		lgSliceStaticsAnalog.setValueMax((Double)returnMap.get("maxValue"));
		lgSliceStaticsAnalog.setValueMin((Double)returnMap.get("minValue"));
		lgSliceStaticsAnalog.setValueAvg((Double)returnMap.get("average"));
		lgSliceStaticsAnalog.setValue5(MathUtils.computePercent(tmp, 5, 1));
		lgSliceStaticsAnalog.setValue25(MathUtils.computePercent(tmp, 25, 1));
		lgSliceStaticsAnalog.setValue50(MathUtils.computePercent(tmp, 50, 1));
		lgSliceStaticsAnalog.setValue75(MathUtils.computePercent(tmp, 75, 1));
		lgSliceStaticsAnalog.setValue95(MathUtils.computePercent(tmp, 95, 1));
		lgSliceStaticsAnalog.setStandardStd((Double)returnMap.get("stdVariance"));
		lgSliceStaticsAnalog.setVarianceStd((Double)returnMap.get("variance"));
		//梯度处理
		List<Double> gradList = MathUtils.getGradient(tmp);//获取梯度值列表
		Map<String,Object> gradMap = MathUtils.computeVariance(gradList, 1);//获取梯度具体需要的值
		lgSliceStaticsAnalog.setGradMax((Double)gradMap.get("maxValue"));
		lgSliceStaticsAnalog.setGradMin((Double)gradMap.get("minValue"));
		lgSliceStaticsAnalog.setGradAvg((Double)gradMap.get("average"));
		lgSliceStaticsAnalog.setGrad5(MathUtils.computePercent(gradList, 5, 1));
		lgSliceStaticsAnalog.setGrad25(MathUtils.computePercent(gradList, 25, 1));
		lgSliceStaticsAnalog.setGrad50(MathUtils.computePercent(gradList, 50, 1));
		lgSliceStaticsAnalog.setGrad75(MathUtils.computePercent(gradList, 75, 1));
		lgSliceStaticsAnalog.setGrad95(MathUtils.computePercent(gradList, 95, 1));
		
		//存入数据库
		int ret = lgSliceStaticsAnalogDao.insert(lgSliceStaticsAnalog);
		if(ret<1){
			System.out.println("数据库插入失败");
		}
	}
	
	public List<String> getDeviceNoList() throws IOException{
		List<String> deviceNoList = new ArrayList<String>();
		String jsonResult = HttpUtil.get(HTTP_URL_DEVICENOLIST);
		HttpUrlDeviceNo1 deviceResult = JSON.parseObject(jsonResult, HttpUrlDeviceNo1.class);
		for(HttpUrlDeviceNo2 deviceNo2 : deviceResult.getData()){
			deviceNoList.add(deviceNo2.getId());
		}
		return deviceNoList;
	}
	
	public void getAccStatus(String startTime,String finishTime) throws IOException{
		String endTime = "";
		String startTimeJson = getHttpJsonForSlice(startTime, finishTime, "1");
		HttpUrlSelect1 startTimeResult = JSON.parseObject(startTimeJson, HttpUrlSelect1.class);
		for(HttpUrlSelect2 select2 : startTimeResult.getDataRows()){
				startTime=select2.getIso().replace("+", "%2B");//切片开始时间
				break;
		}
		String endTimeJson = getHttpJsonForSlice(startTime, finishTime, "10");
		HttpUrlSelect1 endTimeResult = JSON.parseObject(endTimeJson, HttpUrlSelect1.class);
		for(HttpUrlSelect2 select2 : endTimeResult.getDataRows()){
				endTime=select2.getIso().replace("+", "%2B");//切片结束时间
				break;
		}	
		String nextJson = getHttpJsonForSlice(endTime, finishTime, "1");
		HttpUrlSelect1 finishTimeResult = JSON.parseObject(nextJson, HttpUrlSelect1.class);
		System.out.println(startTime+"--------------------------"+endTime);
		//if(finishTimeResult.getDataRowsCount()==null||finishTimeResult.getDataRowsCount().equals("0")){
	//		System.out.println("----------------------------------------------slice finish------------------------------------------------");
	//	}else{
	//		this.getAccStatus(endTime, finishTime);
	//	}
	}
	
	public void addSliceStaticsAnalog() throws ParseException{
		List<String> deviceNo = getDeviceAll();
		Map<String,Object> params = new HashMap<String,Object>();
		String startTime = "2016-01-01 00:00:01";
		Date sliceStart = stringToDate(startTime);
		String endTime = "2016-07-30 23:59:59";
		Date sliceStop = stringToDate(endTime);
		for(int i=0;i<deviceNo.size();i++){
			String device = deviceNo.get(i);
			params.put("deviceNo", device);
			params.put("startTime", sliceStart);
			params.put("endTime", sliceStop);
			List<LgDeviceSliceStatics> sliceStatics = lgDeviceSliceStaticsDao.getSliceInfo(params);
			List<LgGpsTemplateDetail> sensorList =lgGpsTemplateDetailDao.getSensorListAnalog(device);	
			for (int j = 0; j < sliceStatics.size(); j++) {
				LgDeviceSliceStatics deviceSliceStatics = sliceStatics.get(j);
				getAnalogValueList(deviceSliceStatics, sensorList);
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
	
	public void getAnalogValueList(LgDeviceSliceStatics lgDeviceSliceStatics,List<LgGpsTemplateDetail> sensorList){
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
			insertAnalogValue(lgDeviceSliceStatics,sensorMark,valueList);
		}
	}
	
	
	
	@Transactional(readOnly = false)
	public void insertAnalogValue(LgDeviceSliceStatics lgDeviceSliceStatics,String sensorMark,List<Double> valueList){
		Date sliceStart = lgDeviceSliceStatics.getSliceStart();
		Date sliceEnd = lgDeviceSliceStatics.getSliceStop();
		Integer runDuration = (int) ((sliceEnd.getTime()-sliceStart.getTime())% nd % nh / nm);
		LgSliceStaticsAnalog lgSliceStaticsAnalog = new LgSliceStaticsAnalog();
		lgSliceStaticsAnalog.setDeviceSliceId(lgDeviceSliceStatics.getId());
		lgSliceStaticsAnalog.setInsertTime(new Date());//写入时间
		lgSliceStaticsAnalog.setDeviceNo(lgDeviceSliceStatics.getDeviceNo());//设备号
		lgSliceStaticsAnalog.setSensorType(1);//1：模拟量2：开关量3：数字量
		lgSliceStaticsAnalog.setSensorNo(sensorMark);
		lgSliceStaticsAnalog.setDataPointCount(valueList.size());//回传次数为查询结果的长度
		lgSliceStaticsAnalog.setSensorDataStart(sliceStart);
		lgSliceStaticsAnalog.setSensorDataEnd(sliceEnd);
		lgSliceStaticsAnalog.setSensorDataDuration(runDuration);
		if(runDuration!=0){
		lgSliceStaticsAnalog.setSendFrequence((double)(valueList.size()/runDuration));
		}
		Map<String, Object> returnMap = MathUtils.computeVariance(valueList, 1);//获取具体需要的值
		//值处理
		lgSliceStaticsAnalog.setValueMax((Double)returnMap.get("maxValue"));
		lgSliceStaticsAnalog.setValueMin((Double)returnMap.get("minValue"));
		lgSliceStaticsAnalog.setValueAvg((Double)returnMap.get("average"));
		lgSliceStaticsAnalog.setValue5(MathUtils.computePercent(valueList, 5, 1));
		lgSliceStaticsAnalog.setValue25(MathUtils.computePercent(valueList, 25, 1));
		lgSliceStaticsAnalog.setValue50(MathUtils.computePercent(valueList, 50, 1));
		lgSliceStaticsAnalog.setValue75(MathUtils.computePercent(valueList, 75, 1));
		lgSliceStaticsAnalog.setValue95(MathUtils.computePercent(valueList, 95, 1));
		lgSliceStaticsAnalog.setStandardStd((Double)returnMap.get("stdVariance"));
		lgSliceStaticsAnalog.setVarianceStd((Double)returnMap.get("variance"));
		//梯度处理
		List<Double> gradList = MathUtils.getGradient(valueList);//获取梯度值列表
		Map<String,Object> gradMap = MathUtils.computeVariance(gradList, 1);//获取梯度具体需要的值
		lgSliceStaticsAnalog.setGradMax((Double)gradMap.get("maxValue"));
		lgSliceStaticsAnalog.setGradMin((Double)gradMap.get("minValue"));
		lgSliceStaticsAnalog.setGradAvg((Double)gradMap.get("average"));
		lgSliceStaticsAnalog.setGrad5(MathUtils.computePercent(gradList, 5, 1));
		lgSliceStaticsAnalog.setGrad25(MathUtils.computePercent(gradList, 25, 1));
		lgSliceStaticsAnalog.setGrad50(MathUtils.computePercent(gradList, 50, 1));
		lgSliceStaticsAnalog.setGrad75(MathUtils.computePercent(gradList, 75, 1));
		lgSliceStaticsAnalog.setGrad95(MathUtils.computePercent(gradList, 95, 1));
		
		//存入数据库
		 lgSliceStaticsAnalogService.save(lgSliceStaticsAnalog);
	}
	
	public String getHttpJsonForSlice(String startTime,String endTime,String accStatus) throws IOException{
		String url="http://192.168.130.2/cloud/qa3/kmx/v2/data/data-rows?select={%22sources%22:{%22device%22:%22C2000B%22,%22sensor%22:[%22acc_status%22],%22timeRange%22:{%22start%22:{%22iso%22:%22"+startTime+"%22},%22end%22:{%22iso%22:%22"+endTime+"%22}}}}&valueFilter={%22defaultFilter%22:{%22$eq%22:"+accStatus+"}}";
		String json = HttpUtil.get(url);
		return json;
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
	
}