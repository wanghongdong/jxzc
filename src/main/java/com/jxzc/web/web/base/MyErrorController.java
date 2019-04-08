package com.jxzc.web.web.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController{

    private static final Logger logger = LoggerFactory.getLogger(MyErrorController.class);

    @RequestMapping("error-404")
    public String toPage404(){
        return "common/error/error-404";
    }
    @RequestMapping("error-400")
    public String toPage400(){
        return "common/error/error-400";
    }
    @RequestMapping("error-500")
    public String toPage500(){
        return "common/error/error-500";
    }

}



