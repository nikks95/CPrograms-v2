package com.prognoobie.nikhil.programc;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikhil on 12/22/2016.
 */

public class ProgramWriter {

    File file;

    private Context context;

    static int progid=-1;

    ArrayList<String> al;

    String[] arg=new String[3];
    String FileName;



    public ProgramWriter(Context context)
    {
        this.context=context;
    }

    String[] getProgram(InputStream inputStream,String header)
    {
        String line;
        StringBuilder text2 =new StringBuilder(),text1 = new StringBuilder();
        boolean writerFlag = true;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            this.arg[0]=header;


            while ((line = reader.readLine()) != null) {

                    if(!line.equals("====")&&!line.equals("****")){
                        if(line.length()>0 && (line.charAt(0)=='~'|| !writerFlag))
                           {
                                writerFlag=false;
                                text2.append(line);
                                text2.append('\n');
                           }

                        else{
                                writerFlag = true;
                                text1.append(line);
                                text1.append('\n');}}

                    else
                        {
                            break;
                        }
                            }
            reader.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
        arg[1] = text1.toString();
        arg[2] = text2.toString();

        return arg;
    }


    static int getIndex(InputStream inputStream){
        int index=0;
        String line;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
           boolean getInt=false;


            while ((line = reader.readLine()) != null) {
             if(line.equals("===="))
             {
                 getInt = true;

             }
                if(getInt&&!line.equals("====")){
                    index=Integer.parseInt(line);
                    getInt = false;
                }

            }
            reader.close();



        } catch (IOException e) {
            e.printStackTrace();
        }


        return index;

    }
    String getKeyWords(InputStream inputStream)
    {
        String keyword="";
        String line;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            boolean getKey=false;


            while ((line = reader.readLine()) != null) {
                if(line.equals("****"))
                {
                    getKey = true;

                }
                if(getKey&&!line.equals("****")){
                    keyword =line;
                    getKey = false;
                }

            }
            reader.close();



        } catch (IOException e) {
            e.printStackTrace();
        }


        return keyword;
    }

    ArrayList<String> getQuesAns(InputStream inputStream)
    {
        ArrayList<String> al = new ArrayList<>();
        String line;
        StringBuilder text =new StringBuilder();

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while((line=reader.readLine())!=null){
                if(line.equals("***************************************")||line.equals("========================================"))
                {
                    al.add(text.toString());
                    text = new StringBuilder();

                }
                else {
                    text.append(line);
                    text.append('\n');
                }

            }

        } catch (Exception e)
        {
            e.printStackTrace();
            al.add("NULL OUTPUT");
        }

        return al;
    }

    public static void setProgID(int id)
    {
        progid=id;
    }
    public static int getprogID()
    {
        return progid;
    }
}
