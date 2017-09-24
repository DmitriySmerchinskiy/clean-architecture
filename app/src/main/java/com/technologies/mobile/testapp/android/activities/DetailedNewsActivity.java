package com.technologies.mobile.testapp.android.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.technologies.mobile.testapp.R;
import com.technologies.mobile.testapp.databinding.ActivityDetailedNewsBinding;

public class DetailedNewsActivity extends AppCompatActivity {

    ActivityDetailedNewsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailed_news);
    }
}
