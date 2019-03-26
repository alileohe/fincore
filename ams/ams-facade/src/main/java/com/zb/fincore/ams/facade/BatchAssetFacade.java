package com.zb.fincore.ams.facade;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.dto.req.*;
import com.zb.fincore.ams.facade.dto.resp.BatchCreateAssetResponse;
import com.zb.fincore.ams.facade.dto.resp.ExportBusinessCreditResponse;
import com.zb.fincore.ams.facade.dto.resp.RegisterExchangeAssetResponse;
import com.zb.fincore.ams.facade.model.*;

/**
 * 功能: 批量录入服务接口
 * Created by MABIAO on 2017/6/15 0015.
 */
public interface BatchAssetFacade {

    /**
     * 文件导入
     * @param req
     * @return
     */
    public BatchCreateAssetResponse importBatchAsset(ImportBatchAssetRequest req);

    /**
     * 模板录入
     */
    public BaseResponse createFileTemplate(CreateFileTemplateRequest req);

    /**
     * 模板详情
     */
    public QueryResponse<FileTemplateModel> queryFileTemplate(QueryFileTemplateRequest req);

    /**
     * 模板参数录入
     */
    public BaseResponse createFileTemplateParam(CreateFileTemplateParamRequest req);

    /**
     * 模板参数更新
     */
    public BaseResponse updateFileTemplateParam(CreateFileTemplateParamRequest req);

    /**
     * 模板参数详情
     */
    public QueryResponse<FileTemplateParamModel> queryFileTemplateParam(QueryFileTemplateParamRequest req);

    /**
     * 授信表导出
     */
    public ExportBusinessCreditResponse exportBusinessCredit(ExportBusinessCreditRequest req);

    /**
     * 备案信息导出
     */
    public BaseResponse exportRecordData();

    /**
     * 合同导出
     */
    public BaseResponse exportContract();

    /**
     * 资产备案
     */
    public RegisterExchangeAssetResponse registerExchangeAsset(RegisterExchangeAssetRequest req);

    /**
     * 资产合同列表
     */
    public PageQueryResponse<FileTemplateParamModel> queryAssetContractList(QueryAssetContractListRequest req);

    /**
     * 更新备案状态
     */
    public BaseResponse updateRegisterExchangeInfo(UpdateRegisterExchangeInfoRequest req);

    /**
     * 授信资产录入审核列表
     */
    public PageQueryResponse<AssetModel> queryCreditAssetListForApproval(QueryCreditAssetListRequest req);

    /**
     * 审核
     */
    public BaseResponse approvalCreditAsset(ApprovalCreditAssetRequest req);

    /**
     * 备案详情
     */
    public QueryResponse<AssetExchangeRegisterModel> queryAssetRegisterExchange(QueryAssetRegisterExchangeRequest req);

    /**
     * 授信资产生成合同列表
     */
    public PageQueryResponse<AssetModel> queryUnPackageAssetList(QueryUnPackageAssetRequest req);

    /**
     * 授信资产生成合同详情
     */
    public QueryResponse<UnPackageAssetDetailModel> queryUnPackageAssetDetail(QueryUnPackageAssetDetailRequest req);

    /**
     * 交易所备案资产查询列表
     */
    public PageQueryResponse<AssetModel> queryRecordAssetList(QueryRecordAssetListRequest req);

    public BaseResponse generateContract(GenerateContractRequest req);

    public PageQueryResponse<AssetForTxsModel> queryAssetListForTxs(QueryAssetListForTxsRequest req);

    public BaseResponse updateAssetForTxs(UpdateAssetForTxsRequest req);

    public PageQueryResponse<AssetExchangeRegisterModel> queryExchangeRegisterRecordList(QueryExchangeRegisterRecordListRequest req);

    public QueryResponse<UnPackageAssetDetailModel> queryExchangeRegisterRecordDetail(QueryExchangeRegisterDetailRequest req);

}
