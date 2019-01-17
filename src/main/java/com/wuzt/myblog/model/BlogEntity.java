package com.wuzt.myblog.model;

import java.util.Date;
import java.util.List;

/**
 * @Auther: wuzt
 * @Date: 2018/8/28 11:31
 * @Description:
 */
public class BlogEntity {

    private Integer id;
    //博客标题
    private String title;
    //类型id
    private Integer typeId;
    //类型名称
    private String typeName;
    //点击量
    private Long clickNum;
    //用户id
    private Integer userId;
    //状态 0：草稿 1：发表
    private Integer status;
    //创建时间
    private Date createTime;
    //正文
    private String content;
    //简介
    private String subContent;
    //封面
    private String cover;
    //标签集合
    private List<String> tagList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getClickNum() {
        return clickNum;
    }

    public void setClickNum(Long clickNum) {
        this.clickNum = clickNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public String getSubContent() {
        return subContent;
    }

    public void setSubContent(String subContent) {
        this.subContent = subContent;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
