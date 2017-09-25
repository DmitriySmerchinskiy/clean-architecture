package com.technologies.mobile.testapp.data.structure;

import com.technologies.mobile.testapp.domain.entities.IList;
import com.technologies.mobile.testapp.domain.models.News;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class TimeProtectedListTest {

    private IList<News> news;

    @Before
    public void setUp() throws Exception {
        news = new TimeProtectedList<>(new ArrayList<News>());
    }

    @Test
    public void addSomeItemsInCorrectOrder() throws Exception {
        news.add(createNews(2));
        news.add(createNews(1));
        news.add(createNews(0));
        Assert.assertEquals(3, news.size());
    }

    @Test
    public void addSomeItemsInIncorrectOrder() throws Exception {
        news.add(createNews(2));
        news.add(createNews(3));
        news.add(createNews(4));
        Assert.assertEquals(1, news.size());
    }

    @Test
    public void addCorrectItemToStart() throws Exception {
        news.add(createNews(2));
        news.add(createNews(1));
        news.add(createNews(0));
        news.add(0, createNews(3));
        Assert.assertEquals(4, news.size());
    }

    @Test
    public void addWrongItemToStart() throws Exception {
        news.add(createNews(5));
        news.add(createNews(3));
        news.add(createNews(1));
        news.add(0, createNews(4));
        Assert.assertEquals(3, news.size());
        Assert.assertEquals(news.get(0).getUnixTime(), 5);
        Assert.assertEquals(news.get(1).getUnixTime(), 3);
        Assert.assertEquals(news.get(2).getUnixTime(), 1);
    }

    @Test
    public void addCorrectItemToMiddle() throws Exception {
        news.add(createNews(5));
        news.add(createNews(3));
        news.add(createNews(1));
        news.add(1, createNews(4));
        Assert.assertEquals(4, news.size());
        Assert.assertEquals(news.get(0).getUnixTime(), 5);
        Assert.assertEquals(news.get(1).getUnixTime(), 4);
        Assert.assertEquals(news.get(2).getUnixTime(), 3);
        Assert.assertEquals(news.get(3).getUnixTime(), 1);
    }

    @Test
    public void addMoreThenPrevious() throws Exception {
        news.add(createNews(5));
        news.add(createNews(3));
        news.add(createNews(1));
        news.add(1, createNews(6));
        Assert.assertEquals(3, news.size());
        Assert.assertEquals(news.get(0).getUnixTime(), 5);
        Assert.assertEquals(news.get(1).getUnixTime(), 3);
        Assert.assertEquals(news.get(2).getUnixTime(), 1);
    }

    @Test
    public void addLessThenNext() throws Exception {
        news.add(createNews(5));
        news.add(createNews(3));
        news.add(createNews(1));
        news.add(1, createNews(2));
        Assert.assertEquals(3, news.size());
        Assert.assertEquals(news.get(0).getUnixTime(), 5);
        Assert.assertEquals(news.get(1).getUnixTime(), 3);
        Assert.assertEquals(news.get(2).getUnixTime(), 1);
    }

    @After
    public void tearDown() throws Exception {

    }

    private News createNews(long time) {
        return new News(0, "t", "t", "c", time);
    }
}