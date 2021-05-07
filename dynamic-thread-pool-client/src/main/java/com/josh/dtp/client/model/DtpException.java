package com.josh.dtp.client.model;


/**
 * 描述: 异常类
 *
 * @author Josh
 * @date 2021/4/25 6:05 下午
 */
public class DtpException extends RuntimeException {
    private int code;
    private String errorMessage;

    public DtpException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public DtpException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
        this.errorMessage = errorMessage;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
