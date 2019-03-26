package com.zb.fincore.ams.facade.impl;

import java.util.List;

import com.zb.fincore.ams.facade.dto.resp.RepaymentStatusQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.common.exception.ExceptionHandler;
import com.zb.fincore.ams.facade.BorrowerInfoFacade;
import com.zb.fincore.ams.facade.dto.req.BorrowerInfoRequest;
import com.zb.fincore.ams.facade.dto.req.QueryRepayStatusRequest;
import com.zb.fincore.ams.facade.dto.resp.BorrowerInfoResponse;
import com.zb.fincore.ams.facade.model.AssetRepayPlanModel;
import com.zb.fincore.ams.service.BorrowerInfoService;

/**
 * 功能: 借款人服务接口实现
 * 创建: lijun@zillionfortune.com
 * 日期: 2017/4/18 0018 13:34
 * 版本: V1.0
 */
@Service
public class BorrowerInfoFacadeImpl implements BorrowerInfoFacade {

    @Autowired
    private BorrowerInfoService borrowerInfoService;

    @Autowired
    private ExceptionHandler exceptionHandler;
    
    
	@Override
	public QueryResponse<AssetRepayPlanModel> identityInfoQuery(BorrowerInfoRequest req) {
		try {
            return borrowerInfoService.identityInfoQuery(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, QueryResponse.class);
        }
	}

	@Override
	public RepaymentStatusQueryResponse<AssetRepayPlanModel> repaymentStatusQuery(QueryRepayStatusRequest req) {
		try {
            return borrowerInfoService.repaymentStatusQuery(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, RepaymentStatusQueryResponse.class);
        }
	}
   
}
