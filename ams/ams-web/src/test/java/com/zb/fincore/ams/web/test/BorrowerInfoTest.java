package com.zb.fincore.ams.web.test;

import java.util.List;

import com.zb.fincore.ams.facade.dto.resp.RepaymentStatusQueryResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.BorrowerInfoFacade;
import com.zb.fincore.ams.facade.dto.req.BorrowerInfoRequest;
import com.zb.fincore.ams.facade.dto.req.QueryRepayStatusRequest;
import com.zb.fincore.ams.facade.model.AssetRepayPlanModel;

/**
 * 功能: 资产RESTFUL接口
 * 创建: lijun@zillionfortune.com
 * 日期: 2017/4/19 0019 11:25
 * 版本: V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BorrowerInfoTest {

    @Autowired
    private BorrowerInfoFacade borrowerInfoFacade;


    /**
     * 借款人证件信息查询
     */
    @Test
    public void identityInfoQuery() {
    	BorrowerInfoRequest req = new BorrowerInfoRequest();
    	req.setAssetCode("AMA170800113");
        QueryResponse<AssetRepayPlanModel> response = borrowerInfoFacade.identityInfoQuery(req);
        System.out.println(response.getRespMsg());
    }

    /**
     * 借款人信息查询
     */
    @Test
    public void repayStatusQuery() {
    	QueryRepayStatusRequest req = new QueryRepayStatusRequest();
    	req.setProductCode("P2017050037");
        RepaymentStatusQueryResponse<AssetRepayPlanModel> response = borrowerInfoFacade.repaymentStatusQuery(req);
        System.out.println(response.getRespMsg());
    }

}
