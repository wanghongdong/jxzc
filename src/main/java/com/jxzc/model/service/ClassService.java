package com.jxzc.model.service;

import com.jxzc.model.bean.PageBean;
import com.jxzc.model.entity.Class;
import com.jxzc.model.entity.IndustryCategory;

import java.util.List;

public interface ClassService {

    List<Class> queryByPid(Integer userId,Integer pid);

    List<Class> queryList(Class c);

    PageBean<Class> queryList(PageBean pageBean, Class c);

    Class queryById(Integer id);

    int saveEntity(Class c);

    int updateEntity(Class c);

    int delEntity(Integer id);

    List<Class> verify(Class entity);

}
