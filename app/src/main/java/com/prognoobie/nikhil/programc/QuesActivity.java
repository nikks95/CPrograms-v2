package com.prognoobie.nikhil.programc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class QuesActivity extends AppCompatActivity {
    DatabaseQuesAns db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques);
        db = new DatabaseQuesAns(this);
        try {
            String[] dd = this.getAssets().list("");
            for(int i=0;i<dd.length;i++)
               System.out.println(dd[i]);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
