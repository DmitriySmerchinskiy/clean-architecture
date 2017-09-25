package com.technologies.mobile.testapp.data.database;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.technologies.mobile.testapp.domain.models.News;

import java.util.List;

@android.arch.persistence.room.Dao
public interface Dao {

    @Query("SELECT * FROM News WHERE id = :id LIMIT 1")
    News getNews(long id);

    @Query("SELECT * FROM News ORDER BY unixTime DESC LIMIT :count OFFSET :offset")
    List<News> getNews(long offset, int count);

    @Insert
    void postNews(News news);

    @Query("DELETE FROM News WHERE unixTime IN " +
            "(SELECT unixTime FROM News ORDER BY unixTime LIMIT :count)")
    void clearOldNotes(int count);

    @Query("SELECT COUNT(*) FROM News")
    long getRowsCount();
}
