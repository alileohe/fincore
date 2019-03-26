package com.zb.fincore.ams.service.impl;

import com.zb.fincore.ams.common.Constants;
import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.common.enums.FinanceStatusEnum;
import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.facade.dto.req.CreateTrusteeRequest;
import com.zb.fincore.ams.facade.dto.req.QueryTrusteeListRequest;
import com.zb.fincore.ams.facade.dto.req.QueryTrusteeRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateTrusteeResponse;
import com.zb.fincore.ams.facade.model.TrusteeModel;
import com.zb.fincore.ams.service.SequenceService;
import com.zb.fincore.ams.service.TrusteeService;
import com.zb.fincore.ams.service.dal.dao.TrusteeDao;
import com.zb.fincore.ams.service.dal.model.Trustee;
import com.zb.fincore.ams.service.validate.TrusteeParameterValidator;
import com.zb.fincore.common.utils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

/**
 * 功能: 受托方服务接口实现
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 17:16
 * 版本: V1.0
 */
@Service
public class TrusteeServiceImpl implements TrusteeService {

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private TrusteeDao trusteeDao;

    @Autowired
    TrusteeParameterValidator trusteeParameterValidator;

    /**
     * 创建受托方
     *
     * @param req 创建受托方请求对象
     * @return 创建受托方响应对象
     */
    @Override
    public CreateTrusteeResponse createTrustee(@Valid CreateTrusteeRequest req) throws Exception {

        CreateTrusteeResponse resp = trusteeParameterValidator.checkCreateTrusteeRequest(req);
        if(resp != null){
            return resp;
        }
        resp = new BaseResponse().build(CreateTrusteeResponse.class);
        Trustee trustee = new Trustee();
        PropertyUtils.copyProperties(trustee, req);

        Trustee trusteeQuery = new Trustee();
//        trusteeQuery.setStatus(FinanceStatusEnum.NORMAL.getCode());
        trusteeQuery.setTrusteeName(req.getTrusteeName());
        List<Trustee> trustees = trusteeDao.selectForUnique(trustee);
        if(trustees.size() > 0){
            CreateTrusteeResponse response = new BaseResponse().build(CreateTrusteeResponse.class);
            response.setRespCode(Constants.TRUSTEE_NAME_REPEAT_CODE);
            response.setRespMsg(Constants.TRUSTEE_NAME_REPEAT_DESC);
            return response;
        }
//        for(Trustee model : trustees){
//            //校验信息是否一致
//            CreateTrusteeResponse response = trusteeParameterValidator.checkCreateTrusteeContent(trustee, model);
//            if (null != response){
//                return response;
//            }
//            model.setStatus(FinanceStatusEnum.FROZEN.getCode());
//            trusteeDao.updateByPrimaryKeySelective(model);
//        }
        //生成受托方编号
        String trusteeCode = sequenceService.generateBusinessCode(
                Constants.TRUSTEE_CODE_PREFIX, Constants.TRUSTEE_CODE_SEQUENCE_LENGTH);
        trustee.setTrusteeCode(trusteeCode);
        trusteeDao.insertSelective(trustee);
        resp.setId(trustee.getId());
        resp.setTrusteeCode(trustee.getTrusteeCode());
        return resp;
    }

    /**
     * 查询受托方列表
     *
     * @param req 查询受托方列表请求对象
     * @return 分页查询基础响应对象
     */
    @Override
    @Transactional(readOnly = true)
    public PageQueryResponse<TrusteeModel> queryTrusteeList(QueryTrusteeListRequest req) throws Exception {
        PageQueryResponse<TrusteeModel> resp = BaseResponse.build(PageQueryResponse.class);
        Page page = new Page();
        Trustee trustee = new Trustee();
        PropertyUtils.copyProperties(page, req);
        PropertyUtils.copyProperties(trustee, req);

        int totalCount = trusteeDao.selectCount(trustee);
        List<TrusteeModel> trusteeModels = null;
        if (totalCount > 0) {
            List<Trustee> trustees = trusteeDao.select(trustee, page);
            trusteeModels = BeanUtils.copyAs(trustees, TrusteeModel.class);
        }
        resp.setPageSize(page.getPageSize());
        resp.setPageNo(page.getPageNo());
        resp.setTotalCount(totalCount);
        resp.setDataList(trusteeModels);
        return resp;
    }

    /**
     * 查询受托方详情
     *
     * @param req 查询受托方详情请求
     * @return 基础查询响应对象
     */
    @Override
    public QueryResponse<TrusteeModel> queryTrustee(@Valid QueryTrusteeRequest req) throws Exception {
        Trustee trustee = trusteeDao.selectByCode(req.getTrusteeCode());
        if (null != trustee) {
            TrusteeModel trusteeModel = BeanUtils.copyAs(trustee, TrusteeModel.class);
            QueryResponse<TrusteeModel> resp = BaseResponse.build(QueryResponse.class);
            resp.setData(trusteeModel);
            return resp;
        } else {
            return BaseResponse.build(QueryResponse.class, Constants.PARAM_RESULTBLANK_CODE, Constants.PARAM_RESULTBLANK_DESC);
        }
    }
}
