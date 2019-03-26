package com.zb.fincore.ams.service.dal.dao;

import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.service.dal.model.FinanceSubject;

import java.util.List;

public interface FinanceSubjectDao {

    int selectCount(FinanceSubject record);

    List<FinanceSubject> select(FinanceSubject record, Page page);

    int deleteByPrimaryKey(Long id);

    int insert(FinanceSubject record);

    int insertSelective(FinanceSubject record);

    FinanceSubject selectByPrimaryKey(Long id);

    FinanceSubject selectByCode(String subjectCode);

    int updateByPrimaryKeySelective(FinanceSubject record);

    int updateByPrimaryKey(FinanceSubject record);

    /**
     * 查询融资方名称和证件号是否存在
     * @param record
     * @return
     */
    List<FinanceSubject> selectForUnique(FinanceSubject record);
}