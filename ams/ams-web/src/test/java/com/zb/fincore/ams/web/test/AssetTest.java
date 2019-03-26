package com.zb.fincore.ams.web.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.facade.AssetForP2PFacade;
import com.zb.fincore.ams.facade.AssetServiceFacade;
import com.zb.fincore.ams.facade.dto.req.BatchCreateAssetRequest;
import com.zb.fincore.ams.facade.dto.req.CreateAssetRequest;
import com.zb.fincore.ams.facade.dto.req.QueryAssetListForManageRequest;
import com.zb.fincore.ams.facade.dto.req.QueryAssetRequest;
import com.zb.fincore.ams.facade.dto.req.UpdateAssetStockRequest;
import com.zb.fincore.ams.facade.dto.resp.BatchCreateAssetResponse;
import com.zb.fincore.ams.facade.dto.resp.CreateAssetResponse;
import com.zb.fincore.ams.facade.model.AssetModel;
import com.zb.fincore.common.utils.DateUtils;

/**
 * 功能: 资产RESTFUL接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 11:25
 * 版本: V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AssetTest {

    @Autowired
    private AssetServiceFacade assetServiceFacade;
    
    @Autowired
    private AssetForP2PFacade assetForP2PFacade;

    /**
     * 创建底层资产
     */
//    @Test
    public void createAsset() {
        CreateAssetRequest req = new CreateAssetRequest();
        req.setAssetName("资产00025");
        req.setAssetType(1);
        req.setFinanceProject("资产00025项目");
        req.setProjectDesc("资产0007项目描述");
        req.setRepayGuarenteeMode("资产0007还款保障措施");
        req.setCreditMode("资产0007增信措施");
        req.setAssetAmount(new BigDecimal("1000000"));
        req.setFinanceSubjectId(null);
        req.setFinanceSubjectCode("FS17050001");
        req.setEstablishTime(DateUtils.parse("2017-05-10 18:00:00", "yyyy-MM-dd HH:mm:ss"));
        req.setValueStartTime(DateUtils.parse("2017-05-10 18:00:00", "yyyy-MM-dd HH:mm:ss"));
        req.setValueEndTime(DateUtils.parse("2018-05-28 18:00:00", "yyyy-MM-dd HH:mm:ss"));//
        req.setExpireTime(DateUtils.parse("2018-05-28 18:00:00", "yyyy-MM-dd HH:mm:ss"));//到期日
        req.setTransferLockDays(18);
        req.setValueDays(18);
        req.setYieldRate(new BigDecimal("0.11"));
        req.setRepaymentType(1);
        req.setAssetDesc("");
        req.setCreateBy("SYS");
        CreateAssetResponse response = assetServiceFacade.createAsset(req);
        System.out.println(response.getRespMsg());
    }

    /**
     * 资产管理查询资产列表
     */
    @Test
    public void queryAssetListForManage() {
        QueryAssetListForManageRequest req = new QueryAssetListForManageRequest();
        req.setFinanceSubjectCode("");
        req.setAssetCode("");
        req.setAssetName("");
        req.setAssetType(null);
        req.setCollectStatus(null);
        req.setApprovalStatus(null);
        req.setPageNo(1);
        req.setPageSize(10);
        PageQueryResponse<AssetModel> response = assetServiceFacade.queryAssetListForManage(req);
        System.out.println(response.getRespMsg());
    }

    /**
     * 批量创建底层资产
     */
//    @Test
    public void batchCreateAsset() {
        BatchCreateAssetRequest req = new BatchCreateAssetRequest();
        List<CreateAssetRequest> requestList = new ArrayList<CreateAssetRequest>();
        CreateAssetRequest creatRequest1 = new CreateAssetRequest();
        creatRequest1.setAssetName("资产00032");
        creatRequest1.setAssetType(1);
        creatRequest1.setFinanceProject("资产00025项目");
        creatRequest1.setProjectDesc("资产0007项目描述");
        creatRequest1.setRepayGuarenteeMode("资产0007还款保障措施");
        creatRequest1.setCreditMode("资产0007增信措施");
        creatRequest1.setAssetAmount(new BigDecimal("1000000"));
        creatRequest1.setFinanceSubjectId(null);
        creatRequest1.setFinanceSubjectCode("FS17050001");
        creatRequest1.setEstablishTime(DateUtils.parse("2017-05-10 18:00:00", "yyyy-MM-dd HH:mm:ss"));
        creatRequest1.setValueStartTime(DateUtils.parse("2017-05-10 18:00:00", "yyyy-MM-dd HH:mm:ss"));
        creatRequest1.setValueEndTime(DateUtils.parse("2018-05-28 18:00:00", "yyyy-MM-dd HH:mm:ss"));//
        creatRequest1.setExpireTime(DateUtils.parse("2018-05-28 18:00:00", "yyyy-MM-dd HH:mm:ss"));//到期日
        creatRequest1.setTransferLockDays(18);
        creatRequest1.setValueDays(18);
        creatRequest1.setYieldRate(new BigDecimal("0.11"));
        creatRequest1.setRepaymentType(1);
        creatRequest1.setAssetDesc("");
        creatRequest1.setCreateBy("SYS");

        requestList.add(creatRequest1);
        req.setCreateAssetRequestList(requestList);
        BatchCreateAssetResponse response = assetServiceFacade.batchCreateAsset(req);
        System.out.println(response.getRespMsg());
    }

    /**
     * 资产审核列表
     */
//    @Test
    public void queryAssetListForApproval() {
        QueryAssetListForManageRequest req = new QueryAssetListForManageRequest();
        req.setAssetCode("");
        req.setAssetName("");
        req.setAssetType(null);
        req.setCollectStatus(null);
        req.setFinanceSubjectCode("");
        req.setBeginCreateTime("");
        req.setEndCreateTime("");
        req.setPageSize(1);
        req.setPageNo(10);

        PageQueryResponse<AssetModel> response = assetServiceFacade.queryAssetListForApproval(req);
        System.out.println(response.getRespMsg());
    }

    /**
     * 查询资产详情
     */
//    @Test
    public void queryAsset() {
        QueryAssetRequest req = new QueryAssetRequest();
        req.setAssetCode("AMA170500025");
        QueryResponse<AssetModel> response = assetServiceFacade.queryAsset(req);
        System.out.println(response.getRespMsg());
    }

    /**
     * 资产成立JOB
     */
    @Test
    public void putAssetEstablished(){
        BaseResponse response = assetServiceFacade.putAssetEstablished();
        System.out.println(response.getRespMsg());
    }

    /**
     * 资产到期JOB
     */
    @Test
    public void putAssetExpired(){
        BaseResponse response = assetServiceFacade.putAssetExpired();
        System.out.println(response.getRespMsg());
    }
    
    /**
     * 更新资产库存
     */
    @Test
    public void updateAssetStock() {
    	UpdateAssetStockRequest req = new UpdateAssetStockRequest();
    	req.setExtAssetCode("MSD006");
    	req.setAssetAmount(new BigDecimal(100));
    	BaseResponse response = assetForP2PFacade.updateAssetStock(req);
    	System.out.println(response.getRespMsg());
    }
}
