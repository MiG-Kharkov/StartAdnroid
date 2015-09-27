package ua.com.webtuning.startandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_VALUE = "value";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    final int positive = android.R.drawable.arrow_up_float;
    final int negative = android.R.drawable.arrow_down_float;

    ListView lvSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // массив данных
        int[] values = {8, 4, -3, 2, -5, 0, 3, -6, 1, -1};

        ArrayList<Map<String, Object>> data = new ArrayList<>(values.length);
        Map<String, Object> m;
        for (int i = 0; i < values.length; i++) {
            m = new HashMap<>();
            m.put(ATTRIBUTE_NAME_TEXT, "Day " + i + 1);
            m.put(ATTRIBUTE_NAME_VALUE, values[i]);
            if (values[i] > 0) m.put(ATTRIBUTE_NAME_IMAGE, positive);
            else m.put(ATTRIBUTE_NAME_IMAGE, negative);
            data.add(m);
        }


        String[] from = {ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_VALUE, ATTRIBUTE_NAME_IMAGE};
        int[] to = {R.id.tvText, R.id.tvValue, R.id.ivImg};

        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item, from, to);

        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(adapter);
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
