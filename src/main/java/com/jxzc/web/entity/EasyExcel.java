package com.jxzc.web.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
public class EasyExcel extends BaseRowModel {

    @ExcelProperty(value = "id", index = 0)
    private Integer id;

    @ExcelProperty(value = "id", index = 0)
    private String name;

    @ExcelProperty(value = "时间", index = 0)
    private Date date;

    @ExcelProperty(value = "id", index = 0)
    private Double money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}