package com.zb.fincore.ams.web.test;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.facade.AssetApprovalServiceFacade;
import com.zb.fincore.ams.facade.dto.req.ApproveAssetRequest;
import com.zb.fincore.ams.facade.dto.req.QueryAssetApprovalListRequest;
import com.zb.fincore.ams.facade.model.AssetApprovalModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能: 资产审核RESTFUL接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 15:45
 * 版本: V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AssetApprovalTest {

    @Autowired
    private AssetApprovalServiceFacade assetApprovalServiceFacade;

    /**
     * 审核资产
     */
    @Test
    public void approveAsset() {
        ApproveAssetRequest req = new ApproveAssetRequest();
        req.setAssetCode("AMA170500032");
        req.setApprovalStatus(3);
        req.setSign("A");
        req.setApprovalBy("SYS");
        req.setApprovalSuggestion("审核通过");
        BaseResponse response = assetApprovalServiceFacade.approveAsset(req);
        System.out.println(response.getRespMsg());
    }

    /**
     * 查询资产审核记录列表
     */
    @Test
    public void queryAssetApprovalList() {
        QueryAssetApprovalListRequest req = new QueryAssetApprovalListRequest();
        PageQueryResponse<AssetApprovalModel> response = assetApprovalServiceFacade.queryAssetApprovalList(req);
        System.out.println(response.getRespMsg());
    }
}
