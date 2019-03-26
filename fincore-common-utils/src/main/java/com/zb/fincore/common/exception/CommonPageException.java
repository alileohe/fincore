package com.zb.fincore.common.exception;

/**
 * ClassName: CommonException <br/>
 * Function: 异常处理. <br/>
 * Date: 2016年12月13日 上午10:40:29 <br/>
 *
 * @author mengkai@zb.com
 * @since JDK 1.7
 */
public class CommonPageException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;

    private String message;

    private Throwable exception;

    public CommonPageException() {

    }

    public CommonPageException(String message) {
        super(message);
        this.message = message;
    }

    public CommonPageException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public CommonPageException(String message, Throwable e) {
        super(message);
        this.message = message;
        this.exception = e;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
