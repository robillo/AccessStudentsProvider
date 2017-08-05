package com.robillo.accessstudentsprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String PROVIDER_NAME = "com.appbusters.robinkamboj.udacitysyllabuspart1.controller.StudentsProvider";
    public static final String URL = "content://" + PROVIDER_NAME + "/students";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String GRADE = "grade";

    EditText name;
    EditText grade;
    Button add;
    Button retrieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editText2);
        grade = (EditText) findViewById(R.id.editText3);
        add = (Button) findViewById(R.id.add);
        retrieve = (Button) findViewById(R.id.retrieve);
    }

    public void setAdd(View v){
        // Add a new student record
        ContentValues values = new ContentValues();
        values.put(NAME, name.getText().toString());

        values.put(GRADE, grade.getText().toString());

        Uri uri = MainActivity.this.getContentResolver().insert(CONTENT_URI, values);

        Toast.makeText(MainActivity.this, uri != null ? uri.toString() : null, Toast.LENGTH_LONG).show();
    }

    public void setRetrieve(View v){
        // Retrieve student records
        String URL = "content://com.appbusters.robinkamboj.udacitysyllabuspart1.controller.StudentsProvider";
        Uri students = Uri.parse(URL);
        Cursor c = MainActivity.this.getContentResolver().query(students, null, null, null, "name");
        if (c != null && c.moveToFirst()) {
            do {
                Toast.makeText(MainActivity.this,
                        c.getString(c.getColumnIndex(_ID)) +
                                ", " + c.getString(c.getColumnIndex(NAME)) +
                                ", " + c.getString(c.getColumnIndex(GRADE)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }
    }
}
