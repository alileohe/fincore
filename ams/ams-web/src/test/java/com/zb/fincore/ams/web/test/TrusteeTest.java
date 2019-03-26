package com.zb.fincore.ams.web.test;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.TrusteeServiceFacade;
import com.zb.fincore.ams.facade.dto.req.CreateTrusteeRequest;
import com.zb.fincore.ams.facade.dto.req.QueryTrusteeListRequest;
import com.zb.fincore.ams.facade.dto.req.QueryTrusteeRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateTrusteeResponse;
import com.zb.fincore.ams.facade.model.TrusteeModel;
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
 * 功能: 受托方服务RESTFUL接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/3/29 0029 13:51
 * 版本: V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TrusteeTest {

    @Autowired
    private TrusteeServiceFacade trusteeServiceFacade;

    /**
     * 创建受托方
     */
    @Test
    public void createTrustee() {
        CreateTrusteeRequest req = new CreateTrusteeRequest();
        req.setTrusteeName("受托方方005");
        req.setCertType(2);
        req.setCertNo("123456789012345678");
        req.setRegisteredCapital(new BigDecimal("10000"));
        req.setTrusteeAddress("上海市崇明岛");
        req.setLegalPersonName("SYS");
        req.setLegalPersonCertNo("123456789012345678");
        req.setBusinessScope("123");
        req.setIntroduction("介绍");
        req.setCreateBy("SYS");

        CreateTrusteeResponse response = trusteeServiceFacade.createTrustee(req);
        System.out.println(response.getRespMsg());
    }

    /**
     * 查询受托方列表
     */
    @Test
    public void queryTrusteeList() {
        QueryTrusteeListRequest req = new QueryTrusteeListRequest();
        req.setTrusteeName("受托方");
        PageQueryResponse<TrusteeModel> response = trusteeServiceFacade.queryTrusteeList(req);
        System.out.println(response.getRespMsg());
    }

    /**
     * 查询受托方详情
     */
    @Test
    public void queryTrustee() {
        QueryTrusteeRequest req = new QueryTrusteeRequest();
        req.setTrusteeCode("TR17060001");
        QueryResponse<TrusteeModel> response = trusteeServiceFacade.queryTrustee(req);
        System.out.println(response.getRespMsg());
    }
}
