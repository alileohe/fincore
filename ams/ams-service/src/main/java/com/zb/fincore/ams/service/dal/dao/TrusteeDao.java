package com.zb.fincore.ams.service.dal.dao;

import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.service.dal.model.Trustee;

import java.util.List;

public interface TrusteeDao {

    int selectCount(Trustee record);

    List<Trustee> select(Trustee record, Page page);

    int deleteByPrimaryKey(Long id);

    int insert(Trustee record);

    int insertSelective(Trustee record);

    Trustee selectByPrimaryKey(Long id);

    Trustee selectByCode(String trusteeCode);

    int updateByPrimaryKeySelective(Trustee record);

    int updateByPrimaryKey(Trustee record);

    /**
     * 查询受托方名称或证件号是否存在
     */
    List<Trustee> selectForUnique(Trustee record);
}