package com.zb.fincore.ams.facade.dto.resp;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.facade.model.ErrorModel;

import java.util.List;
import java.util.Map;

/**
 * 功能: 批量创建底层资产响应
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/26 0026 10:19
 * 版本: V1.0
 */
public class BatchCreateAssetResponse extends BaseResponse {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1391220458205045916L;

    /**
     * 总申请记录数
     */
    private int totalCount;

    /**
     * 错误记录数
     */
    private int errorCount;

    /**
     * 错误描述
     */
//    Map<String, List<String>> errorMap;

    List<ErrorModel> errorModelList;

    /**
     * 录入批次号
     */
    private String batchNo;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

//    public Map<String, List<String>> getErrorMap() {
//        return errorMap;
//    }
//
//    public void setErrorMap(Map<String, List<String>> errorMap) {
//        this.errorMap = errorMap;
//    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public List<ErrorModel> getErrorModelList() {
        return errorModelList;
    }

    public void setErrorModelList(List<ErrorModel> errorModelList) {
        this.errorModelList = errorModelList;
    }
}
