package com.technologies.mobile.testapp.data.network;

import com.technologies.mobile.testapp.domain.entities.IFakeNewsGenerator;
import com.technologies.mobile.testapp.domain.models.News;

public class NewNewsGenerator implements IFakeNewsGenerator {

    private static final long VALUE = 9223369024100868167L;

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
                        System.currentTimeMillis() + VALUE);
    }

    private void fakeWait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
