package com.jxzc.web.service.impl;

import com.jxzc.web.constant.violation.ViolationConstant;
import com.jxzc.web.entity.Violation;
import com.jxzc.web.service.ViolationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassPath com.jxzc.web.service.impl.ViolationServiceCXYImpl
 * @ClassName ViolationServiceCXYImpl
 * @Description 车行易查询违章
 * @Author whd
 * @Date 2020/3/4 15:24
 * @Version 1.0
 */
@Service(ViolationConstant.CXY_SERVICE)
public class ViolationServiceCXYImpl implements ViolationService {

    private static final String cxy_host = "";
    private static final String cxy_city_url = "";

    @Override
    public List<Violation> queryList(String carNo, String engineNo, String vin) {



        return null;
    }
}
