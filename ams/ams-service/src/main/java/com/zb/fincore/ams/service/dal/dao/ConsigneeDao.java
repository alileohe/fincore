package com.zb.fincore.ams.service.dal.dao;

import com.zb.fincore.ams.service.dal.model.Consignee;

public interface ConsigneeDao {
    int deleteByPrimaryKey(Long id);

    int insert(Consignee record);

    int insertSelective(Consignee record);

    Consignee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Consignee record);

    int updateByPrimaryKey(Consignee record);
}