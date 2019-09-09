package com.lx.benefits.bean.base.dto;

public class Result<T> {

    private boolean success = true;

    private String message;

    private String code;

    private T model;

    public static Result newResult() {
        return new Result();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public void setSucc() {
        this.success = true;
    }

    public void setFail() {
        this.success = false;
    }

    public boolean isSuccWithModel() {
        return success && model != null;
    }

    public boolean isModelNotNull() {
        return model != null;
    }
}
