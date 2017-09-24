package com.technologies.mobile.testapp.data.network;

import com.technologies.mobile.testapp.domain.entities.INetwork;
import com.technologies.mobile.testapp.domain.models.News;

import java.util.ArrayList;
import java.util.List;

public class Network implements INetwork {
    @Override
    public News getNews(long id) {
        return new News(id, "News #" + id, "News", "Content of news #" + id);
    }

    @Override
    public List<News> getNews(long offset, int count) {
        List<News> news = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            long id = (long) (Math.random() * 10_000);
            news.add(new News(id, "News #" + id, "News", "Content of news #" + id));
        }
        return news;
    }
}
