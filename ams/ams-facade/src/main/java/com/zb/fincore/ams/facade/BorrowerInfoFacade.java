package com.zb.fincore.ams.facade;

import java.util.List;

import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.dto.req.BorrowerInfoRequest;
import com.zb.fincore.ams.facade.dto.req.QueryRepayStatusRequest;
import com.zb.fincore.ams.facade.dto.resp.RepaymentStatusQueryResponse;
import com.zb.fincore.ams.facade.model.AssetRepayPlanModel;

/**
 * 功能: 借款人服务接口
 * 创建: lijun@zillionfortune.com
 * 日期: 2017/8/23 0018 17:31
 * 版本: V1.0
 */
public interface BorrowerInfoFacade {

    /**
     * 证件信息查询
     * @param req
     * @return
     */
	QueryResponse<AssetRepayPlanModel> identityInfoQuery(BorrowerInfoRequest req);
	
	/**
     * 还款状态查询
     * @param req
     * @return
     */
    RepaymentStatusQueryResponse<AssetRepayPlanModel> repaymentStatusQuery(QueryRepayStatusRequest req);

}
