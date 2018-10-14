package com.jxzc.model.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: jxzc
 * @PackageName: com.jxzc.model.web
 * @Description: 行业类别管理
 * @ClassName: IndustryCategoryController
 * @Auther: wanghongdong
 * @Date: 2018/9/26 22 04
 */
@Controller(value = "/industryCategory")
public class IndustryCategoryController {

    @RequestMapping("/list")
    public String list(ModelMap map) {
        map.put("msg", "Hello Freemarker");
        return "/industryCategory/list";
    }

    @RequestMapping("/edit")
    public String edit(ModelMap map,Integer id) {
        map.put("msg", "Hello Freemarker");
        return "/industryCategory/edit";
    }

}
