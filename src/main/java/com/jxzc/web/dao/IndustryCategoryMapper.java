package com.jxzc.web.dao;

import com.jxzc.web.entity.IndustryCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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