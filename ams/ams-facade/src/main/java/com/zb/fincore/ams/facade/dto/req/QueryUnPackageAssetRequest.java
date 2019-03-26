package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.PageQueryRequest;

/**
 * 授信资产生成合同列表查询请求参数
 * Created by MABIAO on 2017/6/29 0029.
 */
public class QueryUnPackageAssetRequest extends PageQueryRequest {

    private String assetCode;//资产编码
    private String assetName;//资产名称
    private String financeName;//融资方名称
    private String approvalStartTime;//审核通过开始时间
    private String approvalEndTime;//审核通过结束时间
    private String contractStatus;//合同生成情况

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getFinanceName() {
        return financeName;
    }

    public void setFinanceName(String financeName) {
        this.financeName = financeName;
    }

    public String getApprovalStartTime() {
        return approvalStartTime;
    }

    public void setApprovalStartTime(String approvalStartTime) {
        this.approvalStartTime = approvalStartTime;
    }

    public String getApprovalEndTime() {
        return approvalEndTime;
    }

    public void setApprovalEndTime(String approvalEndTime) {
        this.approvalEndTime = approvalEndTime;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }
}
