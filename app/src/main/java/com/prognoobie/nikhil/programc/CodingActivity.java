package com.prognoobie.nikhil.programc;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CodingActivity extends AppCompatActivity {

    static ArrayList<String> al;
    static String filename="";
    static String str = "";
    ArrayList<String> listArray = null;
    ProgramWriter writer;

    String[] program = new String[3];

    boolean downloadFlag= false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coding);

      //  Toolbar toolbar = (Toolbar)findViewById(R.id.ttoolbar);
       // setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("PROGRAMS");
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
        final ImageView downloadImage = (ImageView) findViewById(R.id.download_code);
        final TextView textView = (TextView) findViewById(R.id.coding_search);
        final ImageView shareImage = (ImageView) findViewById(R.id.share_code);

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

                downloadImage.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                textView.setText("Share and Download Program");
                listView.setVisibility(View.GONE);
                codingLayout.setVisibility(View.VISIBLE);
                shareImage.setVisibility(View.VISIBLE);

                try {
                    program =writer.getProgram(CodingActivity.this.getAssets().open("Programs/"+item),item);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                header.setText(program[0]);
                mainProgram.setText(program[1]);
                outPut.setText(program[2]);

                str=program[1];

            }
        });
        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String total_data  = str;
                Intent sharewhatsappIntent = new Intent(Intent.ACTION_SEND);
                sharewhatsappIntent.setType("text/plain");
                sharewhatsappIntent.setPackage("com.whatsapp");
                sharewhatsappIntent.putExtra(Intent.EXTRA_TEXT, total_data);

                try {
                    startActivity(sharewhatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {

                }
            }
        });
       downloadImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               LayoutInflater li = LayoutInflater.from(CodingActivity.this);
               View promptsView = li.inflate(R.layout.prompts, null);

               AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                       CodingActivity.this);

               // set prompts.xml to alertdialog builder
               alertDialogBuilder.setView(promptsView);

               final EditText userInput = (EditText) promptsView
                       .findViewById(R.id.editTextDialogUserInput);

               // set dialog message
               alertDialogBuilder
                       .setCancelable(false)
                       .setPositiveButton("OK",
                               new DialogInterface.OnClickListener() {
                                   public void onClick(DialogInterface dialog,int id) {
                                       // get user input and set it to result
                                       // edit text
                                      filename= userInput.getText().toString();
                                       File mydir = new File("/sdcard/CPrograms/"); //Creating an internal dir;
                                       if (!mydir.exists())
                                       {
                                           mydir.mkdirs();
                                           Toast.makeText(CodingActivity.this, "Directory created",
                                                   Toast.LENGTH_SHORT).show();
                                       }

                                       try{
                                           filename = filename+".c";
                                           File cFile = new File(mydir, filename);
                                           FileWriter writer = new FileWriter(cFile);
                                           writer.append(str);
                                           writer.flush();
                                           writer.close();
                                           Toast.makeText(CodingActivity.this, "File saved successfully!",
                                                   Toast.LENGTH_LONG).show();

                                       }catch (Exception e){

                                       }
                                   }
                               })
                       .setNegativeButton("Cancel",
                               new DialogInterface.OnClickListener() {
                                   public void onClick(DialogInterface dialog,int id) {
                                       dialog.cancel();
                                   }
                               });

               // create alert dialog
               AlertDialog alertDialog = alertDialogBuilder.create();

               // show it
               alertDialog.show();











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
            {   if(!listOfPrograms[i].equals("Quesans"))
            {al.add(listOfPrograms[i]);
                arrayList.add(listOfPrograms[i]);}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }


}
