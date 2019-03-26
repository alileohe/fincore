package com.zb.fincore.ams.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.exception.ExceptionHandler;
import com.zb.fincore.ams.facade.AssetForP2PFacade;
import com.zb.fincore.ams.facade.dto.req.CreateAssetForP2PRequest;
import com.zb.fincore.ams.facade.dto.req.CreateAssetProductRelationRequest;
import com.zb.fincore.ams.facade.dto.req.RelateAssetProductForP2PRequest;
import com.zb.fincore.ams.facade.dto.req.UpdateAssetStockRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetProductRelationResponse;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetResponse;
import com.zb.fincore.ams.service.AssetForP2PService;

/**
 * 功能: P2P资产服务接口
 * 创建: mabiao - mabiao@zillionfortune.com
 * 日期: 2017-9-1
 * 版本: V1.0
 */
@Service
public class AssetForP2PFacadeImpl implements AssetForP2PFacade {

    @Autowired
    private AssetForP2PService assetForP2PService;

    @Autowired
    private ExceptionHandler exceptionHandler;


    /**
     * 创建产品资产匹配关系
     */
    @Override
    public CreateAssetProductRelationResponse createAssetProductRelation(CreateAssetProductRelationRequest req) {
        try {
            return assetForP2PService.createAssetProductRelation(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, CreateAssetProductRelationResponse.class);
        }
    }

    /**
     * 创建资产
     * @param req
     * @return
     */
    @Override
    public CreateAssetResponse createAssetForP2P(CreateAssetForP2PRequest req) {
        try {
            return assetForP2PService.createAssetForP2P(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, CreateAssetResponse.class);
        }
    }

    /**
     * 资产产品匹配
     */
    @Override
    public BaseResponse saveAssetProductRelation(RelateAssetProductForP2PRequest req) {
        try {
            return assetForP2PService.saveAssetProductRelation(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

    /**
     * 更新资产库存
     */
	@Override
	public BaseResponse updateAssetStock(UpdateAssetStockRequest req) {
		try {
            return assetForP2PService.updateAssetStock(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
	}
}
