package com.technologies.mobile.testapp.android.adapters;

import com.technologies.mobile.testapp.domain.models.News;

import java.util.List;

public interface INewsAdapter {

    void addNewsLast(List<News> news);

    interface onItemClickListener {
        void onItemClicked(News news);
    }
}
