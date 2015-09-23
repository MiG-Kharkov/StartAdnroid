package ua.com.webtuning.startandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {


    private static final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater layoutInflater = getLayoutInflater();

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        View view1 = layoutInflater.inflate(R.layout.text, linLayout, true);
        ViewGroup.LayoutParams lp1 = view1.getLayoutParams();

        Log.d(LOG_TAG, "Class of view: " + view1.getClass().toString());
        Log.d(LOG_TAG, "LayoutParams of view is : " + lp1.getClass().toString());

        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.relLayout);
        View view2 = layoutInflater.inflate(R.layout.text, relLayout, true);
        ViewGroup.LayoutParams lp2 = view2.getLayoutParams();

        Log.d(LOG_TAG, "Class of view: " + view2.getClass().toString());
        Log.d(LOG_TAG, "LayoutParams of view is : " + lp2.getClass().toString());

//        Смотрим лог:
//
//        Class of view1: class android.widget.LinearLayout
//        Class of layoutParams of view1: class android.widget.LinearLayout$LayoutParams
//        Class of view2: class android.widget.RelativeLayout
//        Class of layoutParams of view2: class android.widget.LinearLayout$LayoutParams

//        Обратите внимание на класс элементов. В первом случае - это LinearLayout,
//        а во втором - RelativeLayout. Т.е. метод inflate вернул нам не созданные из
//        layout-файла View-элементы, а те, что мы указывали как root. А созданные из
//        layout-файла View элементы он добавил в root как дочерние аналогично команде addView.
//        Это произошло потому, что мы указали true в третьем параметре (attachToRoot) метода inflate.


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
