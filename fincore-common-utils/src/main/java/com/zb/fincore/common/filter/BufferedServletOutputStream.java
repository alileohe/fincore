package com.zb.fincore.common.filter;

import javax.servlet.ServletOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 功能:
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/7/4 0004 18:53
 * 版本: V1.0
 */
public class BufferedServletOutputStream extends ServletOutputStream {

    private ByteArrayOutputStream bos = null;

    public BufferedServletOutputStream(ByteArrayOutputStream stream) throws IOException {
        bos = stream;
    }

    @Override
    public void write(int b) throws IOException {
        bos.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        bos.write(b, 0, b.length);
    }
}
