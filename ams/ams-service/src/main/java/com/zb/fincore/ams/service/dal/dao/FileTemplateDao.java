package com.zb.fincore.ams.service.dal.dao;

import com.zb.fincore.ams.service.dal.model.FileTemplate;

public interface FileTemplateDao {
    int deleteByPrimaryKey(Long id);

    int insertSelective(FileTemplate record);

    FileTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileTemplate record);

    FileTemplate selectFileTemplateByTemplateCode(String code);
}