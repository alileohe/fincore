package com.zb.fincore.common.rpc.dto;

/**
 * Created by niuzhanjun on 2017/2/27 0027.
 */
public class BaseCheckResult {
    private boolean checkResult;
    private String resultMsg;

    public BaseCheckResult() {
        setCheckResult(true);
        setResultMsg("");
    }

    public BaseCheckResult(boolean checkResult, String resultMsg) {
        this.checkResult = checkResult;
        this.resultMsg = resultMsg;
    }

    public boolean getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(boolean checkResult) {
        this.checkResult = checkResult;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
