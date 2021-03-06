package com.jxzc.web.bean;

import java.util.List;

public class PageBean<T> {

    private Integer code = 0;

    private String msg;

    private Integer count;

    private List<T> data;

    private Integer page = 1;

    private Integer limit = 10;

    public PageBean(Integer count, List<T> data, Integer page, Integer limit) {
        this.count = count;
        this.data = data;
        this.page = page;
        this.limit = limit;
    }

    public PageBean() {}

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public int getPages() {
        if (getLimit() == 0) {
            return 0;
        } else {
            return ((count + getLimit() - 1) / getLimit());
        }
    }

    public int getNextPageNo() {
        return Math.min(getPage() + 1, getPages());
    }

}
