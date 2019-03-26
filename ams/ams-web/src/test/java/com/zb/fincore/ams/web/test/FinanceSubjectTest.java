package com.zb.fincore.ams.web.test;

import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.FinanceSubjectServiceFacade;
import com.zb.fincore.ams.facade.dto.req.CreateFinanceSubjectRequest;
import com.zb.fincore.ams.facade.dto.req.QueryFinanceSubjectListRequest;
import com.zb.fincore.ams.facade.dto.req.QueryFinanceSubjectRequest;
import com.zb.fincore.ams.facade.dto.resp.CreateFinanceSubjectResponse;
import com.zb.fincore.ams.facade.model.FinanceSubjectModel;
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
 * 功能: 融资主体服务RESTFUL接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/3/29 0029 13:51
 * 版本: V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class FinanceSubjectTest {

    @Autowired
    private FinanceSubjectServiceFacade financeSubjectServiceFacade;

    /**
     * 创建发行方
     */
    @Test
    public void createFinanceSubject() {
        CreateFinanceSubjectRequest req = new CreateFinanceSubjectRequest();
        req.setSubjectName("发行方008");
        req.setCertType(2);
        req.setCertNo("123456789012345678");
        req.setRegisteredCapital(new BigDecimal("10001"));
        req.setSubjectAddress("T10001");
        req.setLegalPersonName("SYS");
        req.setLegalPersonCertNo("123456789012345678");
        req.setBusinessScope("123");
        req.setIntroduction("");
        req.setCreateBy("SYS");

        CreateFinanceSubjectResponse response = financeSubjectServiceFacade.createFinanceSubject(req);
        System.out.println(response.getRespMsg());
    }

    /**
     * 查询融资主体列表
     * @return 分页查询基础响应对象
     */
    @Test
    public void queryFinanceSubjectList() {
        QueryFinanceSubjectListRequest req = new QueryFinanceSubjectListRequest();
        req.setSubjectName("发行方");
        PageQueryResponse<FinanceSubjectModel> response = financeSubjectServiceFacade.queryFinanceSubjectList(req);
        System.out.print(response.getRespMsg());
    }

    /**
     * 查询融资主体详情
     */
    @Test
    public void queryFinanceSubject() {
        QueryFinanceSubjectRequest req = new QueryFinanceSubjectRequest();
        req.setSubjectCode("FS17060001");
        QueryResponse<FinanceSubjectModel> response = financeSubjectServiceFacade.queryFinanceSubject(req);
        System.out.print(response.getRespMsg());
    }
}
