package com.yuding.www.auto.form;

import java.io.Serializable;

/**
 * <p>
 * 描述:
 * </p>
 * <p>
 * 版本1.0: 2017年2月1日 新建
 * </p>
 * 
 * @author 王基鸿
 * @version 1.0
 */
public class Response  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String code;
	private String msg;
	private ResultData resultData;

	public Response() {
		code = "0";
		msg = "OK";
	}
	public Response(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public Response(Object resultData) {
		code = "0";
		msg = "OK";
		if (resultData != null) {
			if (this.resultData == null)
				this.resultData = new ResultData();
			if (resultData instanceof ResultData)
				this.resultData.setResultDataMap(((ResultData) resultData).getResultDataMap());
			else
				this.resultData.setResultDataObject(resultData);
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCode(ResponseCode code) {
		this.code = code.toString();
	}

	public void setCode(String errorType, String funcId, String serviceType, String errorCode) {
		ResponseCode responseCode = new ResponseCode(errorType, funcId, serviceType, errorCode);
		code = responseCode.toString();
	}

	public void setCode(String errorType, String systemType, String funcId, String serviceType, String errorCode) {
		ResponseCode responseCode = new ResponseCode(errorType, systemType, funcId, serviceType, errorCode);
		code = responseCode.toString();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResultData() {
		if (resultData != null) {
			if (!resultData.getResultDataMap().isEmpty())
				return resultData.getResultDataMap();
			else
				return resultData.getResultDataObject();
		} else {
			return null;
		}
	}

	public void setResultData(Object resultData) {
		if (resultData != null) {
			if (this.resultData == null)
				this.resultData = new ResultData();
			if (resultData instanceof ResultData)
				this.resultData.setResultDataMap(((ResultData) resultData).getResultDataMap());
			else
				this.resultData.setResultDataObject(resultData);
		}
	}

	public void setCodeAndMsg(ResponseCode code, String msg) {
		this.code = code.toString();
		this.msg = msg;
	}

	public void setCodeAndMsg(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public void setCodeAndMsg(Integer code, String msg) {
		this.code = code.toString();
		this.msg = msg;
	}

	public Boolean hasError() {
		return Boolean.valueOf(!code.equals("0"));
	}

	public static Response buildOk(Object resultData){
		return new Response(resultData);
	}
	public static Response buildError(Exception e){
		Response response = new Response();
		response.setCodeAndMsg(-1, e.getMessage());
		return response;
	}
}
