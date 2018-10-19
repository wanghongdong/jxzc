package com.jxzc.model.service.impl;

import com.jxzc.model.dao.FilePicMapper;
import com.jxzc.model.entity.FilePic;
import com.jxzc.model.service.FilePicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilePicServiceImpl implements FilePicService {

    @Autowired
    FilePicMapper filePicMapper;

    @Override
    public int insert(FilePic pic) {
        return filePicMapper.insertSelective(pic);
    }
}
