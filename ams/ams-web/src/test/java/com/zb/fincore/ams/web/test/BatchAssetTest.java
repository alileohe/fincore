package com.zb.fincore.ams.web.test;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.BatchAssetFacade;
import com.zb.fincore.ams.facade.dto.req.*;
import com.zb.fincore.ams.facade.dto.resp.BatchCreateAssetResponse;
import com.zb.fincore.ams.facade.model.BusinessCreditModel;
import com.zb.fincore.ams.facade.model.FileTemplateModel;
import com.zb.fincore.ams.facade.model.FileTemplateParamModel;
import com.zb.fincore.ams.facade.model.IssuePlanModel;
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
 * 批量录入资产
 * Created by MABIAO on 2017/6/15 0015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BatchAssetTest {

    @Autowired
    private BatchAssetFacade batchAssetFacade;

    /**
     * 文件导入
     */
    @Test
    public void importBatchAsset(){
        ImportBatchAssetRequest req = new ImportBatchAssetRequest();

        //计划表
        List<IssuePlanModel> planModelList = new ArrayList<IssuePlanModel>();
        IssuePlanModel planModel = new IssuePlanModel();
        planModel.setAssetCount(2);
        planModel.setCreditBusinessCode("BC1234");
        planModel.setContractDayCount(28);
        planModel.setFreeStartTime(DateUtils.parse("2017-06-20", "yyyy-MM-dd"));
        planModel.setFreeEndTime(DateUtils.parse("2017-06-20", "yyyy-MM-dd"));
        planModel.setSubjectCode("123");
        planModel.setProductDayCount(28);
        planModel.setProductValueEndTime(DateUtils.parse("2017-06-20", "yyyy-MM-dd"));
        planModel.setProductValueStartTime(DateUtils.parse("2017-06-20","yyyy-MM-dd"));
        planModel.setValueEndTime(DateUtils.parse("2017-06-20", "yyyy-MM-dd"));
        planModel.setSaleEndTime(DateUtils.parse("2017-06-20","yyyy-MM-dd"));
        planModel.setSaleStartTime(DateUtils.parse("2017-06-20","yyyy-MM-dd"));
        planModel.setValueStartTime(DateUtils.parse("2017-06-20","yyyy-MM-dd"));
        planModel.setSingleAmount(new BigDecimal("20"));
        planModel.setTotalAmount(new BigDecimal("40"));

        planModelList.add(planModel);
        req.setPlanModelList(planModelList);

        //授信
        BusinessCreditModel creditModel = new BusinessCreditModel();
        creditModel.setValueStartTime(DateUtils.parse("2017-06-20","yyyy-MM-dd"));
        creditModel.setValueEndTime(DateUtils.parse("2017-07-20", "yyyy-MM-dd"));
        creditModel.setAddress("");
        creditModel.setCertNo("456789");
        creditModel.setContactWay("fghj");
        creditModel.setCreditBusinessCode("123567");
        creditModel.setCreditLimitAmount(new BigDecimal("100"));
        creditModel.setDayCount(35);
        creditModel.setLegalPersonName("fjsjdgkj");
        creditModel.setRepaymentType("");
        creditModel.setYieldRate(new BigDecimal("12"));

        req.setCreditModel(creditModel);

        BatchCreateAssetResponse response = batchAssetFacade.importBatchAsset(req);
        System.out.println(response.getRespMsg());
    }

    /**
     * 模板录入
     */
    @RequestMapping(value = "createFileTemplate",method = RequestMethod.POST)
    public BaseResponse createFileTemplate(@RequestBody CreateFileTemplateRequest req){
        return batchAssetFacade.createFileTemplate(req);
    }

    /**
     * 模板详情
     */
    @RequestMapping(value = "queryFileTemplate",method = RequestMethod.POST)
    public QueryResponse<FileTemplateModel> queryFileTemplate(@RequestBody QueryFileTemplateRequest req){
        return batchAssetFacade.queryFileTemplate(req);
    }

    /**
     * 模板参数录入
     */
    @RequestMapping(value = "createFileTemplateParam",method = RequestMethod.POST)
    public BaseResponse createFileTemplateParam(@RequestBody CreateFileTemplateParamRequest req){
        return batchAssetFacade.createFileTemplateParam(req);
    }

    /**
     * 模板参数更新
     */
    @RequestMapping(value = "updateFileTemplateParam",method = RequestMethod.POST)
    public BaseResponse updateFileTemplateParam(@RequestBody CreateFileTemplateParamRequest req){
        return batchAssetFacade.updateFileTemplateParam(req);
    }

    /**
     * 模板参数详情
     */
    @RequestMapping(value = "queryFileTemplateParam",method = RequestMethod.POST)
    public QueryResponse<FileTemplateParamModel> queryFileTemplateParam(@RequestBody QueryFileTemplateParamRequest req){
        return batchAssetFacade.queryFileTemplateParam(req);
    }

    /**
     * 授信表导出
     */
    @RequestMapping(value = "exportBusinessCredit",method = RequestMethod.POST)
    public BaseResponse exportBusinessCredit(){

        return null;
    }

    /**
     * 备案信息导出
     */
    @RequestMapping(value = "exportRecordData",method = RequestMethod.POST)
    public BaseResponse exportRecordData(){

        return null;
    }

    /**
     * 合同导出
     */
    @RequestMapping(value = "exportContract",method = RequestMethod.POST)
    public BaseResponse exportContract(){

        return null;
    }

    /**
     * 资产备案
     */
    @RequestMapping(value = "recordAsset",method = RequestMethod.POST)
    public BaseResponse recordAsset(){

        return null;
    }
}
