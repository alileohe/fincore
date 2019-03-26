package com.zb.fincore.ams.facade.dto.resp;

import java.io.Serializable;

/**
 * 功能: 创建底层资产响应For P2P
 */
public class BaseP2PResponse<T> implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 2469709391450351325L;


    /**
     * 业务响应码
     */
    private String code = "0000";

    /**
     * 业务响应描述
     */
    private String msg = "成功";

    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
