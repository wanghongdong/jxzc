package com.jxzc.web.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassPath com.jxzc.web.web.HomeController
 * @ClassName HomeController
 * @Description TODO
 * @Author whd
 * @Date 2020/3/3 17:09
 * @Version 1.0
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

}
