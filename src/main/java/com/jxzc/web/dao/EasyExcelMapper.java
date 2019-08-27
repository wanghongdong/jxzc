package com.jxzc.web.dao;

import com.jxzc.web.entity.EasyExcel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EasyExcelMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(EasyExcel record);

    int insertSelective(EasyExcel record);

    EasyExcel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EasyExcel record);

    int updateByPrimaryKey(EasyExcel record);

    List<EasyExcel> queryAll();

}