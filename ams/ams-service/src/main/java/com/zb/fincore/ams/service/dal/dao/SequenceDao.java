package com.zb.fincore.ams.service.dal.dao;

import com.zb.fincore.ams.service.dal.model.Sequence;

public interface SequenceDao {
    int deleteByPrimaryKey(Long id);

    int insert(Sequence record);

    int insertSelective(Sequence record);

    Sequence selectByPrimaryKey(Long id);

    Sequence selectByName(String sequenceName);

    int updateValWithLock(Sequence record);
}