package com.zb.fincore.common.rpc.dto;

import com.zb.fincore.common.enums.ResultCodeEnum;

import java.io.Serializable;

import static org.apache.commons.lang3.reflect.MethodUtils.invokeMethod;

public class BaseResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String resultCode = ResultCodeEnum.SUCCESS.code();//业务处理结果编码

    protected String resultMsg = ResultCodeEnum.SUCCESS.desc();//业务处理结果描述

    public BaseResponse() {
    }

    public BaseResponse(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    /**
     * 构建RPC响应对象
     *
     * @param clazz 类
     * @param <T>   RPC响应对象类型
     * @return RPC响应对象
     */
    public static <T extends BaseResponse> T build(Class clazz) {
        Object object = null;
        try {
            object = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) object;
    }

    /**
     * 构建RPC响应对象
     *
     * @param clazz    类
     * @param respCode 业务响应码
     * @param respMsg  业务响应描述
     * @param <T>      RPC响应对象类型
     * @return RPC响应对象类型
     */
    public static <T extends BaseResponse> T build(Class clazz, String respCode, String respMsg) {
        Object object = null;
        try {
            object = clazz.newInstance();
            invokeMethod(clazz, "setRespCode", new Class[]{String.class}, object, respCode);
            invokeMethod(clazz, "setRespMsg", new Class[]{String.class}, object, respMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) object;
    }

    public static BaseResponse build() {
        return new BaseResponse();
    }

    public static BaseResponse build( String resultCode, String resultMsg) {
        return new BaseResponse( resultCode, resultMsg);
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
