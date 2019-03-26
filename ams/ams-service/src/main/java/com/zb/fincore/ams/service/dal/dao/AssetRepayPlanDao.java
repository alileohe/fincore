package com.zb.fincore.ams.service.dal.dao;

import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.service.dal.model.AssetRepayPlan;

import java.util.List;

public interface AssetRepayPlanDao {

    int selectCount(AssetRepayPlan record);

    List<AssetRepayPlan> select(AssetRepayPlan record, Page page);

    int deleteByPrimaryKey(Long id);

    int insert(AssetRepayPlan record);

    int insertSelective(AssetRepayPlan record);

    AssetRepayPlan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssetRepayPlan record);

    int updateByPrimaryKey(AssetRepayPlan record);
    
    AssetRepayPlan selectByAssetCode(String assetCode);
    
    List<AssetRepayPlan> selectRepayStatus(String productCode);

}