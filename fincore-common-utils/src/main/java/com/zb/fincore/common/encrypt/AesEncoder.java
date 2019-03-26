package com.zb.fincore.common.encrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * 功能: AES加密器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/7/4 0004 18:19
 * 版本: V1.0
 */
public class AesEncoder {

    private static Logger logger = LoggerFactory.getLogger(AesEncoder.class);

    /**
     * 密钥
     */
    private String key;

    /**
     * 字符集
     */
    private String encoding = "utf-8";

    /**
     * 加密
     *
     * @param content 待加密内容
     * @return
     */
    public String encrypt(String content) throws Exception {
        //logger.debug("encode key:{}", key);
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key.getBytes());
        kgen.init(128, random);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        byte[] byteContent = content.getBytes(encoding);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] byteRresult = cipher.doFinal(byteContent);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteRresult.length; i++) {
            String hex = Integer.toHexString(byteRresult[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        String encodeStr = sb.toString();
        //logger.debug("encode str:{}", encodeStr);
        return encodeStr;
    }

    /**
     * 解密
     *
     * @param content 待解密内容
     * @return
     */
    public String decrypt(String content) throws Exception {
        if (content.length() < 1)
            return null;
        byte[] byteRresult = new byte[content.length() / 2];
        for (int i = 0; i < content.length() / 2; i++) {
            int high = Integer.parseInt(content.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(content.substring(i * 2 + 1, i * 2 + 2), 16);
            byteRresult[i] = (byte) (high * 16 + low);
        }

        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key.getBytes());
        kgen.init(128, random);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] result = cipher.doFinal(byteRresult);
        return new String(result, encoding);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public static void main(String[] args) throws Exception {
        AesEncoder aesEncoder = new AesEncoder();
        aesEncoder.setKey("e#DxeTyUTNu@XJpU");
        String str = aesEncoder.encrypt("{\"productCode\" : \"123456\"}");
        //String str = aesEncoder.decrypt("13C108CE51D845B1E1DE461FF388C4162861AE4FCAD25A89149974B55649DC3A");
        System.out.println(str);
    }
}
