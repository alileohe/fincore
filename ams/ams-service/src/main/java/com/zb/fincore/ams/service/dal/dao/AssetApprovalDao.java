package com.zb.fincore.ams.service.dal.dao;

import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.service.dal.model.AssetApproval;

import java.util.List;

public interface AssetApprovalDao {

    int selectCount(AssetApproval record);

    List<AssetApproval> select(AssetApproval record, Page page);

    int deleteByPrimaryKey(Long id);

    int insert(AssetApproval record);

    int insertSelective(AssetApproval record);

    AssetApproval selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssetApproval record);

    int updateByPrimaryKey(AssetApproval record);
}