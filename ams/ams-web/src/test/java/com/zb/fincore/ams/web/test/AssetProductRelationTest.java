package com.zb.fincore.ams.web.test;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.facade.AssetProductRelationServiceFacade;
import com.zb.fincore.ams.facade.dto.req.CancelAssetProductRelationRequest;
import com.zb.fincore.ams.facade.dto.req.ChangeAssetProductRelationRequest;
import com.zb.fincore.ams.facade.dto.req.CreateAssetProductRelationRequest;
import com.zb.fincore.ams.facade.dto.req.QueryAssetProductRelationRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetProductRelationResponse;
import com.zb.fincore.ams.facade.model.AssetProductRelationModel;
import com.zb.fincore.ams.facade.model.ChangeAssetProductModel;
import com.zb.fincore.common.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能: 产品资产关系RESTFUL接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/18 0018 17:34
 * 版本: V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AssetProductRelationTest {

    @Autowired
    private AssetProductRelationServiceFacade assetProductRelationServiceFacade;

    /**
     * 查询产品资产匹配关系
     */
    @Test
    public void queryAssetProductRelation() {
        QueryAssetProductRelationRequest req = new QueryAssetProductRelationRequest();
        req.setProductCode("P2017050005");

        PageQueryResponse<AssetProductRelationModel> response = assetProductRelationServiceFacade.queryAssetProductRelation(req);
        System.out.println(response.getRespMsg());
    }

    /**
     * 创建产品资产匹配关系(占用资产)
     */
//    @Test
    public void createAssetProductRelation() {
        CreateAssetProductRelationRequest req = new CreateAssetProductRelationRequest();
        req.setProductCode("P2017050006");
        req.setPoolCode("AMP170500008");
        req.setProductAmount(new BigDecimal("200000"));
        req.setProductValueStartTime(DateUtils.parse("2018-05-17 18:00:00", "yyyy-MM-dd HH:mm:ss"));
        req.setProductExpireTime(DateUtils.parse("2017-05-28 18:00:00", "yyyy-MM-dd HH:mm:ss"));

        CreateAssetProductRelationResponse response = assetProductRelationServiceFacade.createAssetProductRelation(req);
        System.out.println(response.getRespMsg());
    }

    /**
     * 更改产品资产匹配关系(资产匹配/赎回释放)
     */
//    @Test
    public void changeAssetProductRelation() {
        ChangeAssetProductRelationRequest req = new ChangeAssetProductRelationRequest();
        req.setProductCode("P2017050006");

        List<ChangeAssetProductModel> changeAssetProductModels = new ArrayList<>();
        ChangeAssetProductModel model = new ChangeAssetProductModel();
        model.setAssetCode("AMA170500024");
        model.setChangeAmount(new BigDecimal("10000"));
        model.setChangeType(1);
        model.setPoolCode("AMP170500008");
        changeAssetProductModels.add(model);
        req.setChangeAssetProductModels(changeAssetProductModels);

        BaseResponse response = assetProductRelationServiceFacade.changeAssetProductRelation(req);
        System.out.println(response.getRespMsg());
    }

    /**
     * 取消产品资产匹配关系(产品审核不通过,占用资产释放)
     */
    @Test
    public void cancelAssetProductRelation() {
        CancelAssetProductRelationRequest req = new CancelAssetProductRelationRequest();
        req.setProductCode("P2017050006");
        BaseResponse response = assetProductRelationServiceFacade.cancelAssetProductRelation(req);
        System.out.println(response.getRespMsg());
    }
}
