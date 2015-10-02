package ua.com.webtuning.startandroid;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";
    final int MAX = 100;
    ProgressBar progressBar;
    TextView tvInfo;
    CheckBox chbInfo;
    int cnt = 0;
    Handler handler;

    Runnable updateProgress = new Runnable() {
        @Override
        public void run() {
            progressBar.setProgress(cnt);
        }
    };
    Runnable showInfo = new Runnable() {
        @Override
        public void run() {
            Log.d(LOG_TAG, "showInfo");
            tvInfo.setText("Count = " + cnt);
            //stat itself in 1000msc
            handler.postDelayed(showInfo, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        progressBar = (ProgressBar) findViewById(R.id.pbCount);
        progressBar.setMax(MAX);
        progressBar.setProgress(0);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
        chbInfo = (CheckBox) findViewById(R.id.chbInfo);

        chbInfo.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvInfo.setVisibility(View.VISIBLE);
                    //show information
                    handler.post(showInfo);
                } else {
                    tvInfo.setVisibility(View.GONE);
                    //cancel information
                    handler.removeCallbacks(showInfo);
                }
            }
        });
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (cnt = 1; cnt < MAX; cnt++) {

                        TimeUnit.MILLISECONDS.sleep(100);
                        Log.d(LOG_TAG, "Cnt = " + cnt);
                        //update information
                        handler.post(updateProgress);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
