package com.zb.fincore.ams.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.zb.fincore.ams.facade.dto.resp.RepaymentStatusQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.dto.req.BorrowerInfoRequest;
import com.zb.fincore.ams.facade.dto.req.QueryRepayStatusRequest;
import com.zb.fincore.ams.facade.dto.resp.BorrowerInfoResponse;
import com.zb.fincore.ams.facade.model.AssetRepayPlanModel;
import com.zb.fincore.ams.service.BorrowerInfoService;
import com.zb.fincore.ams.service.dal.dao.AssetProductRelationDao;
import com.zb.fincore.ams.service.dal.dao.AssetRepayPlanDao;
import com.zb.fincore.ams.service.dal.model.AssetRepayPlan;
import com.zb.fincore.common.utils.BeanUtils;

/**
 * 功能: 借款人服务接口实现
 * 创建: lijun@zillionfortune.com
 * 日期: 2017/8/30 0019 11:26
 * 版本: V1.0
 */
@Service
public class BorrowerInfoServiceImpl implements BorrowerInfoService {

	@Autowired
	private AssetRepayPlanDao assetRepayPlanDao;
	
	@Autowired
	private AssetProductRelationDao assetProductRelationDao;
	
	@Override
	public QueryResponse<AssetRepayPlanModel> identityInfoQuery(@Valid BorrowerInfoRequest req) throws Exception {
		QueryResponse<AssetRepayPlanModel> resp = BaseResponse.build(QueryResponse.class);
		AssetRepayPlan assetRepayPlan = assetRepayPlanDao.selectByAssetCode(req.getAssetCode());
		if(assetRepayPlan != null){
			AssetRepayPlanModel data = BeanUtils.copyAs(assetRepayPlan, AssetRepayPlanModel.class);
			resp.setData(data);
		}
		return resp;
	}

	@Override
	public RepaymentStatusQueryResponse<AssetRepayPlanModel> repaymentStatusQuery(@Valid QueryRepayStatusRequest req) throws Exception {
        RepaymentStatusQueryResponse<AssetRepayPlanModel> resp = BaseResponse.build(RepaymentStatusQueryResponse.class);
		List<AssetRepayPlan> assetRepayPlanList = assetRepayPlanDao.selectRepayStatus(req.getProductCode());
		if(assetRepayPlanList != null){
			List<AssetRepayPlanModel> dataList = new ArrayList<AssetRepayPlanModel>();
			for (int i = 0; i < assetRepayPlanList.size(); i++) {
				AssetRepayPlan temp = assetRepayPlanList.get(i);
				AssetRepayPlanModel data = BeanUtils.copyAs(temp, AssetRepayPlanModel.class);
				dataList.add(data);
			}
			resp.setData(dataList);
		}
		return resp;
	}

}
