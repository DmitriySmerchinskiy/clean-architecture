package com.technologies.mobile.testapp.domain.interactors;

import com.technologies.mobile.testapp.domain.models.News;

import java.util.List;

public interface INewsInteractor {

    void getNews(long id, SingleNewsCallback callback);
    void getNews(long offset, int count, NewsCallback callback);

    interface SingleNewsCallback{
        void onSuccess(News news);
        void onError();
    }

    interface NewsCallback{
        void onSuccess(List<News> news);
        void onError();
    }
}
