package com.wuzt.myblog.dao;

import com.wuzt.myblog.model.BlogType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogTypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogType record);

    int insertSelective(BlogType record);

    BlogType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogType record);

    int updateByPrimaryKey(BlogType record);

    List<BlogType> selectByCondition(BlogType record);

    /* 根据tagName,userId查询tag数量 */
    Integer selectCountByName(Map<String, Object> param);
}