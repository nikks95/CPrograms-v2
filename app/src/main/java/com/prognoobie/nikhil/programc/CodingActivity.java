package com.prognoobie.nikhil.programc;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class CodingActivity extends AppCompatActivity {

    static ArrayList<String> al;
    ArrayList<String> al1=null;
    static String filename="";
    static String str = "";
    Map<String,List<String>> map;
    ArrayList<String> listArray = null;
    ProgramWriter writer;

    String[] program = new String[3];

    boolean downloadFlag= false;
    int backFlag = 0;
    boolean searchBarVisibilityFlag = false;


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


        final EditText searchText = (EditText) findViewById(R.id.search_bar);
        searchText.setVisibility(View.GONE);
        //searchText.setTag(searchText.getKeyListener());
        //searchText.setKeyListener(null);
        final ImageView searchImage = (ImageView) findViewById(R.id.searchProg);
        final LinearLayout codingLayout = (LinearLayout) findViewById(R.id.coding_program);
        final ImageView downloadImage = (ImageView) findViewById(R.id.download_code);
        final TextView textView = (TextView) findViewById(R.id.coding_search);
        final ImageView shareImage = (ImageView) findViewById(R.id.share_code);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.list_view_type,listArray);

        listView.setAdapter(adapter);

        System.out.println(al1);

        adapter.notifyDataSetChanged();

        final TextView header= (TextView) findViewById(R.id.program_header);
        final TextView mainProgram = (TextView) findViewById(R.id.program_main);
        final TextView outPut= (TextView) findViewById(R.id.program_output);


        header.setText("");
        mainProgram.setText("");
        outPut.setText("");
        //*************** **************** **************** **************** ******************* ************
        searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(searchText.getText().toString().equals("type program to be searched here"))
                searchText.setText("");
            }
        });
        //* ********************** ***************** ****************** ****************** ******************
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(searchBarVisibilityFlag) {
                  searchList(searchText,listView,listArray);
                }
                else
                {
                    searchBarVisibilityFlag=true;
                    searchText.setVisibility(View.VISIBLE);
                }
            }
        });
        //* ******************* *  * * * * * * * ****************************************** * * * * * *******
        searchText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i==keyEvent.KEYCODE_ENTER)
                {
                    searchList(searchText,listView,listArray);
                }
                else if(i==keyEvent.KEYCODE_BACK)
                {
                    Intent intent;
                        intent = new Intent(getApplicationContext(),MainActivity.class);




                    startActivity(intent);
                            finish();
                }
                return true;
            }
        });


       //***** ********** * * * * * * * * * * * ********************* ** * * * * *  **************************
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
                searchImage.setVisibility(View.GONE);
                searchText.setVisibility(View.GONE);

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

        //************************** * ************************* ** ********************* *** *************
        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater li = LayoutInflater.from(CodingActivity.this);
                View promptsView = li.inflate(R.layout.shareprompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        CodingActivity.this);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
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

        //************************ ****** ************************** *********** ************************** *****************
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

//***************************************************************************************************************************************************************************



    // search programs list function
    ArrayList<String> searchPrograms(ArrayList<String> list,String searchedKeywords)
    {
        String token;
        String programName;
        String keywords ="";
        writer=new ProgramWriter(CodingActivity.this);

        ArrayList<String> matchedPrograms = new ArrayList<>();
        ArrayList<String> al;

        boolean flag=false;

        map = new HashMap<>();



        for(int i=0;i<list.size();i++)
        {
            al = new ArrayList<>();
            programName = list.get(i);

            try {
                keywords =writer.getKeyWords(CodingActivity.this.getAssets().open("Programs/"+programName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(programName+" "+keywords);

            StringTokenizer st =  new StringTokenizer(programName+" "+keywords," ");
            while(st.hasMoreTokens())
            { token= st.nextToken();
                al.add(token.toUpperCase());
               // System.out.println(token+" ADDING");
            }
            map.put(programName,al);

        }
        StringTokenizer stringTokenizer = new StringTokenizer(searchedKeywords," ");
        while(stringTokenizer.hasMoreTokens())
        {
            token=stringTokenizer.nextToken();
            for(int i=0;i<map.size();i++) {
                al = new ArrayList<>();

                al = (ArrayList<String>) map.get(list.get(i));
                if (al.contains(token.toUpperCase()))
                {  // System.out.println(token+al+map.keySet().toArray()[i]+i);
                    matchedPrograms.add(list.get(i));
                    flag = true;
                }
                else continue;
            }
        }


    if(flag)
        return matchedPrograms;
else {
        matchedPrograms.add("Nothing");
        return matchedPrograms;
    }
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //Array for ListView
    ArrayList<String>  getListPrograms()
    {
        al = new ArrayList<>();
       // int index;
        String item;

        ArrayList<String> arrayList = new ArrayList<>();
        try {
            String[] listOfPrograms = this.getAssets().list("Programs");
            String[] indexesOfPrograms = new String[listOfPrograms.length];

            for(int i=0;i<listOfPrograms.length;i++)
            {
                int index;
                item = listOfPrograms[i];
                if(listOfPrograms[i].equals("Quesans")){
                    index = listOfPrograms.length-1;
                }
                else{index =ProgramWriter.getIndex(CodingActivity.this.getAssets().open("Programs/"+item));
                }
                indexesOfPrograms[index]=listOfPrograms[i];
            }


            for(int i=0;i<indexesOfPrograms.length;i++)
            {
                System.out.println("I: "+i+" "+indexesOfPrograms[i]);

                if(!indexesOfPrograms[i].equals("Quesans"))
                     {
                           al.add(indexesOfPrograms[i]);
                            arrayList.add(indexesOfPrograms[i]);
                     }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;

    }

//************************************************************************************************************************************************************************
    void searchList(EditText searchText,ListView listView,ArrayList<String> listArray)
    {
        String searchString = searchText.getText().toString();
        if (searchString.equals("")) {
            al1 = listArray;
        } else {
            al1 = searchPrograms(listArray, searchString);

        }
        ArrayAdapter adapter1 = new ArrayAdapter<>(CodingActivity.this, R.layout.list_view_type, al1);

        listView.setAdapter(adapter1);

        adapter1.notifyDataSetChanged();
    }


}
