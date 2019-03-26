package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import com.zb.fincore.ams.facade.model.BusinessCreditModel;
import com.zb.fincore.ams.facade.model.IssuePlanModel;

import java.util.List;

/**
 * 功能：批量导入请求参数
 * Created by MABIAO on 2017/6/15 0015.
 */
public class ImportBatchAssetRequest extends BaseRequest {

    private List<IssuePlanModel> planModelList;

    private BusinessCreditModel creditModel;

    public List<IssuePlanModel> getPlanModelList() {
        return planModelList;
    }

    public void setPlanModelList(List<IssuePlanModel> planModelList) {
        this.planModelList = planModelList;
    }

    public BusinessCreditModel getCreditModel() {
        return creditModel;
    }

    public void setCreditModel(BusinessCreditModel creditModel) {
        this.creditModel = creditModel;
    }
}
