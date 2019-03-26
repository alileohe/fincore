package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.PageQueryRequest;

/**
 * 功能: 查询资产池列表请求
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/21 0021 09:20
 * 版本: V1.0
 */
public class QueryPoolListRequest extends PageQueryRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1279800523034995199L;

    /**
     * 资产池名称
     */
    private String poolName;

    /**
     * 资产池编码
     */
    private String poolCode;

    /**
     * 资产池类型
     */
    private Integer poolType;

    /**
     * 融资方编码
     */
    private String financeSubjectCode;

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public Integer getPoolType() {
        return poolType;
    }

    public void setPoolType(Integer poolType) {
        this.poolType = poolType;
    }

    public String getPoolCode() {
        return poolCode;
    }

    public void setPoolCode(String poolCode) {
        this.poolCode = poolCode;
    }

    public String getFinanceSubjectCode() {
        return financeSubjectCode;
    }

    public void setFinanceSubjectCode(String financeSubjectCode) {
        this.financeSubjectCode = financeSubjectCode;
    }
}
