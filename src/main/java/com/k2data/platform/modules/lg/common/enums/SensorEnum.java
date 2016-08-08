/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.common.enums;

/**
 * 传感器列表 Enum
 * @author K2DATA.wsguo
 * @date Jul 25, 2016 9:23:14 AM    
 * 
 */
public enum SensorEnum {

	ACC_STATUS("accStatus","acc_status"),
	COMM_STATUS("commStatus","comm_status"),
	ELEC_STATUS("elecStatus","elec_status"),
	ENGIN_ROTAT("enginRotate","engin_rotate"),
	ENGINE_TEMPERATURE("engineTemperature","engine_temperature"),
	GPRS_SIGNAL("gprsSignal","gprs_signal"),
	GPS_POWER_STATUS("gpsPowerStatus","gps_power_status"),
	GPS_SPEED("gpsSpeed","gps_speed"),
	GSM_SIGNAL("gsmSignal","gsm_signal"),
	IMITATE1("imitate1","imitate1"),
	IMITATE2("imitate2","imitate2"),
	LATITUDE_NUM("latitudeNum","latitude_num"),
	LED_STATUS("ledStatus","led_status"),
	LOCK_STATUS("lockStatus","lock_status"),
	LOCKER_STATUS("lockerStatus","locker_status"),
	LONGITUDE_NUM("longitudeNum","longitude_num"),
	OIL_LEVEL("oilLevel","oil_level"),
	OIL_TEMPERATURE("oilTemperature","oil_temperature"),
	POWER_TYPE("powerType","power_type"),
	PRESSURE_METER("pressureMeter","pressure_meter"),
	REALTIME_DURATION("realtimeDuration","realtime_duration"),
	START_TIMES("startTimes","start_times"),
	TOTAL_DURATION("totalDuration","total_duration"),
	WIRE_STATUS("wireStatus","wire_status"),
	WORK_MONITOR("workMonitor","work_monitor"),
	AMAPLATITUDE_NUM("amaplatitudeNum","amaplatitude_num"),
	AMAPLONGITUDE_NUM("amaplongitudeNum","amaplongitude_num"),
	CITYCODE("cityCode","citycode"),
	ADDRESS("address","address");

	private SensorEnum(String underlineName, String camelhumpName) {
		this.underlineName = underlineName;
		this.camelhumpName = camelhumpName;
	}
	
	private String underlineName;	//下划线式
	private String camelhumpName;	//驼峰式
	
	public String getUnderlineName() {
		return underlineName;
	}
	public void setUnderlineName(String underlineName) {
		this.underlineName = underlineName;
	}
	public String getCamelhumpName() {
		return camelhumpName;
	}
	public void setCamelhumpName(String camelhumpName) {
		this.camelhumpName = camelhumpName;
	}
	
	
}
