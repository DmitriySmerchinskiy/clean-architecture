package com.technologies.mobile.testapp.domain.interactors.callbacks;

import com.technologies.mobile.testapp.domain.interactors.INewsInteractor;
import com.technologies.mobile.testapp.domain.models.News;

import java.util.List;

public class StubNewsInteractorCallback implements INewsInteractor.NewsCallback {
    @Override
    public void onSuccess(List<News> news) {

    }

    @Override
    public void onError() {

    }
}
