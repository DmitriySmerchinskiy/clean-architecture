package com.technologies.mobile.testapp.domain.entities;

import com.technologies.mobile.testapp.domain.models.News;

import java.util.List;

public interface INetwork {

    News getNews(long id);
    List<News> getNews(long offset, int count);
}
