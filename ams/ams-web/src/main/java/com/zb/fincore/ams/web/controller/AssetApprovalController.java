package com.zb.fincore.ams.web.controller;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.facade.AssetApprovalServiceFacade;
import com.zb.fincore.ams.facade.dto.req.ApproveAssetRequest;
import com.zb.fincore.ams.facade.dto.req.QueryAssetApprovalListRequest;
import com.zb.fincore.ams.facade.model.AssetApprovalModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能: 资产审核RESTFUL接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 15:45
 * 版本: V1.0
 */
@RestController
@RequestMapping(value = "/assetApprovalService")
public class AssetApprovalController {

    @Autowired
    private AssetApprovalServiceFacade assetApprovalServiceFacade;

    /**
     * 审核资产
     *
     * @param req 审核资产请求对象
     * @return 审核资产响应对象
     */
    @RequestMapping(value = "/approveAsset", method = RequestMethod.POST)
    public BaseResponse approveAsset(@RequestBody ApproveAssetRequest req) {
        return assetApprovalServiceFacade.approveAsset(req);
    }

    /**
     * 查询资产审核记录列表
     *
     * @param req 查询资产审核记录列表请求对象
     * @return 查询资产审核记录列表响应对象
     */
    @RequestMapping(value = "/queryAssetApprovalList", method = RequestMethod.POST)
    public PageQueryResponse<AssetApprovalModel> queryAssetApprovalList(@RequestBody QueryAssetApprovalListRequest req) {
        return assetApprovalServiceFacade.queryAssetApprovalList(req);
    }
}
