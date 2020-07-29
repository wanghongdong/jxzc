package com.jxzc.web.service;

import com.jxzc.web.constant.violation.ViolationEnum.ServiceName;
import com.jxzc.web.entity.Violation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassPath com.jxzc.web.service.ViolationContent
 * @ClassName ViolationContent
 * @Description 查询
 * @Author whd
 * @Date 2020/3/4 15:29
 * @Version 1.0
 */
@Service
public class ViolationContent {

    @Autowired
    private Map<String, ViolationService> strategyMap = new ConcurrentHashMap<>();

    public List<Violation> queryList(String carNo, String engineNo, String vin, ServiceName serviceName){
        ViolationService service = strategyMap.get(serviceName.getValue());
        return service.queryList(carNo, engineNo, vin);
    }

}
