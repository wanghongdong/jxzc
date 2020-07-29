package com.jxzc.web.dao.cxy;

import lombok.Data;

/**
 * @ClassPath com.jxzc.web.dao.cxy.CitiesBean
 * @ClassName CitiesBean
 * @Description 城市
 * @Author whd
 * @Date 2020/3/4 16:26
 * @Version 1.0
 */
@Data
public class CitiesBean {

    /**
     * CityID : 3401
     * CityName : 安徽合肥
     * Name : 合肥
     * CarNumberPrefix : 皖A
     * CarCodeLen : 6
     * CarEngineLen : 6
     * CarOwnerLen : 0
     * ProxyEnable : 0
     */
    /** 城市id */
    private int CityID;
    /** 城市全名（省+市） */
    private String CityName;
    /** 城市名称 */
    private String Name;
    /** 城市所对应的车牌前缀，除京，沪，津，渝的代码为1位之外，其他城市代码为2位（汉字+字母） */
    private String CarNumberPrefix;
    /** 查询所需车架号长度后N位   其中：0 代表不需要   99 代表完整  具体数字代表 后多少位  如 6 代表需要车架号后6位  99 代表需要完整车架号（下同） */
    private int CarCodeLen;
    /** 查询所需发动机号长度后N位 */
    private int CarEngineLen;
    /** 查询所需车主驾驶证号码后N位，可暂时忽略此字段要求。 */
    private int CarOwnerLen;
    private int ProxyEnable;

}
