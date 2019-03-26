package com.zb.fincore.ams.facade.model;

import com.zb.fincore.ams.common.model.BaseModel;

import java.util.List;

/**
 * 授信资产合同生成详情
 * Created by MABIAO on 2017/6/29 0029.
 */
public class UnPackageAssetDetailModel extends BaseModel {

    private static final long serialVersionUID = -2998116246604252444L;

    private AssetModel assetModel;//资产

    private IssuePlanModel issuePlanModel;//发行计划

    private BusinessCreditModel businessCreditModel;//授信

    private List<FileTemplateParamModel> paramModels;//合同

    private AssetExchangeRegisterModel registerModel;

    private FinanceSubjectModel delistedInstModel;// 摘牌方

    private FinanceSubjectModel listedInstModel;//挂牌方

    private ExchangeModel exchangeModel;//交易所

    private ContractInfoModel infoModel;//合同状态


    public AssetModel getAssetModel() {
        return assetModel;
    }

    private FinanceSubjectModel financeSubjectModel;//融资方

    public void setAssetModel(AssetModel assetModel) {
        this.assetModel = assetModel;
    }

    public IssuePlanModel getIssuePlanModel() {
        return issuePlanModel;
    }

    public void setIssuePlanModel(IssuePlanModel issuePlanModel) {
        this.issuePlanModel = issuePlanModel;
    }

    public BusinessCreditModel getBusinessCreditModel() {
        return businessCreditModel;
    }

    public void setBusinessCreditModel(BusinessCreditModel businessCreditModel) {
        this.businessCreditModel = businessCreditModel;
    }

    public List<FileTemplateParamModel> getParamModels() {
        return paramModels;
    }

    public void setParamModels(List<FileTemplateParamModel> paramModels) {
        this.paramModels = paramModels;
    }

    public FinanceSubjectModel getFinanceSubjectModel() {
        return financeSubjectModel;
    }

    public void setFinanceSubjectModel(FinanceSubjectModel financeSubjectModel) {
        this.financeSubjectModel = financeSubjectModel;
    }

    public AssetExchangeRegisterModel getRegisterModel() {
        return registerModel;
    }

    public void setRegisterModel(AssetExchangeRegisterModel registerModel) {
        this.registerModel = registerModel;
    }

    public FinanceSubjectModel getDelistedInstModel() {
        return delistedInstModel;
    }

    public void setDelistedInstModel(FinanceSubjectModel delistedInstModel) {
        this.delistedInstModel = delistedInstModel;
    }

    public FinanceSubjectModel getListedInstModel() {
        return listedInstModel;
    }

    public void setListedInstModel(FinanceSubjectModel listedInstModel) {
        this.listedInstModel = listedInstModel;
    }

    public ExchangeModel getExchangeModel() {
        return exchangeModel;
    }

    public void setExchangeModel(ExchangeModel exchangeModel) {
        this.exchangeModel = exchangeModel;
    }

    public ContractInfoModel getInfoModel() {
        return infoModel;
    }

    public void setInfoModel(ContractInfoModel infoModel) {
        this.infoModel = infoModel;
    }
}
