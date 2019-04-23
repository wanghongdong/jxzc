package com.jxzc.web.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("gaode")
public class GaodeController {

    @RequestMapping("index")
    public String index(Model model){
        JSONObject demo = new JSONObject();
        demo.put("name", "王洪东");
        demo.put("age", 26);
        model.addAttribute("demo", demo);
        return "gaode/index";
    }

}
