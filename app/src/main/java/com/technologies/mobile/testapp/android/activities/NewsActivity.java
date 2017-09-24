package com.technologies.mobile.testapp.android.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.technologies.mobile.testapp.R;
import com.technologies.mobile.testapp.android.adapters.NewsAdapter;
import com.technologies.mobile.testapp.data.cache.Cache;
import com.technologies.mobile.testapp.data.database.Database;
import com.technologies.mobile.testapp.data.network.Network;
import com.technologies.mobile.testapp.data.repository.NewsRepository;
import com.technologies.mobile.testapp.databinding.ActivityNewsBinding;
import com.technologies.mobile.testapp.presentation.interactors.NewsInteractor;
import com.technologies.mobile.testapp.presentation.presenters.NewsPresenter;
import com.technologies.mobile.testapp.presentation.views.NewsView;

public class NewsActivity extends AppCompatActivity implements NewsView {

    ActivityNewsBinding binding;
    NewsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news);

        NewsAdapter adapter = new NewsAdapter();
        binding.rvNewsList.setLayoutManager(new LinearLayoutManager(this));
        binding.rvNewsList.setAdapter(adapter);

        presenter =
                new NewsPresenter(
                        new NewsInteractor(
                                new NewsRepository(
                                        new Network(),
                                        new Cache(Database.get(getApplicationContext())))),
                        this,
                        adapter);
        presenter.loadData(0);
    }

    @Override
    public void showLoading() {
        binding.pbProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.pbProgress.setVisibility(View.GONE);
    }
}
