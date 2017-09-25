package com.technologies.mobile.testapp.domain.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class News {

    @PrimaryKey
    private long id;
    private String title;
    private String type;
    private String content;
    private long unixTime;

    public News(long id, String title, String type, String content, long unixTime) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.content = content;
        this.unixTime = unixTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getUnixTime() {
        return unixTime;
    }

    public void setUnixTime(long unixTime) {
        this.unixTime = unixTime;
    }

    @Override
    public boolean equals(Object obj) {
        News o = (News) obj;
        return this.id == o.getId();
    }
}
