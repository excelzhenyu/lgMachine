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

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.service.CrudService;
import com.k2data.platform.modules.lg.entity.LgGpsTemplateDetail;
import com.k2data.platform.modules.lg.entity.LgMachineevent;
import com.k2data.platform.modules.lg.entity.SensorChartJson;
import com.k2data.platform.modules.lg.entity.XYValueVO;
import com.k2data.platform.modules.lg.entity.HttpUrlVO.HttpUrlSelect1;
import com.k2data.platform.modules.lg.entity.HttpUrlVO.HttpUrlSelect2;
import com.k2data.platform.modules.lg.common.utils.OkhttpUtils;
import com.k2data.platform.modules.lg.dao.LgMachineeventDao;

/**
 * 机器大事记Service
 * @author chenjingsi
 * @version 2016-05-13
 */
@Service
@Transactional(readOnly = true)
public class LgMachineeventService extends CrudService<LgMachineeventDao, LgMachineevent> {

	@Autowired
	LgMachineeventDao lgMachineeventDao;
	
	public LgMachineevent get(String id) {
		return super.get(id);
	}
	
	public List<LgMachineevent> findList(LgMachineevent lgMachineevent) {
		return super.findList(lgMachineevent); 
	}
	
	public Page<LgMachineevent> findPage(Page<LgMachineevent> page, LgMachineevent lgMachineevent) {
		return super.findPage(page, lgMachineevent);
	}
	
	@Transactional(readOnly = false)
	public void save(LgMachineevent lgMachineevent) {
		super.save(lgMachineevent);
	}
	
	@Transactional(readOnly = false)
	public void delete(LgMachineevent lgMachineevent) {
		super.delete(lgMachineevent);
	}

	public List<String> getEventList(String deviceNo) {
		return lgMachineeventDao.getEventList(deviceNo);
	}

	public String genSensorListDom(List<LgGpsTemplateDetail> sensorList) {
		StringBuilder domStr = new StringBuilder();
		
		for (LgGpsTemplateDetail lg : sensorList) {
			if(lg.getSensorName().equals("GSM信号质量")){
				domStr.append("<li><input name='sensor' type='checkbox' checked='checked' value='")
				.append(lg.getSensorMark())
				.append("'/>")
				.append(lg.getSensorName())
				.append("</li>");
			}else{
				domStr.append("<li><input name='sensor' type='checkbox' value='")
				.append(lg.getSensorMark())
				.append("'/>")
				.append(lg.getSensorName())
				.append("</li>");
			}
		}
		domStr.append("<input id='btnSetOption' class='btn btn-primary' type='button' value='绘制'/>");
		return domStr.toString();
	}
	
	public String genSensorListDomBottom(List<LgGpsTemplateDetail> sensorList) {
		StringBuilder domStr = new StringBuilder();
		
		for (LgGpsTemplateDetail lg : sensorList) {
			if(lg.getSensorName().equals("运行监控") || lg.getSensorName().equals("速度")){
				domStr.append("<li><input name='sensorBottom' type='checkbox' checked='checked' value='")
				.append(lg.getSensorMark())
				.append("'/>")
				.append(lg.getSensorName())
				.append("</li>");
			}else{
				domStr.append("<li><input name='sensorBottom' type='checkbox' value='")
				.append(lg.getSensorMark())
				.append("'/>")
				.append(lg.getSensorName())
				.append("</li>");
			}
		}
		domStr.append("<input id='btnSetOptionBottom' class='btn btn-primary' type='button' value='绘制'/>");
		return domStr.toString();
	}

	public List<SensorChartJson> getChartJson(List<String> sensorMark, Date startDate, Date endDate) throws ParseException, IOException {
		List<SensorChartJson> jsonList = new ArrayList<SensorChartJson>();
		for(String mark : sensorMark){
			List<String> mark0 = new ArrayList<String>();
			mark0.add(mark);
			SensorChartJson sensorChartJson = new SensorChartJson();
			sensorChartJson.setSensorName(mark);
			String select = OkhttpUtils.genSelect("C301F4", mark0,startDate, endDate);
			String httpUrl = OkhttpUtils.joinURL(Global.getApiDataRowsURL(), select, null, null, "order={'defaultOrder':'desc'}", 1000, null);
			List<String> isoList = new ArrayList<String>();
			List<Double> valueList = new ArrayList<Double>();
			System.out.println(httpUrl);
				HttpUrlSelect1 jsonObject = OkhttpUtils.getRowsObject(httpUrl, HttpUrlSelect1.class);
				for(int i=jsonObject.getDataRows().size()-1;i>=0;i--){
					String formatDate = getDateFromISO(jsonObject.getDataRows().get(i).getIso());
					isoList.add(formatDate);
					valueList.add(Double.parseDouble(jsonObject.getDataRows().get(i).getDataPoints().get(0).getValue()));
				}
			sensorChartJson.setIsoList(isoList);
			sensorChartJson.setValueList(valueList);
			jsonList.add(sensorChartJson);
		}
			return jsonList;
	}
	
	public List<SensorChartJson> getChartJsonBottom(List<String> sensorMark, Date startDate, Date endDate) throws ParseException, IOException {
		List<SensorChartJson> jsonList = new ArrayList<SensorChartJson>();
		List<Double> xValueList = new ArrayList<Double>();
		List<Double> yValueList = new ArrayList<Double>();
		List<List<Double>> xyValue = new ArrayList<List<Double>>();
		SensorChartJson sensorChartJson = new SensorChartJson();
		for(int i=0;i<sensorMark.size();i++){
			List<String> mark0 = new ArrayList<String>();
			mark0.add(sensorMark.get(i));
			sensorChartJson.setSensorName(sensorMark.get(i));
			String select = OkhttpUtils.genSelect("C301F4", mark0,startDate, endDate);
			String httpUrl = OkhttpUtils.joinURL(Global.getApiDataRowsURL(), select, null, null, "order={'defaultOrder':'desc'}", 1000, null);
			HttpUrlSelect1 jsonObject = OkhttpUtils.getRowsObject(httpUrl, HttpUrlSelect1.class);
			if(i==0){
				for(int j=jsonObject.getDataRows().size()-1;j>=0;j--){
					xValueList.add(Double.parseDouble(jsonObject.getDataRows().get(j).getDataPoints().get(0).getValue()));
			}
			}
			if(i==1){
				for(int j=jsonObject.getDataRows().size()-1;j>=0;j--){
					yValueList.add(Double.parseDouble(jsonObject.getDataRows().get(j).getDataPoints().get(0).getValue()));
			}
			}
		}
		for(int k=0;k<xValueList.size();k++){
			List<Double> xyValueList = new ArrayList<Double>();
			xyValueList.add(xValueList.get(k));
			xyValueList.add(yValueList.get(k));
			xyValue.add(xyValueList);
		}
		sensorChartJson.setXyValueList(xyValue);
		jsonList.add(sensorChartJson);		
			return jsonList;
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