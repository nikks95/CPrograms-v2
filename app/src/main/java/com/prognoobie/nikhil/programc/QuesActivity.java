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

    ArrayList<String> arrayList;
    List<String> ques;
    Map<String,List<String>> ans;

    ProgramWriter writer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques);
        writer = new ProgramWriter(this);
        listView = (ExpandableListView)findViewById(R.id.expandables_list);
        try {
            arrayList = writer.getQuesAns(this.getAssets().open("Programs/Quesans/Intro"));
        } catch (IOException e) {
            e.printStackTrace();
            arrayList = new ArrayList<>();
            arrayList.add("Nothing");
        }
        fillData(arrayList);
        adapter = new MyListAdapter(this,ques,ans);
        listView.setAdapter(adapter);
    }
    void fillData(ArrayList<String> al)
    {
        ques = new ArrayList<String>();
        ans = new HashMap<String,List<String>>();
        //ques.add("Hello");
       /* List<String> list = new ArrayList<>();
        list.add("YO");
        ans.put(ques.get(0),list);
        */
        for(int i=0;i<al.size();i++)
        {
            if(i%2==0)
            {
                ques.add(al.get(i));
            }
            else {
                System.out.println();
                List<String> list = new ArrayList<>();
                list.add(al.get(i));
                ans.put(al.get(i-1),list);
            }
        }
    }
}
