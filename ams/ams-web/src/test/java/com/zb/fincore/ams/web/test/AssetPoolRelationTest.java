package com.zb.fincore.ams.web.test;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.facade.AssetPoolRelationServiceFacade;
import com.zb.fincore.ams.facade.dto.req.CreateAssetPoolRelationRequest;
import com.zb.fincore.ams.facade.dto.req.QueryAssetPoolRelationRequest;
import com.zb.fincore.ams.facade.dto.req.QueryPoolAssetListRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetPoolRelationResponse;
import com.zb.fincore.ams.facade.model.AssetModel;
import com.zb.fincore.ams.facade.model.AssetPoolRelationModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能: 资产池资产匹配关系RESTFUL接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/21 0021 17:08
 * 版本: V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AssetPoolRelationTest {

    @Autowired
    private AssetPoolRelationServiceFacade assetPoolRelationServiceFacade;

    /**
     * 创建资产资产池关联关系
     */
    @Test
    public void createAssetPoolRelation() {
        CreateAssetPoolRelationRequest req = new CreateAssetPoolRelationRequest();
        req.setPoolCode("AMP170500008");
        req.setCreateBy("SYS");

        List<String> assetCodeList = new ArrayList<>();
        assetCodeList.add("AMA170500032");
        req.setAssetCodeList(assetCodeList);

        CreateAssetPoolRelationResponse response = assetPoolRelationServiceFacade.createAssetPoolRelation(req);
        System.out.println(response.getRespMsg());
    }

    /**
     * 查询资产资产池关联关系
     */
    @Test
    public void queryAssetPoolRelation() {
        QueryAssetPoolRelationRequest req = new QueryAssetPoolRelationRequest();
        req.setPoolCode("AMP170500008");
        PageQueryResponse<AssetPoolRelationModel> response = assetPoolRelationServiceFacade.queryAssetPoolRelation(req);
        System.out.println(response.getRespMsg());
    }


    /**
     * 查询资产池可匹配资产列表
     */
    @Test
    public void queryPoolAssetList() {
        QueryPoolAssetListRequest req = new QueryPoolAssetListRequest();
        req.setPoolCode("AMP170500008");
        PageQueryResponse<AssetModel> assetModel = assetPoolRelationServiceFacade.queryPoolAssetList(req);
        System.out.println(assetModel.getRespMsg());
    }
}
