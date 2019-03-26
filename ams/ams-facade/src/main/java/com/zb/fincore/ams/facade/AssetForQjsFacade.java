package com.zb.fincore.ams.facade;

import com.zb.fincore.ams.facade.dto.req.CreateAssetForP2PRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetResponse;

/**
 * 功能: 侨金所资产服务接口
 * 创建: mabiao - mabiao@zillionfortune.com
 * 日期: 2017-10-10
 * 版本: V1.0
 */
public interface AssetForQjsFacade {

    /**
     * 创建资产
     * @param req
     * @return
     */
    CreateAssetResponse createAssetForQjs(CreateAssetForP2PRequest req);

    /**
     * 导入资产
     * @param req
     * @return
     */
    CreateAssetResponse importAssetForQjs(CreateAssetForP2PRequest req);

    /**
     * 上传信批信息
     * @param req
     * @return
     */
    CreateAssetResponse importAssetPublishInfo(CreateAssetForP2PRequest req);

}
