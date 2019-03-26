package com.zb.fincore.ams.service;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.facade.dto.req.QueryAssetTransactionRequest;
import com.zb.fincore.ams.facade.dto.req.QueryNotInProcessAssetRequest;
import com.zb.fincore.ams.facade.model.AssetTransactionModel;
import com.zb.fincore.ams.facade.model.InProcessAssetModel;
import com.zb.fincore.ams.facade.model.NotInProcessAssetModel;

public interface AssetCheckService {
	public PageQueryResponse<InProcessAssetModel> queryInProcessAssetList() throws Exception;
    
	public PageQueryResponse<NotInProcessAssetModel> queryNotInProcessAssetList(QueryNotInProcessAssetRequest req)
			throws Exception;
	
	public PageQueryResponse<AssetTransactionModel> queryAssetTransaction(QueryAssetTransactionRequest req) throws Exception;
}
