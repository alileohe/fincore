package com.zb.fincore.ams.web.controller;

import java.util.List;

import com.zb.fincore.ams.facade.dto.resp.RepaymentStatusQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.BorrowerInfoFacade;
import com.zb.fincore.ams.facade.dto.req.BorrowerInfoRequest;
import com.zb.fincore.ams.facade.dto.req.QueryRepayStatusRequest;
import com.zb.fincore.ams.facade.dto.resp.BorrowerInfoResponse;
import com.zb.fincore.ams.facade.model.AssetRepayPlanModel;

/**
 * 功能: 借款人信息接口
 * 创建: lijun@zillionfortune.com
 * 日期: 2017/8/30 0019 11:25
 * 版本: V1.0
 */
@RestController
@RequestMapping(value = "/borrowerService")
public class BorrowerInfoController {

    @Autowired
    private BorrowerInfoFacade borrowerInfoFacade;

    /**
     * 借款人信息查询
     * @param req
     * @return
     */
    @RequestMapping(value = "/identityInfoQuery", method = RequestMethod.POST)
    public QueryResponse<AssetRepayPlanModel> borrowerInfoQuery(@RequestBody BorrowerInfoRequest req) {
        return borrowerInfoFacade.identityInfoQuery(req);
    }
    
    /**
     * 借款人还款状态查询
     * @param req
     * @return
     */
    @RequestMapping(value = "/repaymentStatusQuery", method = RequestMethod.POST)
    public RepaymentStatusQueryResponse<AssetRepayPlanModel> repaymentStatusQuery(@RequestBody QueryRepayStatusRequest req) {
        return borrowerInfoFacade.repaymentStatusQuery(req);
    }

}
