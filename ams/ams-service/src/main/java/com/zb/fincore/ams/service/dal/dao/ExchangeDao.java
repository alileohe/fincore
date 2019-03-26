package com.zb.fincore.ams.service.dal.dao;

import com.zb.fincore.ams.service.dal.model.Exchange;

public interface ExchangeDao {
    int deleteByPrimaryKey(Long id);

    int insert(Exchange record);

    int insertSelective(Exchange record);

    Exchange selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Exchange record);

    int updateByPrimaryKey(Exchange record);

    Exchange selectByCode(String code);
}