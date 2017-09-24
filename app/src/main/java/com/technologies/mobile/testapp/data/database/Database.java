package com.technologies.mobile.testapp.data.database;

import android.arch.persistence.room.*;

import com.technologies.mobile.testapp.domain.models.News;

@android.arch.persistence.room.Database(entities = News.class, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract Dao dao();
}
