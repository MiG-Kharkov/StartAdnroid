package ua.com.webtuning.startandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> products = new ArrayList<>();
    BoxAdapter boxAdapter;

    View header;
    View footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillData();
        boxAdapter = new BoxAdapter(this, products);
        ListView lvMain = (ListView) findViewById(R.id.lvMain);

        header = createHeader("header 1");
        footer = createFooter("Footer 2");

        lvMain.addHeaderView(header);
        lvMain.addFooterView(footer);

        lvMain.setAdapter(boxAdapter);


    }

    private View createFooter(String s) {
        View v = getLayoutInflater().inflate(R.layout.footer, null);
        ((TextView) v.findViewById(R.id.tvText)).setText(s);
        return v;
    }

    private View createHeader(String s) {
        View v = getLayoutInflater().inflate(R.layout.header, null);
        ((TextView) v.findViewById(R.id.tvText)).setText(s);
        return v;
    }

    private void fillData() {
        for (int i = 0; i < 20; i++) {
            products.add(new Product("Prodact " + i, i * 100, R.mipmap.ic_launcher, false));
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

    public void showResult(View view) {
        String result = "Товары в корзине:";
        for (Product p : boxAdapter.getBox()) {
            if (p.box)
                result += "\n" + p.name;
        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}
