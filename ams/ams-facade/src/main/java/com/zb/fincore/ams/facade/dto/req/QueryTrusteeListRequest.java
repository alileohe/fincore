package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.PageQueryRequest;

/**
 * 功能: 查询受托方列表请求对象
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 17:07
 * 版本: V1.0
 */
public class QueryTrusteeListRequest extends PageQueryRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 571481723309665335L;

    /**
     * 受托方名称
     */
    private String trusteeName;

    /**
     * 受托方编码
     */
    private String trusteeCode;

    public String getTrusteeName() {
        return trusteeName;
    }

    public void setTrusteeName(String trusteeName) {
        this.trusteeName = trusteeName;
    }

    public String getTrusteeCode() {
        return trusteeCode;
    }

    public void setTrusteeCode(String trusteeCode) {
        this.trusteeCode = trusteeCode;
    }
}
