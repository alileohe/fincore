package com.zb.fincore.ams.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.exception.ExceptionHandler;
import com.zb.fincore.ams.facade.AssetCheckServiceFacade;
import com.zb.fincore.ams.facade.dto.req.QueryAssetTransactionRequest;
import com.zb.fincore.ams.facade.dto.req.QueryNotInProcessAssetRequest;
import com.zb.fincore.ams.facade.model.AssetTransactionModel;
import com.zb.fincore.ams.facade.model.InProcessAssetModel;
import com.zb.fincore.ams.facade.model.NotInProcessAssetModel;
import com.zb.fincore.ams.service.AssetCheckService;

@Service
public class AssetCheckServiceFacadeImpl implements AssetCheckServiceFacade {
	
    @Autowired
    private ExceptionHandler exceptionHandler;
    
    @Autowired
    private AssetCheckService assetCheckService;
    
	@Override
	public PageQueryResponse<InProcessAssetModel> queryInProcessAsset() {
        try {
            return assetCheckService.queryInProcessAssetList();
        } catch (Exception e) {
            return exceptionHandler.handleException(e, PageQueryResponse.class);
        }
	}
	
	@Override
	public PageQueryResponse<NotInProcessAssetModel> queryNotInProcessAsset(QueryNotInProcessAssetRequest req) {
        try {
            return assetCheckService.queryNotInProcessAssetList(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, PageQueryResponse.class);
        }
	}
	
	@Override
	public PageQueryResponse<AssetTransactionModel> queryAssetTransaction(QueryAssetTransactionRequest req) {
        try {
            return assetCheckService.queryAssetTransaction(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, PageQueryResponse.class);
        }
	}
}
