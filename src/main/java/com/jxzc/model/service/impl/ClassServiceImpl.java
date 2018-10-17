package com.jxzc.model.service.impl;

import com.jxzc.model.bean.PageBean;
import com.jxzc.model.dao.ClassMapper;
import com.jxzc.model.entity.Class;
import com.jxzc.model.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public PageBean<Class> queryList(PageBean pageBean, Integer userId) {
        return null;
    }

    @Override
    public Class queryById(Integer id) {
        return classMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveEntity(Class c) {
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


}
