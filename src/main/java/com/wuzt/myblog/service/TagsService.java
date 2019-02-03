package com.wuzt.myblog.service;

import com.wuzt.myblog.model.Tags;

import java.util.List;
import java.util.Map;

/**
 * @Auther: wuzt
 * @Date: 2018/7/23 10:18
 * @Description:
 */
public interface TagsService {

    public int insert(Tags record);

    public List<Tags> selectByCondition(Tags record);

    /* 根据tagName,userId查询tag数量 */
    public Integer selectCountByName(Map<String, Object> param);

}
