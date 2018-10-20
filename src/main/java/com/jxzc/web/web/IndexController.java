package com.jxzc.web.web;

import com.alibaba.fastjson.JSONObject;
import com.jxzc.web.bean.AjaxMsg;
import com.jxzc.web.dao.UserMapper;
import com.jxzc.web.entity.Class;
import com.jxzc.web.entity.*;
import com.jxzc.web.service.ClassService;
import com.jxzc.web.service.IndustryCategoryService;
import com.jxzc.web.service.WorkReportService;
import com.jxzc.web.utils.ExcelUtils;
import com.jxzc.web.utils.JSConstant;
import com.jxzc.web.utils.SystemUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @ProjectName: jxzc
 * @PackageName: com.jxzc.web.web
 * @Auther: wanghongdong
 * @Date: 2018/9/26 22 04
 * @Description: //**
 * @ProjectName: jxzc
 * @PackageName: com.jxzc.web.web
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
    @Autowired
    WorkReportService workReportService;
    @Autowired
    ExcelUtils excelUtils;
    @Autowired
    SystemUtils systemUtils;

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
    @RequestMapping(value = "/saveReport",method = RequestMethod.POST)
    public AjaxMsg saveReport(HttpServletRequest request, String report) {
        try {
            //获取去当前用户
            User user = SystemUtils.getCurrentUser(request);
            //初始化数据
            WorkReportBean bean = initBean(report);
            //设置信息
            bean.getReport().setCreateId(user.getId());
            bean.getReport().setCreateName(user.getLoginName());
            bean.getReport().setCreateTime(new Date());
            //更新
            int i = workReportService.saveEntity(bean);
            if (i>0){
                Map<String, Object> map = new HashMap<>();
                map.put("excelName",excelUtils.initExcel(bean));
                return AjaxMsg.success("保存成功！",map);
            }else{
                return AjaxMsg.error("保存失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxMsg.error("保存失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/excelDown")
    public AjaxMsg excelDemo(Integer id){
        WorkReportBean bean = workReportService.queryBeanById(id);
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("excelName",excelUtils.initExcel(bean));
            return AjaxMsg.success("保存成功！",map);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxMsg.error("保存失败！");
        }
    }

    @RequestMapping(value = "/reportExcelDown")
    public void download(String excelName, HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            User currentUser = SystemUtils.getCurrentUser(request);
            try {
                User user = systemUtils.getUser();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("no ! no ! no !");
            }
            String excelPath = excelUtils.path + File.separatorChar + currentUser.getLoginName() + File.separatorChar + excelName;
            inputStream = new FileInputStream(new File(excelPath));
            outputStream = response.getOutputStream();
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + excelName);
            response.setCharacterEncoding("utf-8");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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

    public List<FilePic> initPics(String file1, String file2, String file3, String file4){
        List<FilePic> pics = new ArrayList<>();
        if (!StringUtils.isEmpty(file1)){
            pics.add(new FilePic(Integer.valueOf(file1)));
        }
        if (!StringUtils.isEmpty(file2)){
            pics.add(new FilePic(Integer.valueOf(file2)));
        }
        if (!StringUtils.isEmpty(file3)){
            pics.add(new FilePic(Integer.valueOf(file3)));
        }
        if (!StringUtils.isEmpty(file4)){
            pics.add(new FilePic(Integer.valueOf(file4)));
        }
        return pics;
    }

    private WorkReportBean initBean(String reportStr) {
        JSONObject jsonObject = JSONObject.parseObject(reportStr);
        WorkReport report = jsonObject.toJavaObject(WorkReport.class);
        List<FilePic> pics = initPics(jsonObject.getString("file1"), jsonObject.getString("file2"), jsonObject.getString("file3"), jsonObject.getString("file4"));
        return new WorkReportBean(report, pics);
    }

}
