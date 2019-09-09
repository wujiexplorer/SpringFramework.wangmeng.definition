package com.lx.benefits.bean.dto.yianapi.client;

/**
 * Created by lidongri on 2018/12/1.
 */
public class ClientPageResp<T> {

    private int page = 1;

    private int total_page = 0;

    private int total_row = 0;

    private int size = 100;

    private T rows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public int getTotal_row() {
        return total_row;
    }

    public void setTotal_row(int total_row) {
        this.total_row = total_row;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }
}
