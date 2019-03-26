package com.zb.fincore.ams.service.dal.dao;

import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.service.dal.model.AssetExchangeRegister;

import java.util.List;
import java.util.Map;

public interface AssetExchangeRegisterDao {
    int deleteByPrimaryKey(Long id);

    int insert(AssetExchangeRegister record);

    int insertSelective(AssetExchangeRegister record);

    AssetExchangeRegister selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssetExchangeRegister record);

    int updateByPrimaryKey(AssetExchangeRegister record);

    AssetExchangeRegister selectByAssetCode(String assetCode);

    List<AssetExchangeRegister> selectExchangeRegisterListByAssetCode(Map<String,Object> param,Page page);

    int selectExchangeRegisterListByAssetCodeCount(Map<String,Object> param);

    int selectByAssetRegisterName(AssetExchangeRegister isRePeat);
}