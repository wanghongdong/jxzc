package com.jxzc.web.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class WorkReport implements Serializable {

    private Integer id;

    private Integer industrycategory;

    private Integer oneclass;

    private Integer twoclass;

    private String title;
    /**
     * 相关事件
     */
    private String relatedEvents;
    /**
     * 个人点评
     */
    private String personalComments;

    private Integer createId;

    private String createName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private Date workTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndustrycategory() {
        return industrycategory;
    }

    public void setIndustrycategory(Integer industrycategory) {
        this.industrycategory = industrycategory;
    }

    public Integer getOneclass() {
        return oneclass;
    }

    public void setOneclass(Integer oneclass) {
        this.oneclass = oneclass;
    }

    public Integer getTwoclass() {
        return twoclass;
    }

    public void setTwoclass(Integer twoclass) {
        this.twoclass = twoclass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getRelatedEvents() {
        return relatedEvents;
    }

    public void setRelatedEvents(String relatedEvents) {
        this.relatedEvents = relatedEvents == null ? null : relatedEvents.trim();
    }

    public String getPersonalComments() {
        return personalComments;
    }

    public void setPersonalComments(String personalComments) {
        this.personalComments = personalComments == null ? null : personalComments.trim();
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
}