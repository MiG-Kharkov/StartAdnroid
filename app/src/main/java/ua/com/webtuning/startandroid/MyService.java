package ua.com.webtuning.startandroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by mig on 03.10.15.
 */
public class MyService extends Service {

    private static final int MULTY_THREADS = 3;
    final String LOG_TAG = "myLogs";
    ExecutorService executorService;
    Object someResult;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
        executorService = Executors.newFixedThreadPool(MULTY_THREADS);
        someResult = new Object();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MyService onDestroy");
        someResult = null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "MyService onStartCommand, startId = " + startId);
        int time = intent.getIntExtra("time", 1);
        MyRun myRun = new MyRun(time, startId);

        executorService.execute(myRun);

        return super.onStartCommand(intent, flags, startId);
    }

    class MyRun implements Runnable {
        int time;
        int startId;

        public MyRun(int time, int startId) {
            this.time = time;
            this.startId = startId;
            Log.d(LOG_TAG, "MyRun = " + startId + " created");
        }

        @Override
        public void run() {
            Log.d(LOG_TAG, "MyRun =" + startId + " start, time = " + time);
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Log.d(LOG_TAG, "MyRun =" + startId + " someRes = " + someResult.getClass());
            } catch (NullPointerException e) {
                Log.d(LOG_TAG, "MyRun =" + startId + " error, null pointer");
            }
            stop();
        }

        void stop() {
            Log.d(LOG_TAG, "MyRun#" + startId + " end, stopSelfResult("
                    + startId + ") = " + stopSelfResult(startId));
        }


    }
}

