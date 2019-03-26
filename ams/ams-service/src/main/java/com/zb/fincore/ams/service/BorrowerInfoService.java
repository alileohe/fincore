package com.zb.fincore.ams.service;

import java.util.List;

import javax.validation.Valid;

import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.dto.req.BorrowerInfoRequest;
import com.zb.fincore.ams.facade.dto.req.QueryRepayStatusRequest;
import com.zb.fincore.ams.facade.dto.resp.BorrowerInfoResponse;
import com.zb.fincore.ams.facade.dto.resp.RepaymentStatusQueryResponse;
import com.zb.fincore.ams.facade.model.AssetRepayPlanModel;

/**
 * 功能: 借款人服务接口
 * 创建: lijung@zillionfortune.com
 * 日期: 2017/8/30 0018 13:39
 * 版本: V1.0
 */
public interface BorrowerInfoService {

    /**
     * 借款人基本信息查询
     * @param req
     * @return
     * @throws Exception
     */
	QueryResponse<AssetRepayPlanModel> identityInfoQuery(@Valid BorrowerInfoRequest req) throws Exception;
	
	/**
     * 借款人还款状态查询
     * @param req
     * @return
     * @throws Exception
     */
    RepaymentStatusQueryResponse<AssetRepayPlanModel> repaymentStatusQuery(@Valid QueryRepayStatusRequest req) throws Exception;

}
