package com.technologies.mobile.testapp.data.network;

import com.technologies.mobile.testapp.domain.entities.IFakeNewsGenerator;
import com.technologies.mobile.testapp.domain.entities.INetwork;
import com.technologies.mobile.testapp.domain.models.News;

import java.util.ArrayList;
import java.util.List;

public class FakeNetwork implements INetwork {

    private IFakeNewsGenerator fakeNewsGenerator;

    public FakeNetwork(IFakeNewsGenerator fakeNewsGenerator) {
        this.fakeNewsGenerator = fakeNewsGenerator;
    }

    @Override
    public News getNews(long id) {
        fakeWait(100);
        return fakeNewsGenerator.generateNews();
    }

    @Override
    public List<News> getNews(long offset, int count) {
        fakeWait(1000);
        List<News> news = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            news.add(fakeNewsGenerator.generateNews());
        }
        return news;
    }

    private void fakeWait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
