package com.jxzc.model.dao;

import com.jxzc.model.entity.WorkReport;

public interface WorkReportMapper {
    int insert(WorkReport record);

    int insertSelective(WorkReport record);
}