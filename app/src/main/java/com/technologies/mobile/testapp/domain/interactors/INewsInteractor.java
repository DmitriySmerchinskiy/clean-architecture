package com.technologies.mobile.testapp.domain.interactors;

import com.technologies.mobile.testapp.domain.models.News;

import java.util.List;

public interface INewsInteractor {

    News getNews(long id);
    List<News> getNews(long offset, int count);
}
