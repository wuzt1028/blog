package com.wuzt.myblog.dao;

import com.wuzt.myblog.model.BlogTag;
import com.wuzt.myblog.model.Tags;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TagsDao {
    public int deleteByPrimaryKey(Integer id);

    public int insert(Tags record);

    public int insertSelective(Tags record);

    public Tags selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(Tags record);

    public int updateByPrimaryKey(Tags record);

    public List<Tags> selectByCondition(Tags record);

    /* 根据tagName,userId查询tag */
    public Tags selectCountByName(Map<String, Object> param);
    /*
     * Description:blog_tag中间表添加数据
     * @auther: wuzt
     * @date: 2019/2/1 10:33
     */
    public void insertBlogTag(BlogTag blogTag);
}