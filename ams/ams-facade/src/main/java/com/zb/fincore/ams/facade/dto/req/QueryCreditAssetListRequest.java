package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.PageQueryRequest;

/**
 * 授信资产录入审核列表请求参数
 * Created by MABIAO on 2017/6/28 0028.
 */
public class QueryCreditAssetListRequest extends PageQueryRequest {

    private String valueStartTime;//起息日YYYY-MM-DD

    private String valueEndTime;//结息日

    private Integer repaymentType;//还款方式

    private String approvalLevel;//审核等级

    private String sign;//审核签名

    public String getValueStartTime() {
        return valueStartTime;
    }

    public void setValueStartTime(String valueStartTime) {
        this.valueStartTime = valueStartTime;
    }

    public String getValueEndTime() {
        return valueEndTime;
    }

    public void setValueEndTime(String valueEndTime) {
        this.valueEndTime = valueEndTime;
    }

    public String getApprovalLevel() {
        return approvalLevel;
    }

    public void setApprovalLevel(String approvalLevel) {
        this.approvalLevel = approvalLevel;
    }

    public Integer getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(Integer repaymentType) {
        this.repaymentType = repaymentType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
