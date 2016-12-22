package com.prognoobie.nikhil.programc;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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


    public ProgramWriter(File file)
    {
        this.file=file;
    }

    String[] getProgram()
    {

        return arg;
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
