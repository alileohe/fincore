package com.zb.fincore.ams.web.controller;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.AssetForP2PFacade;
import com.zb.fincore.ams.facade.BatchAssetFacade;
import com.zb.fincore.ams.facade.BorrowerInfoFacade;
import com.zb.fincore.ams.facade.dto.req.*;
import com.zb.fincore.ams.facade.dto.resp.BaseP2PResponse;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetProductRelationResponse;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetResponse;
import com.zb.fincore.ams.facade.dto.resp.RepaymentStatusQueryResponse;
import com.zb.fincore.ams.facade.model.AssetForTxsModel;
import com.zb.fincore.ams.facade.model.AssetRepayPlanModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 供内部系统调用的REST接口
 * Created by liuchongguang on 2017/7/9.
 */
@RestController
@RequestMapping(value = "/internal")
public class InternalController {

    @Autowired
    private BatchAssetFacade batchAssetFacade;

    @Autowired
    private AssetForP2PFacade assetForP2PFacade;

    @Autowired
    private BorrowerInfoFacade borrowerInfoFacade;

    /**
     * 唐小僧导入资产列表
     */
    @RequestMapping(value = "queryAssetListForTxs", method = RequestMethod.POST)
    public PageQueryResponse<AssetForTxsModel> queryAssetListForTxs(@RequestBody QueryAssetListForTxsRequest req) {
        return batchAssetFacade.queryAssetListForTxs(req);
    }

    /**
     * 唐小僧导入资产更新
     */
    @RequestMapping(value = "updateAssetForTxs", method = RequestMethod.POST)
    public BaseResponse updateAssetForTxs(@RequestBody UpdateAssetForTxsRequest req) {
        return batchAssetFacade.updateAssetForTxs(req);
    }

    /**
     * 创建产品资产匹配关系
     * @param req 创建产品资产匹配关系请求对象
     * @return 创建产品资产匹配关系响应对象
     */
    @RequestMapping(value = "/createAssetProductRelation", method = RequestMethod.POST)
    public CreateAssetProductRelationResponse createAssetProductRelation(@RequestBody CreateAssetProductRelationRequest req) {
        return assetForP2PFacade.createAssetProductRelation(req);
    }

    /**
     * 创建资产
     */
    @RequestMapping(value = "/createAssetForP2P", method = RequestMethod.POST)
    public BaseP2PResponse createAssetForP2P(@RequestBody CreateAssetForP2PRequest req) {
        CreateAssetResponse response = assetForP2PFacade.createAssetForP2P(req);
        BaseP2PResponse resp = new BaseP2PResponse();
        resp.setCode(response.getRespCode());
        resp.setMsg(response.getRespMsg());
        return resp;
    }

    /**
     * 资产匹配
     */
    @RequestMapping(value = "/saveAssetProductRelation", method = RequestMethod.POST)
    public BaseResponse saveAssetProductRelation(@RequestBody RelateAssetProductForP2PRequest req) {
        return assetForP2PFacade.saveAssetProductRelation(req);
    }

    /**
     * 更新资产还款金额
     */
    @RequestMapping(value = "/updateAssetForP2P", method = RequestMethod.POST)
    public CreateAssetResponse updateAssetForP2P(@RequestBody CreateAssetForP2PRequest req) {
        return assetForP2PFacade.createAssetForP2P(req);
    }

    /**
     * 更新资产库存
     */
    @RequestMapping(value = "/updateAssetStock", method = RequestMethod.POST)
    public BaseP2PResponse updateAssetStock(@RequestBody UpdateAssetStockRequest req) {
        BaseResponse response = assetForP2PFacade.updateAssetStock(req);
        BaseP2PResponse resp = new BaseP2PResponse();
        resp.setCode(response.getRespCode());
        resp.setMsg(response.getRespMsg());
        return resp;
    }

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
