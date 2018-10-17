package com.jxzc.model.web;

import com.jxzc.model.bean.PageBean;
import com.jxzc.model.entity.Class;
import com.jxzc.model.entity.User;
import com.jxzc.model.service.ClassService;
import com.jxzc.model.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClassController {

    @Autowired
    ClassService classService;


    @RequestMapping(value = "/class/list",method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap map, Integer level){
        map.addAttribute("level",level);
        return "class/list";
    }

    @ResponseBody
    @RequestMapping(value = "/class/list", method = RequestMethod.POST)
    public PageBean<Class> list(HttpServletRequest request, PageBean page, Class c){
        User user = SystemUtils.getCurrentUser(request);
        c.setCreateId(user.getId());
        page = classService.queryList(page,c);
        return page;
    }

}
