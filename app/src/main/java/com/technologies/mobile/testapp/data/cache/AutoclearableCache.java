package com.technologies.mobile.testapp.data.cache;

import android.util.Log;

import com.technologies.mobile.testapp.Constants;
import com.technologies.mobile.testapp.data.database.Database;
import com.technologies.mobile.testapp.domain.entities.ICache;
import com.technologies.mobile.testapp.domain.models.News;

import java.util.List;

/**
 * Created by diviator on 25.09.2017.
 */

public class AutoclearableCache implements ICache {

    private static final String TAG = "AutoclearableCache";

    private ICache cache;
    private Database database;

    public AutoclearableCache(ICache cache, Database database) {
        this.cache = cache;
        this.database = database;
    }

    @Override
    public News getNews(long id) {
        return cache.getNews(id);
    }

    @Override
    public List<News> getNews(long offset, int count) {
        return cache.getNews(offset, count);
    }

    @Override
    public void addNews(News news) {
        cache.addNews(news);
        if (database.dao().getRowsCount() > Constants.CACHE_LIMIT) {
            database.dao().clearOldNotes(100);
        }
    }
}
