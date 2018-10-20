package com.jxzc.web.service.impl;

import com.jxzc.web.dao.FilePicMapper;
import com.jxzc.web.entity.FilePic;
import com.jxzc.web.service.FilePicService;
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
