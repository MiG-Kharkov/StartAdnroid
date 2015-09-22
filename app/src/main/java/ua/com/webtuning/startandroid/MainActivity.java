package ua.com.webtuning.startandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ALIGN = 1;
    private static final int REQUEST_CODE_COLOR = 2;
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText = (TextView) findViewById(R.id.tvText);
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

    public void onBtnClickMain(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnAlign:
                intent = new Intent(this, AlignActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ALIGN);
                break;
            case R.id.btnColor:
                intent = new Intent(this, ColorActivity.class);
                startActivityForResult(intent, REQUEST_CODE_COLOR);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("myLogs", "requestCode = " + requestCode + ", resultCode = " + resultCode);
        if (resultCode != RESULT_OK || data == null) return;
        switch (requestCode) {
            case REQUEST_CODE_ALIGN:
                int grav = data.getIntExtra("aligment", Gravity.LEFT);
                tvText.setGravity(grav);
                break;
            case REQUEST_CODE_COLOR:
                int color = data.getIntExtra("color", Color.WHITE);
                tvText.setTextColor(color);
                break;
            default:
                Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
