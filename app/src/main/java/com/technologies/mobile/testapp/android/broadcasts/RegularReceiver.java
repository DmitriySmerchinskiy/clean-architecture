package com.technologies.mobile.testapp.android.broadcasts;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.technologies.mobile.testapp.Constants;
import com.technologies.mobile.testapp.data.cache.AutoclearableCache;
import com.technologies.mobile.testapp.data.cache.Cache;
import com.technologies.mobile.testapp.data.database.Database;
import com.technologies.mobile.testapp.data.network.FakeNetwork;
import com.technologies.mobile.testapp.data.network.NewNewsGenerator;
import com.technologies.mobile.testapp.data.repository.OnlineFirstNewsRepository;
import com.technologies.mobile.testapp.domain.interactors.INewsInteractor;
import com.technologies.mobile.testapp.domain.models.News;
import com.technologies.mobile.testapp.presentation.interactors.NewsInteractor;

import java.util.List;

public class RegularReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        new NewsInteractor(
                new OnlineFirstNewsRepository(
                        new FakeNetwork(
                                new NewNewsGenerator()),
                        new AutoclearableCache(
                                new Cache(
                                        Database.get(context)),
                                Database.get(context))))
                .getNews(0, 1, new INewsInteractor.NewsCallback() {
                    @Override
                    public void onSuccess(List<News> news) {
                        registerNewTask(context);
                    }

                    @Override
                    public void onError() {
                        registerNewTask(context);
                    }
                });

    }

    private void registerNewTask(Context context) {
        AlarmManager alarmManager =
                (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + Constants.REGULAR_DOWNLOAD_TASK_PERIOD,
                PendingIntent.getBroadcast(
                        context,
                        0,
                        new Intent(
                                context,
                                RegularReceiver.class),
                        PendingIntent.FLAG_CANCEL_CURRENT));
    }
}
