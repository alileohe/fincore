package com.zb.fincore.ams.service.validate;

import com.zb.fincore.ams.common.Constants;
import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.enums.AssetTypeEnum;
import com.zb.fincore.ams.facade.dto.req.CreateAssetForP2PRequest;
import com.zb.fincore.ams.facade.dto.req.CreateAssetRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


/**
 * 功能: 资产服务参数校验器
 * 日期: 2017/4/20 0020 08:35
 * 版本: V1.0
 */
@Component
public class AssetForP2PServiceParameterValidator {

    public BaseResponse checkCreateAssetRequest(CreateAssetForP2PRequest req) throws Exception{

        if(req.getAssetAmount().compareTo(BigDecimal.ZERO) <= 0 || req.getAssetAmount().compareTo(Constants.P2P_LIMIT_ASSET_AMOUNT) > 0){
            return BaseResponse.build(Constants.P2P_LIMIT_ASSET_AMOUNT_CODE, Constants.P2P_LIMIT_ASSET_AMOUNT_DESC);
        }
        return null;
    }

}
