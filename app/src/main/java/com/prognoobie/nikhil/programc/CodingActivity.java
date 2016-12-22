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
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CodingActivity extends AppCompatActivity {

    static ArrayList<String> al;
    ArrayList listArray = null;
    String searchString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coding);
        final EditText searchBar = (EditText) findViewById(R.id.coding_searchBar);
        final Button searchView = (Button) findViewById(R.id.coding_search);
        LinearLayout codingLayout = (LinearLayout) findViewById(R.id.coding_program);
        //initials();
        al = new ArrayList<>();
        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchBar.setText("");
            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchString = searchBar.getText().toString();
                ArrayAdapter adapter=null;
                ListView listView =null;
                        listView=(ListView) findViewById(R.id.programList);
               if(listArray!=null)
                    listArray.clear();
                listArray=getListPrograms(searchString);

                adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.list_view_type, listArray);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

    }



    //Array for ListView
    ArrayList  getListPrograms(String searchString)
    {
        ArrayList<String> al1 = new ArrayList<>();
        String[] programsInAssests;
        ArrayList<String> nothing=new ArrayList<>();
        nothing.add("NO MATCH FOUND");
        try {
            programsInAssests = getAssets().list("Programs");
            for(int i=0;i<programsInAssests.length;i++)
            {
                al.add(programsInAssests[i]);
                al1.add(programsInAssests[i]);
            }

        if(searchString.equals(""))
        {

            return al1;
        }
        else {
            ArrayList<String> arr = new ArrayList<String>();
            String[] ar;
            String temp;
            StringTokenizer st ;
            boolean flag;
            boolean searchFlag = false;
            for(int i=0,j=0;i<al.size();i++)
            {   flag=false;
                temp =al.get(i);
                st=new StringTokenizer(temp," ");
                while(st.hasMoreTokens())
                {
                    if(temp.contains(st.nextToken()))
                    {
                        flag=true;
                        searchFlag=true;
                    }
                }

                if(flag)
                {
                    arr.add( al.get(i));
                }



            }
            if(!searchFlag) {
               arr = new ArrayList<String>();
                arr.add("NO MATCH FOUND");
            }

            return arr;

        }

               }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return nothing;
    }


}
