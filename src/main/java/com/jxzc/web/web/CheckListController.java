package com.jxzc.web.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckListController {


    @RequestMapping(value = "/checkList/list")
    public String list(){
        return "checkList/list";
    }


}
