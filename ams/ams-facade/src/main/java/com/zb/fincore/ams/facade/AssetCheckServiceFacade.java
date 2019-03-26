package com.zb.fincore.ams.facade;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.facade.dto.req.QueryAssetTransactionRequest;
import com.zb.fincore.ams.facade.dto.req.QueryNotInProcessAssetRequest;
import com.zb.fincore.ams.facade.model.AssetTransactionModel;
import com.zb.fincore.ams.facade.model.InProcessAssetModel;
import com.zb.fincore.ams.facade.model.NotInProcessAssetModel;

public interface AssetCheckServiceFacade {
    PageQueryResponse<InProcessAssetModel> queryInProcessAsset();
    
    PageQueryResponse<NotInProcessAssetModel> queryNotInProcessAsset(QueryNotInProcessAssetRequest req);
    
    PageQueryResponse<AssetTransactionModel> queryAssetTransaction(QueryAssetTransactionRequest req);
    
}
