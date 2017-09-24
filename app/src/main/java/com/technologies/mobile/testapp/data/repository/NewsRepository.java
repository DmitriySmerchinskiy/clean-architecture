package com.technologies.mobile.testapp.data.repository;

import com.technologies.mobile.testapp.domain.entities.ICache;
import com.technologies.mobile.testapp.domain.entities.INetwork;
import com.technologies.mobile.testapp.domain.models.News;
import com.technologies.mobile.testapp.domain.repository.INewsRepository;

import java.util.List;

public class NewsRepository implements INewsRepository {

    private final INetwork network;
    private final ICache cache;

    public NewsRepository(INetwork network, ICache cache) {
        this.network = network;
        this.cache = cache;
    }

    @Override
    public News getNews(long id) {
        News news = cache.getNews(id);
        if (news == null) {
            news = network.getNews(id);
            cache.addNews(news);
        }
        return news;
    }

    @Override
    public List<News> getNews(long offset, int count) {
        List<News> news = cache.getNews(offset, count);
        if (news.isEmpty()) {
            news = network.getNews(offset, count);
            for (News singleNews : news) {
                cache.addNews(singleNews);
            }
        }
        return news;
    }
}
