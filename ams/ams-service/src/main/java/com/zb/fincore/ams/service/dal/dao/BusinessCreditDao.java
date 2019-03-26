package com.zb.fincore.ams.service.dal.dao;

import com.zb.fincore.ams.service.dal.model.BusinessCredit;

import java.util.List;

public interface BusinessCreditDao {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessCredit record);

    int insertSelective(BusinessCredit record);

    BusinessCredit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessCredit record);

    int updateByPrimaryKey(BusinessCredit record);

    BusinessCredit selectByBatchNo(String batchNo);

    BusinessCredit selectByAssetCode(String assetCode);

}