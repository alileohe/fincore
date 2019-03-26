package com.zb.fincore.ams.service.dal.dao;

import com.zb.fincore.ams.common.model.Page;
import com.zb.fincore.ams.facade.model.PoolLeftAssetModel;
import com.zb.fincore.ams.service.dal.model.Pool;

import java.util.List;
import java.util.Map;

public interface PoolDao {

    int selectCount(Pool record);

    List<Pool> select(Pool record, Page page);

    List<Pool> selectPoolDetailList(Pool record, Page page);

    int deleteByPrimaryKey(Long id);

    int insert(Pool record);

    int insertSelective(Pool record);

    Pool selectByPrimaryKey(Long id);

    Pool selectByCode(String poolCode);

    int updateSelectiveWithLock(Pool record);

    int updateByPrimaryKey(Pool record);

    /**
     * 校验资产池名称是否重复
     * @param poolName
     * @return
     */
    int selectPoolCountByName(String poolName);

    /**
     *  资产池详情，关联融资方和受托方
     * @param poolCode
     * @return
     */
    Pool selectDetailByCode(String poolCode);

    /**
     * 查询剩余资产
     * @param param
     * @param page
     * @return
     */
    List<PoolLeftAssetModel> queryLeftAssetAmountList(Map<String,Object> param,Page page);

    /**
     * 查询剩余资产数量
     * @param param
     * @return
     */
    int queryLeftAssetAmountListCount(Map<String,Object> param);
}