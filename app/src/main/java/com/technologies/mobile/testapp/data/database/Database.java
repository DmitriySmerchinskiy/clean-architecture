package com.technologies.mobile.testapp.data.database;

import android.arch.persistence.room.*;
import android.content.Context;

import com.technologies.mobile.testapp.Constants;
import com.technologies.mobile.testapp.domain.models.News;

@android.arch.persistence.room.Database(entities = News.class, version = 1)
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public static Database get(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context,
                    Database.class,
                    Constants.DATABASE_NAME).build();
        }
        return instance;
    }

    public abstract Dao dao();
}
