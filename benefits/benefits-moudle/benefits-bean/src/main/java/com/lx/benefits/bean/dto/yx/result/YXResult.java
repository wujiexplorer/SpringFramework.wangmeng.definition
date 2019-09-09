package com.lx.benefits.bean.dto.yx.result;

import java.io.Serializable;

/**
 * Created by ldr on 2017/5/27.
 */
public class YXResult<T> implements Serializable {

    private static final long serialVersionUID = 554226962125845290L;

    public static final int SUCCESS_CODE = 200;

    private Integer code;

    private String msg;

    private T result;

    public YXResult() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean success(){
        return  code != null && code.equals(SUCCESS_CODE);
    }


    public YXResult(T result) {
        this.result = result;
        this.code = SUCCESS_CODE;
    }

    public YXResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
