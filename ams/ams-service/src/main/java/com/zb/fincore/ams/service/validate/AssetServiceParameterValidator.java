package com.zb.fincore.ams.service.validate;

import com.zb.fincore.ams.common.Constants;
import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.enums.AssetRepaymentTypeEnum;
import com.zb.fincore.ams.common.enums.AssetTypeEnum;
import com.zb.fincore.ams.facade.dto.req.CreateAssetRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetResponse;
import com.zb.fincore.common.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能: 资产服务参数校验器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 08:35
 * 版本: V1.0
 */
@Component
public class AssetServiceParameterValidator {

    public CreateAssetResponse checkCreateAssetRequest(CreateAssetRequest req) throws Exception{
        CreateAssetResponse response = BaseResponse.build(CreateAssetResponse.class);

        //融资类型
        if(null == AssetTypeEnum.getEnumItem(req.getAssetType())){
            response.setRespCode(Constants.ASSET_TYPE_NOT_NULL_CODE);
            response.setRespMsg(Constants.ASSET_TYPE_NOT_NULL_DESC);
            return  response;
        }

        //还款方式
        if(null == AssetRepaymentTypeEnum.getEnumItem(req.getRepaymentType())){
            response.setRespCode(Constants.UNKNOWN_REPAYMENT_TYPE_CODE);
            response.setRespMsg(Constants.UNKNOWN_REPAYMENT_TYPE_DESC);
            return  response;
        }

        //资产总规模大于0
        if(req.getAssetAmount().compareTo(BigDecimal.ZERO) == 0){
            response.setRespCode(Constants.ASSET_AMOUNT_GT_ZERO_CODE);
            response.setRespMsg(Constants.ASSET_AMOUNT_GT_ZERO_DESC);
            return  response;
        }

        //资产总规模范围
        if(req.getAssetAmount().compareTo(Constants.BIGGEST_ASSET_AMOUNT) > 0){
            response.setRespCode(Constants.GT_BIGGEST_ASSET_AMOUNT_CODE);
            response.setRespMsg(Constants.GT_BIGGEST_ASSET_AMOUNT_DESC);
            return  response;
        }

        //利率不能为负
        if(req.getYieldRate().compareTo(BigDecimal.ZERO) < 0){
            response.setRespCode(Constants.YIELD_RATE_GE_ZERO_CODE);
            response.setRespMsg(Constants.YIELD_RATE_GE_ZERO_DESC);
            return  response;
        }
        //成立日要小于等于起息日
        if(DateUtils.isAfter(req.getEstablishTime(),req.getValueStartTime())){
            response.setRespCode(Constants.ESTABLISH_TIME_LT_VALUE_START_TIME_CODE);
            response.setRespMsg(Constants.ESTABLISH_TIME_LT_VALUE_START_TIME_DESC);
            return  response;
        }
        //结息日大于等于起息日
        if(DateUtils.isBefore(req.getValueEndTime(), req.getValueStartTime())){
            response.setRespCode(Constants.VALUE_END_TIME_GT_VALUE_START_TIME_CODE);
            response.setRespMsg(Constants.VALUE_END_TIME_GT_VALUE_START_TIME_DESC);
            return  response;
        }
        //到期日大于等于结息日
        if(DateUtils.isBefore(req.getExpireTime(), req.getValueEndTime())){
            response.setRespCode(Constants.EXPIRE_TIME_GE_VALUE_END_TIME_CODE);
            response.setRespMsg(Constants.EXPIRE_TIME_GE_VALUE_END_TIME_DESC);
            return  response;
        }
        //期限等于结息日-起息日 + 1
//        int d = DateUtils.getBetweenDays(req.getValueStartTime(), req.getValueEndTime());
//        Integer valueDays = d + 1;
//        if(req.getValueDays() != valueDays){
//            response.setRespCode(Constants.VALUE_DAYS_EQ_END_SUB_START_CODE);
//            response.setRespMsg(Constants.VALUE_DAYS_EQ_END_SUB_START_DESC);
//            return  response;
//        }
        return null;
    }

    public List<String> checkBatchCreateAssetRequest(CreateAssetRequest req) throws Exception{
        List<String> errorList = new ArrayList<>();

        //融资类型
        if(null == AssetTypeEnum.getEnumItem(req.getAssetType())){
            errorList.add(req.getAssetName() + "-" + Constants.ASSET_TYPE_NOT_NULL_DESC);
        }

        //还款方式
        if(null == AssetRepaymentTypeEnum.getEnumItem(req.getRepaymentType())){
            errorList.add(req.getAssetName() + "-" + Constants.UNKNOWN_REPAYMENT_TYPE_DESC);
        }

        //资产总规模大于0
        if(null != req.getAssetAmount() && req.getAssetAmount().compareTo(BigDecimal.ZERO) == 0){
            errorList.add(req.getAssetName() +"-"+ Constants.ASSET_AMOUNT_GT_ZERO_DESC);
        }

        //资产总规模范围
        if(req.getAssetAmount().compareTo(Constants.BIGGEST_ASSET_AMOUNT) > 0){
            errorList.add(req.getAssetName() +"-"+ Constants.GT_BIGGEST_ASSET_AMOUNT_DESC);
        }

        //利率不能为负
        if(null !=req.getYieldRate() &&  req.getYieldRate().compareTo(BigDecimal.ZERO) < 0){
            errorList.add(req.getAssetName() +"-"+ Constants.YIELD_RATE_GE_ZERO_DESC);
        }
        //成立日要小于等于起息日
        if(DateUtils.isAfter(req.getEstablishTime(),req.getValueStartTime())){
            errorList.add(req.getAssetName() +"-"+ Constants.ESTABLISH_TIME_LT_VALUE_START_TIME_DESC);
        }
        //结息日大于起息日
        if(DateUtils.isBefore(req.getValueEndTime(), req.getValueStartTime())){
            errorList.add(req.getAssetName() +"-"+ Constants.VALUE_END_TIME_GT_VALUE_START_TIME_DESC);
        }
        //到期日大于等于起息日
        if(DateUtils.isBefore(req.getExpireTime(), req.getValueEndTime())){
            errorList.add(req.getAssetName() +"-"+ Constants.EXPIRE_TIME_GE_VALUE_END_TIME_DESC);
        }
        //期限等于结息日-起息日 + 1
        int d = DateUtils.getBetweenDays(req.getValueStartTime(), req.getValueEndTime());
        Integer valueDays = d + 1;
        if(valueDays <= 0){
            errorList.add(req.getAssetName() +"-"+Constants.VALUE_DAYS_GT_ZERO_DESC);
        }
        return errorList;
    }
}
