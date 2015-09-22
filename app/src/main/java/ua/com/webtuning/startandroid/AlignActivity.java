package ua.com.webtuning.startandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AlignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_align);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_align, menu);
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

    public void btnAlineClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btnLeft:
                intent.putExtra("aligment", Gravity.LEFT);
                break;
            case R.id.btnCenter:
                intent.putExtra("aligment", Gravity.CENTER);
                break;
            case R.id.btnRight:
                intent.putExtra("aligment", Gravity.RIGHT);
                break;
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
