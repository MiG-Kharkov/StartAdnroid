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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private static final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater layoutInflater = getLayoutInflater();

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        View view1 = layoutInflater.inflate(R.layout.text, linLayout, false);
        ViewGroup.LayoutParams lp1 = view1.getLayoutParams();

        Log.d(LOG_TAG, "Class of view: " + view1.getClass().toString());
        Log.d(LOG_TAG, "LayoutParams of view is : " + lp1.getClass().toString());
        Log.d(LOG_TAG, "Text of view: " + ((TextView) view1).getText());

        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.relLayout);
        View view2 = layoutInflater.inflate(R.layout.text, relLayout, false);
        ViewGroup.LayoutParams lp2 = view2.getLayoutParams();

        Log.d(LOG_TAG, "Class of view: " + view2.getClass().toString());
        Log.d(LOG_TAG, "LayoutParams of view is : " + lp2.getClass().toString());
        Log.d(LOG_TAG, "Text of view: " + ((TextView) view2).getText());

//        Мы находим элементы linLayout и relLayout с экрана и с помощью LayoutInflater создаем
//        два View-элемента из layout-файла text.xml. Для первого указываем root – linLayout,
//        для второго – relLayout. Но третий параметр attachToRoot оставляем false.
//        Это значит, что созданный View-элемент получит LayoutParams от root-элемента,
//        но не добавится к нему.
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
