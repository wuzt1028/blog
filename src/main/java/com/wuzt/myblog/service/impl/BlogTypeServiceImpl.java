package com.wuzt.myblog.service.impl;



import com.wuzt.myblog.dao.BlogTypeDao;
import com.wuzt.myblog.model.BlogType;
import com.wuzt.myblog.redis.RedisUtil;
import com.wuzt.myblog.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: wuzt
 * @Date: 2018/7/23 10:55
 * @Description:
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {


    @Autowired
    private BlogTypeDao blogTypeDao;
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public int insert(BlogType record) {
        return blogTypeDao.insert(record);
    }
    @Override
    public List<BlogType> selectByCondition(BlogType record) {
        List<BlogType> blogTypeList = new ArrayList<BlogType>();
        Object blogIndex = redisUtil.get("blogIndex");
        if (blogIndex != null) {
            blogTypeList = (List<BlogType>) blogIndex;
        } else {
            blogTypeList = blogTypeDao.selectByCondition(record);
        }
        return blogTypeList;
    }

    @Override
    public Integer selectCountByName(Map<String, Object> param) {
        return blogTypeDao.selectCountByName(param);
    }
}
