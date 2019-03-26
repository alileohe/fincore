package com.zb.fincore.ams.service.validate;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.zb.fincore.ams.common.Constants;
import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.enums.ChangeAssetProductTypeEnum;
import com.zb.fincore.ams.facade.dto.req.CreateAssetProductRelationRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetProductRelationResponse;
import com.zb.fincore.ams.facade.model.ChangeAssetProductModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 功能: 资产产品匹配服务参数校验器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 08:35
 * 版本: V1.0
 */
@Component
public class AssetProductServiceParameterValidator {

    public BaseResponse checkAssetProductRelationRequest(ChangeAssetProductModel model) {
        if(StringUtils.isBlank(model.getAssetCode())){
            return BaseResponse.build(Constants.ASSET_CODE_NOT_NULL_CODE,Constants.ASSET_CODE_NOT_NULL_DESC);
        }
        if(StringUtils.isBlank(model.getPoolCode())){
            return BaseResponse.build(Constants.POOL_CODE_NOT_NULL_CODE,Constants.POOL_CODE_NOT_NULL_DESC);
        }
        if(null == model.getChangeType() || ChangeAssetProductTypeEnum.getItem(model.getChangeType()) == null){
            return BaseResponse.build(Constants.CHANGE_ASSET_PRODUCT_TYPE_CODE,Constants.CHANGE_ASSET_PRODUCT_TYPE_DESC);
        }
        if(model.getChangeAmount() == null || model.getChangeAmount().compareTo(BigDecimal.ZERO) < 0){
            return BaseResponse.build(Constants.CHANGE_ASSET_PRODUCT_AMOUNT_CODE,Constants.CHANGE_ASSET_PRODUCT_AMOUNT_DESC);
        }

        return null;
    }

    public BaseResponse checkReleaseAssetForProductUnSaleRequest(ChangeAssetProductModel model) {
        if(StringUtils.isBlank(model.getAssetCode())){
            return BaseResponse.build(Constants.ASSET_CODE_NOT_NULL_CODE,Constants.ASSET_CODE_NOT_NULL_DESC);
        }
        if(StringUtils.isBlank(model.getPoolCode())){
            return BaseResponse.build(Constants.POOL_CODE_NOT_NULL_CODE,Constants.POOL_CODE_NOT_NULL_DESC);
        }

        return null;
    }

    public CreateAssetProductRelationResponse checkAssetProductRelationP2PRequest(CreateAssetProductRelationRequest req) {
        if(StringUtils.isBlank(req.getProductCode())){
            return BaseResponse.build(CreateAssetProductRelationResponse.class,Constants.PRODUCT_CODE_NOT_NULL_CODE,Constants.PRODUCT_CODE_NOT_NULL_DESC);
        }
        if(StringUtils.isBlank(req.getPoolCode())){
            return BaseResponse.build(CreateAssetProductRelationResponse.class,Constants.POOL_CODE_NOT_NULL_CODE,Constants.POOL_CODE_NOT_NULL_DESC);
        }

        return null;
    }

}
