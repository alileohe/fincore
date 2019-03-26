package com.zb.fincore.ams.service.validate;

import com.zb.fincore.ams.common.Constants;
import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.facade.dto.req.CreateFileTemplateParamRequest;
import com.zb.fincore.ams.facade.dto.req.ImportBatchAssetRequest;
import com.zb.fincore.ams.facade.dto.resp.BatchCreateAssetResponse;
import org.springframework.stereotype.Component;

/**
 * 功能: 批量录入服务参数校验器
 */
@Component
public class BatchAssetParameterValidator {

    /**
     * 校验导入文件参数
     * @param req
     * @return
     */
    public BaseResponse checkImportBatchAssetRequest(ImportBatchAssetRequest req) {
        BaseResponse response = new BaseResponse().build();

        if(null == req){
            response.setRespCode(Constants.IMPORT_PARAM_NOT_NULL_CODE);
            response.setRespMsg(Constants.IMPORT_PARAM_NOT_NULL_DESC);
            return response;
        }

        if(null == req.getPlanModelList() || req.getPlanModelList().size() == 0){
            response.setRespCode(Constants.ISSUE_PLAN_NOT_NULL_CODE);
            response.setRespMsg(Constants.ISSUE_PLAN_NOT_NULL_DESC);
            return response;
        }

        if(null == req.getCreditModel()){
            response.setRespCode(Constants.BUSINESS_CREDIT_NOT_NULL_CODE);
            response.setRespMsg(Constants.BUSINESS_CREDIT_NOT_NULL_DESC);
            return response;
        }
        return null;
    }

    /**
     * 校验备案合同参数
     * @param req
     * @return
     */
    public BaseResponse checkTemplateParamRequest(CreateFileTemplateParamRequest req) {
        BaseResponse response = new BaseResponse().build();

        if(null == req){
            response.setRespCode(Constants.IMPORT_PARAM_NOT_NULL_CODE);
            response.setRespMsg(Constants.IMPORT_PARAM_NOT_NULL_DESC);
            return response;
        }

        if(null == req.getRegisterName() || "".equals(req.getRegisterName())){
            response.setRespCode(Constants.REGISTER_NAME_NOT_NULL_CODE);
            response.setRespMsg(Constants.REGISTER_NAME_NOT_NULL_DESC);
            return response;
        }

        if(null == req.getRegisterRate()){
            response.setRespCode(Constants.REGISTER_RATE_NOT_NULL_CODE);
            response.setRespMsg(Constants.REGISTER_RATE_NOT_NULL_DESC);
            return response;
        }

        if(null == req.getRegisterAmount()){
            response.setRespCode(Constants.REGISTER_AMOUNT_NOT_NULL_CODE);
            response.setRespMsg(Constants.REGISTER_AMOUNT_NOT_NULL_DESC);
            return response;
        }

        return null;
    }

}
