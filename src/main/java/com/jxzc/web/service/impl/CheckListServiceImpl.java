package com.jxzc.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jxzc.web.bean.PageBean;
import com.jxzc.web.dao.CheckListMapper;
import com.jxzc.web.entity.CheckList;
import com.jxzc.web.entity.Class;
import com.jxzc.web.service.CheckListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class CheckListServiceImpl implements CheckListService {

    @Autowired
    CheckListMapper checkListMapper;

    @Override
    public List<CheckList> queryAll() {
        return checkListMapper.queryAll();
    }

    @Override
    public Map<CheckList, List<CheckList>> queryAllMap() {
        Map<CheckList, List<CheckList>> map = new TreeMap<>();
        List<CheckList> checkLists = queryAll();
        for (CheckList  list : checkLists) {
            if (list.getLevel()==1){
                List<CheckList> childrens = new ArrayList<>();
                for (CheckList list1 : checkLists) {
                    if (list1.getPid()!=null && list1.getPid()==list.getId()){
                        childrens.add(list1);
                    }
                }
                map.put(list, childrens);
            }
        }
        return map;
    }

    @Override
    public PageBean<CheckList> queryList(PageBean pageBean) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getLimit());
        List<CheckList> list = checkListMapper.queryAll();
        PageInfo<Class> page = new PageInfo();
        pageBean.setCount(page.getSize());
        pageBean.setData(list);
        return pageBean;
    }
}
