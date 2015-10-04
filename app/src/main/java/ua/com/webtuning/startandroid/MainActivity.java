package ua.com.webtuning.startandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void onClick(View view) {
        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent.setAction(MyIntentService.ACTION_FIRST)
                .putExtra(MyIntentService.EXTRA_PARAM1, "test1")
                .putExtra(MyIntentService.EXTRA_PARAM2, "call1"));
        startService(intent.setAction(MyIntentService.ACTION_FIRST)
                .putExtra(MyIntentService.EXTRA_PARAM1, "test2")
                .putExtra(MyIntentService.EXTRA_PARAM2, "call2"));
        startService(intent.setAction(MyIntentService.ACTION_FIRST)
                .putExtra(MyIntentService.EXTRA_PARAM1, "test3")
                .putExtra(MyIntentService.EXTRA_PARAM2, "call3"));

        startService(intent.setAction(MyIntentService.ACTION_SECOND)
                .putExtra(MyIntentService.EXTRA_PARAM1, 8)
                .putExtra(MyIntentService.EXTRA_PARAM2, "second 1"));

        startService(intent.setAction(MyIntentService.ACTION_SECOND)
                .putExtra(MyIntentService.EXTRA_PARAM1, 4)
                .putExtra(MyIntentService.EXTRA_PARAM2, "second 2"));

        startService(intent.setAction(MyIntentService.ACTION_SECOND)
                .putExtra(MyIntentService.EXTRA_PARAM1, 5)
                .putExtra(MyIntentService.EXTRA_PARAM2, "second 3"));

    }


}
