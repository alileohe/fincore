package com.zb.fincore.ams.web.controller.job;

import com.zb.fincore.ams.facade.AssetServiceFacade;
import com.zb.fincore.common.utils.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 资产成立JOB
 * Created by MABIAO on 2017/7/5 0005.
 */
public class AssetExpiredJob {

    private static final Logger LOG = LoggerFactory.getLogger(AssetExpiredJob.class);

    @Autowired
    private AssetServiceFacade assetServiceFacade;

    public void doAssetExpiredJob() {
        String jobScheduleConfig = PropertiesUtil.getValue("JOB_SCHEDULE_CONFIG");
        if(StringUtils.isBlank(jobScheduleConfig) || !"Y".equals(jobScheduleConfig)){
            return;
        }

        LOG.info("--------*****-------------doAssetExpiredJob start:" + new Date());
        assetServiceFacade.putAssetExpired();
        LOG.info("--------*****-------------doAssetExpiredJob end:" + new Date());
    }
}
