package com.zb.fincore.ams.service.dal.dao;

import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.service.dal.model.Asset;

import java.util.List;
import java.util.Map;

public interface AssetDao {

    int selectCount(Asset record);

    List<Asset> select(Asset record, Page page);

    List<Asset> selectDetail(Asset record, Page page);

    int deleteByPrimaryKey(Long id);

    int insert(Asset record);

    int insertSelective(Asset record);

    Asset selectByPrimaryKey(Long id);

    Asset selectByCode(String assetCode);

    Asset selectByExtAssetCode(String extAssetCode);
    
    Asset selectDetailByCode(String assetCode);

    int updateSelectiveWithLock(Asset record);

    int updateByPrimaryKey(Asset record);

    int batchUpdateWithLock(List<Asset> records);

    /**
     * 校验资产名称是否重复
     */
    int selectAssetCountByName(String assetName);

    /**
     * 查询资产池可匹配资产
     */
    List<Asset> queryPoolAssetList(Asset record,Page page);

    /**
     * 查询资产池可匹配资产总数
     */
    int queryPoolAssetListCount(Asset record);

    /**
     * 资产详情列表数量
     */
    int selectDetailCount(Asset record);

    /**
     * 资产列表 JOB
     */
    List<Asset> queryAssetListForJob(Asset record);

    /**
     * 授信资产录入审核列表数量
     */
    int queryCreditAssetListForApprovalCount(Map<String,Object> pram);

    /**
     * 授信资产录入审核列表
     */
    List<Asset> queryCreditAssetListForApproval(Map<String,Object> pram,Page page);

    /**
     * 授信资产生成合同列表
     */
    int queryUnPackageAssetListCount(Map<String,Object> pram);

    /**
     * 授信资产生成合同列表
     */
    List<Asset> queryUnPackageAssetList(Map<String,Object> pram,Page page);

    /**
     * 备案资产列表
     */
    int queryRecordAssetListCount(Map<String,Object> pram);

    /**
     * 备案资产列表
     */
    List<Asset> queryRecordAssetList(Map<String, Object> pram, Page page);

    List<Asset> selectAssetListByBatchNo(String batchNo);

    /**
     * 更新披露信息
     */
    int updatePublishInfo(Asset record);

    /**
     * 唐小僧导入资产列表
     */
    List<Asset> queryAssetListForTxs(Map<String,Object> pram);

}