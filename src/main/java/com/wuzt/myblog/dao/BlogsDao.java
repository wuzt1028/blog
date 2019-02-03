package com.wuzt.myblog.dao;

import com.wuzt.myblog.model.BlogEntity;
import com.wuzt.myblog.model.Blogs;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Blogs record);

    int insertSelective(Blogs record);

    Blogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blogs record);

    int updateByPrimaryKeyWithBLOBs(Blogs record);

    int updateByPrimaryKey(Blogs record);

    List<BlogEntity> selectBlogListPage(Map<String, Object> params);

    Integer selectBlogCount(Map<String, Object> params);

    BlogEntity selectBlogInfoById(Integer id);

    int updateClickNumById(Blogs record);
    /* 根据id更新状态 */
    int updateBlogStatusById(Map<String, Object> param);
    /* 取前六个点击量最高的博文列表 */
    List<Blogs> selectClickDESC();
}