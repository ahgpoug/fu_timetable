/*package com.ahgpoug.fu_timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ahgpoug.fu_timetable.Classes.Day;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<Day> {
    private static final int TYPE_1 = 0;
    private static final int TYPE_2 = 1;

    private final Context context;
    private final ArrayList<Day> values;
    private final ArrayList<Integer> arr;

    public ListViewAdapter(Context context, ArrayList<Day> values) {
        super(context, R.layout.item_class, values);
        this.context = context;
        this.values = values;
        this.arr = new ArrayList<Integer>();
        fillPositions();
    }

    private void fillPositions(){
        int start = 0;
        arr.add(0);
        for (int i = 0; i < values.size() - 1; i++){
            arr.add(start + values.get(i).getListOfClasses().size() + 1);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        int newPosition;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> list2 = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < arr.size(); i++){
            for (int j = 0; j < values.get(i).getListOfClasses().size(); j++)
                list1.add(j);
            list2.add(list1);
        }

        for (int i = 0; i < list2.size(); i++)
            if (list2.get(i).contains(position))
                new

        switch (type) {
            case TYPE_1:
                convertView = inflater.inflate(R.layout.item_header, null);

                break;
            case TYPE_2:
                convertView = inflater.inflate(R.layout.item_class, null);

                TextView classType = (TextView)convertView.findViewById(R.id.classType);
                TextView classTime = (TextView)convertView.findViewById(R.id.classTime);
                TextView className = (TextView)convertView.findViewById(R.id.className);
                TextView classNumber = (TextView)convertView.findViewById(R.id.classNumber);
                TextView classLecturer = (TextView)convertView.findViewById(R.id.classLecturer);

                classType.setText(values.get());
                break;
            default:
        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position)
    {
        if (arr.contains(position))
            return TYPE_1;
        else
            return TYPE_2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return (values.size() + arr.size());
    }
}*/
