package com.jxzc.web.web;

import com.jxzc.web.bean.AjaxMsg;
import com.jxzc.web.bean.PageBean;
import com.jxzc.web.entity.Class;
import com.jxzc.web.entity.User;
import com.jxzc.web.service.ClassService;
import com.jxzc.web.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
        else{
            i = classService.updateEntity(entity);
        }
        if (i>0){
            return AjaxMsg.success("保存成功！");
        }
        else{
            return AjaxMsg.error("保存失败！");
        }
    }


    @ResponseBody
    @RequestMapping("/class/verify")
    public AjaxMsg verify(Class entity,HttpServletRequest request) {
        List<Class> list = classService.verify(entity);
        if (list.size()>0){return AjaxMsg.error("已存在此行业名称！");}
        else{return AjaxMsg.success("验证通过！");}
    }

    @ResponseBody
    @RequestMapping("/class/del")
    public AjaxMsg verify(Integer id,HttpServletRequest request) {
        if(id==null){
            return AjaxMsg.error("请选择要删除的数据！");
        }
        int i = 0;
        i = classService.delEntity(id);
        if (i>0) {
            return AjaxMsg.success("删除成功！");
        } else{
            return AjaxMsg.error("删除失败！");
        }
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

    @ResponseBody
    @RequestMapping(value = "/class/batchInsert", method = RequestMethod.POST)
    public AjaxMsg batchInsert(HttpServletRequest request) {
        long l = System.currentTimeMillis();
        List<Class> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Class c = new Class();
            c.setClassname("批量"+i);
            c.setLevel(i);
            if (i%2==1){
                c.setPid(i-1);
            }
            list.add(c);
        }
        int i = classService.batchInsert(list);
        long l1 = System.currentTimeMillis();
        return AjaxMsg.success(String.format("插入 %s 条数据，用时 %s s", list.size() , (l1-l)/1000));
    }
}
