package com.technologies.mobile.testapp.android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.technologies.mobile.testapp.R;
import com.technologies.mobile.testapp.android.adapters.NewsAdapter;
import com.technologies.mobile.testapp.data.cache.Cache;
import com.technologies.mobile.testapp.data.database.Database;
import com.technologies.mobile.testapp.data.network.Network;
import com.technologies.mobile.testapp.data.repository.NewsRepository;
import com.technologies.mobile.testapp.presentation.interactors.NewsInteractor;
import com.technologies.mobile.testapp.presentation.presenters.NewsPresenter;
import com.technologies.mobile.testapp.presentation.views.NewsView;

public class NewsActivity extends AppCompatActivity implements NewsView {

    private NewsPresenter presenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        progressBar = findViewById(R.id.pb_progress);

        NewsAdapter adapter = new NewsAdapter();
        RecyclerView recyclerView = findViewById(R.id.rv_news_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

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
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
