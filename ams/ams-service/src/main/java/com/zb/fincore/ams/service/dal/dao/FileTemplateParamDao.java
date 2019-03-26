package com.zb.fincore.ams.service.dal.dao;

import com.zb.fincore.ams.service.dal.model.FileTemplateParam;

import java.util.List;
import java.util.Map;

public interface FileTemplateParamDao {

    int deleteByPrimaryKey(Long id);

    int insert(FileTemplateParam record);

    int insertSelective(FileTemplateParam record);

    FileTemplateParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileTemplateParam record);

    int updateByPrimaryKey(FileTemplateParam record);

    FileTemplateParam selectParamByTemplateCode(Map<String,Object> param);

    List<FileTemplateParam> selectParamList(FileTemplateParam param);

    int deleteByAssetCode(String assetCode);

    List<FileTemplateParam> selectByAssetCode(Map<String,Object> param);


}