package com.zb.fincore.ams.web.test;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.PoolServiceFacade;
import com.zb.fincore.ams.facade.dto.req.CreatePoolRequest;
import com.zb.fincore.ams.facade.dto.req.QueryPoolListRequest;
import com.zb.fincore.ams.facade.dto.req.QueryPoolRequest;
import com.zb.fincore.ams.facade.dto.resp.CreatePoolResponse;
import com.zb.fincore.ams.facade.model.PoolModel;
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

/**
 * 功能: 资产池RESTFUL接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 15:49
 * 版本: V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PoolTest {

    @Autowired
    private PoolServiceFacade poolServiceFacade;

    /**
     * 创建资产池
     */
    @Test
    public void createPool() {
        CreatePoolRequest req = new CreatePoolRequest();
        req.setPoolName("资产00010");
        req.setPoolType(1);
        req.setPoolDesc("测试资产");
        req.setFinanceSubjectCode("FS17050006");
        req.setTrusteeCode("TR17050003");
        req.setLimitAmount(new BigDecimal("1000000"));
        req.setCreateBy("SYS");
        CreatePoolResponse response = poolServiceFacade.createPool(req);
        System.out.println(response.getRespMsg());
    }

    /**
     * 查询资产池列表
     */
    @Test
    public void queryPoolList() {
        QueryPoolListRequest req = new QueryPoolListRequest();

        PageQueryResponse<PoolModel> response = poolServiceFacade.queryPoolList(req);
        System.out.println(response.getRespMsg());
    }

    /**
     * 查询资产池详情
     */
    @Test
    public void queryPool() {
        QueryPoolRequest req = new QueryPoolRequest();
        req.setPoolCode("AMP170500013");
        QueryResponse<PoolModel> response = poolServiceFacade.queryPool(req);
        System.out.println(response.getRespMsg());
    }
}
