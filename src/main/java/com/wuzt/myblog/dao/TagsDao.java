package com.wuzt.myblog.dao;

import com.wuzt.myblog.model.Tags;

import java.util.List;

public interface TagsDao {
    public int deleteByPrimaryKey(Integer id);

    public int insert(Tags record);

    public int insertSelective(Tags record);

    public Tags selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(Tags record);

    public int updateByPrimaryKey(Tags record);

    public List<Tags> selectByCondition(Tags record);
}