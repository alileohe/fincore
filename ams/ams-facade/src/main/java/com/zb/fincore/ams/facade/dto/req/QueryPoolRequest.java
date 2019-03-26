package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 功能: 查询资产池详情请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/21 0021 09:35
 * 版本: V1.0
 */
public class QueryPoolRequest extends BaseRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 6866190307517316147L;

    /**
     * 资产池编码
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
