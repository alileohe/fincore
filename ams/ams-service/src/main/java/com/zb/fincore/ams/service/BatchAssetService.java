package com.zb.fincore.ams.service;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.dto.req.*;
import com.zb.fincore.ams.facade.dto.resp.BatchCreateAssetResponse;
import com.zb.fincore.ams.facade.dto.resp.ExportBusinessCreditResponse;
import com.zb.fincore.ams.facade.dto.resp.RegisterExchangeAssetResponse;
import com.zb.fincore.ams.facade.model.*;

import javax.validation.Valid;

/**
 * 功能: 批量录入服务接口
 * Created by MABIAO on 2017/6/15 0015.
 */
public interface BatchAssetService {

    /**
     * 文件导入
     * @param req
     * @return
     */
    public BatchCreateAssetResponse importBatchAsset(ImportBatchAssetRequest req) throws Exception;

    /**
     * 模板录入
     */
    public BaseResponse createFileTemplate(@Valid CreateFileTemplateRequest req) throws Exception;

    /**
     * 模板详情
     */
    public QueryResponse<FileTemplateModel> queryFileTemplate(@Valid QueryFileTemplateRequest req) throws Exception;

    /**
     * 模板参数录入
     */
    public BaseResponse createFileTemplateParam(@Valid CreateFileTemplateParamRequest req) throws Exception;

    /**
     * 模板参数更新
     */
    public BaseResponse updateFileTemplateParam(@Valid CreateFileTemplateParamRequest req) throws Exception;

    /**
     * 模板参数详情
     */
    public QueryResponse<FileTemplateParamModel> queryFileTemplateParam(@Valid QueryFileTemplateParamRequest req) throws Exception;

    /**
     * 授信表导出
     */
    public ExportBusinessCreditResponse exportBusinessCredit(ExportBusinessCreditRequest req) throws Exception;

    /**
     * 资产合同列表
     * @param req
     * @return
     */
    public PageQueryResponse<FileTemplateParamModel> queryAssetContractList(@Valid QueryAssetContractListRequest req) throws Exception;

    /**
     * 资产备案
     */
    public RegisterExchangeAssetResponse registerExchangeAsset(@Valid RegisterExchangeAssetRequest req) throws Exception;

    /**
     * 更新备案状态
     */
    public BaseResponse updateRegisterExchangeInfo(@Valid UpdateRegisterExchangeInfoRequest req) throws Exception;

    /**
     * 授信资产录入审核列表
     */
    public PageQueryResponse<AssetModel> queryCreditAssetListForApproval(QueryCreditAssetListRequest req) throws Exception;

    public BaseResponse approvalCreditAsset(@Valid ApprovalCreditAssetRequest req) throws Exception;

    /**
     * 备案详情
     */
    public QueryResponse<AssetExchangeRegisterModel> queryAssetRegisterExchange(@Valid QueryAssetRegisterExchangeRequest req) throws Exception;

    /**
     * 授信资产生成合同列表
     */
    public PageQueryResponse<AssetModel> queryUnPackageAssetList(QueryUnPackageAssetRequest req) throws Exception;

    public QueryResponse<UnPackageAssetDetailModel> queryUnPackageAssetDetail(@Valid QueryUnPackageAssetDetailRequest req) throws Exception;

    public PageQueryResponse<AssetModel> queryRecordAssetList(@Valid QueryRecordAssetListRequest req) throws Exception;

    public BaseResponse generateContract(GenerateContractRequest req) throws Exception;

    public PageQueryResponse<AssetForTxsModel> queryAssetListForTxs(QueryAssetListForTxsRequest req) throws Exception;

    public BaseResponse updateAssetForTxs(UpdateAssetForTxsRequest req) throws Exception;

    public PageQueryResponse<AssetExchangeRegisterModel> queryExchangeRegisterRecordList(QueryExchangeRegisterRecordListRequest req) throws Exception;

    public QueryResponse<UnPackageAssetDetailModel> queryExchangeRegisterRecordDetail(QueryExchangeRegisterDetailRequest req) throws Exception;
}
