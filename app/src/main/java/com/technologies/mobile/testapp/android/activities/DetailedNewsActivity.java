package com.technologies.mobile.testapp.android.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.technologies.mobile.testapp.R;
import com.technologies.mobile.testapp.data.cache.Cache;
import com.technologies.mobile.testapp.data.database.Database;
import com.technologies.mobile.testapp.data.network.Network;
import com.technologies.mobile.testapp.data.repository.NewsRepository;
import com.technologies.mobile.testapp.databinding.ActivityDetailedNewsBinding;
import com.technologies.mobile.testapp.domain.models.News;
import com.technologies.mobile.testapp.presentation.interactors.NewsInteractor;
import com.technologies.mobile.testapp.presentation.presenters.DetailedNewsPresenter;
import com.technologies.mobile.testapp.presentation.views.DetailedNewsView;

public class DetailedNewsActivity extends AppCompatActivity implements DetailedNewsView {

    public static final String EXTRA_ID = "EXTRA_ID";

    ActivityDetailedNewsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailed_news);
        new DetailedNewsPresenter(
                new NewsInteractor(
                        new NewsRepository(
                                new Network(),
                                new Cache(Database.get(getApplicationContext())))),
                this)
                .loadNews(
                        getIntent()
                                .getIntExtra(EXTRA_ID, 0));
    }

    @Override
    public void showNews(News news) {
        binding.setNews(news);
    }
}
