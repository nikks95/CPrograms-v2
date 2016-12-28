package com.prognoobie.nikhil.programc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nikhil on 12/28/2016.
 */

public class DatabaseQuesAns extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="QandA.db";
    public static final String TABLE_NAME="QuesAns";
    public static final String col_1="ID";
    public static final String col_2="QUES";
    public static final String col_3="ANS";
    public static final String col_4="GROUP";

    public DatabaseQuesAns(Context context) {
        super(context,DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,QUES TEXT,ANS TEXT,GROUP TEXT)");
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
