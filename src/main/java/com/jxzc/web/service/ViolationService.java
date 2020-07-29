package com.jxzc.web.service;

import com.jxzc.web.entity.Violation;

import java.util.List;

/**
 * @ClassPath com.jxzc.web.service.ViolationService
 * @ClassName ViolationService
 * @Description 违章信息查询
 * @Author whd
 * @Date 2020/3/3 16:19
 * @Version 1.0
 */

public interface ViolationService {

    /**
     * 查询违章
     * @author whd
     * @date 2020/3/4 15:21
     * @param carNo 车牌号
     * @param engineNo 发动机号
     * @param vin vin
     * @return java.util.List<com.jxzc.web.entity.Violation>
     **/
    public List<Violation> queryList(String carNo, String engineNo, String vin);

}
