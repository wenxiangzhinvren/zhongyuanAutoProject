package com.yuding.www.auto.form;

import java.io.Serializable;

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
public class ResultData implements Serializable{
	private static final long serialVersionUID = 1L;
	private ResultDataMap resultDataMap;
    private Object resultDataObject;

    public ResultData() {
        resultDataMap = new ResultDataMap();
        resultDataObject = new Object();
    }

    ResultDataMap getResultDataMap() {
        return resultDataMap;
    }

    public void setResultDataMap(ResultDataMap resultDataMap) {
        this.resultDataMap = resultDataMap;
    }

    Object getResultDataObject() {
        return resultDataObject;
    }

    public void setResultDataObject(Object resultDataObject) {
        this.resultDataObject = resultDataObject;
    }

    @SuppressWarnings("unchecked")
	public void put(String key, Object value) {
        resultDataMap.put(key, value);
    }
}
