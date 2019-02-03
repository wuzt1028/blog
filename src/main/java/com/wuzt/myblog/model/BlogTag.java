package com.wuzt.myblog.model;

/**
 * @Auther: wuzt
 * @Date: 2019/2/1 10:14
 * @Description:
 */
public class BlogTag {

    private int id;

    private Integer blogId;

    private Integer tagId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}
