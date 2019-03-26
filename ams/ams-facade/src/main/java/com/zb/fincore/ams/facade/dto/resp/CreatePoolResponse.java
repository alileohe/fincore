package com.zb.fincore.ams.facade.dto.resp;

import com.zb.fincore.ams.common.dto.BaseResponse;

/**
 * 功能: 创建底层资产响应
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 11:18
 * 版本: V1.0
 */
public class CreatePoolResponse extends BaseResponse {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 8169771955170716124L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 资产池编码
     */
    private String poolCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoolCode() {
        return poolCode;
    }

    public void setPoolCode(String poolCode) {
        this.poolCode = poolCode;
    }
}
