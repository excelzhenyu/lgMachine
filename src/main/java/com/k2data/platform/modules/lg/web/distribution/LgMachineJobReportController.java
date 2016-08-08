/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.distribution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.utils.DateUtils;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.modules.lg.common.entity.LgDeviceMapLine;
import com.k2data.platform.modules.lg.common.entity.LgDeviceMapPoint;
import com.k2data.platform.modules.lg.common.entity.LgMapLine;
import com.k2data.platform.modules.lg.common.entity.LgMapPoint;
import com.k2data.platform.modules.lg.common.utils.MathUtils;
import com.k2data.platform.modules.lg.dao.LgMachineprofileDao;
import com.k2data.platform.modules.lg.entity.LgMachineprofile;
import com.k2data.platform.modules.lg.entity.distribution.LgMachineJobReportData;
import com.k2data.platform.modules.lg.entity.slice.LgSliceTempDataSection;
import com.k2data.platform.modules.lg.service.distribution.LgMachineJobReportService;
import com.k2data.platform.modules.lg.service.slice.LgSliceTempDataService;

/**
 * 机器工作报告Controller
 * @author wangshengguo
 * @version 2016-07-13
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/distribution/lgMachineJobReport")
public class LgMachineJobReportController extends BaseController {

	@Autowired
	private LgMachineprofileDao lgMachineprofileDao;
	@Autowired
	private LgMachineJobReportService lgMachineJobReportService;
	@Autowired
	private LgSliceTempDataService lgSliceTempDataService;
	
	@RequestMapping(value = "jobReport")
	public String jobReport( HttpServletRequest request, Model model) {
		return "modules/lg/distribution/lgMachineJobReport"; 
	}
	
	
	@RequestMapping(value = "jobReportLine")
	public String jobReportLine( HttpServletRequest request, Model model) {
		model.addAttribute("deviceNo", request.getParameter("deviceNo"));
		model.addAttribute("workDate", request.getParameter("workDate"));
		return "modules/lg/distribution/lgMachineJobReportLine"; 
	}
	
	@RequestMapping(value = "getMachineCodeList")
	public @ResponseBody List<String> getMachineCodeList(HttpServletRequest request) {
		LgMachineprofile lgMachineprofile= new LgMachineprofile();
		lgMachineprofile.setMachineType(request.getParameter("machineType"));
		lgMachineprofile.setModelNumber(request.getParameter("modelNumber"));
		lgMachineprofile.setOrderNumber(request.getParameter("orderNumber"));
		
		return lgMachineprofileDao.getMachineCodeListByCondition(lgMachineprofile);
	}
	
	@RequestMapping(value = "getMachineJobReport")
	public @ResponseBody Page<LgMachineJobReportData> getMachineJobReport(LgMachineJobReportData lgMachineJobReportData, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgMachineJobReportData> page = lgMachineJobReportService.findPage(new Page<LgMachineJobReportData>(request, response), lgMachineJobReportData); 
		return page;
	}
	
	@RequestMapping(value = "getMachineJobReportPoint")
	public @ResponseBody List<LgDeviceMapPoint> getPoint(LgMachineJobReportData lgMachineJobReportData, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<LgDeviceMapPoint> devicePointList = new ArrayList<LgDeviceMapPoint>();

		List<LgMachineJobReportData> devicelist = lgMachineJobReportService.findList(lgMachineJobReportData);
		Map<String, List<LgMapPoint>> deviceTmpMap = new HashMap<String, List<LgMapPoint>>();
		for(LgMachineJobReportData ld : devicelist) {
			LgMapPoint pointMap = new LgMapPoint();
			//pointMap.setName("试验日期："+ DateUtils.formatDate(ld.getWorkDate()) + "<br />详细地址：" + ((ld.getAddress()==null || "".equals(ld.getAddress()) )?"暂无":ld.getAddress()));
			pointMap.setName(DateUtils.formatDate(ld.getWorkDate()) + "<br />" + ((ld.getAddress()==null || "".equals(ld.getAddress()) )?"暂无":ld.getAddress()));

			List<Double> point = new ArrayList<Double>();
			point.add(ld.getLongitude());
			point.add(ld.getLatitudes());
			pointMap.setValue(point);
			String deviceNo = ld.getDeviceNo();
			if(deviceTmpMap.get(deviceNo) == null) {
				deviceTmpMap.put(deviceNo, new ArrayList<LgMapPoint>());
			} 
			deviceTmpMap.get(deviceNo).add(pointMap);
		}
		Iterator<Map.Entry<String, List<LgMapPoint>>> it = deviceTmpMap.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, List<LgMapPoint>> mapEntry = it.next();
			LgDeviceMapPoint deviceMapPoint = new LgDeviceMapPoint();
			deviceMapPoint.setDevice(mapEntry.getKey());
			deviceMapPoint.setMapPointList(mapEntry.getValue());
			devicePointList.add(deviceMapPoint);
		}
		return devicePointList;
	}
	
	@RequestMapping(value = "getMachineJobReportLine")
	public @ResponseBody LgDeviceMapLine getLine(HttpServletRequest request, HttpServletResponse response) {
		String deviceNo = request.getParameter("deviceNo");
		String lineDate = request.getParameter("lineDate");

		deviceNo = (deviceNo == null || "".equals(deviceNo))?"deviceNo":deviceNo;
		lineDate = (lineDate == null || "".equals(lineDate))?"1990-10-25":lineDate;

		LgSliceTempDataSection sliceData = new LgSliceTempDataSection();
		sliceData.setDeviceNum(deviceNo);
		sliceData.setRecordTime(DateUtils.parseDate(lineDate));
		List<LgSliceTempDataSection> sliceDataList = lgSliceTempDataService.findList(sliceData);
		int sliceCount = sliceDataList.size();
		List<List<LgMapLine>> lineList = new ArrayList<List<LgMapLine>>();
		
		List<Double> longitudeList = new ArrayList<Double>();
		List<Double> latitudeList = new ArrayList<Double>();

		for(int index = 0; index < sliceCount-1; index++) {
			List<LgMapLine> lines = new ArrayList<LgMapLine>();
			LgSliceTempDataSection linePoint_1 = sliceDataList.get(index);
			LgSliceTempDataSection linePoint_2 = sliceDataList.get(index+1);

			longitudeList.add(linePoint_1.getLongitudeNum());
			latitudeList.add(linePoint_1.getLatitudeNum());
			
			//1
			LgMapLine line_1 = new LgMapLine();
			line_1.setName((linePoint_1.getAddress() == null || "".equals(linePoint_1.getAddress()))?""+linePoint_1.getLongitudeNum()+"," + linePoint_1.getLatitudeNum() :linePoint_1.getAddress());
			List<Double> pointList_1 = new ArrayList<Double>();
			pointList_1.add(linePoint_1.getLongitudeNum());
			pointList_1.add(linePoint_1.getLatitudeNum());
			line_1.setCoord(pointList_1);
			lines.add(line_1);
			
			//2
			LgMapLine line_2 = new LgMapLine();
			line_2.setName((linePoint_2.getAddress() == null || "".equals(linePoint_2.getAddress()))?""+linePoint_2.getLongitudeNum()+"," + linePoint_2.getLatitudeNum() :linePoint_2.getAddress());
			List<Double> pointList_2 = new ArrayList<Double>();
			pointList_2.add(linePoint_2.getLongitudeNum());
			pointList_2.add(linePoint_2.getLatitudeNum());
			line_2.setCoord(pointList_2);
			lines.add(line_2);
			
			lineList.add(lines);
		}
		LgDeviceMapLine deviceMapLine = new LgDeviceMapLine();
		List<Double> centerPosition = new ArrayList<Double>();
		long zoomValue = 1;
		if(sliceCount > 0) {
		 Map<String, Object>  longitudeMap = MathUtils.computeVarianceHasDegree(longitudeList);
		 Map<String, Object>  latitudeMap = MathUtils.computeVarianceHasDegree(latitudeList);
		 Double longitudeAvg = Double.parseDouble(longitudeMap.get(MathUtils.AVERAGE).toString());
		 Double longitudeDValue = Double.parseDouble(longitudeMap.get(MathUtils.MAX_VALUE).toString()) 
				 - Double.parseDouble(longitudeMap.get(MathUtils.MIN_VALUE).toString());
		
		 Double latitudeAvg = Double.parseDouble(latitudeMap.get(MathUtils.AVERAGE).toString());
		 Double latitudeDValue = Double.parseDouble(latitudeMap.get(MathUtils.MAX_VALUE).toString()) 
				 - Double.parseDouble(latitudeMap.get(MathUtils.MIN_VALUE).toString());
		 centerPosition.add(longitudeAvg);
		 centerPosition.add(latitudeAvg);

		 zoomValue = Math.round(25 / Math.max(longitudeDValue, latitudeDValue));
		
		} 
		 deviceMapLine.setMapLineList(lineList);
		 deviceMapLine.setDevice(deviceNo);
		 deviceMapLine.setCenterPosition(centerPosition);
		 deviceMapLine.setZoomValue(zoomValue);
		 return deviceMapLine;
	}
	
}


