package com.wuzt.myblog.dao;

import com.wuzt.myblog.model.BlogType;

import java.util.List;

public interface BlogTypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogType record);

    int insertSelective(BlogType record);

    BlogType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogType record);

    int updateByPrimaryKey(BlogType record);

    List<BlogType> selectByCondition(BlogType record);
}