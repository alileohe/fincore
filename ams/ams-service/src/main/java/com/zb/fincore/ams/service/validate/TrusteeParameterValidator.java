package com.zb.fincore.ams.service.validate;

import com.zb.fincore.ams.common.Constants;
import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.facade.dto.req.CreateTrusteeRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateTrusteeResponse;
import com.zb.fincore.ams.service.dal.model.Trustee;
import com.zb.fincore.common.enums.CertTypeEnum;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 功能: 受托方服务参数校验器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 08:35
 * 版本: V1.0
 */
@Component
public class TrusteeParameterValidator {

    public CreateTrusteeResponse checkCreateTrusteeRequest(CreateTrusteeRequest req) {
        if (req.getCertType() != CertTypeEnum.SOCIAL_CREDIT_CODE.getIntValue() &&
                req.getCertType() != CertTypeEnum.BUSINESS_LICENSE.getIntValue() ) {
            CreateTrusteeResponse response = new BaseResponse().build(CreateTrusteeResponse.class);
            response.setRespCode(Constants.CERT_TYPE_ERROR_CODE);
            response.setRespMsg(Constants.CERT_TYPE_ERROR_DESC);
            return response;
        }
        if(null != req.getRegisteredCapital() && req.getRegisteredCapital().compareTo(BigDecimal.ZERO) <= 0){
            CreateTrusteeResponse response = new BaseResponse().build(CreateTrusteeResponse.class);
            response.setRespCode(Constants.REGISTERED_CAPITAL_GT_ZERO_CODE);
            response.setRespMsg(Constants.REGISTERED_CAPITAL_GT_ZERO_DESC);
            return response;
        }
        if(null != req.getCertNo() && req.getCertNo().length() != 18){
            CreateTrusteeResponse response = new BaseResponse().build(CreateTrusteeResponse.class);
            response.setRespCode(Constants.CERT_NUM_LENGTH_CODE);
            response.setRespMsg(Constants.CERT_NUM_LENGTH_DESC);
            return response;
        }
        if(null != req.getLegalPersonCertNo() && req.getLegalPersonCertNo().length() != 18){
            CreateTrusteeResponse response = new BaseResponse().build(CreateTrusteeResponse.class);
            response.setRespCode(Constants.ID_NUM_LENGTH_CODE);
            response.setRespMsg(Constants.ID_NUM_LENGTH_DESC);
            return response;
        }
        return null;
    }

    public CreateTrusteeResponse checkCreateTrusteeContent(Trustee create,Trustee origin) {
        boolean isRepeat = false;
        if(!create.getTrusteeName().equals(origin.getTrusteeName())){//受托方名称
            isRepeat = true;
        }
        if(!create.getCertNo().equals(origin.getCertNo())) {//证件类型
            isRepeat = true;
        }
        if(create.getRegisteredCapital().compareTo(origin.getRegisteredCapital()) != 0) {//注册资本
            isRepeat = true;
        }
        if(!create.getBusinessScope().equals(origin.getBusinessScope())) {//业务范围
            isRepeat = true;
        }
        if(!create.getLegalPersonCertNo().equals(origin.getLegalPersonCertNo())) {//法人代表身份证号码
            isRepeat = true;
        }
        if(!create.getLegalPersonName().equals(origin.getLegalPersonName())) {//法人代表名称
            isRepeat = true;
        }
        if(!create.getIntroduction().equals(origin.getIntroduction())) {//简介
            isRepeat = true;
        }
        if(!isRepeat){
            CreateTrusteeResponse response = new BaseResponse().build(CreateTrusteeResponse.class);
            response.setRespCode(Constants.FINANCE_SUBJECT_REPEAT_CODE);
            response.setRespMsg(Constants.FINANCE_SUBJECT_REPEAT_DESC);
            return response;
        }

        return null;
    }
}
