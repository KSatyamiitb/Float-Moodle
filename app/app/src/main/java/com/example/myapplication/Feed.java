package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.util.Calendar;

public class Feed extends AppCompatActivity {

    String TAG = "FeedActivity";

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    EditText title,et_date,et_time,description;
    int Hour,Minute;
    Button save;

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.tasks, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        save = findViewById(R.id.save);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        et_date = findViewById(R.id.et_date);
        Calendar calendar = Calendar.getInstance();
        final  int year = calendar.get(Calendar.YEAR);
        final  int month = calendar.get(Calendar.MONTH);
        final  int day = calendar.get(Calendar.DAY_OF_MONTH);

        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Feed.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date;
                        if(day < 10){
                            if(month<10){
                                 date = "0"+day+"/0"+month+"/"+year;
                            }
                            else{
                                date = "0"+day+"/"+month+"/"+year;
                            }
                        }
                        else{
                            if(month < 10){
                                date = day+"/0"+month+"/"+year;
                            }
                            else {
                                date = day+"/"+month+"/"+year;
                            }
                        }
                        et_date.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        et_time = findViewById(R.id.et_time);

        et_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Feed.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Hour = hourOfDay;
                        Minute = minute;

                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.set(0,0,0,Hour,Minute);
                        et_time.setText(DateFormat.format("hh:mm:aa", calendar1));
                    }
                },12,0,false);
                timePickerDialog.updateTime(Hour,Minute);
                timePickerDialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String taskTXT = spinner.getSelectedItem().toString();
                Log.i(TAG, taskTXT);
                String titleTXT = title.getText().toString();
                Log.i(TAG, titleTXT);
                String dateTXT = et_date.getText().toString();
                Log.i(TAG, dateTXT);
                String timeTXT = et_time.getText().toString();
                Log.i(TAG, timeTXT);
                String descriptionTXT = description.getText().toString();
                Log.i(TAG, descriptionTXT);

                Boolean checkinsertdata = MainActivity.DB.inserttaskdata(taskTXT, titleTXT, dateTXT, timeTXT, descriptionTXT);
                if(checkinsertdata == true){
                    Toast.makeText(Feed.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(Feed.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void back_to_home(View view){
        finish();
    }


}