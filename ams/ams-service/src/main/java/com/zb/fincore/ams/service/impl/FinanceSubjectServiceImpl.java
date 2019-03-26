package com.zb.fincore.ams.service.impl;

import com.zb.fincore.ams.common.Constants;
import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.common.enums.FinanceStatusEnum;
import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.facade.dto.req.CreateFinanceSubjectRequest;
import com.zb.fincore.ams.facade.dto.req.QueryFinanceSubjectListRequest;
import com.zb.fincore.ams.facade.dto.req.QueryFinanceSubjectRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateFinanceSubjectResponse;
import com.zb.fincore.ams.facade.dto.resp.CreateTrusteeResponse;
import com.zb.fincore.ams.facade.model.FinanceSubjectModel;
import com.zb.fincore.ams.service.FinanceSubjectService;
import com.zb.fincore.ams.service.SequenceService;
import com.zb.fincore.ams.service.dal.dao.FinanceSubjectDao;
import com.zb.fincore.ams.service.dal.model.FinanceSubject;
import com.zb.fincore.ams.service.validate.FinanceSubjectParameterValidator;
import com.zb.fincore.common.utils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

/**
 * 功能: 融资方服务接口实现
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/18 0018 14:18
 * 版本: V1.0
 */
@Service
public class FinanceSubjectServiceImpl implements FinanceSubjectService {

    @Autowired
    private FinanceSubjectDao financeSubjectDao;

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    FinanceSubjectParameterValidator financeSubjectParameterValidator;

    /**
     * 创建融资方
     *
     * @param req 创建融资方请求对象
     * @return 创建融资方响应对象
     */
    @Override
    public CreateFinanceSubjectResponse createFinanceSubject(@Valid CreateFinanceSubjectRequest req) throws Exception {
        CreateFinanceSubjectResponse resp = financeSubjectParameterValidator.checkCreateFinanceSubjectRequest(req);
        if(resp != null){
            return resp;
        }

        resp = BaseResponse.build(CreateFinanceSubjectResponse.class);
        FinanceSubject financeSubject = new FinanceSubject();
        PropertyUtils.copyProperties(financeSubject, req);

        FinanceSubject subjectQuery = new FinanceSubject();
//        subjectQuery.setStatus(FinanceStatusEnum.NORMAL.getCode());
        subjectQuery.setSubjectName(req.getSubjectName());
        List<FinanceSubject> subjects = financeSubjectDao.selectForUnique(subjectQuery);
        if(subjects.size() > 0){
            CreateFinanceSubjectResponse response = new BaseResponse().build(CreateFinanceSubjectResponse.class);
            response.setRespCode(Constants.FINANCE_NAME_REPEAT_CODE);
            response.setRespMsg(Constants.FINANCE_NAME_REPEAT_DESC);
            return response;
        }
//        for(FinanceSubject model : subjects){
//            //校验信息是否一致
//            CreateFinanceSubjectResponse response = financeSubjectParameterValidator.checkCreateFinanceContent(financeSubject,model);
//            if (null != response){
//                return response;
//            }
//            model.setStatus(FinanceStatusEnum.FROZEN.getCode());
//            financeSubjectDao.updateByPrimaryKeySelective(model);
//        }
        //生成融资方编号
        String subjectCode = sequenceService.generateBusinessCode(
                Constants.FINANCE_SUBJECT_CODE_PREFIX, Constants.FINANCE_SUBJECT_CODE_SEQUENCE_LENGTH);
        financeSubject.setSubjectCode(subjectCode);
        financeSubjectDao.insertSelective(financeSubject);
        resp.setId(financeSubject.getId());
        resp.setSubjectCode(financeSubject.getSubjectCode());
        return resp;
    }

    /**
     * 查询融资方列表
     *
     * @param req 查询融资方列表请求对象
     * @return 分页查询基础响应对象
     */
    @Override
    @Transactional(readOnly = true)
    public PageQueryResponse<FinanceSubjectModel> queryFinanceSubjectList(QueryFinanceSubjectListRequest req) throws Exception {
        PageQueryResponse<FinanceSubjectModel> resp = BaseResponse.build(PageQueryResponse.class);
        Page page = new Page();
        FinanceSubject financeSubject = new FinanceSubject();
        PropertyUtils.copyProperties(page, req);
        PropertyUtils.copyProperties(financeSubject, req);

        int totalCount = financeSubjectDao.selectCount(financeSubject);
        List<FinanceSubjectModel> financeSubjectModels = null;
        if (totalCount > 0) {
            List<FinanceSubject> financeSubjects = financeSubjectDao.select(financeSubject, page);
            financeSubjectModels = BeanUtils.copyAs(financeSubjects, FinanceSubjectModel.class);
        }
        resp.setPageSize(page.getPageSize());
        resp.setPageNo(page.getPageNo());
        resp.setTotalCount(totalCount);
        resp.setDataList(financeSubjectModels);
        return resp;
    }

    /**
     * 查询融资方详情
     *
     * @param req 查询融资方详情请求
     * @return 基础查询响应对象
     */
    @Override
    public QueryResponse<FinanceSubjectModel> queryFinanceSubject(@Valid QueryFinanceSubjectRequest req) throws Exception {
        FinanceSubject financeSubject = financeSubjectDao.selectByCode(req.getSubjectCode());
        if (null != financeSubject) {
            FinanceSubjectModel financeSubjectModel = BeanUtils.copyAs(financeSubject, FinanceSubjectModel.class);
            QueryResponse<FinanceSubjectModel> resp = BaseResponse.build(QueryResponse.class);
            resp.setData(financeSubjectModel);
            return resp;
        } else {
            return BaseResponse.build(QueryResponse.class, Constants.PARAM_RESULTBLANK_CODE, Constants.PARAM_RESULTBLANK_DESC);
        }
    }
}
