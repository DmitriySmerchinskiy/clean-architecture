package com.technologies.mobile.testapp.data.network;

import com.technologies.mobile.testapp.domain.entities.INetwork;
import com.technologies.mobile.testapp.domain.models.News;

import java.util.ArrayList;
import java.util.List;

public class Network implements INetwork {
    @Override
    public News getNews(long id) {
        fakeWait(100);
        return new News(id, "News #" + id, "News", "Content of news #" + id);
    }

    @Override
    public List<News> getNews(long offset, int count) {
        fakeWait(1000);
        List<News> news = new ArrayList<>();
        long id = (long) (Math.random() * 10_000);
        for (int i = 0; i < count; i++) {
            news.add(new News(id, "News #" + id, "News", "Content of news #" + id));
            id--;
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
