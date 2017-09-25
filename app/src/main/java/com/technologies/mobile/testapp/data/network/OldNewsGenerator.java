package com.technologies.mobile.testapp.data.network;

import android.util.Log;

import com.technologies.mobile.testapp.domain.entities.IFakeNewsGenerator;
import com.technologies.mobile.testapp.domain.models.News;


public class OldNewsGenerator implements IFakeNewsGenerator {

    private static final long MAX_TIME = Long.MAX_VALUE;

    @Override
    public News generateNews() {
        fakeWait(2);
        long id = (long) (Math.random() * 1_000_000_000_000_000_000L);
        return
                new News(
                        id,
                        "News #" + id,
                        "News",
                        "Content of news #" + id,
                        MAX_TIME - System.currentTimeMillis());
    }

    private void fakeWait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
