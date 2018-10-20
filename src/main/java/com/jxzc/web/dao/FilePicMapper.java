package com.jxzc.web.dao;

import com.jxzc.web.entity.FilePic;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilePicMapper {

    int insert(FilePic record);

    int insertSelective(FilePic record);

    int updateWid(FilePic record);

    FilePic selectByPrimaryKey(Integer id);

    List<FilePic> selectByWid(Integer wId);
}