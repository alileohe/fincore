package com.zb.fincore.pms.web.controller.job;

import com.zb.fincore.common.utils.PropertiesUtil;
import com.zb.fincore.pms.facade.product.ProductJobServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 产品阶梯信息更新 JOB
 * description
 * @author
 * @create 2017-06-12 11:00
 */
public class ProductLadderInfoUpdateJob {

    private static final Logger LOG = LoggerFactory.getLogger(ProductLadderInfoUpdateJob.class.getName());

    @Autowired
    ProductJobServiceFacade productJobServiceFacade;

    public void doUpdateProductLadderInfoJob() {
        String jobScheduleConfig = PropertiesUtil.getValue("JOB_SCHEDULE_CONFIG");
        if(StringUtils.isBlank(jobScheduleConfig) || !"Y".equals(jobScheduleConfig)){
            return;
        }

        LOG.info("--------*****-------------doUpdateProductLadderInfoJob start:" + new Date());
        productJobServiceFacade.updateProductLadderInfo();
        LOG.info("--------*****-------------doUpdateProductLadderInfoJob end:" + new Date());
    }
}
