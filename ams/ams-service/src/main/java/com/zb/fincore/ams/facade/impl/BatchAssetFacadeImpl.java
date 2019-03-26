package com.zb.fincore.ams.facade.impl;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.common.exception.ExceptionHandler;
import com.zb.fincore.ams.facade.BatchAssetFacade;
import com.zb.fincore.ams.facade.dto.req.*;
import com.zb.fincore.ams.facade.dto.resp.BatchCreateAssetResponse;
import com.zb.fincore.ams.facade.dto.resp.ExportBusinessCreditResponse;
import com.zb.fincore.ams.facade.dto.resp.RegisterExchangeAssetResponse;
import com.zb.fincore.ams.facade.model.*;
import com.zb.fincore.ams.service.BatchAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能: 批量录入服务实现接口
 * Created by MABIAO on 2017/6/19 0019.
 */
@Service
public class BatchAssetFacadeImpl implements BatchAssetFacade {

    @Autowired
    private BatchAssetService batchAssetService;

    @Autowired
    private ExceptionHandler exceptionHandler;

    /**
     * 文件导入
     * @param req
     * @return
     */
    @Override
    public BatchCreateAssetResponse importBatchAsset(ImportBatchAssetRequest req) {
        try {
            return batchAssetService.importBatchAsset(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,BatchCreateAssetResponse.class);
        }
    }

    /**
     * 模板录入
     */
    @Override
    public BaseResponse createFileTemplate(CreateFileTemplateRequest req) {
        try {
            return batchAssetService.createFileTemplate(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

    /**
     * 模板详情
     */
    @Override
    public QueryResponse<FileTemplateModel> queryFileTemplate(QueryFileTemplateRequest req) {
        try {
            return batchAssetService.queryFileTemplate(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,QueryResponse.class);
        }
    }

    /**
     * 模板参数录入
     */
    @Override
    public BaseResponse createFileTemplateParam(CreateFileTemplateParamRequest req) {
        try {
            return batchAssetService.createFileTemplateParam(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

    /**
     * 模板参数更新
     */
    @Override
    public BaseResponse updateFileTemplateParam(CreateFileTemplateParamRequest req) {
        try {
            return batchAssetService.updateFileTemplateParam(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

    /**
     * 模板参数详情
     */
    @Override
    public QueryResponse<FileTemplateParamModel> queryFileTemplateParam(QueryFileTemplateParamRequest req) {
        try {
            return batchAssetService.queryFileTemplateParam(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,QueryResponse.class);
        }
    }

    /**
     * 授信表导出
     */
    @Override
    public ExportBusinessCreditResponse exportBusinessCredit(ExportBusinessCreditRequest req) {
        try {
            return batchAssetService.exportBusinessCredit(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,ExportBusinessCreditResponse.class);
        }
    }

    /**
     * 资产合同列表
     * @param req
     * @return
     */
    @Override
    public PageQueryResponse<FileTemplateParamModel> queryAssetContractList(QueryAssetContractListRequest req) {
        try {
            return batchAssetService.queryAssetContractList(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,PageQueryResponse.class);
        }
    }

    /**
     * 备案信息表导出
     */
    @Override
    public BaseResponse exportRecordData() {
        return null;
    }

    /**
     * 合同导出
     */
    @Override
    public BaseResponse exportContract() {
        return null;
    }

    /**
     * 资产备案
     */
    @Override
    public RegisterExchangeAssetResponse registerExchangeAsset(RegisterExchangeAssetRequest req) {
        try {
            return batchAssetService.registerExchangeAsset(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,PageQueryResponse.class);
        }
    }

    /**
     * 更新备案状态
     */
    @Override
    public BaseResponse updateRegisterExchangeInfo(UpdateRegisterExchangeInfoRequest req) {
        try {
            return batchAssetService.updateRegisterExchangeInfo(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,PageQueryResponse.class);
        }
    }

    /**
     * 授信资产录入审核列表
     */
    @Override
    public PageQueryResponse<AssetModel> queryCreditAssetListForApproval(QueryCreditAssetListRequest req) {
        try {
            return batchAssetService.queryCreditAssetListForApproval(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,PageQueryResponse.class);
        }
    }

    /**
     * 审核
     */
    @Override
    public BaseResponse approvalCreditAsset(ApprovalCreditAssetRequest req) {
        try {
            return batchAssetService.approvalCreditAsset(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

    /**
     * 备案详情
     */
    public QueryResponse<AssetExchangeRegisterModel> queryAssetRegisterExchange(QueryAssetRegisterExchangeRequest req){
        try {
            return batchAssetService.queryAssetRegisterExchange(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,QueryResponse.class);
        }
    }

    /**
     * 授信资产生成合同列表
     */
    @Override
    public PageQueryResponse<AssetModel> queryUnPackageAssetList(QueryUnPackageAssetRequest req) {
        try {
            return batchAssetService.queryUnPackageAssetList(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,QueryResponse.class);
        }
    }

    /**
     * 授信资产生成合同详情
     */
    @Override
    public QueryResponse<UnPackageAssetDetailModel> queryUnPackageAssetDetail(QueryUnPackageAssetDetailRequest req) {
        try {
            return batchAssetService.queryUnPackageAssetDetail(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,QueryResponse.class);
        }
    }

    /**
     * 交易所备案资产查询列表
     */
    @Override
    public PageQueryResponse<AssetModel> queryRecordAssetList(QueryRecordAssetListRequest req) {
        try {
            return batchAssetService.queryRecordAssetList(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,QueryResponse.class);
        }
    }

    @Override
    public BaseResponse generateContract(GenerateContractRequest req) {
        try {
            return batchAssetService.generateContract(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

    @Override
    public PageQueryResponse<AssetForTxsModel> queryAssetListForTxs(QueryAssetListForTxsRequest req) {
        try {
            return batchAssetService.queryAssetListForTxs(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,PageQueryResponse.class);
        }
    }

    @Override
    public BaseResponse updateAssetForTxs(UpdateAssetForTxsRequest req) {
        try {
            return batchAssetService.updateAssetForTxs(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

    @Override
    public PageQueryResponse<AssetExchangeRegisterModel> queryExchangeRegisterRecordList(QueryExchangeRegisterRecordListRequest req) {
        try {
            return batchAssetService.queryExchangeRegisterRecordList(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,PageQueryResponse.class);
        }
    }

    @Override
    public QueryResponse<UnPackageAssetDetailModel> queryExchangeRegisterRecordDetail(QueryExchangeRegisterDetailRequest req) {
        try {
            return batchAssetService.queryExchangeRegisterRecordDetail(req);
        } catch (Exception e) {
            return exceptionHandler.handleException(e,QueryResponse.class);
        }
    }
}
