package com.jxzc.web.entity;

import java.io.Serializable;
import java.util.Date;

public class Class implements Serializable, Comparable<Class>{

    private Integer id;

    private String classname;

    private Integer level;

    private Integer pid;

    private String pname;

    private String pisOpen;

    private Integer sort;

    private Integer isOpen = 0;

    private Integer createId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
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

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPisOpen() {
        return pisOpen;
    }

    public void setPisOpen(String pisOpen) {
        this.pisOpen = pisOpen;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {return false ;}
        else{
            if (obj instanceof Class) {
                Class c = (Class) obj;
                return (id.equals(c.id));
            }else{
                return false;
            }
        }
    }

    @Override
    public int hashCode() {
        return id.hashCode();

    }

    @Override
    public int compareTo(Class o) {
        if(this.sort > o.sort){
            return 1;
        }else if(this.sort < o.sort){
            return -1;
        }else{
            return 0;
        }
    }
}