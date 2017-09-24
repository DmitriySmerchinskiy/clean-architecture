package com.technologies.mobile.testapp.domain.repository;

import com.technologies.mobile.testapp.domain.models.News;

import java.util.List;

public interface INewsRepository {

    News getNews(long id);
    List<News> getNews(long offset, int count);
}
