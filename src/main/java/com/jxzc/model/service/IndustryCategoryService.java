package com.jxzc.model.service;

import com.jxzc.model.bean.PageBean;
import com.jxzc.model.entity.IndustryCategory;
import org.springframework.stereotype.Service;

@Service(value = "industryCategoryService")
public interface IndustryCategoryService {

    /**
     * 通过id查询
     * @return
     */
    IndustryCategory queryById(Integer id);

    PageBean<IndustryCategory> queryList(PageBean page);

    int saveEntity(IndustryCategory category);

    int updateEntity(IndustryCategory category);

    int delEntity(Integer id);

}
