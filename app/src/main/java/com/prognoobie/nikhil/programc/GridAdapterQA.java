package com.prognoobie.nikhil.programc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

/**
 * Created by Nikhil on 12/29/2016.
 */

public class GridAdapterQA extends BaseAdapter {
    Context context;
    String[] topics;

    public GridAdapterQA(Context context, String[] topics) {
        this.context = context;
        this.topics = topics;
    }

    @Override
    public int getCount() {
        return topics.length;
    }

    @Override
    public Object getItem(int i) {
        return topics[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        String text = (String)getItem(i);
        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.topic_layout,null);

        }
        TextView tv = (TextView) view.findViewById(R.id.gridContent);
        tv.setText(text);

        return view;
    }
}
