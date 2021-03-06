package com.k2data.platform.modules.lg.common.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 日历相关
 */
public class CalendarUtils {
	public static final String DateFormat = "yyyy-MM-dd";// 日期格式
	public static final String DateTimeFormat = "yyyy-MM-dd HH:mm:ss";// 日期格式


	/**
	 * 
	 * 方法名 ：getCalendar<br>
	 * 方法描述 ：返回固定条件设置的Calendar对象。<br>
	 * Calendar对象设置项：设置每周的第一天为周一；设置每周从周一开始；设置每周至少7天<br>
	 * @return 返回类型 ：Calendar
	 */
	public static Calendar getCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置每周的第一天为周一
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 设置每周从周一开始
		cal.setMinimalDaysInFirstWeek(7);// 设置每周至少7天
		return cal;
	}

	/**
	 * 
	 * 方法名 ：getWeekDays<br>
	 * 方法描述 ：输入年、周，计算本周7天的日期<br>
	 * @param year ：年份
	 * @param yearWeek ：周数
	 * @return 返回类型 ：String[]
	 */
	public static String[] getWeekDays(int year, int yearWeek) {
		String[] str = new String[7];
		Calendar cal = getCalendar();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, yearWeek);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DATE, cal.getActualMinimum(Calendar.DAY_OF_WEEK) - dayOfWeek);
		for (int i = 0; i < str.length; i++) {
			cal.add(Calendar.DATE, 1);
			Date d = cal.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
			str[i] = sdf.format(d);
		}
		return str;
	}

	/**
	 * 
	 * 方法名 ：getWeekNumByYear<br>
	 * 方法描述 ：输入年份，获取一年有多少个周<br>
	 * @param year
	 * @return 返回类型 ：int
	 */
	public static int getWeekNumByYear(final int year) {
		int result = 52;// 每年至少有52个周 ，最多有53个周。
		String date = getYearWeekFirstDay(year, 53);
		if (date.substring(0, 4).equals(year + "")) {
			// 判断年度是否相符，如果相符说明有53个周。
			result = 53;
		}
		return result;

	}

	/**
	 * 
	 * 方法名 ：getWeeksByYear<br>
	 * 方法描述 ：输入年份，计算该年份每周的开始日期到结束日期<br>
	 * @param year
	 * @return 返回类型 ：List<String[]>
	 */
	public static List<String[]> getWeeksByYear(final int year) {
		int weeks = getWeekNumByYear(year);
		List<String[]> result = new ArrayList<String[]>(weeks);
		for (int i = 1; i <= weeks; i++) {
			String[] tempWeek = new String[2];
			tempWeek[0] = getYearWeekFirstDay(year, i);
			tempWeek[1] = getYearWeekEndDay(year, i);
			result.add(tempWeek);
		}
		return result;
	}

	/**
	 * 
	 * 方法名 ：getDaysOfMonth<br>
	 * 方法描述 ：输入年份，月数，获取有多少天<br>
	 * @param year
	 * @param month
	 * @return 返回类型 ：int
	 */
	public static int getDaysOfMonth(int year, int month) {
		Calendar calendar = getCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);// 月份从0开始
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 
	 * 方法名 ：getWeeksOfMonth<br>
	 * 方法描述 ：输入年份，月数，获取有多少周<br>
	 * @param year ：年份
	 * @param month ：月份
	 * @return 返回类型 ：int
	 */
	public static int getWeeksOfMonth(int year, int month) {
		Calendar calendar = getCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);//月从0开始，故要-1
		return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 
	 * 方法名 ：getFormatDate<br>
	 * 方法描述 ：格式化转换日期类型<br>
	 * @param date
	 * @return 返回类型 ：String
	 */
	public static String getFormatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormat);
		return dateFormat.format(date);
	}
	
	/**
	 * 
	 * 方法名 ：getYearWeekFirstDay<br>
	 * 方法描述 ：输入年、周，计算本周第一天的日期<br>
	 * @param year ：年份
	 * @param yearWeek ：周数
	 * @return 返回类型 ：String
	 */
	public static String getYearWeekFirstDay(int year, int yearWeek) {
		Calendar calendar = getCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.WEEK_OF_YEAR, yearWeek);
		return getFormatDate(calendar.getTime());
	}

	/**
	 * 
	 * 方法名 ：getYearWeekEndDay<br>
	 * 方法描述 ：输入年、周，计算本周最后一天的日期<br>
	 * @param year ：年份
	 * @param yearWeek ：周数
	 * @return 返回类型 ：String
	 */
	public static String getYearWeekEndDay(int year, int yearWeek) {
		Calendar calendar = Calendar.getInstance();
		// 下面两句代码配合，才能实现，每年度的第一个周，是包含第一个星期一的那个周。
		calendar.setFirstDayOfWeek(Calendar.MONDAY);// 设置每周的第一天为周一
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);// 设置每周最后一天
		calendar.setMinimalDaysInFirstWeek(7);// 设置每周至少7天
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.WEEK_OF_YEAR, yearWeek);
		return getFormatDate(calendar.getTime());
	}
	
	/**
	 * 
	 * 方法名 ：getYearMonthFirstDay<br>
	 * 方法描述 ：输入年、月，计算本月第一天的日期<br>
	 * @param year ：年份
	 * @param month ：月份
	 * @return 返回类型 ：String
	 */
	public static String getYearMonthFirstDay(int year ,int month) {
		return year + "-" + String.format("%02d", month) + "-01";
	}
	
	/**
	 * 
	 * 方法名 ：getYearMonthEndDay<br>
	 * 方法描述 ：输入年、月份，计算本月最后一天的日期<br>
	 * @param year ：年份
	 * @param month ：月份
	 * @return 返回类型 ：String
	 */
	public static String getYearMonthEndDay(int year ,int month) {
		Calendar calendar = getCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1); //月从0开始，故要-1
		return year + "-" + String.format("%02d", month) + "-" + calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 
	 * 方法名 ：getYearQuarterFirstDay<br>
	 * 方法描述 ：输入年、季度，计算本季度第一天的日期<br>
	 * @param year ：年份
	 * @param quarter ：季度数
	 * @return 返回类型 ：String
	 */
	public static String getYearQuarterFirstDay(int year ,int quarter) {
		return year + "-" + String.format("%02d", (quarter-1)*3+1) + "-01";
	}
	
	/**
	 * 
	 * 方法名 ：getYearQuarterEndDay<br>
	 * 方法描述 ：输入年、季度，计算本季度最后一天的日期<br>
	 * @param year ：年份
	 * @param quarter ：季度数
	 * @return 返回类型 ：String
	 */
	public static String getYearQuarterEndDay(int year ,int quarter) {
		Calendar calendar = getCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, (quarter)*3-1); //月从0开始，故要-1
		return year + "-" + String.format("%02d", (quarter)*3) + "-" + calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 
	 * 方法名 ：getYearQuarterFirstMonthNumber<br>
	 * 方法描述 ：输入年、季度，计算本季度第一个月的MonthNumber<br>
	 * @param year ：年份
	 * @param quarter ：季度数
	 * @return 返回类型 ：String
	 */
	public static int getYearQuarterFirstMonthNumber(int year ,int quarter) {
		return genMonthNumber(year, (quarter-1)*3+1);
	}
	
	/**
	 * 
	 * 方法名 ：getYearQuarterEndMonthNumber<br>
	 * 方法描述 ：输入年、季度，计算本季度最后一个月的MonthNumber<br>
	 * @param year ：年份
	 * @param quarter ：季度数
	 * @return 返回类型 ：String
	 */
	public static int getYearQuarterEndMonthNumber(int year ,int quarter) {
		return genMonthNumber(year, quarter*3);
	}
	
	
	/**
	 * 
	 * 方法名 ：getYearFirstDay<br>
	 * 方法描述 ：输入年，计算本年第一天的日期<br>
	 * @param year ：年份
	 * @return 返回类型 ：String
	 */
	public static String getYearFirstDay(int year) {
		return year + "-01-01";
	}
	
	/**
	 * 
	 * 方法名 ：getYearQuarterEndDay<br>
	 * 方法描述 ：输入年，计算本年最后一天的日期<br>
	 * @param year ：年份
	 * @return 返回类型 ：String
	 */
	public static String getYearEndDay(int year) {
		return year + "-12-31";
	}
	
	
	/**
	 * 
	 * 方法名 ：getYearFirstQuarterNumber<br>
	 * 方法描述 ：输入年，计算本年第一季度的QuarterNumber<br>
	 * @param year ：年份
	 * @return 返回类型 ：String
	 */
	public static int getYearFirstQuarterNumber(int year) {
		return genQuarterNumber(year, 1);
	}
	
	/**
	 * 
	 * 方法名 ：getYearEndQuarterNumber<br>
	 * 方法描述 ：输入年，计算本年最后一季度的QuarterNumber<br>
	 * @param year ：年份
	 * @return 返回类型 ：String
	 */
	public static int getYearEndQuarterNumber(int year) {
		return genQuarterNumber(year, 4);
	}
	
	/**
	 * 生成时间维度-weekID 规则为 "四位年+1+两位周" yyyy1ww
	 * @param year
	 * @param week
	 * @return
	 */
	public static int genWeekNumber(int year, int week) {
		return year*1000+100+week;
	}
	/**
	 * 生成时间维度-monthID 规则为 "四位年+2+两位月" yyyy2MM
	 * @param year
	 * @param month
	 * @return
	 */
	public static int genMonthNumber(int year, int month) {
		return year*1000+200+month;
	}
	
	/**
	 * 生成时间维度-monthID 规则为 "四位年+2+两位月" yyyy2MM
	 * @param date
	 * @return
	 */
	public static int genMonthNumber(Date date) {
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		return year*1000+200+month;
	}
	/**
	 * 生成时间维度-quarterID 规则为 "四位年+3+两位季度号" yyyy3qq
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static int genQuarterNumber(int year, int quarter) {
		return year*1000+300+quarter;
	}
	/**
	 * 生成时间维度-yearID  规则为 "四位年" yyyy
	 * @param year
	 * @return
	 */
	public static int genYearNumber(int year) {
		return year;
	}
	
	/**
	 * 生成时间维度-weekName
	 * @param year
	 * @param week
	 * @return
	 */
	public static String genWeekName(int year, int week) {
		return year+"年第"+week+"周";
	}
	/**
	 * 生成时间维度-monthName 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String genMonthName(int year, int month) {
		return year+"年"+month+"月";
	}
	
	/**
	 * 生成时间维度-monthName 
	 * @param date
	 * @return
	 */
	public static String genMonthName(Date date) {
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		return  year+"年"+month+"月";
	}
	/**
	 * 根据 monthId 获取 monthName
	 *
	 * @param monthNumber
	 * @return
	 */
	public static String genMonthName(int monthNumber) {
		return monthNumber/1000+"年"+monthNumber%100+"月";
	}
	
	/**
	 * 生成时间维度-quarterName 
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static String genQuarterName(int year, int quarter) {
		return year+"年第"+quarter+"季度";
	}
	/**
	 * 生成时间维度-yearName  
	 * @param year
	 * @return
	 */
	public static String genYearName(int year) {
		return  year+"年度";
	}
	/**
	 * 根据日期获取为哪一年的第几周
	 * @param date
	 * @return Map<Integer, String> key为weekID， value为weekName
	 */
	public static Map<Integer, String> getWhichYearWeek(Date date) {
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int week = calendar.get(Calendar.WEEK_OF_YEAR);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		if(week > calendar.get(Calendar.WEEK_OF_YEAR)) {
			year -= 1;
		}
		Map<Integer, String>  weekMap = new HashMap<Integer, String>();
		weekMap.put(genWeekNumber(year, week), genWeekName(year, week));
		return weekMap;
	}
	/**
	 * 根据日期获取为哪一年的第几月
	 * @param date
	 * @return Map<Integer, String> key为monthID， value为monthName
	 */
	public static Map<Integer, String> getWhichYearMonth(Date date) {
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		Map<Integer, String>  monthMap = new HashMap<Integer, String>();
		monthMap.put(genMonthNumber(year, month), genMonthName(year, month));
		return monthMap;
	}
	/**
	 * 根据日期获取为哪一年的第几季度
	 * @param date
	 * @return Map<Integer, String> key为quarterID， value为quarterName
	 */
	public static Map<Integer, String> getWhichYearQuarter(Date date) {
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int quarter = 0;
		switch(month) {
			case 1: case 2: case 3: quarter = 1;break;
			case 4: case 5: case 6: quarter = 2;break;
			case 7: case 8: case 9: quarter = 3;break;
			case 10: case 11: case 12: quarter = 4;break;
		}
		Map<Integer, String> quarterMap = new HashMap<Integer, String>();
		quarterMap.put(genQuarterNumber(year, quarter), genQuarterName(year, quarter));
		return quarterMap;
	}
	/**
	 * 根据日期获取为哪一年
	 * @param date
	 * @return Map<Integer, String> key为yearID， value为yearName
	 */
	public static Map<Integer, String> getWhichYear(Date date) {
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		Map<Integer, String> yearMap = new HashMap<Integer, String>();
		int year = calendar.get(Calendar.YEAR);
		yearMap.put(genYearNumber(year), genYearName(year));
		return yearMap;
	}
	
	/**
	 * 获取想对于date时间 最近recentConnt个月的 数组列表
	 *
	 * @param date
	 * @param recentConnt
	 * @return
	 */
	public static List<String> getRecentMonthNameList(Date date, int recentConnt) {
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		List<String> monthList = new ArrayList<String>();
		for(int i = 0; i < recentConnt; i++) {
			calendar.add(Calendar.MONTH, -1);
			monthList.add(genMonthName(calendar.getTime()));
		}
		Collections.reverse(monthList);
		return monthList;
	}
	public static List<String> getRecentMonthNameList() { 
		return getRecentMonthNameList(new Date(), 13);
	}
	/**
	 * 初始化一个monthName的map
	 *
	 * @param date
	 * @param recentConnt
	 * @return
	 */
	public static Map<Integer, Object> getRecentMonthNameInitDataMap(Date date, int recentConnt) {
		Map<Integer,Object> monthDataMap = new TreeMap<Integer, Object>(
			new Comparator<Integer>() {  
                @Override  
                public int compare(Integer o1, Integer o2) {  
                    return o1.compareTo(o2);//升序  
                }  
            }); 
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		for(int i = 0; i < recentConnt; i++) {
			monthDataMap.put(genMonthNumber(calendar.getTime()), 0);
			calendar.add(Calendar.MONTH, -1);
		}
		return monthDataMap;
	}
	public static Map<Integer, Object> getRecentMonthNameInitDataMap() {
		return getRecentMonthNameInitDataMap(new Date(), 13);
	}
	/**
	 * 获取想对于date时间 最近recentConnt个月的 数组列表
	 *
	 * @param date
	 * @param recentConnt
	 * @return
	 */
	public static List<Integer> getRecentMonthNumberList(Date date, int recentConnt) {
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		List<Integer> monthList = new ArrayList<Integer>();
		for(int i = 0; i < recentConnt; i++) {
			monthList.add(genMonthNumber(calendar.getTime()));
			calendar.add(Calendar.MONTH, -1);
		}
		Collections.reverse(monthList);
		return monthList;
	}
	public static List<Integer> getRecentMonthNumberList() { 
		return getRecentMonthNumberList(new Date(), 13);
	}
	
	/**
	 * 获取想对于date时间 最近24个月的 数组列表,不同年份相同月份放一块，比如201501，201601，201502，201602...201512,201612
	 *
	 * @param date
	 * @param recentConnt
	 * @return
	 */
	public static List<Integer> getRecentMonthNumberList24() {
		List<Integer> months = getRecentMonthNumberList(new Date(), 24);
		List<Integer> monthList = new ArrayList<Integer>();
		for(int index=0; index < months.size()/2; index++) {
			monthList.add(months.get(index));
			monthList.add(months.get(index+12));
		}
		return monthList;
	}
	
	/**
	 * 初始化一个monthName的map 24个月的情况，不同年份相同月份放一块，比如201501，201601，201502，201602...201512,201612
	 *
	 * @param date
	 * @param recentConnt
	 * @return
	 */
	public static Map<Integer, Object> getRecentMonthNameInitDataMap24() {
		Map<Integer,Object> monthDataMap = new LinkedHashMap<Integer, Object>(); 
		List<Integer> month24 = getRecentMonthNumberList24();
		for(Integer month : month24) {
			monthDataMap.put(month, 0);
		}
		
		return monthDataMap;
	}
	
	/**
	 * 获取当前日期前一天的日期时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getBeforeDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return calendar.getTime();
	}
	public static Date getBeforeDate() {
		return getBeforeDate(new Date());
	}
	
	public static void main(String[] args) {
		System.out.println(getRecentMonthNameInitDataMap24());
	}
}
