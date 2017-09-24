package com.technologies.mobile.testapp.presentation.interactors;

import com.technologies.mobile.testapp.domain.interactors.INewsInteractor;
import com.technologies.mobile.testapp.domain.models.News;
import com.technologies.mobile.testapp.domain.repository.INewsRepository;

import java.util.List;

public class NewsInteractor implements INewsInteractor {

    private INewsRepository newsRepository;

    public NewsInteractor(INewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public News getNews(long id) {
        return newsRepository.getNews(id);
    }

    @Override
    public List<News> getNews(long offset, int count) {
        return newsRepository.getNews(offset, count);
    }
}
