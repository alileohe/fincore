package com.zb.fincore.ams.service.impl;

import com.zb.fincore.ams.common.Constants;
import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.common.enums.PoolLoadSwitchEnum;
import com.zb.fincore.ams.common.enums.PoolTypeEnum;
import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.facade.dto.req.CreatePoolRequest;
import com.zb.fincore.ams.facade.dto.req.QueryPoolLeftAssetRequest;
import com.zb.fincore.ams.facade.dto.req.QueryPoolListRequest;
import com.zb.fincore.ams.facade.dto.req.QueryPoolRequest;
import com.zb.fincore.ams.facade.dto.resp.CreatePoolResponse;
import com.zb.fincore.ams.facade.model.FinanceSubjectModel;
import com.zb.fincore.ams.facade.model.PoolLeftAssetModel;
import com.zb.fincore.ams.facade.model.PoolModel;
import com.zb.fincore.ams.facade.model.TrusteeModel;
import com.zb.fincore.ams.service.PoolService;
import com.zb.fincore.ams.service.SequenceService;
import com.zb.fincore.ams.service.dal.dao.FinanceSubjectDao;
import com.zb.fincore.ams.service.dal.dao.PoolDao;
import com.zb.fincore.ams.service.dal.dao.TrusteeDao;
import com.zb.fincore.ams.service.dal.model.FinanceSubject;
import com.zb.fincore.ams.service.dal.model.Pool;
import com.zb.fincore.ams.service.dal.model.Trustee;
import com.zb.fincore.ams.service.validate.PoolServiceParameterValidator;
import com.zb.fincore.common.utils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能: 资产池服务接口实现
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 15:49
 * 版本: V1.0
 */
@Service
public class PoolServiceImpl implements PoolService {

    @Autowired
    private PoolDao poolDao;

    @Autowired
    FinanceSubjectDao financeSubjectDao;

    @Autowired
    TrusteeDao trusteeDao;

    @Autowired
    private PoolServiceParameterValidator validator;

    @Autowired
    private SequenceService sequenceService;

    /**
     * 创建资产池
     *
     * @param req 创建资产池请求对象
     * @return 创建资产池响应对象
     */
    @Override
    public CreatePoolResponse createPool(@Valid CreatePoolRequest req) throws Exception {
        //1: 执行请求参数校验
        CreatePoolResponse resp = validator.checkCreatePoolRequest(req);
        if (resp != null) {
            return resp;
        }

        resp = BaseResponse.build(CreatePoolResponse.class);
        //校验资产池名称是否重复
        int count = poolDao.selectPoolCountByName(req.getPoolName());
        if(count > 0){
            resp = BaseResponse.build(CreatePoolResponse.class);
            resp.setRespCode(Constants.POOL_NAME_REPEAT_CODE);
            resp.setRespMsg(Constants.POOL_NAME_REPEAT_DESC);
            return resp;
        }

        //校验融资方
        FinanceSubject financeSubject = financeSubjectDao.selectByCode(req.getFinanceSubjectCode());
        if(null == financeSubject){
            resp.setRespCode(Constants.FINANCE_SUBJECT_NOT_EXIST_CODE);
            resp.setRespMsg(Constants.FINANCE_SUBJECT_NOT_EXIST_DESC);
            return resp;
        }

        //校验受托方
        Trustee trustee = trusteeDao.selectByCode(req.getTrusteeCode());
        if(null == trustee){
            resp.setRespCode(Constants.TRUSTEE_NOT_EXIST_CODE);
            resp.setRespMsg(Constants.TRUSTEE_NOT_EXIST_DESC);
            return resp;
        }

        //2: 按照规则生成资产池编号
        String poolCode = sequenceService.generateBusinessCode(
                Constants.POOL_CODE_PREFIX, Constants.POOL_CODE_SEQUENCE_LENGTH);

        //3: 保存资产池信息
        Pool pool = new Pool();
        PropertyUtils.copyProperties(pool, req);
        pool.setPoolCode(poolCode);
        pool.setLoadSwitchStatus(PoolLoadSwitchEnum.LOAD_SAVE.getCode());
        pool.setFinanceSubjectId(financeSubject.getId());
        pool.setTrusteeId(trustee.getId());
        poolDao.insertSelective(pool);

        //4: 返回结果
        resp = BaseResponse.build(CreatePoolResponse.class);
        resp.setId(pool.getId());
        resp.setPoolCode(poolCode);
        return resp;
    }

    /**
     * 查询资产池列表
     *
     * @param req 查询资产池列表请求对象
     * @return 查询资产池列表响应对象
     */
    @Override
    @Transactional(readOnly = true)
    public PageQueryResponse<PoolModel> queryPoolList(QueryPoolListRequest req) throws Exception {
        PageQueryResponse<PoolModel> resp = BaseResponse.build(PageQueryResponse.class);
        Page page = new Page();
        Pool pool = new Pool();
        PropertyUtils.copyProperties(page, req);
        PropertyUtils.copyProperties(pool, req);

        int totalCount = poolDao.selectCount(pool);
        List<PoolModel> poolModels = null;
        if (totalCount > 0) {
            poolModels = new ArrayList<PoolModel>();
            List<Pool> pools = poolDao.selectPoolDetailList(pool, page);
            for(Pool p : pools){
                PoolModel poolModel = BeanUtils.copyAs(p, PoolModel.class);
                FinanceSubjectModel subjectModel = BeanUtils.copyAs(p.getFinanceSubject(), FinanceSubjectModel.class);
                poolModel.setFinanceSubjectModel(subjectModel);
                TrusteeModel trusteeModel = BeanUtils.copyAs(p.getTrustee(), TrusteeModel.class);
                poolModel.setTrusteeModel(trusteeModel);
                poolModels.add(poolModel);
            }
        }
        resp.setPageSize(page.getPageSize());
        resp.setPageNo(page.getPageNo());
        resp.setTotalCount(totalCount);
        resp.setDataList(poolModels);
        return resp;
    }

    /**
     * 查询资产池详情
     *
     * @param req 查询资产池详情请求对象
     * @return 查询资产池详情响应对象
     */
    @Override
    public QueryResponse<PoolModel> queryPool(@Valid QueryPoolRequest req) throws Exception {
        Pool pool = poolDao.selectDetailByCode(req.getPoolCode());
        if (null != pool) {
            PoolModel poolModel = BeanUtils.copyAs(pool, PoolModel.class);
            FinanceSubjectModel subjectModel = BeanUtils.copyAs(pool.getFinanceSubject(), FinanceSubjectModel.class);
            poolModel.setFinanceSubjectModel(subjectModel);
            TrusteeModel trusteeModel = BeanUtils.copyAs(pool.getTrustee(), TrusteeModel.class);
            poolModel.setTrusteeModel(trusteeModel);
            QueryResponse<PoolModel> resp = BaseResponse.build(QueryResponse.class);
            resp.setData(poolModel);
            return resp;
        } else {
            return BaseResponse.build(QueryResponse.class, Constants.PARAM_RESULTBLANK_CODE, Constants.PARAM_RESULTBLANK_DESC);
        }
    }

    /**
     * 查询资产池剩余资产
     */
    @Override
    public PageQueryResponse<PoolLeftAssetModel> queryLeftAssetAmountList(@Valid QueryPoolLeftAssetRequest req) throws Exception {
        if(null == PoolTypeEnum.getEnumItem(req.getPoolType())){
            return BaseResponse.build(PageQueryResponse.class,Constants.ASSET_POOL_TYPE_NOT_EXIST_CODE,Constants.ASSET_POOL_TYPE_NOT_EXIST_DESC);
        }

        PageQueryResponse<PoolLeftAssetModel> resp = BaseResponse.build(PageQueryResponse.class);
        Page page = new Page();
        PropertyUtils.copyProperties(page, req);
        Map<String,Object> param = new HashMap<>();
        param.put("poolCode",req.getPoolCode());
        param.put("poolType",req.getPoolType());

        int totalCount = poolDao.queryLeftAssetAmountListCount(param);
        List<PoolLeftAssetModel> assetModels = null;
        if (totalCount > 0) {
            assetModels = poolDao.queryLeftAssetAmountList(param, page);
        }
        resp.setPageSize(page.getPageSize());
        resp.setPageNo(page.getPageNo());
        resp.setTotalCount(totalCount);
        resp.setDataList(assetModels);
        return resp;
    }
}
