package com.jxzc.web.service.impl;

import com.jxzc.web.constant.violation.ViolationConstant;
import com.jxzc.web.entity.Violation;
import com.jxzc.web.service.ViolationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassPath com.jxzc.web.service.impl.ViolationServiceJSSJImpl
 * @ClassName ViolationServiceJSSJImpl
 * @Description 极速数据违章查询
 * @Author whd
 * @Date 2020/3/4 15:24
 * @Version 1.0
 */
@Service(ViolationConstant.JS_SERVICE)
public class ViolationServiceJSSJImpl implements ViolationService {

    @Override
    public List<Violation> queryList(String carNo, String engineNo, String vin) {

        return null;
    }
}
