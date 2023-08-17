package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Detail extends AppCompatActivity {

    TextView task,title,date,time,description;

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        task = findViewById(R.id.taskdetail);
        title = findViewById(R.id.titledetail);
        date = findViewById(R.id.datedetail);
        time = findViewById(R.id.timedetail);
        description = findViewById(R.id.descriptiondetail);

        extras = getIntent().getExtras();

        if(extras != null){
            task.setText("Task type :"+extras.getString("Task"));
            title.setText("Title :"+extras.getString("Title"));
            date.setText("Date :"+extras.getString("Date"));
            time.setText("Time :"+extras.getString("Time"));
            description.setText("Description :"+extras.getString("Description"));
        }
    }

    public void back_to_home(View view){
        finish();
    }

    public void delete(View view){
        Boolean checkdeletedata = MainActivity.DB.deletedata(extras.getString("Task"),extras.getString("Title"));
        if(checkdeletedata == true)
            Toast.makeText(Detail.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(Detail.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
        finish();
    }
}