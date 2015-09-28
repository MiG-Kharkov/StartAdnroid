package ua.com.webtuning.startandroid;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int DIALOG_DATE = 2;
    int DIALOG_TIME = 1;
    int myHour = 14;
    int myMinute = 35;
    TextView tvTime, tvDate;
    OnTimeSetListener myCallBack = new OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            tvTime.setText("Time is " + myHour + " hours " + myMinute + " minutes");
        }
    };
    private int myYear = 2015;
    private int myMonth = 9;
    private int myDay = 27;
    OnDateSetListener myCallBackDate = new OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myMonth = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;
            tvDate.setText("Date is " + myDay + "." + monthOfYear + "." + myYear);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTime = (TextView) findViewById(R.id.tvTime);
        tvDate = (TextView) findViewById(R.id.tvDate);
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
        if (view.getId() == R.id.tvDate)
            showDialog(DIALOG_DATE);
        else if (view.getId() == R.id.tvTime)
            showDialog(DIALOG_TIME);
        else if (view.getId() == R.id.tvFragment) {
            DialogFragment newFragment =
                    MyAlertDialogFragment.newInstance(R.string.alert_two_buttons_title);
            newFragment.show(getFragmentManager(), "myDialogFragment");
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME) {
            TimePickerDialog tpd = new TimePickerDialog(this, myCallBack, myHour, myMinute, true);
            return tpd;
        } else if (id == DIALOG_DATE) {
            DatePickerDialog dpd = new DatePickerDialog(this, myCallBackDate, myYear, myMonth, myDay);
            return dpd;
        }

        return super.onCreateDialog(id);
    }

    public void doPositiveClick() {
        Toast.makeText(MainActivity.this, "Вы выбрали кнопку OK!",
                Toast.LENGTH_LONG).show();
    }

    public void doNegativeClick() {
        Toast.makeText(MainActivity.this, "Вы выбрали кнопку отмены!",
                Toast.LENGTH_LONG).show();
    }
}
