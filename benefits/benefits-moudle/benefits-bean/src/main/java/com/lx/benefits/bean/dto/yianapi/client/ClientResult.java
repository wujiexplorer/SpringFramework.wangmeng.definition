package com.lx.benefits.bean.dto.yianapi.client;

import java.io.Serializable;

/**
 * Created by lidongri on 2018/12/1.
 */
public class ClientResult<T> implements Serializable {

    private String result_code;

    private String result_message;

    private Boolean success;

    private T result;

    public ClientResult() {
    }

    public ClientResult(String result_code, String result_message, Boolean success) {
        this.result_code = result_code;
        this.result_message = result_message;
        this.success = success;
    }

    public ClientResult(String result_code, String result_message, Boolean success, T result) {
        this.result_code = result_code;
        this.result_message = result_message;
        this.success = success;
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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
