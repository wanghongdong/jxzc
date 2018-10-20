package com.jxzc.web.dao;

import com.jxzc.web.entity.WorkReport;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkReportMapper {

    WorkReport selectByPrimaryKey(Integer id);

    int insert(WorkReport record);

    int insertSelective(WorkReport record);
}