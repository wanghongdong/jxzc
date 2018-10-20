package com.jxzc.web.service;

import com.jxzc.web.bean.PageBean;
import com.jxzc.web.entity.IndustryCategory;

import java.util.List;


public interface IndustryCategoryService {

    /**
     * 通过id查询
     * @return
     */
    IndustryCategory queryById(Integer id);

    PageBean<IndustryCategory> queryList(PageBean pageBean,Integer userId);

    List<IndustryCategory> queryList(Integer userId);

    int saveEntity(IndustryCategory category);

    int updateEntity(IndustryCategory category);

    int delEntity(Integer id);

    List<IndustryCategory> verify(IndustryCategory entity);
}
