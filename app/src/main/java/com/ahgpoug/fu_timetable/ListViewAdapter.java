package com.ahgpoug.fu_timetable;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahgpoug.fu_timetable.Classes.Class_o;
import com.ahgpoug.fu_timetable.Classes.Day;
import com.techsupportapp.UserProfileActivity;
import com.techsupportapp.databaseClasses.Ticket;
import com.techsupportapp.databaseClasses.User;
import com.techsupportapp.utility.GlobalsMethods;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<Class_o> {

    private final Context context;
    private final ArrayList<Class_o> values;

    public ListViewAdapter(Context context, ArrayList<Class_o> values) {
        super(context, R.layout.item_class, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_class, parent, false);
            holder = new ViewHolder();

            holder.classType = (TextView) convertView.findViewById(R.id.classType);
            holder.classTime = (TextView) convertView.findViewById(R.id.classTime);
            holder.className = (TextView) convertView.findViewById(R.id.className);
            holder.classNumber = (TextView) convertView.findViewById(R.id.classNumber);
            holder.classLecturer = (TextView) convertView.findViewById(R.id.classLecturer);
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.classType.setText(values.get(position).getClassType());
        holder.classTime.setText(values.get(position).getClassTime());
        holder.className.setText(values.get(position).getClassName());
        holder.classNumber.setText(values.get(position).getClassNumber());
        holder.classLecturer.setText(values.get(position).getClassLecturer());


        return convertView;
    }

    static class ViewHolder {
        private TextView classType;
        private TextView classTime;
        private TextView className;
        private TextView classNumber;
        private TextView classLecturer;

    }
}
