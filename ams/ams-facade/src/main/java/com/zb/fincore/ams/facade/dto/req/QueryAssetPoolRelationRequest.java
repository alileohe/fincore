package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.PageQueryRequest;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 功能: 查询资产资产池关联关系请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/24 0024 16:41
 * 版本: V1.0
 */
public class QueryAssetPoolRelationRequest extends PageQueryRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -4306048264253939604L;

    /**
     * 资产池编号
     */
    @NotBlank(message = "资产池编码不能为空")
    private String poolCode;

    public String getPoolCode() {
        return poolCode;
    }

    public void setPoolCode(String poolCode) {
        this.poolCode = poolCode;
    }
}
