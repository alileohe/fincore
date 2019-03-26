package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 功能: 创建资产资产池关联关系请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/21 0021 17:02
 * 版本: V1.0
 */
public class CreateAssetPoolRelationRequest extends BaseRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 8299243039471085458L;

    /**
     * 资产池编码
     */
    @NotBlank(message = "资产池编码不能为空")
    private String poolCode;

    /**
     * 创建人
     */
    @NotBlank(message = "创建人不能为空")
    private String createBy;

    /**
     * 资产编码列表
     */
    @Valid
    @NotNull(message = "资产编码列表不能为空")
    @Size(min = 1, message = "资产编码列表不能为空")
    private List<String> assetCodeList;

    public String getPoolCode() {
        return poolCode;
    }

    public void setPoolCode(String poolCode) {
        this.poolCode = poolCode;
    }

    public List<String> getAssetCodeList() {
        return assetCodeList;
    }

    public void setAssetCodeList(List<String> assetCodeList) {
        this.assetCodeList = assetCodeList;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
