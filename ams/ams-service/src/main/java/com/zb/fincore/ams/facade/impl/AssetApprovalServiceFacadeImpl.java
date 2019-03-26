package com.zb.fincore.ams.facade.impl;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.exception.ExceptionHandler;
import com.zb.fincore.ams.facade.AssetApprovalServiceFacade;
import com.zb.fincore.ams.facade.dto.req.ApproveAssetRequest;
import com.zb.fincore.ams.facade.dto.req.QueryAssetApprovalListRequest;
import com.zb.fincore.ams.facade.model.AssetApprovalModel;
import com.zb.fincore.ams.service.AssetApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能: 资产审核服务接口实现
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 15:44
 * 版本: V1.0
 */
@Service
public class AssetApprovalServiceFacadeImpl implements AssetApprovalServiceFacade {

    @Autowired
    private AssetApprovalService assetApprovalService;

    @Autowired
    private ExceptionHandler exceptionHandler;

    /**
     * 审核资产
     *
     * @param req 审核资产请求对象
     * @return 审核资产响应对象
     */
    @Override
    public BaseResponse approveAsset(ApproveAssetRequest req) {
        try {
            return assetApprovalService.approveAsset(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

    /**
     * 查询资产审核记录列表
     *
     * @param req 查询资产审核记录列表请求对象
     * @return 查询资产审核记录列表响应对象
     */
    @Override
    public PageQueryResponse<AssetApprovalModel> queryAssetApprovalList(QueryAssetApprovalListRequest req) {
        try {
            return assetApprovalService.queryAssetApprovalList(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, PageQueryResponse.class);
        }
    }
}
