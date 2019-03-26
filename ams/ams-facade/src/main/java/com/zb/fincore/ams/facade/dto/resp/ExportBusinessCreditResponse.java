package com.zb.fincore.ams.facade.dto.resp;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.facade.model.ExportBusinessCreditModel;

import java.io.OutputStream;
import java.util.List;

/**
 *  导出授信表响应
 * Created by MABIAO on 2017/6/26 0026.
 */
public class ExportBusinessCreditResponse extends BaseResponse {

    /**
     * 导出授信表数据
     */
    List<ExportBusinessCreditModel> modelList;

    public List<ExportBusinessCreditModel> getModelList() {
        return modelList;
    }

    public void setModelList(List<ExportBusinessCreditModel> modelList) {
        this.modelList = modelList;
    }
}
