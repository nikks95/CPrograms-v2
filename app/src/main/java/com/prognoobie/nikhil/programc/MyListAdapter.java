package com.prognoobie.nikhil.programc;

import android.accessibilityservice.GestureDescription;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Nikhil on 12/29/2016.
 */

public class MyListAdapter extends BaseExpandableListAdapter {
    Context context;
    List<String> ques;
    Map<String, List<String>> ans;

    public MyListAdapter(Context context, List<String> ques, Map<String, List<String>> ans) {
        this.context = context;
        this.ques = ques;
        this.ans = ans;
    }

    @Override
    public int getGroupCount() {
        return ques.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return ans.get(ques.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return ques.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return ans.get(ques.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        String ques = (String) getGroup(i);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_ques,null);
        }
        TextView textView = (TextView) view.findViewById(R.id.parent_text);
        textView.setText(ques);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String ans = (String) getChild(i,i1);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_ans,null);
        }
        TextView textView = (TextView) view.findViewById(R.id.child_ans);
        textView.setText(ans);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
