package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.PageQueryRequest;

/**
 * 功能: 查询资产列表请求对象
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 09:35
 * 版本: V1.0
 */
public class QueryAssetListForManageRequest extends PageQueryRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -2302883417272275442L;

    /**
     * 资产编码
     */
    private String assetCode;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 资产类型
     */
    private Integer assetType;

    /**
     * 募集状态
     */
    private Integer collectStatus;

    /**
     * 审核状态
     */
    private Integer approvalStatus;

    /**
     * 融资方编码
     */
    private String financeSubjectCode;

    /**
     * 提交审核开始时间
     */
    private String beginCreateTime;

    /**
     * 提交审核结束时间
     */
    private String endCreateTime;

    /**
     * 审核开始时间
     */
    private String beginApprovalTime;

    /**
     * 审核结束时间
     */
    private String endApprovalTime;


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

    public Integer getAssetType() {
        return assetType;
    }

    public void setAssetType(Integer assetType) {
        this.assetType = assetType;
    }

    public String getEndApprovalTime() {
        return endApprovalTime;
    }

    public void setEndApprovalTime(String endApprovalTime) {
        this.endApprovalTime = endApprovalTime;
    }

    public String getFinanceSubjectCode() {
        return financeSubjectCode;
    }

    public void setFinanceSubjectCode(String financeSubjectCode) {
        this.financeSubjectCode = financeSubjectCode;
    }

    public String getBeginCreateTime() {
        return beginCreateTime;
    }

    public void setBeginCreateTime(String beginCreateTime) {
        this.beginCreateTime = beginCreateTime;
    }

    public String getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(String endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public String getBeginApprovalTime() {
        return beginApprovalTime;
    }

    public void setBeginApprovalTime(String beginApprovalTime) {
        this.beginApprovalTime = beginApprovalTime;
    }

    public Integer getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(Integer collectStatus) {
        this.collectStatus = collectStatus;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
