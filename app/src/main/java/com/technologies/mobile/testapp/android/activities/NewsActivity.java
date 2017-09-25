package com.technologies.mobile.testapp.android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.technologies.mobile.testapp.R;
import com.technologies.mobile.testapp.android.adapters.INewsAdapter;
import com.technologies.mobile.testapp.android.adapters.NewsAdapter;
import com.technologies.mobile.testapp.data.cache.Cache;
import com.technologies.mobile.testapp.data.database.Database;
import com.technologies.mobile.testapp.data.network.Network;
import com.technologies.mobile.testapp.data.repository.NewsRepository;
import com.technologies.mobile.testapp.domain.models.News;
import com.technologies.mobile.testapp.presentation.interactors.NewsInteractor;
import com.technologies.mobile.testapp.presentation.presenters.NewsPresenter;
import com.technologies.mobile.testapp.presentation.views.NewsView;

public class NewsActivity extends AppCompatActivity implements NewsView, INewsAdapter.onItemClickListener {

    private static final String TAG = "NewsActivity";

    private NewsPresenter presenter;
    private ProgressBar progressBar;
    private NewsAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        progressBar = findViewById(R.id.pb_progress);

        adapter = new NewsAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.rv_news_list);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerScrollListener());

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
    public void notifyList() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
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

    @Override
    public void onItemClicked(News news) {
        Intent intent = new Intent(getApplicationContext(), DetailedNewsActivity.class);
        intent.putExtra(DetailedNewsActivity.EXTRA_ID, news.getId());
        startActivity(intent);
    }

    private class RecyclerScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (layoutManager.findLastVisibleItemPosition() > adapter.getItemCount() - 5) {
                presenter.loadData(adapter.getItemCount());
            }
        }
    }
}
