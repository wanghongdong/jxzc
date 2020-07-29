package com.jxzc.web.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @ClassPath com.jxzc.web.web.TestController
 * @ClassName TestController
 * @Description TODO
 * @Author whd
 * @Date 2020/7/29 14:08
 * @Version 1.0
 */
@RequestMapping("test")
@Controller
public class TestController {


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Map<String,Object> map) {
        return "test/test";
    }


    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1(Map<String,Object> map) {
        return "test/test1";
    }
}
