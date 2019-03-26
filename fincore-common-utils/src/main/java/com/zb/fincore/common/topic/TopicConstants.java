package com.zb.fincore.common.topic;

import com.zb.fincore.common.utils.PropertiesUtil;

/**
 * Created by wangwanbin on 2017/5/8.
 */
public interface TopicConstants {
    /**
     * 产品系统刷新产品缓存
     */
    String FINCORE_TOPIC_PMS_REFRESH_PRODUCT_CACHE = PropertiesUtil.getValue("FINCORE_TOPIC_PMS_REFRESH_PRODUCT_CACHE");
    /**
     * 产品系统产品状态变更
     */
    String FINCORE_TOPIC_PMS_PRODUCT_STATUS_CHANGE = PropertiesUtil.getValue("FINCORE_TOPIC_PMS_PRODUCT_STATUS_CHANGE");
    /**
     * 产品系统产品募集结束日通知
     */
    String FINCORE_TOPIC_PMS_PRODUCT_COLLECT_END_DATE = PropertiesUtil.getValue("FINCORE_TOPIC_PMS_PRODUCT_COLLECT_END_DATE");
    /**
     * 产品系统产品回款日通知
     */
    String FINCORE_TOPIC_PMS_PRODUCT_REPAY_DATE = PropertiesUtil.getValue("FINCORE_TOPIC_PMS_PRODUCT_REPAY_DATE");
    /**
     * 阶梯交易系统投资处理完成
     */
    String FINCORE_TOPIC_LADDER_TRADE_INVEST_COMPLETE = PropertiesUtil.getValue("FINCORE_TOPIC_LADDER_TRADE_INVEST_COMPLETE");
    /**
     * 阶梯交易系统兑付处理完成
     */
    String FINCORE_TOPIC_LADDER_TRADE_REPAY_COMPLETE = PropertiesUtil.getValue("FINCORE_TOPIC_LADDER_TRADE_REPAY_COMPLETE");
    /**
     * 阶梯交易系统开立TA账户申请
     */
    String FINCORE_TOPIC_LADDER_TRADE_OPEN_TA_ACCOUNT = PropertiesUtil.getValue("FINCORE_TOPIC_LADDER_TRADE_OPEN_TA_ACCOUNT");
    /**
     * 阶梯TA系统资产匹配消息通知
     */
    String FINCORE_TOPIC_LADDER_TA_ASSET_MATCH = PropertiesUtil.getValue("FINCORE_TOPIC_LADDER_TA_ASSET_MATCH");
    /**
     * 阶梯TA系统资产释放消息通知
     */
    String FINCORE_TOPIC_LADDER_TA_ASSET_RELEASE = PropertiesUtil.getValue("FINCORE_TOPIC_LADDER_TA_ASSET_RELEASE");
    /**
     * 阶梯TA系统开立TA账户处理完成
     */
    String FINCORE_TOPIC_LADDER_TA_OPEN_ACCOUNT_COMPLETE = PropertiesUtil.getValue("FINCORE_TOPIC_LADDER_TA_OPEN_ACCOUNT_COMPLETE");
    /**
     * 阶梯TA系统开始兑付处理
     */
    String FINCORE_TOPIC_LADDER_TA_REPAY_PROCESS = PropertiesUtil.getValue("FINCORE_TOPIC_LADDER_TA_REPAY_PROCESS");
    /**
     * 订单系统变更产品库存
     */
    String FINCORE_TOPIC_ORDER_CHANGE_PRODUCT_STOCK = PropertiesUtil.getValue("FINCORE_TOPIC_ORDER_CHANGE_PRODUCT_STOCK");

}
