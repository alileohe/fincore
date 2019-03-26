package com.zb.fincore.ams.service.dal.dao;

import com.zb.fincore.ams.service.dal.model.ExportBusinessCredit;
import com.zb.fincore.ams.service.dal.model.IssuePlan;

import java.util.List;
import java.util.Map;

public interface IssuePlanDao {

    int deleteByPrimaryKey(Long id);

    int insert(IssuePlan record);

    int insertSelective(IssuePlan record);

    IssuePlan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IssuePlan record);

    List<ExportBusinessCredit> queryPlanListForExport(Map<String,Object> param);

    IssuePlan queryIssuePlanByAssetCode(String assetCode);
}