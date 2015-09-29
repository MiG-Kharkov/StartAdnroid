package ua.com.webtuning.startandroid;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Handler handler;

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

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btnDefault:
                progressDialog = new ProgressDialog(this);
                progressDialog.setTitle(R.string.dialog_title);
                progressDialog.setMessage("Message");
                //add button
                progressDialog.setButton(Dialog.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                progressDialog.show();
                break;
            case R.id.btnHoriz:
                progressDialog = new ProgressDialog(this);
                progressDialog.setTitle(R.string.dialog_title);
                progressDialog.setMessage("Message");
                //Change style
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //set max
                progressDialog.setMax(2148);
                //turn on animation
                progressDialog.setIndeterminate(true);
                progressDialog.show();
                handler = new Handler() {
                    public void handleMessage(Message msg) {
                        progressDialog.setIndeterminate(false);
                        if (progressDialog.getProgress() < progressDialog.getMax()) {
                            progressDialog.incrementProgressBy(50);
                            progressDialog.incrementSecondaryProgressBy(75);
                            handler.sendEmptyMessageDelayed(0, 100);
                        } else
                            progressDialog.dismiss();

                    }

                };
                handler.sendEmptyMessageDelayed(0, 2000);
                break;
        }
    }
}
