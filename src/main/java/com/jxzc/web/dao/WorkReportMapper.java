package com.jxzc.web.dao;

import com.jxzc.web.entity.WorkReport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkReportMapper {

    WorkReport selectByPrimaryKey(Integer id);

    int insert(WorkReport record);

    int insertSelective(WorkReport record);

    List<WorkReport> selectList(WorkReport report);
}