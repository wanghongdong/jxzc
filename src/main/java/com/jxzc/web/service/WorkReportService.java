package com.jxzc.web.service;

import com.jxzc.web.entity.WorkReport;
import com.jxzc.web.entity.WorkReportBean;

import java.util.Date;
import java.util.List;

public interface WorkReportService {

    WorkReport queryById(Integer id);

    WorkReportBean queryBeanById(Integer id);

    int saveEntity(WorkReportBean c);

    int updateEntity(WorkReport c);

    int delEntity(Integer id);

    List<WorkReportBean> queryBeans(Date date);

}
