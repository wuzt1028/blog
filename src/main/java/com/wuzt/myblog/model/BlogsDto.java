package com.wuzt.myblog.model;

import java.util.List;

/**
 * @Auther: wuzt
 * @Date: 2018/7/26 14:37
 * @Description:
 */
public class BlogsDto extends Blogs {

    private List<String> tags;
    //新增分类：2 以前的分类：1
    private int selectType;
    //类型名称
    private String blogType;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getSelectType() {
        return selectType;
    }

    public void setSelectType(int selectType) {
        this.selectType = selectType;
    }

    public String getBlogType() {
        return blogType;
    }

    public void setBlogType(String blogType) {
        this.blogType = blogType;
    }
}
