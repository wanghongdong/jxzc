package com.jxzc.web.service;

import com.jxzc.web.bean.PageBean;
import com.jxzc.web.entity.CheckList;
import com.jxzc.web.entity.Class;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public interface CheckListService {

    List<CheckList> queryAll();

    Map<CheckList,List<CheckList>> queryAllMap();

    PageBean<CheckList> queryList(PageBean pageBean);
}
