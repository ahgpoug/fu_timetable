package com.ahgpoug.fu_timetable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ahgpoug.fu_timetable.Classes.Day;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private Context mContext;
    private List<Day> dayList;
    private ListViewAdapter adapter;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ListView listView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            listView = (ListView) view.findViewById(R.id.listView);
        }
    }


    public CardAdapter(Context mContext, List<Day> dayList) {
        this.mContext = mContext;
        this.dayList = dayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Day day = dayList.get(position);
        holder.title.setText(day.getDayDate());
        adapter = new ListViewAdapter(mContext, day.getListOfClasses());

        holder.listView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }
}