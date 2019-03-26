package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.PageQueryRequest;

/**
 * 功能: 查询融资方列表请求对象
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/18 0018 14:44
 * 版本: V1.0
 */
public class QueryFinanceSubjectListRequest extends PageQueryRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 3738044946137780312L;

    /**
     *  融资方名称
     */
    private String subjectName;

    /**
     * 融资方编码
     */
    private String subjectCode;

    /**
     * 融资方状态
     */
    private Integer status;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
