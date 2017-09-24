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
    public void getNews(final long id, final SingleNewsCallback callback) {
        new Thread() {
            @Override
            public void run() {
                callback.onSuccess(newsRepository.getNews(id));
            }
        }.start();
    }

    @Override
    public void getNews(final long offset, final int count, final NewsCallback callback) {
        new Thread() {
            @Override
            public void run() {
                callback.onSuccess(newsRepository.getNews(offset, count));
            }
        }.start();
    }
}
