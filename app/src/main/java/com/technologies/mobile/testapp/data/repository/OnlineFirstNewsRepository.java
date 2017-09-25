package com.technologies.mobile.testapp.data.repository;

import com.technologies.mobile.testapp.domain.entities.ICache;
import com.technologies.mobile.testapp.domain.entities.INetwork;
import com.technologies.mobile.testapp.domain.models.News;
import com.technologies.mobile.testapp.domain.repository.INewsRepository;

import java.util.List;

public class OnlineFirstNewsRepository implements INewsRepository {

    private final ICache cache;
    private final INetwork network;

    public OnlineFirstNewsRepository(INetwork network, ICache cache) {
        this.cache = cache;
        this.network = network;
    }

    @Override
    public News getNews(long id) {
        News news = network.getNews(id);
        cache.addNews(news);
        return news;
    }

    @Override
    public List<News> getNews(long offset, int count) {
        List<News> news = network.getNews(offset, count);
        for(News singleNews : news ) {
            cache.addNews(singleNews);
        }
        return news;
    }
}
