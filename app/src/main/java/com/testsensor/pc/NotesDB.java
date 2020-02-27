package com.testsensor.pc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class NotesDB extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "ACR";
    public static final String ID = "_id";
    public static final String CURRENTAC = "currentac";
    public static final String TIME = "time";
    public static final String STIME = "stime";
    public static final String ETIME = "etime";
    public static final String TABLE = "TABLE";
    private SQLiteDatabase dbWriter;

    public NotesDB(Context context){
        super(context,"sensors",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        System.out.println("Good");

        db.execSQL("Create "+TABLE+ " "+TABLE_NAME + "( "
            +ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CURRENTAC+ " TEXT , "
                + TIME +" TEXT  ," + STIME + " TEXT  , "+ETIME+ " TEXT ) "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
