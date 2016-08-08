package com.k2data.platform.common.utils;


public class ErrorCodeUtil {

	public static class ErrorType {
		public int errorCode;
		public String msg;
		
		public ErrorType(int errorCode, String msg) {
			this.errorCode = errorCode;
			this.msg = msg;
		}
	}
	
	// public static final String USERNAME 用户名格式错误
	public static final ErrorType USERNAME_FORMAT_ERROR = new ErrorType(101, "用户名格式错误");
	
	//处理生成风模型错误提示
	public static final ErrorType DEALDLC_ERROR = new ErrorType(102, "工况转换失败");
	public static final ErrorType DEALDLC_CALCULATION_ERROR = new ErrorType(103, "CALCULATION取值为空或者不存在");
	public static final ErrorType DEALDLC_WMODE_ERROR = new ErrorType(104, "WMODE取值为空或者不存在");
	public static final ErrorType DEALDLC_WINDF_ERROR = new ErrorType(100, "WINDF未找到");
	public static final ErrorType FILE_PACKAGING__ERROR = new ErrorType(106, "文件打包错误");

	
	
	
	
	
	
	
	
	
	
	
	
	
}