package com.hewen.service.impl;

import com.hewen.mappers.DataMapper;
import com.hewen.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
public class DataServiceImpl implements DataService {

    @Resource
    private DataMapper dataMapper;

    @Transactional
    public void deleteAllData() {
        // 删除顺序很重要，需要先删除外键约束的表
        dataMapper.deleteAllCommentImages();
        dataMapper.deleteAllQuestionImages();
        dataMapper.deleteAllComments();
        dataMapper.deleteAllQuestions();
        dataMapper.deleteAllUsers();
    }
}
