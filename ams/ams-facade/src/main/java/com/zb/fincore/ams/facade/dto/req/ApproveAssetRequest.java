package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 功能: 资产审核请求对象
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 11:27
 * 版本: V1.0
 */
public class ApproveAssetRequest extends BaseRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -6345687223448572456L;

    /**
     * 资产编码
     */
    @NotBlank(message = "资产编码不能为空")
    private String assetCode;

    /**
     * 审核人
     */
    @NotBlank(message = "审核人不能为空")
    private String approvalBy;

    /**
     * 签字级别
     */
    @NotBlank(message = "审核人签字级别不能为空")
    private String sign;

    /**
     * 审核状态
     */
    @NotNull(message = "审核状态不能为空")
    private Integer approvalStatus;

    /**
     * 审核意见
     */
    private String approvalSuggestion;

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getApprovalBy() {
        return approvalBy;
    }

    public void setApprovalBy(String approvalBy) {
        this.approvalBy = approvalBy;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalSuggestion() {
        return approvalSuggestion;
    }

    public void setApprovalSuggestion(String approvalSuggestion) {
        this.approvalSuggestion = approvalSuggestion;
    }
}
