package com.technologies.mobile.testapp.android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.technologies.mobile.testapp.R;
import com.technologies.mobile.testapp.data.cache.Cache;
import com.technologies.mobile.testapp.data.database.Database;
import com.technologies.mobile.testapp.data.network.Network;
import com.technologies.mobile.testapp.data.repository.NewsRepository;
import com.technologies.mobile.testapp.domain.models.News;
import com.technologies.mobile.testapp.presentation.interactors.NewsInteractor;
import com.technologies.mobile.testapp.presentation.presenters.DetailedNewsPresenter;
import com.technologies.mobile.testapp.presentation.views.DetailedNewsView;

public class DetailedNewsActivity extends AppCompatActivity implements DetailedNewsView {

    public static final String EXTRA_ID = "EXTRA_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_news);
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
        TextView title = findViewById(R.id.tv_title);
        TextView type = findViewById(R.id.tv_type);
        TextView content = findViewById(R.id.tv_content);

        title.setText(news.getTitle());
        type.setText(news.getType());
        content.setText(news.getContent());
    }
}
