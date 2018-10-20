package com.jxzc.web.entity;

import java.io.Serializable;
import java.util.List;

public class WorkReportBean implements Serializable {

    private Integer id;

    private WorkReport report;

    private List<FilePic> pics;

    public WorkReportBean(WorkReport report, List<FilePic> pics) {
        this.report = report;
        this.pics = pics;
    }

    public WorkReportBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WorkReport getReport() {
        return report;
    }

    public void setReport(WorkReport report) {
        this.report = report;
    }

    public List<FilePic> getPics() {
        return pics;
    }

    public void setPics(List<FilePic> pics) {
        this.pics = pics;
    }
}