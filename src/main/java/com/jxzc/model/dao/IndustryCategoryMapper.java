package com.jxzc.model.dao;

import com.jxzc.model.entity.IndustryCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IndustryCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IndustryCategory record);

    int insertSelective(IndustryCategory record);

    IndustryCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IndustryCategory record);

    int updateByPrimaryKey(IndustryCategory record);

    List<IndustryCategory> queryListByPage(@Param(value = "userId") Integer userId);

    List<IndustryCategory> verify(IndustryCategory entity);
}