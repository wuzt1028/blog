package com.wuzt.myblog.service;

import com.wuzt.myblog.model.Tags;

import java.util.List;

/**
 * @Auther: wuzt
 * @Date: 2018/7/23 10:18
 * @Description:
 */
public interface TagsService {

    public int insert(Tags record);

    public List<Tags> selectByCondition(Tags record);

}
