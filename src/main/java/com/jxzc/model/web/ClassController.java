package com.jxzc.model.web;

import com.jxzc.model.bean.AjaxMsg;
import com.jxzc.model.bean.PageBean;
import com.jxzc.model.entity.Class;
import com.jxzc.model.entity.IndustryCategory;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/class/edit", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap map,Integer id, Integer level) {
        User user = SystemUtils.getCurrentUser(request);
        Class entity = new Class();
        if (id!=null){
            entity = classService.queryById(id);
        }
        if (level!=null && level==2){
            Class c = new Class();
            c.setLevel(1);
            c.setCreateId(user.getId());
            List<Class> classes = classService.queryList(c);
            map.addAttribute("classes",classes);
        }
        map.addAttribute("entity",entity);
        map.addAttribute("level",level);
        return "class/edit";
    }

    @ResponseBody
    @RequestMapping("/class/save")
    public AjaxMsg save(Class entity, HttpServletRequest request) {
        int i = 0;
        if (entity.getId()==null){
            User user = SystemUtils.getCurrentUser(request);
            entity.setCreateId(user.getId());
            i = classService.saveEntity(entity);
        }
        else
            i = classService.updateEntity(entity);
        if (i>0)
            return AjaxMsg.success("保存成功！");
        else
            return AjaxMsg.error("保存失败！");
    }


    @ResponseBody
    @RequestMapping("/class/verify")
    public AjaxMsg verify(Class entity,HttpServletRequest request) {
        List<Class> list = classService.verify(entity);
        if (list.size()>0)
            return AjaxMsg.error("已存在此行业名称！");
        else
            return AjaxMsg.success("验证通过！");
    }

    @ResponseBody
    @RequestMapping("/class/del")
    public AjaxMsg verify(Integer id,HttpServletRequest request) {
        if(id==null){
            return AjaxMsg.error("请选择要删除的数据！");
        }
        int i = 0;
        i = classService.delEntity(id);
        if (i>0)
            return AjaxMsg.success("删除成功！");
        else
            return AjaxMsg.error("删除失败！");
    }

    @ResponseBody
    @RequestMapping(value = "/class/edit", method = RequestMethod.POST)
    public AjaxMsg edit(Integer id,HttpServletRequest request) {
        if(id==null){
            return AjaxMsg.error("请选择要查询的数据！");
        }
        Class aClass = classService.queryById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("entity", aClass);
        return AjaxMsg.success("删除成功！",map);
    }

}
