package com.zb.fincore.ams.service.dal.dao;

import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.service.dal.model.AssetPoolRelation;

import java.util.List;
import java.util.Map;

public interface AssetPoolRelationDao {

    int selectCount(AssetPoolRelation record);

    List<AssetPoolRelation> select(AssetPoolRelation record, Page page);

    AssetPoolRelation selectOne(AssetPoolRelation record);

    List<AssetPoolRelation> selectDetail(AssetPoolRelation record, Page page);

    int deleteByPrimaryKey(Long id);

    int insert(AssetPoolRelation record);

    int insertSelective(AssetPoolRelation record);

    AssetPoolRelation selectByPrimaryKey(Long id);

    int updateSelectiveWithLock(AssetPoolRelation record);

    int updateByPrimaryKey(AssetPoolRelation record);

    int insertList(List<AssetPoolRelation> records);

    List<AssetPoolRelation> selectAvailableAsset(Map map);
}