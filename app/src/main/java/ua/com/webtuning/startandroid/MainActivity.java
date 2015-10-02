package ua.com.webtuning.startandroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "myLogs";
    MyTask myTask;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

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

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                myTask = new MyTask();
                myTask.execute("file_1", "file2", "file3", "file4");
                break;
            case R.id.btnStatus:
                if (myTask != null)
                    Toast.makeText(this, myTask.getStatus().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCancel:
                if (myTask == null) return;
                myTask.cancel(false);
                break;
        }
    }

    private class MyTask extends AsyncTask<String, Integer, Integer> {

        @Override
        protected Integer doInBackground(String... urls) {
            try {
                int cnt = 0;
                for (String url : urls) {
                    // загружаем файл
                    downloadFile(url);
                    // выводим промежуточные результаты
                    publishProgress(++cnt, urls.length);
                    if (isCancelled()) return -1;
                }
                // разъединяемся
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return urls.length;
        }

        private void downloadFile(String url) throws InterruptedException {
            TimeUnit.SECONDS.sleep(2);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tvInfo.setText("Downloaded " + values[0] + " from " + values[1] + " files.");
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            tvInfo.setText("End process with " + result + " files");
        }

        @Override
        protected void onCancelled(Integer result) {
            super.onCancelled(result);
            tvInfo.setText("End canceled with " + result + " result.");
        }
    }
}
