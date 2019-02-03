package com.wuzt.myblog.model;

import java.util.Date;

/**
 * @Auther: wuzt
 * @Date: 2019/1/30 10:41
 * @Description:访问记录实体
 */
public class AccessRecord {

    private int id;

    private String ip;

    private String url;

    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
