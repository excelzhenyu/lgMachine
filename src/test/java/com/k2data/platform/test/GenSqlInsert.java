package com.k2data.platform.test;

import java.util.Calendar;
import java.util.Date;

import com.k2data.platform.common.utils.DateUtils;

public class GenSqlInsert {
	private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static final double JD = 36.833852;
	private static final double WD = 117.203842;
	private static final String[] DEVICE = {"C2000B","C30090"};
	public static void genSqlInsert(Date startDate, Date endDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		while(calendar.getTime().getTime() < endDate.getTime()) {
			int gap = (int)Math.ceil(Math.random()*5+1);
			Date start = calendar.getTime();
			calendar.add(Calendar.HOUR_OF_DAY, gap-1);
			Date end = calendar.getTime();
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			int duration = (int)Math.round(Math.random()*1000);
			for(String device : DEVICE) {
			String iSQL = "insert into lg_deviceslicestatics select replace(uuid(),'-',''),sysdate(),";
				iSQL += "'" + device + "'," + gap + ",";
				iSQL += "'" + DateUtils.formatDate(start, PATTERN) + "','" + DateUtils.formatDate(end, PATTERN) + "',";
				iSQL += duration + "," +  (int)Math.round(duration*2/3) + ",";
				iSQL += (Math.round((JD+Math.random())*1000000)/1000000.0) + "," + (Math.round((WD+Math.random())*1000000)/1000000.0) + ",";
				iSQL += "'山东','临沂',";
				iSQL += "'山东省济南市历城区机场路.离济南百年兴业家俱公司(东北)约" + (int)Math.round(Math.random()*1000) + "米','山东'," + (gap+1) + ";";
				System.out.println(iSQL);
			}
		}
	}
	public static void main(String[] args) {
		genSqlInsert(DateUtils.parseDate("2016-03-01 08:12:10"), DateUtils.parseDate("2016-07-01 12:12:10"));
	}
}
