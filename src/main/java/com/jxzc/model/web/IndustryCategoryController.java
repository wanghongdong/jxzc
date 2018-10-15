package com.jxzc.model.web;

import com.jxzc.model.bean.AjaxMsg;
import com.jxzc.model.bean.PageBean;
import com.jxzc.model.dao.IndustryCategoryMapper;
import com.jxzc.model.entity.IndustryCategory;
import com.jxzc.model.entity.User;
import com.jxzc.model.service.IndustryCategoryService;
import com.jxzc.model.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    IndustryCategoryService industryCategoryService;


    @RequestMapping(value = "/industryCategory/list",method = RequestMethod.GET)
    public String list(ModelMap map) {
        map.put("msg", "Hello Freemarker");
        return "industryCategory/list";
    }

    @ResponseBody
    @RequestMapping(value = "/industryCategory/list",method = RequestMethod.POST)
    public PageBean<IndustryCategory> list(HttpServletRequest request,PageBean page) {
        User user = SystemUtils.getCurrentUser(request);
        page = industryCategoryService.queryList(page, user.getId());
        return page;
    }

    @RequestMapping("/industryCategory/edit")
    public String edit(ModelMap map,Integer id) {
        IndustryCategory entity = new IndustryCategory();
        if (id!=null){
            entity = industryCategoryService.queryById(id);
        }
        map.addAttribute("entity",entity);
        return "industryCategory/edit";
    }

    @ResponseBody
    @RequestMapping("/industryCategory/save")
    public AjaxMsg save(IndustryCategory entity,HttpServletRequest request) {
        int i = 0;
        if (entity.getId()==null){
            User user = SystemUtils.getCurrentUser(request);
            entity.setCreateId(user.getId());
            i = industryCategoryService.saveEntity(entity);
        }
        else
            i = industryCategoryService.updateEntity(entity);
        if (i>0)
            return AjaxMsg.success("保存成功！");
        else
            return AjaxMsg.error("保存失败！");
    }


    @ResponseBody
    @RequestMapping("/industryCategory/verify")
    public AjaxMsg verify(IndustryCategory entity,HttpServletRequest request) {
        List<IndustryCategory> list = industryCategoryService.verify(entity);
        if (list.size()>0)
            return AjaxMsg.error("已存在此行业名称！");
        else
            return AjaxMsg.success("验证通过！");
    }

    @ResponseBody
    @RequestMapping("/industryCategory/del")
    public AjaxMsg verify(Integer id,HttpServletRequest request) {
        if(id==null){
            return AjaxMsg.error("请选择要删除的数据！");
        }
        int i = 0;
        i = industryCategoryService.delEntity(id);
        if (i>0)
            return AjaxMsg.success("删除成功！");
        else
            return AjaxMsg.error("删除失败！");
    }


}
