package com.zb.fincore.ams.facade.dto.resp;

import com.zb.fincore.ams.common.dto.BaseResponse;

/**
 * 功能: 创建融资主体响应
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/18 0018 14:10
 * 版本: V1.0
 */
public class CreateFinanceSubjectResponse extends BaseResponse {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 3918859576168207562L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 融资主体编码
     */
    private String subjectCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
}
