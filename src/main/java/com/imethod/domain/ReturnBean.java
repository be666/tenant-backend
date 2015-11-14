package com.imethod.domain;

import java.util.Map;

/**
 * time : 15/11/14.
 * auth : jqwang
 * desc :
 * tips :
 * 1.
 */
public class ReturnBean {

    public static  final Boolean TRUE = true;
    public static  final Boolean FALSE = false ;

    private Boolean status = TRUE;

    private String msg ;

    private Map<String,Object> dataMap ;

    public ReturnBean(Boolean status,String msg,Map<String,Object> dataMap) {
        this.status = status;
        this.msg = msg;
        this.dataMap = dataMap;
    }

    public ReturnBean(Boolean status,String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ReturnBean() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
}
