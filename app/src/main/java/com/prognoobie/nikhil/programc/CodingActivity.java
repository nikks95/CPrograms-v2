package com.prognoobie.nikhil.programc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CodingActivity extends AppCompatActivity {

    static ArrayList<String> al;
    ArrayList<String> listArray = null;
    String searchString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coding);
        //ListView
        ListView listView = (ListView) findViewById(R.id.programList);
        listArray = this.getListPrograms();
        LinearLayout codingLayout = (LinearLayout) findViewById(R.id.coding_program);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_view_type,listArray);
        listView.setAdapter(adapter);


    }



    //Array for ListView
    ArrayList<String>  getListPrograms()
    {   al = new ArrayList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            String[] listOfPrograms = this.getAssets().list("Programs");
            for(int i=0;i<listOfPrograms.length;i++)
            {
                al.add(listOfPrograms[i]);
                arrayList.add(listOfPrograms[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }


}
