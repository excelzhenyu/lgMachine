package com.k2data.platform.common.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.k2data.platform.common.utils.ErrorCodeUtil.ErrorType;

/**
 * 操作结果对象
 * 
 * @author yyj
 * @version 1.0
 * */
public final class K2Result implements Serializable{

	private static final long serialVersionUID = 1L; 
	
	private Boolean success = true;  		// 返回结果 true 对应的errCode为100 表示成功 false 表示失败对应的errCode为非100
	private int errCode = 100; 				// 取值为100 表示成功，其他均为失败，具体失败项目参见错误代码表
	private String msg = "操作成功";       	// 提示消息，与errCode的错误代码表对应
	private Map<String, Object> datas = new HashMap<String, Object>(); // 返回的结果数据

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public Boolean getSuccess() {
		return success;
	}
	
	public void setSuccess(Boolean success) {
		this.success = success;
		if (success) {
			errCode = 100;
			setMsg("操作成功！"); 
		} else {
			errCode = 0;
			setMsg("操作失败！"); 
		}
	}
	
	public int getErrCode() {
		return errCode;
	}
	
	public K2Result setErrInfo(ErrorType errorType) {
		this.errCode = errorType.errorCode;
		this.msg = errorType.msg;
		if (100 == errCode) {
			this.success = true;
		} else {
			this.success = false;
		}
		return this;
	}
	
	public String getMsg() {
		if((!this.success )&& this.errCode ==100){
			msg = "操作失败！";
		}
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public void setErrorMsg(String msg, int errCode) {
		this.msg = msg;
		this.success = false;
		this.errCode = errCode;
	}
	
	public K2Result setErrorMsg(String msg) {
		this.msg = msg;
		this.success = false;
		errCode = 0;
		return this;
	}
	
	public Map<String, Object> getDatas() {
		return datas;
	}
	
	public void setDatas(Map<String, Object> datas) {
		this.datas = datas;
	}
	
	/**
	 * 添加结果对象
	 * 
	 * @param key
	 * @param val
	 */
	public void putObject(String key, Object val) {
		datas.put(key, val);
	}
	
	/**
	 * Description : 添加结果对象MAP
	 * 
	 * @param datas
	 */
	public void addMapDatas(Map<String, Object> datas) {
		this.datas.putAll(datas);
	}
	
	public String toJson(){
		return JSON.toJSONString(this);
	}
	
}
