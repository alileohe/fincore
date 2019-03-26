package com.zb.fincore.common.filter;

import com.zb.fincore.common.encrypt.AesEncoder;
import com.zb.fincore.common.utils.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * 功能:
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/7/4 0004 13:47
 * 版本: V1.0
 */
public class FincoreHttpAesFilter implements Filter {

    /**
     * 日志记录器
     */
    private static Logger logger = LoggerFactory.getLogger(FincoreHttpAesFilter.class);

    /**
     * 是否需要解密标志
     */
    private boolean decode = true;

    /**
     * 字符集
     */
    private String encoding = "UTF-8";

    /**
     * 过滤不加解密的URL集合
     */
    private HashMap<String, String> excludeMap = new HashMap<>();

    /**
     * AES加密器
     */
    private AesEncoder aesEncoder;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String requestURI = httpReq.getRequestURI();
        logger.debug("HTTP请求地址:{}", requestURI);
        if (this.decode) {
            if (this.isExcludeRequestURI(requestURI)) {
                chain.doFilter(request, response);
                return;
            }
            String httpReqBody = this.getHttpRequestBody(httpReq);
            logger.debug("接收到加密http请求:{}", httpReqBody);
            FincoreHttpRequestWrapper httpRequestWrapper = new FincoreHttpRequestWrapper(httpReq);
            FincoreHttpResponseWrapper httpResponseWrapper = new FincoreHttpResponseWrapper((HttpServletResponse) response);
            try {
                if (StringUtils.isNotBlank(httpReqBody)) {
                    String decodeStr = aesEncoder.decrypt(httpReqBody);
                    logger.debug("加密http请求解密内容:{}", decodeStr);
                    httpRequestWrapper.setInputStream(decodeStr.getBytes(encoding));
                }
                chain.doFilter(httpRequestWrapper, httpResponseWrapper);
            } catch (Exception e) {
                logger.error("加密请求解密失败", e);
                //throw new ServletException("无法解析的加密内容");
                ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            String httpRespBody = httpResponseWrapper.getContent();
            if (StringUtils.isNotBlank(httpRespBody)) {
                ServletOutputStream out = null;
                try {
                    String encodeStr = aesEncoder.encrypt(httpRespBody);
                    logger.debug("加密http响应内容:{}", encodeStr);
                    byte[] buffer = encodeStr.getBytes();
                    // 重置响应输出的内容长度
                    response.setContentLength(buffer.length);
                    out = response.getOutputStream();
                    out.write(buffer);
                } catch (Exception e) {
                    logger.error("加密响应报文失败", e);
                    ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                    return;
                } finally {
                    if (out != null) {
                        out.flush();
                        out.close();
                    }
                }
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String isDecodeStr = filterConfig.getInitParameter("decode");
        if (StringUtils.isNotBlank(isDecodeStr)) {
            this.decode = Boolean.valueOf(isDecodeStr);
        }
        String encodingStr = filterConfig.getInitParameter("encoding");
        if (StringUtils.isNotBlank(encodingStr)) {
            this.encoding = encodingStr;
        }
        String excludeStr = filterConfig.getInitParameter("exclude");
        if (StringUtils.isNotBlank(excludeStr)) {
            String[] excludes = excludeStr.split(",");
            for (String exclude : excludes) {
                this.excludeMap.put(exclude, exclude);
            }
        }
        this.excludeMap.put("/internal/", "/internal/");

        this.aesEncoder = SpringUtils.getBean("aesEncoder", AesEncoder.class);
    }

    @Override
    public void destroy() {

    }

    private String getHttpRequestBody(HttpServletRequest httpServletRequest) throws IOException {
        BufferedReader reader = httpServletRequest.getReader();
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private boolean isExcludeRequestURI(String requestURI) {
        if (this.excludeMap != null) {
            for (String exclude : this.excludeMap.keySet()) {
                if (requestURI.contains(exclude)) {
                    logger.debug("请求地址:{}在加密清理过滤列表中", requestURI);
                    return true;
                }
            }
        }
        return false;
    }
}
