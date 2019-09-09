package com.lx.benefits.bean.dto.jd.res;


import java.io.Serializable;

/**
 * Created by ldr on 2017/3/1.
 */
public class JDResult<T> implements Serializable {

    private static final long serialVersionUID = 7164913309829414020L;

    private  boolean success;

    private String resultMessage;

    private String resultCode;

    private T result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
