package com.wuzt.myblog.service.impl;

import com.wuzt.myblog.dao.TagsDao;
import com.wuzt.myblog.model.Tags;
import com.wuzt.myblog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: wuzt
 * @Date: 2018/7/23 10:32
 * @Description:
 */
@Service("tagsService")
@Transactional
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsDao tagsDao;

    public int insert(Tags record){
        return tagsDao.insert(record);
    }

    public List<Tags> selectByCondition(Tags record){
        return tagsDao.selectByCondition(record);
    }

}
