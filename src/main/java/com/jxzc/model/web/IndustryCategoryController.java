package com.jxzc.model.web;

import com.jxzc.model.bean.PageBean;
import com.jxzc.model.dao.IndustryCategoryMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: jxzc
 * @PackageName: com.jxzc.model.web
 * @Description: 行业类别管理
 * @ClassName: IndustryCategoryController
 * @Auther: wanghongdong
 * @Date: 2018/9/26 22 04
 */
@Controller
public class IndustryCategoryController {


    IndustryCategoryMapper IndustryCategoryMapper;


    @RequestMapping(value = "/industryCategory/list",method = RequestMethod.GET)
    public String list(ModelMap map) {
        map.put("msg", "Hello Freemarker");
        return "industryCategory/list";
    }

    @ResponseBody
    @RequestMapping(value = "/industryCategory/list",method = RequestMethod.POST)
    public PageBean list(HttpServletRequest request,PageBean page) {

        return page;
    }

    @RequestMapping("/industryCategory/edit")
    public String edit(ModelMap map,Integer id) {
        map.put("msg", "Hello Freemarker");
        return "industryCategory/edit";
    }

}
