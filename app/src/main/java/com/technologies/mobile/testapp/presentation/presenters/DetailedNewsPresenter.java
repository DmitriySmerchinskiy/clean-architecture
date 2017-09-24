package com.technologies.mobile.testapp.presentation.presenters;

import com.technologies.mobile.testapp.domain.interactors.INewsInteractor;
import com.technologies.mobile.testapp.domain.models.News;
import com.technologies.mobile.testapp.presentation.views.DetailedNewsView;

public class DetailedNewsPresenter {

    private INewsInteractor newsInteractor;
    private DetailedNewsView detailedNewsView;

    public DetailedNewsPresenter(INewsInteractor newsInteractor,
                                 DetailedNewsView detailedNewsView) {
        this.newsInteractor = newsInteractor;
        this.detailedNewsView = detailedNewsView;
    }

    public void loadNews(long id) {
        News news = newsInteractor.getNews(id);
        detailedNewsView.showNews(news);
    }
}
