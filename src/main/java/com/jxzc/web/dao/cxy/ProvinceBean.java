package com.jxzc.web.dao.cxy;

import java.util.List;

/**
 * @ClassPath com.jxzc.web.dao.cxy.ProvinceBean
 * @ClassName ProvinceBean
 * @Description 省份
 * @Author whd
 * @Date 2020/3/4 16:20
 * @Version 1.0
 */

public class ProvinceBean {

    /**
     * ProvinceID : 34
     * ProvinceName : 安徽
     * ProvincePrefix : 皖
     * Cities : [{"CityID":3401,"CityName":"安徽合肥","Name":"合肥","CarNumberPrefix":"皖A","CarCodeLen":6,"CarEngineLen":6,"CarOwnerLen":0,"ProxyEnable":0}]
     */
    /** 省份id */
    private int ProvinceID;
    /** 省份名称 */
    private String ProvinceName;
    /** 车牌前缀 */
    private String ProvincePrefix;
    /** 城市集合 */
    private List<CitiesBean> Cities;

}
