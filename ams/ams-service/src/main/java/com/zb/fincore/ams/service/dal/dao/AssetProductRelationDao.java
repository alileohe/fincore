package com.zb.fincore.ams.service.dal.dao;

import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.service.dal.model.AssetProductRelation;

import java.util.List;

public interface AssetProductRelationDao {

    int selectCount(AssetProductRelation record);

    List<AssetProductRelation> select(AssetProductRelation record, Page page);

    AssetProductRelation selectOne(AssetProductRelation record);

    int deleteByPrimaryKey(Long id);

    int insert(AssetProductRelation record);

    int insertSelective(AssetProductRelation record);

    AssetProductRelation selectByPrimaryKey(Long id);

    int updateSelectiveWithLock(AssetProductRelation record);

    int updateByPrimaryKey(AssetProductRelation record);
}