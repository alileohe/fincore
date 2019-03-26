package com.zb.fincore.common.utils;

import com.zb.fincore.common.encrypt.AesEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能: AES加密HTTP通讯客户端
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/7/5 0005 10:36
 * 版本: V1.0
 */
public class AesHttpClientUtils {

    private static Logger logger = LoggerFactory.getLogger(AesHttpClientUtils.class);

    /**
     * AES加密器
     */
    private AesEncoder aesEncoder;

    /**
     * 是否需要加密
     */
    private boolean encode = true;

    public String sendPostRequest(String reqURL, String sendData) throws Exception {
        logger.debug("HTTP请求地址:{}", reqURL);
        logger.debug("HTTP请求原文:{}", sendData);
        if (encode) {
            sendData = aesEncoder.encrypt(sendData);
            logger.debug("HTTP请求密文:{}", sendData);
        }
        String recvData = null;
        if (reqURL.toLowerCase().startsWith("https")) {
            recvData = HttpClientUtil.sendPostSSLRequestAes(reqURL, sendData);
        } else {
            recvData = HttpClientUtil.sendPostRequestAes(reqURL, sendData);
        }
        logger.debug("HTTP响应原文:{}", recvData);
        if (encode) {
            recvData = aesEncoder.decrypt(recvData);
            logger.debug("HTTP响应密文:{}", recvData);
            return recvData;
        } else {
            return recvData;
        }
    }

    public AesEncoder getAesEncoder() {
        return aesEncoder;
    }

    public void setAesEncoder(AesEncoder aesEncoder) {
        this.aesEncoder = aesEncoder;
    }

    public boolean isEncode() {
        return encode;
    }

    public void setEncode(boolean encode) {
        this.encode = encode;
    }
}
