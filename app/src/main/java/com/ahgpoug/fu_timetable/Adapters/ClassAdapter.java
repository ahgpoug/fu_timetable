package com.ahgpoug.fu_timetable.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahgpoug.fu_timetable.Classes.Class_o;
import com.ahgpoug.fu_timetable.R;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Class_o> values;

    public ClassAdapter(Context mContext, ArrayList<Class_o> values) {
        this.mContext = mContext;
        this.values = values;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView classType;
        public TextView classTime;
        public TextView className;
        public TextView classNumber;
        public TextView classLecturer;

        public ViewHolder(View view) {
            super(view);
            classType = (TextView) view.findViewById(R.id.classType);
            classTime = (TextView) view.findViewById(R.id.classTime);
            className = (TextView) view.findViewById(R.id.className);
            classNumber = (TextView) view.findViewById(R.id.classNumber);
            classLecturer = (TextView) view.findViewById(R.id.classLecturer);
        }
    }

    @Override
    public ClassAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.classType.setText(values.get(position).getClassType());
        holder.classTime.setText(values.get(position).getClassTime());
        holder.className.setText(values.get(position).getClassName());
        holder.classNumber.setText(values.get(position).getClassNumber());
        holder.classLecturer.setText(values.get(position).getClassLecturer());
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}