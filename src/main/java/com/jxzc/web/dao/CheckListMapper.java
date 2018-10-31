package com.jxzc.web.dao;

import com.jxzc.web.entity.CheckList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckListMapper {

    int insert(CheckList record);

    int insertSelective(CheckList record);

    List<CheckList> queryAll();
}