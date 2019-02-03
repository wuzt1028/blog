package com.wuzt.myblog.service.impl;

import com.wuzt.myblog.dao.TagsDao;
import com.wuzt.myblog.model.Tags;
import com.wuzt.myblog.service.TagsService;
import com.wuzt.myblog.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
    @Override
    public int insert(Tags record) {
        return tagsDao.insert(record);
    }
    @Override
    public List<Tags> selectByCondition(Tags record) {
        return tagsDao.selectByCondition(record);
    }

    @Override
    public Integer selectCountByName(Map<String, Object> param) {
        Tags tags = tagsDao.selectCountByName(param);
        if (ObjectUtils.isNull(tags)){
            return 0;
        }else {
            return 1;
        }

    }

}
