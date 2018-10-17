package com.jxzc.model.service;

import com.jxzc.model.bean.PageBean;
import com.jxzc.model.entity.IndustryCategory;

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
