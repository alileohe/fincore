package com.zb.fincore.ams.service.dal.dao;

import com.zb.fincore.ams.service.dal.model.GlobalConfig;

/**
 * 功能: 全局参数数据访问对象
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/20 0020 08:55
 * 版本: V1.0
 */
public interface GlobalConfigDao {

    GlobalConfig selectByPropertyName(String propertyName);
}
