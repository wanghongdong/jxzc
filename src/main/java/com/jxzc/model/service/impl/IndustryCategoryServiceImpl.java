package com.jxzc.model.service.impl;

import com.jxzc.model.bean.PageBean;
import com.jxzc.model.dao.IndustryCategoryMapper;
import com.jxzc.model.entity.IndustryCategory;
import com.jxzc.model.service.IndustryCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IndustryCategoryServiceImpl implements IndustryCategoryService {

    @Autowired
    IndustryCategoryMapper industryCategoryMapper;

    @Override
    public IndustryCategory queryById(Integer id) {
        return industryCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageBean<IndustryCategory> queryList(PageBean page) {

        List<IndustryCategory> list = industryCategoryMapper.queryListByPage();

        return ;
    }

    @Override
    public int saveEntity(IndustryCategory category) {
        return 0;
    }

    @Override
    public int updateEntity(IndustryCategory category) {
        return 0;
    }

    @Override
    public int delEntity(Integer id) {
        return 0;
    }
}
