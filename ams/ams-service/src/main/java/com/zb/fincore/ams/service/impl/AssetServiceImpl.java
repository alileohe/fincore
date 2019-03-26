package com.zb.fincore.ams.service.impl;

import com.zb.fincore.ams.common.Constants;
import com.zb.fincore.ams.common.aspect.validate.FieldValidator;
import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.common.enums.AssetApprovalStatusEnum;
import com.zb.fincore.ams.common.enums.AssetCollectStatusEnum;
import com.zb.fincore.ams.common.enums.RegisterTypeEnum;
import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.facade.dto.req.*;
import com.zb.fincore.ams.facade.dto.resp.BatchCreateAssetResponse;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetResponse;
import com.zb.fincore.ams.facade.model.AssetModel;
import com.zb.fincore.ams.facade.model.ErrorModel;
import com.zb.fincore.ams.facade.model.FinanceSubjectModel;
import com.zb.fincore.ams.service.AssetRepayPlanService;
import com.zb.fincore.ams.service.AssetService;
import com.zb.fincore.ams.service.SequenceService;
import com.zb.fincore.ams.service.dal.dao.AssetDao;
import com.zb.fincore.ams.service.dal.dao.FinanceSubjectDao;
import com.zb.fincore.ams.service.dal.dao.GlobalConfigDao;
import com.zb.fincore.ams.service.dal.model.Asset;
import com.zb.fincore.ams.service.dal.model.FinanceSubject;
import com.zb.fincore.ams.service.dal.model.GlobalConfig;
import com.zb.fincore.ams.service.validate.AssetServiceParameterValidator;
import com.zb.fincore.common.utils.BeanUtils;
import com.zb.fincore.common.utils.DateUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

/**
 * 功能: 资产服务接口实现
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 11:26
 * 版本: V1.0
 */
@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetServiceParameterValidator validator;

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private GlobalConfigDao globalConfigDao;

    @Autowired
    private AssetDao assetDao;

    @Autowired
    FinanceSubjectDao financeSubjectDao;

    @Autowired
    private AssetRepayPlanService assetRepayPlanService;

    @Autowired
    private FieldValidator fieldValidator;

    /**
     * 创建底层资产
     *
     * @param req 创建底层资产请求
     * @return 创建底层资产响应
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CreateAssetResponse createAsset(@Valid CreateAssetRequest req) throws Exception {
        //1: 执行请求参数校验
        CreateAssetResponse resp = validator.checkCreateAssetRequest(req);
        if (resp != null) {
            return resp;
        }

        resp = BaseResponse.build(CreateAssetResponse.class);
        //校验资产名称是否重复
        int totalCount = assetDao.selectAssetCountByName(req.getAssetName());
        if (totalCount > 0) {
            resp.setRespCode(Constants.ASSET_REPEAT_NAME_CODE);
            resp.setRespMsg(Constants.ASSET_REPEAT_NAME_DESC);
            return resp;
        }

        //校验融资方
        FinanceSubject financeSubject = financeSubjectDao.selectByCode(req.getFinanceSubjectCode());
        if (null == financeSubject) {
            resp.setRespCode(Constants.FINANCE_SUBJECT_NOT_EXIST_CODE);
            resp.setRespMsg(Constants.FINANCE_SUBJECT_NOT_EXIST_DESC);
            return resp;
        }
        req.setFinanceSubjectId(financeSubject.getId());

        //校验出资方
        if (StringUtils.isNotBlank(req.getProvideLoanCode())) {
            //出资方和融资方不可一致
            if(req.getProvideLoanCode().equals(req.getFinanceSubjectCode())){
                resp.setRespCode(Constants.LOAN_FINANCE_CODE_NOT_EQ_CODE);
                resp.setRespMsg(Constants.LOAN_FINANCE_CODE_NOT_EQ_DESC);
                return resp;
            }
            FinanceSubject loanSubject = financeSubjectDao.selectByCode(req.getProvideLoanCode());
            if (null == loanSubject) {
                resp.setRespCode(Constants.LOAN_SUBJECT_NOT_EXIST_CODE);
                resp.setRespMsg(Constants.LOAN_SUBJECT_NOT_EXIST_DESC);
                return resp;
            }
        }

        //按照规则生成资产编号
        String assetCode = sequenceService.generateBusinessCode(
                Constants.ASSET_CODE_PREFIX, Constants.ASSEET_CODE_SEQUENCE_LENGTH);
        //校验产品编码是否唯一
        Asset isRepeat = assetDao.selectByCode(assetCode);
        if (null != isRepeat) {
            resp.setRespCode(Constants.ASSET_CODE_REPEAT_CODE);
            resp.setRespMsg(Constants.ASSET_CODE_REPEAT_DESC);
            return resp;
        }

        //入库资产信息
        Asset asset = new Asset();
        PropertyUtils.copyProperties(asset, req);
        asset.setAssetCode(assetCode);
        GlobalConfig globalConfig = globalConfigDao.selectByPropertyName(Constants.ASSET_APPROVAL_SIGN_KEY);
        String requireSign = "";
        if (globalConfig != null) {
            requireSign = globalConfig.getPropertyValue();
        }
        asset.setApprovalRequireSign(requireSign);
        asset.setApprovalStatus(StringUtils.isNotBlank(requireSign) ?
                AssetApprovalStatusEnum.WAIT_APPROVAL.getCode() : AssetApprovalStatusEnum.APPROVAL_SUCCESS.getCode());
        asset.setStockAmount(req.getAssetAmount());//库存和总规模一致
        asset.setCollectStatus(AssetCollectStatusEnum.WAIT_COLLECT.getCode());//募集状态为待审核
        assetDao.insertSelective(asset);

        //4: 生成资产汇款计划
        assetRepayPlanService.createAssetReplayPlan(asset);

        //5: 返回结果
        resp.setId(asset.getId());
        resp.setAssetCode(assetCode);
        return resp;
    }

    /**
     * 批量创建底层资产
     *
     * @param req 批量创建底层资产请求
     * @return 批量创建底层资产请求
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public synchronized BatchCreateAssetResponse batchCreateAsset(@Valid BatchCreateAssetRequest<CreateAssetRequest> req) throws Exception {
        if (CollectionUtils.isEmpty(req.getCreateAssetRequestList())) {
            return BaseResponse.build(BatchCreateAssetResponse.class, Constants.BATCH_CREATE_LIST_EMPTY_CODE,
                    Constants.BATCH_CREATE_LIST_EMPTY_DESC);
        }
//        Map<String, List<String>> errorMap = new HashMap<>();
        List<ErrorModel> errorModelList = new ArrayList<>();
        Set set = new HashSet();
        int index = 1;
        int repeatSize = 0;
        for (CreateAssetRequest createReq : req.getCreateAssetRequestList()) {
            List<String> errors = fieldValidator.validateErrors(createReq);
            for (int i = 0; i < errors.size(); i++) {
                String errorStr = createReq.getAssetName() + "-" + errors.get(i);
                errors.set(i, errorStr);
            }
            //校验传入资产参数是否有重名
            set.add(createReq.getAssetName());
            if (set.size() + repeatSize != index) {
                errors.add(createReq.getAssetName() + "-" + Constants.ASSET_LIST_REPEAT_NAME_DESC);
                repeatSize++;
            }

            //校验资产是否重名
            int count = assetDao.selectAssetCountByName(createReq.getAssetName());
            if (count > 0) {
                errors.add(createReq.getAssetName() + "-" + Constants.ASSET_REPEAT_NAME_DESC);
            }

            //校验融资方
            if (StringUtils.isNotBlank(createReq.getFinanceSubjectCode())) {
                FinanceSubject financeSubject = financeSubjectDao.selectByCode(createReq.getFinanceSubjectCode());
                if (null == financeSubject) {
                    errors.add(createReq.getAssetName() + "-" + Constants.FINANCE_SUBJECT_NOT_EXIST_DESC);
                } else {
                    createReq.setFinanceSubjectId(financeSubject.getId());
                }
            }

            //校验出资方
            if (StringUtils.isNotBlank(createReq.getProvideLoanCode())) {
                //出资方和融资方不可一致
                if(createReq.getProvideLoanCode().equals(createReq.getFinanceSubjectCode())){
                    errors.add(createReq.getAssetName() + "-" + Constants.LOAN_FINANCE_CODE_NOT_EQ_DESC);
                }

                FinanceSubject loanSubject = financeSubjectDao.selectByCode(createReq.getProvideLoanCode());
                if (null == loanSubject) {
                    errors.add(createReq.getAssetName() + "-" + Constants.LOAN_SUBJECT_NOT_EXIST_DESC);
                }
            }

            //校验参数信息
            errors.addAll(validator.checkBatchCreateAssetRequest(createReq));
            if (CollectionUtils.isNotEmpty(errors)) {
                ErrorModel errorModel = new ErrorModel();
                errorModel.setErrorList(errors);
                errorModelList.add(errorModel);
//                errorMap.put(String.valueOf(index), errors);
            }
            index++;
        }
        BatchCreateAssetResponse resp = BaseResponse.build(BatchCreateAssetResponse.class);
        resp.setTotalCount(req.getCreateAssetRequestList().size());
//        resp.setErrorCount(errorMap.size());
//        resp.setErrorMap(errorMap);
        resp.setErrorCount(errorModelList.size());
        resp.setErrorModelList(errorModelList);
        if (resp.getErrorCount() > 0) {
            resp.setRespCode(Constants.PARAM_VALIDATE_ERROR_CODE);
            resp.setRespMsg(Constants.PARAM_VALIDATE_ERROR_DESC);
            return resp;
        }

        GlobalConfig globalConfig = globalConfigDao.selectByPropertyName(Constants.ASSET_APPROVAL_SIGN_KEY);
        String requireSign = "";
        if (globalConfig != null) {
            requireSign = globalConfig.getPropertyValue();
        }
        for (CreateAssetRequest createReq : req.getCreateAssetRequestList()) {
            //按照规则生成资产编号
            String assetCode = sequenceService.generateBusinessCode(
                    Constants.ASSET_CODE_PREFIX, Constants.ASSEET_CODE_SEQUENCE_LENGTH);

            //入库资产信息
            Asset asset = new Asset();
            PropertyUtils.copyProperties(asset, createReq);
            asset.setAssetCode(assetCode);
            asset.setApprovalRequireSign(requireSign);
            asset.setStockAmount(createReq.getAssetAmount());
            asset.setApprovalStatus(StringUtils.isNotBlank(requireSign) ?
                    AssetApprovalStatusEnum.WAIT_APPROVAL.getCode() : AssetApprovalStatusEnum.APPROVAL_SUCCESS.getCode());
            asset.setCollectStatus(AssetCollectStatusEnum.WAIT_COLLECT.getCode());//募集状态为待审核

            //期限等于到期日-起息日
            Integer valueDays = DateUtils.getBetweenDays(createReq.getValueStartTime(), createReq.getValueEndTime()) + 1;
            asset.setValueDays(valueDays);
            //利率
            BigDecimal yieldRate = createReq.getYieldRate().divide(new BigDecimal("100"));
            asset.setYieldRate(yieldRate);

            assetDao.insertSelective(asset);

            //生成资产汇款计划
            assetRepayPlanService.createAssetReplayPlan(asset);
        }
        return resp;
    }

    /**
     * 资产管理查询资产列表
     *
     * @param req 查询资产列表请求
     * @return 查询资产列表响应
     */
    @Override
    @Transactional(readOnly = true)
    public PageQueryResponse<AssetModel> queryAssetListForManage(QueryAssetListForManageRequest req) throws Exception {
        PageQueryResponse<AssetModel> resp = BaseResponse.build(PageQueryResponse.class);
        Page page = new Page();
        Asset asset = new Asset();
        PropertyUtils.copyProperties(page, req);
        PropertyUtils.copyProperties(asset, req);

        int totalCount = assetDao.selectDetailCount(asset);
        List<AssetModel> assetModels = new ArrayList<>();
        if (totalCount > 0) {
            List<Asset> assets = assetDao.selectDetail(asset, page);
            for (Asset a : assets) {
                AssetModel model = BeanUtils.copyAs(a, AssetModel.class);
                if (a.getFinanceSubject() != null) {
                    FinanceSubjectModel subjectModel = BeanUtils.copyAs(a.getFinanceSubject(), FinanceSubjectModel.class);
                    model.setFinanceSubjectModel(subjectModel);
                }
                assetModels.add(model);
            }
        }
        resp.setPageSize(page.getPageSize());
        resp.setPageNo(page.getPageNo());
        resp.setTotalCount(totalCount);
        resp.setDataList(assetModels);
        return resp;
    }

    /**
     * 资产审核列表
     *
     * @param req 资产审核列表请求
     * @return 查询资产审核列表响应
     */
    @Override
    public PageQueryResponse<AssetModel> queryAssetListForApproval(QueryAssetListForManageRequest req) throws Exception {
        PageQueryResponse<AssetModel> resp = BaseResponse.build(PageQueryResponse.class);
        Page page = new Page();
        Asset asset = new Asset();
        PropertyUtils.copyProperties(page, req);
        PropertyUtils.copyProperties(asset, req);
        asset.setApprovalStatus(AssetApprovalStatusEnum.WAIT_APPROVAL.getCode());//待审核
        asset.setRegisterType(RegisterTypeEnum.NORMAL.getCode());

        int totalCount = assetDao.selectDetailCount(asset);
        List<AssetModel> assetModels = new ArrayList<>();
        if (totalCount > 0) {
            List<Asset> assets = assetDao.selectDetail(asset, page);
            for (Asset a : assets) {
                AssetModel model = BeanUtils.copyAs(a, AssetModel.class);
                if (a.getFinanceSubject() != null) {
                    FinanceSubjectModel subjectModel = BeanUtils.copyAs(a.getFinanceSubject(), FinanceSubjectModel.class);
                    model.setFinanceSubjectModel(subjectModel);
                }
                assetModels.add(model);
            }
        }
        resp.setPageSize(page.getPageSize());
        resp.setPageNo(page.getPageNo());
        resp.setTotalCount(totalCount);
        resp.setDataList(assetModels);
        return resp;
    }

    /**
     * 查询资产详情
     *
     * @param req 查询资产详情请求
     * @return 查询资产详情响应
     */
    @Override
    public QueryResponse<AssetModel> queryAsset(@Valid QueryAssetRequest req) throws Exception {
        Asset asset = assetDao.selectDetailByCode(req.getAssetCode());
        if (null != asset) {
            AssetModel assetModel = BeanUtils.copyAs(asset, AssetModel.class);
            if (asset.getFinanceSubject() != null) {
                FinanceSubjectModel subjectModel = BeanUtils.copyAs(asset.getFinanceSubject(), FinanceSubjectModel.class);
                assetModel.setFinanceSubjectModel(subjectModel);
            }
            QueryResponse<AssetModel> resp = BaseResponse.build(QueryResponse.class);
            resp.setData(assetModel);
            return resp;
        } else {
            return BaseResponse.build(QueryResponse.class, Constants.PARAM_RESULTBLANK_CODE, Constants.PARAM_RESULTBLANK_DESC);
        }
    }

    /**
     * 资产成立
     *
     * @return
     * @throws Exception
     */
    @Override
    public BaseResponse putAssetEstablished() throws Exception {
        Asset asset = new Asset();
        asset.setApprovalStatus(AssetApprovalStatusEnum.APPROVAL_SUCCESS.getCode());
        asset.setCollectStatus(AssetCollectStatusEnum.WAIT_ESTABLISH.getCode());
        asset.setValueStartTime(new Date());
        List<Asset> assets = assetDao.queryAssetListForJob(asset);
        for (Asset model : assets) {
            model.setCollectStatus(AssetCollectStatusEnum.ESTABLISHED.getCode());
            assetDao.updateSelectiveWithLock(model);
        }
        return BaseResponse.build();
    }

    /**
     * 资产到期
     *
     * @return
     * @throws Exception
     */
    @Override
    public BaseResponse putAssetExpired() throws Exception {
        Asset asset = new Asset();
        asset.setApprovalStatus(AssetApprovalStatusEnum.APPROVAL_SUCCESS.getCode());
        asset.setCollectStatus(AssetCollectStatusEnum.ESTABLISHED.getCode());
        asset.setExpireTime(new Date());
        List<Asset> assets = assetDao.queryAssetListForJob(asset);
        for (Asset model : assets) {
            model.setCollectStatus(AssetCollectStatusEnum.CAN_NOT_USE.getCode());
            assetDao.updateSelectiveWithLock(model);
        }
        return BaseResponse.build();
    }

    /**
     * 更新资产披露信息
     *
     * @param req 更新资产披露信息请求对象
     * @return 通用响应对象
     */
    @Override
    public BaseResponse updateAssetPublishInfo(@Valid UpdateAssetPublishInfoRequest req) throws Exception {
        Asset asset = assetDao.selectByCode(req.getAssetCode());
        if (asset == null) {
            return BaseResponse.build(Constants.ASSET_NOT_EXIST_CODE, Constants.ASSET_NOT_EXIST_DESC);
        }
        asset.setPublishInfo(req.getPublishInfo());
        assetDao.updatePublishInfo(asset);
        return BaseResponse.build();
    }
}
