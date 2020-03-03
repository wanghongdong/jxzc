package com.jxzc.web.web;

import lombok.Data;

/**
 * @ClassPath com.jxzc.web.web.BjPoints
 * @ClassName BjPoints
 * @Description TODO
 * @Author whd
 * @Date 2020/3/1 17:36
 * @Version 1.0
 */
@Data
public class BjPoints {

    private String lnt;
    private String lot;

    public BjPoints(String lnt, String lot) {
        this.lnt = lnt;
        this.lot = lot;
    }
}
