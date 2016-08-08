/**
 * Copyright &copy; 2016 <a href="https://www.k2data.com.cn">K2DATA</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.utils.DateUtils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OkHttp 工具类
 * 
 * @author K2DATA.wsguo
 * @date Jul 20, 2016 10:16:08 AM
 */
public class OkhttpUtils {
    private static final int CONNECT_TIMEOUT = 60;  
    private static final int READ_TIMEOUT = 100;  
    private static final int WRITE_TIMEOUT = 60;  
	public static final String ISO_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";
	public static final int PAGE_SIZE = 1000; //页大小
	public static final int RETRY_COUNT = 3;  //重试次数

	private static OkHttpClient client =  
	        new OkHttpClient.Builder()  
	                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间  
	                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)//设置写的超时时间  
	                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间  
	                .build();  
    
    /**
     * get请求
     * 
     * @param url 请求地址
     * @param params 请求参数
     * @return 返回结果
     * @throws IOException
     */
    public static Response get(String url, Map<String, String> params) throws IOException {
        return createGetCall(url, params).execute();
    }

    /**
     * get异步请求
     * 
     * @param url 请求地址
     * @param params 请求参数
     * @param callback 回调
     */
    public static void get(String url, Map<String, String> params, Callback callback) {
        createGetCall(url, params).enqueue(callback);
    }

    /**
     * post同步请求
     * 
     * @param url 请求地址
     * @param params 请求参数
     * @return 返回结果
     * @throws IOException
     */
    public static Response post(String url, Map<String, String> params) throws IOException {
        return createPostCall(url, params).execute();
    }

    /**
     * post异步请求
     * 
     * @param url 请求地址
     * @param params 请求参数
     * @param callback 回调
     */
    public static void post(String url, Map<String, String> params, Callback callback) {
        createPostCall(url, params).enqueue(callback);
    }

    /**
     * post同步请求
     * 
     * @param url 请求地址
     * @param params 提交参数
     * @param files 提交的文件
     * @return 返回结果
     * @throws IOException
     */
    public static Response post(String url, Map<String, String> params, Map<String, File> files) throws IOException {
        return createPostCall(url, params, files).execute();
    }

    /**
     * post异步请求
     * 
     * @param url 请求地址
     * @param params 提交参数
     * @param files 提交的文件
     * @param callback 回调
     */
    public static void post(String url, Map<String, String> params, Map<String, File> files, Callback callback) {
        createPostCall(url, params, files).enqueue(callback);
    }

    /**
     * 生成 get 请求的 {@link Call} 对象
     * 
     * @param url 地址
     * @param params 参数
     * @return
     */
    private static Call createGetCall(String url, Map<String, String> params) {
        String urlParams = buildUrlParams(params);
        String urlGet = (urlParams == null)?url:(url + '?' + urlParams);
        Request request = new Request.Builder().get().url(urlGet).build();
        return client.newCall(request);
    }

    /**
     * 生成 get 请求的 url 地址
     * 
     * @param params 请求参数
     * @return
     */
    private static String buildUrlParams(Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (result.length() > 0)
                result.append("&");
            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }
        return result.toString();
    }

    /**
     * 生成 post 请求的 {@link Call} 对象
     * 
     * @param url
     * @param params
     * @param files
     * @return
     */
    private static Call createPostCall(String url, Map<String, String> params, Map<String, File> files) {
        okhttp3.MultipartBody.Builder builder = new MultipartBody.Builder();
        // 上传的参数
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        // 设置上传的文件
        if (files != null && !files.isEmpty()) {

            for (Entry<String, File> entry : files.entrySet()) {
                File file = entry.getValue();
                String contentType = null;

                boolean isPng = file.getName().endsWith(".png") || file.getName().endsWith(".PNG");

                if (isPng) {
                    contentType = "image/png; charset=UTF-8";
                }

                boolean isJpg = file.getName().endsWith(".jpg") || file.getName().endsWith(".JPG")
                        || file.getName().endsWith(".jpeg") || file.getName().endsWith(".JPEG");
                if (isJpg) {
                    contentType = "image/jpeg; charset=UTF-8";
                }
                if (file != null && file.exists()) {
                    RequestBody body = RequestBody.create(MediaType.parse(contentType), file);
                    builder.addFormDataPart(entry.getKey(), file.getName(), body);
                }
            }
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        return client.newCall(request);
    }

    /**
     * 生成 post 请求的 {@link Call} 对象
     * 
     * @param url
     * @param params
     * @return
     */
    private static Call createPostCall(String url, Map<String, String> params) {
        Builder builder = new FormBody.Builder();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        return client.newCall(request);
    }
	
    @Deprecated
	public static String getString(String url, Map<String, String> params) throws IOException {
		String responseStr = "";
		int retry = 0;
		while(retry < RETRY_COUNT) {
			Response response = get(url,params);
			responseStr = response.body().string();
			if(!response.isSuccessful()) {
				retry++;
				//System.out.println("第"+retry + "次尝试!" + "请求失败：" + response.code() + "-" + response.message() + "请求URL为:" + url);
			} else {
				return responseStr;
			}
		}
		return responseStr;
	}
	
    @Deprecated
	public static JSONObject getJSON(String url, Map<String, String> params) throws IOException {
		return JSON.parseObject(getString(url, params));
	}
	
    @Deprecated
	public static String genSelect(String device, List<String> sensors, Date start, Date stop ) {
		return genSelect(device, sensors.toArray(new String[0]), start, stop);
	}
	
	/**
	 * 生成查询条件
	 * 
	 * @param device 设备号
	 * @param sensors ch
	 * @param start
	 * @param stop
	 * @return
	 */
    @Deprecated
	public static String genSelect(String device, String[] sensors, Date start, Date stop ) {
		String sliceStart = DateUtils.formatDate(start, ISO_PATTERN).replace("+", "%2B");
	    String sliceStop = DateUtils.formatDate(stop, ISO_PATTERN).replace("+", "%2B");

		StringBuilder selectSb = new StringBuilder();
		selectSb.append("select=");
		selectSb.append("{\"sources\":{\"device\":");
		selectSb.append("\"").append(device).append("\"");
		selectSb.append(",\"sensors\":[");
		String prefix = "";
		for(String sensor: sensors) {
			selectSb.append(prefix);
			prefix = ",";
			selectSb.append("\"").append(sensor).append("\"");
		}
		selectSb.append("]");
		selectSb.append(",\"timeRange\":{\"start\":{\"iso\":");
		selectSb.append("\"").append(sliceStart).append("\"");
		selectSb.append("},\"end\":{\"iso\":");
		selectSb.append("\"").append(sliceStop).append("\"");
		selectSb.append("}}}}");
		return selectSb.toString();
	}
	
    @Deprecated
	public static String genAggregation(String[] aggregations) {
		StringBuilder aggregationSb = new StringBuilder();
		aggregationSb.append("aggregation=");
		aggregationSb.append("{\"defaultAggregation\":[");
		String prefix = "";
		for(String aggregation: aggregations) {
			aggregationSb.append(prefix);
			prefix = ",";
			aggregationSb.append("\"").append(aggregation).append("\"");
		}
		aggregationSb.append("]}");
		return aggregationSb.toString();
	}
	
    @Deprecated
	public static String joinURL(String url, String select, String valueFilter, String aggregation, String order, Object size, Object page ) {
		StringBuilder urlSb = new StringBuilder();
		urlSb.append(url).append("?").append(select);
		if(StringUtils.isNotBlank(valueFilter)) {
			urlSb.append("&").append(valueFilter);
		}
		if(StringUtils.isNotBlank(aggregation)) {
			urlSb.append("&").append(aggregation);
		}
		if(StringUtils.isNotBlank(order)) {
			urlSb.append("&").append(order);
		}
		if(size != null) {
			urlSb.append("&size=" + size.toString());
		}
		if(page != null) {
			urlSb.append("&page=" + page.toString());
		}
		return urlSb.toString();
	}
	
	/**
	 * http get 请求返回 指定对象
	 *
	 * @author K2DATA.wsguo
	 * @date Jul 5, 2016 5:31:00 PM   
	 * @param url
	 * @param clazz
	 * @return
	 */
    @Deprecated
	public static <T> T getRowsObject(String url, Class<T> clazz) {
		//int retry = 0;
		try {
			//while(retry < RETRY_COUNT) {
				JSONObject rowsJson = OkhttpUtils.getJSON(url, null);
				if(rowsJson.getInteger("code") == 0) { 
					return JSON.toJavaObject(rowsJson, clazz);
				} else {
					//retry++;
					//System.out.println("第"+retry + "次尝试!" + "请求失败：" + rowsJson.getString("code") + "-" + rowsJson.getString("message") + "请求URL为:" + url);
					System.out.println("请求失败：" + rowsJson.getString("code") + "-" + rowsJson.getString("message") + "请求URL为:" + url);
				}
			//}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
  }
	public static void main(String[] args) {
		String select = genSelect("C3027E", new String[]{"acc_status","comm_status","elec_status","engin_rotate","engine_temperature","gprs_signal","gps_power_status","gps_speed","gsm_signal","imitate1","imitate2","latitude_num","led_status","lock_status","locker_status","longitude_num","oil_level","oil_temperature","power_type","pressure_meter","realtime_duration","start_times","total_duration","wire_status","work_monitor","amaplatitude_num","amaplongitude_num","address","citycode"}
		,DateUtils.parseDate("2016-03-01 00:00:00"), DateUtils.parseDate("2016-03-31 23:59:59"));
		String aggregation  = genAggregation(new String[]{"count"});
		System.out.println(joinURL(Global.getApiDataRowsURL(),select, null, aggregation, null, null, null));
		System.out.println(joinURL(Global.getApiDataRowsURL(),select, null, null, null, null, null));
		String url = joinURL(Global.getApiDataRowsURL(),select, null, null, null, null, null);
		try {
			System.out.println(getJSON(url, null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
