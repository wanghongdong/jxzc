package com.jxzc.web.web;

import com.jxzc.web.bean.PageBean;
import com.jxzc.web.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/angJs")
public class AngJsController {

    private static Logger logger = LoggerFactory.getLogger(AngJsController.class);

    @Value("${excel.path}")
    public String path;
    @Value("${fileName}")
    private String fileName;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listGet(ModelMap map, HttpServletRequest request){
        map.addAttribute("fileName", fileName);
        return "angJs/list";
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public PageBean listPost(PageBean pageBean){
        pageBean.setCount(100);
        pageBean = new PageBean(pageBean.getCount(), getUserList(pageBean), pageBean.getPage(), pageBean.getLimit());
        return pageBean;
    }

    private List<User> getUserList(PageBean pageBean) {
        List<User> list = new ArrayList<>();
        for (int i = 0; i <= pageBean.getCount(); i++) {
            int id = i + 1;
            String loginName = "user_" + id;
            String password = "******";
            String userName = "用户_" + id;
            User user = new User(id, loginName, password, userName);
            list.add(user);
        }
        Integer page = (pageBean.getPage() - 1) * pageBean.getLimit();
        List<User> collect = list.parallelStream().skip(page).limit(pageBean.getLimit()).collect(Collectors.toList());
        return collect;
    }

    @RequestMapping(value = "/mul")
    public int mulParam(int param) {
        return 9/param;
    }

}
