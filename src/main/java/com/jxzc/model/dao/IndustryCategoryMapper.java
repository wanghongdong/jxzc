package com.jxzc.model.dao;

import com.jxzc.model.entity.IndustryCategory;

public interface IndustryCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IndustryCategory record);

    int insertSelective(IndustryCategory record);

    IndustryCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IndustryCategory record);

    int updateByPrimaryKey(IndustryCategory record);
}