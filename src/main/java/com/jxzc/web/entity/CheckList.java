package com.jxzc.web.entity;

import java.io.Serializable;

public class CheckList implements Serializable, Comparable<CheckList>{

    private Integer id;

    private String checkname;

    private Integer level;

    private Integer pid;

    private String b1;

    private String b2;

    private String b3;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCheckname() {
        return checkname;
    }

    public void setCheckname(String checkname) {
        this.checkname = checkname == null ? null : checkname.trim();
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

    public String getB1() {
        return b1;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }

    public String getB2() {
        return b2;
    }

    public void setB2(String b2) {
        this.b2 = b2;
    }

    public String getB3() {
        return b3;
    }

    public void setB3(String b3) {
        this.b3 = b3;
    }

    @Override
    public String toString() {
        return "CheckList{" +
                "id=" + id +
                ", checkname='" + checkname + '\'' +
                ", level=" + level +
                ", pid=" + pid +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {return false ;}
        else{
            if (obj instanceof CheckList) {
                CheckList c = (CheckList) obj;
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
    public int compareTo(CheckList o) {
        if(this.id > o.id){
            return 1;
        }else if(this.id < o.id){
            return -1;
        }else{
            return 0;
        }
    }
}