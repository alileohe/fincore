package com.zb.fincore.ams.service.impl;

import com.zb.fincore.ams.common.Constants;
import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.enums.AssetRepaymentTypeEnum;
import com.zb.fincore.ams.common.enums.RegisterTypeEnum;
import com.zb.fincore.ams.common.exception.BusinessException;
import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.facade.dto.req.CreateAssetForP2PRequest;
import com.zb.fincore.ams.facade.dto.req.QueryAssetRepayPlanListRequest;
import com.zb.fincore.ams.facade.model.AssetRepayPlanModel;
import com.zb.fincore.ams.service.AssetRepayPlanService;
import com.zb.fincore.ams.service.dal.dao.AssetRepayPlanDao;
import com.zb.fincore.ams.service.dal.model.Asset;
import com.zb.fincore.ams.service.dal.model.AssetRepayPlan;
import com.zb.fincore.common.utils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 功能: 资产回款计划服务实现
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 09:31
 * 版本: V1.0
 */
@Service
public class AssetRepayPlanServiceImpl implements AssetRepayPlanService {

    @Autowired
    private AssetRepayPlanDao assetRepayPlanDao;

    /**
     * 创建资产回款计划
     *
     * @param asset 资产信息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createAssetReplayPlan(Asset asset) throws Exception {
        if (asset.getRepaymentType() == AssetRepaymentTypeEnum.ONE_TIME_DEBT.getCode()) {
            createOneTimeDebtRepayPlan(asset);
        } else if (AssetRepaymentTypeEnum.getEnumItem(asset.getRepaymentType()) != null) {

        } else {
            throw new BusinessException(Constants.UNKNOWN_REPAYMENT_TYPE_CODE, Constants.UNKNOWN_REPAYMENT_TYPE_DESC);
        }
    }

    /**
     * 创建一次性还本付息类型资产回款计划
     *
     * @param asset 资产信息
     */
    public void createOneTimeDebtRepayPlan(Asset asset) throws Exception {
        AssetRepayPlan repayPlan = new AssetRepayPlan();
        PropertyUtils.copyProperties(repayPlan, asset);
        repayPlan.setRepayPrincipal(asset.getAssetAmount());
        BigDecimal interest = asset.getAssetAmount().multiply(asset.getYieldRate()).
                multiply(BigDecimal.valueOf(asset.getValueDays())).divide(new BigDecimal(360), 2, BigDecimal.ROUND_DOWN);
        repayPlan.setRepayAmount(asset.getAssetAmount().add(interest));
        repayPlan.setRepayInterest(interest);
        assetRepayPlanDao.insertSelective(repayPlan);
    }

    /**
     * 查询资产回款计划列表
     *
     * @param req 查询资产回款计划列表请求
     * @return 查询资产回款计划列表响应
     */
    @Override
    @Transactional(readOnly = true)
    public PageQueryResponse<AssetRepayPlanModel> queryAssetRepayPlanList(QueryAssetRepayPlanListRequest req) throws Exception {
        PageQueryResponse<AssetRepayPlanModel> resp = BaseResponse.build(PageQueryResponse.class);
        Page page = new Page();
        AssetRepayPlan repayPlan = new AssetRepayPlan();
        PropertyUtils.copyProperties(page, req);
        PropertyUtils.copyProperties(repayPlan, req);

        int totalCount = assetRepayPlanDao.selectCount(repayPlan);
        List<AssetRepayPlanModel> assetRepayPlanModels = null;
        if (totalCount > 0) {
            List<AssetRepayPlan> assetRepayPlans = assetRepayPlanDao.select(repayPlan, page);
            assetRepayPlanModels = BeanUtils.copyAs(assetRepayPlans, AssetRepayPlanModel.class);
        }
        resp.setPageSize(page.getPageSize());
        resp.setPageNo(page.getPageNo());
        resp.setTotalCount(totalCount);
        resp.setDataList(assetRepayPlanModels);
        return resp;
    }

    /**
     * 生成P2P还款计划
     * @param asset 资产信息
     * @param req
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createAssetReplayPlanForP2P(Asset asset, CreateAssetForP2PRequest req) throws Exception {

        AssetRepayPlan repayPlan = new AssetRepayPlan();
        PropertyUtils.copyProperties(repayPlan, asset);
        repayPlan.setRepayPrincipal(asset.getAssetAmount());
        BigDecimal interest = req.getRepayInterest();
        repayPlan.setRepayAmount(asset.getAssetAmount().add(interest));
        repayPlan.setRepayInterest(interest);
        repayPlan.setAssetId(asset.getId());
        repayPlan.setWaitingRepayAmount(asset.getAssetAmount().add(interest));
        repayPlan.setFinanceSubjectCode(RegisterTypeEnum.MSD.getValue());
        repayPlan.setId(null);
        repayPlan.setLoanName(req.getLoanName());
        repayPlan.setLoanCertNo(req.getLoanCertNo());
        repayPlan.setLoanCertType(req.getLoanCertType());
        repayPlan.setLoanFee(req.getLoanFee());
        repayPlan.setMemberId(req.getMemberId());
        repayPlan.setLoanPurpose(req.getLoanPurpose());
        repayPlan.setTel(req.getTel());

        assetRepayPlanDao.insertSelective(repayPlan);
    }
}
