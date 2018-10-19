package com.jxzc.model.web;

import com.jxzc.model.bean.AjaxMsg;
import com.jxzc.model.dao.UserMapper;
import com.jxzc.model.entity.Class;
import com.jxzc.model.entity.IndustryCategory;
import com.jxzc.model.entity.User;
import com.jxzc.model.entity.WorkReport;
import com.jxzc.model.service.ClassService;
import com.jxzc.model.service.IndustryCategoryService;
import com.jxzc.model.utils.JSConstant;
import com.jxzc.model.utils.SystemUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: jxzc
 * @PackageName: com.jxzc.model.web
 * @Auther: wanghongdong
 * @Date: 2018/9/26 22 04
 * @Description: //**
 * @ProjectName: jxzc
 * @PackageName: com.jxzc.model.web
 * @ClassName: TestControll
 * @Auther: wanghongdong
 * @Date: 2018/9/26 22 04
 * @Description:
 */
@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    IndustryCategoryService industryCategoryService;
    @Autowired
    ClassService classService;

    @RequestMapping("/index")
    public String index(ModelMap map, HttpServletRequest request) {
        User user = SystemUtils.getCurrentUser(request);
        List<IndustryCategory> categoryList = industryCategoryService.queryList(user.getId());
        Class c = new Class();
        c.setCreateId(user.getId());
        c.setLevel(1);
        List<Class> classes = classService.queryList(c);
        map.put("categoryList", categoryList);
        map.put("oneClasses",classes);
        return "index";
    }

    @ResponseBody
    @RequestMapping("/saveReport")
    public AjaxMsg saveReport(ModelMap map, HttpServletRequest request, WorkReport report) {
        User user = SystemUtils.getCurrentUser(request);
        report.setCreateId(user.getId());
        report.setCreateTime(new Date());
        return AjaxMsg.success();
    }


    @ResponseBody
    @RequestMapping("/queryTwoClasses")
    public AjaxMsg queryTwoClasses(HttpServletRequest request, Integer pid){
        User user = SystemUtils.getCurrentUser(request);
        List<Class> classes = classService.queryTwoList(pid, user.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("classes",classes);
        return AjaxMsg.success("查询成功！",map);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Map<String,Object> map) {
        map.put("msg", "Hello Freemarker");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object doLogin(HttpServletRequest request) {
        String loginname = request.getParameter("loginname");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(loginname) || StringUtils.isEmpty(password)){
            return AjaxMsg.error("用户名和密码不能为空");
        }
        User user = userMapper.selectUserByLoginName(loginname);
        if (user !=null && user.getPassword().equals(password)){
            request.getSession().setAttribute(JSConstant.SESSION_CURRENT_USER,user);
            request.getSession().setMaxInactiveInterval(2*60*60);
            return AjaxMsg.success("登录成功");
        }else {
            return AjaxMsg.error("用户名或密码不正确");
        }
    }

}
