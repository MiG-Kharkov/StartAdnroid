package ua.com.webtuning.startandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "myLogs";
    String[] name = {"Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
            "Костя", "Игорь", "Petr"};
    String[] position = {"Программер", "Бухгалтер", "Программер",
            "Программер", "Бухгалтер", "Директор", "Программер", "Охранник", "PM"};
    int salary[] = {13000, 10000, 13000, 13000, 10000, 15000, 13000, 8000, 10000};

    int[] colors = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colors[0] = Color.parseColor("#559966CC");
        colors[1] = Color.parseColor("#55336699");

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);

        LayoutInflater ltInflater = getLayoutInflater();

        for (int i = 0; i < name.length; i++) {
            Log.d(LOG_TAG, "i= " + i);
            View item = ltInflater.inflate(R.layout.item, linLayout, false);
            TextView tvName = (TextView) item.findViewById(R.id.tvName);
            TextView tvSalary = (TextView) item.findViewById(R.id.tvSalary);
            TextView tvPosition = (TextView) item.findViewById(R.id.tvPosition);

            tvName.setText(name[i]);
            tvPosition.setText("Должность : " + position[i]);
            tvSalary.setText("Зарплата: " + String.valueOf(salary[i]));

            item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
            item.setBackgroundColor(colors[i % 2]);
            linLayout.addView(item);
        }

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
