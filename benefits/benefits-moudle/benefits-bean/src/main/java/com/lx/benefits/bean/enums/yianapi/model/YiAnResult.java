package com.lx.benefits.bean.enums.yianapi.model;

import java.io.Serializable;

/**
 * Created by lidongri on 2018/11/14.
 */
public class YiAnResult<T> implements Serializable {

    private static final long serialVersionUID = 8396139395158055279L;
    private T result;

    private String result_code;

    private String result_message;

    private boolean success;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getResult_message() {
        return result_message;
    }

    public void setResult_message(String result_message) {
        this.result_message = result_message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
