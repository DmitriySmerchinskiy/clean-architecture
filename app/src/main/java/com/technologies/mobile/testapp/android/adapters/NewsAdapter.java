package com.technologies.mobile.testapp.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technologies.mobile.testapp.R;
import com.technologies.mobile.testapp.domain.models.News;

import java.util.LinkedList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> implements INewsAdapter {

    private LinkedList<News> mNews;
    private INewsAdapter.onItemClickListener onItemClickListener;

    public NewsAdapter(INewsAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        mNews = new LinkedList<>();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView tvTitle;
        private TextView tvType;
        private TextView tvContent;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            tvTitle = view.findViewById(R.id.tv_title);
            tvType = view.findViewById(R.id.tv_type);
            tvContent = view.findViewById(R.id.tv_content);
        }

        void bind(final News news) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClicked(news);
                }
            });
            tvTitle.setText(news.getTitle());
            tvType.setText(news.getType());
            tvContent.setText(news.getContent());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.item_news,
                                parent,
                                false));
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
