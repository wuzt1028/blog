package com.wuzt.myblog.service;

import com.wuzt.myblog.model.BlogEntity;
import com.wuzt.myblog.model.Blogs;
import com.wuzt.myblog.model.BlogsDto;

import java.util.List;
import java.util.Map;

/**
 * @Auther: wuzt
 * @Date: 2018/7/26 17:49
 * @Description:
 */
public interface BlogService {

    public int deleteByPrimaryKey(Integer id);

    public int insert(BlogsDto record);

    public int insertBlog(BlogsDto record);

    public Blogs selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(Blogs record);

    public int updateByPrimaryKeyWithBLOBs(Blogs record);

    public int updateByPrimaryKey(Blogs record);

    public List<BlogEntity> selectIndexPage(Map<String, Object> params);

    public List<BlogEntity> selectBlogListPage(Map<String, Object> params);

    public Integer selectBlogCount(Map<String, Object> params);

    /*
     * Description:根据id获取博客详情
     * @auther: wuzt
     * @date: 2018/9/11 16:06
     */
    public BlogEntity selectBlogInfoById(Integer id);

    /*
     * Description:根据id更新点击量
     * @auther: wuzt
     * @date: 2018/9/12 10:19
     */
    public int updateClickNumById(Blogs record);

    /* 根据id更新状态 */
    int updateBlogStatusById(Map<String, Object> param);

    /* 取前六个点击量最高的博文列表 */
    List<Blogs> selectClickDESC();
}
