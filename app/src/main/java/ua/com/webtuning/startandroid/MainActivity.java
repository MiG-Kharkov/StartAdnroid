package ua.com.webtuning.startandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = getClass().getSimpleName();

    DBHelper dbHelper;
    EditText etName, etEmail, etID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etID = (EditText) findViewById(R.id.etID);

        dbHelper = new DBHelper(this);
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

    public void onBtnClick(View view) {

        // подготовим данные для вставки\редактирования в виде пар: наименование столбца - значение
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", etName.getText().toString());
        contentValues.put("email", etEmail.getText().toString());
        String id = etID.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (view.getId()) {
            case R.id.btnAdd:
                Log.d(LOG_TAG, "--- Insert in mytable: ---");

                // вставляем запись и получаем ее ID
                long rowID = db.insert("mytable", null, contentValues);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                break;
            case R.id.btnRead:
                Log.d(LOG_TAG, "===Read from mytable===");
                Log.d(LOG_TAG, "===Rows: ");
                Cursor c = db.query("mytable", null, null, null, null, null, null);

                if (c.moveToFirst()) {
                    // определяем номера столбцов по имени в выборке
                    int idColIndex = c.getColumnIndex("id");
                    int nameColIndex = c.getColumnIndex("name");
                    int emailColIndex = c.getColumnIndex("email");
                    do {
                        Log.d(LOG_TAG, "ID = " + c.getInt(idColIndex) +
                                ", name = " + c.getString(nameColIndex) +
                                " email = " + c.getString(emailColIndex));

                    } while (c.moveToNext());

                } else
                    Log.d(LOG_TAG, "0 row");
                break;
            case R.id.btnClear:
                Log.d(LOG_TAG, "===Clear table==");

                int clearCount = db.delete("mytable", null, null);
                Log.d(LOG_TAG, "deleted rows = " + clearCount);
                break;
            case R.id.btnUpd:
                Log.d(LOG_TAG, "---Update mytabe ----");
                int updCount = db.update("mytable", contentValues, "id = ?",
                        new String[]{id});
                Log.d(LOG_TAG, "updated rows count = " + updCount);
                break;
            case R.id.btnDel:
                if (!id.equalsIgnoreCase("")) {
                    Log.d(LOG_TAG, "--- Delete from mytabe: ---");
                    int delCount = db.delete("mytable", "id = " + id, null);
                    Log.d(LOG_TAG, "deleted rows count = " + delCount);
                }
                break;
        }

        dbHelper.close();
    }


    class DBHelper extends SQLiteOpenHelper {

        private static final int DB_VERTION = 1;

        public DBHelper(Context context) {
            super(context, "myDB", null, DB_VERTION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "---Create Database---");

            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "email text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
