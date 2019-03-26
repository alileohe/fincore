package com.zb.fincore.ams.service.validate;

import com.zb.fincore.ams.common.Constants;
import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.enums.PoolTypeEnum;
import com.zb.fincore.ams.facade.dto.req.CreatePoolRequest;
import com.zb.fincore.ams.facade.dto.resp.CreatePoolResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 功能: 资产池服务参数校验器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 08:35
 * 版本: V1.0
 */
@Component
public class PoolServiceParameterValidator {

    public CreatePoolResponse checkCreatePoolRequest(CreatePoolRequest req) {
        if (null != req.getLimitAmount() && req.getLimitAmount().compareTo(BigDecimal.ZERO) < 0) {
            CreatePoolResponse response = new BaseResponse().build(CreatePoolResponse.class);
            response.setRespCode(Constants.POOL_LIMIT_AMOUNT_GT_ZERO_CODE);
            response.setRespMsg(Constants.POOL_LIMIT_AMOUNT_GT_ZERO_DESC);
            return response;
        }
        //交易结构
        if(PoolTypeEnum.getEnumItem(req.getPoolType()) == null){
            CreatePoolResponse response = new BaseResponse().build(CreatePoolResponse.class);
            response.setRespCode(Constants.ASSET_POOL_TYPE_NOT_EXIST_CODE);
            response.setRespMsg(Constants.ASSET_POOL_TYPE_NOT_EXIST_DESC);
            return  response;
        }
        return null;
    }
}
