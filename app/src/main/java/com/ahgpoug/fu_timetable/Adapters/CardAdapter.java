package com.ahgpoug.fu_timetable.Adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahgpoug.fu_timetable.Classes.Day;
import com.ahgpoug.fu_timetable.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private Context mContext;
    private List<Day> dayList;
    private ClassAdapter adapter;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public RecyclerView recyclerView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
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
        try {
            Day day = dayList.get(position);
            adapter = new ClassAdapter(mContext, day.getListOfClasses());
            SimpleDateFormat fmtIn = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat fmtOut = new SimpleDateFormat("dd MMMM, EEEE");

            Date date = fmtIn.parse(day.getDayDate());

            holder.title.setText(fmtOut.format(date));

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            mLayoutManager.setAutoMeasureEnabled(true);
            holder.recyclerView.setLayoutManager(mLayoutManager);
            holder.recyclerView.setHasFixedSize(false);
            holder.recyclerView.addItemDecoration(new SimpleDividerItemDecoration(mContext));
            holder.recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }
}