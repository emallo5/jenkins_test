package com.aparcsystems.task;

import android.content.Context;

import com.aparcsystems.client.AndroidClient;
import com.aparcsystems.model.HttpPenalty;
import com.google.inject.Inject;

public class ShowPenaltyTask extends RestAsyncTask<HttpPenalty> {
    private String penaltyCode;
    @Inject
    private Context context;
    @Inject
    private AndroidClient client;

    public ShowPenaltyTask(Context context,String penaltyCode) {
        super(context);
        this.penaltyCode=penaltyCode;
    }

    @Override
    protected void onPreExecute() throws Exception {
        super.onPreExecute();
    }

    @Override
    public synchronized HttpPenalty call() throws Exception {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
        return client.getPenalty(penaltyCode);
    }

    @Override
    protected void onSuccess(HttpPenalty httpPenalty) throws Exception {
        super.onSuccess(httpPenalty);
        bus.postSticky(new PenaltyEvent(httpPenalty));
    }

    public class PenaltyEvent {
        public HttpPenalty httpPenalty;

        public PenaltyEvent(HttpPenalty httpPenalty) {
            this.httpPenalty = httpPenalty;
        }
    }


}
