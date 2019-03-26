package com.zb.fincore.ams.service.dal.dao;

import java.util.List;

import com.zb.fincore.ams.facade.dto.req.QueryNotInProcessAssetRequest;
import com.zb.fincore.ams.facade.model.AssetTransactionModel;
import com.zb.fincore.ams.facade.model.InProcessAssetModel;
import com.zb.fincore.ams.facade.model.NotInProcessAssetModel;

public interface AssetCheckDao {
	List<InProcessAssetModel> selectInProcessAsset(); 
	
	List<NotInProcessAssetModel> selectNotInProcessAsset(QueryNotInProcessAssetRequest req); 
	
	List<AssetTransactionModel> selectAssetTransaction(); 
}
