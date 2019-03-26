package com.zb.fincore.pms.service.product.impl;


import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zb.fincore.common.utils.DecimalUtils;
import com.zb.fincore.pms.common.dto.BaseResponse;
import com.zb.fincore.pms.common.enums.ChangeProductStockStatusEnum;
import com.zb.fincore.pms.common.enums.ChangeProductStockTypeEnum;
import com.zb.fincore.pms.facade.product.dto.req.ChangeProductStockForP2PRequest;
import com.zb.fincore.pms.facade.product.dto.req.ChangeProductStockRequest;
import com.zb.fincore.pms.facade.product.dto.resp.ChangeProductStockResponse;
import com.zb.fincore.pms.service.dal.model.ProductStock;
import com.zb.fincore.pms.service.dal.model.ProductStockChangeReq;
import com.zb.fincore.pms.service.product.ProductCacheForP2PService;

/**
 * 功能: 产品缓存接口类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/6 0006 16:58
 * 版本: V1.0
 */
@Service
public class ProductCacheForP2PServiceImpl extends AbstracProductCacheServiceImpl implements ProductCacheForP2PService {

    /**
     * 日志记录器
     */
    private static Logger logger = LoggerFactory.getLogger(ProductCacheForP2PServiceImpl.class);


    /**
     * 产品库存变更
     *
     * @param req
     * @return
     */
    public ChangeProductStockResponse changeProductStockForP2P(@Valid ChangeProductStockForP2PRequest reqForP2P) throws Exception {
        ChangeProductStockResponse resp = BaseResponse.build(ChangeProductStockResponse.class);
        try {
            PropertyUtils.copyProperties(resp, reqForP2P);
            resp.setStatus(ChangeProductStockStatusEnum.PROCESSING.getCode());
            
            ProductStockChangeReq changeReq = new ProductStockChangeReq();
            changeReq.setRefNo(reqForP2P.getRefNo());
            changeReq.setChangeType(reqForP2P.getChangeType());
            
            //查询原冻结记录
            ProductStockChangeReq freezeReq = new ProductStockChangeReq();
            freezeReq.setRefNo(reqForP2P.getRefNo());
            freezeReq.setChangeType(ChangeProductStockTypeEnum.FREEZE.getCode());
            freezeReq = super.productStockChangeReqDao.select(freezeReq);
            if (null == freezeReq ) {
                resp.setStatus(ChangeProductStockStatusEnum.FAIL.getCode());
                resp.setAddition("未查询到冻结记录");
            } else {
                if (freezeReq.getStatus() != ChangeProductStockStatusEnum.SUCCESS.getCode()) {
                	resp.setStatus(ChangeProductStockStatusEnum.FAIL.getCode());
                    resp.setAddition("冻结记录未成功冻结");
                } else {                	
                	BigDecimal totalReleaseAmount = this.doGetStockAmount(ChangeProductStockTypeEnum.RELEASE.getCode(), reqForP2P.getRefNo());
            		BigDecimal totalOccupyAmount = this.doGetStockAmount(ChangeProductStockTypeEnum.OCCUPY.getCode(), reqForP2P.getRefNo());
            		BigDecimal remainingAmount = freezeReq.getChangeAmount().subtract(totalReleaseAmount.add(totalOccupyAmount));//【库存】剩余金额 = 冻结总金额 - （ 释放总金额 + 取消总金额 ）
            		
                	resp.setStatus(ChangeProductStockStatusEnum.PROCESSING.getCode());//添加，req没传值 插入申请记录 报错
                    changeReq.setProductCode(freezeReq.getProductCode());
                    changeReq.setChangeAmount(reqForP2P.getChangeAmount() != null ? reqForP2P.getChangeAmount() : BigDecimal.ZERO);
                    
                    if (DecimalUtils.lt(remainingAmount, reqForP2P.getChangeAmount())) {
                        resp.setStatus(ChangeProductStockStatusEnum.FAIL.getCode());
                        resp.setAddition("冻结金额不足:" + reqForP2P.getProductCode());
                        logger.info("冻结金额不足:product={},refNo={}", reqForP2P.getProductCode(), reqForP2P.getRefNo());
                    }
                    
//            		if (reqForP2P.getChangeType() == ChangeProductStockTypeEnum.OCCUPY.getCode()) {
//            			if (DecimalUtils.lt(remainingAmount, reqForP2P.getChangeAmount())) {
//                            resp.setStatus(ChangeProductStockStatusEnum.FAIL.getCode());
//                            resp.setAddition("冻结金额不足:" + reqForP2P.getProductCode());
//                            logger.info("冻结金额不足:product={},refNo={}", reqForP2P.getProductCode(), reqForP2P.getRefNo());
//                        }
//            		}
                }
            }

            //插入申请记录
            if (resp.getStatus() == ChangeProductStockStatusEnum.FAIL.getCode()
                    || resp.getStatus() == ChangeProductStockStatusEnum.ERROR.getCode()) {
                changeReq.setStatus(resp.getStatus());
                changeReq.setMemo(resp.getAddition());
            } else {
                changeReq.setStatus(ChangeProductStockStatusEnum.PROCESSING.getCode());
            }
            super.productStockChangeReqDao.insertSelective(changeReq);

            //根据上面逻辑，如果resp 为失败，不需要进行下面的  库存逻辑变更处理；
            if (resp.getStatus() == ChangeProductStockStatusEnum.FAIL.getCode()
                    || resp.getStatus() == ChangeProductStockStatusEnum.ERROR.getCode()) {
                return resp;
            }

            try {
                //真正变更逻辑处理
            	ChangeProductStockRequest req = new ChangeProductStockRequest();
            	PropertyUtils.copyProperties(req, reqForP2P);
                super.internalChangeProductStock(req, resp);
            } catch (Exception e) {
                logger.error("变更库存异常", e);
            }
            //根据冻结结果更新申请记录
            changeReq.setStatus(resp.getStatus());
            changeReq.setMemo(resp.getAddition());
            super.productStockChangeReqDao.updateByPrimaryKeySelective(changeReq);
        } catch (Exception e) {
            logger.error("库存变更异常", e);
            resp.setStatus(ChangeProductStockStatusEnum.ERROR.getCode());
        }
        return resp;
    }
    
    
    private BigDecimal doGetStockAmount(Integer changeType, String refNo) {
    	
    	BigDecimal totalAmount = BigDecimal.ZERO;
    	List<ProductStockChangeReq> productStockReleaseReqList = null;
    	
    	//查询
    	ProductStockChangeReq releaseStockReq = new ProductStockChangeReq();
        releaseStockReq.setRefNo(refNo);
        releaseStockReq.setChangeType(changeType);
    	
    	try {
            productStockReleaseReqList = super.productStockChangeReqDao.queryProductStockChangeReqList(releaseStockReq);
            if (null != productStockReleaseReqList && productStockReleaseReqList.size() > 0) {
            	for (ProductStockChangeReq stockChangeReq : productStockReleaseReqList) {
            		if (stockChangeReq.getStatus() != ChangeProductStockStatusEnum.FAIL.getCode()) {
            			totalAmount = totalAmount.add(stockChangeReq.getChangeAmount());
            		}
            	}
            }
        } catch (Exception e) {
        	throw e;
        }
        
		return totalAmount;
    }


}
