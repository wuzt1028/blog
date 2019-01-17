package com.wuzt.myblog.dao;

import com.wuzt.myblog.model.BlogEntity;
import com.wuzt.myblog.model.Blogs;

import java.util.List;
import java.util.Map;

public interface BlogsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Blogs record);

    int insertSelective(Blogs record);

    Blogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blogs record);

    int updateByPrimaryKeyWithBLOBs(Blogs record);

    int updateByPrimaryKey(Blogs record);

    List<BlogEntity> selectBlogListPage(Map<String,Object> params);

    Integer selectBlogCount();

    BlogEntity selectBlogInfoById(Integer id);

    int updateClickNumById(Blogs record);
}