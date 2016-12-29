package com.prognoobie.nikhil.programc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuesActivity extends AppCompatActivity {
    ExpandableListAdapter adapter;
    ExpandableListView listView;
    GridView gridView;


    ArrayList<String> arrayList;
    List<String> ques;
    Map<String,List<String>> ans;

    String[] topics;
    static String fileName ="";

    ProgramWriter writer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques);

        writer = new ProgramWriter(this);
        listView = (ExpandableListView)findViewById(R.id.expandables_list);


        gridView = (GridView) findViewById(R.id.gridList);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    String[] topics =   QuesActivity.this.getAssets().list("Programs/Quesans");
                    QuesActivity.fileName= topics[i];
                    gridView.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    arrayList = writer.getQuesAns(QuesActivity.this.getAssets().open("Programs/Quesans/"+fileName));
                    fillData(arrayList);

                    adapter = new MyListAdapter(QuesActivity.this,ques,ans);
                    listView.setAdapter(adapter);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            topics = this.getAssets().list("Programs/Quesans");

        } catch (Exception e) {
            e.printStackTrace();
            arrayList = new ArrayList<>();
            arrayList.add("Nothing");
        }


        GridAdapterQA  adapterQA= new GridAdapterQA(this,topics);
        gridView.setAdapter(adapterQA);



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
