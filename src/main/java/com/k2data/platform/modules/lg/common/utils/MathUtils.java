package com.k2data.platform.modules.lg.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 切片统计相关计算
 */
public class MathUtils {
	
	public static final String HIGH_ELEC = "01"; 				//高电平 
	public static final String LOW_ELEC  = "00"; 				//低电平
	public static final boolean CALC_ABS_TRUE  = true; 			//计算绝对值（模拟量梯度）
	public static final boolean CALC_ABS_FALSE  = false; 		//不计算绝对值（模拟量梯度）
	public static final int TIME_INTERVAL  = 3; 				//数据时间间隔 3min

	public static final int PERCENT_5  = 5; 					// 5分位
	public static final int PERCENT_25  = 25; 					//25分位
	public static final int PERCENT_50  = 50; 					//50分位
	public static final int PERCENT_75  = 75; 					//75分位
	public static final int PERCENT_95  = 95; 					//95分位

	public static final String AVERAGE  = "average"; 			//平均值
	public static final String VARIANCE  = "variance"; 		//方差
	public static final String STD_VARIANCE  = "stdVariance"; 	//标准差
	public static final String MAX_VALUE  = "maxValue"; 		//最大值
	public static final String MIN_VALUE  = "minValue"; 		//最小值

	public static final String STATUS_MAP  = "statusMap"; 		//状态量Map
	public static final String SWITCH_MAP  = "switchMap"; 		//状态量切换Map

	/**
	 * 分析状态数据，每个状态对应一个map，key为状态，value为每次状态持续的次数
	 * @param dataList
	 * @return
	 */
	public static <T> Map<String,List<Double>> analysisStatusData(List<T> dataList) {
		//计数变量
		int count = 0;

		Map<String,List<Double>> statusMap = new HashMap<String,List<Double>>();
		//上一个数据
		String prev = dataList.get(0).toString();
		for(T data : dataList) {
			if(!data.toString().equals(prev)) {
				if(statusMap != null && statusMap.get(prev) != null) {
					statusMap.get(prev).add((double)count);
				} else {
					statusMap.put(prev, new ArrayList<Double>());
					statusMap.get(prev).add((double)count);
				}
				count = 0;
			}
			count++;
			prev = data.toString();
		}
		//扫描结束也要考虑最后一个状态
		if(statusMap != null && statusMap.get(prev) != null) {
			statusMap.get(prev).add((double)count);
		} else {
			statusMap.put(prev, new ArrayList<Double>());
		}
		return statusMap;
	}

	/**
	 * 分析状态切换数据，每个状态对应一个map，key为源状态-目标状态，value为每种【源状态-目标状态】状态切换的次数
	 * @param dataList
	 * @return
	 */
	public static <T> Map<String, Double> analysisSwitchStatusData(List<T> dataList) {

		Map<String,Double> switchMap = new HashMap<String, Double>();
		//上一个数据
		String prev = dataList.get(0).toString();
		for(T data : dataList) {
			if(!data.toString().equals(prev)) {
				if(switchMap != null && switchMap.get(prev+ "->" +data) != null) {
					switchMap.put(prev+ "->" +data, switchMap.get(prev+ "->" +data)+1);
				} else {
					switchMap.put(prev+ "->" +data, 1d);
				}
			}
			prev = data.toString();
		}
		return switchMap;
	}
	/**
	 * 获取数据的梯度列表
	 * @param data
	 * @return
	 */
	public static <T> List<Double> getGradient(List<T> data){
		List<Double> gradList = new ArrayList<Double>();
		for (int i = 0; i < data.size()-1; i++) {
			if(i == data.size()){
				break;                                                                                                                               
			}
			Double gradient = Double.parseDouble(data.get(i+1).toString()) - Double.parseDouble(data.get(i).toString());
			gradList.add(gradient);
		}
		return gradList;
	}
	
	/**
	 * 一次扫描 分析状态持续数据 及 状态切换数据 
	 * @param dataList
	 * @return
	 */
	public static <T> Map<String, Object> analysisStatusDataAndStatusSwitchData (List<T> dataList) {
		Map<String,Double> switchMap = new HashMap<String, Double>();
		//计数变量
		int count = 0;

		Map<String,List<Double>> statusMap = new HashMap<String,List<Double>>();
		//上一个数据
		String prev = dataList.get(0).toString();
		for(T data : dataList) {
			if(!data.toString().equals(prev)) {
				//状态持续数据
				if(statusMap != null && statusMap.get(prev) != null) {
					statusMap.get(prev).add((double)count);
				} else {
					statusMap.put(prev, new ArrayList<Double>());
					statusMap.get(prev).add((double)count);
				}
				count = 0;
				
				//状态切换数据
				if(switchMap != null && switchMap.get(prev+ "->" +data) != null) {
					switchMap.put(prev+ "->" +data, switchMap.get(prev+ "->" +data)+1);
				} else {
					switchMap.put(prev+ "->" +data, 1d);
				}
			}
			count++;
			prev = data.toString();
		}
		//扫描结束也要考虑最后一个状态
		if(statusMap != null && statusMap.get(prev) != null) {
			statusMap.get(prev).add((double)count);
		} else {
			statusMap.put(prev, new ArrayList<Double>());
			statusMap.get(prev).add((double)count);
		}
		Map<String, Object> sMap = new HashMap<String, Object>();
		sMap.put(STATUS_MAP, statusMap);
		sMap.put(SWITCH_MAP, switchMap);
		return sMap;
	}

	/**
	 * 分析梯度数据 默认计算绝对值
	 * @param dataList
	 * @return
	 */
	public static <T> List<Double> analysisGradient(List<T> dataList) {
		return analysisGradient(dataList, CALC_ABS_TRUE);
	}
	/**
	 * 分析梯度数据
	 * @param dataList
	 * @param calcAbs 是否计算绝对值 CALC_ABS_TRUE or CALC_ABS_FALSE
	 * @return
	 */
	public static <T> List<Double> analysisGradient(List<T> dataList, boolean calcAbs) {
		List<Double> gradientList = new ArrayList<Double>();

		if(calcAbs) {
			for(int i=1; i < dataList.size(); i++) {
				gradientList.add(Math.abs(Double.parseDouble(dataList.get(i).toString()) - Double.parseDouble(dataList.get(i-1).toString())));
			}
		} else {
			for(int i=1; i < dataList.size(); i++) {
				gradientList.add(Double.parseDouble(dataList.get(i).toString()) - Double.parseDouble(dataList.get(i-1).toString()));
			}
		}
		
		return gradientList;
	}
	/**
	 * 转换list元素为绝对值
	 * @param srcList
	 * @return
	 */
	public static List<Double> absList(List<Double> srcList) {
		List<Double> absList = new ArrayList<Double>();
		for(Double data : srcList) {
			absList.add(Math.abs(data));
		}
		return absList;
	}
	/**
	 * 计算指定的分位值 默认 timeInterval 为1
	 * @param list
	 * @param percentNum 第几分位 比如 PERCENT_5：第五分位 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T extends Comparable> double computePercent(List<T> list, int percentNum) {
		return computePercent(list, percentNum, 1);
	}
	/**
	 * 计算指定的分位值
	 * @param list
	 * @param percentNum 第几分位 比如 PERCENT_5：第五分位 
	 * @param timeInterval 每次取数据的时间间隔，如果计算时间相关需要x该时间间隔
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T extends Comparable> double computePercent(List<T> list, int percentNum, int timeInterval) {
		if(list == null ||list.size() <= 0) {
			return 0d;
		}
		Collections.sort(list);
		int index = (int)Math.round(percentNum * list.size()/100.00);
		//list下标从0开始，故下标减1
		index = (index == 0)?0:(index-1);
		return Double.parseDouble(list.get(index).toString()) * timeInterval;
	}
	
	/**
	 * 一次计算 5,25,50,75,95 分位值, 非时间计算，即时间间隔为1
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T extends Comparable> Map<Integer, Double> computeAllPercent(List<T> list) {
		return computeGivenPercent(list, new int[]{PERCENT_5, PERCENT_25, PERCENT_50, PERCENT_75, PERCENT_95}, 1);
	}
	
	/**
	 * 一次计算 5,25,50,75,95 分位值, 时间间隔为timeInterval
	 * @param list
	 * @param timeInterval 每次取数据的时间间隔，如果计算时间相关需要x该时间间隔
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T extends Comparable> Map<Integer, Double> computeAllPercent(List<T> list, int timeInterval) {
		return computeGivenPercent(list, new int[]{PERCENT_5, PERCENT_25, PERCENT_50, PERCENT_75, PERCENT_95}, timeInterval);
	}
	
	/**
	 * 一次计算 percentNums 数组中指定的分位值,非时间计算，即时间间隔为1
	 * @param list
	 * @param percentNums 给定要计算的分位值
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T extends Comparable> Map<Integer, Double> computeGivenPercent(List<T> list, int[] percentNums) {
		return computeGivenPercent(list, percentNums, 1);
	}

	/**
	 * 一次计算 percentNums 数组中指定的分位值
	 * @param list
	 * @param percentNums  给定要计算的分位值
	 * @param timeInterval 每次取数据的时间间隔，如果计算时间相关需要x该时间间隔
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T extends Comparable> Map<Integer, Double> computeGivenPercent(List<T> list, int[] percentNums, int timeInterval) {
		if(list == null ||list.size() <= 0) {
			return null;
		}
		Map<Integer, Double> percentValueMap = new HashMap<Integer, Double>();
		//排序
		Collections.sort(list);
		for(int percentNum : percentNums) {
			int index = (int)Math.round(percentNum * list.size()/100.00);
			//list下标从0开始，故下标减1
			index = (index == 0)?0:(index-1);
			percentValueMap.put(percentNum, Double.parseDouble(list.get(index).toString()) * timeInterval);
		}
		return percentValueMap;
	}
	
	/**
	 * 获取平均值，方差，标准差，最大最小值 默认 timeInterval=1
	 * @param list
	 * @return
	 */
	public static <T> Map<String, Object> computeVariance(List<T> list) {
		return computeVariance(list, 1);
	}
	/**
	 * 获取平均值，方差，标准差，最大最小值
	 * @param list
	 * @param timeInterval 每次取数据的时间间隔，如果计算时间相关需要x该时间间隔
	 * @return
	 */
	public static <T>  Map<String, Object> computeVariance(List<T> list, int timeInterval) {
		if(timeInterval <= 0) {
			timeInterval = 1;
		}
		double variance = 0d,
				average = 0d,
				sum1 = 0d, 
				sum2 = 0d,
				maxValue = 0d,
				minValue = 0d;;
		int len = list.size();
		if(len <= 0) {
			return null;
		}
		minValue = Double.parseDouble(list.get(0).toString());
		for(int i = 0; i < len; i++) {
			double tmp = Double.parseDouble(list.get(i).toString());
			sum1 += tmp*timeInterval;
			sum2 += (tmp*timeInterval)*(tmp*timeInterval);
			if(maxValue < tmp) {
				maxValue = tmp;
			}
			if(minValue > tmp) {
				minValue = tmp;
			}
		}
		maxValue *= timeInterval;
		minValue *= timeInterval;
		average = sum1/len;
		variance = sum2/len - (sum1/len)*(sum1/len);
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put(AVERAGE, (double)(Math.round(average*100)/100.0));
		retMap.put(VARIANCE, (double)(Math.round(variance*100)/100.0));
		retMap.put(STD_VARIANCE, (double)(Math.round(Math.sqrt(variance)*100)/100.0));
		retMap.put(MAX_VALUE,maxValue);
		retMap.put(MIN_VALUE,minValue);
		return retMap;
	}


	public static Double computeSum(List<Double> list) {
		return computeSum(list, 1);
	}
	public static Double computeSum(List<Double> list, int timeInterval) {
		Double sum = 0d;
		for(Double d : list) {
			sum += d ;
		}
		return sum * timeInterval;
	}
	
	/**
	 * 获取平均值，最大最小值 保留精度，地图作图时使用
	 * @param list
	 * @return
	 */
	public static <T> Map<String, Object> computeVarianceHasDegree(List<T> list) {
		return computeVarianceHasDegree(list, 1);
	}
	
	/**
	 * 获取平均值，最大最小值 保留精度，地图作图时使用
	 * @param list
	 * @param timeInterval 每次取数据的时间间隔，如果计算时间相关需要x该时间间隔
	 * @return
	 */
	public static <T> Map<String, Object> computeVarianceHasDegree(List<T> list, int timeInterval) {
		if(timeInterval <= 0) {
			timeInterval = 1;
		}
		double average = 0d,
				sum1 = 0d, 
				maxValue = 0d,
				minValue = 0d;;
		int len = list.size();
		if(len <= 0) {
			return null;
		}
		minValue = Double.parseDouble(list.get(0).toString());

		for(int i = 0; i < len; i++) {
			double tmp = Double.parseDouble(list.get(i).toString());
			sum1 += tmp*timeInterval;
			if(maxValue < tmp) {
				maxValue = tmp;
			}
			if(minValue > tmp) {
				minValue = tmp;
			}
		}
		maxValue *= timeInterval;
		minValue *= timeInterval;
		average = sum1/len;
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put(AVERAGE, average);
		retMap.put(MAX_VALUE,maxValue);
		retMap.put(MIN_VALUE,minValue);
		return retMap;
	}
	public static void main(String[] args) {
		List<String> dataList = new ArrayList<String>();
		dataList.add("01");
		dataList.add("01");
		dataList.add("01");
		dataList.add("11");
		dataList.add("11");
		dataList.add("00");
		dataList.add("00");
		dataList.add("01");
		dataList.add("01");
		dataList.add("10");
		dataList.add("10");
		dataList.add("10");
		dataList.add("00");
		dataList.add("00");
		dataList.add("00");
		dataList.add("01");
		dataList.add("01");
		dataList.add("11");
		dataList.add("11");
		dataList.add("10");
		dataList.add("10");
		dataList.add("10");
		dataList.add("00");
		dataList.add("00");
		dataList.add("01");
		dataList.add("01");
		dataList.add("00");
		dataList.add("00");
		System.out.println("状态切换分析-----" + analysisSwitchStatusData(dataList));
		System.out.println("状态持续分析-----" + analysisStatusData(dataList));
		System.out.println("状态切换及持续数据分析之状态持续数据----" + analysisStatusDataAndStatusSwitchData(dataList).get(MathUtils.STATUS_MAP));
		System.out.println("状态切换及持续数据分析之状态切换数据----" + analysisStatusDataAndStatusSwitchData(dataList).get(MathUtils.SWITCH_MAP));

		List<Double> dataList2 = new ArrayList<Double>();
		dataList2.add(30d);
		dataList2.add(50d);
		dataList2.add(43d);
		dataList2.add(27d);
		dataList2.add(27d);
		dataList2.add(50d);
		System.out.println("5分位值.........." + computePercent(dataList2, 5));
		System.out.println("梯度........... )" + getGradient(dataList2));
		System.out.println("分析梯度数据加绝对值-----"  + analysisGradient(dataList2, MathUtils.CALC_ABS_TRUE));
		System.out.println("分析梯度数据不加绝对值-----"  + analysisGradient(dataList2, MathUtils.CALC_ABS_FALSE));

		System.out.println("计算模拟量方差等-----" + computeVariance(dataList2));
		System.out.println("计算模拟量方差等-次数----" + computeVariance(dataList2));
		System.out.println("计算模拟量方差等-时间----" + computeVariance(dataList2, MathUtils.TIME_INTERVAL));


		List<String> dataList3 = new ArrayList<String>();
		dataList3.add("01");
		dataList3.add("01");
		dataList3.add("01");
		dataList3.add("00");
		dataList3.add("00");
		dataList3.add("00");
		dataList3.add("00");
		dataList3.add("01");
		dataList3.add("01");
		dataList3.add("00");
		dataList3.add("00");
		dataList3.add("00");
		dataList3.add("00");
		dataList3.add("00");
		dataList3.add("00");
		dataList3.add("01");
		dataList3.add("01");
		dataList3.add("01");
		dataList3.add("01");
		dataList3.add("01");
		dataList3.add("00");
		dataList3.add("00");
		dataList3.add("00");
		dataList3.add("00");
		dataList3.add("01");
		dataList3.add("01");
		dataList3.add("00");
		dataList3.add("00");
		dataList3.add("00");
		dataList3.add("00");
		dataList3.add("00");
		dataList3.add("00");
		dataList3.add("01");
		dataList3.add("01");
		System.out.println("状态分析替换电平数据分析-----" + analysisStatusData(dataList3));
		List<Double> elecList = new ArrayList<Double>();
		elecList = analysisStatusData(dataList3).get(MathUtils.HIGH_ELEC);
		System.out.println("计算电平数据" + MathUtils.PERCENT_5 + "时间分位值-----" + computePercent(elecList,MathUtils.PERCENT_5, MathUtils.TIME_INTERVAL));
		System.out.println("计算电平数据" + MathUtils.PERCENT_25 + "分位值-----" + computePercent(elecList,MathUtils.PERCENT_25));
		System.out.println("计算电平数据" + MathUtils.PERCENT_50 + "分位值-----" + computePercent(elecList,MathUtils.PERCENT_50));
		System.out.println("计算电平数据" + MathUtils.PERCENT_75 + "分位值-----" + computePercent(elecList,MathUtils.PERCENT_75));
		System.out.println("计算电平数据" + MathUtils.PERCENT_95 + "分位值-----" + computePercent(elecList,MathUtils.PERCENT_95));
		System.out.println("一次计算5,25,50,75,95" + "分位值-----" + computeAllPercent(elecList));
		System.out.println("一次计算指定分位值：" + MathUtils.PERCENT_5 + "," + MathUtils.PERCENT_25 + "分位值-----" + computeGivenPercent(elecList, new int[]{PERCENT_5, PERCENT_25}));
	}
}
