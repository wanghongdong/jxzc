package com.jxzc.web.service;

import com.jxzc.web.bean.PageBean;
import com.jxzc.web.entity.Class;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public interface ClassService {

    List<Class> queryByPid(Integer userId,Integer pid);

    List<Class> queryList(Class c);

    PageBean<Class> queryList(PageBean pageBean, Class c);

    Class queryById(Integer id);

    int saveEntity(Class c);

    int updateEntity(Class c);

    int delEntity(Integer id);

    List<Class> verify(Class entity);

    List<Class> queryTwoList(Integer pid, Integer userId);

    Map<Class,TreeSet<Class>> queryMyClasses(Integer userId);
}
