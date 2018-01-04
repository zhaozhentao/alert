package com.alert.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zhaotao on 2018/1/1.
 */

@Entity
public class Notice {

    @Id(autoincrement = true)
    public Long id;

    public String content; //内容

    public Date created_at;//消息接收时间

    @Generated(hash = 1200837581)
    public Notice(Long id, String content, Date created_at) {
        this.id = id;
        this.content = content;
        this.created_at = created_at;
    }

    @Generated(hash = 1880392847)
    public Notice() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
