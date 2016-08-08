package com.k2data.platform.modules.lg.common.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.k2data.platform.common.utils.DateUtils;
import com.k2data.platform.common.utils.IdGen;
import com.k2data.platform.modules.lg.entity.baseinfo.LgDimDate;

public class GenDimDate {
	/**
	 * 生成时间维度数据List
	 * @param startDate 起始日期
	 * @param howManyYear 生成多少年的数据
	 * @return
	 */
	public static List<LgDimDate>  genDimDateData(Date startDate, int howManyYear) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		int startYear = calendar.get(Calendar.YEAR);
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		List<LgDimDate> dimDateList = new ArrayList<LgDimDate>();
		
		while(!(calendar.get(Calendar.YEAR) - startYear == howManyYear && dayOfYear == calendar.get(Calendar.DAY_OF_YEAR))) {
			LgDimDate lgDimDate = new LgDimDate();
			Date day = calendar.getTime();

			lgDimDate.setId(IdGen.uuid());
			lgDimDate.setDay(day);
			for (Map.Entry<Integer, String> entry : CalendarUtils.getWhichYearWeek(day) .entrySet()) {  
				lgDimDate.setWeekId(entry.getKey());
				lgDimDate.setWeekName(entry.getValue());
			}
			for (Map.Entry<Integer, String> entry : CalendarUtils.getWhichYearMonth(day) .entrySet()) {  
				lgDimDate.setMonthId(entry.getKey());
				lgDimDate.setMonthName(entry.getValue());
			}
			for (Map.Entry<Integer, String> entry : CalendarUtils.getWhichYearQuarter(day) .entrySet()) {  
				lgDimDate.setSeasonId(entry.getKey());
				lgDimDate.setSeasonName(entry.getValue());
			}
			for (Map.Entry<Integer, String> entry : CalendarUtils.getWhichYear(day) .entrySet()) {  
				lgDimDate.setYearId(entry.getKey());
				lgDimDate.setYearName(entry.getValue());
			}
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			dimDateList.add(lgDimDate);
		}

		return dimDateList;
		
	}
	/**
	 * 
	 * @param oldData 已存在数据的最大最小日期 如{minDay=2016-01-01, maxDay=2035-12-31}
	 * @param startDate
	 * @param howManyYear
	 * @return
	 */
	public static List<LgDimDate>  genDimDateData(Map<String, Object> oldData ,Date startDate, int howManyYear) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		int startYear = calendar.get(Calendar.YEAR);
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		List<LgDimDate> dimDateList = new ArrayList<LgDimDate>();
		if(oldData == null || oldData.size() <= 0) {
			return genDimDateData(startDate, howManyYear);
		}
				
		while(!(calendar.get(Calendar.YEAR) - startYear == howManyYear && dayOfYear == calendar.get(Calendar.DAY_OF_YEAR))) {
			LgDimDate lgDimDate = new LgDimDate();
			Date day = calendar.getTime();
			if(day.getTime() < DateUtils.parseDate(oldData.get("minDay")).getTime() || day.getTime() > DateUtils.parseDate(oldData.get("maxDay")).getTime()) {
				lgDimDate.setId(IdGen.uuid());
				lgDimDate.setDay(day);
				for (Map.Entry<Integer, String> entry : CalendarUtils.getWhichYearWeek(day) .entrySet()) {  
					lgDimDate.setWeekId(entry.getKey());
					lgDimDate.setWeekName(entry.getValue());
				}
				for (Map.Entry<Integer, String> entry : CalendarUtils.getWhichYearMonth(day) .entrySet()) {  
					lgDimDate.setMonthId(entry.getKey());
					lgDimDate.setMonthName(entry.getValue());
				}
				for (Map.Entry<Integer, String> entry : CalendarUtils.getWhichYearQuarter(day) .entrySet()) {  
					lgDimDate.setSeasonId(entry.getKey());
					lgDimDate.setSeasonName(entry.getValue());
				}
				for (Map.Entry<Integer, String> entry : CalendarUtils.getWhichYear(day) .entrySet()) {  
					lgDimDate.setYearId(entry.getKey());
					lgDimDate.setYearName(entry.getValue());
				}
				dimDateList.add(lgDimDate);
			}
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}

		return dimDateList;
		
	}
}
