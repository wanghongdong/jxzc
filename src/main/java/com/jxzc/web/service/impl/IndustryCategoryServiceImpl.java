package com.jxzc.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jxzc.web.bean.PageBean;
import com.jxzc.web.dao.IndustryCategoryMapper;
import com.jxzc.web.entity.IndustryCategory;
import com.jxzc.web.service.IndustryCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "industryCategoryService")
public class IndustryCategoryServiceImpl implements IndustryCategoryService {

    @Autowired
    IndustryCategoryMapper industryCategoryMapper;

    @Override
    public IndustryCategory queryById(Integer id) {
        return industryCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageBean<IndustryCategory> queryList(PageBean pageBean,Integer userId) {
        Page<IndustryCategory> page = PageHelper.startPage(pageBean.getPage(), pageBean.getLimit());
        List<IndustryCategory> list = industryCategoryMapper.queryListByPage(userId);
        pageBean.setCount((int) page.getTotal());
        pageBean.setData(list);
        return pageBean;
    }

    @Override
    public List<IndustryCategory> queryList(Integer userId) {
        List<IndustryCategory> list = industryCategoryMapper.queryListByPage(userId);
        return list;
    }

    @Override
    public int saveEntity(IndustryCategory category) {
        category.setCreateTime(new Date());
        return industryCategoryMapper.insertSelective(category);
    }

    @Override
    public int updateEntity(IndustryCategory category) {
        return industryCategoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public int delEntity(Integer id) {
        return industryCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<IndustryCategory> verify(IndustryCategory entity) {
        List<IndustryCategory> list = industryCategoryMapper.verify(entity);
        return list;
    }
}
