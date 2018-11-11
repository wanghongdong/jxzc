package com.jxzc.web.service.impl;

import com.jxzc.web.dao.FilePicMapper;
import com.jxzc.web.dao.WorkReportMapper;
import com.jxzc.web.entity.FilePic;
import com.jxzc.web.entity.WorkReport;
import com.jxzc.web.entity.WorkReportBean;
import com.jxzc.web.service.WorkReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: jxzc
 * @PackageName: com.jxzc.web.service.impl
 * @ClassName: WorkReportServiceImpl
 * @Auther: wanghongdong
 * @Date: 2018/10/20 14 55
 * @Description:
 * */
@Service
public class WorkReportServiceImpl implements WorkReportService {

    @Autowired
    WorkReportMapper workReportMapper;
    @Autowired
    FilePicMapper filePicMapper;

    @Override
    public WorkReport queryById(Integer id) {
        return workReportMapper.selectByPrimaryKey(id);
    }

    @Override
    public WorkReportBean queryBeanById(Integer id) {
        WorkReport workReport = workReportMapper.selectByPrimaryKey(id);
        List<FilePic> filePics = filePicMapper.selectByWid(id);
        WorkReportBean bean = new WorkReportBean(workReport, filePics);
        bean.setId(workReport.getId());
        return bean;
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public int saveEntity(WorkReportBean c) {
        WorkReport report = c.getReport();
        int insert = workReportMapper.insert(report);
        List<FilePic> pics = c.getPics();
        for (FilePic pic : pics) {
            pic.setwId(report.getId());
            filePicMapper.updateWid(pic);
        }
        report = workReportMapper.selectByPrimaryKey(report.getId());
        pics = filePicMapper.selectByWid(report.getId());
        c.setId(report.getId());
        c.setReport(report);
        c.setPics(pics);
        return insert;
    }

    @Override
    public int updateEntity(WorkReport c) {
        return 0;
    }

    @Override
    public int delEntity(Integer id) {
        return 0;
    }

    @Override
    public List<WorkReportBean> queryBeans(Date workTime) {
        WorkReport report = new WorkReport();
        report.setWorkTime(workTime);
        List<WorkReport> reports = workReportMapper.selectList(report);
        List<WorkReportBean> list = new ArrayList<>();
        if(reports!=null && reports.size()>0){
            for(WorkReport work : reports){
                List<FilePic> filePics = filePicMapper.selectByWid(work.getId());
                WorkReportBean bean = new WorkReportBean(work, filePics);
                bean.setId(work.getId());
                list.add(bean);
            }
        }
        return list;
    }
}
