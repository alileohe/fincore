package com.zb.fincore.common.rpc.dto;

import com.zb.fincore.common.enums.ResultCodeEnum;

public class CommonResponse<T extends BaseDTO> extends BaseResponse {

    private static final long serialVersionUID = 1L;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public CommonResponse() {
    }

    public CommonResponse(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public static CommonResponse build() {
        return new CommonResponse();
    }

    public static CommonResponse build( String resultCode, String resultMsg) {
        return new CommonResponse(resultCode, resultMsg);
    }

    public static CommonResponse buildNotFindRsp() {
        CommonResponse response = new CommonResponse();
        response.setResultCode(ResultCodeEnum.NOT_FOUND_INFO.code());
        response.setResultMsg(ResultCodeEnum.NOT_FOUND_INFO.desc());
        return response;
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
