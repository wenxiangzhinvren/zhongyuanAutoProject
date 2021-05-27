package com.yuding.www.auto.form;

/**
 * <p>
 * 描述: 
 * </p>
 * <p>
 * 版本1.0: 2017年2月14日 新建
 * </p>
 * 
 * @author 臧其乐
 * @version 1.0
 */
public class ResponseCode {
	 private String errorType;
	    private String systemType;
	    private String funcId;
	    private String serviceType;
	    private String errorCode;

	    public ResponseCode() {
	        systemType = "01";
	    }

	    public ResponseCode(String errorType, String systemType, String funcId, String serviceType, String errorCode) {
	        this.systemType = "01";
	        this.errorType = errorType;
	        this.systemType = systemType;
	        this.funcId = funcId;
	        this.serviceType = serviceType;
	        this.errorCode = errorCode;
	    }

	    public ResponseCode(String errorType, String funcId, String serviceType, String errorCode) {
	        systemType = "01";
	        this.errorType = errorType;
	        this.funcId = funcId;
	        this.serviceType = serviceType;
	        this.errorCode = errorCode;
	    }

	    public String getErrorType() {
	        return errorType;
	    }

	    public ResponseCode setErrorType(String errorType) {
	        this.errorType = errorType;
	        return this;
	    }

	    public String getSystemType() {
	        return systemType;
	    }

	    public ResponseCode setSystemType(String systemType) {
	        this.systemType = systemType;
	        return this;
	    }

	    public String getFuncId() {
	        return funcId;
	    }

	    public ResponseCode setFuncId(String funcId) {
	        this.funcId = funcId;
	        return this;
	    }

	    public String getServiceType() {
	        return serviceType;
	    }

	    public ResponseCode setServiceType(String serviceType) {
	        this.serviceType = serviceType;
	        return this;
	    }

	    public String getErrorCode() {
	        return errorCode;
	    }

	    public ResponseCode setErrorCode(String errorCode) {
	        this.errorCode = errorCode;
	        return this;
	    }

	    public String toString() {
	        return (new StringBuilder()).append(errorType).append(systemType).append(funcId).append(serviceType)
	                .append(errorCode).toString();
	    }
}
