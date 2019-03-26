package com.zb.fincore.ams.service.dal.dao;

import com.zb.fincore.ams.service.dal.model.Asset;
import com.zb.fincore.ams.service.dal.model.AssetIssueRelation;

import java.util.List;

public interface AssetIssueRelationDao {
    int insert(AssetIssueRelation record);

    int insertSelective(AssetIssueRelation record);

    List<Asset> selectApprovaledAssetByCode(String assetCode);

    List<Asset> selectUnApprovalAssetByCode(String assetCode);

    int updateAssetByCode(String assetCode);

    int updateAssetApprovalFail(String assetCode);
}