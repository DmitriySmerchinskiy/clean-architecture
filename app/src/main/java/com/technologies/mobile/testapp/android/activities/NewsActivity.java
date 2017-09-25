package com.technologies.mobile.testapp.android.activities;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.technologies.mobile.testapp.Constants;
import com.technologies.mobile.testapp.R;
import com.technologies.mobile.testapp.android.adapters.INewsAdapter;
import com.technologies.mobile.testapp.android.adapters.NewsAdapter;
import com.technologies.mobile.testapp.data.cache.Cache;
import com.technologies.mobile.testapp.data.database.Database;
import com.technologies.mobile.testapp.data.network.FakeNetwork;
import com.technologies.mobile.testapp.data.network.OldNewsGenerator;
import com.technologies.mobile.testapp.data.repository.NewsRepository;
import com.technologies.mobile.testapp.domain.models.News;
import com.technologies.mobile.testapp.databinding.ActivityNewsBinding;
import com.technologies.mobile.testapp.presentation.interactors.NewsInteractor;
import com.technologies.mobile.testapp.presentation.presenters.NewsPresenter;
import com.technologies.mobile.testapp.presentation.views.NewsView;

public class NewsActivity extends AppCompatActivity implements NewsView, INewsAdapter.onItemClickListener {

    private static final String TAG = "NewsActivity";

    private NewsPresenter presenter;
    private NewsAdapter adapter;
    private LinearLayoutManager layoutManager;
    ActivityNewsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news);

        adapter = new NewsAdapter(this);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.rvNewsList.setLayoutManager(layoutManager);
        binding.rvNewsList.setAdapter(adapter);
        binding.rvNewsList.addOnScrollListener(new RecyclerScrollListener());

        presenter =
                new NewsPresenter(
                        new NewsInteractor(
                                new NewsRepository(
                                        new FakeNetwork(
                                                new OldNewsGenerator()),
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
        binding.pbProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.pbProgress.setVisibility(View.GONE);
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
            if (layoutManager.findLastVisibleItemPosition()
                    >= adapter.getItemCount() - Constants.REMAIN_ITEMS_TRIGGER) {
                presenter.loadData(adapter.getItemCount());
            }
        }
    }
}
