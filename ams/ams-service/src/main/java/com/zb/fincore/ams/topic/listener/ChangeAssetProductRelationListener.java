package com.zb.fincore.ams.topic.listener;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.enums.ReleaseTypeEnum;
import com.zb.fincore.ams.common.exception.BusinessException;
import com.zb.fincore.ams.facade.dto.req.ChangeAssetProductRelationRequest;
import com.zb.fincore.ams.facade.model.ChangeAssetProductModel;
import com.zb.fincore.ams.service.AssetProductRelationService;
import com.zb.fincore.common.utils.JsonUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能: 变更产品资产占用关系监听器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/4/19 0019 10:47
 * 版本: V1.0
 */
public class ChangeAssetProductRelationListener implements MessageListener {

    /**
     * 日志记录器
     */
    private static Logger logger = LoggerFactory.getLogger(ChangeAssetProductRelationListener.class);

    @Autowired
    private AssetProductRelationService assetProductRelationService;

    @Override
    public Action consume(Message message, ConsumeContext context) {
        try {
            JSONObject jsonObject = JSONObject.fromObject(new String(message.getBody(), "UTF-8"));

            Map<String, Class> map = new HashMap<String, Class>();
            map.put("changeAssetProductModels", ChangeAssetProductModel.class);
            ChangeAssetProductRelationRequest req = (ChangeAssetProductRelationRequest) JSONObject.toBean(jsonObject, ChangeAssetProductRelationRequest.class, map);

            String info = new String(message.getBody(), "UTF-8");
            logger.info("TA资产匹配请求参数:"+info);
            BaseResponse response = null;
            if(null != req.getReleaseType() && req.getReleaseType() == ReleaseTypeEnum.UN_SALE.getCode()){//产品未售
                response = assetProductRelationService.releaseAssetForProductUnSale(req);
            }else{//产品已售
                response = assetProductRelationService.changeAssetProductRelation(req);
            }
            String res = JsonUtils.beanToJson(response);
            logger.info("TA资产匹配请求返回参数:"+res);
            return Action.CommitMessage;
        }catch (BusinessException b){
            logger.error("处理消息异常:"+b.getResultMsg(), b);
        }catch (Exception e) {
            logger.error("处理消息异常:"+e.getMessage(), e);
        }
        return Action.ReconsumeLater;
    }
}
