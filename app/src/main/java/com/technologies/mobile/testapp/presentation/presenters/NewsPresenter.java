package com.technologies.mobile.testapp.presentation.presenters;

import com.technologies.mobile.testapp.Constants;
import com.technologies.mobile.testapp.android.adapters.INewsAdapter;
import com.technologies.mobile.testapp.domain.interactors.INewsInteractor;
import com.technologies.mobile.testapp.domain.models.News;
import com.technologies.mobile.testapp.presentation.views.NewsView;

import java.util.List;

public class NewsPresenter {

    private INewsInteractor newsInteractor;
    private final NewsView newsView;
    private final INewsAdapter newsAdapter;
    private boolean isLoading;

    public NewsPresenter(INewsInteractor newsInteractor,
                         NewsView newsView,
                         INewsAdapter newsAdapter) {
        this.newsInteractor = newsInteractor;
        this.newsView = newsView;
        this.newsAdapter = newsAdapter;
        this.isLoading = false;
    }

    public void loadData(int offset) {
        if(!isLoading) {
            isLoading = true;
            newsView.showLoading();
            newsInteractor
                    .getNews(offset, Constants.DOWNLOAD_COUNT, new INewsInteractor.NewsCallback() {
                        @Override
                        public void onSuccess(List<News> news) {
                            newsAdapter.addNewsLast(news);
                            newsView.notifyList();
                            newsView.hideLoading();
                            isLoading = false;
                        }

                        @Override
                        public void onError() {
                            newsView.hideLoading();
                            isLoading = false;
                        }
                    });
        }
    }
}
