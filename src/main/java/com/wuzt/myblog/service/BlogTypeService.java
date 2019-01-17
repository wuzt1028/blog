package com.wuzt.myblog.service;

import com.wuzt.myblog.model.BlogType;

import java.util.List;

/**
 * @Auther: wuzt
 * @Date: 2018/7/23 10:40
 * @Description:
 */
public interface BlogTypeService {

    public int insert(BlogType record);

    public List<BlogType> selectByCondition(BlogType record);

}
