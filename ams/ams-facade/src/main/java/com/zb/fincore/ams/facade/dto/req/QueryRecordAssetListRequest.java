package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.PageQueryRequest;

/**
 * 交易所备案查询列表请求参数
 * Created by MABIAO on 2017/6/29 0029.
 */
public class QueryRecordAssetListRequest extends PageQueryRequest{

    private String assetName;//资产名称
    private String contractStatus;//合同生成情况
    private String registerStatus;//备案状态
    private String batchNo;//录入批次号
    private String tradeInstCode;//交易所编码
    private String delistedInstCode;//摘牌方编码
    private String listedInstCode;//挂牌方编码
    private String startValueTime;//资产起息开始时间
    private String endValueTime;//资产起息结束时间

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(String registerStatus) {
        this.registerStatus = registerStatus;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getTradeInstCode() {
        return tradeInstCode;
    }

    public void setTradeInstCode(String tradeInstCode) {
        this.tradeInstCode = tradeInstCode;
    }

    public String getDelistedInstCode() {
        return delistedInstCode;
    }

    public void setDelistedInstCode(String delistedInstCode) {
        this.delistedInstCode = delistedInstCode;
    }

    public String getListedInstCode() {
        return listedInstCode;
    }

    public void setListedInstCode(String listedInstCode) {
        this.listedInstCode = listedInstCode;
    }

    public String getStartValueTime() {
        return startValueTime;
    }

    public void setStartValueTime(String startValueTime) {
        this.startValueTime = startValueTime;
    }

    public String getEndValueTime() {
        return endValueTime;
    }

    public void setEndValueTime(String endValueTime) {
        this.endValueTime = endValueTime;
    }
}
