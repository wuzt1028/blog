package com.wuzt.myblog.service;

import com.wuzt.myblog.model.BlogType;

import java.util.List;
import java.util.Map;

/**
 * @Auther: wuzt
 * @Date: 2018/7/23 10:40
 * @Description:
 */
public interface BlogTypeService {

    public int insert(BlogType record);

    public List<BlogType> selectByCondition(BlogType record);

    /* 根据tagName,userId查询tag数量 */
    public Integer selectCountByName(Map<String, Object> param);

}
