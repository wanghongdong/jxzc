package com.jxzc.model.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jxzc.model.bean.PageBean;
import com.jxzc.model.dao.ClassMapper;
import com.jxzc.model.entity.Class;
import com.jxzc.model.entity.IndustryCategory;
import com.jxzc.model.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassMapper classMapper;

    @Override
    public List<Class> queryByPid(Integer userId,Integer pid) {
        Class c = new Class();
        c.setCreateId(userId);
        if (pid==null || pid==1) c.setLevel(1);
        if (pid==2) c.setLevel(2);
        List<Class> classes = this.queryList(c);
        return classes;
    }

    @Override
    public List<Class> queryList(Class c) {
        return classMapper.queryList(c);
    }

    @Override
    public PageBean<Class> queryList(PageBean pageBean, Class c) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getLimit());
        List<Class> list = new ArrayList<>();
        if (c.getLevel()!=null && c.getLevel()==2){
            list = classMapper.queryTwoList(c);
        }else{
            list = classMapper.queryList(c);
        }
        PageInfo<Class> page = new PageInfo();
        pageBean.setCount(page.getSize());
        pageBean.setData(list);
        return pageBean;
    }

    @Override
    public Class queryById(Integer id) {
        return classMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveEntity(Class c) {
        c.setCreateTime(new Date());
        return classMapper.insert(c);
    }

    @Override
    public int updateEntity(Class c) {
        return classMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public int delEntity(Integer id) {
        return classMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Class> verify(Class entity) {
        return null;
    }

    @Override
    public List<Class> queryTwoList(Integer pid, Integer userId) {
        Class c = new Class();
        c.setPid(pid);
        c.setCreateId(userId);
        List<Class> list = classMapper.queryTwoList(c);
        return list;
    }


}
