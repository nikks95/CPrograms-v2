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
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CodingActivity extends AppCompatActivity {

    static ArrayList<String> al;
    ArrayList<String> listArray = null;
    ProgramWriter writer;
    String[] program = new String[3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coding);
        //ListView
        if(al!=null)
        {
            al.clear();
        }
        if(listArray!=null)
        {
            listArray.clear();
        }
        final ListView listView = (ListView) findViewById(R.id.programList);
        listArray = this.getListPrograms();
        final LinearLayout codingLayout = (LinearLayout) findViewById(R.id.coding_program);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.list_view_type,listArray);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        final TextView header= (TextView) findViewById(R.id.program_header);
        final TextView mainProgram = (TextView) findViewById(R.id.program_main);
        final TextView outPut= (TextView) findViewById(R.id.program_output);

        header.setText("");
        mainProgram.setText("");
        outPut.setText("");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String item = listView.getItemAtPosition(position).toString();
                Toast.makeText(CodingActivity.this,"You selected : " + item,Toast.LENGTH_SHORT).show();

                writer=new ProgramWriter(CodingActivity.this);

                listView.setVisibility(View.GONE);
                codingLayout.setVisibility(View.VISIBLE);

                try {
                    program =writer.getProgram(CodingActivity.this.getAssets().open("Programs/"+item),item);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                header.setText(program[0]);
                mainProgram.setText(program[1]);
                outPut.setText(program[2]);


            }
        });


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
