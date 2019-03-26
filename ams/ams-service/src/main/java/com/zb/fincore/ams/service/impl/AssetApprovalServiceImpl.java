package com.zb.fincore.ams.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.zb.fincore.ams.common.Constants;
import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.enums.AssetApprovalStatusEnum;
import com.zb.fincore.ams.common.enums.AssetCollectStatusEnum;
import com.zb.fincore.ams.common.exception.BusinessException;
import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.facade.dto.req.ApproveAssetRequest;
import com.zb.fincore.ams.facade.dto.req.QueryAssetApprovalListRequest;
import com.zb.fincore.ams.facade.model.AssetApprovalModel;
import com.zb.fincore.ams.service.AssetApprovalService;
import com.zb.fincore.ams.service.dal.dao.AssetApprovalDao;
import com.zb.fincore.ams.service.dal.dao.AssetDao;
import com.zb.fincore.ams.service.dal.model.Asset;
import com.zb.fincore.ams.service.dal.model.AssetApproval;
import com.zb.fincore.common.utils.BeanUtils;

/**
 * 功能: 资产审核服务接口实现
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 15:42
 * 版本: V1.0
 */
@Service
public class AssetApprovalServiceImpl implements AssetApprovalService {

    @Autowired
    private AssetDao assetDao;

    @Autowired
    private AssetApprovalDao assetApprovalDao;

    /**
     * 审核资产
     *
     * @param req 审核资产请求对象
     * @return 审核资产响应对象
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BaseResponse approveAsset(@Valid ApproveAssetRequest req) throws Exception {
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
        if (!CollectionUtils.isEmpty(signedApprovals)) {
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
            }
        } else {
            asset.setApprovalRequireSign("");
            asset.setApprovalStatus(AssetApprovalStatusEnum.APPROVAL_FAILURE.getCode());
            asset.setAssetName(asset.getAssetName() + "(审核不通过)");
            asset.setCollectStatus(AssetCollectStatusEnum.CAN_NOT_USE.getCode());
        }

        if (assetDao.updateSelectiveWithLock(asset) <= 0) {
            throw new BusinessException(Constants.RECORD_IS_LOCKED_CODE, Constants.RECORD_IS_LOCKED_DESC);
        }
        assetApproval.setApprovalStatus(asset.getApprovalStatus());
        assetApprovalDao.insertSelective(assetApproval);
        return BaseResponse.build();
    }

    /**
     * 查询资产审核记录列表
     *
     * @param req 查询资产审核记录列表请求对象
     * @return 查询资产审核记录列表响应对象
     */
    @Override
    @Transactional(readOnly = true)
    public PageQueryResponse<AssetApprovalModel> queryAssetApprovalList(QueryAssetApprovalListRequest req) throws Exception {
        PageQueryResponse<AssetApprovalModel> resp = BaseResponse.build(PageQueryResponse.class);
        Page page = new Page();
        AssetApproval assetApproval = new AssetApproval();
        PropertyUtils.copyProperties(page, req);
        PropertyUtils.copyProperties(assetApproval, req);

        int totalCount = assetApprovalDao.selectCount(assetApproval);
        List<AssetApprovalModel> assetApprovalModels = null;
        if (totalCount > 0) {
            List<AssetApproval> assetApprovals = assetApprovalDao.select(assetApproval, page);
            assetApprovalModels = BeanUtils.copyAs(assetApprovals, AssetApprovalModel.class);
        }
//        else {
//            resp = BaseResponse.build(PageQueryResponse.class, Constants.PARAM_RESULTBLANK_CODE, Constants.PARAM_RESULTBLANK_DESC);
//        }
        resp.setPageSize(page.getPageSize());
        resp.setPageNo(page.getPageNo());
        resp.setTotalCount(totalCount);
        resp.setDataList(assetApprovalModels);
        return resp;
    }
}
