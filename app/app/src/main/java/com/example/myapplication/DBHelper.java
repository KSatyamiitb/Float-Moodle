package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "Taskdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Taskdetails(task TEXT, title TEXT primary key, date TEXT, time TEXT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Taskdetails");
    }

    public Boolean inserttaskdata(String task, String title, String date, String time, String description){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("task", task);
        contentValues.put("title", title);
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("description", description);
        long result = db.insert("Taskdetails", null, contentValues);
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }

    Cursor read_studyplan(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("Select * from Taskdetails where task = ?", new String[] {"Study Plan"});
        }
        return cursor;
    }

    Integer studyplan_count_date(String dt){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("Select * from Taskdetails where task = ? AND date = ?", new String[] {"Study Plan",dt});
        }
        return cursor.getCount();
    }

    Cursor read_assignment(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("Select * from Taskdetails where task = ?", new String[] {"Assignment"});
        }
        return cursor;
    }

    Integer assignment_count_date(String dt){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("Select * from Taskdetails where task = ? AND date = ?", new String[] {"Assignment",dt});
        }
        return cursor.getCount();
    }

    Cursor read_exam(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
           cursor = db.rawQuery("Select * from Taskdetails where task = ?", new String[] {"Exam"});
        }
        return cursor;
    }

    Integer exam_count_date(String dt){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("Select * from Taskdetails where task = ? AND date = ?", new String[] {"Exam",dt});
        }
        return cursor.getCount();
    }

    Cursor read_lecture(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("Select * from Taskdetails where task = ?", new String[] {"Lecture"});
        }
        return cursor;
    }

    Integer lecture_count_date(String dt){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("Select * from Taskdetails where task = ? AND date = ?", new String[] {"Lecture",dt});
        }
        return cursor.getCount();
    }

    Cursor read(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("Select * from Taskdetails", null);
        }
        return cursor;
    }

    Integer count_date(String dt){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("Select * from Taskdetails where date = ?", new String[] {dt});
        }
        return cursor.getCount();
    }



    public Boolean deletedata(String task1, String title1){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Taskdetails where  task = ? AND title = ?", new String[] {task1,title1});
        if(cursor.getCount() > 0) {
            long result = db.delete("Taskdetails", "task = ? AND title = ?", new String[] {task1,title1});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }


}

