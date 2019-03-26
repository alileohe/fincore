package com.zb.fincore.common.filter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * 功能:
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/7/4 0004 16:50
 * 版本: V1.0
 */
public class FincoreHttpRequestWrapper extends HttpServletRequestWrapper {

    private BufferedServletInputStream inputStream;

    public FincoreHttpRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return this.inputStream;
    }

    public void setInputStream(byte[] buffer) {
        this.inputStream = new BufferedServletInputStream(buffer);
    }
}
