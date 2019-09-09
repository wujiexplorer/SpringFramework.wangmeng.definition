package com.lx.benefits.bean.enums.yianapi.model;

import java.io.Serializable;

/**
 * Created by lidongri on 2018/11/17.
 */
public class YiAnOrderImportStatus implements Serializable {
    private static final long serialVersionUID = 2933781742938125712L;

    private int status =0;// 0没有状态信息 ，1 导入中，2导入完成

    private int total=0;

    private int add = 0;

    private int update = 0;
    private int error = 0;

    private int finished=0;

    private long beginTime;

    private int leftTime;//剩余时间 单位秒

    private String message;

    public YiAnOrderImportStatus() {
    }

    public YiAnOrderImportStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public int getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(int leftTime) {
        this.leftTime = leftTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAdd() {
        return add;
    }

    public void setAdd(int add) {
        this.add = add;
    }

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
