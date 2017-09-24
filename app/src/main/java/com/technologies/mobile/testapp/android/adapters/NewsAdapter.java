package com.technologies.mobile.testapp.android.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.technologies.mobile.testapp.R;
import com.technologies.mobile.testapp.databinding.ItemNewsBinding;
import com.technologies.mobile.testapp.domain.models.News;

import java.util.LinkedList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> implements INewsAdapter {

    private LinkedList<News> mNews;

    public NewsAdapter() {
        mNews = new LinkedList<>();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemNewsBinding itemNewsBinding;

        ViewHolder(ItemNewsBinding itemNewsBinding) {
            super(itemNewsBinding.getRoot());
            this.itemNewsBinding = itemNewsBinding;
        }

        void bind(News news) {
            itemNewsBinding.setNews(news);
            itemNewsBinding.executePendingBindings();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemNewsBinding binding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.item_news,
                        parent,
                        false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mNews.get(position));
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    @Override
    public void addNewsFirst(List<News> news) {
        int i = 0;
        for (News singleNews : news) {
            if (!mNews.get(i).equals(singleNews)) {
                mNews.add(i, singleNews);
            } else {
                break;
            }
        }
    }

    @Override
    public void addNewsLast(List<News> news) {
        mNews.addAll(news);
    }
}
