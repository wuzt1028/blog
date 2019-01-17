package com.wuzt.myblog.service.impl;

import com.wuzt.myblog.dao.BlogTypeDao;
import com.wuzt.myblog.dao.BlogsDao;
import com.wuzt.myblog.dao.TagsDao;
import com.wuzt.myblog.model.*;
import com.wuzt.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: wuzt
 * @Date: 2018/7/26 17:50
 * @Description:
 */
@Service("blogService")
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogsDao blogDao;
    @Autowired
    private TagsDao tagsDao;
    @Autowired
    private BlogTypeDao blogTypeDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return blogDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BlogsDto record) {
        return blogDao.insert(record);
    }

    @Override
    public int insertBlog(BlogsDto record) {
        Blogs blog = new Blogs();
        blog.setUserId(record.getUserId());
        blog.setTitle(record.getTitle());
        blog.setContent(record.getContent());
        blog.setSubContent(record.getSubContent());
        blog.setCover("cover");
        blog.setStatus(1);
        //类型
        if (record.getSelectType()==1){//以前的分类
            blog.setTypeId(record.getTypeId());
        }else{//新的分类
            BlogType blogType = new BlogType();
            blogType.setTypeName(record.getBlogType());
            blogType.setUserId(record.getUserId());
            blogType.setCreateName(new Date());
            blogTypeDao.insert(blogType);
            blog.setTypeId(blogType.getId());
        }
        blog.setCreateTime(new Date());
        blogDao.insertSelective(blog);
        //标签
        if(record.getTags()!=null && record.getTags().size()>0){
            for(String tagStr : record.getTags()){
                Tags tag = new Tags();
                tag.setUserId(record.getUserId());
                tag.setBlogId(blog.getId());
                tag.setTagName(tagStr);
                tag.setCreateTime(new Date());
                tagsDao.insert(tag);
            }
        }

        return 1;
    }

    @Override
    public Blogs selectByPrimaryKey(Integer id) {
        return blogDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Blogs record) {
        return blogDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Blogs record) {
        return blogDao.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Blogs record) {
        return blogDao.updateByPrimaryKey(record);
    }

    @Override
    public List<BlogEntity> selectBlogListPage(Map<String,Object> params){
        return blogDao.selectBlogListPage(params);
    }

    @Override
    public Integer selectBlogCount(){
        return blogDao.selectBlogCount();
    }

    @Override
    public BlogEntity selectBlogInfoById(Integer id){
        return blogDao.selectBlogInfoById(id);
    }

    public int updateClickNumById(Blogs record){
        return blogDao.updateClickNumById(record);
    }
}
