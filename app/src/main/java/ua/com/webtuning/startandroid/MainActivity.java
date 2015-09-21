package ua.com.webtuning.startandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

        tv.setOnCreateContextMenuListener(this);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Animation anim = null;

        switch (item.getItemId()) {
            case R.id.alpha:
                anim = AnimationUtils.loadAnimation(this, R.anim.myalpha);
                break;
            case R.id.comb:
                anim = AnimationUtils.loadAnimation(this, R.anim.mycombo);
                break;
            case R.id.rotate:
                anim = AnimationUtils.loadAnimation(this, R.anim.myrotate);
                break;
            case R.id.scale:
                anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
                break;
            case R.id.translate:
                anim = AnimationUtils.loadAnimation(this, R.anim.mytrans);
                break;
        }
        if (anim != null) tv.startAnimation(anim);
        return super.onContextItemSelected(item);
    }
}
