package com.jxzc.web.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("gaode")
public class GaodeController {

    @RequestMapping("index")
    public String index(){
        return "gaode/index";
    }

}
