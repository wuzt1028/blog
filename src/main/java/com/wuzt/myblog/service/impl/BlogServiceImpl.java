package com.wuzt.myblog.service.impl;

import com.wuzt.myblog.dao.BlogTypeDao;
import com.wuzt.myblog.dao.BlogsDao;
import com.wuzt.myblog.dao.TagsDao;
import com.wuzt.myblog.model.*;
import com.wuzt.myblog.redis.RedisUtil;
import com.wuzt.myblog.service.BlogService;
import com.wuzt.myblog.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Autowired
    private RedisUtil redisUtil;

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
        int fileName = (int)(1+Math.random()*58);
        blog.setCover("/static/images/covers/" + fileName + ".jpg");
        blog.setStatus(1);
        //类型
        if (record.getSelectType() == 1) {//以前的分类
            blog.setTypeId(record.getTypeId());
        } else {//新的分类
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
        if (record.getTags() != null && record.getTags().size() > 0) {
            for (String tagStr : record.getTags()) {
                Map<String, Object> param = new HashMap<>();
                param.put("tagName", tagStr);
                param.put("userId", record.getUserId());
                Tags tags = tagsDao.selectCountByName(param);
                Tags tag = new Tags();
                if (ObjectUtils.isNotNull(tags)){
                    tag.setId(tags.getId());
                }else {
                    tag.setUserId(record.getUserId());
                    tag.setBlogId(blog.getId());
                    tag.setTagName(tagStr);
                    tag.setCreateTime(new Date());
                    tagsDao.insert(tag);
                }
                BlogTag blogTag = new BlogTag();
                blogTag.setBlogId(blog.getId());
                blogTag.setTagId(tag.getId());
                tagsDao.insertBlogTag(blogTag);
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
    public List<BlogEntity> selectIndexPage(Map<String, Object> params) {
        List<BlogEntity> blogList = new ArrayList<BlogEntity>();
        Integer index = (Integer) params.get("index");
        if(index == 0){
            Object indexPage_0 = redisUtil.get("indexPage_0");
            if(ObjectUtils.isNotNull(indexPage_0)){
                blogList = (List<BlogEntity>) indexPage_0;
            }else {
                blogList = blogDao.selectBlogListPage(params);
                for (BlogEntity blogEntity : blogList){
                    String tagStr = blogEntity.getTagStr();
                    String[] split = tagStr.split(",");
                    List<String> tagList = Arrays.asList(split);
                    blogEntity.setTagList(tagList);
                }
                redisUtil.set("indexPage_0", blogList, 172800);
            }
        }else {
            blogList = blogDao.selectBlogListPage(params);
            for (BlogEntity blogEntity : blogList){
                String tagStr = blogEntity.getTagStr();
                String[] split = tagStr.split(",");
                List<String> tagList = Arrays.asList(split);
                blogEntity.setTagList(tagList);
            }
        }
        return blogList;
    }

    @Override
    public List<BlogEntity> selectBlogListPage(Map<String, Object> params) {
        List<BlogEntity> blogList = blogDao.selectBlogListPage(params);
        for (BlogEntity blogEntity : blogList){
            String tagStr = blogEntity.getTagStr();
            String[] split = tagStr.split(",");
            List<String> tagList = Arrays.asList(split);
            blogEntity.setTagList(tagList);
        }
        return blogList;
    }

    @Override
    public Integer selectBlogCount(Map<String, Object> params) {
        return blogDao.selectBlogCount(params);
    }

    @Override
    public BlogEntity selectBlogInfoById(Integer id) {
        BlogEntity blogEntity = null;
        Object o = redisUtil.get("blogInfo_" + id);
        if(ObjectUtils.isNotNull(o)){
            blogEntity = (BlogEntity) o;
        }else{
            blogEntity = blogDao.selectBlogInfoById(id);
            String tagStr = blogEntity.getTagStr();
            String[] split = tagStr.split(",");
            List<String> tagList = Arrays.asList(split);
            blogEntity.setTagList(tagList);
            redisUtil.set("blogInfo_" + id, blogEntity, 86400);
        }
        return blogDao.selectBlogInfoById(id);
    }

    @Override
    public int updateClickNumById(Blogs record) {
        return blogDao.updateClickNumById(record);
    }

    @Override
    public int updateBlogStatusById(Map<String, Object> param) {
        return blogDao.updateBlogStatusById(param);
    }

    @Override
    public List<Blogs> selectClickDESC() {
        return blogDao.selectClickDESC();
    }
}
