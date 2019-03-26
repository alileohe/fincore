package com.zb.fincore.ams.service.impl;

import com.zb.fincore.ams.common.Constants;
import com.zb.fincore.ams.common.aspect.validate.FieldValidator;
import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.common.enums.*;
import com.zb.fincore.ams.common.exception.BusinessException;
import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.facade.dto.req.*;
import com.zb.fincore.ams.facade.dto.resp.BatchCreateAssetResponse;
import com.zb.fincore.ams.facade.dto.resp.ExportBusinessCreditResponse;
import com.zb.fincore.ams.facade.dto.resp.RegisterExchangeAssetResponse;
import com.zb.fincore.ams.facade.model.*;
import com.zb.fincore.ams.service.BatchAssetService;
import com.zb.fincore.ams.service.SequenceService;
import com.zb.fincore.ams.service.dal.dao.*;
import com.zb.fincore.ams.service.dal.model.*;
import com.zb.fincore.ams.service.validate.BatchAssetParameterValidator;
import com.zb.fincore.common.utils.BeanUtils;
import com.zb.fincore.common.utils.DateUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by MABIAO on 2017/6/19 0019.
 */
@Service
public class BatchAssetServiceImpl implements BatchAssetService {

    @Autowired
    private GlobalConfigDao globalConfigDao;

    @Autowired
    private FileTemplateDao fileTemplateDao;

    @Autowired
    private FileTemplateParamDao fileTemplateParamDao;

    @Autowired
    private BusinessCreditDao businessCreditDao;

    @Autowired
    private IssuePlanDao issuePlanDao;

    @Autowired
    private BatchAssetParameterValidator batchAssetParameterValidator;

    @Autowired
    private FieldValidator fieldValidator;

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private AssetDao assetDao;

    @Autowired
    private FinanceSubjectDao financeSubjectDao;

    @Autowired
    private AssetIssueRelationDao assetIssueRelationDao;

    @Autowired
    private AssetExchangeRegisterDao assetExchangeRegisterDao;

    @Autowired
    private AssetApprovalDao assetApprovalDao;

    @Autowired
    private ExchangeDao exchangeDao;

    /**
     * 文件导入
     * @param req
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public synchronized BatchCreateAssetResponse importBatchAsset(ImportBatchAssetRequest req) throws Exception {
        BaseResponse response = batchAssetParameterValidator.checkImportBatchAssetRequest(req);
        if(null != response){
            return new BaseResponse().build(BatchCreateAssetResponse.class,response.getRespCode(),response.getRespMsg());
        }

        BigDecimal creditAmount = BigDecimal.ZERO;
        List<ErrorModel> errorModelList = new ArrayList<>();
        int index = 0;
        for(IssuePlanModel planModel : req.getPlanModelList()){
            index ++;
            creditAmount = creditAmount.add(planModel.getTotalAmount());
            //校验发行计划
            CreateIssuePlanRequest issuePlanRequest = BeanUtils.copyAs(planModel,CreateIssuePlanRequest.class);
            List<String> errorList = fieldValidator.validateErrors(issuePlanRequest);
            for(String result : errorList){
                result = "第"+index+"行" + result;
            }
            //校验融资方
            if(StringUtils.isNotBlank(planModel.getSubjectName())){
                FinanceSubject subject = new FinanceSubject();
                subject.setSubjectName(planModel.getSubjectName());
                List<FinanceSubject> financeList = financeSubjectDao.selectForUnique(subject);
                if(null == financeList || financeList.size() == 0){
                    errorList.add("第"+index+"行" + Constants.FINANCE_SUBJECT_NOT_EXIST_DESC);
                }else{
                    planModel.setSubjectCode(financeList.get(0).getSubjectCode());
                }
            }

            //起息日小于结息日
            if(DateUtils.isAfterOrEqual(planModel.getValueStartTime(), planModel.getProductValueEndTime())){
                errorList.add("第"+index+"行" +Constants.VALUE_START_LT_VALUE_END_DESC);
            }

            //合同天数大于产品天数
            if(planModel.getProductDayCount() >= planModel.getContractDayCount()){
                errorList.add("第"+index+"行" +Constants.PRODUCT_COUNT_LT_CONTRACT_COUNT_DESC);
            }

            if (CollectionUtils.isNotEmpty(errorList)) {
                ErrorModel errorModel = new ErrorModel();
                errorModel.setErrorList(errorList);
                errorModelList.add(errorModel);
            }
        }

        //校验授信表
        BusinessCreditModel creditModel = req.getCreditModel();
        CreateBusinessCreditRequest issuePlanRequest = BeanUtils.copyAs(creditModel,CreateBusinessCreditRequest.class);
        List<String> validateCredits = fieldValidator.validateErrors(issuePlanRequest);

        ErrorModel errorModel = new ErrorModel();

        if(CollectionUtils.isNotEmpty(validateCredits)){
            errorModel.setErrorList(validateCredits);
        }
        if(creditAmount.compareTo(creditModel.getCreditLimitAmount()) > 0){
            List<String> strings = new ArrayList<>();
            strings.add(Constants.CREDIT_LINES_NOT_ENOUGH_DESC);
            errorModel.setErrorList(strings);
        }

        if(null != errorModel.getErrorList() && errorModel.getErrorList().size() > 0){
            errorModelList.add(errorModel);
        }

        BatchCreateAssetResponse resp = BaseResponse.build(BatchCreateAssetResponse.class);
        resp.setTotalCount(req.getPlanModelList().size());
        resp.setErrorCount(errorModelList.size());
        resp.setErrorModelList(errorModelList);
        if (resp.getErrorCount() > 0) {
            resp.setRespCode(Constants.PARAM_VALIDATE_ERROR_CODE);
            resp.setRespMsg(Constants.PARAM_VALIDATE_ERROR_DESC);
            return resp;
        }

        /**
         * 资产申请权限
         */
        GlobalConfig globalConfig = globalConfigDao.selectByPropertyName(Constants.BATCH_ASSET_APPROVAL_SIGN_KEY);
        String requireSign = "";
        if (globalConfig != null) {
            requireSign = globalConfig.getPropertyValue();
        }

        /**
         * 录入批次号
         */
        String batchNo = sequenceService.generateRegisterNo(
                Constants.BATCH_NO_PREFIX, Constants.BATCH_NO_SEQUENCE_LENGTH);
        batchNo = batchNo.replace(Constants.BATCH_NO_PREFIX,"");

        //授信业务
        BusinessCredit businessCredit = BeanUtils.copyAs(creditModel,BusinessCredit.class);
        businessCredit.setCreditLimitAmount(businessCredit.getCreditLimitAmount().multiply(new BigDecimal("10000")));
        businessCredit.setYieldRate(businessCredit.getYieldRate().divide(new BigDecimal("100")));
        businessCredit.setBatchNo(batchNo);
        businessCreditDao.insertSelective(businessCredit);
        //发行计划
        for(IssuePlanModel planModel : req.getPlanModelList()){

            IssuePlan issuePlan = BeanUtils.copyAs(planModel,IssuePlan.class);
            issuePlan.setCreditBusinessCode(businessCredit.getCreditBusinessCode());
            issuePlan.setTotalAmount(issuePlan.getTotalAmount().multiply(new BigDecimal("10000")));
            issuePlan.setSingleAmount(issuePlan.getSingleAmount().multiply(new BigDecimal("10000")));
            issuePlan.setBatchNo(batchNo);
            issuePlanDao.insertSelective(issuePlan);//保存计划表

            int num = issuePlan.getAssetCount();
            for(int i=0;i < num; i++){
                //按照规则生成资产编号
                String assetCode = sequenceService.generateBusinessCode(
                        Constants.ASSET_CODE_PREFIX, Constants.ASSEET_CODE_SEQUENCE_LENGTH);
                Asset asset = new Asset();

                //资产名称=产品计划表中融资方+募集开始日+产品天数+产品的顺序。

                //按照规则生成资产顺序
                String assetNo = sequenceService.generateBusinessCode(
                        Constants.BATCH_NAME_PREFIX, Constants.BATCH_NAME_SEQUENCE_LENGTH);
                String assetName = planModel.getSubjectName() +
                        DateUtils.format(issuePlan.getSaleStartTime(),"yyMMdd") + issuePlan.getProductDayCount() +
                        assetNo.substring(assetNo.length() - 3, assetNo.length());

                asset.setAssetName(assetName);//资产名称
                asset.setAssetCode(assetCode);//资产编码
                asset.setAssetType(AssetTypeEnum.ACCOUNTS_RECEIVABLE_EARNINGS_RIGHT.getCode());//资产融资类型
                asset.setApprovalRequireSign(requireSign);//审核权限
                asset.setStockAmount(planModel.getSingleAmount().multiply(new BigDecimal("10000")));//库存
                asset.setAssetAmount(planModel.getSingleAmount().multiply(new BigDecimal("10000")));//资产额度
                asset.setApprovalStatus(StringUtils.isNotBlank(requireSign) ?
                        AssetApprovalStatusEnum.WAIT_APPROVAL.getCode() : AssetApprovalStatusEnum.APPROVAL_SUCCESS.getCode());
                asset.setCollectStatus(AssetCollectStatusEnum.WAIT_COLLECT.getCode());//募集状态为待审核


                asset.setEstablishTime(issuePlan.getValueStartTime());//成立日
                asset.setValueStartTime(issuePlan.getValueStartTime());//起息日
                asset.setValueEndTime(issuePlan.getProductValueEndTime());//结息日
                asset.setExpireTime(issuePlan.getProductValueEndTime());//到期日
                asset.setRepaymentType(Integer.valueOf(creditModel.getRepaymentType()));//还款方式
                asset.setFinanceSubjectCode(issuePlan.getSubjectCode());//融资方编码
                asset.setRegisterType(RegisterTypeEnum.RECORD.getCode());
                //期限等于合同天数
                asset.setValueDays(issuePlan.getContractDayCount());
                //利率
                BigDecimal yieldRate = creditModel.getYieldRate().divide(new BigDecimal("100"));
                asset.setYieldRate(yieldRate);//利率
                asset.setStorageStatus(StorageTypeEnum.UN_STORAGED.getCode());//未入库

                assetDao.insertSelective(asset);

                //生成资产合同
                String prams[] = {"ddrz-01","ddrz-02","ddrz-03"};
                //按照规则生成合同编码
                String contractBatchNo = sequenceService.generateRegisterNo(
                        Constants.CONTRACT_BATCH_NO_PREFIX, Constants.CONTRACT_BATCH_NO_LENGTH);
                contractBatchNo = "CDDRZ-" + contractBatchNo.substring(2,contractBatchNo.length());
                for(int k=0;k < prams.length;k++){
                    FileTemplateParam fileTemplateParam = new FileTemplateParam();
                    fileTemplateParam.setTemplateCode(prams[k]);
                    fileTemplateParam.setAssetCode(asset.getAssetCode());
                    fileTemplateParam.setStatus(ContractGenerateTypeEnum.NO.getCode());
                    fileTemplateParam.setContractBatchNo(contractBatchNo);
                    fileTemplateParamDao.insertSelective(fileTemplateParam);
                }

                AssetIssueRelation assetIssueRelation = new AssetIssueRelation();
                assetIssueRelation.setAssetCode(asset.getAssetCode());
                assetIssueRelation.setBatchNo(batchNo);
                assetIssueRelation.setIssueId(issuePlan.getId());
                assetIssueRelationDao.insertSelective(assetIssueRelation);
            }
        }
        resp.setBatchNo(batchNo);
        return resp;
    }

    /**
     * 模板录入
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BaseResponse createFileTemplate(@Valid CreateFileTemplateRequest req) throws Exception {
        FileTemplate fileTemplate = fileTemplateDao.selectFileTemplateByTemplateCode(req.getTemplateCode());
        if(null == fileTemplate){
            fileTemplate = new FileTemplate();
            BeanUtils.copy(req,fileTemplate);
            fileTemplateDao.insertSelective(fileTemplate);
        }else {
            BeanUtils.copy(req,fileTemplate);
            fileTemplateDao.updateByPrimaryKeySelective(fileTemplate);
        }
        return BaseResponse.build();
    }

    /**
     * 模板详情
     */
    @Override
    public QueryResponse<FileTemplateModel> queryFileTemplate(@Valid QueryFileTemplateRequest req) throws Exception {
        FileTemplate fileTemplate = fileTemplateDao.selectFileTemplateByTemplateCode(req.getTemplateCode());
        if(null != fileTemplate){
            QueryResponse<FileTemplateModel> resp = BaseResponse.build(QueryResponse.class);
            FileTemplateModel fileTemplateModel = new FileTemplateModel();
            BeanUtils.copy(fileTemplate,fileTemplateModel);
            resp.setData(fileTemplateModel);
            return resp;
        } else {
            return BaseResponse.build(QueryResponse.class, Constants.PARAM_RESULTBLANK_CODE, Constants.PARAM_RESULTBLANK_DESC);
        }
    }

    /**
     * 模板参数录入
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BaseResponse createFileTemplateParam(@Valid CreateFileTemplateParamRequest req) throws Exception {
        Map<String,Object> param = new HashMap<>();
        param.put("assetCode",req.getAssetCode());
        param.put("templateCode",req.getTemplateCode());
        List<FileTemplateParam> paramList = fileTemplateParamDao.selectByAssetCode(param);
        FileTemplateParam templateParam = null;

        if(null == paramList || paramList.size() == 0){
            templateParam = BeanUtils.copyAs(req,FileTemplateParam.class);
            templateParam.setStatus(ContractGenerateTypeEnum.YES.getCode());
            if(null != req.getTemplateCode() && req.getTemplateCode().equals("ddrz-04")){
                BaseResponse response = batchAssetParameterValidator.checkTemplateParamRequest(req);
                if(null != response){
                    return response;
                }

                AssetExchangeRegister isRePeat = new AssetExchangeRegister();
                isRePeat.setRegisterName(req.getRegisterName());
                isRePeat.setRegisterStatus(AssetRegisterExchangeEnum.REGISTER_FAILED.getValue());
                int repeat = assetExchangeRegisterDao.selectByAssetRegisterName(isRePeat);
                if(repeat > 0){
                    return  BaseResponse.build(Constants.REGISTER_NAME_REPEAT_CODE,Constants.REGISTER_NAME_REPEAT_DESC);
                }

                AssetExchangeRegister register = assetExchangeRegisterDao.selectByAssetCode(req.getAssetCode());

                if (null != register){
                    Asset asset = assetDao.selectByCode(register.getParentAssetCode());
                    if(null != asset){
                        if(req.getRegisterAmount().compareTo(asset.getStockAmount()) > 0){
                            return  BaseResponse.build(Constants.ASSET_STOCK_AMOUNT_NOT_ENOUGH_CODE,Constants.ASSET_STOCK_AMOUNT_NOT_ENOUGH_DESC + asset.getStockAmount());
                        }
                        asset.setStockAmount(asset.getStockAmount().subtract(req.getRegisterAmount()));
                        assetDao.updateSelectiveWithLock(asset);
                    }

                    register.setRegisterName(req.getRegisterName());//备案名称
                    register.setRegisterRate(req.getRegisterRate());//备案利率
                    register.setRegisterAmount(req.getRegisterAmount());//备案金额
                    register.setRegisterStatus(AssetRegisterExchangeEnum.REGISTERING.getValue());//备案中
                    assetExchangeRegisterDao.updateByPrimaryKeySelective(register);
                }
                //按照规则生成合同编码
                String contractBatchNo = sequenceService.generateRegisterNo(
                        Constants.CONTRACT_BATCH_NO_PREFIX, Constants.CONTRACT_BATCH_NO_LENGTH);
                contractBatchNo = "CDDRZ-" + contractBatchNo.substring(2,contractBatchNo.length());
                templateParam.setContractBatchNo(contractBatchNo);
                templateParam.setStatus(ContractGenerateTypeEnum.NO.getCode());
            }
            fileTemplateParamDao.insertSelective(templateParam);
        }else {
            templateParam = paramList.get(0);
            templateParam.setAssetCode(req.getAssetCode());
            templateParam.setTemplateCode(req.getTemplateCode());
            templateParam.setTemplateParam(req.getTemplateParam());
            templateParam.setCreateBy(req.getCreateBy());
            templateParam.setStatus(ContractGenerateTypeEnum.YES.getCode());
            if(null != req.getTemplateCode() && req.getTemplateCode().equals("ddrz-04")){
                BaseResponse response = batchAssetParameterValidator.checkTemplateParamRequest(req);
                if(null != response){
                    return response;
                }
                AssetExchangeRegister register = assetExchangeRegisterDao.selectByAssetCode(req.getAssetCode());

                if (null != register){
                    if(!req.getRegisterName().equals(register.getRegisterName())){

                        AssetExchangeRegister isRePeat = new AssetExchangeRegister();
                        isRePeat.setRegisterName(req.getRegisterName());
                        isRePeat.setRegisterStatus(AssetRegisterExchangeEnum.REGISTER_FAILED.getValue());
                        int repeat = assetExchangeRegisterDao.selectByAssetRegisterName(isRePeat);
                        if(repeat > 0){
                            return  BaseResponse.build(Constants.REGISTER_NAME_REPEAT_CODE,Constants.REGISTER_NAME_REPEAT_DESC);
                        }
                    }

                    Asset asset = assetDao.selectByCode(register.getParentAssetCode());
                    if(null != asset){
                        if(req.getRegisterAmount().compareTo(asset.getStockAmount().add(register.getRegisterAmount())) > 0){
                            return  BaseResponse.build(Constants.ASSET_STOCK_AMOUNT_NOT_ENOUGH_CODE,Constants.ASSET_STOCK_AMOUNT_NOT_ENOUGH_DESC + asset.getStockAmount());
                        }
                        asset.setStockAmount(asset.getStockAmount().add(register.getRegisterAmount()).subtract(req.getRegisterAmount()));
                        assetDao.updateSelectiveWithLock(asset);
                    }
                    register.setRegisterName(req.getRegisterName());//备案名称
                    register.setRegisterRate(req.getRegisterRate());//备案利率
                    register.setRegisterAmount(req.getRegisterAmount());//备案金额
                    assetExchangeRegisterDao.updateByPrimaryKeySelective(register);
                }

                templateParam.setStatus(ContractGenerateTypeEnum.NO.getCode());
            }
            fileTemplateParamDao.updateByPrimaryKeySelective(templateParam);
        }

        return BaseResponse.build();
    }

    /**
     * 模板参数更新
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BaseResponse updateFileTemplateParam(@Valid CreateFileTemplateParamRequest req) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("templateCode",req.getTemplateCode());
        map.put("assetCode",req.getAssetCode());
        FileTemplateParam param = fileTemplateParamDao.selectParamByTemplateCode(map);
        if(null == param){
            return BaseResponse.build(Constants.TEMPLATE_PARAM_NOT_EXIST_CODE,Constants.TEMPLATE_PARAM_NOT_EXIST_DESC);
        }else {
            BeanUtils.copy(req,param);
            fileTemplateParamDao.updateByPrimaryKeySelective(param);
        }
        return BaseResponse.build();
    }

    /**
     * 模板参数详情
     */
    @Override
    public QueryResponse<FileTemplateParamModel> queryFileTemplateParam(@Valid QueryFileTemplateParamRequest req) throws Exception {

        Map<String,Object> param = new HashMap<>();
        param.put("templateCode",req.getTemplateCode());
        param.put("assetCode",req.getAssetCode());

        FileTemplateParam fileTemplate = fileTemplateParamDao.selectParamByTemplateCode(param);
        if(null != param){
            QueryResponse<FileTemplateParamModel> resp = BaseResponse.build(QueryResponse.class);
            FileTemplateParamModel fileTemplateModel = new FileTemplateParamModel();
            BeanUtils.copy(fileTemplate,fileTemplateModel);
            resp.setData(fileTemplateModel);
            return resp;
        }else {
            return BaseResponse.build(QueryResponse.class, Constants.PARAM_RESULTBLANK_CODE, Constants.PARAM_RESULTBLANK_DESC);
        }
    }

    /**
     * 授信表导出
     */
    @Override
    public ExportBusinessCreditResponse exportBusinessCredit(ExportBusinessCreditRequest req) throws Exception {
        ExportBusinessCreditResponse resp = BaseResponse.build(ExportBusinessCreditResponse.class);
        Map<String,Object> param = new HashMap<>();
        List<ExportBusinessCreditModel> modelList = null;
        List<ExportBusinessCredit> planList = issuePlanDao.queryPlanListForExport(param);
        if(null != planList && planList.size() > 0){
            modelList = BeanUtils.copyAs(planList,ExportBusinessCreditModel.class);
        }
        resp.setModelList(modelList);
        return resp;
    }

    /**
     * 资产合同列表
     * @param req
     * @return
     */
    @Override
    public PageQueryResponse<FileTemplateParamModel> queryAssetContractList(@Valid QueryAssetContractListRequest req) throws Exception {
        PageQueryResponse<FileTemplateParamModel> resp = BaseResponse.build(PageQueryResponse.class);
        List<FileTemplateParamModel> models = null;
        FileTemplateParam fileTemplateParam = BeanUtils.copyAs(req,FileTemplateParam.class);
        List<FileTemplateParam> params = fileTemplateParamDao.selectParamList(fileTemplateParam);
        if (null != params && params.size() > 0){
            models = BeanUtils.copyAs(params,FileTemplateParamModel.class);
            for(FileTemplateParamModel model : models){
                model.setStatusDesc(ContractGenerateTypeEnum.getEnumItem(model.getStatus()).getDesc());
            }
        }
        resp.setDataList(models);
        return resp;
    }

    /**
     * 资产备案 old
     */
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public BaseResponse registerExchangeAsset(@Valid RegisterExchangeAssetRequest req) throws Exception {
//
//        AssetExchangeRegister isRegister = assetExchangeRegisterDao.selectByAssetCode(req.getAssetCode());
//        if(null == isRegister){
//            AssetExchangeRegister exchangeRegister = BeanUtils.copyAs(req,AssetExchangeRegister.class);
//            exchangeRegister.setRegisterStatus(AssetRegisterExchangeEnum.REGISTERING.getValue());//备案中
//
//            //按照规则生成备案批次号
//            String registerNo = sequenceService.generateRegisterNo(
//                    req.getTradeInstCode(), Constants.BATCH_NAME_SEQUENCE_LENGTH);
//            exchangeRegister.setExchangeRegisterBatchNo(registerNo);
//
//            assetExchangeRegisterDao.insertSelective(exchangeRegister);
//        }else {
//            isRegister.setAssetCode(req.getAssetCode());
//            isRegister.setDelistedInstCode(req.getDelistedInstCode());
//            isRegister.setListedInstCode(req.getListedInstCode());
//            isRegister.setTradeInstCode(req.getTradeInstCode());
//            isRegister.setRegisterStatus(AssetRegisterExchangeEnum.REGISTERING.getValue());//备案中
//
//            //按照规则生成备案批次号
//            String registerNo = sequenceService.generateRegisterNo(
//                    req.getTradeInstCode(), Constants.BATCH_NAME_SEQUENCE_LENGTH);
//            isRegister.setExchangeRegisterBatchNo(registerNo);
//            assetExchangeRegisterDao.updateByPrimaryKeySelective(isRegister);
//        }
//
//        return BaseResponse.build();
//    }

    /**
     * 资产备案
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public RegisterExchangeAssetResponse registerExchangeAsset(@Valid RegisterExchangeAssetRequest req) throws Exception {
        RegisterExchangeAssetResponse resp =  new BaseResponse().build(RegisterExchangeAssetResponse.class);

        if(null == req.getAssetCode() || req.getAssetCode().equals("")){//创建资产备案记录
            AssetExchangeRegister exchangeRegister = BeanUtils.copyAs(req,AssetExchangeRegister.class);
            exchangeRegister.setRegisterStatus(AssetRegisterExchangeEnum.UN_REGISTER.getValue());//未备案

            Asset asset = assetDao.selectByCode(req.getParentAssetCode());
            if(null != asset){
                exchangeRegister.setRepaymentType(asset.getRepaymentType());
            }

            IssuePlan issuePlan = issuePlanDao.queryIssuePlanByAssetCode(req.getParentAssetCode());
            if(null != issuePlan){
                exchangeRegister.setRegisterValueStartTime(issuePlan.getProductValueStartTime());
                exchangeRegister.setRegisterValueEndTime(issuePlan.getProductValueEndTime());
                exchangeRegister.setRegisterExpireTime(issuePlan.getProductValueEndTime());
                exchangeRegister.setRegisterTime(new Date());
                exchangeRegister.setValueDays(issuePlan.getProductDayCount());
            }

            //按照规则生成备案批次号
            String registerNo = sequenceService.generateRegisterNo(
                    req.getTradeInstCode(), Constants.BATCH_NAME_SEQUENCE_LENGTH);
            exchangeRegister.setExchangeRegisterBatchNo(registerNo);

            //按照规则生成资产编号
            String assetCode = sequenceService.generateBusinessCode(
                    Constants.ASSET_CODE_PREFIX, Constants.ASSEET_CODE_SEQUENCE_LENGTH);
            exchangeRegister.setAssetCode(assetCode);

            assetExchangeRegisterDao.insertSelective(exchangeRegister);

            resp.setAssetCode(assetCode);
        }else {//更新备案记录
            AssetExchangeRegister isRegister = assetExchangeRegisterDao.selectByAssetCode(req.getAssetCode());
            if(null == isRegister){
                RegisterExchangeAssetResponse response =  new BaseResponse().build(RegisterExchangeAssetResponse.class);
                response.setRespCode(Constants.EXCHANGE_REGISTER_NOT_EXIST_CODE);
                response.setRespMsg(Constants.EXCHANGE_REGISTER_NOT_EXIST_DESC);
                return response;
            }
            isRegister.setAssetCode(req.getAssetCode());
            isRegister.setDelistedInstCode(req.getDelistedInstCode());
            isRegister.setListedInstCode(req.getListedInstCode());
            isRegister.setTradeInstCode(req.getTradeInstCode());
//            isRegister.setRegisterStatus(AssetRegisterExchangeEnum.REGISTERING.getValue());//备案中

            assetExchangeRegisterDao.updateByPrimaryKeySelective(isRegister);
            resp.setAssetCode(isRegister.getAssetCode());
        }

        return resp;
    }

    /**
     * 更新备案状态
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BaseResponse updateRegisterExchangeInfo(@Valid UpdateRegisterExchangeInfoRequest req) throws Exception {
        if(AssetRegisterExchangeEnum.REGISTERED.getCode() != req.getRegisterStatus() &&
               AssetRegisterExchangeEnum.REGISTER_FAILED.getCode() != req.getRegisterStatus()){
            return BaseResponse.build(Constants.UNKNOWN_EXCHANGE_REGISTER_CODE,Constants.UNKNOWN_EXCHANGE_REGISTER_DESC);
        }

        AssetExchangeRegister exchangeRegister = assetExchangeRegisterDao.selectByAssetCode(req.getAssetCode());
        if(null == exchangeRegister){
            return BaseResponse.build(Constants.EXCHANGE_REGISTER_NOT_EXIST_CODE,Constants.EXCHANGE_REGISTER_NOT_EXIST_DESC);
        }

        if(AssetRegisterExchangeEnum.REGISTERED.getCode() == req.getRegisterStatus()){//备案成功
            if(AssetRegisterExchangeEnum.REGISTERED.getValue().equals(exchangeRegister.getRegisterStatus())){
                return BaseResponse.build();
            }

            exchangeRegister.setRegisterStatus(AssetRegisterExchangeEnum.REGISTERED.getValue());
            assetExchangeRegisterDao.updateByPrimaryKeySelective(exchangeRegister);

            Asset newAsset = new Asset();
            newAsset.setAssetName(exchangeRegister.getRegisterName());
            newAsset.setAssetCode(exchangeRegister.getAssetCode());
            newAsset.setAssetAmount(exchangeRegister.getRegisterAmount());
            newAsset.setStockAmount(exchangeRegister.getRegisterAmount());//库存和资产总规模一致
            newAsset.setFinanceSubjectCode(exchangeRegister.getListedInstCode());//融资方等于备案记录挂牌方
            newAsset.setProvideLoanCode(exchangeRegister.getDelistedInstCode());//出资方等于备案记录摘牌方
            newAsset.setCollectStatus(AssetCollectStatusEnum.WAIT_ESTABLISH.getCode());//待成立
            newAsset.setApprovalStatus(AssetApprovalStatusEnum.APPROVAL_SUCCESS.getCode());//审核通过
            newAsset.setValueDays(exchangeRegister.getValueDays());
            newAsset.setEstablishTime(exchangeRegister.getRegisterValueStartTime());
            newAsset.setValueStartTime(exchangeRegister.getRegisterValueStartTime());
            newAsset.setValueEndTime(exchangeRegister.getRegisterValueEndTime());
            newAsset.setExpireTime(exchangeRegister.getRegisterValueEndTime());
            newAsset.setRepaymentType(exchangeRegister.getRepaymentType());
            newAsset.setYieldRate(exchangeRegister.getRegisterRate());
            newAsset.setRegisterType(RegisterTypeEnum.REGISTER.getCode());//备案录入
            newAsset.setStorageStatus(StorageTypeEnum.STORAGED.getCode());//已入库

            Asset asset = assetDao.selectByCode(exchangeRegister.getParentAssetCode());
            if(null != asset){
                newAsset.setAssetType(asset.getAssetType());
                newAsset.setApprovalSign(asset.getApprovalSign());
                newAsset.setParentAssetCode(asset.getAssetCode());
                newAsset.setPublishInfo(asset.getPublishInfo());
            }

            assetDao.insertSelective(newAsset);
        }else {//备案失败
            if(AssetRegisterExchangeEnum.REGISTER_FAILED.getValue().equals(exchangeRegister.getRegisterStatus())){
                return BaseResponse.build();
            }

            exchangeRegister.setRegisterStatus(AssetRegisterExchangeEnum.REGISTER_FAILED.getValue());
            assetExchangeRegisterDao.updateByPrimaryKeySelective(exchangeRegister);

            Asset asset = assetDao.selectByCode(exchangeRegister.getParentAssetCode());
            asset.setStockAmount(asset.getStockAmount().add(exchangeRegister.getRegisterAmount()));
            assetDao.updateSelectiveWithLock(asset);
        }

        return BaseResponse.build();
    }

    /**
     * 授信资产录入审核列表
     */
    @Override
    public PageQueryResponse<AssetModel> queryCreditAssetListForApproval(QueryCreditAssetListRequest req) throws Exception {
        PageQueryResponse<AssetModel> response = BaseResponse.build(PageQueryResponse.class);
        Page page = new Page();
        PropertyUtils.copyProperties(page, req);

        Map<String,Object> pram = new HashMap<>();
        pram.put("valueStartTime",req.getValueStartTime());
        pram.put("valueEndTime",req.getValueEndTime());
        pram.put("repaymentType",req.getRepaymentType());
        pram.put("sign",req.getSign());
        List<AssetModel> assetModels = null;
        int totalCount = assetDao.queryCreditAssetListForApprovalCount(pram);
        if(totalCount > 0){
            List<Asset> assetList = assetDao.queryCreditAssetListForApproval(pram,page);

            if(null != assetList && assetList.size() > 0){
                assetModels = BeanUtils.copyAs(assetList,AssetModel.class);

                for (int i=0;i < assetModels.size();i++){
                    AssetModel model = assetModels.get(i);

                    BusinessCredit businessCredit = businessCreditDao.selectByAssetCode(model.getAssetCode());
                    BusinessCreditModel businessCreditModel = new BusinessCreditModel();
                    if(null != businessCredit){
                        businessCreditModel = BeanUtils.copyAs(businessCredit,BusinessCreditModel.class);
                    }
                    model.setBusinessCreditModel(businessCreditModel);

                    IssuePlan issuePlan = issuePlanDao.queryIssuePlanByAssetCode(model.getAssetCode());
                    IssuePlanModel issuePlanModel = new IssuePlanModel();
                    if(null != issuePlan){
                        issuePlanModel = BeanUtils.copyAs(issuePlan,IssuePlanModel.class);
                    }
                    model.setIssuePlanModel(issuePlanModel);
                }
            }
        }
        response.setTotalCount(totalCount);
        response.setDataList(assetModels);
        response.setPageSize(page.getPageSize());
        response.setPageNo(page.getPageNo());
        return response;
    }

    /**
     * 审核
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BaseResponse approvalCreditAsset(@Valid ApprovalCreditAssetRequest req) throws Exception {
        Asset asset = assetDao.selectByCode(req.getAssetCode());
        //1.判断资产是否存在
        if (asset == null) {
            return BaseResponse.build(Constants.ASSET_NOT_EXIST_CODE, Constants.ASSET_NOT_EXIST_DESC);
        }
        //2.判断资产审核状态
        if (asset.getApprovalStatus() != AssetApprovalStatusEnum.WAIT_APPROVAL.getCode()) {
            return BaseResponse.build(Constants.ASSET_NOT_NEED_APPROVAL_CODE, Constants.ASSET_NOT_NEED_APPROVAL_DESC);
        }
        //3.判断资产需要审核权限
        if (StringUtils.isBlank(asset.getApprovalRequireSign())) {
            return BaseResponse.build(Constants.ASSET_NOT_NEED_APPROVAL_CODE, Constants.ASSET_NOT_NEED_APPROVAL_DESC);
        }
        //4.判断当前是否到达授权等级
        String[] requireSigns = asset.getApprovalRequireSign().split(",");
        String requireSign = requireSigns[0];
        if (!requireSign.equals(req.getSign())) {
            return BaseResponse.build(Constants.ASSET_NOT_NEED_CURRENT_SIGN_CODE, Constants.ASSET_NOT_NEED_CURRENT_SIGN_DESC);
        }
        //5.判断此人是否已经授权过相同等级
        AssetApproval assetApproval = new AssetApproval();
        assetApproval.setAssetCode(req.getAssetCode());
        assetApproval.setSign(req.getSign());
        assetApproval.setApprovalBy(req.getApprovalBy());
        List<AssetApproval> signedApprovals = assetApprovalDao.select(assetApproval, new Page());
        if (!org.springframework.util.CollectionUtils.isEmpty(signedApprovals)) {
            return BaseResponse.build(Constants.ASSET_CAN_NOT_SIGN_BY_SAME_USER_CODE, Constants.ASSET_CAN_NOT_SIGN_BY_SAME_USER_DESC);
        }
        assetApproval.setAssetId(asset.getId());
        assetApproval.setApprovalSuggestion(req.getApprovalSuggestion());

        String signed = (StringUtils.isBlank(asset.getApprovalSign())?"":asset.getApprovalSign() + ",") + req.getSign();
        asset.setApprovalSign(signed);
        if (req.getApprovalStatus() == AssetApprovalStatusEnum.APPROVAL_SUCCESS.getCode()) {
            if (requireSigns.length > 1) {
                asset.setApprovalRequireSign(asset.getApprovalRequireSign().substring(asset.getApprovalRequireSign().indexOf(",") + 1));
                asset.setApprovalStatus(AssetApprovalStatusEnum.WAIT_APPROVAL.getCode());
            } else {
                asset.setApprovalRequireSign("");
                asset.setApprovalStatus(AssetApprovalStatusEnum.APPROVAL_SUCCESS.getCode());
                asset.setCollectStatus(AssetCollectStatusEnum.WAIT_ESTABLISH.getCode());//募集状态为待成立

                //查询该批次是否有审核未通过资产，若没有则全部入库
                List<Asset> unApprovalAssets = assetIssueRelationDao.selectUnApprovalAssetByCode(asset.getAssetCode());//待审核审核资产
                List<Asset> approvalAssets = assetIssueRelationDao.selectApprovaledAssetByCode(asset.getAssetCode());//审核未通过资产
                if(unApprovalAssets.size() == 1 && approvalAssets.size() == 0){
                    asset.setStorageStatus(StorageTypeEnum.STORAGED.getCode());
                    assetIssueRelationDao.updateAssetByCode(asset.getAssetCode());
                }
            }
        } else {
            asset.setApprovalRequireSign("");
            asset.setApprovalStatus(AssetApprovalStatusEnum.APPROVAL_FAILURE.getCode());
            asset.setCollectStatus(AssetCollectStatusEnum.CAN_NOT_USE.getCode());

            assetIssueRelationDao.updateAssetApprovalFail(asset.getAssetCode());//一条审核不过，该批次全部不过
        }

        if (assetDao.updateSelectiveWithLock(asset) <= 0) {
            throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
        }
        assetApproval.setApprovalStatus(asset.getApprovalStatus());
        assetApprovalDao.insertSelective(assetApproval);
        return BaseResponse.build();
    }

    /**
     * 备案详情
     */
    @Override
    public QueryResponse<AssetExchangeRegisterModel> queryAssetRegisterExchange(@Valid QueryAssetRegisterExchangeRequest req) throws Exception {
        QueryResponse<AssetExchangeRegisterModel> resp = BaseResponse.build(QueryResponse.class);
        AssetExchangeRegister exchangeRegister = assetExchangeRegisterDao.selectByAssetCode(req.getAssetCode());
        if(null != exchangeRegister){
            AssetExchangeRegisterModel assetExchangeRegisterModel = BeanUtils.copyAs(exchangeRegister,AssetExchangeRegisterModel.class);
            resp.setData(assetExchangeRegisterModel);
        }
        return resp;
    }

    /**
     * 授信资产生成合同列表
     */
    @Override
    public PageQueryResponse<AssetModel> queryUnPackageAssetList(QueryUnPackageAssetRequest req) throws Exception {
        PageQueryResponse<AssetModel> response = BaseResponse.build(PageQueryResponse.class);
        Page page = new Page();
        PropertyUtils.copyProperties(page, req);

        Map<String,Object> pram = new HashMap<>();
        pram.put("assetCode",req.getAssetCode());
        pram.put("assetName",req.getAssetName());
        pram.put("financeName",req.getFinanceName());
        pram.put("approvalStartTime",req.getApprovalStartTime());
        pram.put("approvalEndTime",req.getApprovalEndTime());
        pram.put("contractStatus",req.getContractStatus());
        pram.put("registerType",RegisterTypeEnum.RECORD.getCode());
        pram.put("approvalStatus",AssetApprovalStatusEnum.APPROVAL_SUCCESS.getCode());
//        pram.put("templateCode",TemplateTypeEnum.TEMPLATE_4.getValue());
        pram.put("templateStatus",ContractGenerateTypeEnum.YES.getCode());
        List<AssetModel> assetModels = new ArrayList<>();
        int totalCount = assetDao.queryUnPackageAssetListCount(pram);
        if(totalCount > 0){
            List<Asset> assetList = assetDao.queryUnPackageAssetList(pram,page);

            if(null != assetList && assetList.size() > 0){

                for (Asset asset : assetList){
                    AssetModel model = new AssetModel();
                    BeanUtils.copy(asset,model);
                    FinanceSubject financeSubject = asset.getFinanceSubject();
                    if(null != financeSubject){
                        FinanceSubjectModel financeSubjectModel = BeanUtils.copyAs(financeSubject,FinanceSubjectModel.class);
                        model.setFinanceSubjectModel(financeSubjectModel);
                    }

                    BusinessCredit businessCredit = asset.getBusinessCredit();
                    if(null != businessCredit){
                        BusinessCreditModel businessCreditModel = BeanUtils.copyAs(businessCredit,BusinessCreditModel.class);
                        model.setBusinessCreditModel(businessCreditModel);
                    }

                    IssuePlan issuePlan = issuePlanDao.queryIssuePlanByAssetCode(asset.getAssetCode());
                    if(null != issuePlan){
                        IssuePlanModel issuePlanModel = BeanUtils.copyAs(issuePlan,IssuePlanModel.class);
                        model.setIssuePlanModel(issuePlanModel);
                    }

                    ContractInfoModel infoModel = new ContractInfoModel();
                    Map<String,Object> param = new HashMap<>();
                    param.put("assetCode", asset.getAssetCode());
                    param.put("status", ContractGenerateTypeEnum.YES.getCode());
                    List<FileTemplateParam> params = fileTemplateParamDao.selectByAssetCode(param);
                    if(null !=params && params.size() > 0){
                        infoModel.setContractStatus(ContractGenerateTypeEnum.YES.getDesc());
                    }else {
                        infoModel.setContractStatus(ContractGenerateTypeEnum.NO.getDesc());
                    }

                    AssetApproval approval = asset.getApproval();
                    if(null != approval){
                        infoModel.setApprovalTime(approval.getApprovalTime());
                    }
                    model.setInfoModel(infoModel);
                    assetModels.add(model);
                }

            }
        }
        response.setTotalCount(totalCount);
        response.setDataList(assetModels);
        response.setPageNo(page.getPageNo());
        response.setPageSize(page.getPageSize());
        return response;
    }

    /**
     * 授信资产生成合同详情
     */
    @Override
    public QueryResponse<UnPackageAssetDetailModel> queryUnPackageAssetDetail(@Valid QueryUnPackageAssetDetailRequest req) throws Exception {
        QueryResponse response = BaseResponse.build(QueryResponse.class);

        UnPackageAssetDetailModel detailModel = new UnPackageAssetDetailModel();
        Asset asset = assetDao.selectDetailByCode(req.getAssetCode());
        if(null == asset){
            response = BaseResponse.build(QueryResponse.class,Constants.ASSET_NOT_EXIST_CODE,Constants.ASSET_NOT_EXIST_DESC);
            return response;
        }
        IssuePlan issuePlan = issuePlanDao.queryIssuePlanByAssetCode(req.getAssetCode());
        if(null != issuePlan){
            detailModel.setIssuePlanModel(BeanUtils.copyAs(issuePlan,IssuePlanModel.class));
        }

        BusinessCredit businessCredit = businessCreditDao.selectByBatchNo(issuePlan.getBatchNo());
        if(null != businessCredit){
            detailModel.setBusinessCreditModel(BeanUtils.copyAs(businessCredit,BusinessCreditModel.class));
        }

        FinanceSubject financeSubject = financeSubjectDao.selectByCode(asset.getFinanceSubjectCode());
        FinanceSubjectModel financeSubjectModel = null;
        if(null != financeSubject){
            financeSubjectModel = BeanUtils.copyAs(financeSubject,FinanceSubjectModel.class);
            detailModel.setFinanceSubjectModel(financeSubjectModel);
        }

        ContractInfoModel infoModel = new ContractInfoModel();
        Map<String,Object> map = new HashMap<>();
        map.put("assetCode",asset.getAssetCode());
        map.put("status",ContractGenerateTypeEnum.YES.getCode());
        map.put("templateCode","ddrz-04");
        List<FileTemplateParam> params = fileTemplateParamDao.selectByAssetCode(map);
        if(null !=params && params.size() > 0){
            infoModel.setContractStatus(ContractGenerateTypeEnum.YES.getCode()+"");
        }else {
            infoModel.setContractStatus(ContractGenerateTypeEnum.NO.getCode()+"");
        }
        detailModel.setInfoModel(infoModel);

        AssetExchangeRegisterModel registerModel = new AssetExchangeRegisterModel();
        AssetExchangeRegister register = assetExchangeRegisterDao.selectByAssetCode(asset.getAssetCode());
        if(null != register){
            registerModel = BeanUtils.copyAs(register,AssetExchangeRegisterModel.class);
            registerModel.setRegisterStatus(AssetRegisterExchangeEnum.getEnumItem(register.getRegisterStatus()).getCode() + "");


            FinanceSubject delistedInst = financeSubjectDao.selectByCode(register.getDelistedInstCode());//摘牌方
            FinanceSubjectModel delistedInstModel = null;
            if(null != delistedInst){
                delistedInstModel = BeanUtils.copyAs(delistedInst,FinanceSubjectModel.class);
                detailModel.setDelistedInstModel(delistedInstModel);
            }

            FinanceSubject listedInst = financeSubjectDao.selectByCode(register.getListedInstCode());//挂牌方
            FinanceSubjectModel listedInstModel = null;
            if(null != listedInst){
                listedInstModel = BeanUtils.copyAs(listedInst,FinanceSubjectModel.class);
                detailModel.setListedInstModel(listedInstModel);
            }

            Exchange exchange = exchangeDao.selectByCode(register.getTradeInstCode());//交易所
            ExchangeModel exchangeModel = null;
            if(null != exchange){
                exchangeModel = BeanUtils.copyAs(exchange,ExchangeModel.class);
                detailModel.setExchangeModel(exchangeModel);
            }
        }else {
            registerModel.setRegisterStatus(AssetRegisterExchangeEnum.REGISTERING.getCode()+"");
        }
        detailModel.setRegisterModel(registerModel);

        Map<String,Object> param = new HashMap<>();
        param.put("assetCode",req.getAssetCode());
        param.put("templateCode","ddrz-04");
        List<FileTemplateParam> paramList = fileTemplateParamDao.selectByAssetCode(param);

        detailModel.setAssetModel(BeanUtils.copyAs(asset,AssetModel.class));

        detailModel.setParamModels(BeanUtils.copyAs(paramList,FileTemplateParamModel.class));
        response.setData(detailModel);

        return response;
    }

    /**
     * 交易所备案资产查询行表
     */
    @Override
    public PageQueryResponse<AssetModel> queryRecordAssetList(@Valid QueryRecordAssetListRequest req) throws Exception {
        PageQueryResponse<AssetModel> response = BaseResponse.build(PageQueryResponse.class);
        Page page = new Page();
        PropertyUtils.copyProperties(page, req);

        Map<String,Object> pram = new HashMap<>();
        pram.put("assetName",req.getAssetName());

        if(null != req.getRegisterStatus() && !req.getRegisterStatus().equals("")){
            Integer reg = Integer.valueOf(req.getRegisterStatus());
            pram.put("registerStatus",null == AssetRegisterExchangeEnum.getEnumItem(reg)?"":AssetRegisterExchangeEnum.getEnumItem(reg).getValue());
        }
        pram.put("batchNo",req.getBatchNo());
//        pram.put("tradeInstCode",req.getTradeInstCode());
//        pram.put("listedInstCode",req.getListedInstCode());
//        pram.put("delistedInstCode",req.getDelistedInstCode());
        pram.put("startValueTime",req.getStartValueTime());
        pram.put("endValueTime",req.getEndValueTime());
        pram.put("registerType",RegisterTypeEnum.RECORD.getCode());
        pram.put("approvalStatus",AssetApprovalStatusEnum.APPROVAL_SUCCESS.getCode());
        pram.put("contractStatus",req.getContractStatus());
        pram.put("templateCode",TemplateTypeEnum.TEMPLATE_4.getValue());
        pram.put("templateStatus",ContractGenerateTypeEnum.YES.getCode());
        List<AssetModel> assetModels = new ArrayList<>();
        int totalCount = assetDao.queryRecordAssetListCount(pram);
        if(totalCount > 0){
            List<Asset> assetList = assetDao.queryRecordAssetList(pram, page);

            if(null != assetList && assetList.size() > 0){

                for (Asset asset : assetList){
                    AssetModel model = new AssetModel();
                    BeanUtils.copy(asset,model);

                    IssuePlanModel issuePlanModel = new IssuePlanModel();
                    IssuePlan issuePlan = asset.getIssuePlan();
                    if(null != issuePlan){
                        issuePlanModel = BeanUtils.copyAs(issuePlan,IssuePlanModel.class);
                    }
                    model.setIssuePlanModel(issuePlanModel);

//                    FinanceSubject financeSubject = asset.getFinanceSubject();
//                    FinanceSubjectModel financeSubjectModel = new FinanceSubjectModel();
//                    if(null != financeSubject){
//                        financeSubjectModel = BeanUtils.copyAs(financeSubject,FinanceSubjectModel.class);
//                    }
//
//                    BusinessCredit businessCredit = businessCreditDao.selectByAssetCode(asset.getAssetCode());
//                    BusinessCreditModel businessCreditModel = new BusinessCreditModel();
//                    if(null != businessCredit){
//                        businessCreditModel = BeanUtils.copyAs(businessCredit,BusinessCreditModel.class);
//                    }
//                    model.setBusinessCreditModel(businessCreditModel);
//                    model.setFinanceSubjectModel(financeSubjectModel);

//                    AssetExchangeRegisterModel registerModel = new AssetExchangeRegisterModel();
//                    AssetExchangeRegister register = assetExchangeRegisterDao.selectByAssetCode(model.getAssetCode());
//                    if(null != register){
//                        registerModel = BeanUtils.copyAs(register,AssetExchangeRegisterModel.class);
//                        registerModel.setRegisterStatus(AssetRegisterExchangeEnum.getEnumItem(register.getRegisterStatus()).getCode() + "");
//                    }else {
//                        registerModel.setRegisterStatus(AssetRegisterExchangeEnum.UN_REGISTER.getCode()+"");
//                    }
//                    model.setRegisterModel(registerModel);

                    ContractInfoModel infoModel = new ContractInfoModel();
//                    Map<String,Object> param = new HashMap<>();
//                    param.put("assetCode",asset.getAssetCode());
//                    param.put("status",ContractGenerateTypeEnum.YES.getCode());
//                    param.put("templateCode","ddrz-04");
//                    List<FileTemplateParam> params = fileTemplateParamDao.selectByAssetCode(param);
//                    if(null !=params && params.size() > 0){
//                        infoModel.setContractStatus(ContractGenerateTypeEnum.YES.getCode()+"");
//                    }else {
//                        infoModel.setContractStatus(ContractGenerateTypeEnum.NO.getCode()+"");
//                    }
                    Map<String,Object> param = new HashMap<>();
                    param.put("assetCode",asset.getAssetCode());

                    int count = assetExchangeRegisterDao.selectExchangeRegisterListByAssetCodeCount(param);
                    infoModel.setRegisterRecordCount(count);
                    model.setInfoModel(infoModel);

                    assetModels.add(model);
                }

            }
        }
        response.setTotalCount(totalCount);
        response.setDataList(assetModels);
        response.setPageNo(page.getPageNo());
        response.setPageSize(page.getPageSize());
        return response;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BaseResponse generateContract(GenerateContractRequest req) throws Exception {
        Map<String,Object> param = new HashMap<>();
        param.put("assetCode",req.getAssetCode());
        param.put("templateCode",req.getTemplateCode());
        List<FileTemplateParam> paramList = fileTemplateParamDao.selectByAssetCode(param);
        if(null == paramList || paramList.size() == 0){
            return BaseResponse.build(Constants.TEMPLATE_PARAM_NOT_EXIST_CODE,Constants.TEMPLATE_PARAM_NOT_EXIST_DESC);
        }else {
            FileTemplateParam templateParam = paramList.get(0);
            templateParam.setStatus(ContractGenerateTypeEnum.YES.getCode());
            fileTemplateParamDao.updateByPrimaryKeySelective(templateParam);
        }
        return BaseResponse.build();
    }

    @Override
    public PageQueryResponse<AssetForTxsModel> queryAssetListForTxs(QueryAssetListForTxsRequest req) throws Exception {
        PageQueryResponse<AssetForTxsModel> response = new PageQueryResponse<>();

        Map<String,Object> pram = new HashMap<>();
        pram.put("registerType",RegisterTypeEnum.REGISTER.getCode());
        pram.put("approvalStatus",AssetApprovalStatusEnum.APPROVAL_SUCCESS.getCode());
        pram.put("registerStatus",AssetRegisterExchangeEnum.REGISTERED.getValue());
        pram.put("storageStatus",StorageTypeEnum.STORAGED.getCode());
        List<AssetForTxsModel> txsModels = new ArrayList<>();
//        int totalCount = assetDao.queryRecordAssetListCount(pram);
//        if(totalCount > 0){
            List<Asset> assetList = assetDao.queryAssetListForTxs(pram);

            for (Asset asset : assetList){
                AssetForTxsModel txsModel = new AssetForTxsModel();

//                FinanceSubject financeSubject = financeSubjectDao.selectByCode(asset.getFinanceSubjectCode());
//                FinanceSubjectModel financeSubjectModel = new FinanceSubjectModel();
//                if(null != financeSubject){
//                    financeSubjectModel = BeanUtils.copyAs(financeSubject,FinanceSubjectModel.class);
//                }

                txsModel.setAssetName(asset.getAssetName());//资产名称
                txsModel.setAssetCode(asset.getAssetCode());
                txsModel.setAssetDisclosure(asset.getPublishInfo());//披露信息
                txsModel.setAssetType(AssetTypeEnum.getEnumItem(asset.getAssetType()).getDesc());//融资类型
                txsModel.setAssetAmount(asset.getAssetAmount());//募集金额

                AssetExchangeRegister register = assetExchangeRegisterDao.selectByAssetCode(asset.getAssetCode());
                if(null != register){
                    txsModel.setRegisterName(register.getRegisterName());//备案名称
                    txsModel.setYieldRate(register.getRegisterRate());//备案利率

                    FinanceSubject listedInst = financeSubjectDao.selectByCode(register.getListedInstCode());
                    if(null != listedInst){
                        txsModel.setListedInstName(listedInst.getSubjectName());//挂牌方
                    }

                    FinanceSubject delistedInst = financeSubjectDao.selectByCode(register.getDelistedInstCode());
                    if(null != delistedInst){
                        txsModel.setDelistedInstName(delistedInst.getSubjectName());//摘牌方
                    }

                    Exchange exchange = exchangeDao.selectByCode(register.getTradeInstCode());//交易所
                    if(null != exchange) {
                        txsModel.setTradeInstName(exchange.getExchangeName());//交易所
                    }

                    IssuePlan issuePlan = issuePlanDao.queryIssuePlanByAssetCode(register.getParentAssetCode());
                    if(null != issuePlan){
                        txsModel.setProductDayCount(issuePlan.getProductDayCount());//期限
                        txsModel.setSaleStartTime(issuePlan.getSaleStartTime());//募集开始
                        txsModel.setSaleEndTime(issuePlan.getSaleEndTime());//募集结束
                        txsModel.setProductValueStartTime(issuePlan.getProductValueStartTime());//产品起息日
                        txsModel.setProductValueEndTime(issuePlan.getProductValueEndTime());//产品结息日
                    }
                }

                txsModels.add(txsModel);
//            }
        }
//        response.setTotalCount(totalCount);
        response.setDataList(txsModels);
        return response;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BaseResponse updateAssetForTxs(UpdateAssetForTxsRequest req) throws Exception {

        List<String> assetCodes = req.getAssetCodes();
        if(null != assetCodes && assetCodes.size() > 0){

            for(String assetCode:assetCodes){
                Asset asset = assetDao.selectDetailByCode(assetCode);
                if(null != asset){
                    Asset assetUpdate = new Asset();
                    assetUpdate.setId(asset.getId());
                    assetUpdate.setAssetCode(asset.getAssetCode());
                    assetUpdate.setDistributeAmount(asset.getAssetAmount());
                    assetUpdate.setStockAmount(BigDecimal.ZERO);
                    assetUpdate.setVersion(asset.getVersion());
                    assetDao.updateSelectiveWithLock(assetUpdate);
                }
            }

        }
        return BaseResponse.build();
    }

    @Override
    public PageQueryResponse<AssetExchangeRegisterModel> queryExchangeRegisterRecordList(QueryExchangeRegisterRecordListRequest req) throws Exception {
        PageQueryResponse<AssetExchangeRegisterModel> response = new PageQueryResponse<>();
        Page page = new Page();
        PropertyUtils.copyProperties(page, req);
        Map<String,Object> param = new HashMap<>();
        param.put("assetCode",req.getAssetCode());

        int totalCount = assetExchangeRegisterDao.selectExchangeRegisterListByAssetCodeCount(param);
        List<AssetExchangeRegisterModel> registerModels = new ArrayList<>();
        if(totalCount > 0){
            List<AssetExchangeRegister> registerList = assetExchangeRegisterDao.selectExchangeRegisterListByAssetCode(param, Page.getNullPage());
            for (AssetExchangeRegister register : registerList){
                AssetExchangeRegisterModel registerModel = BeanUtils.copyAs(register,AssetExchangeRegisterModel.class);
                AssetModel assetModel = BeanUtils.copyAs(register.getAsset(),AssetModel.class);
                FileTemplateParamModel paramModel = BeanUtils.copyAs(register.getFileTemplateParam(),FileTemplateParamModel.class);

                if(null != register.getRegisterStatus() && !"".equals(register.getRegisterStatus())){
                    registerModel.setRegisterStatus(AssetRegisterExchangeEnum.getEnumItem(register.getRegisterStatus()).getCode() + "");
                }else {
                    registerModel.setRegisterStatus(AssetRegisterExchangeEnum.REGISTERING.getCode()+"");
                }
                registerModel.setAssetModel(assetModel);
                registerModel.setParamModel(paramModel);
                registerModels.add(registerModel);
            }
        }
        response.setTotalCount(totalCount);
        response.setPageNo(page.getPageNo());
        response.setPageSize(page.getPageSize());
        response.setDataList(registerModels);

        return response;
    }

    @Override
    public QueryResponse<UnPackageAssetDetailModel> queryExchangeRegisterRecordDetail(QueryExchangeRegisterDetailRequest req) throws Exception {
        QueryResponse response = BaseResponse.build(QueryResponse.class);

        UnPackageAssetDetailModel detailModel = new UnPackageAssetDetailModel();

        Asset asset = new Asset();
        AssetExchangeRegisterModel registerModel = new AssetExchangeRegisterModel();
        AssetExchangeRegister register = assetExchangeRegisterDao.selectByAssetCode(req.getAssetCode());
        if(null != register){
            registerModel = BeanUtils.copyAs(register,AssetExchangeRegisterModel.class);
            registerModel.setRegisterStatus(AssetRegisterExchangeEnum.getEnumItem(register.getRegisterStatus()).getCode() + "");


            FinanceSubject delistedInst = financeSubjectDao.selectByCode(register.getDelistedInstCode());//摘牌方
            FinanceSubjectModel delistedInstModel = null;
            if(null != delistedInst){
                delistedInstModel = BeanUtils.copyAs(delistedInst,FinanceSubjectModel.class);
                detailModel.setDelistedInstModel(delistedInstModel);
            }

            FinanceSubject listedInst = financeSubjectDao.selectByCode(register.getListedInstCode());//挂牌方
            FinanceSubjectModel listedInstModel = null;
            if(null != listedInst){
                listedInstModel = BeanUtils.copyAs(listedInst,FinanceSubjectModel.class);
                detailModel.setListedInstModel(listedInstModel);
            }

            Exchange exchange = exchangeDao.selectByCode(register.getTradeInstCode());//交易所
            ExchangeModel exchangeModel = null;
            if(null != exchange){
                exchangeModel = BeanUtils.copyAs(exchange,ExchangeModel.class);
                detailModel.setExchangeModel(exchangeModel);
            }

            asset = assetDao.selectDetailByCode(register.getParentAssetCode());
            if(null == asset){
                response = BaseResponse.build(QueryResponse.class,Constants.ASSET_NOT_EXIST_CODE,Constants.ASSET_NOT_EXIST_DESC);
                return response;
            }
        }
        detailModel.setRegisterModel(registerModel);

        IssuePlan issuePlan = issuePlanDao.queryIssuePlanByAssetCode(asset.getAssetCode());
        if(null != issuePlan){
            detailModel.setIssuePlanModel(BeanUtils.copyAs(issuePlan,IssuePlanModel.class));

            BusinessCredit businessCredit = businessCreditDao.selectByBatchNo(issuePlan.getBatchNo());
            if(null != businessCredit){
                detailModel.setBusinessCreditModel(BeanUtils.copyAs(businessCredit,BusinessCreditModel.class));
            }
        }


        FinanceSubject financeSubject = financeSubjectDao.selectByCode(asset.getFinanceSubjectCode());
        FinanceSubjectModel financeSubjectModel = null;
        if(null != financeSubject){
            financeSubjectModel = BeanUtils.copyAs(financeSubject,FinanceSubjectModel.class);
            detailModel.setFinanceSubjectModel(financeSubjectModel);
        }

        ContractInfoModel infoModel = new ContractInfoModel();
        Map<String,Object> map = new HashMap<>();
        map.put("assetCode",req.getAssetCode());
        map.put("status",ContractGenerateTypeEnum.YES.getCode());
        map.put("templateCode","ddrz-04");
        List<FileTemplateParam> params = fileTemplateParamDao.selectByAssetCode(map);
        if(null !=params && params.size() > 0){
            infoModel.setContractStatus(ContractGenerateTypeEnum.YES.getCode()+"");
        }else {
            infoModel.setContractStatus(ContractGenerateTypeEnum.NO.getCode()+"");
        }
        detailModel.setInfoModel(infoModel);


        Map<String,Object> param = new HashMap<>();
        param.put("assetCode",req.getAssetCode());
        param.put("templateCode","ddrz-04");
        List<FileTemplateParam> paramList = fileTemplateParamDao.selectByAssetCode(param);

        detailModel.setAssetModel(BeanUtils.copyAs(asset,AssetModel.class));

        detailModel.setParamModels(BeanUtils.copyAs(paramList,FileTemplateParamModel.class));
        response.setData(detailModel);

        return response;
    }
}
