package com.zb.fincore.ams.common;

import java.math.BigDecimal;

/**
 *
 */
public class Constants {

    public static final String FINANCE_SUBJECT_CODE_PREFIX = "FS";

    public static final int FINANCE_SUBJECT_CODE_SEQUENCE_LENGTH = 4;

    public static final String TRUSTEE_CODE_PREFIX = "TR";

    public static final int TRUSTEE_CODE_SEQUENCE_LENGTH = 4;

    public static final String ASSET_CODE_PREFIX = "AMA";

    public static final int ASSEET_CODE_SEQUENCE_LENGTH = 5;

    public static final String BATCH_NO_PREFIX = "BN";

    public static final int BATCH_NO_SEQUENCE_LENGTH = 3;

    public static final String CONTRACT_BATCH_NO_PREFIX = "DDRZ-";

    public static final int CONTRACT_BATCH_NO_LENGTH = 4;

    public static final String BATCH_NAME_PREFIX = "AN";

    public static final int BATCH_NAME_SEQUENCE_LENGTH = 3;

    public static final String POOL_CODE_PREFIX = "AMP";

    public static final int POOL_CODE_SEQUENCE_LENGTH = 5;

    public static final String ASSET_APPROVAL_SIGN_KEY = "asset_approval_sign";

    public static final String BATCH_ASSET_APPROVAL_SIGN_KEY = "batch_asset_approval_sign";

    public static final BigDecimal BIGGEST_ASSET_AMOUNT = new BigDecimal("999999999.99");

    public static final int LENGTH_3 = 3;

    public static final int LENGTH_4 = 4;

    public static final int LENGTH_5 = 5;

    public static final int P2P_LIMIT_VALUE_DAYS = 21;

    public static final int P2P_LIMIT_VALUE_2DAYS = 2;

    public static final BigDecimal P2P_LIMIT_ASSET_AMOUNT = new BigDecimal("10000");

    /**
     * ************************************** 通用状态 start ************************************
     */
    public static final String SUCCESS_RESP_CODE = "0000";
    public static final String SUCCESS_RESP_DESC = "成功";

    public static final String FAIL_RESP_CODE = "9999";
    public static final String FAIL_RESP_DESC = "处理失败";

    public static final String PARAM_NOBLANK_CODE = "9001";
    public static final String PARAM_NOBLANK_DESC = "必填参数不能为空";

    public static final String RECORD_IS_LOCKED_CODE = "9002";
    public static final String RECORD_IS_LOCKED_DESC = "更新记录已被锁定，请稍后再试";

    public static final String PARAM_VALIDATE_ERROR_CODE = "9003";
    public static final String PARAM_VALIDATE_ERROR_DESC = "参数校验未通过";

    public static final String PARAM_RESULTBLANK_CODE = "9998";
    public static final String PARAM_RESULTBLANK_DESC = "查询结果为空";

    /*******************************************
     * 通用状态 end
     ***************************************/
    public static final String ASSET_NOT_EXIST_CODE = "9102";
    public static final String ASSET_NOT_EXIST_DESC = "资产不存在";

    public static final String ASSET_NOT_NEED_APPROVAL_CODE = "9103";
    public static final String ASSET_NOT_NEED_APPROVAL_DESC = "资产当前状态不需要审核";

    public static final String ASSET_NOT_NEED_CURRENT_SIGN_CODE = "9104";
    public static final String ASSET_NOT_NEED_CURRENT_SIGN_DESC = "授权等级不符合";

    public static final String ASSET_CAN_NOT_SIGN_BY_SAME_USER_CODE = "9005";
    public static final String ASSET_CAN_NOT_SIGN_BY_SAME_USER_DESC = "当前用户已签授权,请用其他用户重试";

    public static final String POOL_NOT_EXIST_CODE = "9106";
    public static final String POOL_NOT_EXIST_DESC = "资产池不存在";

    public static final String POOL_CAN_NOT_SAVE_CODE = "9107";
    public static final String POOL_CAN_NOT_SAVE_DESC = "资产池装载开关不可入";

    public static final String POOL_OVER_LIMIT_CODE = "9108";
    public static final String POOL_OVER_LIMIT_DESC = "资产加入后资产池超过总额限制";

    public static final String ASSET_NOT_APPROVAL_CODE = "9109";
    public static final String ASSET_NOT_APPROVAL_DESC = "资产未通过审核";

    public static final String NOT_SAME_SUBJECT_CODE = "9110";
    public static final String NOT_SAME_SUBJECT_DESC = "融资方不一致";

    public static final String NOT_ENOUGH_AMOUNT_CODE = "9111";
    public static final String NOT_ENOUGH_AMOUNT_DESC = "资产无可分配金额";

    public static final String ASSET_POOL_NOT_EXIST_CODE = "9112";
    public static final String ASSET_POOL_NOT_EXIST_DESC = "资产与资产池关联关系不存在";

    public static final String ASSET_PRODUCT_NOT_EXIST_CODE = "9113";
    public static final String ASSET_PRODUCT_NOT_EXIST_DESC = "产品与资产关联关系不存在";

    public static final String POOL_NOT_ENOUGH_AMOUNT_CODE = "9114";
    public static final String POOL_NOT_ENOUGH_AMOUNT_DESC = "资产池可用金额不足";

    public static final String POOL_NOT_FIT_ASSET_CODE = "9115";
    public static final String POOL_NOT_FIT_ASSET_DESC = "资产池无符合条件资产可匹配产品";

    public static final String BATCH_CREATE_LIST_EMPTY_CODE = "9116";
    public static final String BATCH_CREATE_LIST_EMPTY_DESC = "批量创建资产列表不能为空";

    public static final String UNKNOWN_REPAYMENT_TYPE_CODE = "9201";
    public static final String UNKNOWN_REPAYMENT_TYPE_DESC = "未知还款方式";

    public static final String ASSET_REPEAT_NAME_CODE = "9202";
    public static final String ASSET_REPEAT_NAME_DESC = "资产名称已存在";

    public static final String ASSET_CODE_REPEAT_CODE = "9203";
    public static final String ASSET_CODE_REPEAT_DESC = "资产编码已存在";

    public static final String ESTABLISH_TIME_LT_VALUE_START_TIME_CODE = "9204";
    public static final String ESTABLISH_TIME_LT_VALUE_START_TIME_DESC = "成立日期要小于等于起息日";

    public static final String VALUE_END_TIME_GT_VALUE_START_TIME_CODE = "9205";
    public static final String VALUE_END_TIME_GT_VALUE_START_TIME_DESC = "结息日要大于等于起息日";

    public static final String EXPIRE_TIME_GE_VALUE_END_TIME_CODE = "9206";
    public static final String EXPIRE_TIME_GE_VALUE_END_TIME_DESC = "到期日要大于等于结息日";

    public static final String POOL_NAME_REPEAT_CODE = "9207";
    public static final String POOL_NAME_REPEAT_DESC = "资产池名称已存在";

    public static final String TRUSTEE_NAME_REPEAT_CODE = "9208";
    public static final String TRUSTEE_NAME_REPEAT_DESC = "受托方名称已存在";

    public static final String CERT_NO_REPEAT_CODE = "9209";
    public static final String CERT_NO_REPEAT_DESC = "证件号码已存在";

    public static final String FINANCE_NAME_REPEAT_CODE = "9210";
    public static final String FINANCE_NAME_REPEAT_DESC = "融资方名称已存在";

    public static final String ASSET_AMOUNT_GT_ZERO_CODE = "9211";
    public static final String ASSET_AMOUNT_GT_ZERO_DESC = "资产总规模必须大于零";

    public static final String VALUE_DAYS_GT_ZERO_CODE = "9212";
    public static final String VALUE_DAYS_GT_ZERO_DESC = "期限必须大于零";

    public static final String YIELD_RATE_GE_ZERO_CODE = "9213";
    public static final String YIELD_RATE_GE_ZERO_DESC = "利率不能为负数";

    public static final String FINANCE_SUBJECT_NOT_EXIST_CODE = "9214";
    public static final String FINANCE_SUBJECT_NOT_EXIST_DESC = "融资方不存在";

    public static final String TRUSTEE_NOT_EXIST_CODE = "9215";
    public static final String TRUSTEE_NOT_EXIST_DESC = "受托方不存在";

    public static final String ASSET_PRODUCT_IS_EXIST_CODE = "9216";
    public static final String ASSET_PRODUCT_IS_EXIST_DESC = "产品与资产关联关系已存在";

    public static final String ASSET_LIST_REPEAT_NAME_CODE = "9217";
    public static final String ASSET_LIST_REPEAT_NAME_DESC = "资产名称重复";

    public static final String POOL_LIMIT_AMOUNT_GT_ZERO_CODE = "9218";
    public static final String POOL_LIMIT_AMOUNT_GT_ZERO_DESC = "资产池总额上限不能为负数";

    public static final String CERT_TYPE_ERROR_CODE = "9219";
    public static final String CERT_TYPE_ERROR_DESC = "证件类型输入有误";

    public static final String REGISTERED_CAPITAL_GT_ZERO_CODE = "9220";
    public static final String REGISTERED_CAPITAL_GT_ZERO_DESC = "注册资本必须大于0";

    public static final String ASSET_POOL_TYPE_NOT_EXIST_CODE = "9221";
    public static final String ASSET_POOL_TYPE_NOT_EXIST_DESC = "交易结构不存在";

    public static final String FINANCE_SUBJECT_REPEAT_CODE = "9222";
    public static final String FINANCE_SUBJECT_REPEAT_DESC = "存在重名信息，如需覆盖请更改其他信息";

    public static final String ASSET_COLLECT_NOT_ESTABLISHED_CODE = "9223";
    public static final String ASSET_COLLECT_NOT_ESTABLISHED_DESC = "存在未成立资产，不可关联资产池";

    public static final String ASSET_RELATION_POOL_CODE = "9224";
    public static final String ASSET_RELATION_POOL_DESC = "存在资产已匹配过资产池";

    public static final String ASSET_CODE_NOT_REPEAT_CODE = "9225";
    public static final String ASSET_CODE_NOT_REPEAT_DESC = "资产编码不能重复";

    public static final String ASSET_CODE_NOT_NULL_CODE = "9226";
    public static final String ASSET_CODE_NOT_NULL_DESC = "资产编码不能为空";

    public static final String POOL_CODE_NOT_NULL_CODE = "9227";
    public static final String POOL_CODE_NOT_NULL_DESC = "资产池编码不能为空";

    public static final String CHANGE_ASSET_PRODUCT_TYPE_CODE = "9228";
    public static final String CHANGE_ASSET_PRODUCT_TYPE_DESC = "未知的变更类型";

    public static final String CHANGE_ASSET_PRODUCT_AMOUNT_CODE = "9229";
    public static final String CHANGE_ASSET_PRODUCT_AMOUNT_DESC = "变更金额必须是大于等于0的数字";

    public static final String CHANGE_AMOUNT_LE_SALE_AMOUNT_CODE = "9230";
    public static final String CHANGE_AMOUNT_LE_SALE_AMOUNT_DESC = "资产释放金额不能大于资产已售总额";

    public static final String CERT_NUM_LENGTH_CODE = "9231";
    public static final String CERT_NUM_LENGTH_DESC = "证件号码长度必须是18位";

    public static final String ID_NUM_LENGTH_CODE = "9232";
    public static final String ID_NUM_LENGTH_DESC = "身份证号码长度必须是18位";

    public static final String VALUE_DAYS_EQ_END_SUB_START_CODE = "9233";
    public static final String VALUE_DAYS_EQ_END_SUB_START_DESC = "期限必须等于结息日减起息日加1";

    public static final String TEMPLATE_PARAM_NOT_EXIST_CODE = "9234";
    public static final String TEMPLATE_PARAM_NOT_EXIST_DESC = "模板参数不存在";

    public static final String IMPORT_PARAM_NOT_NULL_CODE = "9235";
    public static final String IMPORT_PARAM_NOT_NULL_DESC = "导入参数不能为空";

    public static final String ISSUE_PLAN_NOT_NULL_CODE = "9236";
    public static final String ISSUE_PLAN_NOT_NULL_DESC = "发行计划不能为空";

    public static final String BUSINESS_CREDIT_NOT_NULL_CODE = "9237";
    public static final String BUSINESS_CREDIT_NOT_NULL_DESC = "授信表信息不能为空";

    public static final String CREDIT_LINES_NOT_ENOUGH_DESC = "发行计划总金额不能大于授信额度";

    public static final String ASSET_TYPE_NOT_NULL_CODE = "9238";
    public static final String ASSET_TYPE_NOT_NULL_DESC = "未知的融资类型";

    public static final String GT_BIGGEST_ASSET_AMOUNT_CODE = "9239";
    public static final String GT_BIGGEST_ASSET_AMOUNT_DESC = "募集金额超出范围";

    public static final String EXCHANGE_REGISTER_NOT_EXIST_CODE = "9240";
    public static final String EXCHANGE_REGISTER_NOT_EXIST_DESC = "资产尚未备案";

    public static final String UNKNOWN_EXCHANGE_REGISTER_CODE = "9241";
    public static final String UNKNOWN_EXCHANGE_REGISTER_DESC = "未知的备案状态";

    public static final String VALUE_START_LT_VALUE_END_CODE = "9242";
    public static final String VALUE_START_LT_VALUE_END_DESC = "合同起息日要小于合同结息日";

    public static final String PRODUCT_COUNT_LT_CONTRACT_COUNT_CODE = "9243";
    public static final String PRODUCT_COUNT_LT_CONTRACT_COUNT_DESC = "合同天数要大于产品天数";

    public static final String PRODUCT_ASSET_ACCOUNT_IS_RELEASE_CODE = "9244";
    public static final String PRODUCT_ASSET_ACCOUNT_IS_RELEASE_DESC = "产品关联资产已释放";

    public static final String LOAN_SUBJECT_NOT_EXIST_CODE = "9245";
    public static final String LOAN_SUBJECT_NOT_EXIST_DESC = "出资方不存在";

    public static final String REGISTER_NAME_NOT_NULL_CODE = "9246";
    public static final String REGISTER_NAME_NOT_NULL_DESC = "备案名称不能为空";

    public static final String REGISTER_RATE_NOT_NULL_CODE = "9247";
    public static final String REGISTER_RATE_NOT_NULL_DESC = "备案利率不能为空";

    public static final String REGISTER_AMOUNT_NOT_NULL_CODE = "9248";
    public static final String REGISTER_AMOUNT_NOT_NULL_DESC = "备案金额不能为空";

    public static final String ASSET_STOCK_AMOUNT_NOT_ENOUGH_CODE = "9249";
    public static final String ASSET_STOCK_AMOUNT_NOT_ENOUGH_DESC = "授信资产金额不足，剩余";

    public static final String REGISTER_NAME_REPEAT_CODE = "9247";
    public static final String REGISTER_NAME_REPEAT_DESC = "备案名称已存在";

    public static final String LOAN_FINANCE_CODE_NOT_EQ_CODE = "9248";
    public static final String LOAN_FINANCE_CODE_NOT_EQ_DESC = "出资方和融资方不可一致";

    public static final String PRODUCT_CODE_NOT_NULL_CODE = "9249";
    public static final String PRODUCT_CODE_NOT_NULL_DESC = "产品编码不能为空";

    public static final String MSD_CODE = "MSD";
    public static final String MSD_DESC = "马上贷";

    public static final String TRADE_ASSET_SYNC_CODE = "9250";
    public static final String TRADE_ASSET_SYNC_FAILED = "交易同步资产失败";

    public static final String P2P_LIMIT_VALUE_DAYS_CODE = "9251";
    public static final String P2P_LIMIT_VALUE_DAYS_DESC = "借款期限有误";

    public static final String EXT_ASSET_CODE_REPEAT_CODE = "9252";
    public static final String EXT_ASSET_CODE_REPEAT_DESC = "资产外部编码已存在";

    public static final String REPAID_CODE_REPEAT_CODE = "9253";
    public static final String REPAID_CODE_REPEAT_DESC = "借款已还清";

    public static final String P2P_LIMIT_ASSET_AMOUNT_CODE = "9254";
    public static final String P2P_LIMIT_ASSET_AMOUNT_DESC = "金额只能大于0小于等于10000";

    public static final String P2P_ASSET_RELATED_PRODUCT_NOT_EXISTS_CODE = "9255";
    public static final String P2P_ASSET_RELATED_PRODUCT_NOT_EXISTS_DESC = "相同资产池相同起息日期和期限的产品不存在";
}
