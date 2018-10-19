package com.jxzc.model.dao;

import com.jxzc.model.entity.FilePic;

public interface FilePicMapper {
    int insert(FilePic record);

    int insertSelective(FilePic record);
}