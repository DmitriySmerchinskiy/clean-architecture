package com.technologies.mobile.testapp.data.cache;

import com.technologies.mobile.testapp.data.database.Database;
import com.technologies.mobile.testapp.domain.entities.ICache;
import com.technologies.mobile.testapp.domain.models.News;

import java.util.List;


public class Cache implements ICache {

    Database database;

    public Cache(Database database) {
        this.database = database;
    }

    @Override
    public News getNews(long id) {
        return database.dao().getNews(id);
    }

    @Override
    public List<News> getNews(long offset, int count) {
        return database.dao().getNews(offset, count);
    }

    @Override
    public void addNews(News news) {
        if (database.dao().getNews(news.getId()) == null) {
            database.dao().postNews(news);
        }
    }
}
