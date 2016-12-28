package com.prognoobie.nikhil.programc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuesActivity extends AppCompatActivity {
    ExpandableListAdapter adapter;
    ExpandableListView listView;
    List<String> ques;
    Map<String,List<String>> ans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques);

        listView = (ExpandableListView)findViewById(R.id.expandables_list);
        fillData();
        adapter = new MyListAdapter(this,ques,ans);
        listView.setAdapter(adapter);
    }
    void fillData()
    {
        ques = new ArrayList<String>();
        ans = new HashMap<String,List<String>>();
        ques.add("Hello");
        List<String> list = new ArrayList<>();
        list.add("YO");
        ans.put(ques.get(0),list);
    }
}
