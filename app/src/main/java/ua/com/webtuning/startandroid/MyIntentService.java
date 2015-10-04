package ua.com.webtuning.startandroid;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * helper methods.
 */
public class MyIntentService extends IntentService {

    static final String ACTION_FIRST = "ua.com.webtuning.startandroid.action.FIRST";
    static final String ACTION_SECOND = "ua.com.webtuning.startandroid.action.SECOND";
    static final String EXTRA_PARAM1 = "ua.com.webtuning.startandroid.extra.PARAM1";
    static final String EXTRA_PARAM2 = "ua.com.webtuning.startandroid.extra.PARAM2";
    final String LOG_TAG = "myLogs -" + getClass().getSimpleName();


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FIRST.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFirst(param1, param2);
            } else if (ACTION_SECOND.equals(action)) {
                final int param1 = intent.getIntExtra(EXTRA_PARAM1, 0);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionSecond(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFirst(String param1, String param2) {
        Log.d(LOG_TAG, "First light method + param1 =" + param1 + " param2= " + param2);
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionSecond(int param1, String param2) {
        Log.d(LOG_TAG, "Second hard method + param1 =" + param1 + " param2= " + param2 + " START");
        try {
            TimeUnit.SECONDS.sleep(param1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(LOG_TAG, "Second hard method + param1 =" + param1 + " param2= " + param2 + " FINISH");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
}
